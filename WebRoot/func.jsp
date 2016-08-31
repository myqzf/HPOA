<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'func.jsp' starting page</title>
    
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
    <a href="<%=path %>/system/getRoleInfo">角色管理</a>
    <br>
    <a href="<%=path %>/system/toFuncList">系统权限管理</a>
     <br>
    <!-- 员工信息维护模块 -->
    <a href="/HPOA/staff/gotoListStaff">  跳转至全部员工信息列表页面</a>
     <br>
    <a href="/HPOA/staff/gotoAddStaff">  跳转至添加员工信息页面</a>
     <br>
    <!-- 字典表管理模块 -->
     <a href="<%=path %>/dictionary/godictictionHead">字典表管理</a>
  </body>
</html>
