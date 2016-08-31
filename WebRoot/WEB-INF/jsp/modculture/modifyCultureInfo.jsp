<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改企业文化信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 富文本编辑器 -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/ueditor/ueditor.all.min.js"> </script>
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	
    <script type="text/javascript" charset="utf-8" src="<%=path %>/js/modculture/modifyCultureInfo.js"></script>
	  


  </head>
  
   <body class="mainbody">
    <div id="mainbgcon" class="mainbgcon">
	   <div class="mainbg_ltop"></div>
	   <div id="mainbg_midtop" class="mainbg_midtop">
	     <div class="maintoptit">
	      <p>企业文化->修改企业文化信息</p>
	     </div>
	   </div>
	   
	   <div class="mainbg_rtop"></div>
		<div id="mianbg_mid" class="mianbg_mid">
	     <div id="main_midntitbg" class="main_midntitbg">
	      <div class="main_midntitwz">
	     	<p>修改企业文化信息</p>
		</div>
		</div>
		<div id="main_tablelb" class="main_tablelb">
		  <input type="hidden" name="cultureid" id="cultureid" value="${cul.cultureid }" />
		  <input type="hidden" name="staffComp" id="staffComp" value="${cul.staffComp }">
			<table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="800" >
				<tbody>
					<tr>
						<td class="listtabletr3"><label>标题：</label></td><td class="listtabletr1"><input type="text" id="culturetitle" size="75" value="${cul.culturetitle }" ></td>
					</tr>
				</tbody>
			</table>
			
	       
			<div style="margin-top:5px;">
				<textArea id="editor" type="text/plain" style="width:800px;height:375px;">${cul.culturecontent }</textArea>
			</div>
			<script type="text/javascript">
				var editor = UE.getEditor("editor");
			</script>
			<input class="butzcon" type="button" value="保存" onclick="saveCultureInfo();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="butzcon" type="button" value="返回" onclick="javascript:window.history.go(-1);"/>
		</div>
		</div>
	</div>
  </body>
</html>
