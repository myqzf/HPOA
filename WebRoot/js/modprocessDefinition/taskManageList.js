$(document).ready(function() {
	
	InitGrid();
	
var message =$("#message").val();
	
	if(message==1){
		return artDialog({
			 content:'部署成功',
		        lock:true,
		        style:'succeed noClose',
		        cancel:false//禁用关闭按钮
			    },
	     function () {
			    	//if(window.attachEvent) {
						window.location.href="goProcessDefinitionlist";
					//}else{
					//	  window.location.href="process/goProcessDefinitionlist";
					//}
	     }
	 );			
	}if(message==2){
		return artDialog({ 
			 content:'部署失败',
		        lock:true,
		        style:'succeed noClose',
		        cancel:false
			    },
	     function () {
			    	//if(window.attachEvent) {
						window.location.href="goProcessDefinitionlist";
					//}else{
					//	  window.location.href="process/goProcessDefinitionlist";
					//}
	     }
	 );	

};
});
//实现对DataGird控件的绑定操作
function InitGrid() {
	$('#grid').datagrid({   
        url:'/HPOA/reimburse/listTask.json', //指向后台的Action来获取当前菜单的信息的Json格式的数据
        title: '个人任务管理列表',
        iconCls: 'icon-view',
      //  height: 300,
        width: 750,//function () { return document.body.clientWidth * 0.9 },
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        collapsible: true,
        pagination: true,//分页
        pageSize: 2,
        pageList: [2,10,20],
        rownumbers: true,
        sortName: 'id',    //根据某个字段给easyUI排序
        sortOrder: 'desc',
        remoteSort: false,
        idField: 'id',
      //  queryParams: queryData,  //异步查询的参数
        columns: [[
         //   { field: 'ck', checkbox: true },   //选择
             { title: '任务ID', field: 'id',hidden:'true', width: 200},
             { title: '任务名称', field: 'name', width: 150 },
             { title: '创建时间', field: 'createTime', width: 250 },
             { title: '办理人', field: 'assignee', width: 100 },
//             { title: '添加人ID', field: 'dgrmResourceName', width: 80 },
             { title: '操作', field: '_operate',formatter:formatOper, width: 200,align:'center' }
        
        ]],
        toolbar: [{
            id: 'btnAdd',
            text: '添加流程定义',
            iconCls: 'icon-add',
            handler: function () {
                ShowAddDialog();//实现添加记录的页面
            }
        },
        '-', {
            id: 'btnReload',
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                //实现刷新栏目中的数据
                $("#grid").datagrid("reload");
            }
        }],
        onDblClickRow: function (rowIndex, rowData) {
            $('#grid').datagrid('uncheckAll');
            $('#grid').datagrid('checkRow', rowIndex);
           // ShowEditOrViewDialog();
        },
		onLoadSuccess:function(data){  
	        $('.handleTtask').linkbutton({text:'办理任务',plain:true});  
	        $('.ViewCuFlowCh').linkbutton({text:'查看当前流程图',plain:true}); 
	    }  
	
    });
	//添加操作行
	function formatOper(val,row,index){                        
		var returnstr = "";
		returnstr += '<a class="handleTtask"  href="javascript:void(0);" onclick="handleTtask('+index+')">办理任务</a>&nbsp;&nbsp;';  
		returnstr += '<a class="ViewCuFlowCh"  href="javascript:void(0);" onclick="ViewCurrentFlowChart(' + index+ ')">  查看当前流程图</a>&nbsp;';
		return returnstr; 
	};  
}

	/**
	 * 跳转到添加页面
	 */
	function ShowAddDialog(){
		  
		    window.location.href="/HPOA/process/goAddProcessDefinition";
		    
	}
	
	/**
	 * 办理任务
	 */
	function handleTtask(index){
		 $('#grid').datagrid('selectRow',index);// 关键在这里    
		    var row = $('#grid').datagrid('getSelected');  
		taskId= row.id;   
		// window.location.href="/HPOA/process/goHandleTtask?taskId="row.id;
		// window.location.href="/HPOA/process/goHandleTtask?taskId="taskId;
		 window.location.href="/HPOA/process/goHandleTask?taskId="+row.id+"";
	    
}
	
	/**查看当前流程图
	 * @param index
	 */
	function ViewCurrentFlowChart(index){
		  window.location.href="/HPOA/process/goAddProcessDefinition";
		    
	};
	
	
	

	
	



