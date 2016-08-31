$(document).ready(function() {
	
});




/**
 * 保存修改的企业文化信息
 * 
 * @param 
 */
function saveCultureInfo() {
	
	var cultureid = document.getElementById("cultureid").value;
	var title = document.getElementById("culturetitle").value;
	var content=UE.getEditor('editor').getContent();
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
	
	//提交修改的企业文化信息
	$.ajax({
	async : false,
	dataType:'json',
	data:{cultureid : cultureid,culturetitle: title,culturecontent:content},
	type : 'post',
	url : "/HPOA/culture/saveModifyCultureInfo",
	success : function(data) {	
		if(data==1){
			art.dialog.alert('修改成功！');
		}else{
			art.dialog.alert('修改失败！请稍候再试！');
		}
		
		//window.location.href='/HPOA/dictionary/godictictionZtree?headId='+headId;
		window.location.href='/HPOA/culture/goCulturelist';
		
	},
	error : function() {				
		alert("error");
	       }
       });  
}
