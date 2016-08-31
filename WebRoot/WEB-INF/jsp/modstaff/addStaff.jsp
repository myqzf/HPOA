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
    
    <title>添加员工</title>
	
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- staff.js -->
	<script type="text/javascript" src="<%=path%>/js/modstaff/addStaff.js"></script>
	
	
  </head>
  
  <body class="mainbody">
			<div id="main_tablelb" class="main_tablelb">
	  		<div>
		  	<table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="900">
		  	<tr>
		  	  <td class="listtabletr5">
			  	<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
			  	<input class="tabInput" id="staffName" type="text" maxLength="50"/>
			  </td>
			  <td class="listtabletr5">
			  	<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
			  	  <select id="staffGender" style="width:120px;">
			  	  <option value="0">请选择</option>
			  	  <option value="1">男</option>
			  	  <option value="2">女</option></select>
			 </td>
			 </tr>
			 <tr>			
			 <td class="listtabletr5" >
		  	 <label>身&nbsp;份&nbsp;证&nbsp;号：</label>
				<input class="tabInput" id="staffIdcard" type="text" maxLength="70"/>
				<label id="lblIdcardCheck">请输入18位正确的身份证号码</label></td>				
		  	</td>
	 		<td class="listtabletr5">
			 <label>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>
			 	<select id="staffQualid" style="width:120px;">
			 		<option value="0">请选择</option>			 	
			 		<c:forEach  items="${qualidList}" var="sta"> 
			 		<option value='${sta.itemsid}'>${sta.itemsname}</option> 
 			 		</c:forEach> 
			 	</select>			
			 </td> 
		  	</tr>
		  	<tr>
		  	<td class="listtabletr5">
			  	<label>联&nbsp;系&nbsp;电&nbsp;话：</label>
			  	<input class="tabInput" id="staffPhone" type="text" maxLength="50"/>
			  </td>
		  	<td class="listtabletr5">
			  	<label>婚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否：</label>
					<select id="staffMarry" style="width:120px;">
				  	  <option value="0">请选择</option>
				  	  <option value="1">未婚</option>
				  	  <option value="2">已婚</option>
				  	  <option value="3">离婚</option>			  	  
				  	</select>
			 </td>
			</tr>
			<tr>
		     <td class="listtabletr5">
			  	<label>家&nbsp;庭&nbsp;住&nbsp;址：</label>
			  	<input class="tabInput" id="staffAddress" type="text" maxLength="200"/>
			  </td>		
		  	<td class="listtabletr5">	
		  		<label>隶&nbsp;属&nbsp;部&nbsp;门：</label>
		  		<select id="staffDept" style="width:120px;">
		  			<option value="0">请选择</option>
		  			 <c:forEach items="${deptList}" var="sta">
		  				<option value="${sta.itemsid}">${sta.itemsname}</option>
		  			</c:forEach>
		  		</select>		  		
		  	</td>	
			</tr>
			<tr>
		  	<td class="listtabletr5">
			  	<label>隶&nbsp;属&nbsp;职&nbsp;位：</label>
				<select id="staffPosi" style="width:120px;">
		 		<option value="0">请选择</option>			 	
		 		<c:forEach  items="${posiList}" var="sta"> 
		 		<option value='${sta.itemsid}'>${sta.itemsname}</option> 
 		 		</c:forEach> 
			 	</select>		
			  </td>
			  <td class="listtabletr5">
			  	<label>隶&nbsp;属&nbsp;公&nbsp;司：</label>
				<select id="staffComp" style="width:120px;">
			 	<option value="0">请选择</option>			 	
			 	<c:forEach  items="${compList}" var="sta"> 
			 	<option value='${sta.itemsid}'>${sta.itemsname}</option> 
 			 	</c:forEach> 
			 	</select>		
			  </td>
		  </tr>		 
		  	</table>		  
		  	&nbsp;&nbsp;<input type="button" value="添加" class="tsbtn2" id="addStaff" >
		  	<br/>
		  	<br/>
	  	</div>
		</div>
	</div>
	</div>
  </body>
</html>
