$(document).ready(function(e){
	InitGrid();
});
//实现对DataGird控件的绑定操作
function InitGrid() {
	$('#reportList').datagrid({
		url:'/HPOA/responseWork/getReportList.json', //指向后台的Action来获取当前菜单的信息的Json格式的数据
		title: '收到的汇报',
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
		sortName: 'reportTime',    //根据某个字段给easyUI排序
		sortOrder: 'desc',
		remoteSort: true,
		idField: 'reportid',
		columns: [[
			//{ field: 'ck', checkbox: true },   //选择
			{ title: '汇报id', field: 'reportid',hidden:'true'},
			{ title: '标题', field: 'title',formatter:formatOper,sortable:'true',width:'30%'},
			{ title: '汇报部门', field: 'reporterOrg',sortable:'true',width:'23%'},
			{ title: '汇报人', field: 'reporterName',sortable:'true',width:'23%'},
			{ title: '汇报时间', field: 'reportTime',sortable:'true',width:'23%'},
		]],
		toolbar: [
		{
            id: 'btnReload',
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                //实现刷新栏目中的数据
                $("#reportList").datagrid("reload");
            }
		}],
		onDblClickRow: function (rowIndex, rowData) {
			$('#reportList').datagrid('uncheckAll');
			$('#reportList').datagrid('checkRow', rowIndex);
		}
	});
	//添加操作行
	function formatOper(val,row,index){
		var rtnstr="";
		rtnstr="<a href='javascript:void(0)' onclick='goReportDetail(\""+row.reportid+"\")'>"+val+"</a>";
		return rtnstr;
	}
}
function goReportDetail(reportid){
	location.href="/HPOA/responseWork/goReportDetail?reportid="+reportid;
}