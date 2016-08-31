<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  
      <title>添加主权限</title>
    <meta http-equiv="x-ua-compatible" content="ie=8" />
	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=8">
  
  
	<!-- jquery -->
    <script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
    <!-- artDialog -->
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script> 
	<!-- addMainFunc -->
	<script type="text/javascript" src="<%=path%>/js/modsystem/addMainFunc.js"></script>  </head>
  
  <body>
  <form>
  <input type="hidden" name="funcPid" id="funcPid" value="${funcPid }" />

	
	  <p>新增主要权限</p>		 
    <table  border="0" width="600px;" style="margin-left:10px;margin-top:10px;"  cellspacing="1" cellpadding="1" align="center">
       <div id="messageDiv"><span id="message"></span></div>
       <tr>
         <td>功能名称：</td>
         <td><input type="text" name="funcName" id="funcName"/>
      </td>
         <td>功能描述：</td>
         <td><input type="text" name="funcDesc" id="funcDesc"/></td>
       </tr>
        <tr>
         <td>访问地址：</td>
         <td><input type="text" name="funcUrl" id="funcUrl"/></td>
       </tr>
       <tr>
         <td >是否可用：</td>
         <td colspan="3"><input type="radio" checked="checked" name="isUsed" value="1">是</input>&nbsp;&nbsp;<input type="radio"  name="isUsed"  value="2">否</input></td>
       </tr>
       <tr>
         <td colspan="4" style="height:40px; line-height:40px; padding-top:10px;"><input type="button" value="提交" onclick="addFunc();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" onclick="javascript:window.history.go(-1);"/></td>
       </tr>
    </table>

   </form>
  </body>
</html>
