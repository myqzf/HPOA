<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>费用报销单</title>
    
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
	<script type="text/javascript" src="<%=path%>/js/modreimburse/viewPurchReq.js"></script>
  </head>
  
  <body>
    <div align="center">
  	<h1>费用报销单</h1>
  	<table style="min-height: 80px;" border="1px solid" width="686" height="98">
  		<tr>
  		  	<td colspan="5" align="left">报销部门：<label id="dept">${staff.staffDept}</label></td>
  		</tr>
  		<tr>
  		  	<td align="left">报销人员：<label id="name">${staff.staffName}</label></label></td>
  		  	<td colspan="4">${reidate}</td>
  		</tr>
	  	<tr>
	  		<th align="center" style="width:200px;height:18px">  				
 				<label>货&nbsp;物&nbsp;名&nbsp;称</label>
 			</th>
	  		<th align="center" style="width:200px;height:18px">
	  			<label>数&nbsp;&nbsp;量</label>
	  		</th>
  			<th align="center" style="width:200px;height:18px">
  				<label>单&nbsp;&nbsp;价</label>
  			</th>
  			<th align="center" style="width:200px;height:18px">
  				<label>金&nbsp;&nbsp;额</label>
  			</th>
  			<th align="center" style="width:200px;height:18px">
  				<label>备&nbsp;&nbsp;注</label>
  			</th>
	  	</tr>
	  	<c:forEach items="${list}" var="s">
 		<tr>
			<td>
  				${s.reimname}
  			</td>
  			<td>
  				${s.bak1}
  			</td>
  			<td>
  				${s.bak2}
  			</td>
  			<td>
  				${s.reimmoney}
  			</td>
  			<td>
  				${s.reimsum}
  			</td>
  		</tr>
  		</c:forEach>
  		<tr>
  			<td colspan="3">合计</td>
  			<td colspan="2">${reimoney}</td>
  		</tr>
		<tr>
  			<td align="left" colspan="5">  			
 				人民币大写：${uppermoney}
 			</td>
  		</tr>
  	</table>
	<input type="button" value="返回" id="btnReturn">
  </body>
</html>
