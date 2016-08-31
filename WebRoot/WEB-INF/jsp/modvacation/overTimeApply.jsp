<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'overTimeApply.jsp' starting page</title>
    
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
	<!-- easyui -->
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/default/easyui.css" />
    <script type="text/javascript" src="<%=path%>/commonjs/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path%>/commonjs/easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- overTimeApply.js -->
	<script type="text/javascript" src="<%=path%>/js/modvacation/overTimeApply.js"></script>
  </head>
  
  <body>
  	<label>加班时间：</label><input type="text" id="overTimeStart">至<input type="text" id="overTimeEnd"><br/>
  	<label>加班类型</label><select id="overTimeType"><option value="1">周末加班</option><option value="2">工作日加班</option><option value="3">节假日加班</option></select>
	<label>加班原因</label><br/><textarea id="content"></textarea><br/>
	<input type="button" value="提交" id="submit">
  </body>
</html>
