<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/modlogin/login.js"></script>
	<script type="text/javascript">
		//防止session失效时带框架页面刷新问题
		var _topWin = window;  
		while (_topWin != _topWin.parent.window) {  
		     _topWin = _topWin.parent.window;  
		}  
		if (window != _topWin)_topWin.document.location.href = '<%=path%>/login/login'; 
		
	</script>
  </head>
  
	<body>
  		<%-- <font color="red" size="16">欢迎使用XX系统</font>
  		<hr>
  		<div class="tistext"><p id="msg"></p></div>
  		<form id="userForm" action="<%=path %>/login/goMain" method="post">
  		请输入用户名：<input id="account" type="text" name="account" /><br/>
  		请输入密码:<input id="password" type="password" name="password"/>
  		<input type="button" id="subBtn" onclick="sub();"  value="登 录"/> 
        <input type="button" onclick="clearInfo();" value="取 消"/>
  		</form> --%>
		<div class="container">
    	<div class="dengluyemian-container">
    	<div class="kuangnei">
        	<div class="dengluyemian-er">
        	<div class="dengluyemian-er-right">
        		<form id="userForm" action="<%=path %>/login/goMain" method="post">
            	<div class="dengluyemian-er-right-1">
                	<span class="zuo"><img src="images/yonghuming_02.png" width="20" height="20" /></span>
                    <span class="you">
                    	<input id="account" type="text" name="account" />
                    </span>
                </div>
                <div class="dengluyemian-er-right-1">
                	<span class="zuo"><img src="images/mima-.png" width="20" height="20" /></span>
                    <span class="you">
                    	<input id="password" type="password" name="password" />
                    </span>
                </div>
                <div class="dengluyemian-er-right-3">
                	<span class="zuo">
                    	<input type="button" id="subBtn" onclick="sub();" value="登录" />
                    </span>
                    <span class="you">
                    	<input type="button" onclick="clearInfo();" value="取消" />
                    </span>
                </div>
                </form>
            </div>	
            </div>
        </div>
    	</div>
    	</div>
	</body>
</html>
