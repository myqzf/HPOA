<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orgmanage.jsp' starting page</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- jquery -->
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/jquery-1.10.2.js"> </script>	
	<!-- artDialog -->
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script> 	
	<!-- zTree/css -->
	<link href="<%=path%>/commonjs/zTree/css/demo.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/commonjs/zTree/css/zTreeStyle/zTreeStyle1.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/commonjs/zTree/css/zTreeStyle/zTreeStyle_urmioo.css" rel="stylesheet" type="text/css">
	<!-- zTree -->
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.all-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.exedit-3.5.js"></script>
	<!-- orgmanage.js -->
	<script type="text/javascript" src="<%=path %>/js/modorg/orgmanage.js"></script>
	<style type="text/css">
       .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
       .ztree li span.button.add1 {margin-left:2px; margin-right: -1px; background-position:-110px -30px; vertical-align:top; *vertical-align:middle}
	</style>
  </head>
  
  <body>
    <input type="button" value="添加基本部门" id="addorg">
    <ul id="orgtree" class="ztree"  style="width:100%;"></ul>
  </body>
</html>
