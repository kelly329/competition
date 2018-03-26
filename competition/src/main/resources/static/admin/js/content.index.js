/**
 * Created by Chan on 17/5/17.
 */
function getDay(yesterday) {
    var date = new Date();
    var day;
    var month;
    if (!yesterday) {
        day = date.getDate();
        month = date.getMonth() + 1;
    } else {
        var preDate = new Date(date.getTime() - 24 * 60 * 60 * 1000); //前一天
        day = preDate.getDate();
        month = preDate.getMonth() + 1;
    }
    var year = date.getFullYear();
    return year + "-" + month + "-" + day;
}


function Match() {
    var userAgent = navigator.userAgent,
        rMsie = /(msie\s|trident.*rv:)([\w.]+)/,
        rFirefox = /(firefox)\/([\w.]+)/,
        rOpera = /(opera).+version\/([\w.]+)/,
        rChrome = /(chrome)\/([\w.]+)/,
        rSafari = /version\/([\w.]+).*(safari)/;
    var browser;
    var version;
    var ua = userAgent.toLowerCase();
    var match = rMsie.exec(ua);
    if (match != null) {
        return {browser: "IE", version: match[2] || "0"};
    }
    var match = rFirefox.exec(ua);
    if (match != null) {
        return {browser: match[1] || "", version: match[2] || "0"};
    }
    var match = rOpera.exec(ua);
    if (match != null) {
        return {browser: match[1] || "", version: match[2] || "0"};
    }
    var match = rChrome.exec(ua);
    if (match != null) {
        return {browser: match[1] || "", version: match[2] || "0"};
    }
    var match = rSafari.exec(ua);
    if (match != null) {
        return {browser: match[2] || "", version: match[1] || "0"};
    }
    if (match != null) {
        return {browser: "", version: "0"};
    }
}

function initBrowserVersion() {
    var browserMatch = Match();
    alert(browserMatch.browser + '--' + browserMatch.version);
    if (browserMatch.browser) {
        if (browserMatch.browser == "IE" && browserMatch.version <= 10) {
            alert("浏览器版本太低，请升级，以免影响使用体验！");
            return false;
        }
        else if (browserMatch.browser == "chrome" && 30 > browserMatch.version.substring(0, browserMatch.version.indexOf("."))) {
            alert("浏览器版本太低，请升级，以免影响使用体验！");
            return false;
        }
        else if (browserMatch.browser == "firefox" && 40 > browserMatch.version.substring(0, browserMatch.version.indexOf("."))) {
            alert("浏览器版本太低，请升级，以免影响使用体验！");
            return false;
        }
    }
    var flag=initBrowserVersion();
    if(!flag){
        console.log(flag);
        window.location.href="{:U('index/IEWarning')}";
    }
}