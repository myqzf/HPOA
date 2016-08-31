$(document).ready(function(e){

		//attachmenturl=$("#attachmenturlaaa").val();
		//url="upload/notice/"+attachmenturl;  /HPOA/monthly/seachIfSubmitted
	
	    minutesid = $("#minutesid").val();
	    minuteslink = $("#minuteslinkaaa").val();
		ss = "/HPOA/minutes/minutesFileDownload.do?fileName=" + minuteslink;
		url = encodeURI(ss);
		url = encodeURI(url);
		var index=minuteslink.indexOf("~");
		attachmentName=minuteslink.substring(index+1);
		//document.getElementById("attachmentName").value=attachmentName;
    if(minuteslink!==""){
    	$("#uploadFile_div").remove();
		$("#uploadFile").append("<div id='uploadFile_div'><div class='tsxxnr'>"+attachmentName+"<a href='#' style='text-decoration: none;' onclick='checkAttachment(minuteslink); return false;'>点击下载</a><br/></div></div></div>	"
				+ "<a href='javascript:void(0);' onclick='delete_minutes_attachment(\"" + minutesid 
		        + "\");'>  删除附件</a></div>&nbsp;"
                 );
    }else{
    	
    }
	
	
});

/**
 * form表单提交项检查
 * @returns {Boolean}
 */
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
	if(fileSize>10485760){
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

/**
 * 附件下载前检查文件是否存在
 * @param json
 */
function checkAttachment(minuteslink){
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		url : "/HPOA/minutes/checkMinutesAttachment",
		data:{fileName : minuteslink},
		//success : sucCheckAttachment(),
		success : function(list) {
			if(list==true){
				ss = "/HPOA/minutes/minutesFileDownload.do?fileName=" + minuteslink;
				url = encodeURI(ss);
				url = encodeURI(url);
				location.href=url;
			}else{
				art.dialog.alert("文件已经不存在！");
				//return false;
			}
		},
		error : function() {
			alert("error");

		}
	});
}

/**
 * 根据id 删除附件
 * @param id 纪要id
 */
function delete_minutes_attachment(minutesid){
	return artDialog({
		 content:'确认要删除吗？',
	        lock:true,
	        style:'succeed noClose'
		    },
    function () {
        $.post("/HPOA/minutes/deleteMinutesAttachment",{minutesid:minutesid},function(data){
       	 
       	 if(data.message!=null){
       			art.dialog.alert(data.message);
       	 }
        	
       	// window.location.href="cppcc/notice/gotoNoticeList";
        	location.reload();
    	});
    },
    function(){
    	location.reload();
    }
);			
}
