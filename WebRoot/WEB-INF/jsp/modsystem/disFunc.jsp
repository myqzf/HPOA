<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
    <meta http-equiv="x-ua-compatible" content="ie=8" />
	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=8">
	<!-- jquery -->
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/jquery-1.10.2.js"> </script>
	<!-- artDialog -->
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script> 
	<!-- zTreeCss -->
	<link href="<%=path%>/commonjs/zTree/css/demo.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/commonjs/zTree/css/zTreeStyle/zTreeStyle1.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/commonjs/zTree/css/zTreeStyle/zTreeStyle_urmioo.css" rel="stylesheet" type="text/css">
	<!-- zTree -->
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.all-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.core-3.5.js"></script>	
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.exedit-3.5.js"></script>
    <!-- disFunc -->
    <script type="text/javascript" charset="utf-8" src="<%=path %>/js/modsystem/disFunc.js"></script>

  
  <body>
	  <form>
	  <p>分配权限</p>  
	  
	  <input type="hidden" id="roleId" name="roleId" value="${roleId }" />
		
		<ul id="treeDemo" class="ztree"  style="width:100%;"></ul>
	  	<br />
		<br />
	 	<input type="button" value="保存" onclick="addRoleFunc();"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" onclick="javascript:window.history.go(-1);"/>
	  	
	  </form>
  </body>
</html>
