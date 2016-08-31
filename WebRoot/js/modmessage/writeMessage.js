function cancelrecever(id,obj){
	var receversid=$("#staffsid").val().split(",");
	var tmp="";
	for(var i=0;i<receversid.length;i++){
		if(id!=receversid[i]){
			tmp=tmp+receversid[i]+",";
		}
	}
	tmp=tmp.substring(0,tmp.length-1);
	$("#staffsid").val(tmp);
	obj.parentNode.parentNode.remove();
}
$(document).ready(function(e){
	$("#choice").click(function(e){
		art.dialog.open("/HPOA/message/goChoiceUser?receiversid="+$("#staffsid").val(),{
			title:"请选择收件人",
			background: '#ccc', // 背景色
			opacity: 0,	// 透明度
			lock:true,
			height:300,
			width:650
		});
	});
	$("#clear").click(function(e){
		$("#staffsid").val("");
		//$("#receversname").html("");
		$("#recevertab").html("");
	});
	$("#save").click(function(e){
		if($("#staffsid").val()==""){
			art.dialog.alert("请选择收件人");
			return false;
		}
		if($("#title").val()==""){
			art.dialog.alert("请输入主题");
			return false;
		}
		$.ajax({
			type : "post",
			url : "message/saveMessage.json",
			data : {
				receiverid:$("#staffsid").val(),
				messageid:$("#messageid").val(),
				createDate:$("#createDate").val(),
				title:$("#title").val(),
				content:editor.getContent(),
				},
			success:function(data){
				art.dialog({
					title:"提示",content:data.flagmsg,style:"success",background:'#ccc',opacity: 0,lock:true,
						button:{name:"确定", callback:function(){
							if(window.attachEvent) {
								location.href='goMessage?flag=nosendmsg';
							}else{
								location.href='message/goMessage?flag=nosendmsg';
							}
						}},
						close:function(){
							if(window.attachEvent) {
								location.href='goMessage?flag=nosendmsg';
							}else{
								location.href='message/goMessage?flag=nosendmsg';
							}
						}
				});
			}
		});
	});
	$("#send").click(function(e){
		if($("#staffsid").val()==""){
			art.dialog.alert("请选择收件人");
			return false;
		}
		if($("#title").val()==""){
			art.dialog.alert("请输入主题");
			return false;
		}
		$.ajax({
			type : "post",
			url : "message/sendMessage.json",
			data : {
				receiverid:$("#staffsid").val(),
				messageid:$("#messageid").val(),
				createDate:$("#createDate").val(),
				title:$("#title").val(),
				content:editor.getContent(),
				},
			success:function(data){
				art.dialog(
					{title:"提示",content:data.flagmsg,background:'#ccc',opacity: 0,lock:true,style:"success",
						button:{name:"确定", callback:function(){
							if(window.attachEvent) {
								location.href='goMessage?flag=sendmsg';
							}else{
								location.href='message/goMessage?flag=sendmsg';
							}
						}},
						close:function(){
							if(window.attachEvent) {
								location.href='goMessage?flag=sendmsg';
							}else{
								location.href='message/goMessage?flag=sendmsg';
							}
						}
					}
				);
			}
		});
	});
	$("#retu").click(function(e){
		if(window.attachEvent) {
			location.href='goMessage?flag=nosendmsg';
		}else{
			location.href='message/goMessage?flag=nosendmsg';
		}
	});
});
