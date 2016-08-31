<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/jquery-1.10.2.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/zsy.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/js/moddictionary/oneDictItems.js"></script>
    <title>增加字典项</title>

  </head>
  
  <body class="mainbody">
 
	<div id="mianbg_mid" class="mianbg_mid">
	     <div id="main_midntitbg" class="main_midntitbg">
	      <div class="ma/in_midntitwz">
	     	<p>添加一级字典项</p>
		  </div>
		 </div>
	  <div class="main_tablelb" id="main_tablelb"> 
	   <div id="msgDiv"></div>
	    <input type="hidden" name="headId" id="headId" value="${headId }" />
	    <table  border="0" width="600px;" style="margin-left:10px;margin-top:10px;" class="tableborder" cellspacing="1" cellpadding="1" align="center">
	       <div id="messageDiv" style="display:none;"><font color="red">&nbsp;&nbsp;<span id="message"></span></font></div>
	       <tr>
	         <td class="listtabletr3">字典项名称：</td>
	         <td class="listtabletr1"><input class="tabInput" type="text" name="itemsName" id="itemsName"/>
	      </td>
	         <td class="listtabletr3">通讯地址：</td>
	         <td class="listtabletr1"><input class="tabInput" type="text" name="itemsNumber" id="itemsNumber"/></td>
	       </tr>
	        <tr>
	         <td class="listtabletr3">联系电话：</td>
	         <td class="listtabletr1"><input class="tabInput" type="text" name="bak1" id="bak1"/>
	      </td>
	         <td class="listtabletr3">邮编：</td>
	         <td class="listtabletr1"><input class="tabInput" type="text" name="bak2" id="bak2"/></td>
	       </tr>
	        <tr>
	         <td class="listtabletr3">排序字段：</td>
	         <td class="listtabletr1"><input class="tabInput" type="text" name="sort" id="sort"/></td>
	       </tr>
	       <tr>
	         <td class="listtabletr3" colspan="4" style="height:40px; line-height:40px; padding-top:10px;"><input class="butzcon" type="button" value="提交" onclick="addInDictItems();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="butzcon" type="button" value="返回" onclick="javascript:window.history.go(-1);"/></td>
	       </tr>
	    </table>
	   </div>
	</div>
  </body>
</html>
