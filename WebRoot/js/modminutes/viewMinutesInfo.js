$(document).ready(function(e){

		//attachmenturl=$("#attachmenturlaaa").val();
		//url="upload/notice/"+attachmenturl;  /HPOA/monthly/seachIfSubmitted
		
	    minuteslink = $("#minuteslinkaaa").val();
		ss = "/HPOA/minutes/minutesFileDownload.do?fileName=" + minuteslink;
		url = encodeURI(ss);
		url = encodeURI(url);
		var index=minuteslink.indexOf("~");
		attachmentName=minuteslink.substring(index+1);
		document.getElementById("attachmentName").value=attachmentName;
    if(minuteslink!==""){
    	$("#uploadFile_div").remove();
		$("#uploadFile").append("<div id='uploadFile_div'> <div class='tsxxnr'>"+attachmentName+"<a href='#' style='text-decoration: none;' onclick='checkAttachment(minuteslink); return false;'>点击下载</a><br/></div></div></div>	" 
                 );
    }else{
    	$("#uploadFile_div").remove();
    }
	
   
	
	
});


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
 * 成功
 * 检查文件是否存在
 * @param json
 */
function sucCheckAttachment(json) {
	var list=json;
	if(list.message=="true"){
		ss = "/cppcc/notice/FileDownload.action?fileName=" + attachmenturl;
		url = encodeURI(ss);
		url = encodeURI(url);
		location.href=url;
	}else{
		art.dialog.alert("文件已经不存在！");
		//return false;
	}
}
 

//禁用富文本编辑器 
function setDisabled() {
	  editor.setDisabled('fullscreen');
	  disableBtn("enable");
	} 

//灰掉工具栏按钮
function disableBtn(str) {
   var div = document.getElementById('btns');
   var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
   for (var i = 0, btn; btn = btns[i++];) {
       if (btn.id == str) {
      UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
       } else {
      btn.setAttribute("disabled", "true");
       }
   }
    }
//加载完页面后执行
window.onload=function(){ 

	setDisabled();//禁用富文本编辑器 

	} ;
