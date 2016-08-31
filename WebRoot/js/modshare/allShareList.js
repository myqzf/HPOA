$(document).ready(function(e){
	InitGrid();
});
//实现对DataGird控件的绑定操作
function InitGrid() {
	$('#shareList').datagrid({
		url:'/HPOA/share/getAllShareList.json', //指向后台的Action来获取当前菜单的信息的Json格式的数据
		title: '共享内容',
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
		sortName: 'shareTime',    //根据某个字段给easyUI排序
		sortOrder: 'desc',
		remoteSort: true,
		idField: 'shareid',
		columns: [[
			//{ field: 'ck', checkbox: true },   //选择
			{ title: '共享资料id', field: 'shareid',hidden:'true'},
			{ title: '共享资料标题', field: 'shareTitle',sortable:'true',width:'25%'},
			{ title: '所在公司', field: 'authorComp',sortable:'true',width:'15%'},
			{ title: '所在部门', field: 'authroDepart',sortable:'true',width:'15%'},
			{ title: '共享人', field: 'authorName',sortable:'true',width:'15%'},
			{ title: '共享时间', field: 'shareTime',sortable:'true',width:'15%'},
			{ title: '存储文件名', field: 'shareFileName',sortable:'false',hidden:'true'},
			{ title: '实际文件名', field: 'shareRealFileName',sortable:'false',hidden:'true'},
			{ title: '操作', field: 'no',formatter:formatOper,sortable:'false',width:'10%'},
		]],
		toolbar: [
		{
            id: 'btnReload',
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                //实现刷新栏目中的数据
                $("#shareList").datagrid("reload");
            }
		}],
		onDblClickRow: function (rowIndex, rowData) {
			$('#shareList').datagrid('uncheckAll');
			$('#shareList').datagrid('checkRow', rowIndex);
		}
	});
	//添加操作行
	function formatOper(val,row,index){
		var rtnstr="";
		rtnstr="<a href='javascript:void(0)' onclick='goShareDetail(\""+row.shareid+"\")'>查看</a>&nbsp;&nbsp;";
		rtnstr+="<a href='javascript:void(0)' onclick='down(\""+row.shareFileName+"\",\""+row.shareRealFileName+"\")'>下载</a>";
		return rtnstr;
	}
}
function goShareDetail(shareid){
	location.href="/HPOA/share/goShareDetail?shareid="+shareid;
}
function down(filename,realname){
	window.open("/HPOA/share/downShareFile.json?fileName="+filename+"&realFileName="+realname);
}