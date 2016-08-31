<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'newnotice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 富文本编辑器 -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.parse.min.js"> </script> 
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- writenotice.js -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modnotice/writenotice.js"></script>
  </head>
  
  <body>
    <table>
    	<tr>
    		<td>标题</td><td><input type="text" id="title" value="${title}" style="width:300px;"></td>
    	</tr>
    </table>
    <input type="hidden" value="${noticeid }" id="noticeid">
    <div style="margin-top:5px;">
		<textArea id="editor" type="text/plain" style="width:800px;height:375px;">${content}</textArea>
	</div>
	<script type="text/javascript">
		var editor = UE.getEditor("editor");
	</script>
	<input type="button" id="publish" class="tsbtn2" value="发布" style="display:none;"/>
	<input type="button" id="edit" class="tsbtn2" value="修改" style="display:none;"/>
	<input id="retu" type="button" onclick="history.go(-1)" class="tsbtn2" value="返回"/>
  </body>
</html>
