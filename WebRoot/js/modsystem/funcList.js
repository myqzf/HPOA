var zTreeObj;
if (!String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, '');
	};
}


// 初始化zTree
$(document).ready(function() {
	$.ajax({
		url : "/HPOA/system/getFuncList",
		type : "post",
		dataType : "json",
		success : initZtree,
		//加载完后展开所有节点
//		complete: function(XMLHttpRequest, textStatus){					
//			var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 			
//			treeObj.expandAll(true);			
//		},		
	});
});

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
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom,
		dblClickExpand : false
	},
	callback : {
		enable : true,
		onRemove : delNode,	
		beforeEditName : modifyNode,
		onClick : zTreeOnClick
	},
	edit : {
		enable : true,
		showRemoveBtn : setRemoveBtn,
		showRenameBtn : true,
		removeTitle : "删除",
		renameTitle : "修改"
	}
};

//展开/关闭节点
function zTreeOnClick(event, treeId, treeNode) {
	
		
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getSelectedNodes();
			
	if (nodes.length > 0) {
		treeObj.expandNode(nodes[0]);
	}

}

//有子菜单的不显示删除符号
function setRemoveBtn(treeId, treeNode) {
	return !treeNode.isParent;
}

// 自定义按钮（添加和查看元素）
function addHoverDom(treeId, treeNode) {
	var aObj = $("#" + treeNode.tId + "_a");

	if ($("#addBtn_" + treeNode.tId).length > 0)
		return;
	var editStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='添加' onfocus='this.blur();'></span>";

	aObj.append(editStr);
	var btn = $("#addBtn_" + treeNode.tId);
	if (btn)
		btn.bind("click", function() {
			addNode(treeId, treeNode);
		});
 
};

//用于当鼠标移出节点时，隐藏用户自定义控件，显示隐藏状态同 zTree 内部的编辑、删除按钮
//与 addHoverDom 同时使用
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.tId).unbind().remove();
	
};

// 增加节点
function addNode(treeId, treeNode) {
		if (treeNode.isLeaf == '1') {
		alert("叶子节点无法添加子功能！");
		return false;
	}
	window.location.href="/HPOA/system/toAddFunc?funcPid="+treeNode.funcId;
}

// 修改节点
function modifyNode(treeId, treeNode) {
	window.location.href="/HPOA/system/toModifyFuncInfo?funcId="+treeNode.funcId; 
}

// 删除节点
function delNode(event, treeId, treeNode) {

	return artDialog({
		content : '确认要删除吗？',
		lock : true,
		style : 'succeed noClose'
	}, function() {
		var funcId = treeNode.funcId;
		$.post("/HPOA/system/deleteFuncById", {
			funcId : funcId
		}, function(data) {			
			if (data == 1) {
				alert("删除成功!");
				window.location.href='/HPOA/system/toFuncList';

			} else {
				alert("删除失败!");
			}
		});
	}, function() {
		window.location.href='/HPOA/system/toFuncList';
	});
}

//zTree 初始化方法
function initZtree(json) {	
	zTreeObj = $.fn.zTree.init($('#treeDemo'), setting, json);
	
	zTreeObj.expandAll(true);//默认加载完展开所有

}


