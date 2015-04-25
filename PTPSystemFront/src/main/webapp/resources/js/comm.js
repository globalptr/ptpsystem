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
	$.wxCode(); //Weixin Code
	
	$.onLine( //在线客服
		{
			//加入要显示QQ和对应名称
			qn:[
				{"qq":"123456789", "name":"客服-小青"},
				{"qq":"223456789", "name":"客服-小熊"},
				{"qq":"323456789", "name":"技术支持-小何"}
			]
		}
	);
	
	$.hdFixed(); //Head 固定头部
	
	$("div.more_bank_sw").on("click", function(){ //More Bank Show
		var o = $("div.bType"),
			p = $(this).find("i"),
			l = o.find("label"),
			h = l.eq(0).outerHeight(true);

		if(o.height() <= h*3) {
			o.animate({
				"height" : Math.ceil(l.length / 3) * h
			}, 200, function(){
				p.addClass("icon_arr_d");
			});
		} else {
			o.animate({
				"height" : h * 3
			}, 200, function(){
				p.removeClass("icon_arr_d");
			});
		}
	});
	
	
	$("div.sum").on("blur", "input.inpte", function(){ //充值金额Set
		var o = $(this),
			p = $(".e_tips");
		if($.trim(o.val()) == "") {
			p.text("请输入金额");
		}
		o.on("focus", function(){
			p.text("");
		});
	});
	
	
	$("div.acc_debt_menu").on("click", "a", function(){ //债权转让&&购买记录
		var o = $(this),
			i = o.index("div.acc_debt_menu a");
			
		if(o.is(".on")) {
			return false;
		}
		o.addClass("on").siblings().removeClass("on")
		$("div.acc_debt_status").addClass("dNone").eq(i).removeClass("dNone");
		$("div.acc_debt_tab").addClass("dNone").eq(i).removeClass("dNone");
	});
	
	$("#btn_infoEdit").on("click", function(){ //信息Edit-切换
		var o = $(this),
			p = $("ul.acc_info_list"),
			t = $.trim(o.text());

		if(t == "修改信息") {
			p.hide().eq(1).show();
			o.text("取消修改").attr("title", "取消修改");
		} else if (t == "取消修改") {
			p.hide().eq(0).show();
			o.text("修改信息").attr("title", "修改信息");
		}
	});
	
	$("div.bType").on("click", "img", function(){ //充值类型Chioce
		var o = $("div.bType").find("input[type='radio']"),
			c = $(this);
		o.prop("checked", false);
		c.prev().prop("checked", true);
	});

});


/***Num add and subtract*/
$.numChioce = function(o){
	var o = $(o),
		e = o.find("em"),
		n = o.find("input"),
		r = /^[0-9]*[1-9][0-9]*$/,
		s = 0,
		M = $("#maxNum").text()*1,
		T = $("#count_qy");

	e.eq(0).on("click", function(){
		s = nVer();
		if(s && s>1) {
			n.val(--s);
			T.text(T.text()*1 + 1);
			count(s);
		}
	});
	e.eq(1).on("click", function(){
		s = nVer();
		if(s && s<=M) {
			n.val(++s);
			T.text(T.text()*1 - 1);
			count(s);
		}
	});
	
	n.on("keyup", function(){
		s = nVer();
		if(s<=M) {
			n.val(s);
			T.text(T.attr("data")*1 - s);
			count(s);
		} else {
			n.val(M);
			T.text(T.attr("data")*1 - M);
			count(M);
		}
	});
	
	function nVer() {
		var t= $.trim(n.val());
		
		if(r.test(t)) {
			return t;
		} else {
			alert("亲,数量应为大于0的正整数哦~");
			n.val(1);
			return 1;
		}
	}
	
	function count(num){
		var price  = $("#price").text() * 1,
			total  = $("#total");
		
		total.text(price * num);
	}
}


/***Menu 固定*/
$.nodeFixed = function(){
	var o    = $("div.menu_sub"),
		sTop = 0;

	$(window).scroll(function(){
		if(typeof(document.body.style.maxHeight) == "undefined") {
			sTop = document.documentElement.scrollTop;
		} else {
			sTop = $("html").scrollTop();
			if(sTop == 0) {
				sTop = $("body").scrollTop();
			}
		}
		
		if(sTop>63) {
			if(!o.is(".menuFixed")) {
				o.addClass("menuFixed");
			}
		} else {
			if(o.is(".menuFixed")) {
				o.removeClass("menuFixed");
			}
		}
	});
}


/***Head 固定头部*/
$.hdFixed = function(){
	var o    = $("div.head"),
		p    = $("div.online"),
		sTop = 0;

	$(window).scroll(function(){
		if(typeof(document.body.style.maxHeight) == "undefined") {
			sTop = document.documentElement.scrollTop;
		} else {
			sTop = $("html").scrollTop();
			if(sTop == 0) {
				sTop = $("body").scrollTop();
			}
		}
		
		if(sTop>27) {
			if(!o.is(".headFixed")) {
				o.prev().css("marginBottom", o.height());
				o.addClass("headFixed");
				p.animate({
					"top" : 121
				}, 100);
			}
		} else {
			if(o.is(".headFixed")) {
				o.prev().css("marginBottom", 0);
				o.removeClass("headFixed");
				p.animate({
					"top" : 148
				}, 100);
			}
		}
	});
}


/***在线客服*/
$.onLine = function(opts){
	if(typeof(opts) != "object" || opts.qn.length < 1 || $("#inpOnLine").val() == 0) { //不显示右侧浮动窗 [未传入QQ 或 设置为不显示]
		return false;
	}

	var temp = "",
		htm  = '<div class="online">\
					<div class="online_s"><a title="联系我们">联系我们</a></div>\
					<div class="online_b">\
						<div class="ti"><span>天天聚财官网</span></div>\
						<div class="te">\
							<p class="txt_m">在线客服</p>';
	for(var i=0; i<opts.qn.length; i++) {
		htm += '<p class="fm2"><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin='+ opts.qn[i].qq +'&site=qq&menu=yes" title="'+ opts.qn[i].name +'"><img src="http://wpa.qq.com/pa?p=2:'+ opts.qn[i].qq +':45"><em>'+ opts.qn[i].name +'</em></a></p>';
	}
		htm += 			   '<p class="txt_m">资讯热线</p>\
							<p class="ph">400-0233536</p>\
							<p class="wx"><img src="http://www.ttjc.com.cn/images/ttjc_weixin.jpg" /></p>\
						</div>\
					</div>\
				</div>';
	
	$("body").append(htm);
	var o = $("div.online"),
		s = o.find("div.online_s"),
		b = o.find("div.online_b");

	s.on("click", function(){
		o.css({"width":b.width(), "height":b.height()+1});
		s.fadeOut(100);
		b.animate({
			"right" : 0
		}, 150);
	});
	
	b.on("click", "div.ti", function(){
		b.animate({
			"right" : -b.width()
		}, 150, function(){
			o.css({"width":s.width(), "height":s.height()});
			s.fadeIn(100);
		});
	});
}


/***Weixin Code*/
$.wxCode = function(){
	var htm  = '<div class="dialogOverlay"></div>\
				<div class="weixin_top">\
					<h3><a class="trans" href="javascript:;" hidefocus="true">×</a>官方微信</h3>\
				</div>',
		o    = $("a.a_wx");
		
	if($("div.dialogOverlay").length<1) {
		$("body").append(htm);
	}
	var d_s = $("div.dialogOverlay"),
		d_w = $("div.weixin_top");

	o.on("click", function(){
		d_s.show();
		d_w.show();
	});
	d_w.find("a").on("click", function(){
		d_s.hide();
		d_w.hide();
	});
	d_s.on("click", function(){
		d_s.hide();
		d_w.hide();
	});
}


/***Content Cut*/
$.contentCut = function(o, p, ev){
	var o   = $(o).find("a"),
		p   = $(p).find("li"),
		ev  = ev == null ? "click" : ev;
	
	o.on(ev, function(){
		var c = $(this);
		if(c.is(".on")) {
			return false
		} else {
			p.eq(c.index()).addClass("on").siblings().removeClass("on");
			c.addClass("on").siblings().removeClass("on");
		}
	});
}


/***Menu 手风琴*/
$.menuArt = function(o){
	var o = $(o)
	o.on("click", "> a", function(){
		var c = $(this),
			d = c.next("div"),
			n = c.next("div").find("a").length,
			h = c.next("div").find("a").outerHeight(true);

		if(c.parent().is(".on")) {
			c.next().stop(true, false).animate({
				"height" : 0
			}, 200, function(){
				$(this).hide();
				$(this).parent().removeClass("on");
			});
		} else {
			c.parent().addClass("on");
			c.next().stop(true, false).show().height(0).animate({
				"height" : n*h
			}, 200);
			c.parent().siblings("li.on").find("div").animate({
				"height" : 0
			}, 200, function(){
				$(this).hide();
				$(this).parent().removeClass("on");
			});
		}
	});
}


/***文本框内容设置 */
$.SetTextInitVal = function(o){
	var o   = $(o),
		cte = $.trim(o.val());

	if(typeof(o.attr("ot"))=="undefined") {
		o.attr("ot",cte);
	}
	o.val("");
	o.css({"color":"#333"});
	
	o.blur(function() {
		var nte = $.trim(o.val()),
			ote = o.attr("ot");
		if(nte == ote || nte.length == 0) {
			o.val(ote)
			o.css({"color":"#999"});
		} else {
			o.css({"color":"#333"});
		}
	});
}


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
		tDiv     = $(p).find("li"),
		prev     = $("div.focus").find("a.prev"),
		next     = $("div.focus").find("a.next");

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

	$("div.focus").hover(function(){
		window.clearInterval(f_clear);
		prev.show();
		next.show();
	},
	function(){
		f_clear = setInterval(autoFocus, f_T);
		prev.hide();
		next.hide();
	});

	prev.on("click", function(){
		f_cur    = iDiv.parent().find(".on").index();
		f_newcur = f_cur - 1;
		f_newcur = f_newcur < 0 ? f_num-1 : f_newcur;
		
		cut(f_cur, f_newcur);
	});

	next.on("click", function(){
		f_cur    = iDiv.parent().find(".on").index();
		f_newcur = f_cur + 1;
		f_newcur = f_newcur >= f_num ? 0 : f_newcur;
		
		cut(f_cur, f_newcur);
	});
	
	tDiv.find("a").on("mouseover",function(){
		clearInterval(f_clear);
		
		if($(this).parent().hasClass("on"))
		{
			return false;
		}
		f_cur    = iDiv.parent().find(".on").index();
		f_newcur = $(this).parent().index();
		
		cut(f_cur, f_newcur);
	});
	tDiv.find("a").on("mouseout",function(){
		clearInterval(f_clear);
		f_clear = setInterval(autoFocus, f_T);
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