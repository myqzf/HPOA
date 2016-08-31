$(document).ready(function() {
	
	InitGrid();
	
var message =$("#message").val();
	
	if(message==1){
		return artDialog({
			 content:'添加成功',
		        lock:true,
		        style:'succeed noClose',
		        cancel:false//禁用关闭按钮
			    },
	     function () {
			    	if(window.attachEvent) {
						window.location.href="goMinuteslist";
					}else{
						  window.location.href="minutes/goMinuteslist";
					}
	     }
	 );			
	}else if(message==2){
		return artDialog({ 
			 content:'添加失败',
		        lock:true,
		        style:'succeed noClose',
		        cancel:false
			    },
	     function () {
			    	if(window.attachEvent) {
						window.location.href="goMinuteslist";
					}else{
						  window.location.href="minutes/goMinuteslist";
					}
	     }
	 );			
	}else if(message==3){
		return artDialog({
			 content:'修改成功',
		        lock:true,
		        style:'succeed noClose',
		        cancel:false
			    },
	     function () {
			    	if(window.attachEvent) {
						window.location.href="goMinuteslist";
					}else{
						window.location.href="minutes/goMinuteslist";
					}	
	     }
	 );		
	}else if(message==4){
		return artDialog({
			 content:'修改失败',
		        lock:true,
		        style:'succeed noClose',
		        cancel:false
			    },
	     function () {
			    	if(window.attachEvent) {
						window.location.href="goMinuteslist";
					}else{
						  window.location.href="minutes/goMinuteslist";
					}
	     }
	 );			
	}
});

//实现对DataGird控件的绑定操作
function InitGrid() {
	$('#grid').datagrid({   
        url:'/HPOA/minutes/searchMinutesList', //指向后台的Action来获取当前菜单的信息的Json格式的数据
        title: '会议纪要列表',
        iconCls: 'icon-view',
      //  height: 300,
        width: 750,//function () { return document.body.clientWidth * 0.9 },
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        collapsible: true,
        pagination: true,
        pageSize: 2,
        pageList: [2,10,20],
        rownumbers: true,
        sortName: 'meetingdate',    //根据某个字段给easyUI排序
        sortOrder: 'desc',
        remoteSort: false,
        idField: 'cultureid',
      //  queryParams: queryData,  //异步查询的参数
        columns: [[
         //   { field: 'ck', checkbox: true },   //选择
             { title: '纪要ID', field: 'minutesid',hidden:'true', width: 200},
             { title: '会议日期', field: 'meetingdate', width: 150 },
             { title: '会议主题', field: 'meetingtheme', width: 250 },
             { title: '会议地点', field: 'meetingplace', width: 100 },
             { title: '添加人ID', field: 'staffId',hidden:'true', width: 80 },
             { title: '操作', field: '_operate',formatter:formatOper, width: 200,align:'center' }
        
        ]],
        toolbar: [{
            id: 'btnAdd',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                ShowAddDialog();//实现添加记录的页面
            }
        },
//        '-', {
//            id: 'btnEdit',
//            text: '修改',
//            iconCls: 'icon-edit',
//            handler: function () {
//                ShowEditOrViewDialog();//实现修改记录的方法
//            }
//        }, '-', {
//            id: 'btnDelete',
//            text: '删除',
//            iconCls: 'icon-remove',
//            handler: function () {
//                Delete();//实现直接删除数据的方法
//            }
//        }, '-', {
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
                $("#grid").datagrid("reload");
            }
        }],
        onDblClickRow: function (rowIndex, rowData) {
            $('#grid').datagrid('uncheckAll');
            $('#grid').datagrid('checkRow', rowIndex);
           // ShowEditOrViewDialog();
        },
		onLoadSuccess:function(data){  
	        $('.viewcls').linkbutton({text:'查看详情',plain:true});  
	        $('.editcls').linkbutton({text:'修改',plain:true}); 
	        $('.delcls').linkbutton({text:'删除',plain:true}); 
	    }  
	
    });
	//添加操作行
	function formatOper(val,row,index){  
		var returnstr = "";
		returnstr += '<a class="viewcls"  href="javascript:void(0);" onclick="viewMinutes('+index+')">查看详情</a>&nbsp;&nbsp;';  
		returnstr += '<a class="editcls"  href="javascript:void(0)" onclick="modifyMinutes('+index+')">修改</a>&nbsp;&nbsp;';  
		returnstr += '<a class="delcls"  href="javascript:void(0);" onclick="deleteMinutes(' + index+ ')">  删除</a>&nbsp;';
		return returnstr; 
	}  
}

	/**
	 * 跳转到添加页面
	 */
	function ShowAddDialog(){
		  
		    window.location.href="/HPOA/minutes/goAddMinutes";
		    
	}

	/**查看会议纪要详情
	 * @param index
	 */
	function viewMinutes(index){
		   $('#grid').datagrid('selectRow',index);// 关键在这里    
		    var row = $('#grid').datagrid('getSelected');  
		    
		    window.location.href="/HPOA/minutes/toviewMinutesInfo?minutesid="+row.minutesid+"";
		    
	}

	/**跳转到修改会议纪要页面
	 * @param index
	 */
	function modifyMinutes(index){
		   $('#grid').datagrid('selectRow',index);// 关键在这里  
		    var row = $('#grid').datagrid('getSelected');  
		    
	  window.location.href="/HPOA/minutes/tomodifyMinutesInfo?minutesid="+row.minutesid+"";
	}
	
	/**
	 * 删除会议纪要信息
	 */
	function deleteMinutes(index){
		  $('#grid').datagrid('selectRow',index);// 关键在这里  
		    var row = $('#grid').datagrid('getSelected');  
		    
		return artDialog({
			 content:'确认要删除吗？',
		        lock:true,
		        style:'succeed noClose'
			    },
	     function () {
	         $.post("/HPOA/minutes/deleteMinutesInfo",{minutesid:row.minutesid},function(data){
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
	
	



