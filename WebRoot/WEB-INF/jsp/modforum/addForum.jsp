<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addForum.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="/HPOA/forum/saveForum" method="post">
    	
    	<br>
    	<br>
    	<br>
    	板块名称：
    	<input type="text" name="sessionName">
    	
    	<br>
    	
    	板块描述：
    	<input type="text" name="sessionDesc">
    	
    	
    	<br>
    	
    	板块主题：
    	<input type="text" name="sessionPro">
    	
    
    	<input type="submit" value="提交">
    </form>
    <br>
    <button onclick="history.go(-1)">返回</button>
  </body>
</html>
