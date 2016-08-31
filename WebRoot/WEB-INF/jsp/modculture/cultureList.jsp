<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>企业文化列表</title>
    
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
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	
	
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modculture/cultureList.js"></script>
	  

	<style type="text/css">
	#mianbg_mid .datagrid-btable tr{height: 30px;}
	.l-btn-text{line-height:20px;}
	.datagrid-btable tr span{color:blue;}
	</style>

  </head>
  
   <body class="mainbody">
    <div id="mainbgcon" class="mainbgcon">
	   <div class="mainbg_ltop"></div>
	   <div id="mainbg_midtop" class="mainbg_midtop">
	     <div class="maintoptit">
	      <p>企业文化->企业文化列表</p>
	     </div>
	   </div>
	   
	   <div class="mainbg_rtop"></div>
		<div id="mianbg_mid" class="mianbg_mid">
	     <div id="main_midntitbg" class="main_midntitbg">
	      <div class="main_midntitwz">
	     	<p>企业文化列表</p>
		</div>
		</div>
		<br><br>
		
		
<!-- 		<div style="margin:20px 0;"></div> -->
<!-- 	<table id="dg" title="Custom DataGrid Pager" style="width:700px;height:250px" -->
<!-- 			data-options="rownumbers:true,singleSelect:true,pagination:true,url:'HPOA/commonjs/easyui/demo/datagrid/datagrid_data1.json',method:'get'"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<th data-options="field:'itemid',width:80">Item ID</th> -->
<!-- 				<th data-options="field:'productid',width:100">Product</th> -->
<!-- 				<th data-options="field:'listprice',width:80,align:'right'">List Price</th> -->
<!-- 				<th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th> -->
<!-- 				<th data-options="field:'attr1',width:240">Attribute</th> -->
<!-- 				<th data-options="field:'status',width:60,align:'center'">Status</th> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 	</table> -->
	<br>
	  <table id="grid" style="width: 1024px" title="用户操作" iconcls="icon-view">            
            </table>
		
		
		
	
		
		
		</div>
	</div>
  </body>
  
</html>
