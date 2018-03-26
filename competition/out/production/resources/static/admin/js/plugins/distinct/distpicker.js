/*!
 * Distpicker v@VERSION
 * https://github.com/fengyuanchen/distpicker
 *
 * Copyright (c) 2014-@YEAR Fengyuan Chen
 * Released under the MIT license
 *
 * Date: @DATE
 */

(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as anonymous module.
        define(['jquery', 'ChineseDistricts'], factory);
    } else if (typeof exports === 'object') {
        // Node / CommonJS
        factory(require('jquery'), require('ChineseDistricts'));
    } else {
        // Browser globals.
        factory(jQuery, ChineseDistricts);
    }
})(function ($j, ChineseDistricts) {

    'use strict';

    if (typeof ChineseDistricts === 'undefined') {
        throw new Error('The file "distpicker.data.js" must be included first!');
    }

    var NAMESPACE = 'distpicker';
    var EVENT_CHANGE = 'change.' + NAMESPACE;
    var PROVINCE = 'province';
    var CIRY = 'city';
    var DISTRICT = 'district';

    function Distpicker(element, options) {
        this.$jelement = $j(element);
        this.options = $j.extend({}, Distpicker.DEFAULTS, $j.isPlainObject(options) && options);
        this.placeholders = $j.extend({}, Distpicker.DEFAULTS);
        this.active = false;
        this.init();
    }

    Distpicker.prototype = {
        constructor: Distpicker,

        init: function () {
            var options = this.options;
            var $jselect = this.$jelement.find('select');
            var length = $jselect.length;
            var data = {};

            $jselect.each(function () {
                $j.extend(data, $j(this).data());
            });

            $j.each([PROVINCE, CIRY, DISTRICT], $j.proxy(function (i, type) {
                if (data[type]) {
                    options[type] = data[type];
                    this['$j' + type] = $jselect.filter('[data-' + type + ']');
                } else {
                    this['$j' + type] = length > i ? $jselect.eq(i) : null;
                }
            }, this));

            this.bind();

            // Reset all the selects (after event binding)
            this.reset();

            this.active = true;
        },

        bind: function () {
            if (this.$jprovince) {
                this.$jprovince.on(EVENT_CHANGE, (this._changeProvince = $j.proxy(function () {
                    this.output(CIRY);
                    this.output(DISTRICT);
                }, this)));
            }

            if (this.$jcity) {
                this.$jcity.on(EVENT_CHANGE, (this._changeCity = $j.proxy(function () {
                    this.output(DISTRICT);
                }, this)));
            }
        },

        unbind: function () {
            if (this.$jprovince) {
                this.$jprovince.off(EVENT_CHANGE, this._changeProvince);
            }

            if (this.$jcity) {
                this.$jcity.off(EVENT_CHANGE, this._changeCity);
            }
        },

        output: function (type) {
            var options = this.options;
            var placeholders = this.placeholders;
            var $jselect = this['$j' + type];
            var districts = {};
            var data = [];
            var code;
            var matched;
            var value;

            if (!$jselect || !$jselect.length) {
                return;
            }

            value = options[type];

            code = (
                type === PROVINCE ? 86 :
                    type === CIRY ? this.$jprovince && this.$jprovince.find(':selected').data('code') :
                        type === DISTRICT ? this.$jcity && this.$jcity.find(':selected').data('code') : code
            );

            districts = $j.isNumeric(code) ? ChineseDistricts[code] : null;

            if ($j.isPlainObject(districts)) {
                $j.each(districts, function (code, address) {
                    var selected = address === value;

                    if (selected) {
                        matched = true;
                    }

                    data.push({
                        code: code,
                        address: address,
                        selected: selected
                    });
                });
            }

            if (!matched) {
                if (data.length && (options.autoSelect || options.autoselect)) {
                    data[0].selected = true;
                }

                // Save the unmatched value as a placeholder at the first output
                if (!this.active && value) {
                    placeholders[type] = value;
                }
            }

            // Add placeholder option
            if (options.placeholder) {
                data.unshift({
                    code: '',
                    address: placeholders[type],
                    selected: false
                });
            }

            $jselect.html(this.getList(data));
        },

        getList: function (data) {
            var list = [];

            $j.each(data, function (i, n) {
                list.push(
                    '<option' +
                    ' value="' + (n.address && n.code ? n.address : '') + '"' +
                    ' data-code="' + (n.code || '') + '"' +
                    (n.selected ? ' selected' : '') +
                    '>' +
                    (n.address || '') +
                    '</option>'
                );
            });

            return list.join('');
        },

        reset: function (deep) {
            if (!deep) {
                this.output(PROVINCE);
                this.output(CIRY);
                this.output(DISTRICT);
            } else if (this.$jprovince) {
                this.$jprovince.find(':first').prop('selected', true).trigger(EVENT_CHANGE);
            }
        },

        destroy: function () {
            this.unbind();
            this.$jelement.removeData(NAMESPACE);
        }
    };

    Distpicker.DEFAULTS = {
        autoSelect: true,
        placeholder: true,
        province: '—— 省 ——',
        city: '—— 市 ——',
        district: '—— 区 ——'
    };

    Distpicker.setDefaults = function (options) {
        $j.extend(Distpicker.DEFAULTS, options);
    };

    // Save the other distpicker
    Distpicker.other = $j.fn.distpicker;

    // Register as jQuery plugin
    $j.fn.distpicker = function (option) {
        var args = [].slice.call(arguments, 1);

        return this.each(function () {
            var $jthis = $j(this);
            var data = $jthis.data(NAMESPACE);
            var options;
            var fn;

            if (!data) {
                if (/destroy/.test(option)) {
                    return;
                }

                options = $j.extend({}, $jthis.data(), $j.isPlainObject(option) && option);
                $jthis.data(NAMESPACE, (data = new Distpicker(this, options)));
            }

            if (typeof option === 'string' && $j.isFunction(fn = data[option])) {
                fn.apply(data, args);
            }
        });
    };

    $j.fn.distpicker.Constructor = Distpicker;
    $j.fn.distpicker.setDefaults = Distpicker.setDefaults;

    // No conflict
    $j.fn.distpicker.noConflict = function () {
        $j.fn.distpicker = Distpicker.other;
        return this;
    };

    $j(function () {
        $j('[data-toggle="distpicker"]').distpicker();
    });
});
