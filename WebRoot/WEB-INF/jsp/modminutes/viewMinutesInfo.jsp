<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>会议纪要</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- 富文本编辑器 -->
<script type="text/javascript" charset="utf-8" src="<%=path%>/commonjs/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/commonjs/ueditor/ueditor.all.min.js"> </script>
<!-- jquery -->
<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
<!-- artDialog -->
<script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
<!-- easyui -->
<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/easyui/demo/demo.css">
<script type="text/javascript" src="<%=path%>/commonjs/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/commonjs/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modminutes/viewMinutesInfo.js"></script>


<style type="text/css">
select {
	width: 200px;
	borde: none;
}

.rows {
	padding-top: 5px;
	width: 650px;
	border-bottom-color: #f0a0a0;
	border-bottom-width: 1px;
	border-bottom-style: solid;
}

#photodiv {
	height: 105px;;
}

.littlelbl {
	padding-right: 32px;
}
 input{
 border-style: solid; 
 border-width: 0;
 }
</style>
</head>

<body class="mainbody">
	<div id="mainbgcon" class="mainbgcon">
		<div class="mainbg_ltop"></div>
		<div id="mainbg_midtop" class="mainbg_midtop">
			<div class="maintoptit">
				<p>会议纪要-&gt;查看会议纪要</p>
			</div>
		</div>
		<div class="mainbg_rtop"></div>
		<div id="mianbg_mid" class="mianbg_mid">
			<div id="main_midntitbg" class="main_midntitbg">
				<div class="main_midntitwz">
					<p>查看会议纪要</p>
				</div>
			</div>
			<div id="main_tablelb" class="main_tablelb">
				<div>
					<form id="minutes_form" action="minutes/addMinutesInfo" method="post" enctype="multipart/form-data" onsubmit="return checkMinutes()">
						<!---->
						<table border="0" cellpadding="5" cellspacing="5" class="tableborder" width="1000" height="354">
							<tr>
								<td>
								  <input type="hidden" name="minuteslinkaaa" id="minuteslinkaaa" value="${mut.minuteslink }">
								</td>
							</tr>
							<tr>
								<td class="listtabletr5" colspan=2><label>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期：</label>
								<input name="meetingdate" id="meetingdate" value="${mut.meetingdate }" readonly="readonly"></input></td>
							</tr>
							<tr>
								<td class="listtabletr5" colspan=2><label>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点：</label>
								<input  type="text" name="meetingplace" id="meetingplace" style="width: 900px;"  value="${mut.meetingplace }"></input></td>
							</tr>
							<tr>
								<td class="listtabletr5" colspan=2><label>主&nbsp;&nbsp;持&nbsp;人：</label>
								<input type="text" name="meetinghost" id="meetinghost"  value="${mut.meetinghost }"></input></td>
							</tr>
							<tr>
								<td class="listtabletr5" colspan=2><label>参会人员：</label>
								<input  name="attendees" id="attendees" data-options="multiline:true" style="width: 910px;"  value="${mut.attendees }"></input></td>
							</tr>
							<tr>
								<td class="listtabletr5" ><label>会议主题：</label>
								<input name="meetingtheme" id="meetingtheme" data-options="multiline:true"  value="${mut.meetingtheme }"></input></td>
							    
<!-- 							    <td class="listtabletr5" ><label>请选择<font	color="#ff0000">扩展名为doc或docx,不大于2M</font>的文件</label><br /> <label>上传附件</label> -->
<!-- 								<input type="file" id="minutesFile" name="minutesFile" accept="application/msword" /><br /> -->
								
							</tr>
						 	
							<tr>
								<td class="listtabletr5" colspan=2><label>内容：</label>
								 <textArea id="meetingcontent" name="meetingcontent" type="text/plain" style="width:880px;height:200px;" enable="false" >${mut.meetingcontent }</textArea>
									<script type="text/javascript">
									//	var editor = UE.getEditor("meetingcontent");
									var editor = new baidu.editor.ui.Editor({ toolbars:[[]],
									wordCount:false//禁用字数统计
									 }); editor.render("meetingcontent");
									</script>
								</td>
							</tr>
							<tr>
								<td class="listtabletr5" colspan=2><label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
								<input name="remark" id="remark" data-options="multiline:true" value="${mut.remark }"></input></td>
							</tr>
							<tr>
							     <td class="listtabletr5" colspan=2>
								  <div id="uploadFile">附件：
								  <div id="uploadFile_div">
								 <div class="tsxxnr"><input class="tabInput" name="attachmentName" id="attachmentName" type="text" maxLength="160"  disabled="true" value=""/>
			                       <a href='#' style="text-decoration: none;" onclick="checkAttachment('${mut.minuteslink}'); return false;">点击下载</a>
			                         <br/></div></div> </div> 
								
								</td>
							</tr>
						</table>

						<input class="butzcon" type="button" value="返回" onclick="javascript:window.history.go(-1);" />
					</form>
					<br /> <br />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
