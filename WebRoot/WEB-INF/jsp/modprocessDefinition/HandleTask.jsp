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
    
    <title>办理任务</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	<!-- handleTask.js -->
	<script type="text/javascript" src="<%=path%>/js/modprocessDefinition/handleTask.js"></script>
	<!-- viewPurchase.js -->
<!-- 	<script type="text/javascript" src="<%=path%>/js/modreimburse/viewPurchase.js"></script> -->
  </head>
  
  <body>
    <input type="text" name="url" id="url" value="${url}" />
    <input type="text" name="taskId" id="taskId" value="${taskId}" />
    <input type="text" name="reimburse_id" id="reimburse_id" value="${hri.id}" />
    <div align="center">
  	<h1>采购报销单</h1>
  	报销部门：<label id="dept">${staff.staffDept}</label>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	报销人员：<label id="name">${staff.staffName}</label>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<label id="date" ></label>
  	
  	<table style="min-height: 80px;" border="1px solid" width="686" height="98">
	  	<tr>
	  		<th align="center" style="width:200px;height:18px">  				
 				<label>项&nbsp;目&nbsp;名&nbsp;称</label>
 			</th>
	  		<th align="center" style="width:200px;height:18px">
	  			<label>金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额</label>
	  		</th>
	  		<th align="center" style="width:200px;height:18px">
  				<label>单&nbsp;据&nbsp;数&nbsp;量</label>
  			</th>
  			<th align="center" style="width:200px;height:18px">
  				<label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
  			</th>
	  	</tr>
 		<tr>
			<td>
  				${hri.entryname}
  			</td>
  			<td>
  				${hri.reimoney}
  			</td>
  			<td>
  				${hri.reinum}
  			</td>
  			<td>
  				${hri.remark}
  			</td>
  		</tr>
		<tr>
  			<td>  			
 				<label>金额大写：</label>
 			</td>
  			<td colspan=3>
  				${hri.uppermoney}
  			</td>
  		</tr>
  	</table>
  	批注：<textarea id="comment" rows="3" cols="20"> </textarea>
  	<!-- 使用连线的名称作为按钮 -->
  	    <c:forEach  items="${outcomeList}" var="sta"> 
  	    <input type="button" name="outcome" id="outcome" value="${sta}" class="button_ok" onclick="submit_handle();"/>
     </c:forEach>
	<input type="button" value="返回" id="btnReturn">
	
<!-- 	<c:if test="${not empty commentList}"> -->
<!--        This is your first visit . -->
<!--     </c:if> -->
	<c:choose>  
	  
	   <c:when test="${not empty commentList}">    
                        显示批注信息  未画表格
	   </c:when>  
	     
	   <c:otherwise>   
	   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr>
			    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td height="24" bgcolor="#F7F7F7"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="6%" height="19" valign="bottom"><div align="center"></div></td>
			                <td width="94%" valign="bottom"><span><b>暂时没有批注信息</b></span></td>
			              </tr>
			            </table></td>
			          </tr>
			        </table></td>
			      </tr>
			    </table></td>
			  </tr>
		</table>
	   </c:otherwise>  
	</c:choose>  
	</div>
  </body>
</html>
