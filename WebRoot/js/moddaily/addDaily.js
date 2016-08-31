$(document).ready(function(e){	
	var oDate = new Date(); //实例一个时间对象；
	var year=oDate.getFullYear();
	var month=oDate.getMonth()+1;
	var day=oDate.getDate();
	if(month<10){
		month='0'+month;
	}
	if(day<10){
		day='0'+day;
	}
	var x=year+'-'+month+'-'+day;
	//若标题为空则指定默认标题
	$("#daydate").val(x);	
	if($("#title").val()==''){
		$("#title").val(x+'日志');
	}
	//显示或隐藏昨日计划
	var n=2;
	//显示或隐藏昨日计划内容
	$("#planY").click(function(){
		n=n+1;
		if(n%2==0){      //n若为奇数则显示,否则隐藏
			$("#yesterday").css("display","inline");
			$("#planY").html("昨 日 计 划︽");
		}else{
		$("#yesterday").css("display","none");
		$("#planY").html("昨 日 计 划︾");
		}
	  });
	
	$("#rtn").click(function(e){
		window.location.href="/HPOA/daily/gotoDaily?daydate="+$("#daydate").val();
	})
	
	$("#addDaily").click(function(e){
		//判断标题非空
		if($("#title").val()==''){
			art.dialog.alert('标题不能为空');
			return false;
		}
		//判断内容非空
		if(editorC.getContent()==''){
			art.dialog.alert('工作总结内容不能为空');
			return false;
		}
		$.post(
			"/HPOA/dailyManage/addDaily",
			{	
				daydate:$("#daydate").val(),
				title:$("#title").val(),
				content:editorC.getContent(),
				plan:editorP.getContent(),
				
			},
			function(data){
				var message="";
				switch(data.result){
				case "0":{
					message = "系统出错";
					break;
				}
				case "1":{
					message ="提交成功";
					break;
				}
				case "2":{
					message = "提交失败";
					break;
				}
				case "3":{
					message = "工作日志内容";
					break;
				}
			}
				art.dialog({					
					title:'感谢您对我们工作的支持',
					content:message,
					opacity: 0,
					lock:true,
					button: [{
						name:'确定',
						callback: function () {
							window.location.href="/HPOA/daily/gotoDaily";
						}						
					}],
					lock:true,
					close:function(){
						window.location.href="/HPOA/daily/gotoDaily";
					}
				});
			}
		);
		
	});
	
});