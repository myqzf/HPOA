<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增采购申请单</title>
	
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- addReimburse.js -->
	<script type="text/javascript" src="<%=path%>/js/modreimburse/addPurchReq.js"></script>
	<%-- <script type="text/javascript" src="<%=path%>/js/modreimburse/convertCurrency.js"></script> --%>
	<style>
		/*tr{max-height:10px;}
 		input{height:10px;} 
		textarea{height:10px;}*/
		.reim{}
		#reinum{width:20px;}
	</style>
  </head>
  <body>
  	<div align="center">
  	<h1>采购申请单</h1>
  	<label>申请部门：</label>${staff.staffDept}
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<label id="date" ></label>
  	<table style="width:700px;min-height:80px;" border="1px solid">
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
	  	

 		<tr>
			<td>
  				<input type="text" id="item0" class="reim"  maxLength="30">
  			</td>
			<td>
  				<input type="text" id="num0" class="reim"  maxLength="30">
  			</td>
  			<td>
  				<input type="text" id="price0" class="reim"  maxLength="30">
  			</td>
  			<td>
  				<lable id="money0"></lable>
  			</td>
  			<td>
  				<textarea id="remark0" class="reim" maxLength="80"></textarea>
  			</td>
  		</tr>

			<tr id="tab">
				<td colspan=2 align="center"><label>合&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计</label>
				</td>
				<td colspan="3" align="center">
					<label id="sum"></label><input type="button" id="statistics" value="统计金额">
				</td>
			</tr>
			<tr>
  			<td>  			
 				<label>金额大写：</label>
 			</td>
  			<td colspan="5">
  				<label id="uppermoney"></label>
  			</td>
  		</tr>
  	</table>
  	<input type="hidden" id="dept" value="${staff.staffDept}"/>
  	<input type="hidden" id="staffid" value="${staff.staffId}"/>
  	<input type="button" id="additem" value="添加项目"/>
  	<input type="button" id="submit" value="提交"/>
  	</div>
  </body>
</html>
