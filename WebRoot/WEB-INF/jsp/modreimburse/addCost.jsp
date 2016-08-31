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
    
    <title>新增费用报销单</title>
	
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- addReimburse.js -->
	<script type="text/javascript" src="<%=path%>/js/modreimburse/addCost.js"></script>
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
  	<h1>费用报销单</h1>
  	<label>申请部门：</label>${staff.staffDept}
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<label id="date" ></label>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	单据共<input type="text" id="reinum" width="5px;">页
  	<table style="width:700px;min-height:80px;" border="1px solid">
	  	<tr>
	  		<th align="center" style="width:200px;height:18px">  				
 				<label>报&nbsp;销&nbsp;项&nbsp;目</label>
 			</th>
	  		<th align="center" style="width:200px;height:18px">
	  			<label>事&nbsp;由&nbsp;说&nbsp;明</label>
	  		</th>
	  		<th align="center" style="width:200px;height:18px">
  				<label>报&nbsp;销&nbsp;金&nbsp;额</label>
  			</th>
	  	</tr>
	  	

 		<tr>
			<td>
  				<input type="text" id="item0" class="reim"  maxLength="30">
  			</td>
  			<td>
  				<textarea id="remark0" class="reim" maxLength="80"></textarea>
  			</td>
  			<td>
  				<input type="text" id="money0" class="reim" maxLength="14">
  			</td>
  		</tr>

			<tr id="tab">
				<td colspan=2 align="center"><label>合&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计</label>
				</td>
				<td align="center">
					<label id="sum"></label><input type="button" id="statistics" value="统计">
				</td>
			</tr>
			<tr>
  			<td>  			
 				<label>金额大写：</label>
 			</td>
  			<td colspan=2>
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
