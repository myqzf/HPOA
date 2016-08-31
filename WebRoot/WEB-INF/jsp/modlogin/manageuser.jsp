<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mamageuser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/redmond/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/ui.jqgrid.css" /> 
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="<%=path%>/js/modlogin/usermanage.js"></script>
	<style>
		*{padding:0;margin:0}
		ul,li{list-style:none}
		a{text-decoration: none;}
		.roleManager{width:474px;border:1px solid #ccc;}
		/*.roleManager{width:474px;border:1px solid #ccc;margin:0 auto;}*/
		.roleManagerTabtit{height:42px;overflow:hidden;}
		.roleManagerTabtit li{float:left;width:236.4px;border-right:1px solid #ccc;line-height:41px;height:41px;text-align:center;border-bottom:1px solid #ccc;cursor:pointer}
		.roleManagerTabtit li:last-child{border-right:none;}
		.roleManagerTabtit li.active{background:#0a9deb url(../images/sanjiao.jpg) no-repeat center bottom;height:42px;border-bottom:none;color:#fff;}
		
		
		.roleMain{height:320px;display:none}
		.checkedRole,.nonCheckRole{width:46%;float:left}
		.choiceRoleControl{width:8%;float:left;height:100%;}
		
		.checkedRole ul{overflow:auto;height:300px;}
		.checkedRole ul li{height:34px;line-height:34px;padding:0px 10px;}
		.checkedRole ul li:hover{background:#6ee58d;cursor:pointer}
		.checkedRole ul li.active{background:#6ee58d;}
		
		
		.choiceRoleControl div{border:1px solid #ccc;border-width:0px 1px;height:100%;}
		.choiceRoleControl div ul{padding-top:50px;}
		.choiceRoleControl ul li{font-size:20px;text-align:center;margin-bottom:25px;font-weight:bolder;cursor:pointer;color:#929492;}
		.choiceRoleControl ul li.last{padding-top:25px;}
		
		.nonCheckRole ul{overflow:auto;height:300px;}
		.nonCheckRole ul li{height:34px;line-height:34px;}
		.nonCheckRole ul li :hover{background:#f3f2f2;cursor:pointer}
		.nonCheckRole ul li.active{background:#f3f2f2;}
		.nonCheckRole ul li span{width:95%;display:block;border-right:0px solid #e6e6e6;padding-left:10px;float:left;}
	</style>
  </head>
  
  <body>
  	<input type="hidden" id="staffid" value="${staffid }">
  	<input type="hidden" id="flag" value="${flag }">
  	<input type="hidden" id="Pwd" value=${userPwd }>
    <table  border="0" width="600px;" style="margin-left:10px;margin-top:10px;" class="tableborder" cellspacing="1" cellpadding="1" align="center">
       <tr>
		<td class="listtabletr3">用户账户：</td>
		<td class="listtabletr1">
		<input class="tabInput" type="text" name="userAccount" id="userAccount" value="${userAccount }"/>
		</td>
		<td class="listtabletr3" >用户姓名：</td>
		<td class="listtabletr1" ><input class="tabInput" type="text" name="userName" id="userName" value="${userName }"/></td>
       </tr>
       <tr>
         <td class="listtabletr3">密码：</td>
         <td class="listtabletr1"><input class="tabInput" type="password" name="userPad" id="userPad" value="${userAccount}"/></td>
         <td class="listtabletr3">密码确认：</td>
         <td class="listtabletr1"><input class="tabInput" type="password" name="userPad1" id="userPad1" value="${userAccount}"/></td>
       </tr> 
       <%-- <tr>
         <td class="listtabletr3" >用户姓名：</td>
         <td class="listtabletr1" ><input class="tabInput" type="text" name="userName" id="userName" value="${userName }"/></td>
           <td class="listtabletr3" ></td>
         <td class="listtabletr1" ></td>
       </tr>--%>
    </table>
    <label>请选择该用户角色</label>
    <%-- <div style="margin-bottom:10px;margin-left:10px;margin-top:10px; " id="list">
		<table id="roleList" class="scroll" cellpadding="0" cellspacing="0"></table>
		<div id="pager1" class="scroll"></div>
	</div>--%>
	<div class="roleManager">
		<div class="roleMain" style="display:block">
			<div class="checkedRole">
				<ul>
				</ul>
			</div>
			<div class="choiceRoleControl">
				<div>
					<ul>
						<li class="alladdElement">&lt&lt</li>
						<li class="addElement">&lt</li>
						<li class="removeElement">&gt</li>
						<li class="allremoveElement">&gt&gt</li>
					</ul>
				</div>
			</div>
			<div class="nonCheckRole">
				<ul>
				</ul>
			</div>
		</div>
	</div>
	<input class="butzcon" id="add" type="button" value="新增"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input class="butzcon" id="modify" type="button" value="修改"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input class="butzcon" type="button" value="返回" onclick="rtn();"/>
  </body>
</html>
