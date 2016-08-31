<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
<head>
    <title>流程定义列表</title>
    	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	
	<!-- easyui -->
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/commonjs/easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	
	
	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modprocessDefinition/ProcessDefinitionList.js"></script>
	
	<style type="text/css">
	#mianbg_mid .datagrid-btable tr{height: 30px;}
	.l-btn-text{line-height:20px;}
	.datagrid-btable tr span{color:blue;}
	</style>
  <%--   <%@ include file="/WEB-INF/jsp/public/common.jspf" %> --%>
   <!--  <script type="text/javascript">
	    function showProcessImage( pdId ){
	    	// alert("原文：" + pdId);
	    	
	    	pdId = encodeURI(pdId);
	    	// alert("第一次URL编码：" + pdId);

	    	pdId = encodeURI(pdId);
	    	// alert("第二次URL编码：" + pdId);
	    	
            var url = "processDefinitionAction_downloadProcessImage.action?id=" + pdId + "&t=" + new Date();
            window.showModalDialog(url, null, "dialogHeight:500px;dialogWidth:600px;resizable:yes");
        }
    </script>  -->
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
                                        流程定义管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
 <input type="hidden" name="message" id="message" value="${rtnFlg}" />
  <table id="grid" style="width: 1024px" title="审批流程列表" iconcls="icon-view">            
            </table>

    
    <!-- 其他功能超链接 -->
<!--     <div id="TableTail"> -->
<!--         <div id="TableTail_inside"> -->
<!--             <table border="0" cellspacing="0" cellpadding="10" align="left"> -->
<!--                 <tr> -->
<!-- 			        <td><div class="FuncBtn"> -->
<!--                             <div class=FuncBtnHead></div> -->
<!--                             <div class=FuncBtnMemo></div> -->
<!--                               <a href="<%=path %>/process/goAddProcessDefinition">部署流程定义文档</a> -->
<!--                             <div class=FuncBtnTail></div> -->
<!--                         </div></td> -->
<!--                 </tr> -->
<!-- 			</table> -->
<!--         </div> -->
<!--     </div> -->
</div>

<div class="Description">
	说明：<br />
	1，列表显示的是所有流程定义（不同Key）的最新版本。<br />
	2，删除流程定义时，此Key的所有版本的流程定义都会被删除。<br />
	3，查看流程图时显示的这个最新版本的流程定义的图片。
</div>

</body>
</html>
