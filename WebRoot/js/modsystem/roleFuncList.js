var zTreeObj;
if (!String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, '');
	};
}

// 初始化zTree
$(document).ready(function() {
	
	findDictictionZtree();
	
});

/**
 * 字典树查询请求后台
 */
function findDictictionZtree() {
	var roleId = document.getElementById("roleId").value;
	$.ajax({
		async : false,
		url : "/HPOA/system/getFuncInfo",
		type : "post",
		data:"roleId=" + roleId,		
		dataType : "json",
		success : initZtree,
		error : function() {		
		//	window.location.href="/HPOA/system/getRoleInfo";
		}
	});
}

//setting 配置
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "funcId",
			pIdKey : "funcPid",
		},
		key : {
			name : "funcName"
		}
	},
	view : {
		dblClickExpand : false
	},
	callback : {
		enable : true,
		onClick : zTreeOnClick
	},

};

//展开/关闭节点
function zTreeOnClick(event, treeId, treeNode) {	
	
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();	
	if (nodes.length > 0) {
		treeObj.expandNode(nodes[0]);
	}
	
//	if(!treeNode.isParent){
//		window.location.href='/HPOA/test.jsp';
//	}	
}

//zTree 初始化方法
function initZtree(json) {

	if(json.length==0){
		alert("此角色没有权限");
		window.history.go(-1);
	}
	
	zTreeObj = $.fn.zTree.init($('#treeDemo'), setting, json);
	
	
}
