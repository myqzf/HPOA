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
        url:'/HPOA/process/searchProcessDefinitionlist', //指向后台的Action来获取当前菜单的信息的Json格式的数据
        title: '流程定义列表',
        iconCls: 'icon-view',
      //  height: 300,
        width: 750,//function () { return document.body.clientWidth * 0.9 },
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        collapsible: true,
       // pagination: true,//分页
      //  pageSize: 2,
     //   pageList: [2,10,20],
        rownumbers: true,
       // sortName: 'meetingdate',    //根据某个字段给easyUI排序
      //  sortOrder: 'desc',
        remoteSort: false,
        idField: 'id',
      //  queryParams: queryData,  //异步查询的参数
        columns: [[
         //   { field: 'ck', checkbox: true },   //选择
             { title: '流程定义ID', field: 'id',hidden:'true', width: 200},
             { title: '流程名称', field: 'key', width: 150 },
             { title: '最新版本', field: 'version', width: 250 },
             { title: '说明', field: 'description', width: 100 },
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
	        $('.viewcls').linkbutton({text:'查看流程图',plain:true});  
	        $('.delcls').linkbutton({text:'删除',plain:true}); 
	    }  
	
    });
	//添加操作行
	function formatOper(val,row,index){                        
		var returnstr = "";
		returnstr += '<a class="viewcls"  href="javascript:void(0);" onclick="showProcessImage('+index+')">查看流程图</a>&nbsp;&nbsp;';  
		returnstr += '<a class="delcls"  href="javascript:void(0);" onclick="deleteProcessDefinition(' + index+ ')">  删除</a>&nbsp;';
		return returnstr; 
	}  
}

	/**
	 * 跳转到添加页面
	 */
	function ShowAddDialog(){
		  
		    window.location.href="/HPOA/process/goAddProcessDefinition";
		    
	}

	/**查看流程定义图片
	 * @param index
	 */
	function showProcessImage(index){
		   $('#grid').datagrid('selectRow',index);// 关键在这里    
		    var row = $('#grid').datagrid('getSelected');  
		    
		   // window.location.href="/HPOA/minutes/toviewMinutesInfo?minutesid="+row.minutesid+"";
		     
			pdId = encodeURI(row.id);
	    	// alert("第一次URL编码：" + pdId);

	    	pdId = encodeURI(pdId);
	    	// alert("第二次URL编码：" + pdId);
	    	
            var url = "downloadProcessImage?id=" + pdId + "&t=" + new Date();
            window.showModalDialog(url, null, "dialogHeight:500px;dialogWidth:600px;resizable:yes");
		    
	}

	
	
	/**
	 * 删除流程定义
	 */
	function deleteProcessDefinition(index){
		  $('#grid').datagrid('selectRow',index);// 关键在这里  
		    var row = $('#grid').datagrid('getSelected');  
		    
		return artDialog({
			 content:'确认要删除吗？',
		        lock:true,
		        style:'succeed noClose'
			    },
	     function () {
	         $.post("/HPOA/process/deleteProcessDefinition",{key:row.key},function(data){
	         	if(data=="1"){
	         		art.dialog.alert("删除成功！");
	         	}else{
	         		art.dialog.alert("删除失败，请稍候再试!");
	         	}
	            window.location.href="/HPOA/process/goProcessDefinitionlist";
	         	//location.reload();
	     	});
	     },
	     function(){
	     	location.reload();
	     }
	 );			
	}
	



