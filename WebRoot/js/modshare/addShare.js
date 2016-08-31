function check(){
	if($("#title").val()==''){
		alert("请输入标题");
		return false;
	}
	if(editor.getContentTxt()==''){
		alert('请输入简介内容');
		return false;
	}else{
		$("#content").val(editor.getContent());
	}
	/*if($("#shareFile").val()==''){
		alert('请选择文件');
		return false;
	}*/
	document.getElementById("bg").style.display ="block";
	document.getElementById("show").style.display ="block";
}