<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- css样式 -->
	<%-- <link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" /> --%>  	
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<script type="text/javascript">
		$(document).ready(function(e){
			$("#retusend").click(function(e){
				if(window.attachEvent) {
					location.href='goMessage?flag=sendmsg';
				}else{
					location.href='message/goMessage?flag=sendmsg';
				}
			});
		});
	</script>
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
	     	<p>短信详细信息</p>
		</div>
		</div>
		<div id="main_tablelb" class="main_tablelb">
			<table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="834">
				<tbody>
					<tr>
						<td class="listtabletr3"><label>收件人</label></td><td class="listtabletr1"><table>${sendMessageDetail.recevername}</table></td>
					</tr>
					<tr>
						<td class="listtabletr3"><label>主&nbsp;&nbsp;&nbsp;&nbsp;题</label></td><td class="listtabletr1"><label>${sendMessageDetail.title}</label></td>
					</tr>
					<tr>
						<td class="listtabletr3"><label>日&nbsp;&nbsp;&nbsp;&nbsp;期</label></td><td class="listtabletr1"><label>${sendMessageDetail.sendtime}</label></td>
					</tr>
				</tbody>
			</table>
			<div style="width:800px;min-height:280px;padding:16px;border-style:solid;border-width:1px;border-color:#e8a999;overflow-y:auto;">
				${sendMessageDetail.content}
			</div>
			<input type="button" id="retusend" value="返回" class="tsbtn2" />		
		</div>
		</div>
	</div>
  </body>
</html>
