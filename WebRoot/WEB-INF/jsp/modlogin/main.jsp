<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/modlogin/horizontalscroll.js"></script>
	<script type="text/javascript" src="<%=path%>/js/modlogin/verticalscroll.js"></script>
	<script type="text/javascript">
	//账号与密码相同则提示用户修改密码
		if('${flag}'=='true'){
			alert('请修改初始密码');
			location.href='<%=path%>/menu/goModifyPWD';
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function(e){
			$.ajax({
			url : "message/jsonNoReadMessage.json",
			type : "POST",
			success : function(data){
					var messages="&nbsp;";
					var wordcount=0;
					for(var i=0;i<data.noRead.length;i++){
						messages=messages+data.noRead[i].senderName+"给您发来的：<a href='/HPOA/message/goReceiveDetail?readstatus="+data.noRead[i].readstatus+"&messageid="+data.noRead[i].messageId+"'>"+data.noRead[i].title+"</a>&nbsp;";
						wordcount=wordcount+data.noRead[i].senderName.length+data.noRead[i].title.length+7;
					}
					$("#messagescroll").html(messages);
					//div最大允许宽度(包括左右内填充)
					var divwidth=$("#messagescroll").css("width").substring(0,$("#messagescroll").css("width").indexOf("p"))-$("#messagescroll").css("padding-right").substring(0,$("#messagescroll").css("padding-right").indexOf("p"));
					//链接宽度
					var linkwidth=0;
					if($("a").css("font-size")!=undefined){
						linkwidth=wordcount*$("a").css("font-size").substring(0,2);
					}
					//链接宽度大于div宽度再加载滚动条
					if(linkwidth>divwidth){
						var elem = document.getElementById("messagescroll");
						var sc = new HorizontalScroll(elem);
					}
				}
			});
			$.ajax({
			url : "notice/getNoticeScrollList.json",
			type : "POST",
			success : function(data){
					var notice="";
					var licount=0;
					for(var i=0;i<data.noticeScroll.length;i++){
						notice=notice+"<li><a href='notice/goReadNotice?noticeid="+data.noticeScroll[i].noticeid+"' title='"+data.noticeScroll[i].realTitle+"'>"+data.noticeScroll[i].title+"</a></li>";
						licount++;
					}
					$("#listnotice").html(notice);
					if($("li").css("height")!=undefined){
						if($("li").css("height").substring(0,2)*licount>$("#listnotice").css("height").substring(0,$("#listnotice").css("height").indexOf('p'))){
							var scroll2 = new ScrollText("listnotice", true,70, true);
						}
					}
				}
			});
		});
	</script>
	<style>
		#msgimg{float:left;}
		#messagescroll{overflow:hidden;border:1px solid #f60;padding:5px 5px;margin-top:10px;font-size:18px;}
		#listnotice{overflow: hidden;padding-left: 10px;height:150px;}
	</style>
		
  </head>
  
  <body>
    <div><img id="msgimg" src="/HPOA/img/message.png">
	<div id="messagescroll">
	</div>
	</div>
	<div id="notice">
		<div style="float:right;">
			公告
			<ul id="listnotice">
			</ul>
		</div>
	</div>
  </body>
</html>
