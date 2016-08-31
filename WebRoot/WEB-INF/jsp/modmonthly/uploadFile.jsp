<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>上传文件</title>
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>	
	<!--  uploadFile.js-->
	<script type="text/javascript" src="<%=path%>/js/modmonthly/uploadFile.js"></script>
  </head>
  
  <body class="mianbg_mid" style="border:0px;">
	<form action="<%=path %>/monthly/uploadFile" method="post" enctype="multipart/form-data" onsubmit="return checkFile()" >
		<input type="hidden" name="title" value="${title }">
		<input type="hidden" name="month" value="${month }">
		<label>请选择<font color="#ff0000">扩展名为doc或docx,不大于2M</font>的文件</label><br/>
<!-- 		<label><font color="#ff0000">注：上传即更改员工照片</font></label><br/> -->
		<input type="file" id="monthlyFile" name="monthlyFile" accept="application/msword"/><br/>
		<input type="submit" id="submit" value="上传"/>
	</form>
  </body>
</html>
