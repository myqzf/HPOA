$(document).ready(function() {
	
	fillDictHead();
	
	 
});

/**
 * 填充下拉选项菜单的字典头信息
 * 
 * @param json
 */
function fillDictHead() {
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		url : "/HPOA/dictionary/findDictictionHead.json",
		success : sucfillDictHead,
		error : function() {
			alert("error");

		}
	});
}

/**
 * 成功
 * 填充下拉选项菜单的字典头信息
 * @param json
 */
function sucfillDictHead(json) {
	var list=json;
	 for (var i = 0; i < list.length; i++) {		
			$("#headId").append("<option value='"+list[i].headId+"'>"+list[i].headName+"</option>");
										
		}
}


/**
 * 添加一级字典项
 * 
 * @param 
 */
function addDictItems() {
	
	$("#messageDiv").hide();
	var itemsName = document.getElementById("itemsName").value;
	//var ifleaf=document.getElementById("ifleaf").value;
	var headId=document.getElementById("headId").value;
	if(itemsName==null ||itemsName.trim()==""){
		$("#message").html("字典项名称不能为空！");
		$("#messageDiv").show();
		//$("#itemsName").focus();
		return false;
	}
	if(itemsName.length>20){
		alert("你输入的字典项名称过长！");
		return false;
	}
	if(headId=="0"){
		alert("请选择所属字典头");
		return false;
	}
	
	$.ajax({
		async : false,
		dataType:'json',
		data:"itemsName=" + itemsName+"&headId="+headId,		
		type : 'post',
		url : "/HPOA/dictionary/addOneDictictionItems",
		success : function() {				
			alert("添加成功！");
			//window.location.href='/HPOA/dictionary/godictictionZtree?headId='+headId;
			window.location.href='/HPOA/dictionary/godictictionHead';
			
		},
		error : function() {				
			alert("error");
		}
	});
}


/**
 * 内部添加一级字典项
 * 
 * @param 
 */
function addInDictItems() {
	
	$("#messageDiv").hide();
	var itemsName = document.getElementById("itemsName").value;
	var itemsNumber=document.getElementById("itemsNumber").value;
	var bak1 = document.getElementById("bak1").value;
	var bak2=document.getElementById("bak2").value;
	var sort = document.getElementById("sort").value;
	var headId=document.getElementById("headId").value;
	if(sort==""){
		sort =0;
		}
	if(itemsName==null ||itemsName.trim()==""){
		$("#message").html("字典项名称不能为空！");
		$("#messageDiv").show();
		//$("#itemsName").focus();
		return false;
	}
	if(itemsName.length>20){
		alert("你输入的字典项名称过长！");
		return false;
	}
	$.ajax({
		async : false,
		dataType:'json',
		data:"itemsName=" + itemsName+"&itemsNumber="+itemsNumber+"&bak1="+bak1+
		      "&bak2="+bak2+"&sort="+sort+"&headId="+headId,		
		type : 'post',
		url : "/HPOA/dictionary/addOneDictictionItems",
		success : function() {				
			alert("添加成功！");
			window.location.href='/HPOA/dictionary/godictictionZtree?headId='+headId;
		},
		error : function() {				
			alert("error");
		}
	});
}
