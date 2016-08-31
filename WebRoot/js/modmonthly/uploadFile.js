$(document).ready(function(e){
	
	
	
	//上传月报文件
	$("#uploadFile").click(function(e){	
		var title = document.getElementById("title").value;
		var monthvalue = document.getElementById("month").value;
		var obj=document.getElementById("month");
		var month = obj.options[obj.selectedIndex].text;
		
		if(title==null ||title.trim()==""){
			alert("标题不能为空！");
			return false;
		}
		
		
		findIfSubmitted();
		
		
	});
	

	//删除照片  未用
	$("#delFile").click(function(e){
		$.post(
			"monthly/delFile",
			{staffId:$("#id").val()},
			function(data){
				var message="";
				switch(data.result){
				case "0":{
					message = "系统出错";
					break;
				}
				case "1":{
					message ="删除成功";
					break;
				}
				case "2":{
					message = "删除失败";
					break;
				}				
			}
				art.dialog({
					title:'感谢您对我们工作的大力支持',
					content:message,
					background:'#ccc',
					opacity: 0,
					lock:true,
					button: [{
						name:'确定',
						callback: function () {							
//								window.location.reload();							
						}
					}],					
				});				
//				$("#picture").attr("src","staffpicture/none.jpg");
//				$("#delPhoto").css("display","none");
			}
		);
	});
	
	
});

	

//检验是否选择了文件
function checkFile(){  
	 var s=document.getElementById("monthlyFile").value;
	 var fileTypes = new Array("doc","docx");  //定义可支持的文件类型数组
	 var fileTypeFlag = "0";
     if(s==""){
         alert("请选择一个文件");
       //  document.getElementById("monthlyFile").focus();
         return false;
     }
     //判断上传文件类型
    var newFileName = s.split('.');
 	newFileName = newFileName[newFileName.length-1];
 	for(var i=0;i<fileTypes.length;i++){
 	  if(fileTypes[i] == newFileName){
 	  fileTypeFlag = "1";
 	  }
 	}
 	if(fileTypeFlag == "0"){
 		alert("文件类型不符合要求！");
 	  return false;
 	}
 	//判断上传文件的大小
 	var dom = document.getElementById("monthlyFile");  
	var fileSize =  dom.files[0].size;//文件的大小，单位为字节B
	if(fileSize>2097152){
		alert('文件大小不合要求');
		return false;
	}
}

//上传成功
function uoloadOK(){  
	if($("#flag").val()=="上传成功"){
		//window.opener.location.reload();//刷新
		}
	  window.opener.location.href='/HPOA/monthly/goEachMonthly';
	  window.close();
}


/**
 * 查询当月月报是否已提交
 * 是 便提示当月月报已提交
 * 
 * @param json
 */
function findIfSubmitted() {
	var monthvalue = document.getElementById("month").value;
	var obj=document.getElementById("month");
	var month = obj.options[obj.selectedIndex].text;
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		url : "/HPOA/monthly/seachIfSubmitted",
		data:{monthscope:monthvalue},
		success : sucseachIfSubmitted,
		error : function() {
			alert("error");

		}
	});
}
/**
 * 成功
 * 查询当月月报是否已提交
 * @param json
 */
function sucseachIfSubmitted(data) {
	var title = document.getElementById("title").value;
	var monthvalue = document.getElementById("month").value;
	var obj=document.getElementById("month");
	var month = obj.options[obj.selectedIndex].text;
	if(data==2){
		alert("此月月报已提交");
		//window.location.href='/HPOA/monthly/goEachMonthly';//月报列表页面
	}else if(data==3){
		alert("系统出错，请稍候再试！");
	} else{
		url="/HPOA/monthly/gotoUploadFile?title="+title+"&month="+monthvalue;
		   url = encodeURI(url);
	       url = encodeURI(url);
		window.open(url,"upload","height=200,width=500,top=400,left=400,alwaysRaised=yes,location=no");
	}
}
