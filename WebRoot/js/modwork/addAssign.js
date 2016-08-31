$(document).ready(function(e){
	//$(".danduhuibao-bg").css("width",$("body").css("width").substring(0,$("body").css("width").indexOf("p")));
	$(".danduhuibao-bg").css("width",$("body").css("width"));
	$("#choickstaff").window({title:"选择下属员工",width:400,height:300,modal:true,shadow:true,closed:true,resizable:false});
	$("#addsubassign").click(function(e){
		$("#choickstaff").window('open');
	});
	$("#ok").click(function(e){
		
		//循环拼接任务，每条任务用分号(;)隔开，每个字段用逗号(,)隔开，字段顺序为：部门id、员工id、工作说明
		var subasslist="";
		if($("#title").val().length==0){
			alert("请填写工作标题");
			return false;
		}
		$("#subassign tr").each(function(e){
			var subass=$("#org"+$(this).attr("id")).val()+","+$("#staff"+$(this).attr("id")).val()+","+$("#txt"+$(this).attr("id")).val();
			subasslist=subasslist+subass+";";
		});
		if(subasslist.length==0){
			alert("请分配任务");
			return false;
		}
		subasslist=subasslist.substring(0, subasslist.length-1);
		
		$.ajax({
			type : "post",
			url : "assignWork/addWork.json",
			data : {
				orgid:$("#orgid").val(),
				upassign:subasslist,
				title:$("#title").val(),
				content:editor.getContent(),
			},
			success:function(data){
				alert(data.message);
				if(data.flag==1){
					location.reload();
				}
			}
		});
	});
});
function assignwork(orgid,staffid,staffname){
	var tmp=$.now();
	var worktmp="<tr id="+tmp+"><td>"+staffname+"<input type='hidden' id='staff"+tmp+"' value='"+staffid+"'></td>" +
			"<td><textarea id='txt"+tmp+"' style='width:500px;height:50px;'></textarea><input id='org"+tmp+"' type='hidden' value='"+orgid+"'></td>" +
			"<td><a href='javascript:void(0);' onclick='cancelassign("+tmp+")'  class='cancel'>取消</a></td>" +
			"</tr>";
	$("#subassign").append(worktmp);
	$("#choickstaff").window('close');
}
function cancelassign(assignid){
	$("#"+assignid).remove();
}
function choiceorg(orgid,orgName){
	$("#orgid").val(orgid);
	$("#orgName").html(orgName+"&nbsp;任务");
	$.ajax({
		type : "post",
		url : "assignWork/getSubStaff.json",
		data : {
			orgid:$("#orgid").val(),
			},
		success:function(data){
			var stafflist="";
			for(var i=0;i<data.subStaff.length;i++){
				stafflist=stafflist+'<a href="javascript:void(0);" onclick="assignwork(\''+data.subStaff[i].orgid+'\',\''+data.subStaff[i].staffid+'\',\''+data.subStaff[i].staffName+'\');">'+data.subStaff[i].staffName+'</a><br/>';
			}
			$("#choickstaff").html(stafflist);
		}
	});
	$("#choiceorg").window("close");
}