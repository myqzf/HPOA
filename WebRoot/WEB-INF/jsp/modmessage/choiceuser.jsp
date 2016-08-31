<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请选择收件人</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- choiceUser.js -->
	<script type="text/javascript" src="<%=path %>/js/modmessage/choiceUser.js"></script>
	<style>
		.tccon { width:600px; float:left; border:none;}
		.tccon ul { list-style-type:none;}
		.tccon ul li { float:left; width:500px; height:30px; line-height:30px; margin-left:15px; margin-right:0px;  display:inline; overflow:hidden; white-space: nowrap; text-overflow: ellipsis;}
		.tccon ul li label { cursor:pointer;width:450px}
		.tccon ul li a { font-size:12px; text-decoration:none; color:#000; cursor:pointer;}
		.tccon ul li a:link,.tccon ul li a:visited { color:#000; text-decoration:none;}
		.tccon ul li a:hover { color:#000; text-decoration:underline;}
		.tccon ul li a:active{ color:#000; text-decoration:none;}
	</style>
  </head>
  
  <body class="mianbg_mid" style="border:0px;">
  	<input type="hidden" id="receversid" value="${receiversid }"/>
  	<input type="text" id="searchtxt"/><input class="tsbtn2" type="button" id="searchbtn" value="查询"/><input type="button" class="tsbtn2" value="选择" id="choicebtn"/><input type="button" class="tsbtn2" value="清空" id="clearbtn"><br/>
  	<div id="nr" style="border: 1px solid #000;width:600px;heigth:30px;overflow: auto">
		<div class="tccon">
			<ul>
		    
		    </ul>
    	</div>
    </div>
  </body>
</html>
