$(document).ready(function(e){
	var receiver=new String("");
	var receivername=new String("");
	var receivernametable=new String("");
	var parentreceiver=","+$("#receversid").val()+",";
	var win = art.dialog.parent;
	$.ajax({
		type : "post",
		url : "message/getAllStaff.json",
		success:function(data){
			for(var i=0;i<data.allStaff.length;i++){
				var re = new RegExp(","+data.allStaff[i].staffId+",+");
				var li="";
				if(re.test(parentreceiver)) {
					li="<li id='"+data.allStaff[i].staffId+"'><label><input type=\"checkbox\" name='"+data.allStaff[i].staffId+"' value='"+data.allStaff[i].staffName+"' checked />"+data.allStaff[i].staffName+"</label></li>";
				}else{
					li="<li id='"+data.allStaff[i].staffId+"'><label><input type=\"checkbox\" name='"+data.allStaff[i].staffId+"' value='"+data.allStaff[i].staffName+"' />"+data.allStaff[i].staffName+"</label></li>";
				}
				$(".tccon ul").append(li);
			}
		}
	});
	$("#searchtxt").keypress(function(event){
		if(event.keyCode ==13){
			var restr=$("#searchtxt").val()+"+";
			if(restr=="+"){
				restr=" *";
			}
			var re = new RegExp(restr);
			var flag=false;
			var temp=$("#"+$("input[type=checkbox]:first").prop("name"));
			$("input[type=checkbox]").each(function(i){
				if(re.test($(this).prop("value"))) {
					$("#"+$(this).prop("name")).css("display","inline");
					if(flag){
						temp.before($("#"+$(this).prop("name")));
						temp=$("#"+$(this).prop("name"));
						flag=true;
					}else{
						temp.after($("#"+$(this).prop("name")));
						temp=$("#"+$(this).prop("name"));
					}
				}else{
					$("#"+$(this).prop("name")).css("display","none");
				}
			});
		}
	});
	$("#searchbtn").click(function(e){
		var restr=$("#searchtxt").val()+"+";
		if(restr=="+"){
			restr=" *";
		}
		var re = new RegExp(restr);
		var flag=false;
		var temp=$("#"+$("input[type=checkbox]:first").prop("name"));
		$("input[type=checkbox]").each(function(i){
			if(re.test($(this).prop("value"))) {
				$("#"+$(this).prop("name")).css("display","inline");
				if(flag){
					temp.before($("#"+$(this).prop("name")));
					temp=$("#"+$(this).prop("name"));
					flag=true;
				}else{
					temp.after($("#"+$(this).prop("name")));
					temp=$("#"+$(this).prop("name"));
				}
			}else{
				$("#"+$(this).prop("name")).css("display","none");
			}
		});
	});
	$("#choicebtn").click(function(e){
		$("input[type=checkbox]").each(function(i){
			if($(this).prop("checked")) {
				receiver=receiver+$(this).prop("name")+",";
				receivername=receivername+$(this).val()+",";
				receivernametable=receivernametable+"<tr><td>"+$(this).val()+"&nbsp;&nbsp;<a href='javascript:void(0);' onclick=cancelrecever('"+$(this).prop("name")+"',this)>取消</a></td></tr>";
			}
		});
		receiver=receiver.substring(0,receiver.length-1);
		receivername=receivername.substring(0,receivername.length-1);
		//win.document.getElementById("receversname").innerHTML=receivername;
		win.document.getElementById("recevertab").innerHTML=receivernametable;
		win.document.getElementById("staffsid").value=receiver;
		art.dialog.close();
	});
	$("#clearbtn").click(function(e){
		$("input[type=checkbox]").attr("checked",false);
	});
});