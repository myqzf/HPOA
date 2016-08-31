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
    
    <title>My JSP 'forum.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- jquery -->
	<script type="text/javascript" src="<%=path %>/commonjs/jquery-1.10.2.js"></script>
  	<script type="text/javascript">
	function delecte(obj){		
	r=confirm("您确定要删除吗？");

		if(r==true){
			//点过取消后href属性就成为javascript:void(0)，所以重新给href赋值
	  		obj.href=obj.id;
	 		return true;
           }else{         
           	obj.href="javascript:void(0)";
          }
	}
 	</script>

  </head>
  
  <body>
  	
  	<a href="/HPOA/forum/toSaveForum">新增板块</a>
  	
  	<table style="margin-top: 25px" border="1">
  
  		<tr align="center" valign="middle">
			<td>版块</td>			
			<td>版块描述</td>
			<td>操作</td>
		</tr>
  			
  			
  
    	<c:if test="${!empty forumList }">
			<c:forEach items="${forumList}" var="list">
				<tr>
				<td>${list.sessionName}</td>	
				
				<td>
					${list.sessionName}
				</td>
				<td>
					<a href="<%=path%>/forum/toupdateForum?sessionId=${list.sessionId}">修改</a>
					<a onclick="delecte(this)" id="<%=path%>/forum/delecteForum?sessionId=${list.sessionId}" href="<%=path%>/forum/delecteForum?sessionId=${list.sessionId}">删除</a>
					
				</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
		
		
  </body>
</html>
