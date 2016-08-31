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

<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">

</head>

<body>
	<div class="container">
    	<div class="top">
    		<span>会议纪要</span>
    	</div>
<!--         <div class="list-search"> -->
<!--         	 <div class="search"> -->
<!--              	搜索：<input type="text" value="" /> -->
<!--              </div> -->
<!--         	<div class="Spring"> -->
<!--             	汇报人：<select name="select"> -->
<!--                 	<option value="">王经理</option> -->
<!--                     <option value="">苏经理</option> -->
<!--                     <option value="">杨经理</option> -->
<!--                     <option value="">李经理</option> -->
<!--                 </select>	 -->
<!--             </div> -->
<!--             <div class="Start"> -->
<!--             	起始时间：<input type="text" value="" /> -->
<!--             </div> -->
<!--             <div class="end"> -->
<!--             	结束时间：<input type="text" value="" /> -->
<!--             </div> -->
<!--             <div class="refer"> -->
<!--             	<a href="">查询</a> -->
<!--             </div> -->
            
<!--         </div> -->
       
      <input type="hidden" name="minuteslinkaaa" id="minuteslinkaaa" value="${mut.minuteslink }">
<table class="meeting--" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" style="border-collapse:collapse;">
	
	<tr>
    	<td>会议主题</td>
        <td colspan="3">${mut.meetingtheme }</td>
       
    </tr>
    <tr>
    	<td>会议时间</td>
        <td>${mut.meetingdate }</td>
        <td>地点</td>
        <td>${mut.meetingplace }</td>
    </tr>
    <tr>
    	<td>会议主持</td>
        <td colspan="3">${mut.meetinghost }</td>
       
    </tr>
    <tr>
    	<td>出席人员</td>
        <td colspan="3">${mut.attendees }</td>
        
    </tr>
    <tr>
    	<td>缺席人员</td>
        <td colspan="3"></td>
       
    </tr>
    <tr style="min-height:500px;height:auto;">
    	<td colspan="4" class="jiyao">会议内容<br /><hr style="width:1200px;margin:20px auto;"/>
    	 <textArea id="meetingcontent" name="meetingcontent" type="text/plain" style="width:880px;height:200px;margin: auto;" enable="false" >${mut.meetingcontent }</textArea>
					<script type="text/javascript">
					//	var editor = UE.getEditor("meetingcontent");
					var editor = new baidu.editor.ui.Editor({ toolbars:[[]], 
									elementPathEnabled : false,
									wordCount:false//禁用字数统计
									}
					
					); editor.render("meetingcontent");
					</script>
        </td>
    </tr>
	<tr>
	      <td>附件</td>
	     <td colspan=3>
	   
		  <div id="uploadFile">
		  <div id="uploadFile_div">
		 <div class="tsxxnr"><input class="tabInput" name="attachmentName" id="attachmentName" type="text" maxLength="160"  disabled="true" value=""/>
                    <a href='#' style="text-decoration: none;" onclick="checkAttachment('${mut.minuteslink}'); return false;">点击下载</a>
                      <br/></div></div> </div> 
		
		</td>
	</tr>
</table> 

</div>
</body>
</html>
