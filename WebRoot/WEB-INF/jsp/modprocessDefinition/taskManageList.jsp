<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
<head>
    <title>任务管理列表</title>
    	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	
	<!-- easyui -->
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	
	
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modprocessDefinition/taskManageList.js"></script>
	
	<style type="text/css">
	#mianbg_mid .datagrid-btable tr{height: 30px;}
	.l-btn-text{line-height:20px;}
	.datagrid-btable tr span{color:blue;}
	</style>

</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
                                       个人任务管理列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
 <input type="hidden" name="message" id="message" value="${rtnFlg}" />
  <table id="grid" style="width: 1024px" title="个人任务管理列表" iconcls="icon-view">            
            </table>

<!--     <c:forEach  items="${list}" var="sta">  -->
<!-- 			 		<input type="text" name="message" id="message" value="${sta.id}" /> -->
<!-- 			 		<input type="text" name="message" id="message" value="${sta.name}" /> -->
<!--      </c:forEach> -->
</div>


</body>
</html>
