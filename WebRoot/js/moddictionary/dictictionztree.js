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
	var headId = document.getElementById("headId").value;
	$.ajax({
		url : "/HPOA/dictionary/findDictictionZtree.json",
		type : "post",
		data:"headId=" + headId,		
		dataType : "json",
		success : initZtree,
		error : function() {
			alert("error");

		}
	});
}

// 展开/关闭节点
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

var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "itemsId",
			pIdKey : "pid",
		},
		key : {
			name : "itemsName"
		}
	},
	view : {
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom,
		dblClickExpand : false
	},
	callback : {
		enable : true,
		beforeRemove : zTreeBeforeRemove,
		onRemove : delNode,
		beforeRename : zTreeBeforeRename,
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


function zTreeBeforeRemove(treeId, treeNode) {
	if (treeNode.isParent) {
		art.dialog.alert("请先删除子节点之后，再删除本节点！");
		return false;
	} else {
		return true;
	}
}

function zTreeBeforeRename(treeId, treeNode, newName) {
	return true;
}


// 增加节点  跳转到添加页面
function addNode(treeId, treeNode) {
	if (treeNode.ifleaf == '1') {
		alert("叶子节点无法添加子功能！");
		return false;
	}
	if (treeNode.pid == null) {
		  pid=0;
	} else {
		pid=treeNode.pid;
	}
	window.location.href="/HPOA/dictionary/addDictionaryInfo?itemsId="+treeNode.itemsId+"&headId="+treeNode.headId+"&pid="+pid;
}

// 修改节点
function modifyNode(treeId, treeNode) {
	window.location.href="/HPOA/dictionary/turnModifyDictInfo?itemsId="+treeNode.itemsId; 
}

// 删除节点
function delNode(event, treeId, treeNode) {

	return artDialog({
		content : '确认要删除吗？',
		lock : true,
		style : 'succeed noClose'
	}, function() {
		var itemsId = treeNode.itemsId;
		$.post("/HPOA/dictionary/deleteDictById", {
			itemsId : itemsId
		}, function(data) {
			if (data == 1) {
				alert("删除成功!");
				window.location.href='/HPOA/dictionary/godictictionZtree?headId='+treeNode.headId;

			} else {
				alert("删除失败!");
			}
		});
	}, function() {
		window.location.href='/HPOA/dictionary/godictictionZtree?headId='+treeNode.headId;
	});
}

function gnListConsol() {
	art.dialog.close(true);
}

function initZtree(json) {
	if(json==""){
//		$("#message").html("暂无字典项名称！ "+"<a href='/HPOA/dictionary/goAddOneDictItem'>添加</a>");
//		$("#messageDiv").show();
	}
	zTreeObj = $.fn.zTree.init($('#treeDemo'), setting, json);
	
	zTreeObj.expandAll(true);//默认加载完展开所有
}
