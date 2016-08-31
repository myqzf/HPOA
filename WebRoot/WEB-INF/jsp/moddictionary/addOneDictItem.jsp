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
	    <table  border="0" width="600px;" style="margin-left:10px;margin-top:10px;" class="tableborder" cellspacing="1" cellpadding="1" align="center">
	       <div id="messageDiv" style="display:none;"><font color="red">&nbsp;&nbsp;<span id="message"></span></font></div>
	       <tr>
	         <td class="listtabletr3">字典项名称：</td>
	         <td class="listtabletr1"><input class="tabInput" type="text" name="itemsName" id="itemsName"/>
	      </td>
	       </tr>
	        <tr>
	        <!--  <td class="listtabletr3">是否叶子节点：</td>
	         <td class="listtabletr1"><input type="radio" checked="checked" name="ifleaf" id="ifleaf"  value="1">是</input>&nbsp;&nbsp;<input type="radio"  name="ifleaf" id="ifleaf" value="2">否</input></td> -->
	       </tr>
	       <tr>
	         <td align="center"><select name="headId" id="headId" > <option value='0'>请选择所属字典头</option>    </select></td> 
	        </tr><!-- onchange="serchDictHead()" -->
	       <tr>
	         <td class="listtabletr3" colspan="4" style="height:40px; line-height:40px; padding-top:10px;"><input class="butzcon" type="button" value="提交" onclick="addDictItems();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="butzcon" type="button" value="返回" onclick="javascript:window.history.go(-1);"/></td>
	       </tr>
	    </table>
	   </div>
	</div>
  </body>
</html>
