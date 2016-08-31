<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购报销单</title>
    
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
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- viewPurchase.js -->
	<script type="text/javascript" src="<%=path%>/js/modreimburse/viewItem.js"></script>
  </head>
  
  <body>
    <div align="center">
  	<h1>项目报销单</h1>
  	报销部门：<label id="dept">${staff.staffDept}</label>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	报销人员：<label id="name">${staff.staffName}</label>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<label id="date" ></label>
  	
  	<table style="min-height: 80px;" border="1px solid" width="686" height="98">
	  	<tr>
	  		<th align="center" style="width:200px;height:18px">  				
 				<label>项&nbsp;目&nbsp;名&nbsp;称</label>
 			</th>
	  		<th align="center" style="width:200px;height:18px">
	  			<label>金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额</label>
	  		</th>
	  		<th align="center" style="width:200px;height:18px">
  				<label>附项目合同几份</label>
  			</th>
  			<th align="center" style="width:200px;height:18px">
  				<label>备注（简述工程量和单价）</label>
  			</th>
	  	</tr>
 		<tr>
			<td>
  				${entryname}
  			</td>
  			<td>
  				${reimoney}
  			</td>
  			<td>
  				${reinum}
  			</td>
  			<td>
  				${remark}
  			</td>
  		</tr>
		<tr>
  			<td>  			
 				<label>金额大写：</label>
 			</td>
  			<td colspan=3>
  				${uppermoney}
  			</td>
  		</tr>
  	</table>
	<input type="button" value="返回" id="btnReturn">
  </body>
</html>
