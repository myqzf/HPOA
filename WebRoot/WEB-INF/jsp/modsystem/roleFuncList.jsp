<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
   <base href="<%=basePath%>">
     <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
	<!-- roleFuncList -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modsystem/roleFuncList.js"></script>

  </head>
  
  <body>
  <p>该角色拥有权限</p>  
  <br>
  <br>
  <input type="hidden" name="roleId" id="roleId" value="${roleId }" />
	<div>
	 	<ul id="treeDemo" class="ztree"  style="width:100%;"></ul>
  </div>
  </body>
</html>


