$(document).ready(function(e){
	$(".dashed:last").remove();
	$("#percent").numberbox({
		min:0,
		max:100,
		suffix:'%'
	});
	$("#response").click(function(e){
		if($("#percent").numberbox("getValue")==''){
			$.messager.alert('','请填写进度');
			return false;
		}
		if(editor.getContentTxt()==''){
			$.messager.alert('','请填写汇报内容');
			return false;
		}
		$.ajax({
			type : "post",
			url : "responseWork/addWorkResponse.json",
			data : {
				subAssignid:$("#subassignid").val(),
				percent:$("#percent").val(),
				content:editor.getContent(),
			},
			async : true,
			success : function(data){
				alert(data.message);
				if(data.flag==1){
					location.href="/HPOA/responseWork/goReceiveManage";
				}
			}
		});
	});
	$("#btnReturn").click(function(e){
		location.href="/HPOA/responseWork/goReceiveManage";
	});
});