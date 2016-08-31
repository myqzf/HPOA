$(document).ready(function () {			
	jqgrid();		
//	editMonth();
	
 });


   // var PostData={year:2013,projectcode:"value"};
function jqgrid() {
	jQuery("#list").jqGrid({
	//	url: '/HPOA/system/searchRoleList',
	//	postData: PostData,//内容直接赋值到url上 ，传递参数  。参数类型：{name1:value1…}
		url: '/HPOA/monthly/searchEachMonthly',
		datatype: "json",
		mtype: 'POST',
		
		jsonReader: {
		    	root: 'gridDTOsEach',
		    	page: 'page',       //当前页
		    	total: 'total',     //总页数
		    	records: 'records', //查询出的记录数
		    	repeatitems: false
		    },
		
	rowList: [5, 10, 15],
    sortname: 'uploaddate',   //排序列的名称，此参数会被传到后台
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
		 // judge_month(data);
		  

     },
		 
//	gridComplete : function() {
//		var ids = jQuery("#list").jqGrid('getDataIDs');
//		for ( var i = 0; i < ids.length; i++) {
//		var cl = ids[i];
//		update = "<input type='button' value='修改' onclick='updateMonthly("
//		+ cl + ")'/>&nbsp;";
//		del = "<input type='button' value='删除' onclick='deleteStu("
//		+ cl + ")'/>&nbsp;";
//		view = "<input type='button' value='查看' onclick='viewStu("
//		+ cl + ")'/>";
//		jQuery("#list").jqGrid('setRowData',
//		ids[i], {
//	//	process : update + del + view
//		});
//		}
//		},
	colNames: ['monthsumid', '提交日期', '所属月份', '标题', '文件', '操作','staffId'],//列显示名称，是一个数组对象
    colModel: [
	    {
	      name: 'monthsumid',  //列显示的名称
    	  index: 'monthsumid', //传到服务器端用来排序用的列名称
    	  hidden : true,   //在初始化表格时是否要隐藏此列
    	  width: 20,
    	  align: "left",   //对齐方式
    	  sortable: true },//是否可以排序
	    { 
    	  name: 'uploaddate', 
    	  index: 'uploaddate', 
    	  search : true,
    	  width: 100, 
    	  formatter:uploaddateFmatter,
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
		  width: 150, 
		  formatter : 
	        link,
		  align: "center"},
	    { 
		  name: 'monthsumid', 
		  index: 'monthsumid', 
		 // xmlmap: 'roleId',
		  search : false,
		  formatter : 
		  initnextlogicbutton,
		  sortable : false, 
		  width: 250,
		  align: "center"},
	    { 
		  name: 'staffId', 
		  index: 'staffId', 
		  hidden : true,
		  width: 20, 
		  align: "center"},
    ],

    }).trigger("reloadGrid");		
	
	
	
	// jqgrid列标题增加查询框
	//$("#roleList").jqGrid('navGrid', '#pager',{edit:false,add:false,del:false});
//	$("#list").jqGrid("navGrid", "#pager", {  
//        addfunc : openDialog4Adding,    // (1) 点击添加按钮，则调用openDialog4Adding方法  
//     //   editfunc : openDialog4Updating, // (2) 点击编辑按钮，则调用openDialog4Updating方法  
//     //   delfunc : openDialog4Deleting,  // (3) 点击删除按钮，则调用openDialog4Deleting方法  
//        alerttext : "请选择需要操作的数据行!"  // (4) 当未选中任何行而点击编辑、删除、查看按钮时，弹出的提示信息  
//    }); 
	
	
	//配置模态对话框
//	$("#consoleDlg").dialog({  
//	    autoOpen: false,      
//	    modal: true,    // 设置对话框为模态（modal）对话框  
//	    resizable: true,      
//	    width: 480,  
//	    buttons: {  // 为对话框添加按钮  
//	        "取消": function() {$("#consoleDlg").dialog("close")},
////	        "创建": addContact,  
////	        "保存": updateContact,  
////	        "删除": deleteContact  
//	    }  
//	});  

	
	
	
}
		


		
	//添加操作行
	function initnextlogicbutton(cellvalue, options, rowdata) {
		var returnstr = "";
		
		$.ajax({
			async : false,
			dataType : 'json',
			type : 'post',
			url : "/HPOA/monthly/findLatestMonthly.json",
			success : function(list) {
				var myDate = new Date();
				monthscope=list[0].monthscope;//所属月份
				currentMonth=myDate.getMonth()+1+"月"; //获取当前月份
				lastMonth=myDate.getMonth()+"月";//获取上一月份
				if(lastMonth=="0月"){
					lastMonth=12+"月";
				}
				  
				//判断是最新的月报  && 是否是本月或上月的     
				if(list[0].monthsumid==rowdata.monthsumid &&  (monthscope == currentMonth||monthscope == lastMonth)){//判断是否包含最近两月份     
					if(rowdata.sumlink==null){
						returnstr += "<a id='disable_a' href='javascript:void(0);' onclick='modifyMonthly(\"" +  rowdata.monthsumid
						+ "\");'>  修改</a>&nbsp;";
					
					}else{
						returnstr += "<a class='disable_a' href='javascript:void(0);' onclick='modifyUploadMonthly(\"" +  rowdata.monthsumid
						+ "\");'>  修改</a>&nbsp;";
					}
					returnstr += "<a href='javascript:void(0);' onclick='deleteMonthly(\"" + rowdata.monthsumid 
			        + "\");'>  删除</a>&nbsp;"; 
				   //不是最新的月报
				}else{
					returnstr += "<a id='disable_a' href='javascript:void(0);' style='color: #868282; text-decoration: none;' >  修改</a>&nbsp;";
					returnstr += "<a href='javascript:void(0);' style='color: #868282; text-decoration: none;' >  删除</a>&nbsp;"; 
				}

			},
			error : function() {
				alert("error");

			}
		});
		
		
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
			//returnstr += "<a href='"+ url +"'>  查看上传文件</a>&nbsp;";  
	//		returnstr += "<a href='"+url+"' style='text-decoration: none;'>查看上传文件</a> ";
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
			//success : sucCheckAttachment(),
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
	
		//格式化日期
		function uploaddateFmatter (cellvalue, options, rowObject)  
		{  
			var returnstr = "";
			returnstr=cellvalue;
			var date=returnstr.substr(0,10); 
	
		   return date;  
	
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
		 * 上传的
		 * 提示不能修改内容
		 */
		function modifyUploadMonthly(monthsumid){

			return artDialog({
				 content:'此为上传文件，只能修改月份、标题信息!',
			        lock:true,
			        style:'succeed noClose'
				    },
		     function () {
				 window.location.href="/HPOA/monthly/toModifyUploadMonthly?monthsumid="+monthsumid;
		     },
		     function(){
		     	location.reload();
			     }
			 );			
			
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
		
		function updateMonthly(cl){
			window.location.href="/HPOA/monthly/toModifyMonthly?monthsumid="+rowdata.monthsumid;
		}
		
		//加载完页面后执行
//		window.onload=function(){ 
//			$('#disable_a').removeAttr('onclick');
//			//document.getElementById("disable_a").removeAttr('onclick');
//			 //document.getElementByClassName("disable_a").removeAttr("onclick");
//			//$(".disable_a").removeAttr("onclick");
//			alert("加载完了");
//			} ;
			
			/**
			 * 判断月份，若不是最新的月报
			 */
			function judge_month(data){
				$.ajax({
					async : false,
					dataType : 'json',
					type : 'post',
					url : "/HPOA/monthly/findLatestMonthly.json",
					success : function(list) {
						alert(list[0].monthsumid);
						alert(data.gridDTOs.length);
						alert("success");
						for (var i=0;i<data.gridDTOs.length;i++)
						if(list[0].monthsumid==data.gridDTOs[i].monthsumid){
							
						}

					},
					error : function() {
						alert("error");

					}
				});
				
				$('.disable_a').removeAttr('onclick');
				alert("fdgdf");
			}
		
			
			