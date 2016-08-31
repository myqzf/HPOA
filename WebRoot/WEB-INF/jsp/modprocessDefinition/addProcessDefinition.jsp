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
<base href="<%=basePath%>">

<title>新增流程定义</title>
 	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
		<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>

    <script type="text/javascript" charset="utf-8" src="<%=path %>/js/modprocessDefinition/addProcessDefinition.js"></script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
                         部署流程定义
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>

   

        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部署流程定义 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
             <form id="minutes_form" action="process/AddProcessDefinition" method="post" enctype="multipart/form-data" onsubmit="return checkprocess()">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
<!-- 						<td>请选择流程定义文档(bpmn格式)</td> -->
<!--                         <td><input type="file" name="processFile" class="InputStyle {required:true, accept:'.bpmn'}" style="width:450px;" /> *</td> -->
                        
                         <td class="listtabletr5" ><label>请选择流程定义文档<font	color="#ff0000">扩展名为bar</font>的流程文件</label><br /> 
								<input type="file" id="processFile" name="processFile"  /><br />
								</td>
                    </tr>
                </table>
                 <!-- 表单操作 -->
		        <div id="InputDetailBar">
		            <input class="butzcon" type="submit" id="sc" value="添加" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="butzcon" type="button" value="返回" onclick="javascript:window.history.go(-1);" />
		        </div>
              </form>
            </div>
        </div>
        
       
      
</div>

<div class="Description">
	说明：只接受bar扩展名的文件。
</div>

</body>
</html>
