<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'noticeDetail.jsp' starting page</title>
    
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
    <table>
    	<tr>
    		<td>标题</td><td>${notice.title }</td>
    	</tr>
    	<tr>
	    	<td>发布者</td><td>${notice.author }</td>
    	</tr>
    	<tr>
    		<td>发布时间</td><td>${notice.noticetime }</td>
    	</tr>
    </table>
    <div>
    	<div style="width:80%;height:500px;overflow-y:auto;">${notice.content }</div>
    	<!-- <a href="javascript:history.go(-1);">返回</a> -->
    	<a href="javascript:void(0);" onclick='$("#tabs").tabs("close","查看公告")'>返回</a>
    </div>
  </body>
</html>
