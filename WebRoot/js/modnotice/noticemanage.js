$(document).ready(function(e){
	//jqgrid();
	InitGrid();
});

function jqgrid() {
	$("#list").show();
	$("#noticeList").jqGrid({
		url : '/HPOA/notice/getGridNotice.json',
		datatype : "json",
		mtype : 'post',
		height : 400,
		width : 600,
		//autowidth : true,
		rownumbers : true,
		rowNum : 20,
		pgtext : "第{0}页 共{1}页",
		rowList : [ 20, 30, 40 ],
		pager : '#pager1',
		sortname : 'noticeTime',
		viewrecords : false,
		sortorder : "desc",
		caption : "&nbsp;&nbsp;&nbsp;&nbsp;公告列表",
		multiselect : true,
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
		colNames : ['公告id', '公告标题', '所属公司','发布时间','公告发布者','置顶','操作'],
		colModel : [  {
			name : 'noticeid',
			index : 'noticeid',
			width : 80,
			hidden : true,
			search : false
			
		}, {
			name : 'title',
			index : 'title',
			width : 80,
			hidden : false,
			search : false
		}, {
			name : 'comp',
			index : 'comp',
			width : 80,
			hidden : false,
			search : false
		},{
			name : 'noticeTime',
			index : 'noticeTime',
			width : 80,
			hidden : false,
			search : false
		},{
			name : 'author',
			index : 'author',
			hidden : false,
			search : false
		},{
			name : 'isTop',
			index : 'isTop',
			width : 80,
			hidden : true,
			search : false
		},{
			name : 'settop',
			index : 'settop',
			width : 80,
			align : 'center',
			formatter : setTopFunction,
			sortable : false,
			hidden : false,
			search : false
			}
		],
		loadComplete : function() {
			$("#noticeList").setGridWidth($(window).width() * 0.98);
		},
		onSelectAll : function() {
		},
		onSelectRow : function() {
		},
		onPaging : function() {
		},
		onSortCol : function() {
		},
		search : function() {
		}
	});

	// jqgrid列标题增加查询框
	//jQuery("#roleList").jqGrid('filterToolbar');

	$("#noticeList").jqGrid('navGrid', '#pager1', {
		del : true,
		add : false,
		edit : false,
		search : false,
		view : false,
		refresh : true,
		addtext : "增加",
		deltext : "删除",
		edittext : "编辑",
		searchtext : "查找",
		viewtext : "查看",
		refreshtext : "刷新",
		// alertcap: "Warning",
		alerttext : "请选择需要操作的数据行!",// 当未选中任何行而点击编辑、删除、查看按钮时，弹出的提示信息
		delfunc : function(){
			var selrow;
			selrow = jQuery("#noticeList").jqGrid('getGridParam', 'selarrrow');
			if (selrow==null||selrow==""){
				alert("请至少选择一条记录");
				return false;
			}else {
				var rowData = "";
				var alldelData = "";
				$.each(selrow, function(i, value) {
					rowData = jQuery("#noticeList").jqGrid("getRowData", value);
					alldelData += rowData.noticeid+",";
				});
				alldelData = alldelData.substring(0, alldelData.length-1);
				var r=confirm("您确定要删除公告吗？");
				if (r==true){
					$.post("notice/delNoteice.json",
			          	{	
			    		 	noticeid:alldelData
			          	},
			          	function(data,status){
			          		if (status=="success"){
			          			art.dialog(
			        				{title:"提示",content:data.flagmsg,style:"success",
			        					button:{name:"确定", callback:function(){
			        						window.location.reload();
			       						}},
			       						close:function(){
			       							window.location.reload();
		        						}
		        					}
			        			);
			          			
			          		}
			          	});
					
				}else{
					return false;
				}
			}
		},
	}, {}, {}, {}, {}, {});
}

//实现对DataGird控件的绑定操作
function InitGrid() {
	$('#noticeList').datagrid({
        url:'/HPOA/notice/getUiGridNotice.json', //指向后台的Action来获取当前菜单的信息的Json格式的数据
        title: '公告管理',
        iconCls: 'icon-view',
      //  height: 300,
        //width: 750,//function () { return document.body.clientWidth * 0.9 },
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        collapsible: true,
        pagination: true,
        pageSize: 5,
        pageList: [5,10,20],
        rownumbers: true,
        sortName: 'noticeTime',    //根据某个字段给easyUI排序
        sortOrder: 'desc',
        remoteSort: true,
        idField: 'noticeid',
      //  queryParams: queryData,  //异步查询的参数
        columns: [[
             { field: 'ck', checkbox: true },   //选择
             { title: '公告id', field: 'noticeid',hidden:'true'},
             { title: '公告标题', field: 'title',sortable:'true'},
             { title: '所属公司', field: 'comp',sortable:'true'},
             { title: '发布时间', field: 'noticeTime',sortable:'true'},
             { title: '发布者', field: 'author',sortable:'true'},
             { title: '置顶', field: 'isTop',hidden:'true'},
             { title: '操作', field: 'settop',formatter:formatOper,align:'center'}
        
        ]],
        toolbar: [{
            id: 'btnAdd',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                //ShowAddDialog();//实现添加记录的页面
            	location.href='/HPOA/notice/goCreateNotice';
            }
        },
//        '-', {
//            id: 'btnEdit',
//            text: '修改',
//            iconCls: 'icon-edit',
//            handler: function () {
//                ShowEditOrViewDialog();//实现修改记录的方法
//            }
//        }, 
        '-', {
            id: 'btnDelete',
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                //Delete();//实现直接删除数据的方法
            	var checkrow=$("#noticeList").datagrid("getSelections");
            	if(checkrow.length==0){
            		alert("请选择相应公告");
            		return false;
            	}
            	var r=confirm("您确定要删除公告吗？");
				if (r==true){
					var alldelData = "";
					for(var i=0;i<checkrow.length;i++){
						alldelData=alldelData+checkrow[i].noticeid+",";
					}
					alldelData = alldelData.substring(0, alldelData.length-1);
					$.post("notice/delNoteice.json",
			          	{	
			    		 	noticeid:alldelData
			          	},
			          	function(data,status){
			          		if (status=="success"){
			          			art.dialog(
			        				{title:"提示",content:data.flagmsg,style:"success",
			        					button:{name:"确定", callback:function(){
			        						window.location.reload();
			       						}},
			       						close:function(){
			       							window.location.reload();
		        						}
		        					}
			        			);
			          			
			          		}
			          	});
					
				}else{
					return false;
				}
            }
        }, 
//        '-', {
//            id: 'btnView',
//            text: '查看',
//            iconCls: 'icon-table',
//            handler: function () {
//                ShowEditOrViewDialog("view");//实现查看记录详细信息的方法
//            }
//        }, 
        '-', {
            id: 'btnReload',
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                //实现刷新栏目中的数据
                $("#noticeList").datagrid("reload");
            }
        }],
        onDblClickRow: function (rowIndex, rowData) {
            $('#noticeList').datagrid('uncheckAll');
            $('#noticeList').datagrid('checkRow', rowIndex);
           // ShowEditOrViewDialog();
        }
	
    });
	//添加操作行
	function formatOper(val,row,index){
		setTopLink="<a href='javascript:void(0);' onclick='";
		if(row.isTop=='1'){
			setTopLink+="canceltop(\""+row.noticeid+"\")'>取消置顶</a>&nbsp;&nbsp;";
		}else{
			setTopLink+="settop(\""+row.noticeid+"\")'>置顶</a>&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		setTopLink+="<a href='javascript:void(0);' onclick='eidtnotice(\""+row.noticeid+"\")'>修改公告</a>";
		return setTopLink;
	}
}

function setTopFunction(cellvalue, options, rowdata){
	setTopLink="<a href='javascript:void(0);' onclick='";
	if(rowdata.isTop=='1'){
		setTopLink+="canceltop(\""+rowdata.noticeid+"\")'>取消置顶</a>&nbsp;&nbsp;";
	}else{
		setTopLink+="settop(\""+rowdata.noticeid+"\")'>置顶</a>&nbsp;&nbsp;&nbsp;&nbsp;";
	}
	setTopLink+="<a href='javascript:void(0);' onclick='eidtnotice(\""+rowdata.noticeid+"\")'>修改公告</a>";
	return setTopLink;
}
function canceltop(noticeid){
	$.ajax({
		url : "notice/setTop.json",
		data : {noticeid:noticeid,stauts:'2'},
		type : "POST",
		success : function(data){
			art.dialog(
				{title:"提示",content:data.flagmsg,background:'#ccc',opacity: 0,lock:true,style:"success",
					button:{name:"确定", callback:function(){
						window.location.reload();
					}},
					close:function(){
						window.location.reload();
					}
				}
			);
		}
	});
}
function settop(noticeid){
	$.ajax({
		url : "notice/setTop.json",
		data : {noticeid:noticeid,stauts:'1'},
		type : "POST",
		success : function(data){
			art.dialog(
				{title:"提示",content:data.flagmsg,background:'#ccc',opacity: 0,lock:true,style:"success",
					button:{name:"确定", callback:function(){
						window.location.reload();
					}},
					close:function(){
						window.location.reload();
					}
				}
			);
		}
	});
}
function eidtnotice(noticeid){
	location.href="/HPOA/notice/goEditNotice?noticeid="+noticeid;
}