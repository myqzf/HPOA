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
	
	 for (var i = 0; i < list.length; i++) {	
		if(list[i].itemsName==month_max){
			$("#month").append("<option value='"+list[i].itemsId+"'>"+month_max+"</option>");
		}
	 }
	 for (var i = 0; i < list.length; i++) {	
			if(list[i].itemsName==month_min){
				$("#month").append("<option value='"+list[i].itemsId+"'>"+month_min+"</option>");
			} 
		 }
	 
             //	向下拉选项菜单填充从字典表中查询出的月份信息
//	 for (var i = 0; i < list.length; i++) {		
//			$("#month").append("<option value='"+list[i].itemsId+"'>"+list[i].itemsName+"</option>");
//		}
}


/**
 * 保存撰写的月报
 * 
 * @param 
 */
function saveMonthly() {
	
	var title = document.getElementById("title").value;
	//var title=$("#title").val();
	var monthvalue = document.getElementById("month").value;
	var obj=document.getElementById("month");
	var month = obj.options[obj.selectedIndex].text;
//	var title=$("#month").val();
//	var content=editor.getContent();

	var content=editor.getContent();
	//alert("month！"+month);
	//alert("content！"+content); 
	
//	var myDate = new Date();
//	month_max=myDate.getMonth()+1+"月";//当前月
//	month_min=myDate.getMonth()+"月";//上一月
	
//	if(monthvalue=="0"){
//		alert("请选择所属月份");
//		return false;
//	}
//	if(month!=month_max || month!=month_min){
//		alert("对不起，只能提交本月或上月的月报！");
//		return false;
//	}
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
	
	  // 查询当月月报是否已提交
	  //是 便提示当月月报已提交
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		url : "/HPOA/monthly/seachIfSubmitted",
		data:{monthscope:monthvalue},
//		success : sucseachwriteIfSubmitted,
		success :  function(data) {
			if(data==2){
				alert("此月月报已提交");
				//window.location.href='/HPOA/monthly/goEachMonthly';
			} else if(data==3){
				alert("系统出错，请稍候再试！");
			} else{
				$.post(
						"/HPOA/monthly/saveMonthly",
						{	
							monthtitle:title,
							monthscope:monthvalue,
							monthcontent:content
											
						},
						function(data){				
							if(data=="1"){
							window.location.href='/HPOA/monthly/goEachMonthly';
					//			location.reload();     
							}
							             				
						});
			}

		},
		
		error : function() {
			alert("error");

		}
	});
	

}
 
/**
 * 点击上传禁用富文本编辑器
 */
function disableUeditor(){
	 document.getElementById("save_btn").disabled=true;//保存按钮禁用
	 editor.setDisabled('fullscreen');
	  disableBtn("enable");
	 
}
//灰掉工具栏按钮
function disableBtn(str) {
   var div = document.getElementById('btns');
   var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
   for (var i = 0, btn; btn = btns[i++];) {
       if (btn.id == str) {
      UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
       } else {
      btn.setAttribute("disabled", "true");
       }
   }
    }


