$(document).ready(function () {			
	jqgrid();		
 });

function jqgrid() {
	jQuery("#roleList").jqGrid({
		url: '/HPOA/system/searchRoleList',
		datatype: "json",
		mtype: 'POST',
		
		jsonReader: {
		    	root: 'gridDTOs',
		    	page: 'page',
		    	total: 'total',
		    	records: 'records',
		    	repeatitems: false
		    },
		
		    
	rowList: [5, 10, 15],
    sortname: 'roleId',
    sortable: true,
    viewrecords: true,
    multiselect: false,
	rownumbers: true,
    sortorder: 'desc',
    pager: 'pager',
    rowNum: 5,
    altclass: 'altRowsColour',
    width: 'auto',
    height: 'auto',
    caption: "角色管理",
		    
		    
		    
	colNames: ['roleId', '角色名称', '角色描述', 'bak1', 'bak2', '操作'],
    colModel: [
	    {
	      name: 'roleId',
    	  index: 'roleId',
    	  xmlmap: 'roleId',
    	  hidden : true,
    	  width: 40,
    	  align: "left",
    	  sortable: true },
	    { 
    	  name: 'roleName', 
    	  index: 'roleName', 
    	  xmlmap: 'roleName', 
    	  search : true,
    	  width: 100, 
    	  align: "center" },
	    {
    	  name: 'roleDesc', 
    	  index: 'roleDesc', 
    	  xmlmap: 'roleDesc', 
    	  width: 80, 
    	  align: "center" },
	    { 
    	  name: 'bak1', 
    	  index: 'bak1', 
    	  xmlmap: 'bak1', 
    	  hidden : true,
    	  width: 100, 
    	  align: "center", 
    	  search: true, 
    	  sortable: true },
	    { 
		  name: 'bak2', 
		  index: 'bak2', 
		  xmlmap: 'bak2', 
		  hidden : true,
		  width: 150, 
		  align: "center"},
	    { 
		  name: 'roleId', 
		  index: 'roleId', 
		  xmlmap: 'roleId',
		  search : false,
		  formatter : 
		  initnextlogicbutton,
		  sortable : false, 
		  width: 270,
		  align: "center"},
    ],

    }).trigger("reloadGrid");		
	
	// jqgrid列标题增加查询框
	jQuery("#roleList").jqGrid('filterToolbar');	
	
}
		
	
			
		
		
		//添加操作行
		function initnextlogicbutton(cellvalue, options, rowdata) {
			var returnstr = "";

			returnstr += "<a href='javascript:void(0);' onclick='modifyRole(\"" + rowdata.roleId
					+ "\");'>  修改</a>&nbsp;";
			returnstr += "<a href='javascript:void(0);' onclick='deleteRole(\"" + rowdata.roleId
			+ "\");'>  删除</a>&nbsp;"; 
			returnstr += "<a href='javascript:void(0);' onclick='disFunc(\"" + rowdata.roleId
			+ "\");'>  分配权限</a>&nbsp;&nbsp; ";
			returnstr += "<a href='javascript:void(0);' onclick='roleFunc(\"" + rowdata.roleId
			+ "\");'>  查看该角色权限</a>&nbsp;&nbsp; ";
			return returnstr;
		}
		
		
		
		/**
		 * 分配权限
		 */
		function disFunc(roleId){
			 window.location.href="/HPOA/system/toRoleFuncList?roleId="+roleId;
		}

		/**
		 * 查看该角色权限
		 */
		function roleFunc(roleId){
			 window.location.href="/HPOA/system/toFuncInfo?roleId="+roleId;
		}
		
		

		/**
		 * 跳转到修改角色信息页面
		 */
		function modifyRole(roleId){
			window.location.href="/HPOA/system/toModifyRole?roleId="+roleId;
		}


		/**
		 * 删除角色信息
		 */
		function deleteRole(roleId){
			return artDialog({
				 content:'一旦删除，此角色下的所有用户将会失去该角色拥有的权限！确认要删除吗？',
			        lock:true,
			        style:'succeed noClose'
				    },
		     function () {
		         $.post("/HPOA/system/deleteRole",{roleId:roleId},function(data){
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


		