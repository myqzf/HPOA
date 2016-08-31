<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'menu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/modlogin/menu.js"></script>
  </head>
  
  <body>
  <script type="text/javascript">
		function searchMeanInfo(){
			$.post("menu/jsonmenu.json",{},function(data){
				var li = data.funcList;
				var first = "";
				$.each(li,function(n,funcVo){
					if(funcVo.isLeaf == 2 ){
						first +="<p id="+funcVo.funcId+">"+funcVo.funcName+"</p><div id="+funcVo.funcId+"items ></div>";
					}
				});
				$("#firstpane").html(first);
				$.each(li,function(n,funcVo){
					if(funcVo.isLeaf == 2 ){
						var sec="";
						$.each(li,function(n,funcVo1){
							if(funcVo1.isLeaf == 1 && funcVo1.funcPid == funcVo.funcId){
								sec += "<a href=\"javascript:void(0);\" onclick=\"turnMain('"+funcVo1.funcUrl+"')\">"+funcVo1.funcName+"</a><br>";
							}
						});
						$("#"+funcVo.funcId+"items").html(sec);
					}
				});
				
			});
		}
		//拼接左侧功能菜单
		$(document).ready(function(e) {
			searchMeanInfo();
		});
	</script>
	<div id="menu" style="height:100%;overflow-x: hidden;overflow-y: auto;">
	    <div class="leftmetitbg">
	     <a href="javascript:void(0);"></a>
	   	</div>
	  	<div id="firstpane" class="menu_list">
	  	</div>
  	</div>
  </body>
</html>
