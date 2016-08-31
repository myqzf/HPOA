$(document).ready(function(e){
	$("#btnResponse").click(function(e){
		if($("#title").val()==''){
			$.messager.alert("注意","标题不能为空");
			return false;
		}
		if(editor.getContentLength()==0){
			$.messager.alert("注意","内容不能为空");
			return false;
		}
		$.ajax({
			type : "post",
			url : "responseWork/addIndividualResponse.json",
			async : false,
			data :{
				title : $("#title").val(),
				content : editor.getContent(),
				orgid : $("#orgid").val(),
				leaderid : $("#receiverid").val(),
			},
			success : function(data){
				alert(data.message);
				if(data.flag==1){
					location.reload();
				}
			}
		});
	});
});
function choiceLeader(leaderid,leaderName,orgid){
	$("#receiverid").val(leaderid);
	$("#orgid").val(orgid);
	$("#receiverName").html("以 "+leaderName+" 汇报");
	$("#winLeaderList").window('close');
}