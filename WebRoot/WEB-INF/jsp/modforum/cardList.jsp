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
    
    <title>【${sessionName}】板块中的主题列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=path%>/style/modforum/css/forum.css" type="text/css"></link>
	 <!--jquery -->
	<script type="text/javascript" src="<%=path %>/commonjs/jquery-1.10.2.js"></script>
    <script type="text/javascript">
  		$(document).ready(function() {	   	    		
	   		//去掉a标签下划线
// 	   		for(i=0;i<=$("a").length;i++){	   		
// 	   			$("a:eq("+i+")").css("text-decoration", "none");	   		
// 	   		}   		
	   
	   		//当前页按钮不可以再点
	   		//当前页小于3时
			if($("#currentPage").val()<3){
			$(".index:eq("+(($("#currentPage").val())-1)+")").attr("href","javascript:void(0);");	
	   		$(".index:eq("+(($("#currentPage").val())-1)+")").css("color", "#666666");	
			}
			//当前页比总页数小1时
			else if($("#currentPage").val()==($("#totalPage").val()-1)){			
			$(".index:eq(3)").attr("href","javascript:void(0);");	
	   		$(".index:eq(3)").css("color", "#666666");			
			}
			//当前页等于总页数时
			else if($("#currentPage").val()==$("#totalPage").val()){			
			$(".index:eq(4)").attr("href","javascript:void(0);");	
	   		$(".index:eq(4)").css("color", "#666666");			
			}
			//其他的中间页不可点击
			else{
	   		$(".index:eq(2)").attr("href","javascript:void(0);");	
	   		$(".index:eq(2)").css("color", "#666666");   
			}
			
	    		//当前页为第一页时上一页按钮不可以再点
	    	if($("#currentPage").val()==1){
	    		$(".previous").attr("href","javascript:void(0);");
	    		$(".previous").css("color", "#666666");		    		
	    	}
	    		//当前页为最后一页时下一页按钮不可以再点
	    	if($("#currentPage").val()==$("#totalPage").val()){
	    		$(".next").attr("href","javascript:void(0);");
	    		$(".next").css("color", "#666666");		    		
	    	}    		
  		});  	
  	</script>

  </head>
  
  <body>
  
  	<!-- 标题显示 -->
	<div id="Title_bar">
	    <div id="Title_bar_Head">
	        <div id="Title_Head"></div>
	        <div id="Title"><!--页面标题-->
	            <img border="0" width="13" height="13" src="<%=path %>/style/modforum/img/title_arrow.gif"/> 【${sessionName}】板块中的主题列表
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
  

<!--     <a href="<%=path%>/forum/toRegister?sessionId=${sessionId}">注册论坛账号</a> -->
  <br>
  <br> 	
	
		<div class="ItemBlock_Title1" style="width: 98%;">
			<font class="MenuPoint"> &gt; </font>
			<a href="<%=path%>/forum/getForum">论坛</a>
			<font class="MenuPoint"> &gt; </font>
			<b>${sessionName}</b>
			<!-- 新增帖子 -->
			<span style="margin-left:30px;">
				<a href="<%=path%>/forum/toSaveCard?sessionId=${sessionId}">
					<img  src="<%=path %>/style/modforum/img/publishNewTopic.png"/>
				</a>
			</span>
		</div>
	
	<div class="ForumPageTableBorder">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0">

    	<c:if test="${!empty cardList }">
			<!--标题-->
		   <tr align="center" valign="middle">  			
			<td width="3" class="ForumPageTableTitleLeft">
				<img border="0" width="1" height="1" src="<%=path %>/style/modforum/img/blank.gif" />
			</td>
  			<td width="50" class="ForumPageTableTitle">状态/图标</td>  			
  			<td class="ForumPageTableTitle">标题</td>
			<td width="200" class="ForumPageTableTitle">作者</td>
  			<td width="100" class="ForumPageTableTitle">点击次数</td>
  		   </tr>			
			<tr height="1" class="ForumPageTableTitleLine"><td colspan="8"></td></tr>
			<tr height=3><td colspan=8></td></tr>
			
				

			
			
			
			
			<!--主题列表-->
			<tbody class="dataContainer"  >
			<!-- 显示帖子列表 -->
			<c:forEach items="${cardList}" var="list">
				<tr height="35" id="d0" class="template">
				<td></td>
				<!-- 普通帖子图片状态 -->
				<c:if test="${list.cistop==0}">
				<td class="ForumTopicPageDataLine" align="center"><img src="<%=path%>/style/modforum/img/topicType_0.gif" /></td>
				</c:if>
				<!-- 置顶帖子图片状态 -->
				<c:if test="${list.cistop==1}">
				<td class="ForumTopicPageDataLine" align="center"><img src="<%=path%>/style/modforum/img/topicType_2.gif" /></td>
				</c:if>
				
				
				<!--标题 -->
				<td class="Topic">		
					<a href="/HPOA/forum/getResponseList?cid=${list.cid}&sessionId=${sessionId}&currentPage=1">${list.ctitle}
						<c:if test="${!empty list.cisnew}">
							<img src="<%=path%>/style/modforum/img/new.gif"></img>
						</c:if>						
					</a>	
				</td>

				<!--作者-->
				<td class="ForumTopicPageDataLine">
					<ul class="ForumPageTopicUl">
						<li class="Author">${list.csid}</li>
						<c:if test="${empty list.bbsLevel}">
						<li class="CreateTime">你是旧数据，还没有等级，请添加帖子刷新数据</li>
						</c:if>
						<c:if test="${!empty list.bbsLevel}">
						<li class="CreateTime">${list.bbsLevel}</li>
						</c:if>
						<li class="CreateTime">${list.ctime}</li>
					</ul>
				</td>

				<!--点击次数-->
				<td class="ForumTopicPageDataLine Reply" align="center"><b>${list.cclickcount}</b></td>

				</tr>
				
				<tr height="3"><td colspan="9"></td></tr>
				
			</c:forEach>
			<!-- 显示帖子列表结束 -->			
		</c:if>
	</table>	
			<!-- 还没有人发布帖子 -->			
			<c:if test="${empty cardList }">
			
			<b>还没有人发布帖子</b>
			
			</c:if>	
	
	</div>

		
		<!-- 分页 -->		
        <input type="hidden" id="totalPage" value="${totalPage}">
        <input type="hidden" id="currentPage" value="${currentPage}">
        <input type="hidden" id="beginPageIndex" value="${beginPageIndex}">
        <input type="hidden" id="endPageIndex" value="${endPageIndex}">
       
        <div> 
         	<a class="previous" href="/HPOA/forum/getCard?sessionId=${sessionId}&currentPage=1" >&laquo; 首页</a>
         	<a class="previous" href="/HPOA/forum/getCard?sessionId=${sessionId}&currentPage=${currentPage==1?1:currentPage-1}">&laquo; 上一页</a> 
         	<c:forEach  begin="${beginPageIndex}" end="${endPageIndex}"  varStatus="id" step="1" var="index">
<!--           	<a href="/HPOA/forum/getCard?sessionId=${sessionId}&currentPage=${id.index}">${id.index}</a> 		                 -->
         		<a class="index" href="/HPOA/forum/getCard?sessionId=${sessionId}&currentPage=${index}">${index}</a>
         	</c:forEach>
         	<a class="next" href="/HPOA/forum/getCard?sessionId=${sessionId}&currentPage=${currentPage==totalPage?currentPage:currentPage+1}">下一页 &raquo;</a>
         	<a class="next" href="/HPOA/forum/getCard?sessionId=${sessionId}&currentPage=${totalPage}">尾页 &raquo;</a> 
        </div>
	
  </body>
</html>
