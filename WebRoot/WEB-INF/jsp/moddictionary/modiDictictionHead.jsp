<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/jquery-1.10.2.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/zsy.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/moddictionary/modiDictiction.js"></script>
    <title>增加字典头</title>
    
  </head>
  
  <body class="mainbody">
	 <h>修改字典头</h>
		<form name="DictHeadForm" action="">
		 <input type="hidden" name="headId" id="headId" value="${sfi.headId }" />
			字典头名称：<input type="text" id="headName" name="headName" value="${sfi.headName }">
			<input type="button" value="提交" onclick="modifyDictHead()">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input class="butzcon" type="button" value="返回" onclick="javascript:window.history.go(-1);"/>
		</form>
  </body>
</html>
