<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增采购报销单</title>
    
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
	<!-- addPurchase.js -->
	<script type="text/javascript" src="<%=path%>/js/modreimburse/addPurchase.js"></script>

  </head>
  
  <body>
    <div align="center">
  	<h1>采购报销单</h1>
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
  				<label>单&nbsp;据&nbsp;数&nbsp;量</label>
  			</th>
  			<th align="center" style="width:200px;height:18px">
  				<label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
  			</th>
	  	</tr>
 		<tr>
			<td>
  				<input type="text"	id="item" class="reim"  maxLength="30">
  			</td>
  			<td>
  				<input type="text" id="money" class="reim" maxLength="14" onblur="daxie()">
  			</td>
  			<td>
  				<input type="text" id="number" class="reim" maxLength="14">
  			</td>
  			<td>
  				<textarea id="remark" class="reim" maxLength="14"></textarea>
  			</td>
  		</tr>
		<tr>
  			<td>  			
 				<label>金额大写：</label>
 			</td>
  			<td colspan=3>
  				<label id="uppermoney"></label>
  			</td>
  		</tr>
  	</table>
  	<input type="hidden" id="dept" value="${staff.staffDept}"/>
  	<input type="button" id="submit" value="提交"/>
  </body>
</html>
