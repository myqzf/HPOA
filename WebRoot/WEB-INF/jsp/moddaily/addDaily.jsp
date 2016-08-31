<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加日志</title>
	
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- 富文本编辑器 -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.all.min.js"> </script>
    
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- adddaily.js -->
	<script type="text/javascript" src="<%=path%>/js/moddaily/addDaily.js"></script>
	
	</head>
  
  <body class="mainbody">
			<div id="main_tablelb" class="main_tablelb">
	  		<div>
		  	<table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="900">
		  	<tr>
		  	  <td class="listtabletr5">
		  	  <label>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期：</label>
				<input id="daydate" type="text" disabled value="${dailyInfo.daydate}"/>			  	
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</label>
			  	<input class="tabInput" id="title" type="text" maxLength="50" value='${dailyInfo.title}'/>
		
		  		</td>
		  	</tr>
		  	<tr>
		  		<td>
		  		<div id="planY" style="border:solid 1px #c3c3c3;text-align:center;width:900px;cursor:pointer;">昨&nbsp;日&nbsp;计&nbsp;划︽</div>
			  	<div style="margin-top:5px;display:inline;" id="yesterday">
					<div style="width:900px;height:350px;border: solid 1px #c3c3c3;overflow:auto;">${planY }</div>
				</div>
				今日工作总结：
				<div style="margin-top:5px;">
				 	 <textArea id="content" type="text/plain" style="width:900px;height:300px;">${dailyInfo.content }</textArea>
				</div>			   	
				   	<script type="text/javascript">
						var editorC = UE.getEditor("content");		
					</script>
				<label id="planL">明&nbsp;日&nbsp计&nbsp;划：</label>
		  			<div style="margin-top:5px;">
			 	 		<textArea id="planT" type="text/plain" style="width:900px;height:200px;">${dailyInfo.plan }</textArea>
					</div>	
						<script type="text/javascript">
						var editorP = UE.getEditor("planT");		
					</script>
				</td>
		  	</tr>		  	
		  	</table>		
		  	<br>  
		  	<input type="hidden" id="yesterdayPlan" value="${planY }"/>
		  	&nbsp;&nbsp;<input type="button" value="提交" class="tsbtn2" id="addDaily"/>
		  	&nbsp;&nbsp;<input type="button" value="返回" class="tsbtn2" id="rtn"/>
		  	<br/>
		  	<br/>
		  	
	  	</div>
		</div>
	</div>
	</div>
  </body>
</html>
