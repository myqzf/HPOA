$(document).ready(function(e){
	InitGrid();
});

//实现对DataGird控件的绑定操作
function InitGrid() {
	$('#itemList').datagrid({
        url:'/HPOA/reimburse/getUiGridItem.json', //指向后台的Action来获取当前菜单的信息的Json格式的数据
        title: '项目报销单',
        iconCls: 'icon-view',
        //height: 300,
        //width: 750,//function () { return document.body.clientWidth * 0.9 },
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        collapsible: true,
        pagination: true,
        pageSize: 5,
        pageList: [5,10,20],
        rownumbers: true,
        sortName: 'entryname',    //根据某个字段给easyUI排序
        sortOrder: 'desc',
        remoteSort: true,
        idField: 'reimburseid',
      //  queryParams: queryData,  //异步查询的参数
        columns: [[
             { field: 'ck', checkbox: true },   //选择
             { title: '报销单id', field: 'reimburseid',hidden:'true'},
             { title: '项目', field: 'entryname',sortable:'true'},
             { title: '日期', field: 'reidate',sortable:'true'},
             { title: '金额', field: 'reimoney',sortable:'true'},
             { title: '操作', field: 'settop',formatter:formatOper,align:'center'}
        
        ]],
        toolbar: [{
            id: 'btnAdd',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                //ShowAddDialog();//实现添加记录的页面
            	location.href='/HPOA/reimburse/goCreateItem';
            }
        },
        '-', {
            id: 'btnDelete',
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                //Delete();//实现直接删除数据的方法
            	var checkrow=$("#itemList").datagrid("getSelections");
            	if(checkrow.length==0){
            		alert("请选择相应报销单");
            		return false;
            	}
            	var r=confirm("您确定要删除报销单吗？");
				if (r==true){
					var alldelData = "";
					for(var i=0;i<checkrow.length;i++){
						alldelData=alldelData+checkrow[i].reimburseid+",";
					}
					alldelData = alldelData.substring(0, alldelData.length-1);
					$.post("reimburse/delItem.json",
			          	{	
						reimburseid:alldelData
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
        '-', {
            id: 'btnReload',
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                //实现刷新栏目中的数据
                $("#itemList").datagrid("reload");
            }
        }],
        onDblClickRow: function (rowIndex, rowData) {
            $('#itemList').datagrid('uncheckAll');
            $('#itemList').datagrid('checkRow', rowIndex);
           // ShowEditOrViewDialog();
        }
	
    });
	//添加操作行
	function formatOper(val,row,index){
		setTopLink="<a href='javascript:void(0);' onclick='";
		setTopLink+="findItem(\""+row.reimburseid+"\")'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;";
		/*setTopLink+="<a href='javascript:void(0);' onclick='eidtPurchase(\""+row.reimburseid+"\")'>修改</a>";*/
		return setTopLink;
	}
}

function findItem(reimburseid){
	location.href="/HPOA/reimburse/findItem?reimburseId="+reimburseid;
}

/*function eidtPurchase(reimburseid){
	location.href="/HPOA/reimburse/goEidtPurchase?reimburseid="+reimburseid;
}*/