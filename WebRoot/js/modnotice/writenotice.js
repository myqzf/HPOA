$(document).ready(function(e){
	if(''==$("#noticeid").val()){
		$("#publish").show();
	}else{
		$("#edit").show();
	}
	$("#publish").click(function(e){
		if($("#title").val()==""){
			art.dialog.alert("请输入主题");
			return false;
		}
		$.ajax({
			type : "post",
			url : "notice/publishNoteice.json",
			data : {
				title:$("#title").val(),
				content:editor.getContent(),
				},
			success:function(data){
				art.dialog(
					{title:"提示",content:data.flagmsg,background:'#ccc',opacity: 0,lock:true,style:"success",
						button:{name:"确定", callback:function(){
							location.href="/HPOA/notice/goNoticeManage";
						}},
						close:function(){
							location.href="/HPOA/notice/goNoticeManage";
						}
					}
				);
			}
		});
	});
	$("#edit").click(function(e){
		if($("#title").val()==""){
			art.dialog.alert("请输入主题");
			return false;
		}
		$.ajax({
			type : "post",
			url : "notice/editNoteice.json",
			data : {
				noticeid:$("#noticeid").val(),
				title:$("#title").val(),
				content:editor.getContent(),
				},
			success:function(data){
				art.dialog(
					{title:"提示",content:data.flagmsg,background:'#ccc',opacity: 0,lock:true,style:"success",
						button:{name:"确定", callback:function(){
							location.href="/HPOA/notice/goNoticeManage";
						}},
						close:function(){
							location.href="/HPOA/notice/goNoticeManage";
						}
					}
				);
			}
		});
	});
});