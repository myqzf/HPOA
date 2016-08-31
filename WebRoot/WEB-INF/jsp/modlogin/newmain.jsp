<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="x-ua-compatible" content="ie=8" />
	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=8">
    <title>欢迎使用项目管理系统</title>
    
	<link href="<%=path %>/newloginmain/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/newloginmain/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=path %>/newloginmain/themes/default/easyui.css" />
    <style>
		#msgimg{float:left;}
		#messagescroll{overflow:hidden;border:1px solid #f60;padding:5px 5px;margin-top:10px;}
		#messagescroll a{font-size:18px;color:#ff0000;}
		#messagescroll label{font-size:18px;color:#000000;}
		#listnotice{overflow: hidden;padding-left: 10px;height:150px;text-align: left;}
		#listnotice a{font-size:15px;}
		.panel-body-noborder{overflow: visible;}
	</style>
	<script type="text/javascript">var _menus = ${list}</script>
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="<%=path %>/newloginmain/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="<%=path %>/newloginmain/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=path %>/newloginmain/js/outlook2.js"> </script>
    <script type="text/javascript" src="<%=path%>/js/modlogin/newmain.js"></script>
    <script type="text/javascript" src="<%=path%>/js/modlogin/horizontalscroll.js"></script>
	<script type="text/javascript" src="<%=path%>/js/modlogin/verticalscroll.js"></script>
    <script type="text/javascript" src="<%=path%>/js/modlogin/top.js"></script>
    
    </head>
    <body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
		<noscript>
	    	<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;"> <img src="<%=path%>/newloginmain/images/noscript.gif" alt='抱歉，请开启脚本支持！' /> </div>
	    </noscript>
	<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
	        background: url(<%=path%>/newloginmain/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
	        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体"> <span style="float:right; padding-right:20px;" class="head">欢迎 管理员 <a href="javascript:void(0);" id="editpass" >修改密码</a> <a href="javascript:void(0);" id="loginOut">安全退出</a></span> <span style="padding-left:10px; font-size: 16px; "><img src="<%=path%>/newloginmain/images/blocks.gif" width="20" height="20" align="absmiddle" />项目管理系统</span> </div>
	<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
	      <div class="footer">版权所有，翻版必究</div>
	    </div>
	<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
	      <div id="nav" class="easyui-accordion" fit="true" border="false"> 
	    <!--  导航内容 --> 
	    
	  </div>
	    </div>
	<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
	      <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
	    <div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
			<h1 style="font-size:24px;">欢迎使用项目管理系统</h1>
			<div><img id="msgimg" src="/HPOA/img/message.png">
				<div id="messagescroll">
				</div>
			</div>
			<div id="notice">
				<div style="float:right;">
					<font style="font-size:20px;">公告</font>
					<ul id="listnotice">
					</ul>
				</div>
			</div>
		</div>
	  </div>
	    </div>
	<div region="east" title="其他" split="true" style="width:180px;overflow:hidden;">
	      <div class="easyui-calendar"></div>
	    </div>
	
	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
	        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
	        background: #fafafa;">
	      <div class="easyui-layout" fit="true">
	    <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
	          <table cellpadding=3>
	        <tr>
	        	<td>旧密码：</td>
	        	<td><input id="txtOldPass" type="password" class="txt01" /></td>
	        </tr>
	        <tr>
	              <td>新密码：</td>
	              <td><input id="txtNewPass" type="password" class="txt01" /></td>
	            </tr>
	        <tr>
	              <td>确认密码：</td>
	              <td><input id="txtRePass" type="password" class="txt01" /></td>
	            </tr>
	      </table>
	        </div>
	    <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;"> <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" > 确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a> </div>
	  </div>
	    </div>
	<div id="mm" class="easyui-menu" style="width:150px;">
	      <div id="mm-tabupdate">刷新</div>
	      <div class="menu-sep"></div>
	      <div id="mm-tabclose">关闭</div>
	      <div id="mm-tabcloseall">全部关闭</div>
	      <div id="mm-tabcloseother">除此之外全部关闭</div>
	      <div class="menu-sep"></div>
	      <div id="mm-tabcloseright">当前页右侧全部关闭</div>
	      <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	      <div class="menu-sep"></div>
	      <div id="mm-exit">退出</div>
	    </div>
	    <input type="hidden" id="status" name="status" value="${status}">
	</body>
</html>
