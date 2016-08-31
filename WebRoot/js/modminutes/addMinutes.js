$(document).ready(function(e){
	
	
});




//form表单提交项检查
function checkMinutes(){
	//判断日期非空
	var dv = $('#meetingdate').datebox('getValue');  
	if(dv==''){
		art.dialog.alert('日期不能为空，请选择日期！');
		return false;
	}
	//判断地点非空
	if($("#meetingplace").val()==''){
		art.dialog.alert('地点不能为空！');
		return false;
	}
	//判断地点长度
	if($("#meetingplace").val().length>=65){
		art.dialog.alert('输入的地点过长');
		return false;
	}
	//判断会议主题非空
	if($("#meetingtheme").val()==''){
		art.dialog.alert('会议主题不能为空！');
		return false;
	}
	//判断会议主题长度
	if($("#meetingtheme").val().length>=165){
		art.dialog.alert('主题过长');
		return false;
	}
	
	//判断正文非空
	if(editor.getContent()==''){
		art.dialog.alert('内容不能为空');
		return false;
	}
	//判断上传的文件大小
	var fileId = "minutesFile";
	var dom = document.getElementById(fileId);  
	var fileSize =  dom.files[0].size;//文件的大小，单位为字节B
	if(fileSize>2097152){
		art.dialog.alert('附件大小不合要求');
		return false;
	}
	//判断上传的文件类型
	var minutesFile = $("#minutesFile").val();
	var fileTypes = new Array("doc","docx");  //定义可支持的文件类型数组
	var fileTypeFlag = "0";
	var newFileName = minutesFile.split('.');
	newFileName = newFileName[newFileName.length-1];
	for(var i=0;i<fileTypes.length;i++){
	  if(fileTypes[i] == newFileName){
	  fileTypeFlag = "1";
	  }
	}
	if(fileTypeFlag == "0"){
		art.dialog.alert("附件类型不符合要求！");
	  return false;
	}
}


//输入内容字数统计
function sumCount(e){
	$("#tip").html("当前已输入"+($('#summary').val()).length+"0个字符, 您还可以输入"+(620-($('#summary').val()).length)+"个字符。"),
	$("#tip").css("color","#708090");
}



