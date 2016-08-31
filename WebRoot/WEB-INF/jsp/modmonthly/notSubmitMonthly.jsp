<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>月报未提交人员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>

    <!-- jqgridCss -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/redmond/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/ui.jqgrid.css" />  	
	<!-- jqgridJs -->
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/i18n/grid.locale-cn.js"></script>

    <!-- artDialog -->
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script> 
	
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modmonthly/notSubmitMonthly.js"></script>
  </head>
  
  <body>
     <!-- artDialog 对话框   未提交人员 -->
	
       <div class='jqtable_boxy'  id="jqtable_boxy">   
                <table id="notSubmitlist"  class="scroll" cellpadding="0" cellspacing="0"></table>
	 	    <div id="pager1" class="scroll"></div>  
       </div>
        
  </body>
</html>
