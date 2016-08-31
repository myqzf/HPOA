$(document).ready(function(e){	
	var url =$("#url").val();
	//$("#btnReturn").click(function(e){
	if(url!==null){
	//	window.location.href="/HPOA/process/"+url;
	}
	//});
});

/**
 * 提交任务
 * 进行提交批注
 * @param 
 */
function submit_handle() {
	var taskId = document.getElementById("taskId").value;
	var reimburse_id = document.getElementById("reimburse_id").value;
	var comment = document.getElementById("comment").value;
	var outcome = document.getElementById("outcome").value;

	if(comment==null ||comment.trim()==""){
		alert("批注不能为空！");
		return false;
	}
	if(comment.length>=20 ){
		alert("批注过长！");
		return false;
	}
	
	$.ajax({
		async : false,
		dataType:'json',
		data:{taskId : taskId,reimburseId: reimburse_id,comment:comment,outcome:outcome},
		type : 'post',
		url : "/HPOA/process/saveHandleInfo",
		success : function(data) {	
			if(data==1){
				alert("办理完成！");
			}else{
				alert("办理失败！请稍候再试！");
			}
			
			
			//window.location.href='/HPOA/dictionary/godictictionZtree?headId='+headId;
			window.location.href='/HPOA/reimburse/gotoListTaskManage';
			
		},
		error : function() {				
			alert("error");
		}
	});
}