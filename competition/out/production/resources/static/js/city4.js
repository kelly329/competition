 $(function(){ //调用插件
        $.fn.citySelect();
    });
    $(function(){
        form1 = $('form[name=form1]'),
            prev = $('input[name=cho_Province]' , form1),
            city = $('input[name=cho_City]' , form1),
            area = $('input[name=cho_Area]' , form1),
            insu = $('input[name=cho_Insurer]',form1),
            vale = ['请选择学校','请选择学院','请选择专业','请选择入学年份'];
        form1.submit(function(){
            if(prev.val() == vale[0]){
                alert(vale[0]);
                return false;
            };
            if(city.val() == vale[1]){
                alert(vale[1]);
                return false;
            };
            if(area.val() == vale[2]){
                alert(vale[2]);
                return false;
            };
            if(insu.val() == vale[3]){
                alert(vale[3]);
                return false;
            }
        });
    });
    
(function($){
$.fn.citySelect = function(options) {  
 	var defaults = {  
 		setId : ['#Province' , '#City' , '#Area','#Insurer'],                //默认id
 		stval : ['请选择学校' , '请选择学院' , '请选择专业','请选择入学年份'],     //默认值
 		czemt : 'i',                        					  //存值元素
 		inpvt : 'input[name^="cho"]',                     		  //存值文本框	
 		intva : true											  //初始化所有下拉默认确定
 	},
 		opts = $.extend(defaults, options),
 			_setId = opts.setId,
 			_stval = opts.stval,
 			_czemt = opts.czemt,
 			_inpnt = opts.inpvt,
 			len = _setId.length;
 	$.fn.removelist = function(options){  //清空
 			var removdefa = {  
 			    removeAll : false,
 			    thisindex : 0
 			},
 		optremove = $.extend(removdefa, options);  
 	var $_removebox = $(this),
 		$_listall = $('ul li' , $_removebox),
 		$_listfirst = $('ul li:first' , $_removebox),
 		$_listsib = $('ul li:gt(0)' , $_removebox),
 		$_vala = $(_czemt , $_removebox),
 		$_valb = $(_inpnt , $_removebox);
 		if(optremove.removeAll){
 			$_listall.remove();
 		}else{
 			$_listsib.remove();
 		}
 		$_vala.text(_stval[optremove.thisindex]);
 		$_valb.val(_stval[optremove.thisindex]);
 		return this;
 	};
 	$.fn.appendlist = function(options){  //添加
 			var appdefa = {  
 		  	    theindex : '0'
 			},
 		optapp = $.extend(appdefa, options);
 		var $_appendbox = $(this),
 			$_listbox = $('ul' , $_appendbox),
 			appendArray = dsy.Items[optapp.theindex],
 			appList = '';
 		if(typeof(appendArray) == "undefined") return false; //如果不存在当前对象返回false
 		for(var i = 0; i<appendArray.length;i++){
 			appList += '<li><a href="javascript:void(0)" alt="'+appendArray[i]+'">'+appendArray[i]+'</a></li>';
 		}
 		$_listbox.append(appList);
 		appList = '';
 	};
 	function slide(str){ //下拉事件
 		var oID = $(str),
 		    oClass = 'active',
 		    oList = $('ul' , oID),
 		    closed;
 		oID.click(function(){
 		    $(this).toggleClass(oClass);
 		    oList.stop(true,true).slideToggle();
 		});
 		closed = function(){
 		    oID.removeClass(oClass);
 		    oList.stop(true,true).slideUp();
 		}
 		$("body").click(function(e){
 		    if(!$(e.target).is(str+" *")){
 		        closed();
 		    }
 		});
 	};
 	$.fn.liClick = function(){
 			var $_liA = $('li' , _setId[0]),
 				$_liB = $('li' , _setId[1]),
 				$_liC = $('li' , _setId[2]),
 				$_liD = $('li' , _setId[3]),
 				indA,indB,indC,indD;
 			$('li' , _setId[0]).live('click' , function(){ //省点击事件
 				indA = $('li' , _setId[0]).index(this) - 1;
 				var   _valA = $('a' , this).attr('alt'), 
 					_emeltA = $(_czemt , _setId[0]),
 					_inputA = $(_inpnt , _setId[0]);
 				_emeltA.text(_valA);
 				_inputA.val(_valA);
 				$(_setId[1]).removelist({thisindex : 1});
 				$(_setId[1]).appendlist({theindex:'0_'+indA});
 				$(_setId[2]).removelist({thisindex : 2});
 				$(_setId[3]).removelist({thisindex : 3});
 				return indA;
 			});
 			$('li' , _setId[1]).live('click' , function(){ //市点击事件
 				indB = $('li' , _setId[1]).index(this) - 1;
 				var   _valB = $('a' , this).attr('alt'), 
 					_emeltB = $(_czemt , _setId[1]),
 					_inputB = $(_inpnt , _setId[1]);
 				_emeltB.text(_valB);
 				_inputB.val(_valB);
 				$(_setId[2]).removelist({thisindex : 2});
 				$(_setId[2]).appendlist({theindex:'0_'+indA+'_'+indB});	
 				$(_setId[3]).removelist({thisindex : 3});
 				return indB;
 			});
 			$('li' , _setId[2]).live('click', function(){ //区点击事件
 				indC = $('li' , _setId[2]).index(this) - 1;
 				var   _valC = $('a' , this).attr('alt'), 
 					_emeltC = $(_czemt , _setId[2]),
 					_inputC = $(_inpnt , _setId[2]);
 				_emeltC.text(_valC);
 				_inputC.val(_valC);	
 				$(_setId[3]).removelist({thisindex : 3});
 				$(_setId[3]).appendlist({theindex:'0_'+indA+'_'+indB+'_'+indC});	
 				return indC;
 			});
 			$('li' , _setId[3]).live('click', function(){ //保险公司点击事件
 				indC = $('li' , _setId[3]).index(this);
 				var   _valD = $('a' , this).attr('alt'), 
 					_emeltD = $(_czemt , _setId[3]),
 					_inputD = $(_inpnt , _setId[3]);
 				_emeltD.text(_valD);
 				_inputD.val(_valD);					
 				return indD;
 			});	
 	};
 	function show(obj){   //大按钮事件
 		$(obj).toggleClass('active').find('ul').slideToggle();
 	}
 	if(opts.intva){
 		for(var i = 0 ; i < len; i++) {  //初始化默认值所有值
 			$(_setId[i]).removelist({thisindex : i});
 			slide(_setId[i]);
 		};			
 	}
 	$(_setId[0]).appendlist({theindex:'0'}); //默认添加省
 	$.fn.liClick();
};  
})(jQuery);