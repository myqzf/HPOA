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
    
    <title>My JSP 'individualResponse.jsp' starting page</title>
    
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
	<!-- individualResponse.js -->
	<script type="text/javascript" src="<%=path %>/js/modwork/individualResponse.js"></script>
  </head>
  
  <body>
	<c:if test="${leaderList.size()>1}">
		<div id="winLeaderList" class="easyui-window" title="选择要回复的上级" collapsible="false" minimizable="false"
		        maximizable="false" modal="true" closed="false" closable="false" style="width: 300px; height: 300px; padding: 5px;
		        background: #fafafa;">
			<c:forEach items="${leaderList}" var="leader">
				以<a href="javascirpt:void(0);" onclick="choiceLeader('${leader.leaderid}','${leader.leaderName}','${leader.orgid}')">${leader.leaderName}</a>汇报<br/>
			</c:forEach>
		</div>
	</c:if>
	<input type="hidden" value="${defaultLeaderid}" id="receiverid">
	<input type="hidden" value="${defaultorg}" id="orgid">
	<div class="danduhuibao-bg"><label id="receiverName">以 ${defaultLeaderName} 汇报</label></div><br/>
	<label>标题：</label><input class="ddhb-kuang" type="text" id="title">
	<div>
		<div id="editor" type="text/plain" style="width:800px;height:375px;">${noSendMessageDetail.content}</div>
		<script type="text/javascript">
			var editor = UE.getEditor("editor");
		</script>
	</div>
	<input type="button" id="btnResponse" value="汇报">
  </body>
</html>
