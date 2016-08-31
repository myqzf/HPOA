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
    
    <title>My JSP 'addshare.jsp' starting page</title>
    
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
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- 富文本编辑器 -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.parse.min.js"> </script>
    <!-- addShare.js -->
    <script type="text/javascript" charset="utf-8" src="<%=path %>/js/modshare/addShare.js"></script>
    <style>
		#bg{ display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%; background-color: black; z-index:1001; -moz-opacity: 0.4; opacity:.40; filter: alpha(opacity=70);}
		#show{display: none; position: absolute; top: 25%; left: 22%; width: 53%; height: 49%; padding: 8px; border: 8px solid #E8E9F7; background-color: white; z-index:1002; overflow: auto;}
	</style>
  </head>
  
  <body>
  	<div id="bg"></div>
	<div id="show"><label>正在上传，请稍候</label></div>
  	<form action="share/addShare" method="post" enctype="multipart/form-data" onsubmit="return check();">
  		<div>标题：<input type="text" id="title" name="title"></div>
	    <div>正文：
	    	<div style="margin-top:5px;">
				<div id="editor" type="text/plain" style="width:800px;height:375px;"></div>
			</div>
			<script type="text/javascript">
				var editor = UE.getEditor("editor");
			</script>
			<input type="hidden" id="content" name="content">
	    </div>
	    <div>
	    	<input type="file" name="shareFile" id="shareFile"><br/>
	    	<input type="submit" value="上传">
	    </div>
  	</form>
  </body>
</html>
