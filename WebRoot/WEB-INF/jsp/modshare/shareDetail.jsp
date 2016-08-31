<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shareDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- shareDetail.js -->
	<script type="text/javascript" src="<%=path%>/js/modshare/shareDetail.js"></script>
  </head>
  
  <body>
	<div>${shareDetail.shareTitle}</div>
	<div>共享人：${shareDetail.authorName}&nbsp;所在公司：${shareDetail.authorComp}&nbsp;所属部门：${shareDetail.authroDepart}&nbsp;上传时间：${shareDetail.shareTime}</div>
	<div>${shareDetail.content}</div>
	<div><a href="/HPOA/share/downShareFile.json?fileName=${shareDetail.shareFileName}&realFileName=${shareDetail.shareRealFileName}">${shareDetail.shareRealFileName}</a></div>
	<input type="button" value="返回" id="btnreturn">
  </body>
</html>
