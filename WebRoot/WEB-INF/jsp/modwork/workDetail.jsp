<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'workDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/style/modwork/css/style.css">
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
    <!-- easyui -->
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- workDetail.js -->
	<script type="text/javascript" src="<%=path %>/js/modwork/workDetail.js"></script> 
  </head>
  
  <body>
	<div class="work-bg"><label id="task">工作标题:${assignWork.title}</label></div>
	<div class="when">
		<label class="worktitle1">总进度${assignWork.totalpercent}%</label><hr>
		<label></label>
	</div>
	<div class="jobcontent-big"><label class="jobcontent">工作内容</label>
		<div style="margin-top:5px;">
			<div style="width:800px;min-height:375px;">${assignWork.content }</div>
		</div>
	</div>
	<c:forEach items="${subAssign}" var="assign">
		<div class="subAssign" id="subAssign${assign.subAssignid}">${assign.receiveOrgName},${assign.receiveStaffName}&nbsp;&nbsp;${assign.percent}%&nbsp;${assign.statusmsg}
			<%-- <c:if test="${assign.count!=null}">
				<a href="javascript:void(0);" id="extbtn${assign.subAssignid}" onclick="extendSubAssign('${assign.subAssignid}')">展开回复</a>
			</c:if> --%>
			<div>
				${assign.content}
			</div>
			<c:if test='${assign.resportList.size()>0}'>
				<div id="response${assign.subAssignid}" class="response">
					<c:forEach items="${assign.resportList}" var="resportList">
						<div>
							<span class="recoverytime">回复时间：${resportList.responseTime}，回复进度：${resportList.percent}%</span><br>
							${resportList.content}
						</div>
						<div class="dashed"></div>
					</c:forEach>
					<script>$(".dashed:last").remove();</script>
				</div>
			</c:if>
			
		</div>
	</c:forEach>
	<c:if test='${assignWork.status=="4"}'>
		<label class="remark">结束备注</label>
		<div>
			<c:if test="${assignWork.bak1==null}">
				<label class="none">（无）</label>
			</c:if>
			<c:if test="${assignWork.bak1!=null}">
				<label class="none">${assignWork.bak1}</label>
			</c:if>
		</div>
	</c:if>
	<input type="button" value="返回" id="btnReturn" onclick="history.go(-1);">
  </body>
</html>
