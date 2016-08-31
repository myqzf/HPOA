<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reportDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/style/modwork/css/style.css">
  </head>
  
  <body>
	<label class="report3">标题：${reportDetail.title}</label>
	<div class="Reporttodepartment">
		<label class="report">汇报部门：${reportDetail.reporterOrg}</label>
		<label class="report1">汇报人：${reportDetail.reporterName}</label>
		<label class="report2">汇报时间：${reportDetail.reportTime}</label>
	</div>
	<span class="Reportcontent">汇报内容</span>
	<div class="report4" style="min-width:800px;min-height:400px;">
		${reportDetail.content}
	</div>
	<input type="button" value="返回" onclick="location.href='/HPOA/responseWork/goReceiveReport'" class="button-get">
  </body>
</html>
