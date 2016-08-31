<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'requestVacation.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<script>
		$(document).ready(function(e){
			$("#test").click(function(e){
				$.ajax({
					type : "post",
					url : "vacation/test.json",
					data : {
						
					},
					success:function(data){
						console.log(data);
					}
				});
			});
			$("#createTask").click(function(e){
				$.ajax({
					type : "post",
					url : "vacation/createTask.json",
					data : {
						
					},
					success:function(data){
						
					}
				});
			});
			$("#endtask").click(function(e){
				$.ajax({
					type : "post",
					url : "vacation/endTask.json",
					data : {
						taskid:$("#taskid").val(),
					},
					success:function(data){
						
					}
				});
			});
			$("#setVariables").click(function(e){
				$.ajax({
					type : "post",
					url : "vacation/setVariables.json",
					data : {
						taskid:$("#taskid").val(),
					},
					success:function(data){
						
					}
				});
			});
		});
	</script>
  </head>
  
  <body>
    <input type="button" id="test" value="测试"><br/>
    <input type="button" id="createTask" value="创建任务"><br/>
    <input type="text" id="taskid"><input type="button" id="endtask" value="结束任务"><input type="button" id="setVariables" value="加参数">
  </body>
</html>
