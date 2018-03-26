
document.writeln("<script type=\"text/javascript\"> ");
document.writeln("window.onload=function(){ ");
document.writeln("var linkbt=document.getElementById(\"linkbt\"); ");
document.writeln("var light=document.getElementById('light'); ");
document.writeln("var fade=document.getElementById('fade'); ");
document.writeln("var closebt=document.getElementById(\"closebt\"); ");
document.writeln("linkbt.onclick=function(){ ");
document.writeln("light.style.display='block'; ");
document.writeln("fade.style.display='block'; ");
document.writeln("} ");
document.writeln("closebt.onclick=function(){ ");
document.writeln("light.style.display='none'; ");
document.writeln("fade.style.display='none'; ");
document.writeln("} ");
document.writeln("} ");
document.writeln("</script>");






document.writeln("<script> ");
document.writeln("function show(){ ");
document.writeln("var box1 = document.getElementById(\"box1\"); ");
document.writeln("var text = box1.innerHTML; ");
document.writeln("var newBox1 = document.createElement(\"div\"); ");
document.writeln("var btn = document.createElement(\"a\"); ");
document.writeln("newBox1.innerHTML = text.substring(0,68); ");
document.writeln("btn.innerHTML = text.length > 50 ? \"...Show All\" : \"\"; ");
document.writeln("btn.href = \"###\"; ");
document.writeln("btn.onclick = function(){ ");
document.writeln("if (btn.innerHTML == \"...Show All\"){ ");
document.writeln("btn.innerHTML = \"...Hide\"; ");
document.writeln("newBox1.innerHTML = text; ");
document.writeln("}else{ ");
document.writeln("btn.innerHTML = \"...Show All\"; ");
document.writeln("newBox1.innerHTML = text.substring(0,68); ");
document.writeln("} ");
document.writeln("} ");
document.writeln("box1.innerHTML = \"\"; ");
document.writeln("box1.appendChild(newBox1); ");
document.writeln("box1.appendChild(btn); ");
document.writeln("} ");
document.writeln("show(); ");
document.writeln("</script> ");








document.writeln("<script> ");
document.writeln("function show(){  ");
document.writeln("var box2 = document.getElementById(\"box2\"); ");
document.writeln("var text = box2.innerHTML; ");
document.writeln("var newBox2 = document.createElement(\"div\"); ");
document.writeln("var btn = document.createElement(\"a\"); ");
document.writeln("newBox2.innerHTML = text.substring(0,200); ");
document.writeln("btn.innerHTML = text.length > 200 ? \"...Show All\" : \"\"; ");
document.writeln("btn.href = \"###\"; ");
document.writeln("btn.onclick = function(){ ");
document.writeln("if (btn.innerHTML == \"...Show All\"){ ");
document.writeln("btn.innerHTML = \"...Hide\"; ");
document.writeln("newBox2.innerHTML = text; ");
document.writeln("}else{ ");
document.writeln("btn.innerHTML = \"...Show All\"; ");
document.writeln("newBox2.innerHTML = text.substring(0,200); ");
document.writeln("} ");
document.writeln("} ");
document.writeln("box2.innerHTML = \"\"; ");
document.writeln("box2.appendChild(newBox2); ");
document.writeln("box2.appendChild(btn); ");
document.writeln("} ");
document.writeln("show(); ");
document.writeln("</script> ");









document.writeln("<script>  ");
document.writeln("function msgbox(n){");
document.writeln("document.getElementById('inputbox1').style.display=n?'block':'none';");
document.writeln("}");
document.writeln(" </script\>  ");

document.writeln("<script>  ");
document.writeln("function mgbox(n){");
document.writeln("document.getElementById('inputbox2').style.display=n?'block':'none';");
document.writeln("}");
document.writeln(" </script\>  ");







document.writeln("<script src=\"src/superplaceholder.js\"></script\>");
document.writeln("<script>");
document.writeln("superplaceholder({");
document.writeln("el: inp1,");
document.writeln("sentences: [ '在这里','海量比赛信息任你搜', '无论校内校外'],");
document.writeln("options: {");
document.writeln("    loop: true");
document.writeln("}");
document.writeln("})");
document.writeln("superplaceholder({");
document.writeln("el: inp3,");
document.writeln("sentences: [ '这里', '我们通过一个话题', '召唤一群拥有相同兴趣爱好的人士', '如果你有靠谱的脑洞','欢迎一起七嘴八舌' ],");
document.writeln("options: {");
document.writeln("letterDelay: 80,");
document.writeln("loop: true,");
document.writeln("startOnFocus: false");
document.writeln("}");
document.writeln("})");
document.writeln("</script>");













document.writeln("<script>");
document.writeln("$('.ck-slide').ckSlide({");
document.writeln("    autoPlay: true,//默认为不自动播放，需要时请以此设置");
document.writeln("    dir: 'x',//默认效果淡隐淡出，x为水平移动，y 为垂直滚动");
document.writeln("    interval:3000//默认间隔2000毫秒");
document.writeln("});");
document.writeln("</script> ");










document.writeln("<script type=\"text/javascript\">");
document.writeln("$(document).ready(function(){");
document.writeln("    //Full Caption Sliding (Hidden to Visible)");
document.writeln("    $('.boxgrid.captionfull').hover(function(){");
document.writeln("        $(\".cover\", this).stop().animate({top:'160px'},{queue:false,duration:160});");
document.writeln("    }, function() {");
document.writeln("        $(\".cover\", this).stop().animate({top:'260px'},{queue:false,duration:160});");
document.writeln("    });");
document.writeln("});");
document.writeln("</script>");










document.writeln("<script type=\"text/javascript\">");
document.writeln("$(function(){   ");
document.writeln("    $(window).scroll(function() {       ");
document.writeln("        if($(window).scrollTop() >= 100){");
document.writeln("            $('.side').fadeIn(300); ");
document.writeln("        }else{    ");
document.writeln("            $('.side').fadeOut(300);    ");
document.writeln("        }  ");
document.writeln("    });");
document.writeln("    $('.side').click(function(){");
document.writeln("    $('html,body').animate({scrollTop: '0px'}, 800);}); ");
document.writeln("});");
document.writeln("</script>");













document.writeln("<script type=\"text/javascript\">");
document.writeln("$(document).ready(function(){");
document.writeln("");
document.writeln("    $(\".side ul li\").hover(function(){");
document.writeln("        $(this).find(\".sidebox\").stop().animate({\"width\":\"124px\"},200).css({\"opacity\":\"1\",\"filter\":\"Alpha(opacity=100)\",\"background\":\"#ae1c1c\"})    ");
document.writeln("    },function(){");
document.writeln("        $(this).find(\".sidebox\").stop().animate({\"width\":\"54px\"},200).css({\"opacity\":\"0.8\",\"filter\":\"Alpha(opacity=80)\",\"background\":\"#000\"})   ");
document.writeln("    });");
document.writeln("    ");
document.writeln("});");
document.writeln("function goTop(){");
document.writeln("    $('html,body').animate({'scrollTop':0},600);");
document.writeln("}");
document.writeln("</script>");