<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	
	  <!-- jqgridCss -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/redmond/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/ui.jqgrid.css" />  	
	<!-- jqgridJs -->
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/i18n/grid.locale-cn.js"></script>
      <!-- 	artDialog -->
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script> 

    <script type="text/javascript" src="<%=path%>/js/modmonthly/eachMonthly.js"></script>
    <style type="text/css">
     .ui-jqgrid td input{height: 30px;}
	</style>

<script language="javascript" for="window" event="onload">   
//     function openTheIndexPage() {       
//       alert("wfg");//360兼容模式
//     };  
//     if(document.readyState=="complete"){  
//         openTheIndexPage();  
//     }   
</script>  
  </head>
  
  <body>
  <a href="<%=path %>/monthly/goWriteMonthly">撰写或上传月报</a>
  <br><br><br>
       已提交过的月报
   <br>
    	<table id="list"  class="scroll" cellpadding="0" cellspacing="0"></table>
	 	<div id="pager" class="scroll"></div>
  </body>
  
</html>
