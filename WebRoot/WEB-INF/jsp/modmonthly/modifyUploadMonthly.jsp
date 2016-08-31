<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改上传月报</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	
	  <script type="text/javascript" charset="utf-8" src="<%=path %>/js/modmonthly/modifyMonthly.js"></script>
	  


  </head>
  
   <body class="mainbody">
    <div id="mainbgcon" class="mainbgcon">
	   <div class="mainbg_ltop"></div>
	   <div id="mainbg_midtop" class="mainbg_midtop">
	     <div class="maintoptit">
	      <p>月报->修改上传月报</p>
	     </div>
	   </div>
	   
	   <div class="mainbg_rtop"></div>
		<div id="mianbg_mid" class="mianbg_mid">
	     <div id="main_midntitbg" class="main_midntitbg">
	      <div class="main_midntitwz">
	     	<p>修改上传月报</p>
		</div>
		</div>
		<div id="main_tablelb" class="main_tablelb">
		  <input type="hidden" name="monthsumid" id="monthsumid" value="${mon.monthsumid }" />
		  <input type="hidden" name="monthscope" id="monthscope" value="${mon.monthscope }">
			<table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="800" >
				<tbody>
					<tr>
					
						<td class="listtabletr3"><label>月份：</label></td> <td align="center"><select name="month" id="month" > <%-- <option value="0">${mon.monthscope }</option> --%>    </select></td> 
						<td class="listtabletr3"><label>标题：</label></td><td class="listtabletr1"><input type="text" id="title" size="75" value="${mon.monthtitle }" ></td>
					</tr>
				</tbody>
			</table>
			<br>
	       
			<input class="butzcon" type="button" value="保存" onclick="savemodifyUploadMonthly();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="butzcon" type="button" value="返回" onclick="javascript:window.history.go(-1);"/>
		</div>
		</div>
	</div>
  </body>
</html>
