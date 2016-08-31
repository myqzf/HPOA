<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="ie-comp">
<meta name="renderer" content="ie-stand">    
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=8">

<title>My First Grid</title>

	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>

   <!-- jqgridCss -->
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/redmond/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/commonjs/jqgrid/css/ui.jqgrid.css" />  	
	<!-- jqgridJs -->
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/jqgrid/js/i18n/grid.locale-cn.js"></script>

    <!-- artDialog -->
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/jquery.artDialog.js?skin=default"></script>
	<script type="text/javascript" src="<%=path%>/commonjs/artDialog/plugins/iframeTools.source.js"></script> 
    <style type="text/css">
     .ui-jqgrid td input{height: 30px;}
	</style>
	
		<script type="text/javascript" charset="utf-8" src="<%=path %>/js/modmonthly/monthlyManage.js"></script>
</head>

<body>
  <input type="hidden" name="monthscope" id="monthscope" value="${mon.monthscope }">
<table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="800" >
				<tbody>
					<tr style="display: block;">
					
						<td class="listtabletr3"><label>年份：</label></td> <td align="center"><select name="year" id="year" >     </select></td> 
                        <td class="listtabletr3"><label>月份：</label></td> <td align="center"><select name="month" id="month" > <%-- <option value="">${mon.monthscope }</option>    --%>   </select></td> 
                        <td class="listtabletr3"> <button class="button" id="query" onclick="queryManthly()">查询</button>&nbsp;</td> 	
                        <td class="listtabletr3"> <button class="button" id="query" onclick="queryNotSubmit()">查看未提交人员</button>&nbsp;</td> 					</tr>
				</tbody>
			</table>
        <br>
			月报已交：<input type="text"  name="isSubmit" id="isSubmit"  disabled="true" style="background-color: white;border: hidden;width: 30px;" />
			月报未交：<input type="text"  name="notSubmit" id="notSubmit" disabled="true" style="background-color: white;border: hidden;width: 30px;" />&nbsp;&nbsp;
<!-- 			<button class="button" id="query" onclick="queryNotSubmit()">查看未提交人员</button> -->
			    
			     
			     <br/>
			 	<br />			 
			 <div id="jqtable">
	 			<table id="list"  class="scroll" cellpadding="0" cellspacing="0"></table>
	 			<div id="pager" class="scroll"></div>
	 		</div>
	 	
	 	    <!-- artDialog 对话框   未提交人员 -->
<!-- 	 <div id="jqtable_notSubmit_div"> -->
<!--        <div class='jqtable_boxy' style="display:none;" id="jqtable_boxy">    -->
<!--                 <table id="notSubmitlist"  class="scroll" cellpadding="0" cellspacing="0"></table> -->
<!-- 	 	    <div id="pager1" class="scroll"></div>   -->
<!--                           <input id='cancer' type='button' class='btn1' value='返&nbsp;&nbsp;回'/> -->
<!--              </div> -->
<!--           </div> -->
	<br>
</body>
</html>
