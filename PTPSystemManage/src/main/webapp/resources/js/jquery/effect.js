var hei = 0;

$(function(){
	$("#username").focus(function(){
		if($(this).val() == "管理员账号"){
			$(this).val("");
		}
	});
	$("#username").blur(function(){
		if($(this).val() == ""){
			$(this).val("管理员账号");
		}
	});
	
	$("#passface").focus(function(){
		$("#passface").hide(0);
		$("#password").show(0);
		$("#password").focus().select();
	});
	$("#password").blur(function(){
		if($("#password").val() == ""){
			$("#password").hide(0);
			$("#passface").show(0);
		}
	});
	
	$("#code").focus(function(){
		if($(this).val() == "验证码"){
			$(this).val("");
		}
	});
	$("#code").blur(function(){
		if($(this).val() == ""){
			$(this).val("验证码");
		}
	});
	
	if($(".lefttree")){
		
		if($("#toptoolbar")){
			hei = 20;
		}
		$(".lefttree").height($(window).height()-80-hei);	
	}
	if($(".note")){
		if($("#toptoolbar")){
			hei = 20;
		}
		$(".note").height($(window).height()-80-hei);	
	}
});

var timelinv;
function timer(){
	timelinv = setInterval("timerun()",1000);
}

function recode(){
	$("#ccode").attr("src","/chkcode?id=" + new Date().getTime());
}

function timerun(){
	var mydate = new Date();
	
	$(".time").html(mydate.getHours() + ":" + todabo(mydate.getMinutes()) + "<span>:" + todabo(mydate.getSeconds()) + "</span>");
	$(".day").html(mydate.getFullYear() + "年" + (mydate.getMonth()+1) + "月" + mydate.getDate() + "日 星期" + days(mydate.getDay()));
}
function days(d){
	if(d == 0) return "日";
	if(d == 1) return "一";
	if(d == 2) return "二";
	if(d == 3) return "三";
	if(d == 4) return "四";
	if(d == 5) return "五";
	if(d == 6) return "六";
}
function todabo(t){
	if(parseInt(t) < 10){
		return "0" + t.toString();	
	}else{
		return t.toString();	
	}
}
function ParseNumber(num,tab){
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(Number(num))) num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10) cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++) {
		num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
	}
	return tab + (((sign) ? '' : '-') + num + '.' + cents);
};
function GridsBeforeRender(resdata,grid){
    if(0 !== resdata.e){
      if(100100 == resdata.e){
          $.ligerDialog.error('您未登录或已经超时退出，请重新登录');
          top.location.href = "/";
      }else{
          $.ligerDialog.error(resdata.msg);
      }
      return false;
    };
};