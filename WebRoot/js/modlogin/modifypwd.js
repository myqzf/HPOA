$(document).ready(function(e){
	//按下键盘上回车键
	$(document).keydown(function(event){
		if(event.which==13){
			modifypwd();
		}
	});
	//点击修改按钮
	$("#modify").click(function(e){
		modifypwd();
	});
});
function modifypwd(){
	if ($("#oldpwd").val() == "" || $("#newpwd").val() == "" || $("#checkpwd").val()=="") {
		alert("请检查是否全部输入");
		return;
	}
	if($("#newpwd").val() != $("#checkpwd").val()){
		alert("新密码与再次输入密码不一致");
		return;
	}
	$.ajax({
		url : "menu/jsonmodifypwd.json",
		data : {oldpwd:$("#oldpwd").val(),newpwd:$("#newpwd").val()},
		type : "POST",
		success : function(data){
			var flag = data.flag;
			if (flag == "1") {
				alert("修改成功");
				window.parent.document.location.href="/HPOA/login/loginOut";
			} else if (flag == "2") {
				alert("旧密码不正确！");
			} else if (flag == "3") {
				alert("旧密码或新密码不能为空！");
			} else {
				alert("系统繁忙，请稍后再试！");
			}
		}
	});
}