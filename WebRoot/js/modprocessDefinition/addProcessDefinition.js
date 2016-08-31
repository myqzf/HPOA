$(document).ready(function() {
	


});


	//form表单提交项检查
	function checkprocess(){
		
		//判断上传的文件类型
		var processFile = $("#processFile").val();
		var fileTypes = new Array("bar");  //定义可支持的文件类型数组
		var fileTypeFlag = "0";
		var newFileName = processFile.split('.');
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




