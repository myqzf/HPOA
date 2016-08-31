$(document).ready(function(e){
	jqgrid();
	$(document).keydown(function(event){
		if(event.which==13){			
		$("#list").html('<table id="pfList" class="scroll" cellpadding="0" cellspacing="0"></table><div id="pager1" class="scroll"></div>');
		var staffName=encodeURIComponent(encodeURIComponent($("#staffName").val()));
		var staffAddress=encodeURIComponent(encodeURIComponent($("#staffAddress").val()));
		$("#pfList").jqGrid({
			url : "/HPOA/staffSearch/searchStaffList?staffName="+staffName+"&staffGender="+$("#staffGender").val()
				+"&staffMarry="+$("#staffMarry").val()+"&staffIdcard="+$("#staffIdcard").val()+"&staffQualid="+$("#staffQualid").val()
				+"&staffAddress="+staffAddress+"&staffDept="+$("#staffDept").val()+"&staffPosi="+$("#staffPosi").val()+"&staffComp="+$("#staffComp").val(),
				datatype : "json",
				mtype : 'post',
				height : 500,
				width : 1000,
				rownumbers : true,
				rowNum : 15,
				pgtext : "第{0}页 共{1}页",
				rowList : [ 15, 30, 50 ],
				pager : '#pager1',
				sortname : 'staffId',
				viewrecords : false,
				sortorder : "desc",
				caption : "&nbsp;&nbsp;&nbsp;&nbsp;员工信息",
				multiselect : false,
				
				// 入参
				prmNames : {
					page : "currentPage", // 表示请求页码的参数名称
					rows : "pageSize", // 表示请求行数的参数名称
					totalrows : "rowCount",
					order : "sord",
					search : "search",
				},
				jsonReader : {
					root : "gridData", // json中代表实际模型数据的入口
					records : "rowCount",
					total : "totalPage",
					page : "currentPage",
					repeatitems : false,
					cell : "cell",
					id : "id"
				},

				colNames:['员工id','姓名','性别', '婚否','身份证号','学历','电话','住址','隶属部门','隶属职位','隶属公司','操作'],
				colModel:[
	  			{name : 'staffId',
				index : 'staffId',
				width : 10,
				hidden : true,
				editable:false,
				search:false
				},{
				name : 'staffName',
				index : 'staffName',
				width :80,
				search:true
				
				}, {
				name : 'staffGender',
				index : 'staffGender',
				width : 30,
				hidden : false,
				search : false,
				},{
				name : 'staffMarry',
				index : 'staffMarry',
				width : 30,
				hidden : false,
				search : false,
				align : 'center'
				}, {				
				name : 'staffIdcard',
				index : 'staffIdcard',
				width : 100,
				hidden : false,
				search : false,
				}, {
				name : 'staffQualid',
				index : 'staffQualid',
				width : 30,
				hidden : false,
				search : false,
				},{
				name : 'staffPhone',
				index : 'staffPhone',
				width : 70,
				hidden : false,
				search : false,
				}, {
				name : 'staffAddress',
				index : 'staffAddress',
				width : 120,
				hidden : false,
				search : false,
				}, {
				name : 'staffDept',
				index : 'staffDept',
				width : 60,
				hidden : false,
				search : false,
				}, {
				name : 'staffPosi',
				index : 'staffPosi',
				width : 60,
				hidden : false,
				search : false,
				}, {
				name : 'staffComp',
				index : 'staffComp',
				width : 110,
				hidden : false,
				search : false,
				},{
				name : 'operating',
				index : 'operating',
				width : 110,
				align : 'center',
				formatter : operatFunction,
				sortable : false,
				hidden : false,
				search : false
				}
				],				
				onSelectAll : function() {
				},
				onSelectRow : function() {
				},
				onPaging : function() {
				},
				onSortCol : function() {
				},
				search : function() {
				},
				onSortCol:function(index,iCol,sortorder){
//					toExcel(index,sortorder);			
				},
				gridComplete : function() {
//					toExcel($("#pfList").getGridParam('sortname'),$("#pfList").getGridParam('sortorder'));
				},
		});
	}
	});
	$("#clearStaff").click(function(e){
		window.location.reload();
	})
	$("#searchStaff").click(function(e){		
		$("#list").html('<table id="pfList" class="scroll" cellpadding="0" cellspacing="0"></table><div id="pager1" class="scroll"></div>');
		var staffName=encodeURIComponent(encodeURIComponent($("#staffName").val()));
		var staffAddress=encodeURIComponent(encodeURIComponent($("#staffAddress").val()));
		//var staffPosi=encodeURIComponent(encodeURIComponent($("#staffPosi").val()));
		//var staffComp=encodeURIComponent(encodeURIComponent($("#staffComp").val()));
		$("#pfList").jqGrid({
			url : "/HPOA/staffSearch/searchStaffList?staffName="+staffName+"&staffGender="+$("#staffGender").val()
				+"&staffMarry="+$("#staffMarry").val()+"&staffIdcard="+$("#staffIdcard").val()+"&staffQualid="+$("#staffQualid").val()
				+"&staffAddress="+staffAddress+"&staffDept="+$("#staffDept").val()+"&staffPosi="+$("#staffPosi").val()+"&staffComp="+$("#staffComp").val(),
				datatype : "json",
				mtype : 'post',
				height : 500,
				width : 1000,
				rownumbers : true,
				rowNum : 15,
				pgtext : "第{0}页 共{1}页",
				rowList : [ 15, 30, 50 ],
				pager : '#pager1',
				sortname : 'staffId',
				viewrecords : false,
				sortorder : "desc",
				caption : "&nbsp;&nbsp;&nbsp;&nbsp;员工信息",
				multiselect : false,
				// 入参
				prmNames : {
					page : "currentPage", // 表示请求页码的参数名称
					rows : "pageSize", // 表示请求行数的参数名称
					totalrows : "rowCount",
					order : "sord",
					search : "search",
				},
				jsonReader : {
					root : "gridData", // json中代表实际模型数据的入口
					records : "rowCount",
					total : "totalPage",
					page : "currentPage",
					repeatitems : false,
					cell : "cell",
					id : "id"
				},
				colNames:['员工id','姓名','性别', '婚否','身份证号','学历','电话','住址','隶属部门','隶属职位','隶属公司','操作'],
				colModel:[
	  			{name : 'staffId',
				index : 'staffId',
				width : 10,
				hidden : true,
				editable:false,
				search:false
				},{
				name : 'staffName',
				index : 'staffName',
				width :80,
				search:true				
				}, {
				name : 'staffGender',
				index : 'staffGender',
				width : 30,
				hidden : false,
				search : false,
				},{
				name : 'staffMarry',
				index : 'staffMarry',
				width : 30,
				hidden : false,
				search : false,
				align : 'center'
				}, {				
				name : 'staffIdcard',
				index : 'staffIdcard',
				width : 100,
				hidden : false,
				search : false,
				}, {
				name : 'staffQualid',
				index : 'staffQualid',
				width : 30,
				hidden : false,
				search : false,
				},{
				name : 'staffPhone',
				index : 'staffPhone',
				width : 70,
				hidden : false,
				search : false,
				}, {
				name : 'staffAddress',
				index : 'staffAddress',
				width : 120,
				hidden : false,
				search : false,
				}, {
				name : 'staffDept',
				index : 'staffDept',
				width : 60,
				hidden : false,
				search : false,
				}, {
				name : 'staffPosi',
				index : 'staffPosi',
				width : 60,
				hidden : false,
				search : false,
				}, {
				name : 'staffComp',
				index : 'staffComp',
				width : 110,
				hidden : false,
				search : false,
				},{
				name : 'operating',
				index : 'operating',
				width : 110,
				align : 'center',
				formatter : operatFunction,
				sortable : false,
				hidden : false,
				search : false
				}
				],
				onSelectAll : function() {
				},
				onSelectRow : function() {
				},
				onPaging : function() {
				},
				onSortCol : function() {
				},
				search : function() {
				},
				onSortCol:function(index,iCol,sortorder){
//					toExcel(index,sortorder);				
				},
				gridComplete : function() {
//					toExcel($("#pfList").getGridParam('sortname'),$("#pfList").getGridParam('sortorder'));
				},
				
		});
	});	
});
function jqgrid() {
	$("#list").show();
	$("#pfList").jqGrid({
		url : '/HPOA/staffList/showStaffList',
		datatype : "json",
		mtype : 'post',
		height : 500,
		width : 1000,
		//autowidth : true,
		rownumbers : true,
		rowNum : 15,
		pgtext : "第{0}页 共{1}页",
		rowList : [ 15, 30, 50 ],
		pager : '#pager1',
		sortname : 'staffId',
		viewrecords : false,
		sortorder : "desc",
		caption : "&nbsp;&nbsp;&nbsp;&nbsp;员工信息",
		multiselect : false,
		// 入参
		prmNames : {
			page : "currentPage", // 表示请求页码的参数名称
			rows : "pageSize", // 表示请求行数的参数名称
			totalrows : "rowCount",
			order : "sord",
			search : "search",
		},
		jsonReader : {
			root : "gridData", // json中代表实际模型数据的入口
			records : "rowCount",
			total : "totalPage",
			page : "currentPage",
			repeatitems : false,
			cell : "cell",
			id : "id"
		},

		colNames:['staffId','姓名','性别', '婚否','身份证号','学历','电话','住址','隶属部门','隶属职位','隶属公司','操作'],
		colModel:[
		{name : 'staffId',
		index : 'staffId',
		width : 10,
		hidden : true,
		editable:false,
		search:false
		},{
		name : 'staffName',
		index : 'staffName',
		width :80,
		search:true
		
		}, {
		name : 'staffGender',
		index : 'staffGender',
		width : 30,
		hidden : false,
		search : false,
		},{
		name : 'staffMarry',
		index : 'staffMarry',
		width : 30,
		hidden : false,
		search : false,
		align : 'center'
		}, {
		name : 'staffIdcard',
		index : 'staffIdcard',
		width : 100,
		hidden : false,
		search : false,	
		}, {
		name : 'staffQualid',
		index : 'staffQualid',
		width : 30,
		hidden : false,
		search : false,
		},{
		name : 'staffPhone',
		index : 'staffPhone',
		width : 70,
		hidden : false,
		search : false,
		}, {
		name : 'staffAddress',
		index : 'staffAddress',
		width : 120,
		hidden : false,
		search : false,
		}, {
		name : 'staffDept',
		index : 'staffDept',
		width : 60,
		hidden : false,
		search : false,
		}, {
		name : 'staffPosi',
		index : 'staffPosi',
		width : 60,
		hidden : false,
		search : false,
		}, {
		name : 'staffComp',
		index : 'staffComp',
		width : 100,
		hidden : false,
		search : false,
		},{
			name : 'operating',
			index : 'operating',
			width : 110,
			align : 'center',
			formatter : operatFunction,
			sortable : false,
			hidden : false,
			search : false
		}
		],
		onSelectAll : function() {
		},
		onSelectRow : function() {
		},
		onPaging : function() {
		},
		onSortCol : function() {
		},
		search : function() {
		},
		onSortCol:function(index,iCol,sortorder){
//			toExcel(index,sortorder);				
		},
		gridComplete : function() {
//			toExcel($("#pfList").getGridParam('sortname'),$("#pfList").getGridParam('sortorder'));

		},
		
	});	
}
//填充操作项
function operatFunction(cellvalue, options, rowdata){
	var modi="<a href='javascript:void(0);' onclick='modifyStaff(\""+rowdata.staffId+"\")'>修改</a>&nbsp;&nbsp;";
	modi=modi+"<a href='javascript:void(0);' onclick='showDetail(\""+rowdata.staffId+"\")'>查看详情</a><br>";
	modi=modi+"<a href='javascript:void(0);' onclick='delStaff(\""+rowdata.staffId+"\")'>删除</a>&nbsp;&nbsp;";
	modi=modi+"<a href='javascript:void(0);' onclick='assignedAccount(\""+rowdata.staffId+"\")'>分配账号</a>";
	return modi;
}
//修改方法
function modifyStaff(staffId){
		window.location.href="/HPOA/staff/gotoModifyStaff?staffId="+staffId;
}
//删除方法
function delStaff(staffId){
//	var delflag=0;
	art.dialog.confirm('确定删除该员工？',
	function(){
		$.post(
			"/HPOA/staffManage/delStaff",
			{staffId:staffId},
			function(data){
				var message="";
				switch(data.result){
				case "0":{
					message = "系统出错";
					break;
				}
				case "1":{
					message ="删除成功";
					break;
				}
				case "2":{
					message = "删除失败";
					break;
				}
				case "3":{
					message = "员工ID为空";
					break;
				}
			}
				art.dialog({
					title:'删除消息',
					content:message,
					background:'#ccc',
					opacity: 0,
					lock:true,
					button: [{
						name:'确定',
						callback: function () {
							window.location.reload();
						}
					}]
				});
			}
		);
	},
	function(){
	});
}
//查看详细信息方法
function showDetail(staffId){
		location.href="/HPOA/staff/gotoStaffDetail?staffId="+staffId;
}
//分配账号方法
function assignedAccount(staffId){
	window.location.href="/HPOA/login/goManageUser?staffid="+staffId;
}
function toExcel(){
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		url : "/HPOA/staffExcel/toExcel",
		data:{
			staffName:$("#staffName").val(),
			staffIdcard:$("#staffIdcard").val(),
			staffGender:$("#staffGender").val(),
			staffQualid:$("#staffQualid").val(),
			staffMarry:$("#staffMarry").val(),
			staffAddress:$("#staffAddress").val(),
			staffDept:$("#staffDept").val(),
			staffPosi:$("#staffPosi").val(),
			staffComp:$("#staffComp").val(),	
			sidx:jQuery("#pfList").getGridParam('sortname'),//排序字段
			sord:jQuery("#pfList").getGridParam('sortorder'),//排序方式
		currentPage:0,
//			$(".ui-pg-input").val(),
		pageSize:0,
//			$(".ui-pg-selbox").val(),
			},
		success : function(data){
			var file=encodeURIComponent(encodeURIComponent(data.file));
//			window.location.href="/HPOA/staffExcel/downExcel?fileName="+data.file;
			$("#downExcel").attr("href","/HPOA/staffExcel/downExcel?fileName="+file);
		},
		error : function() {
			alert("error");

		}
	});
	

}
