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
    
    <title>My JSP 'workManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/style/modwork/css/style.css">
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
    <!-- easyui -->
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- assignManage.js -->
	<script type="text/javascript" src="<%=path %>/js/modwork/assignManage.js"></script> 
  </head>
  
  <body>
	<input type="hidden" value="${assignWork.assignId}" id="assignid">
	<div><label class="worktitle">工作标题:${assignWork.title}</label></div>
	<div class="worktitl2">
		<div class="worktitle1">
			<label>总进度</label>
			<c:if test='${assignWork.status=="1"}'>
				<input type="text" id="percent" value="${assignWork.totalpercent}">
			</c:if>
			<c:if test='${assignWork.status=="4"}'>
				<label>${assignWork.totalpercent}%</label>
			</c:if>
		</div>
		
	</div>
	<hr>
	<div><label class="worktitle3">工作内容</label>
		<div style="margin-top:5px;">
			<div class="text" style="width:800px;min-height:375px;">${assignWork.content }</div>
		</div>
	</div>
	<c:forEach items="${subAssign}" var="assign">
		<div class="subAssign" id="subAssign${assign.subAssignid}"><span class="recoverytime">${assign.receiveOrgName},${assign.receiveStaffName}&nbsp;&nbsp;${assign.percent}%&nbsp;${assign.statusmsg}</span>
			<c:if test='${assign.status=="1"}'>
				<a href="javascript:void(0);" onclick="endSubAssign('${assign.subAssignid}')">完成</a>&nbsp;&nbsp;
			</c:if>
			<c:if test="${assign.count!=null}">
				<a href="javascript:void(0);" id="extbtn${assign.subAssignid}" onclick="extendSubAssign('${assign.subAssignid}')">展开回复</a>
			</c:if>
			<div class="testmodule">
				${assign.content}
			</div>
			<div id="response${assign.subAssignid}">
			</div>
		</div>
	</c:forEach>
	<c:if test='${assignWork.status=="1"}'>
		<input type="button" value="添加任务" id="addsubassign">
		<table id="newsubassign">
			
		</table>
		<input type="button" value="更新工作状态" id="btnRenewWork">
		<input type="button" value="完成工作" id="btnEndWork">
	</c:if>
	<c:if test='${assignWork.status=="4"}'>
		<label class="remark">结束备注</label>
		<div>
			<c:if test="${assignWork.bak1==null}">
				<label class="none">（无）</label>
			</c:if>
			<c:if test="${assignWork.bak1!=null}">
				<label class="none">${assignWork.bak1}</label>
			</c:if>
		</div>
	</c:if>
	<input type="button" value="返回" id="btnReturn">
	<div id="choickstaff">
		<c:forEach items="${subStaff}" var="li">
			<div>
				<a href="javascript:void(0);" onclick="assignwork('${li.orgid}','${li.staffid }','${li.staffName}');">${li.staffName}</a>
			</div>
		</c:forEach>
	</div>
	<div id="endbak">
		<label>请输入工作完结备注（可以不写）</label><br/>
		<textarea id="txtendbak"></textarea><br/>
		<input type="button" value="确定" id="btnEnd">
	</div>
  </body>
</html>
