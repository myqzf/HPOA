$(document).ready(function() {
	
	 
});



/**
 * 保存添加的企业文化
 * 
 * @param 
 */
function saveCulture() {
	
	var title = document.getElementById("title").value;
	
	var content=editor.getContent();
	
	if(title==null ||title.trim()==""){
		art.dialog.alert('标题不能为空！');
		return false;
	}
	if(title.length>=30 ){
		art.dialog.alert('标题过长！');
		return false;
	}
	if(content.length==0){
		art.dialog.alert('请编辑后再进行保存！');
		return false;
	}
	
	  //提交
	$.post(
			"/HPOA/culture/saveCulture",
			{	
				culturetitle:title,
				culturecontent:content
								
			},
			function(data){		
				if(data=="1"){
					art.dialog.alert('保存成功！');
					window.location.href='/HPOA/culture/goCulturelist';
		//			location.reload();     
				}else if(data=="2"){
					art.dialog.alert('保存失败！');
				}else{
					art.dialog.alert('系统出错！');
				}
				             				
			});
	

}
 


