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
	<link rel="stylesheet" href="<%=path%>/style/modforum/css/forum.css" type="text/css"></link>
 	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/jquery-1.10.2.js"> </script>
  </head>
  
  <body>
  	<div class="ForumPageTableBorder" style="margin-top: 25px;">
  	
  	<table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
  
  		<!--板块列标题-->
  		<tr align="center" valign="middle">
			<td colspan="3" class="ForumPageTableTitleLeft">版块</td>
			<td width="80" class="ForumPageTableTitle">点击数</td>
			<td width="80" class="ForumPageTableTitle">发帖数</td>
			<td width="270" class="ForumPageTableTitle">最后发表的主题</td>			
		</tr> 			
  			
  
  
  		<!--板块列表-->
    	<c:if test="${!empty forumList }">
    	
    	<tr height="1" class="ForumPageTableTitleLine"><td colspan="9"></td></tr>
		<tr height="3"><td colspan="9"></td></tr>	
		
		<tbody class="dataContainer" >
    	
			<c:forEach items="${forumList}" var="list">
				<tr height="78" align="center" class="template">
				 <td width="3"></td>
				 <td width="75" class="ForumPageTableDataLine">
					<img src="<%=path%>/style/modforum/img/forumpage3.gif"></img> 
				 </td>
				
				<!-- 板块名称 -->
				<td class="ForumPageTableDataLine">
					<ul class="ForumPageTopicUl">
						<li class="ForumPageTopic"><a    href="/HPOA/forum/getCard?sessionId=${list.sessionId}&currentPage=1">${list.sessionName}</a></li>
						<li class="ForumPageTopicMemo">${list.sessionDesc}</li>
					</ul>
				</td>
					
					
				<!-- 判断点击数是否为空 -->
				<c:if test="${empty list.clickCount}">
				<td class="ForumPageTableDataLine">
					0
				</td>
				</c:if>
				<c:if test="${!empty list.clickCount}">
					<td class="ForumPageTableDataLine">
						${list.clickCount}
					</td>
				</c:if>
				<!-- 判断点击数是否为空结束 -->
				
				
				<!-- 判断发帖数是否为空 -->
				<c:if test="${empty list.topCount}">
				<td class="ForumPageTableDataLine">
					0
				</td>
				</c:if>				
				<c:if test="${!empty list.topCount}">
					<td class="ForumPageTableDataLine">
						${list.topCount}
					</td>
				</c:if>
				<!-- 判断发帖数是否为空结束 -->
				
				
				
				
				<!-- 最后发布的帖子 -->
				<td class="ForumPageTableDataLine">
					<ul class="ForumPageTopicUl">
						<li><font color="#444444">┌ 主题：</font> 
							<a  href="/HPOA/forum/getResponseList?cid=${list.cId}&sessionId=${list.sessionId}">${list.cTitle}</a>
						</li>
						<li><font color="#444444">├ 作者：</font> ${list.nickName}</li>
						<li><font color="#444444">└ 时间：</font> ${list.cTime}</li>
					</ul>
				</td>
				<td width="3"></td>
				</tr>
			</c:forEach>
			</tbody>
		</c:if>
	</table>
	</div>	
		
  </body>
</html>
