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
    
    <title>修改员工信息</title>
   
	<!-- jquery -->
	<script type="text/javascript" src="<%=path%>/commonjs/jquery-1.10.2.js"></script>
	<!-- artDialog -->
    <script type="text/javascript" src="<%=path %>/commonjs/artDialog/jquery.artDialog.js?skin=chrome"></script>
	<script type="text/javascript" src="<%=path %>/commonjs/artDialog/plugins/iframeTools.source.js"></script>
	
	<!-- updateStaff.js -->
	<script type="text/javascript" src="<%=path%>/js/modstaff/updateStaff.js"></script>  	
  </head>
  
  <body class="mainbody">
  	<div id="mainbgcon" class="mainbgcon">
  		<div class="mainbg_ltop"></div>
		   <div id="mainbg_midtop" class="mainbg_midtop">
		     <div class="maintoptit">
		      <p>员工管理->修改员工信息  </p>
		     </div>
		   </div>
		   <div class="mainbg_rtop"></div>
			<div id="mianbg_mid" class="mianbg_mid">
		     <div id="main_midntitbg" class="main_midntitbg">
		      <div class="main_midntitwz">
		     	<p>修改员工信息</p>
			  </div>
			 </div>
			<div id="main_tablelb" class="main_tablelb">
	  		<div>
	  		<table border="0" cellpadding="1" cellspacing="1" class="tableborder" width="800" >
				<tr>
		  	 	 <td class="listtabletr5">
			  	<label >姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
			  	<input class="tabInput" id="staffName" type="text" value="${staffInfo.staffName}" maxLength="50"/>
			 	 </td>
			 	 <td class="listtabletr5">
			  		<label  >性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
			  		  <select id="staffGender" style="width:120px;">
			  	 		 <option value="1">男</option>
			  	  		<option value="2">女</option></select>
			 	</td>
			 	<td class="listtabletr5" width="160" rowspan="5" >	
			       <div style="margin-left:70px;" >
		  		     <img height="145" width="100" id="picture" name="picture" src="staffpicture/none.jpg" scrolling="no" /><br/>
		  		     <label><a href="javascript:void(0)" id="modifyPicture">上传照片</a></label>
		  		     <br><label><a href="javascript:void(0)" id="delPhoto">删除照片</a></label>
		  	       </div>

		  	    </td>
			 	</tr>
				 <tr>			
				 <td class="listtabletr5" >
		  		 	<label>身&nbsp;份&nbsp;证&nbsp;号：</label>
					<input class="tabInput" id="staffIdcard" type="text" value="${staffInfo.staffIdcard}" maxLength="70"/>
					<label id="lblIdcardCheck">请输入18位正确的身份证号码</label></td>				
		  		</td>
		  		<td class="listtabletr5">
			  	<label>婚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否：</label>
					<select id="staffMarry" style="width:120px;">
			  	  		<option value="1">未婚</option>
			  	  		<option value="2">已婚</option>	
			  	  		<option value="3">离异</option>			  	  		
			  	 	 </select>
				 </td>	 		
		  		</tr>
		  		<tr>
		  		<td class="listtabletr5">
			 		<label>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：</label>
			 		<select id="staffQualid" style="width:120px;">
			 			<c:forEach  items="${qualidList}" var="sta"> 
			 				<option value='${sta.itemsid}'>${sta.itemsname}</option> 
 			 			</c:forEach> 
			 		</select>			
			 	</td> 
		  		<td class="listtabletr5">
			  	<label>联&nbsp;系&nbsp;电&nbsp;话：</label>
			  	<input class="tabInput" id="staffPhone" type="text" value="${staffInfo.staffPhone}" maxLength="50"/>
			  	</td>		  		
				</tr>
				<tr>
		    	 <td class="listtabletr5">
			  		<label>家&nbsp;庭&nbsp;住&nbsp;址：</label>
			  		<input class="tabInput" id="staffAddress" type="text" value="${staffInfo.staffAddress}" maxLength="200"/>
			 	 </td>		
		  		<td class="listtabletr5">	
		  		<label>隶&nbsp;属&nbsp;部&nbsp;门：</label>
		  		<select id="staffDept" style="width:155px;">
		  			 <c:forEach items="${deptList}" var="sta">
		  				<option value="${sta.itemsid}">${sta.itemsname}</option>
		  			</c:forEach>
		  		</select>		  		
		  		</td>	
				</tr>
				<tr>
		  		<td class="listtabletr5">
			  	<label>隶&nbsp;属&nbsp;职&nbsp;位：</label>
				<select id="staffPosi" style="width:155px;">
		  			 <c:forEach items="${posiList}" var="sta">
		  				<option value="${sta.itemsid}">${sta.itemsname}</option>
		  			</c:forEach>
		  		</select>				 	 
		  		</td>
			 	 <td class="listtabletr5">
			  	<label>隶&nbsp;属&nbsp;公&nbsp;司：</label>
				<select id="staffComp" style="width:155px;">
		  			 <c:forEach items="${compList}" var="sta">
		  				<option value="${sta.itemsid}">${sta.itemsname}</option>
		  			</c:forEach>
		  		</select>	
			  	</td>
		  		</tr>		 
		  	</table>		
		  	<input type="hidden" id="id" value="${staffInfo.staffId}"/>
		  	<input type="hidden" id="gender" value="${staffInfo.staffGender}"/>
		  	<input type="hidden" id="name" value="${staffInfo.staffName}"/>
		  	<input type="hidden" id="marry" value="${staffInfo.staffMarry}"/>
		  	<input type="hidden" id="idcard" value="${staffInfo.staffIdcard}"/>
		  	<input type="hidden" id="qualid" value="${staffInfo.staffQualid}"/>
		  	<input type="hidden" id="address" value="${staffInfo.staffAddress }"/>
		  	<input type="hidden" id="dept" value="${staffInfo.staffDept}"/>
		  	<input type="hidden" id="posi" value="${staffInfo.staffPosi}"/>
		  	<input type="hidden" id="comp" value="${staffInfo.staffComp}"/>		  	
		  	<input type="hidden" id="photoval" value="${staffInfo.staffPhotourl}"/>		  	
		  	<input type="hidden" id="userid" value="${staffInfo.staffUserid }"/>
		  	<input type="button" class="tsbtn2" value="确认修改" id="modifyStaff"/>
		  	<input type="button" class="tsbtn2" value="取消" id="cancel"/>
		  	</div>
		</div>
	</div>
	</div>
	
  </body>
</html>
