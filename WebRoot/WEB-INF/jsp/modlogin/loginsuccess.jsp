<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hpkj.login.vo.UserInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎您，<%=((UserInfo)session.getAttribute("user")).getUserName() %></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  	<frameset rows="123,*" border="1" frameSpacing="0" frameBorder="1">
		<frame name="header" id="header" src="<%=path%>/menu/turnTop" scrolling="no" noresize>
		<frameset cols="166,*"  name="main">
			<frame name="menu" id="menuFrame" src="<%=path%>/menu/turnMenu" scrolling="auto">
            <frame name="main" id="mainFrame" src="<%=path%>/menu/turnMain" scrolling="auto">
		</frameset>
		
	</frameset><noframes></noframes>
</html>
