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

		<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- 富文本编辑器 -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.all.min.js"> </script>
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



	<script type="text/javascript">
	$(document).ready(function(e){
	
	$("#reply").click(function(e){
		//判断内容非空
		if(editorC.getContent()==''){
			art.dialog.alert('内容不能为空');
			return false;
		}

		$.post(
			"/HPOA/forum/reply",
			{	
				rcid:$("#rcid").val(),
				rtitle:$("#rtitle").val(),
				rcontent:editorC.getContent()
								
			},
			function(data){				
				if(data=="1"){
				window.location.href="/HPOA/forum/getResponseList?cid="+$("#cid").val()+"&sessionId="+$("#sessionId").val()+"&currentPage=1";
		//			location.reload();     
				}
				             				
			});
		});		
});
	</script>


  </head>
  
  <body>
  <input type="hidden" id="sessionId" value="${sessionId}">
  <input type="hidden" id="cid" value="${cid}">
  
  
  <!-- 标题显示 -->
	<div id="Title_bar">
	    <div id="Title_bar_Head">
	        <div id="Title_Head"></div>
	        <div id="Title"><!--页面标题-->
	            <img border="0" width="13" height="13" src="<%=path %>/style/modforum/img/title_arrow.gif"/> 查看主题
	        </div>
	        <div id="Title_End"></div>
	    </div>
	</div>
  
  <!--内容显示-->	
   <div id="MainArea">
   <div id="PageHead"></div>  
   
   		<div class="ItemBlock_Title1" style="width: 98%">
			<!-- 路径显示 -->
			<font class="MenuPoint"> &gt; </font>
			<a href="<%=path%>/forum/getForum">论坛</a>
			<font class="MenuPoint"> &gt; </font>
			<a href="<%=path%>/forum/getCard?sessionId=${sessionId}&currentPage=1">${sessionName}</a>
			<font class="MenuPoint"> &gt;&gt; </font>
			<b>帖子阅读</b>
			<!-- 新增帖子 -->
			<span style="margin-left:30px;">
				<a href="<%=path%>/forum/toSaveCard?sessionId=${sessionId}">
					<img  src="<%=path %>/style/modforum/img/publishNewTopic.png"/>
				</a>
			</span>
		</div>
   
	   <br>
	   <br>
   
   
   	<div class="ForumPageTableBorder dataContainer" >
   
   
   			<!--显示主题标题等-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="bottom">
				<td width="3" class="ForumPageTableTitleLeft">&nbsp;</td>
					<td class="ForumPageTableTitle"><b>本帖主题：${ctitle}</b></td>
					<!-- 如果是版主则显示置顶按钮 -->
					<c:if test="${isMaster==1}">
					<td class="ForumPageTableTitle" align="right" style="padding-right:12px;">
<!-- 						<a  href="#"> -->
<!-- 							<img border="0" src="<%=path %>/style/modforum/img/reply.gif" /> -->
<!-- 							回复 -->
<!-- 						</a> -->
<!-- 						<a href="#"><img border="0" src="<%=path %>/style/modforum/img/edit.gif" />移动到其他版块</a> -->
<!-- 						<a href="#" onClick="return confirm('要把本主题设为精华吗？')"><img border="0" src="<%=path %>/style/modforum/img/topicType_1.gif" />精华</a> -->
						<a href="<%=path%>/forum/toTop?cid=${cid}&sessionId=${sessionId}" onClick="return confirm('要把本主题设为置顶吗？')"><img border="0" src="<%=path %>/style/modforum/img/topicType_2.gif" />置顶</a>
<!-- 						<a href="#" onClick="return confirm('要把本主题设为普通吗？')"><img border="0" src="<%=path %>/style/modforum/img/topicType_0.gif" />普通</a> -->
					</td>
					</c:if>
					<!-- 如果是版主则显示置顶按钮结束 -->
					
					<td width="3" class="ForumPageTableTitleRight">&nbsp;</td>
				</tr>
				<tr height="1" class="ForumPageTableTitleLine"><td colspan="4"></td></tr>
			</table>
   
   
   
   
	<!-- 回复内容 -->
	<table border="0" cellpadding="0" cellspacing="1" width="100%">
    	<c:if test="${!empty responseList }">    	
    	
			<c:forEach items="${responseList}" var="list">				
				<tr>

				<td>
			<!-- 作者头像 -->
			    <c:if test="${!empty list.psUrl }">
					<div>
						<img border="0"  height="145" width=100 src="staffpicture/${list.psUrl }" /> 
							
					</div>
				</c:if>
				<c:if test="${empty list.psUrl }">
					<div>
						<img border="0" height="145" width=100 src="<%=path %>/style/modforum/img/defaultAvatar.gif" /> 
							
					</div>
				</c:if>
				
				
				<!--作者名称 -->
					<div class="AuthorName">${list.ruid}</div>
				</td>
				
				<!--回复内容 -->
				<td width="100%">
					<div>${list.rtitle}</div>
					<div style="background-color:#91c0e3;height:1;width:100%; "></div>
					<div>${list.rcontent}</div>
				</td>
									
				</tr>
				<tr>
					<td colspan="2">
						<div style="background-color:#91c0e3;height:28;width:100%; ">[xx楼]&nbsp;&nbsp;&nbsp;${list.rtime}</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div style="background-color:#91c0e3;height:1;width:100%; "></div>
					</td>
				</tr>				
				
			</c:forEach>
		</c:if>
	</table>
	<!-- 回复内容结束 -->

	
	</div>

		<c:if test="${empty responseList }">
			<b>还没有人回复此贴</b>
		</c:if>
		
		<!--分页 -->	
        <input type="hidden" id="totalPage" value="${totalPage}">
        <input type="hidden" id="currentPage" value="${currentPage}">
        <input type="hidden" id="beginPageIndex" value="${beginPageIndex}">
        <input type="hidden" id="endPageIndex" value="${endPageIndex}">        
              
        <div> 
               	<a class="previous" href="/HPOA/forum/getResponseList?cid=${cid}&sessionId=${sessionId}&currentPage=1" >&laquo; 首页</a>
               	<a class="previous" href="/HPOA/forum/getResponseList?cid=${cid}&sessionId=${sessionId}&currentPage=${currentPage==1?1:currentPage-1}">&laquo; 上一页</a> 
               	<c:forEach  begin="1" end="${totalPage}"  varStatus="id">
                	<a class="index" href="/HPOA/forum/getResponseList?cid=${cid}&sessionId=${sessionId}&currentPage=${id.index}">${id.index}</a> 		                
               	</c:forEach>
               	<a class="next" href="/HPOA/forum/getResponseList?cid=${cid}&sessionId=${sessionId}&currentPage=${currentPage==totalPage?currentPage:currentPage+1}">下一页 &raquo;</a>
               	<a class="next" href="/HPOA/forum/getResponseList?cid=${cid}&sessionId=${sessionId}&currentPage=${totalPage}">尾页 &raquo;</a> 
        </div>		
		<br>
		<br>
		
		
		
<!-- 		快速回复 -->
	<div class="Deep" style="width:100px;color:#004a7d"><b>快速回复 </b></div>
		<form>
		
		<input type="hidden" id="rcid" name="rcid" value="${cid}">

		<div style="width:50px"  class="Deep"><b style="color:#004a7d">标题</b></div>
		<input type="text" id="rtitle" name="rtitle" >
		<br>
		
		<div style="width:50px"  class="Deep"><b style="color:#004a7d">内容</b></div>
		
		<div style="margin-top:5px;">
	 	   <textArea id="content"  style="width:800px;height:300px;"></textArea>
	    </div>			   	
	   	<script type="text/javascript">
			var editorC = UE.getEditor("content");		
		</script>
		
		
		<input type="button" id="reply" value="回复">
		</form>
	</div>
	
	
  </body>
</html>
