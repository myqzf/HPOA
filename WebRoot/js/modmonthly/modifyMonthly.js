$(document).ready(function() {
	
	fillDictMonth();
	
	 
});

/**
 * 填充下拉选项菜单的字典项--月份信息
 * 
 * @param json
 */
function fillDictMonth() {
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		url : "/HPOA/monthly/findDictMonth.json",
		success : findDictMonth,
		error : function() {
			alert("error");

		}
	});
}

/**
 * 成功
 * 填充下拉选项菜单的字典项--月份信息
 * @param json
 */
function findDictMonth(json) {
	var list=json;
	var myDate = new Date();
	month_max=myDate.getMonth()+1+"月";//当前月
	month_min=myDate.getMonth()+"月";//上一月
	//alert("month_max="+month_max+"month_min"+month_min);
	if(month_min == "0月"){
		month_min=12+"月";
	}
//	$("#month").append("<option value='"+month_max+"'>"+month_max+"</option>");
//	$("#month").append("<option value='"+month_min+"'>"+month_min+"</option>");
	
	 var monthscope = document.getElementById("monthscope").value;
	 for (var i = 0; i < list.length; i++) {	
			if(list[i].itemsId==monthscope){
				$("#month").append("<option value='"+list[i].itemsId+"'>"+list[i].itemsName+"</option>");
			}
		 }
	 for (var i = 0; i < list.length; i++) {	
		 if(list[i].itemsId!=monthscope){
			if(list[i].itemsName==month_max){
				$("#month").append("<option value='"+list[i].itemsId+"'>"+month_max+"</option>");
			}
		 }
	 }
	 for (var i = 0; i < list.length; i++) {	
		 if(list[i].itemsId!=monthscope){
				if(list[i].itemsName==month_min){
					$("#month").append("<option value='"+list[i].itemsId+"'>"+month_min+"</option>");
				} 
			 }
	 }
	
	 
//	var obj=document.getElementById("month");
//	var month = obj.options[obj.selectedIndex].text;
//	$("#month option[value='"+month+"']").remove(); //删除重复的option
//	 for (var i = 0; i < list.length; i++) {		
//			$("#month").append("<option value='"+list[i].itemsId+"'>"+list[i].itemsName+"</option>");
//		}
}

//加载完页面后执行
window.onload=function(){ 
	//alert("加载完了");
	
	} ;

/**
 * 根据monthsumid查询单个月报
 * 
 * @param json
 */
//function findEachMonthly() {
//	$.ajax({
//		async : false,
//		dataType : 'json',
//		type : 'post',
//		url : "/HPOA/monthly/findEachMonthly.json",
//		data:"monthsumid=" + monthsumid,	
//		success : sucfindEachMonthly,
//		error : function() {
//			alert("error");
//
//		}
//	});
//}

/**
 * 成功
 * 根据monthsumid查询单个月报
 * @param json
 */
//function sucfindEachMonthly(json) {
//	alert("success");
//	var list=json;
//	 for (var i = 0; i < list.length; i++) {		
//			$("#month").append("<option value='"+list[i].itemsId+"'>"+list[i].itemsName+"</option>");
//										
//		}
//}






/**
 * 保存修改的月报
 * 
 * @param 
 */
function savemodifyMonthly() {
	
	var monthsumid = document.getElementById("monthsumid").value;
	var title = document.getElementById("title").value;
	//var month = document.getElementById("month").value;
	var monthvalue = document.getElementById("month").value;
	var obj=document.getElementById("month");
	var month = obj.options[obj.selectedIndex].text;
	var content=UE.getEditor('editor').getContent();
//	alert("month！"+month);
//	alert("content！"+content);
	if(title==null ||title.trim()==""){
		alert("标题不能为空！");
		return false;
	}
	if(title.length>=50 ){
		alert("标题过长！");
		return false;
	}
	if(content.length==0){
		alert("请编辑后再进行保存！");
		return false;
	}
	var monthscope = document.getElementById("monthscope").value;
	//var backMonth = document.getElementById("sect").value  ;//
	//var backMonth = obj.options[obj.value=0].text;//获得值为0的option的文本
	//if(backMonth==month){
	if(monthvalue==monthscope){
		$.ajax({
			async : false,
			dataType:'json',
			data:{monthsumid : monthsumid,monthtitle: title,monthscope:monthvalue,monthcontent:content},
			type : 'post',
			url : "/HPOA/monthly/saveModifyMonthly",
			success : function(data) {	
				if(data==1){
					alert("修改成功！");
				}else{
					alert("修改失败！请稍候再试！");
				}
				
				
				//window.location.href='/HPOA/dictionary/godictictionZtree?headId='+headId;
				window.location.href='/HPOA/monthly/goEachMonthly';
				
			},
			error : function() {				
				alert("error");
			}
		});
	}else{
		  // 查询当月月报是否已提交
		  //是 便提示当月月报已提交
		$.ajax({
			async : false,
			dataType : 'json',
			type : 'post',
			url : "/HPOA/monthly/seachIfSubmitted",
			data:{monthscope:monthvalue},
//			success : sucseachwriteIfSubmitted,
			success :  function(data) {
				if(data==2){
					alert("此月月报已提交");
					window.location.href='/HPOA/monthly/goEachMonthly';
				} else if(data==3){
					alert("系统出错，请稍候再试！");
				}else{
					//提交修改的月报
					$.ajax({
					async : false,
					dataType:'json',
					data:{monthsumid : monthsumid,monthtitle: title,monthscope:monthvalue,monthcontent:content},
					type : 'post',
					url : "/HPOA/monthly/saveModifyMonthly",
					success : function(data) {	
						if(data==1){
							alert("修改成功！");
						}else{
							alert("修改失败！请稍候再试！");
						}
						
						//window.location.href='/HPOA/dictionary/godictictionZtree?headId='+headId;
						window.location.href='/HPOA/monthly/goEachMonthly';
						
					},
					error : function() {				
						alert("error");
					       }
				       });  
		        	} 
				
				
		        	},
			error : function() {
				alert("error");
		
			}
				});
			
		}
}

/**
 * 保存修改上传月报的标题、月份
 */
function savemodifyUploadMonthly(){

	
	var monthsumid = document.getElementById("monthsumid").value;
	var title = document.getElementById("title").value;
	//var month = document.getElementById("month").value;
	var monthvalue = document.getElementById("month").value;
	var obj=document.getElementById("month");
	var month = obj.options[obj.selectedIndex].text;
	if(title==null ||title.trim()==""){
		alert("标题不能为空！");
		return false;
	}
	if(title.length>=50 ){
		alert("标题过长！");
		return false;
	}
	
	var monthscope = document.getElementById("monthscope").value;
	//var backMonth = obj.options[obj.value=monthscope].text;//获得值为0的option的文本
	//var backMonth = obj.options[obj.value=0].text;//获得值为0的option的文本
	if(monthvalue==monthscope){
		$.ajax({
			async : false,
			dataType:'json',
			data:{monthsumid : monthsumid,monthtitle: title,monthscope:monthvalue},
			type : 'post',
			url : "/HPOA/monthly/saveModifyMonthly",
			success : function(data) {	
				if(data==1){
					alert("修改成功！");
				}else{
					alert("修改失败！请稍候再试！");
				}
				
				//window.location.href='/HPOA/dictionary/godictictionZtree?headId='+headId;
				window.location.href='/HPOA/monthly/goEachMonthly';
				
			},
			error : function() {				
				alert("error");
			}
		});
	}else{
		  // 查询当月月报是否已提交
		  //是 便提示当月月报已提交
		$.ajax({
			async : false,
			dataType : 'json',
			type : 'post',
			url : "/HPOA/monthly/seachIfSubmitted",
			data:{monthscope:monthvalue},
//			success : sucseachwriteIfSubmitted,
			success :  function(data) {
				if(data==2){
					alert("此月月报已提交");
					window.location.href='/HPOA/monthly/goEachMonthly';
				} else if(data==3){
					alert("系统出错，请稍候再试！");
				}else{
					//提交修改的月报
					$.ajax({
					async : false,
					dataType:'json',
					data:{monthsumid : monthsumid,monthtitle: title,monthscope:monthvalue},
					type : 'post',
					url : "/HPOA/monthly/saveModifyMonthly",
					success : function(data) {	
						if(data==1){
							alert("修改成功！");
						}else{
							alert("修改失败！请稍候再试！");
						}
						
						//window.location.href='/HPOA/dictionary/godictictionZtree?headId='+headId;
						window.location.href='/HPOA/monthly/goEachMonthly';
						
					},
					error : function() {				
						alert("error");
					       }
				       });  
		        	} 
				
		        	},
			error : function() {
				alert("error");
		
			}
				});
			
		}

	
}

