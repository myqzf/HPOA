<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addCard.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/jquery-1.10.2.js"> </script>
	
	<script type="text/javascript">
		$(document).ready(function() {
 		
		if($("#bbsUserInfo").val()==0){
			alert("论坛表里没有您的信息！");
			history.go(-1);
		};
		});
	</script>

  </head>
  
  <body>
  
  		<input type="hidden" id="bbsUserInfo" value="${bbsUserInfo}">
  
        <form action="/HPOA/forum/saveCard" method="post">
    	
    	<br>
    	<br>
    	<br>    	
    	<input type="hidden" name="csid" value="${sessionId}">    	
    	<br>
    	
    	帖子名称：
    	<input type="text" name="ctitle">
    
    	<input type="submit" value="提交">
    </form>
    <br>
    <button onclick="history.go(-1)">返回</button>
  </body>
</html>
