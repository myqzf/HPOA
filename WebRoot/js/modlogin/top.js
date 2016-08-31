//修改密码
function turnmainpage(url){
	$(window.parent.document).find("#mainFrame").attr("src",url);
}
//注销
function loginout(){
	window.parent.document.location.href="/HPOA/login/loginOut";
}