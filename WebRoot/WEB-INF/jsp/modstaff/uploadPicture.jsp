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
    <title>上传图片</title>
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>	
	<!--  uploadPicture.js-->
	<script type="text/javascript" src="<%=path%>/js/modstaff/uploadPicture.js"></script>
  </head>
  
  <body class="mianbg_mid" style="border:0px;">
	<form action="<%=path %>/uploadPicture/uploadPicture" method="post" enctype="multipart/form-data" onsubmit="return checkpic()" >
		<input type="hidden" name="staffId" value="${staffId }">
		<label>请选择<font color="#ff0000">扩展名为bmp、png、gif、jpeg、jpg,不大于2M</font>的图片</label><br/>
		<label><font color="#ff0000">注：上传即更改员工照片</font></label><br/>
		<input type="file" id="staffPicture" name="staffPicture" accept="image/bmp,image/png,image/gif,image/jpeg,image/jpg"/><br/>
		<input type="submit" id="submit" value="上传"/>
	</form>
  </body>
</html>
