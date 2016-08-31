<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工详细信息</title>
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	
	<!-- staffDetail.js -->
	<script type="text/javascript" src="<%=path%>/js/modstaff/staffDetail.js"></script>
  </head>
  
  <body class="mainbody">
  	<div id="mainbgcon" class="mainbgcon">
  		<div class="mainbg_ltop"></div>
		<div id="mainbg_midtop" class="mainbg_midtop">
		   <div class="maintoptit">
		      <p>员工管理->查看员工信息 </p>
		   </div>
		</div>
		<div class="mainbg_rtop"></div>
		<div id="mianbg_mid" class="mianbg_mid">
		    <div id="main_midntitbg" class="main_midntitbg">
		     	<div class="main_midntitwz">
		     <p>员工详细信息</p>
			 </div>
			</div>
			<div id="main_tablelb" class="main_tablelb">
	  		<div>
	  		<table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="800">
		  		<tr>
					<td class="listtabletr5"><label>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</label>${staffInfo.staffName}</td>
					<td class="listtabletr5"><label>性&nbsp;&nbsp;&nbsp;&nbsp;别：</label>${staffInfo.staffGender}</td>
					<td class="listtabletr5" rowspan="5" width="160" colspan=3 >	
			      	<div style="margin-left:70px;" >
					<img alt="" height="145" width=100 id="photo" src="staffpicture/none.jpg" onerror="nofindimg()"/>
		  	       	</div></td>
			 	</tr>
				<tr>			
					<td class="listtabletr5" ><label>身份证号：</label>${staffInfo.staffIdcard}</td>
		  			<td class="listtabletr5">
			  		<label>婚&nbsp;&nbsp;&nbsp;&nbsp;否：</label>${staffInfo.staffMarry}</td>	 		
		  		</tr>
		  		<tr>
			  		<td class="listtabletr5"><label>学&nbsp;&nbsp;&nbsp;&nbsp;历：</label>${staffInfo.staffQualid}</td> 
			  		<td class="listtabletr5"><label>联系电话：</label>${staffInfo.staffPhone}</td>		  		
				</tr>
				<tr>
			    	<td class="listtabletr5"><label>家庭住址：</label>${staffInfo.staffAddress}</td>		
			  		<td class="listtabletr5"><label>隶属部门：</label>${staffInfo.staffDept}</td>	
				</tr>
				<tr>
			  		<td class="listtabletr5"><label>隶属职位：</label>${staffInfo.staffPosi}</td>
				 	<td class="listtabletr5"><label>隶属公司：</label>${staffInfo.staffComp}</td>
		  		</tr>
		 	</table>				  	
		  	  <input type="button" class="tsbtn2" id="retu" value="返回" />
		  	 <input type="hidden" id="photourl" value="${staffInfo.staffPhotourl}"/></div><br/>		  	
	  	</div>
		</div>
	
  </body>
</html>
