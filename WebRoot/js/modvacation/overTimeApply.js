$(document).ready(function(e){
	$('#overTimeStart').datetimebox({
		currentText: "现在",
		required: true,
		showSeconds: false,
	});
	$('#overTimeEnd').datetimebox({
		currentText: "现在",
		required: true,
		showSeconds: false,
	});
	$("#submit").click(function(e){
		$.ajax({
			url : 'overtime/submitOverTimeApply.json',
			data : {
				startTime : $('#overTimeStart').datebox('getValue'),
				endTime : $('#overTimeEnd').datebox('getValue'),
				overTimeType : $("#overTimeType").val(),
				content : $("#content").val(),
			},
			type : "post",
			async : false,
			success : function(data){
				console.log(data);
			},
		});
	});
});