<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>权限管理</title>
    <meta http-equiv="x-ua-compatible" content="ie=8" />
	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=8">
	
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
	<!-- funcList -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modsystem/funcList.js"></script>

    <style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		.ztree li span.button.add1 {margin-left:2px; margin-right: -1px; background-position:-110px -30px; vertical-align:top; *vertical-align:middle}
	</style>
  </head>
  
   <body>  
	     <p>权限管理</p>
	<br>
	<br>
	
		<a href="/HPOA/system/toAddMainFunc">新增主权限</a>
	
	<br>
	<br>
	 	<ul id="treeDemo" class="ztree"  style="width:100%;"></ul>
  </body>
</html>
