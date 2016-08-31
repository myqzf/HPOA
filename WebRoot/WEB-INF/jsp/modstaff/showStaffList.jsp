<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <head>
  	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=8">
  
  <title>员工信息列表</title>
  
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- jqgrid -->
  	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/i18n/grid.locale-cn.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- staff.js -->
	<script type="text/javascript" src="<%=path%>/js/modstaff/showStaffList.js"></script>
	<!-- jqgridCss -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/redmond/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/ui.jqgrid.css" />  
	
  </head>
  
  <body class="mainbody">
   <div id="mainbgcon" class="mainbgcon">
	   <div class="mainbg_ltop"></div>
	   <div id="mainbg_midtop" class="mainbg_midtop">
	     <div class="maintoptit">
	      <p>员工管理->显示员工信息 </p>
	     </div>
	   </div>
	   <div class="mainbg_rtop"></div>
		<div id="mianbg_mid" class="mianbg_mid">
	     <div id="main_midntitbg" class="main_midntitbg">
	      <div class="main_midntitwz">
	     	<p>员工信息列表</p>
		</div>
		</div>
		
		<div id="main_tablelb" class="main_tablelb">
            <table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="70%" style="margin:1px 0 0 1px;">
			   <form action="" theme="simple" >
			   <tr>
				<td class="listtabletr5">
				  <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
				  <input type="text" class="tabInput" style="width:180px;" id="staffName"/>
				</td>
				<td class="listtabletr5" >
				  <label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
				<select id="staffGender" style="width:120px;">
				<option value="0">请选择</option>
				<option value="1">男</option>
				<option value="2">女</option>
				</select>
				</td>				
				<td class="listtabletr5">
				<label>隶&nbsp;属&nbsp;公&nbsp;司</label>
					<select id="staffComp" style="width:150px;">
			 		<option value="0">请选择</option>			 	
			 		<c:forEach  items="${compList}" var="sta"> 
			 		<option value='${sta.itemsid}'>${sta.itemsname}</option> 
 			 		</c:forEach> 
			 		</select>		
			  
		  		 </td>
				</tr>
			   <tr>
				<td class="listtabletr5">
				 <label>身&nbsp;份&nbsp;证&nbsp;号</label>
				 <input type="text" class="tabInput" style="width:180px;" id="staffIdcard"/>
				</td>
		  		<td class="listtabletr5">
				 <label>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历</label>
				 <select id="staffQualid" style="width:120px;">
			 		<option value="0">请选择</option>			 	
			 		<c:forEach  items="${qualidList}" var="sta"> 
			 		<option value='${sta.itemsid}'>${sta.itemsname}</option> 
 			 		</c:forEach> 
			 	</select>			
		  		</td>		  		
		  		  <td class="listtabletr5">
		  		  <label>隶&nbsp;属&nbsp;部&nbsp;门</label>
					<select id="staffDept" style="width:150px;">
					<option value="0">请选择</option>
		  			 <c:forEach items="${deptList}" var="sta">
		  				<option value="${sta.itemsid}">${sta.itemsname}</option>
		  			</c:forEach>
		  			</select>
				  
				 </td>
				</tr>			
				<tr>
				<td class="listtabletr5">
				 <label>住&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
				  <input type="text" class="address" style="width:180px;" id="staffAddress"/>
		  		 </td>
				 <td class="listtabletr5" >
				  <label>婚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否</label>
				<select id="staffMarry" style="width:120px;"><option value="0">请选择</option>
				<option value="1">未婚</option>
				<option value="2">已婚</option>
				<option value="3">离异</option>				
				</select>
				</td>		  		
				 <td class="listtabletr5">
					<label>隶&nbsp;属&nbsp;职&nbsp;位</label>				
				 	<select id="staffPosi" style="width:150px;">
			 		<option value="0">请选择</option>			 	
			 		<c:forEach  items="${posiList}" var="sta"> 
			 		<option value='${sta.itemsid}'>${sta.itemsname}</option> 
 			 		</c:forEach> 
			 	</select>		
				</td>
				</tr>
				<tr>
				<td colspan="2"></td>				
				<td class="listtabletr5" align="center">
				<input type="button" class="tsbtn2" value="查询" id="searchStaff" onclick="toExcel();"/>
				<input type="reset" class="tsbtn2" value="清空" id="clearStaff"/>
				</td>  	
				</tr>
			</form>
			</table>
			
			
			<a href="javaScript:void(0);" id="downExcel" onclick="toExcel();">生成excel表</a>		
			
			
			<div style="margin:1px 0 0 1px;">
			<div style="margin-bottom:10px; display: none " id="list">
				<table id="pfList" class="scroll" cellpadding="0" cellspacing="0"></table>
				<div id="pager1" class="scroll"></div>
			</div>
		</div>
	</div>
	</div>
	</div>
  </body>
</html>
