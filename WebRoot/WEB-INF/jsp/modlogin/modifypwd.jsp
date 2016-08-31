<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modifypwd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/modlogin/modifypwd.js"></script>
  </head>
  
  <body>
	请输入旧密码:<input id="oldpwd" type="password" name="password"/><br>
	请输入新密码:<input id="newpwd" type="password" name="password"/><br>
	确认新密码:<input id="checkpwd" type="password" name="password"/>
	<input type="button" id="modify" value="修改">
  </body>
</html>
