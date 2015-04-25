(function($) {
    $.extend($.fx.step,{
        backgroundPosition: function(fx) {
            if (fx.pos === 0 && typeof fx.end == 'string') {
                var start = $.css(fx.elem,'backgroundPosition');
                start = toArray(start);
                fx.start = [start[0],start[2]];
                var end = toArray(fx.end);
                fx.end = [end[0],end[2]];
                fx.unit = [end[1],end[3]];
            }
            var nowPosX = [];
            nowPosX[0] = ((fx.end[0] - fx.start[0]) * fx.pos) + fx.start[0] + fx.unit[0];
            nowPosX[1] = ((fx.end[1] - fx.start[1]) * fx.pos) + fx.start[1] + fx.unit[1];
            fx.elem.style.backgroundPosition = nowPosX[0]+' '+nowPosX[1];

            function toArray(strg){
                strg = strg.replace(/left|top/g,'0px');
                strg = strg.replace(/right|bottom/g,'100%');
                strg = strg.replace(/([0-9\.]+)(\s|\)|$)/g,"$1px$2");
                var res = strg.match(/(-?[0-9\.]+)(px|\%|em|pt)\s(-?[0-9\.]+)(px|\%|em|pt)/);
                return [parseFloat(res[1],10),res[2],parseFloat(res[3],10),res[4]];
            }
        }
    });
})(jQuery);


$(function(){
	$.organ("ul.func"); //手风琴
	
	$("div.c_plan").each(function(i){ //项目LIST进度条
		var t  = $.trim($(this).text());
			t  = t.substring(0, t.indexOf("%"));
			t  = t*56;
		$(this).css("backgroundPosition", "-" + t + "px 0");
    });
});
$(window).load(function(){
	$.focusCut("ul.fI", "ul.fT"); //头部焦点图切换
	
	$.digitalScroll("span.charNum"); //数字自增
	$.digitalScroll("#nemPer", 0);
});



/***
	o     :Dom,
	_loop :是否自增,
	_up   :自增区间,
	_dec  :小数位,
	_lh   :数字行高
*/
$.digitalScroll = function(o, _loop, _up, _dec, _lh) {
	var _loop = _loop != 0 && _loop != 1 ? 1 : _loop,
		_up   = _up == null || isNaN(_up) ? 1000 : n,
		_dec  = _dec>=0 && _dec<6 ? _dec : 2,
		_lh   = _lh == null || isNaN(_lh) ? 30 : _lh;

	//Random
	function getRandom(n) {
		var n = n == null || isNaN(n) ? 10 : n;
		return Math.random() * ( n + 1);
	}

	//Number scroll
	function dScroll(o, n2, n1) {
		var n2  = n2 + "",
			n1  = n1 + "",
			htm = "",
			c   = o.find("i"),
			s   = c.length;

		for(var i=n2.length-1, t=1; i>=0; i--, t++) {
			if(t <= s) {
				if(n2.charAt(i) == ".") {
					c.eq(s - t).attr("data", 10);
				} else {
					c.eq(s - t).attr("data", n2.charAt(i));
					if(n2.charAt(i) == n1.charAt(i)) {
						c.eq(s - t).addClass("m");
					} else {
						c.eq(s - t).removeClass("m");
					}
				}
			} else {
				if(n2.charAt(i) == ".") {
					htm = "<i class='d' data='10'></i>" + htm;
				} else {
					htm = "<i data="+ n2.charAt(i) +"></i>" + htm;
				}
			}
		}
		o.prepend(htm);

		o.find("i:not('.d, .m')").each(function(i) {
            var c = $(this);
			if(navigator.userAgent.toLocaleLowerCase().match(/firefox/) != null) {
				c.animate({
					"backgroundPosition" : "0px -" + c.attr("data")*_lh + "px"
				}, 1000);
			} else {
				c.animate({
					"backgroundPositionY" : -c.attr("data")*_lh
				}, 1000);
			}
        });
	}

	$(o).each(function(i) {
        var o   = $(this),
			num = o.attr("data") * 1;
		if(isNaN(num)) {
			return true;
		}

		dScroll(o, num);
		if(_loop == 1) {
			setInterval(function(){ //Number addSelf
				var new_val = (num + getRandom(_up)).toFixed(_dec);
				dScroll(o, new_val, num);
				num = new_val*1;
			}, 3000);
		}
    });	
}



/***手风琴*/
o_Fg = true;
$.organ = function(o){
	var o     = $(o),
		li_w  = o.width()/o.find("li").length;

	o.on("mouseenter", "li", function(e){
		if(!o_Fg) {
			return false;
		}
		o_Fg = false;
		var c = $(this);
		c.addClass("on").siblings().removeClass("on");
		c.siblings().find("p").hide();
		
		c.siblings().stop(true, true).animate({
			"width" : 110
		}, 500, function(){
			c.stop(true, false).animate({
				"width" : o.width()-220
			}, 500, function(){
				c.find("span.s2").hide(200);
				c.find("span.s3").show(200);
				o_Fg = true;
			});
		});
		e.stopPropagation();
	});

	o.on("mouseleave", function(e){
		o_Fg = false;
		o.find("li p").show();
		o.find("span.s3").hide(200);
		o.find("span.s2").show(200);

		o.find("li").stop(true, true).animate({
			"width" : li_w
		}, 400, function(){
			o.find("span.s3").hide(200);
			o.find("span.s2").show(200);
			o_Fg = true;
		});
		o.find("li").removeClass("on");
		e.stopPropagation();
	});
}



/** 头部焦点图切换 */
$.focusCut = function(o, p){
	//常量
	var f_num    = 0,
		f_cur    = 0,
		f_newcur = 1,
		f_clear  = 0,
		f_T      = 3500,
		iDiv     = $(o).find("li"),
		tDiv     = $(p).find("li");

	f_num = iDiv.size();
	$(p).width(tDiv.eq(0).outerWidth(true)*f_num);
	
	dotPs();
	function dotPs(){
		var w = iDiv.eq(0).width(),
			t = tDiv.eq(0).outerWidth(true);
			w = w<950 ? 950 : w;
		$(p).css({"left":Math.floor((w-t*f_num)/2)});
	}
	
	iDiv.find("img").removeAttr("title style alt");
	f_clear = window.setInterval(autoFocus, f_T);
	
	
	$(window).resize(function(){
		dotPs();
	});

	iDiv.hover(
		function(){
			window.clearInterval(f_clear);
		},
		function(){
			f_clear = window.setInterval(autoFocus, f_T);
		}
	);
	
	tDiv.find("a").on("mouseover",function(){
		window.clearInterval(f_clear);
		
		if($(this).parent().hasClass("on"))
		{
			return false;
		}
		f_cur    = iDiv.parent().find(".on").index();
		f_newcur = $(this).parent().index();
		
		cut(f_cur, f_newcur);
	});
	tDiv.find("a").on("mouseout",function(){
		window.clearInterval(f_clear);
		f_clear = window.setInterval(autoFocus, f_T);
	});
	
	function autoFocus(){
		f_cur    = iDiv.parent().find(".on").index();
		f_newcur = f_cur + 1;
		f_newcur = f_newcur >= f_num ? 0 : f_newcur;

		cut(f_cur, f_newcur);
	}
	
	function cut(c, n){
		tDiv.removeClass("on");
		tDiv.eq(n).addClass("on");
		
		iDiv.eq(c).stop(true, false).fadeOut(300);
		iDiv.eq(n).stop(true, true).fadeIn(300);
		iDiv.removeClass("on");
		iDiv.eq(n).addClass("on");
	}
}