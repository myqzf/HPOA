<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/modlogin/top.js"></script>
	
  </head>
  
  <body>
  	<font color="red" size="16">欢迎使用XX系统</font><br/>
	欢迎您：${userDetailedCall}<a href="javascript:void(0);" onclick="loginout();">注销</a>
	<a href="javascript:void(0);" onclick="turnmainpage('menu/goModifyPWD');">修改密码</a><br/>
  </body>
</html>
