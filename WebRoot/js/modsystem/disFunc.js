var  zTreeObj;
if(!String.prototype.trim) {
	  String.prototype.trim = function () {
	    return this.replace(/^\s+|\s+$/g,'');
	  };
	}

//初始化zTree
$(document).ready(function() {  
    $.ajax( {  
        url : "/HPOA/system/searchRoleFuncList?roleId="+$("#roleId").val(),  
        type : "post",  
        dataType : "json",  
        success : initZtree,        
        //加载完后展开所有节点
		complete: function(XMLHttpRequest, textStatus){			
			
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
			
			treeObj.expandAll(true);		
		}
    });  
}); 

//setting 配置
var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "funcId",
				pIdKey: "funcPid",
			},
			key:{
				name:"funcName"
			}
		},
		check:{
			enable: true,
			chkStyle: "checkbox",
			chkboxType : { "Y" : "ps", "n" : "s" }
		},
		view: {
			dblClickExpand: false
		},
		callback:{
			enable: true,
			onClick:zTreeOnClick
		} 
	};

//展开/关闭节点
function zTreeOnClick(event, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes(); 
	if (nodes.length>0) {
		treeObj.expandNode(nodes[0]);
	}
}


//zTree 初始化方法
function initZtree(json){
	 zTreeObj = $.fn.zTree.init($('#treeDemo'), setting, json); 
}

//保存角色权限树信息
function addRoleFunc(){
	var array =zTreeObj.getCheckedNodes(true);//获取选中的节点
	len = array.length;
	var roleId = $("#roleId").val();
	if(len==0){
		art.dialog.alert("请选择岗位");
		return false;
	}else{
		var funcArray = "";
		$.each(array, function(i, value) {
			funcArray += value.funcId + ",";
		});
		$("#funcArray").val(funcArray);
		 $.post("/HPOA/system/addRoleFunc",{roleId:roleId,funcArray:funcArray},function(data){
	         	if(data=="1"){
	         		window.history.go(-1);
	         	}else{
	         		art.dialog.alert("分配失败，请稍候再试!");
	         	}
	     	});
	}
}
 