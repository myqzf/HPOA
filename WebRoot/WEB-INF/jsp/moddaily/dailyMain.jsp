<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>日志页面</title>	  
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- datepicker -->
	<link rel="stylesheet" href="<%=path%>/commonjs/datepicker/css/smoothness/jquery.ui.css" type="text/css" />
	<link rel="stylesheet" href="<%=path%>/commonjs/datepicker/css/dailystyle.css" type="text/css" />

	<!--dailyMain.js  --> 	
 	<script type="text/javascript" src="<%=path%>/js/moddaily/dailyMain.js"></script> 
 	<!-- 日志功能专用日历 -->
 	<script type="text/javascript" src="<%=path%>/js/moddaily/jquery.ui.js"></script>
 		
  </head>
  <body>    
     <center><div id="date" class="date"></div></center> 
     <br>
     <div style="margin-left:36%">
      	<input type="text" id="time" readonly style="width:150px;height:30px;font-size:18px;text-align:center"/>
      	<input type='button' id='today' value='今天' style="width:70px;height:30px;"/>      
      	<input type='button' id='read' value='查看日志' style="width:70px;height:30px;"/>
      	<input type='button' id='add' value='撰写日志' style="width:70px;height:30px;" onclick="window.location='/HPOA/daily/gotoAddDaily'"/>
      </div>
      	<input type="hidden" id="staffTime" value="${staffTime}"/>
		<input type="hidden" id="defaultTime" value="${defaultTime}"/>
      	
  </body>
<html>
