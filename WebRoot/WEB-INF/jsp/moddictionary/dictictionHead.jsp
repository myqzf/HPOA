<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
   <base href="<%=basePath%>">
     <title>字典表管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/jquery-1.10.2.js"> </script>
    
     <script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script> 
    
	<link href="<%=path%>/commonjs/zTree/css/demo.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/commonjs/zTree/css/zTreeStyle/zTreeStyle1.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/commonjs/zTree/css/zTreeStyle/zTreeStyle_urmioo.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.all-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/zTree/js/jquery.ztree.exedit-3.5.js"></script>


	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/moddictionary/dictictionHead.js"></script>


      <style type="text/css">
       .ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
       .ztree li span.button.add1 {margin-left:2px; margin-right: -1px; background-position:-110px -30px; vertical-align:top; *vertical-align:middle}
	</style>
  </head>
  
  <body class="mainbody">
	   <p>字典表管理</p>
		<br>
    <br>
		&nbsp;&nbsp;&nbsp;<a href="/HPOA/dictionary/addDictionaryHead">新增字典头</a>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
		<!-- <a href="/HPOA/dictionary/goAddOneDictItem">新增字典项</a> -->
	<br>
	<div>
	 	<ul id="treeDemo" class="ztree"  style="width:100%;"></ul>
  </div>
  </body>
</html>


