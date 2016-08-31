<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function(e){
	
		//上传照片
	$("#modifyPicture").click(function(e){			
			window.open("/HPOA/forum/gotoUploadPicture?staffId="+$("#staffId").val(),
				"upload","height=200,width=500,top=400,left=400,alwaysRaised=yes,location=no");
	});
	
	
	});
	
	</script>


  </head>
  
  <body>
  
    <form action="<%=path%>/forum/register" method="post">
    	<input name="staffId" id="staffId" value="${staffID}">
    	<input name="sessionId" value="${sessionId}">
    	
    	昵称：<input name="nickName">
    	
<!-- 		<div style="margin-left:70px;" > -->
<!-- 	     <img height="145" width="100" id="picture" name="picture" src="staffpicture/none.jpg" scrolling="no" /><br/> -->
<!-- 	     <label><a href="javascript:void(0)" id="modifyPicture">上传照片</a></label> -->
<!--        </div> -->
    	
    	
    	<input type="submit" value="注册">
    </form>
    <br>
    <button onclick="history.go(-1)">返回</button>
  </body>
</html>
