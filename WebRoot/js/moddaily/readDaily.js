$(document).ready(function(e){
	var n=1;
	//显示或隐藏昨日计划内容
	$("#planY").click(function(){
		n=n+1;
		if(n%2==0){      //默认为隐藏。n若为偶数则显示,否则隐藏
			$("#yesterday").css("display","inline");
			$("#planY").html("昨 日 计 划︽");
		}else{
		$("#yesterday").css("display","none");
		$("#planY").html("昨 日 计 划︾");
		}
	  });	
	$("#retn").click(function(e){
		window.location.href="/HPOA/daily/gotoDaily?daydate="+$("#daydate").val();
	})
});
