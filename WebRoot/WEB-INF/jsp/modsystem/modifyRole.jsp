<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <title>角色修改</title>
  
  
    <script type="text/javascript" charset="utf-8" src="<%=path %>/commonjs/jquery-1.10.2.js"> </script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script> 

    <script type="text/javascript" src="<%=path%>/js/modsystem/modifyRole.js"></script>

  </head>
  
  <body>
  <form>
  <input type="hidden" name="roleId" id="roleId" value="${sri.roleId }" />


	     	<p>角色修改</p>

    <table id="tabItems" border="0" cellpadding="0" cellspacing="0" width="300" align="center" style="font-size:13px;border:1px solid #e0e4e7;">
     <tr>
       <td valign="middle" height="35" align="right">角色名：</td>
       <td valign="middle"><input type="text" id="roleName" name="roleName" value="${sri.roleName }" maxlength="200" /></td>
     </tr>
     <tr>
       <td valign="middle" height="35" align="right">角色描述：</td>
       <td valign="middle"><input type="text" id="roleDesc" name="roleDesc" value="${sri.roleDesc }" maxlength="200" /></td>
     </tr>
      <tr>
         <td class="listtabletr3" colspan="4" style="height:40px; line-height:40px; padding-top:10px;"><input class="butzcon" type="button" value="修改" onclick="modRole();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="butzcon" type="button" value="返回" onclick="javascript:window.history.go(-1);"/></td>
       </tr>
  </table>

   </form>
  </body>
</html>
