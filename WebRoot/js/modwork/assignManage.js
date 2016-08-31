$(document).ready(function(e){
	$("#percent").numberbox({
		min:0,
		max:100,
		suffix:'%'
	});
	$("#endbak").window({title:"完结备注",width:400,height:300,modal:true,shadow:true,closed:true,closable:false,maximizable:false,minimizable:false,collapsible:false});
	$("#choickstaff").window({title:"选择下属员工",width:400,height:300,modal:true,shadow:true,closed:true,resizable:false});
	$("#addsubassign").click(function(e){
		$("#choickstaff").window('open');
	});
	$("#btnEndWork").click(function(e){
		$("#endbak").window("open");
	});
	$("#btnEnd").click(function(e){
		$.ajax({
			type : "post",
			url : "workManage/endAssign.json",
			data : {
				assignid : $("#assignid").val(),
				baktxt : $("#txtendbak").val(),
			},
			success : function(data){
				alert(data.message);
				location.reload();
			}
		});
	});
	$("#btnRenewWork").click(function(e){
		var subasslist="";
		$("#newsubassign tr").each(function(e){
			var subass=$("#org"+$(this).attr("id")).val()+","+$("#staff"+$(this).attr("id")).val()+","+$("#txt"+$(this).attr("id")).val();
			subasslist=subasslist+subass+";";
		});
		subasslist=subasslist.substring(0, subasslist.length-1);
		$.ajax({
			type : "post",
			url : "workManage/editAssign.json",
			data : {
				assignid : $("#assignid").val(),
				percent : $("#percent").val(),
				upassign : subasslist,
			},
			success : function(data){
				alert(data.message);
				if(data.flag==1){
					location.reload();
				}
			}
		});
	});
	$("#btnReturn").click(function(e){
		location.href="workManage/goWorkControl";
	});
});
function assignwork(orgid,staffid,staffname){
	var tmp=$.now();
	var worktmp="<tr id="+tmp+"><td>"+staffname+"<input type='hidden' id='staff"+tmp+"' value='"+staffid+"'></td>" +
			"<td><textarea id='txt"+tmp+"' style='width:500px;height:50px;'></textarea><input id='org"+tmp+"' type='hidden' value='"+orgid+"'></td>" +
			"<td><a href='javascript:void(0);' onclick='cancelassign("+tmp+")' class='cancel'>取消</a></td>" +
			"</tr>";
	$("#newsubassign").append(worktmp);
	$("#choickstaff").window('close');
}
function cancelassign(assignid){
	$("#"+assignid).remove();
}

function endSubAssign(subAssignid){
	$.ajax({
		type : "post",
		url : "workManage/endSubAssign.json",
		data : {
			subAssignid:subAssignid,
		},
		success : function(data){
			alert(data.message);
		}
	});
}
function extendSubAssign(subAssignid){
	$.ajax({
		type : "post",
		url : "workManage/getResponseList.json",
		data : {
			subAssignid:subAssignid,
		},
		success:function(data){
			$("#response"+subAssignid).addClass("response");
			for(var i=0;i<data.responseList.length;i++){
				var response=$("#response"+subAssignid).html()+"<div class='RDate'><span class='recoverytime'>回复时间："+data.responseList[i].responseTime+"，回复进度："+data.responseList[i].percent+"%</span><br/>"+data.responseList[i].content+"</div><div class='dashed'></div>";
				$("#response"+subAssignid).html(response);
			}
			$("#extbtn"+subAssignid).remove();
			$(".dashed:last").remove();
		}
	});
}
function refreshSubWrok(subAssignid,receiveOrgName,receiveStaffName,percent,statusmsg,status,content){
	var subAssign=receiveOrgName+receiveStaffName+"  "+percent+"% "+statusmsg;
	if(status==1){
		subAssign=subAssign;
	}
	$("#subAssign"+subAssignid).html();
}