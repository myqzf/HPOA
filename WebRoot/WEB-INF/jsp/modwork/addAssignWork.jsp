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
    
    <title>My JSP 'asignwork.jsp' starting page</title>
    
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
	<!-- 富文本编辑器 -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.parse.min.js"> </script>
    <!-- easyui -->
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/locale/easyui-lang-zh_CN.js"></script> 
	<!-- addAssign.js -->
	<script type="text/javascript" src="<%=path %>/js/modwork/addAssign.js"></script>
	<style>
		/*body{font-}*/
		label{font-size:14px;}
	</style>
  </head>
  <body style="font-family:宋体;">
  	
  	<input type="hidden" value="${defaultOrgid}" id="orgid">
  	<div class="danduhuibao-bg"><label id="orgName">${defaultOrgName}&nbsp;任务</label></div>
	<div class="fenpeigongzuo"><label>工作标题:</label><input class="ddhb-kuang" type="text" id="title" /></div>
	<div class="fenpeigongzuo-gznr"><label>工作内容</label>
		<div style="margin-top:5px;">
			<div id="editor" type="text/plain" style="width:800px;height:375px;">${noSendMessageDetail.content }</div>
		</div>
		<script type="text/javascript">
			var editor = UE.getEditor("editor");
		</script>
	</div>
	<input type="button" value="添加任务" id="addsubassign">
	<table id="subassign">
		
	</table>
	<input type="button" value="确定" id="ok">
	<div id="choickstaff">
		<c:forEach items="${subStaff}" var="li">
			<div>
				<a href="javascript:void(0);" onclick="assignwork('${li.orgid}','${li.staffid }','${li.staffName}');">${li.staffName}</a>
			</div>
		</c:forEach>
	</div>
	
	<c:if test="${flag}">
  		<div id="choiceorg" class="easyui-window" title="选择部门" collapsible="false" minimizable="false"
	        maximizable="false" modal="true" closed="false" style="width: 300px; height: 300px; padding: 5px;
	        background: #fafafa;">
	        <c:forEach items="${orgList}" var="li">
				<div>
					<a href="javascript:void(0);" onclick="choiceorg('${li.orgId}','${li.orgName}');">${li.orgName}</a>
				</div>
			</c:forEach>
  		</div>
  	</c:if>
  </body>
</html>
