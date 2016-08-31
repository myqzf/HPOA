$(document).ready(function(e){
	InitGrid();
});
//实现对DataGird控件的绑定操作
function InitGrid() {
	$('#allWorkList').datagrid({
		url:'/HPOA/workManage/getAllWorkList.json', //指向后台的Action来获取当前菜单的信息的Json格式的数据
        title: '工作办理情况查询',
        iconCls: 'icon-view',
      //  height: 300,
        //width: 750,//function () { return document.body.clientWidth * 0.9 },
        nowrap: true,
        autoRowHeight: false,
        singleSelect: true,
        striped: true,
        collapsible: true,
        pagination: true,
        pageSize: 5,
        pageList: [5,10,20],
        rownumbers: true,
        sortName: 'startTime',    //根据某个字段给easyUI排序
        sortOrder: 'asc',
        remoteSort: true,
        idField: 'assignid',
      //  queryParams: queryData,  //异步查询的参数
        columns: [[
             //{ field: 'ck', checkbox: true },   //选择
             { title: '工作id', field: 'assignid',hidden:'true'},
             { title: '标题', field: 'title',sortable:'true',width:'20%'},
             { title: '公司', field: 'compname',sortable:'true',width:'15%'},
             { title: '发布部门', field: 'orgname',sortable:'true',width:'10%'},
             { title: '进度(百分比)', field: 'percent',sortable:'true',width:'8%'},
             { title: '状态', field: 'status',sortable:'true',width:'8%'},
             { title: '发布时间', field: 'startTime',sortable:'true',width:'15%'},
             { title: '结束时间', field: 'endTime',sortable:'true',width:'15%'},
             { title: '操作', field: 'operate',formatter:formatOper,align:'center',width:'8%'}
        
        ]],
		toolbar: [
		{
            id: 'btnReload',
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                //实现刷新栏目中的数据
                $("#allWorkList").datagrid("reload");
            }
		}],
		onDblClickRow: function (rowIndex, rowData) {
			$('#allWorkList').datagrid('uncheckAll');
			$('#allWorkList').datagrid('checkRow', rowIndex);
		}
	});
	//添加操作行
	function formatOper(val,row,index){
		var rtnstr="<a href='javascript:void(0);' onclick='goReportDetail(\""+row.assignid+"\")'>查看</a>";
		return rtnstr;
	}
}
function goReportDetail(assignid){
	location.href="/HPOA/workManage/getWorkDetail?assignid="+assignid;
}