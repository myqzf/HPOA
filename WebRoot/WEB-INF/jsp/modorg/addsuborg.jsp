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
    
    <title>添加下属部门</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- addsuborg.js -->
	<script type="text/javascript" src="<%=path%>/js/modorg/addsuborg.js"></script>
	<style>
		*{padding:0;margin:0}
		ul,li{list-style:none}
		a{text-decoration: none;}
		.orgManager{width:474px;border:1px solid #ccc;}
		
		.staffMain{height:320px;display:none}
		.checkedStaff,.nonCheckStaff{width:46%;float:left}
		.choiceStaffControl{width:8%;float:left;height:100%;}
		
		.checkedStaff ul{overflow:auto;height:300px;}
		.checkedStaff ul li{height:34px;line-height:34px;padding:0px 10px;}
		.checkedStaff ul li:hover{background:#6ee58d;cursor:pointer}
		.checkedStaff ul li.active{background:#6ee58d;}
		
		
		.choiceStaffControl div{border:1px solid #ccc;border-width:0px 1px;height:100%;}
		.choiceStaffControl div ul{padding-top:50px;}
		.choiceStaffControl ul li{font-size:20px;text-align:center;margin-bottom:25px;font-weight:bolder;cursor:pointer;color:#929492;}
		.choiceStaffControl ul li.last{padding-top:25px;}
		
		.nonCheckStaff ul{overflow:auto;height:300px;}
		.nonCheckStaff ul li{height:34px;line-height:34px;}
		.nonCheckStaff ul li:hover{background:#f3f2f2;cursor:pointer}
		.nonCheckStaff ul li.active{background:#f3f2f2;}
		.nonCheckStaff ul li span{width:95%;display:block;border-right:0px solid #e6e6e6;padding-left:10px;float:left;}
	</style>
  </head>
  
  <body>
    <input type="hidden" id="orgpid" value="${orgpid}">
    <input type="hidden" id="orgid" value="${orgid}">
    <input type="hidden" id="depid" value="${depid}">
    <table>
    	<tr>
    		<td><label>部门名称</label></td>
    		<td><input type="text" id="orgname" value="${soi.orgName}"></td>
    		<td><label>部门全称</label></td>
    		<td><input type="text" id="orgallname" value="${soi.orgAllName}"></td>
    	</tr>
    	<tr>
    		
    		<td></td>
    		<td>
    			
    		</td>
    		<td><label></label></td>
    		<td>
    			
			</td>
    	</tr>
    </table>
    <label>请选择该部门成员</label>
    <div class="orgManager">
		<div class="staffMain" style="display:block">
			<div class="checkedStaff">
				<ul>
					<c:forEach items="${checkstafflist}" var="checkstaff">
						<li id="${checkstaff.staffId}" >${checkstaff.staffName}</li>
					</c:forEach>
				</ul>
			</div>
			<div class="choiceStaffControl">
				<div>
					<ul>
						<li class="alladdElement">&lt;&lt;</li>
						<li class="addElement">&lt;</li>
						<li class="removeElement">&gt;</li>
						<li class="allremoveElement">&gt;&gt;</li>
					</ul>
				</div>
			</div>
			<div class="nonCheckStaff">
				<ul>
					<c:forEach  items="${stafflist}" var="staff"> 
			 			<li id='${staff.staffId}'><span>${staff.staffName}</span></li> 
 			 		</c:forEach> 
				</ul>
			</div>
		</div>
	</div>
    <c:if test="${flag=='new'}">
		<input type="button" id="addbtn" value="添加">
	</c:if>
	<c:if test="${flag=='edit'}">
		<input type="button" id="editbtn" value="修改">
	</c:if>
  </body>
</html>
