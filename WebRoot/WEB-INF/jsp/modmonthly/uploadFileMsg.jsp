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
    <title>提示</title>
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>	
	<!--  uploadFile.js-->
	<script type="text/javascript" src="<%=path%>/js/modmonthly/uploadFile.js"></script>	
  </head>
<!-- 	<script type="text/javascript"> -->
<!-- 		$(document).ready(function(e){			 -->
<!-- 			$("#ok").click(function(e){ -->
<!-- 				if($("#flag").val()=="上传成功"){ -->
<!-- 				alert("上传成功了"); -->
<!-- 					window.location.href='/HPOA/monthly/goEachMonthly'; -->
<!-- 					window.location.href='/HPOA/monthly/goEachMonthly'; -->
<!-- 				} -->
<!-- 				window.close(); -->
<!-- 			}); -->
<!-- 		});		 -->
<!-- 	</script> -->
  </head>  
  <body>
  	<input type="hidden" id="flag" value="${message}">
   	${message}<br>
    <input type="button" id="ok" onclick="uoloadOK()" value="确定"/>
    
  </body>
</html>
