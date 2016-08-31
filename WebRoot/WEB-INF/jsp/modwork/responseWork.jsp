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
    
    <title>My JSP 'responseWork.jsp' starting page</title>
    
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
	<!-- responseWork.js -->
	<script type="text/javascript" src="<%=path %>/js/modwork/responseWork.js"></script> 
  </head>
  
  <body>
  	<input type="hidden" value="${workDetail.subAssignid}" id="subassignid">
	<div class="reportwork">
		<label class="subsequentdevelopmentwork">${workDetail.title}</label>
		<label class="departments">发布部门：${workDetail.publishOrgName},发布时间:${workDetail.workStartTime},总工作进度${workDetail.workPercent}%</label><hr>
		<div>
			${workDetail.content}
		</div>
	</div>
	<div class="receipt">
		<span class="receipttime">接收时间：${workDetail.subWorkStartTime},目前进度${workDetail.subWorkPercent}%</span><br/>
		<p class="Responsibleforthemodule">你的任务：${workDetail.subContent}</p>
	</div>
	<div class="yourreply-out">你的回复：</div>
	<div class="yourreply">
		
		<c:if test="${responseList.size()==0}">
			<label>（无）</label>
		</c:if>
		<c:forEach items="${responseList}" var="li">
			<div class="RDate">
				<span class="time-hui">${li.responseTime}</span><br/>
				<p style="line-height: 16px;"></p>${li.content}
				<div class="dashed"></div>
			</div>
		</c:forEach>
	</div>
	<div>
		<c:if test='${workDetail.status=="1"}'>
			<span class="textbox numberbox" style="width: 151px; height: 20px;margin-top:5px;"><input type="text" id="percent" value="${workDetail.subWorkPercent}"></span>
			<div style="margin-top:5px;">
				<div id="editor" type="text/plain" style="width:800px;height:375px;">${noSendMessageDetail.content }</div>
				<script type="text/javascript">
					var editor = UE.getEditor("editor");
				</script>
			</div>
			<input type="button" id="response" value="回复">
		</c:if>
		<c:if test='${workDetail.status!="1"}'>
			<label>进度：${workDetail.subWorkPercent}%</label><br/>
		</c:if>
		
		<input type="button" id="btnReturn" value="返回">
	</div>
  </body>
</html>
