<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
   <link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/zsy.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script> 
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="<%=path%>/js/modsystem/modifyFunc.js"></script>
    <title></title>

  </head>
  
  <body class="mainbody">
  <form id="userForm" name="userForm" method="post" action="modifyUserInfo">
  <input type="hidden" name="funcId" id="funcId" value="${sfi.funcId }" />
  <input type="hidden" name="isLeafaaa" id="isLeafaaa" value="${sfi.isLeaf }" />
  <input type="hidden" name="isUsedaaa" id="isUsedaaa" value="${sfi.isUsed }" />
   <div id="mainbgcon" class="mainbgcon">
	   <div class="mainbg_ltop"></div>
	   <div id="mainbg_midtop" class="mainbg_midtop">
	     <div class="maintoptit">
	      <p>系统管理->组织体系</p>
	     </div>
	   </div>
	</div>
	<div id="mianbg_mid" class="mianbg_mid">
	     <div id="main_midntitbg" class="main_midntitbg">
	      <div class="ma/in_midntitwz">
	     	<p>修改功能</p>
		</div>
		</div>
  <div class="main_tablelb" id="main_tablelb"> 
  <div id="msgDiv"></div>
    <table  border="0" width="600px;" style="margin-left:10px;margin-top:10px;" class="tableborder" cellspacing="1" cellpadding="1" align="center">
       <div id="messageDiv" style="display:none;"><font color="red">&nbsp;&nbsp;<span id="message"></span></font></div>
       <tr>
         <td class="listtabletr3">功能名称：</td>
         <td class="listtabletr1"><input class="tabInput" type="text" name="funcName" id="funcName" value="${sfi.funcName }"/>
      </td>
         <td class="listtabletr3">功能描述：</td>
         <td class="listtabletr1"><input class="tabInput" type="text" name="funcDesc" id="funcDesc" value="${sfi.funcDesc }"/></td>
       </tr>
        <tr>
         <td class="listtabletr3">访问地址：</td>
         <td class="listtabletr1"><input class="tabInput" type="text" name="funcUrl" id="funcUrl" value="${sfi.funcUrl }"/></td>
         <td class="listtabletr3">是否叶子节点：</td>
         <td class="listtabletr1"><input type="radio"  name="isLeaf"   value="1">是</input>&nbsp;&nbsp;<input type="radio"  name="isLeaf"  value="2">否</input></td>
       </tr>
       <tr>
         <td class="listtabletr3" >是否可用：</td>
         <td class="listtabletr1" colspan="3"><input type="radio"   name="isUsed" value="1">是</input>&nbsp;&nbsp;<input type="radio"  name="isUsed"  value="2">否</input></td>
       </tr>
       <tr>
         <td class="listtabletr3" colspan="4" style="height:40px; line-height:40px; padding-top:10px;"><input class="butzcon" type="button" value="提交" onclick="modifyFunc();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="butzcon" type="button" value="返回" onclick="javascript:window.history.go(-1);"/></td>
       </tr>
    </table>
    </div>
   </div>
   </form>
  </body>
</html>
