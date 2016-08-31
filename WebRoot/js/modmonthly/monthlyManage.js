$(document).ready(function() {
	
	searchYearScope();
	searchMonthScope();
	jqgrid();	//
	 
	
});


/**
 * 填充下拉选项菜单  年份范围信息
 * 
 * @param json
 */
function searchYearScope() {
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		url : "/HPOA/monthlyManage/findYearScope.json",
		success : sucfindYearScope,
		error : function() {
			alert("error");

		}
	});
}

/**
 * 成功
 * 填充下拉选项菜单  年份范围信息
 * @param json
 */
function sucfindYearScope(data) {
	var datemin=data[0].bak1;
	var datemax=data[1].bak1;
	var yearmin=datemin.substr(0,4);
	var yearmax=datemax.substr(0,4);
	 for (var i = yearmax; i >= yearmin; i--) {		
			$("#year").append("<option value='"+i+"'>"+i+"</option>");
										
		}
}

/**
 * 填充下拉选项菜单  月份范围信息
 * 
 * @param json
 */
function searchMonthScope() {
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		url : "/HPOA/monthly/findDictMonth.json",
		success : sucfindMonthScope,
		error : function() {
			alert("error");

		}
	});
}

/**
 * 成功
 * 填充下拉选项菜单  月份范围信息
 * @param json
 */
function sucfindMonthScope(data) {
	var list=data;
	 var monthscope = document.getElementById("monthscope").value;
	 for (var i = 0; i < list.length; i++) {	
			if(list[i].itemsId==monthscope){
				$("#month").append("<option value='"+list[i].itemsId+"'>"+list[i].itemsName+"</option>");
			}
		 }
	 for (var i = 0; i < list.length; i++) {
		 if(list[i].itemsId!=monthscope){
			$("#month").append("<option value='"+list[i].itemsId+"'>"+list[i].itemsName+"</option>");
		   }							
		}
//	  var obj=document.getElementById("month");
//		var month = obj.options[obj.selectedIndex].text;
//		$("#month option[value='"+month+"']").remove(); //删除重复的option选项
}


	//加载jqgrid表格
	function jqgrid() {
		var year = document.getElementById("year").value;
		var monthvalue = document.getElementById("month").value;
		var obj=document.getElementById("month");
		var month = obj.options[obj.selectedIndex].text;
		jQuery("#list").jqGrid({
			url: '/HPOA/monthlyManage/searchCurrentMonthly',
			postData: {year:year,month:monthvalue},//内容直接赋值到url上 ，传递参数  。参数类型：{name1:value1…}
			datatype: "json",
			mtype: 'POST',
			
			jsonReader: {
		    	root: 'gridDTOs',
		    	page: 'page',       //当前页
		    	total: 'total',     //总页数
		    	records: 'records', //查询出的记录数
		    	repeatitems: false
		    },
		
	rowList: [5, 10, 15],
    sortname: 'monthsumid',   //排序列的名称，此参数会被传到后台
    sortable: true,       //是否可排序
    viewrecords: true,    //是否要显示总记录数
   // multiselect: true,    //定义是否可以多选
	rownumbers: true,     //如为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.
    sortorder: 'desc',    //排序顺序，升序或者降序（asc or desc）
    pgtext : "第{0}页 共{1}页", //页面信息，第一个值是当前页第二个值是总页数   
    pager: '#pager',       //导航栏对象，必须是一个有效的html元素，位置可以随意
    rowNum: 5,
   // altRows:true,  
    altclass: 'altRowsColour',//用来指定行显示的css，可以编辑自己的css文件，只有当altRows设为 ture时起作用
    width: 'auto',
    height: 'auto',
    caption: "已提交过的月报",
		    
 // 入参
	prmNames : {
		page : "page", // 表示请求页码的参数名称
		rows : "rows", // 表示请求行数的参数名称
		totalrows : "rowCount",// 表示需从Server得到总共多少行数据的参数名称
		order : "sord",// 表示采用的排序方式的参数名称
		search : "search",// 表示是否是搜索请求的参数名称
	},	
		
	  loadComplete: function (data) {//加载完jqgrid 返回的数据
		   var rowNum = parseInt($(this).getGridParam("records"), 10);  
	        if (rowNum <= 0) {  
	            alert("没有符合条件的记录！");  
	        }  
		  
          returndata(data);//赋值给 input

      },
//		gridComplete : function() {
//			var ids = jQuery("#list").jqGrid('getDataIDs');
//			for ( var i = 0; i < ids.length; i++) {
//			var cl = ids[i];
//			update = "<input type='button' value='修改' onclick='updateStu("
//			+ cl + ")'/>&nbsp;";
//			del = "<input type='button' value='删除' onclick='deleteStu("
//			+ cl + ")'/>&nbsp;";
//			view = "<input type='button' value='查看' onclick='viewStu("
//			+ cl + ")'/>";
//			jQuery("#list").jqGrid('setRowData',
//			ids[i], {
//			process : update + del + view
//			});
//			}
//			},
			colNames: ['monthsumid','员工姓名', '提交日期', '所属月份', '标题', '文件', '操作','staffId'],//列显示名称，是一个数组对象
		    colModel: [
			    {
			      name: 'monthsumid',  //列显示的名称
		    	  index: 'monthsumid', //传到服务器端用来排序用的列名称
		    	  hidden : true,   //在初始化表格时是否要隐藏此列
		    	  width: 40,
		    	  align: "left",   //对齐方式
		    	  sortable: true },//是否可以排序
	    	   { 
		    	  name: 'staffName', 
		    	  index: 'staffName', 
		    	  search : true,
		    	  width: 100, 
		    	  align: "center" },
			    { 
		    	  name: 'uploaddate', 
		    	  index: 'uploaddate', 
		    	  search : true,
		    	  width: 100, 
		    	  formatter:uploaddateFmatter,//格式化日期
		    	  align: "center" },
			    {
		    	  name: 'monthscope', 
		    	  index: 'monthscope', 
		    	  width: 80, 
		    	  align: "center" },
			    { 
		    	  name: 'monthtitle', 
		    	  index: 'monthtitle', 
		    	  width: 250, 
		    	  align: "center", 
		    	  search: true, 
		    	  sortable: true },
			    { 
				  name: 'sumlink', 
				  index: 'sumlink', 
				  width: 200, 
				  formatter : link,//查看月报
				  align: "center"},
			    { 
				  name: 'monthsumid', 
				  index: 'monthsumid', 
				 // xmlmap: 'roleId',
				  search : false,
				  formatter : 
				  initnextlogicbutton,
				  sortable : false, 
				  width: 270,
				  hidden : true,
				  align: "center"},
			    { 
				  name: 'staffId', 
				  index: 'staffId', 
				  hidden : true,
				  width: 100, 
				  align: "center"},
		    ],

		    }).trigger("reloadGrid");		
		
	}
			
			
	//添加操作行
	function initnextlogicbutton(cellvalue, options, rowdata) {
		var returnstr = "";
		
		if(rowdata.sumlink==null){
			returnstr += "<a class='disable_a' href='javascript:void(0);' onclick='modifyMonthly(\"" +  rowdata.monthsumid
			+ "\");'>  修改</a>&nbsp;";
		
		}else{
			returnstr += "<a class='disable_a href='javascript:void(0);' onclick='modifyUploadMonthly(\"" +  rowdata.monthsumid
			+ "\");'>  修改</a>&nbsp;";
		}
		
		
		returnstr += "<a href='javascript:void(0);' onclick='deleteMonthly(\"" + rowdata.monthsumid 
		        + "\");'>  删除</a>&nbsp;"; 
		
		return returnstr;
	}
			
			
	//查看
	function link(cellvalue, options, rowdata) {
		var returnstr = "";
		
		if(rowdata.sumlink==null){
			returnstr += "<a href='javascript:void(0);' onclick='viewMonthly(\"" + rowdata.monthsumid
			+ "\");'>  查看撰写文件</a>&nbsp;";
		}else{
			attachmenturl=rowdata.sumlink;
			ss = "monthly/monthlyDownload.do?fileName=" + attachmenturl;
			url = encodeURI(ss);
			url = encodeURI(url);
			//returnstr += "<a href="+ rowdata.sumlink +">  查看上传文件</a>&nbsp;";
			returnstr += "<a href='#' style='text-decoration: none;' onclick='checkAttachment(\""+rowdata.sumlink+"\"); return false;'>查看上传文件</a> ";

		}
		
		return returnstr;
	}
			
	
	/**
	 * 附件下载前检查文件是否存在
	 * @param json
	 */
	function checkAttachment(attachmenturl){
		$.ajax({
			async : false,
			dataType : 'json',
			type : 'post',
			url : "/HPOA/monthly/checkMonthlyAttachment",
			data:{fileName : attachmenturl},
			//success : sucCheckAttachment,
			success : function(list) {
				if(list==true){
					ss = "monthly/monthlyDownload.do?fileName=" + attachmenturl;
					url = encodeURI(ss);
					url = encodeURI(url);
					location.href=url;
				}else{
					art.dialog.alert("文件已经不存在！");
					//return false;
				}

			},
			error : function() {
				alert("error");

			}
		});
	}

	/**
	 * 成功
	 * 检查文件是否存在  未用
	 * @param json
	 */
	function sucCheckAttachment(json) {
		var list=json;
		if(list==true){
			ss = "monthly/monthlyDownload.do?fileName=" + attachmenturl;
			url = encodeURI(ss);
			url = encodeURI(url);
			location.href=url;
		}else{
			art.dialog.alert("文件已经不存在！");
			//return false;
		}
	}
	
	
	/**
	 * 撰写的
	 * 跳转到查看月报页面
	 */
	function viewMonthly(monthsumid){
		window.location.href="/HPOA/monthly/toViewMonthly?monthsumid="+monthsumid;
	}
	
	/**
	 * 撰写的
	 * 跳转到修改月报页面
	 */
	function modifyMonthly(monthsumid){
		window.location.href="/HPOA/monthly/toModifyMonthly?monthsumid="+monthsumid;
	}

	/**
	 * 上传的的
	 * 提示不能修改
	 */
	function modifyUploadMonthly(monthsumid){
		
			alert("此为上传文件，请在本地修改后，重新上传!");
	}


	/**
	 * 删除月报信息
	 */
	function deleteMonthly(monthsumid){
		return artDialog({
			 content:'确认要删除吗？',
		        lock:true,
		        style:'succeed noClose'
			    },
	     function () {
	         $.post("/HPOA/monthly/deleteMonthly",{monthsumid:monthsumid},function(data){
	         	if(data=="1"){
	         		art.dialog.alert("删除成功！");
	         	}else{
	         		art.dialog.alert("删除失败，请稍候再试!");
	         	}
	         	location.reload();
	     	});
	     },
	     function(){
	     	location.reload();
	     }
	 );			
	}
	
	
	/**
	 * 格式化日期
	 */
	function uploaddateFmatter (cellvalue, options, rowObject)  {  
		var returnstr = "";
		returnstr=cellvalue;
		var date=returnstr.substr(0,10); 

	   return date;  

	}  

	
	/**
	 * 查询月报
	 */
	function queryManthly()  {  
		$("#gbox_list").remove();
		$("#jqtable").append("<table id='list'  class='scroll' cellpadding='0' " +
				"cellspacing='0'></table><div id='pager' class='scroll'></div>");
		jqgrid();
	}  

	/**
	 * 返回的数据
	 */
	function returndata(data)  {  
		document.getElementById("isSubmit").value=data.records+"份";//已提交
		// input.value=data.records+"份";//已提交
	   var staffnumber= data.staffnumber;
	   var notSubmit=staffnumber-data.records;
	  //  input=top.document.getElementsByName("notSubmit");
	  //  input[0].value=notSubmit+"份";//未提交
	    document.getElementById("notSubmit").value=notSubmit+"份";//未提交
	}
		
	
	/**
	 * 查看未提交人员
	 */
//	function queryNotSubmit()  { 
//		$("#jqtable_boxy").remove();
//		//$("#gbox_notSubmitlist").remove();
//		$("#jqtable_notSubmit_div").append(" <div class='jqtable_boxy' style='display:none;' id='jqtable_boxy'><table id='notSubmitlist'  class='scroll' cellpadding='0' " +
//				"cellspacing='0'></table><div id='pager1' class='scroll'></div></div>");
//		
//			   var d=artDialog({
//                   title:'未提交人员',
//                   lock:true,
//                   content:document.getElementById("jqtable_boxy"),
//                  // height:30,
//                     }); 
//                   d.show();
//                   jqgridNotSubmit();
//		$("#cancer").bind("click",function(){
//		                    d.close();
//		                    });
//     
//	}  

	/**
	 * 查看未提交人员
	 */
	 function queryNotSubmit(){
	    	art.dialog.open("/HPOA/monthlyManage/queryNotSubmit",{
				width:560,
			    height:250,
			    title:"未提交人员",
			    background: '#ccc', // 背景色
			    opacity: 0.50,	// 透明度
			    lock:true
			});    	
	    }
	
	
	
	
	//加载完页面后执行
	window.onload=function(){ 

		//alert("加载完了");
		} ;
