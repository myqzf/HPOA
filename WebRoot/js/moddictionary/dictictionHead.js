var zTreeObj;
if (!String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, '');
	};
}

// 初始化zTree
$(document).ready(function() {
	$.ajax({
		//url : "/HPOA/dictionary/findDictictionHead.json",
		url : "/HPOA/dictionary/findDictHeadAndItems.json",
		type : "post",
		dataType : "json",
		success : initZtree
	});
});

// 跳转到详细字典树页面
function zTreeOnClick(event, treeId, treeNode) {
	  window.location.href="/HPOA/dictionary/godictictionZtree?headId="+treeNode.headId;
}

//有子菜单的不显示删除符号
function setRemoveBtn(treeId, treeNode) {
	return !treeNode.isParent;
}

var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "idKey",
			pIdKey : "pIdKey",
		// rootPId: ""
		},
		key : {
			name : "name"
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
		//onClick : zTreeOnClick
	},
	edit : {
		enable : true,
		//showRemoveBtn : setRemoveBtn,
		showRenameBtn : true,
		removeTitle : "删除",
		renameTitle : "修改"
	}
	
};



//自定义按钮（添加和查看元素）
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

function removeHoverDom(treeId, treeNode) {
	 $("#addBtn_" + treeNode.tId).unbind().remove();
//	 $("#1addBtn_"+treeNode.tId).unbind().remove();
};





// 增加节点
function addNode(treeId, treeNode) {
	//window.location.href="/HPOA/dictionary/addDictionaryHead";
	var headId = treeNode.headId;
	var itemsId= treeNode.itemsId;
	if(itemsId==null){
		if (treeNode.pIdKey == null) {
			pIdKey=0;
		}
		window.location.href="/HPOA/dictionary/addDictionaryInfo?idKey="+treeNode.idKey+"&headId="+treeNode.headId+"&pIdKey="+pIdKey;
	}else{
		if (treeNode.ifleaf == '1') {
			alert("叶子节点无法添加子功能！");
			return false;
		}
		if (treeNode.pIdKey == null) {
			pIdKey=0;
		} else {
			pIdKey=treeNode.pIdKey;
		}
		window.location.href="/HPOA/dictionary/addDictionaryInfo?idKey="+treeNode.idKey+"&headId="+treeNode.headId+"&pIdKey="+pIdKey;
	}

}

// 修改节点
function modifyNode(treeId, treeNode) {
	if(treeNode.itemsId==null){
		window.location.href="/HPOA/dictionary/turnModifyDictionaryHeadInfo?headId="+treeNode.headId; 
	}else{
		window.location.href="/HPOA/dictionary/turnModifyDictInfo?itemsId="+treeNode.itemsId; 
	}
	
}

// 删除节点
function delNode(event, treeId, treeNode) {
	var headId = treeNode.headId;
	var itemsId= treeNode.itemsId;
	if(itemsId==null){
		return artDialog({
			content : '确认要删除吗？',
			lock : true,
			style : 'succeed noClose'
		}, function() {
			$.post("/HPOA/dictionary/deleteDictHeadById", {
				headId : headId
			}, function(data) {
				if (data == 1) {
					alert("删除成功!");
					window.location.href='/HPOA/dictionary/godictictionHead';

				} else {
					alert("删除失败!");
				}
			});
		}, function() {
			window.location.href='/HPOA/dictionary/godictictionHead';
		});
	}else{
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
					window.location.href='/HPOA/dictionary/godictictionHead';

				} else {
					alert("删除失败!");
				}
			});
		}, function() {
			window.location.href='/HPOA/dictionary/godictictionHead';
		});
	}
	
}
	


//查询是否包含子项
function searchIfItem(treeId, treeNode){
	$.ajax({
		async : false,
		dataType : 'json',
		type : 'post',
		url : "/HPOA/dictionary/searchIfItem",
		data:"headId=" + treeNode.headId,
		success : sucsearchIfItem( treeId, treeNode),
		error : function() {
			alert("error");

		}
	});
}

//查询是否包含子项返回标识符
function sucsearchIfItem(json) {
	//alert("treeNode.headId!"+treeNode.headId);
	if(json==2){
		alert("请先删除所属的字典项");
		return false;
	} else {
		return true;
	}
		
	 
}


function gnListConsol() {
	art.dialog.close(true);
}

function zTreeBeforeRemove(treeId, treeNode) {
	//searchIfItem(treeId, treeNode);//查询是否包含子项
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


function initZtree(json) {
	
	zTreeObj = $.fn.zTree.init($('#treeDemo'), setting, json);
	/*var sNodes = zTreeObj.getSelectedNodes();
	if (sNodes.length > 0) {
		var node = sNodes[0].getParentNode();
		ParentNode=node.getParentNode();
		zTreeObj.expandNode(ParentNode);
	}*/
	
	
}


