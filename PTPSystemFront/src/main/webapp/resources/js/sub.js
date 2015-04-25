$(function(){
	
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

