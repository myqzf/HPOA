<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>撰写短消息</title>
    <meta http-equiv="x-ua-compatible" content="ie=8" />
	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=8">
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
	<!-- writeMessage.js -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modmessage/writeMessage.js"></script>
  </head>
  
  <body class="mainbody">
    <div id="mainbgcon" class="mainbgcon">
	   <div class="mainbg_ltop"></div>
	   <div id="mainbg_midtop" class="mainbg_midtop">
	     <div class="maintoptit">
	      <p>站内消息->短信箱 </p>
	     </div>
	   </div>
	   <div class="mainbg_rtop"></div>
		<div id="mianbg_mid" class="mianbg_mid">
	     <div id="main_midntitbg" class="main_midntitbg">
	      <div class="main_midntitwz">
	     	<p>撰写信息</p>
		</div>
		</div>
		<div id="main_tablelb" class="main_tablelb">
			<input type="hidden" id="staffsid" value="${noSendMessageDetail.receversid}"/>
			<input type="hidden" id="messageid" value="${noSendMessageDetail.messageid }"/>
			<input type="hidden" id="createDate" value="${noSendMessageDetail.createTime}"/>
			<table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="800" >
				<tbody>
					<tr>
						<td class="listtabletr3"><label>收件人</label></td><td class="listtabletr1"><table id="recevertab">${noSendMessageDetail.receversName}</table><%-- <label id="receversname">${noSendMessageDetail.receversName}</label>--%>&nbsp;<a href="javascript:void(0);" id="choice">选择</a>,<a href="javascript:void(0);" id="clear">清空</a></td>
					</tr>
					<tr>
						<td class="listtabletr3"><label>主&nbsp;&nbsp;&nbsp;&nbsp;题</label></td><td class="listtabletr1"><input type="text" id="title" size="75" value="${noSendMessageDetail.title }"></td>
					</tr>
				</tbody>
			</table>
			<div style="margin-top:5px;">
				<textArea id="editor" type="text/plain" style="width:800px;height:375px;">${noSendMessageDetail.content }</textArea>
			</div>
			<script type="text/javascript">
				var editor = UE.getEditor("editor");
			</script>
			<input type="button" id="save" class="tsbtn2" value="保存"/><input type="button" id="send" class="tsbtn2" value="发送"/><input id="retu" type="button" class="tsbtn2" value="返回"/>
		</div>
		</div>
	</div>
  </body>
</html>
