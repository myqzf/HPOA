$(document).ready(function(e){
	InitGrid();
});
//实现对DataGird控件的绑定操作
function InitGrid() {
	$('#reimburseList').datagrid({
		url:'/HPOA/reimburse/getReimburseList.json', //指向后台的Action来获取当前菜单的信息的Json格式的数据
		title: '报销列表',
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
		sortName: 'reimname',    //根据某个字段给easyUI排序
		sortOrder: 'desc',
		remoteSort: true,
		idField: 'reimburseid',
		columns: [[
			//{ field: 'ck', checkbox: true },   //选择
			{ title: '报销id', field: 'reimburseid',hidden:'true'},
			{ title: '项目', field: 'reporterOrg',sortable:'true',width:'33%'},
			{ title: '日期', field: 'reporterName',sortable:'true',width:'33%'},
			{ title: '金额', field: 'reportTime',sortable:'true',width:'33%'},
		]],
		toolbar: [
		{
            id: 'btnReload',
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                //实现刷新栏目中的数据
                $("#reimburseList").datagrid("reload");
            }
		}],
		onDblClickRow: function (rowIndex, rowData) {
			$('#reimburseList').datagrid('uncheckAll');
			$('#reimburseList').datagrid('checkRow', rowIndex);
		}
	});
	//添加操作行
	function formatOper(val,row,index){
		var rtnstr="";
		rtnstr="<a href='javascript:void(0)' onclick='goReimburseDetail(\""+row.reimburseid+"\")'>"+val+"</a>";
		return rtnstr;
	}
}
function goReimburseDetail(reimburse){
	location.href="/HPOA/responseWork/goReimburseDetail?reimburseid="+reimburseid;
}