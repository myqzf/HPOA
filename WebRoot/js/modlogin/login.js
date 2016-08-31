//登录js
jQuery().ready(function() {
	
	//回车监控
	document.onkeydown = function(event) {
		e = event ? event : (window.event ? window.event : null);
		if (e.keyCode == 13) {
			// 执行的方法
			sub();
		}
	};

});

// 检查用户名密码是否正确
function sub() {
	var userAccount = $("#account").val();
	var userPassword = $("#password").val();
	if (userAccount == "" || userPassword == "") {
		$("#msg").html("用户名或密码不能为空！");
		return;
	}
	$.post("login/jsonCheckUser.json", {
		account : userAccount,
		password : userPassword
	}, function(data) {
		var flag = data.flag;
		if (flag == "1") {
			$("#userForm").submit();
		} else if (flag == "2") {
			alert("用户名或密码不正确！");
		} else if (flag == "3") {
			alert("用户名或密码不能为空！");
		} else if (flag == "4") {
			alert("此账号已被冻结！");
		}/* else if (flag == "5") {
			$("#msg").html("请修改密码！");
		}*/ else {
			// $("#msg").attr("color","blue");
			alert("系统繁忙，请稍后再试！");
		}
	});
}
//清除
function clearInfo() {
	$("#account").val("");
	$("#password").val("");
	$("#msg").html("");
}