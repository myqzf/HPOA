$(document).ready(function() {
	
	InitGrid();
	
});

//实现对DataGird控件的绑定操作
function InitGrid() {
	$('#grid').datagrid({   
        url:'/HPOA/culture/searchCultureList', //指向后台的Action来获取当前菜单的信息的Json格式的数据
        title: '企业文化列表',
        iconCls: 'icon-view',
      //  height: 300,
        width: 600,//function () { return document.body.clientWidth * 0.9 },
        nowrap: true,
        autoRowHeight: false,
        striped: true,
        collapsible: true,
        pagination: true,
        pageSize: 5,
        pageList: [5,10,20],
        rownumbers: true,
        sortName: 'releasedate',    //根据某个字段给easyUI排序
        sortOrder: 'desc',
        remoteSort: false,
        idField: 'cultureid',
      //  queryParams: queryData,  //异步查询的参数
        columns: [[
 //           { field: 'ck', checkbox: true },   //选择
             { title: '文化ID', field: 'cultureid',hidden:'true', width: 200},
             { title: '标题', field: 'culturetitle', width: 150 ,hight:40},
             { title: '发布时间', field: 'releasedate', width: 150 },
             { title: '员工ID', field: 'staffId',hidden:'true', width: 80 },
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
            ShowEditOrViewDialog();
        },
	
		onLoadSuccess:function(data){  
	        $('.viewcls').linkbutton({text:'查看内容',plain:true});  
	        $('.editcls').linkbutton({text:'修改',plain:true}); 
	        $('.delcls').linkbutton({text:'删除',plain:true}); 
	       // $('.delcls').linkbutton({text:'删除',plain:true,iconCls:'icon-remove'}); 
	    }  
    });
	//添加操作行
	function formatOper(val,row,index){  
		var returnstr = "";
		returnstr += '<a class="viewcls" onclick="viewCulture('+index+')" href="javascript:void(0)">查看内容</a>&nbsp;&nbsp;';  
		//returnstr += '<a href="javascript:void(0);" onclick="viewCulture('+index+')">查看内容</a>&nbsp;&nbsp;';  
		returnstr += '<a class="editcls"  href="javascript:void(0)" onclick="modifyCulture('+index+')">修改</a>&nbsp;&nbsp;';  
		returnstr += '<a class="delcls"  href="javascript:void(0)" onclick="deleteCulture(' + index+ ')"> 删除</a>&nbsp;'; 
		return returnstr;
	}  
}


	/**查看文化内容
	 * @param index
	 */
	function viewCulture(index){
		   $('#grid').datagrid('selectRow',index);// 关键在这里  
		    var row = $('#grid').datagrid('getSelected');  
		    
		    art.dialog.open("/HPOA/culture/toviewCultureContent?cultureid="+row.cultureid+"",{
				width:830,
			    height:500,
			    title:row.culturetitle,
			    background: '#ccc', // 背景色
			    opacity: 0.50,	// 透明度
			    lock:true
			});    	
		    
	}

	/**跳转到添加企业文化信息页面
	 */
	function ShowAddDialog(){
		
	  window.location.href="/HPOA/culture/goAddCulture";
	}
	
	/**跳转到修改企业文化信息页面
	 * @param index
	 */
	function modifyCulture(index){
		   $('#grid').datagrid('selectRow',index);// 关键在这里  
		    var row = $('#grid').datagrid('getSelected');  
		    
	  window.location.href="/HPOA/culture/tomodifyCultureContent?cultureid="+row.cultureid;
	}
	
	/**
	 * 删除企业文化信息
	 */
	function deleteCulture(index){
		  $('#grid').datagrid('selectRow',index);// 关键在这里  
		    var row = $('#grid').datagrid('getSelected');  
		    
		return artDialog({
			 content:'确认要删除吗？',
		        lock:true,
		        style:'succeed noClose'
			    },
	     function () {
	         $.post("/HPOA/culture/deleteCultureInfo",{cultureid:row.cultureid},function(data){
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
	
	
	//加载完页面后执行
	window.onload=function(){ 

		$('#btn').linkbutton({
		    plain:true
		});
		//$('#btn').linkbutton('disable');    //禁用按钮
		$('#btn').linkbutton('enable');     //启用按钮

		} ;


