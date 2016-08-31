<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>短信箱</title>
    <meta http-equiv="x-ua-compatible" content="ie=8" />
	<meta name="renderer" content="webkit" />
	<meta http-equiv="X-UA-Compatible" content="IE=8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- jqgridCss -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/redmond/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/ui.jqgrid.css" />
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- jqgrid -->
  	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/i18n/grid.locale-cn.js"></script>
	<!-- listMessage.js -->
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modmessage/listMessage.js"></script>
	<STYLE type=text/css>
		BODY {
			FONT-SIZE: 14px; FONT-FAMILY: "宋体"
		}
		OL LI {
			MARGIN: 8px
		}
		#con {
			FONT-SIZE: 12px; MARGIN-BOTTOM:10px;
		}
		#tags {
			PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px 0px 0px 0px; WIDTH: 400px; PADDING-TOP: 0px; HEIGHT: 29px
		}
		#tags LI {
			BACKGROUND: url(img/tagleft.jpg) no-repeat left bottom; FLOAT: left; MARGIN-RIGHT: 1px; LIST-STYLE-TYPE: none; HEIGHT: 29px
		}
		#tags LI A {
			PADDING-RIGHT: 10px; PADDING-LEFT: 10px; BACKGROUND: url(img/tagright.jpg) no-repeat right bottom; FLOAT: left; PADDING-BOTTOM: 0px; COLOR: #000; LINE-HEIGHT: 30px; PADDING-TOP: 0px; HEIGHT: 29px; TEXT-DECORATION: none
		}
		#tags LI.emptyTag {
			BACKGROUND: none transparent scroll repeat 0% 0%; WIDTH: 4px
		}
		#tags LI.selectTag {
			BACKGROUND-POSITION: left top; MARGIN-BOTTOM: -2px; POSITION: relative; HEIGHT: 29px
		}
		#tags LI.selectTag A {
			BACKGROUND-POSITION: right top; COLOR: #000; LINE-HEIGHT: 30px; HEIGHT: 29px
		}
		#tagContent {
			BORDER-RIGHT: #e8a999 1px solid; PADDING-RIGHT: 1px; BORDER-TOP: #e8a999 1px solid; PADDING-LEFT: 1px; PADDING-BOTTOM: 1px; BORDER-LEFT: #e8a999 1px solid; PADDING-TOP: 1px; BORDER-BOTTOM: #e8a999 1px solid; BACKGROUND-COLOR: #fff
		}
		.tagContent {
			PADDING-RIGHT: 1px; DISPLAY: none; PADDING-LEFT: 1px; COLOR: #474747; PADDING-TOP: 1px; 
		}
		.tagContent DIV.selectTag {
			DISPLAY: block
		}
	</STYLE>
  </head>
  
  <body>
		<input type="hidden" id="userid" value="${userVo.userId }"/>
		<input type="hidden" id="flag" value="${flag }"/> 
		<%-- <input type="hidden" id="flag" value="receivemsg"/> --%>
		&nbsp;&nbsp;<a href="javascript:void(0);" id="gotowrite">撰写信息</a><label>您有<a href="javascript:void(0);" id="newMessage">${noReadCount}封</a>邮件未读</label>
		<DIV id="con" >
			<UL id="tags">
				<LI id="receivemsg" ><A onClick="selectTag('tagContent0',this)" href="javascript:void(0)">收件箱</A> </LI>
				<LI id="sendmsg"><A onClick="selectTag('tagContent1',this)" href="javascript:void(0)">发件箱</A> </LI>
				<LI id="nosendmsg"><A onClick="selectTag('tagContent2',this)" href="javascript:void(0)">草稿箱</A> </LI>
			</UL>
			
			<DIV id="tagContent">
				<DIV class="tagContent" id="tagContent0">
					<div style="display: none " id="receivelist">
						<table id="receivemessageList" class="scroll" cellpadding="0" cellspacing="0"></table>
						<div id="receivepager1" class="scroll"></div>
					</div>
				</DIV>
				
				<DIV class="tagContent" id="tagContent1">
					<div style="display: none " id="sendlist">
						<table id="sendmessageList" class="scroll" cellpadding="0" cellspacing="0"></table>
						<div id="sendpager1" class="scroll"></div>
					</div>
				</DIV>
				<DIV class="tagContent" id="tagContent2">
					<div style="display: none " id="nosendlist">
						<table id="nosendmessageList" class="scroll" cellpadding="0" cellspacing="0"></table>
						<div id="nosendpager1" class="scroll"></div>
					</div>
				</DIV>
			</DIV>
		</DIV>
  </body>
</html>
