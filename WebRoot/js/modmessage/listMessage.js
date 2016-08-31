$(document).ready(function(e){
	if($("#flag").val()==""){
		$("#flag").val("receivemsg");
	}
	$("#"+$("#flag").val()).addClass('selectTag');
	eval($("#flag").val()+"()");
	if($("#flag").val()=="receivemsg"){
		$("#tagContent0").css("display","block");
	}
	if($("#flag").val()=="sendmsg"){
		$("#tagContent1").css("display","block");
	}
	if($("#flag").val()=="nosendmsg"){
		$("#tagContent2").css("display","block");
	}
	$("#newMessage").click(function(e){
		if(window.attachEvent) {
			location.href='goMessage?flag=receivemsg';
		}else{
			location.href='message/goMessage?flag=receivemsg';
		}
	});
	$("#sendmsg").click(function(e){
		sendmsg();
	});
	$("#nosendmsg").click(function(e){
		nosendmsg();
	});
	$("#receivemsg").click(function(e){
		receivemsg();
	});
	$("#gotowrite").click(function(e){
		if(window.attachEvent) {
			location.href='goWriteNewMessage';
		}else{
			location.href='message/goWriteNewMessage';
		}
	});
});
function selectTag(showContent,selfObj){
	// 操作标签
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.parentNode.className = "selectTag";
	// 操作内容
	for(i=0; j=document.getElementById("tagContent"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
}
function sendmsg(){
	$("#sendlist").show();
	$("#sendmessageList").jqGrid({
		url : 'message/getSendMessage.json',
		datatype : "json",
		mtype : 'post',
		height : 500,
		rownumbers : true,
		rowNum : 15,
		pgtext : "第{0}页 共{1}页",
		rowList : [ 15, 30, 50 ],
		pager : '#sendpager1',
		sortname : 'messageid',
		viewrecords : false,
		sortorder : "desc",
		caption : "&nbsp;&nbsp;&nbsp;&nbsp;发件箱",
		multiselect : true,
		// 入参
		prmNames : {
			page : "currentPage", // 表示请求页码的参数名称
			rows : "pageSize", // 表示请求行数的参数名称
			totalrows : "rowCount",
			order : "sord",
			search : "search",
		},
		jsonReader : {
			root : "gridData", // json中代表实际模型数据的入口
			records : "rowCount",
			total : "totalPage",
			page : "currentPage",
			repeatitems : false,
			cell : "cell",
			id : "id"
		},

		colNames : [ '收件人', '标题', '日期', '短消息id', '短信主题'],
		colModel : [  {
			name : 'recevername',
			index : 'recevername',
			align : 'center',
			width : 300,
			hidden : false,
			search : false
		}, {
			name : 'mtitle',
			index : 'mtitle',
			width : 400,
			align : 'center',
			formatter : openSendFunction,
			sortable : false,
			hidden : false,
			search : false,
		}, {
			name : 'sendtime',
			index : 'sendtime',
			align : 'center',
			width : 300,
			hidden : false,
			search : false,
		},{
			name : 'messageid',
			index : 'messageid',
			hidden : true,
			search : false,
		},{
			name : 'title',
			index : 'title',
			hidden : true,
			search : false,
		}
		],
		loadComplete : function() {
			$("#sendmessageList").setGridWidth($(window).width() * 0.98);
		},
		onSelectAll : function() {
		},
		onSelectRow : function() {
		},
		onPaging : function() {
		},
		onSortCol : function() {
		},
		search : function() {
		}
	});
	$("#sendmessageList").jqGrid('navGrid', '#sendpager1', {
		del : true,
		add : false,
		edit : false,
		search : false,
		view : false,
		refresh : true,
		addtext : "增加",
		deltext : "删除",
		edittext : "编辑",
		searchtext : "查找",
		viewtext : "查看",
		refreshtext : "刷新",
		alerttext : "请选择需要操作的数据行!",
		delfunc : function(){
		var selrow;
		selrow = jQuery("#sendmessageList").jqGrid('getGridParam', 'selarrrow');
		if (selrow==null||selrow==""){
			alert("请至少选择一条记录");
			return false;
		}else {
			var rowData = "";
			var newData = "";
			$.each(selrow, function(i, value) {
				rowData = jQuery("#sendmessageList").jqGrid("getRowData", value);
				newData += rowData.messageid+",";
			});
			newData = newData.substring(0, newData.length-1);
			var r=confirm("您确定要删除已发信息吗？");
			if (r==true){
				$.post("message/delSendMessage.json",
		          	{	
		    		 	messageid:newData
		          	},
		          	function(data,status){
		          		if (status=="success"){
		          			art.dialog(
		        				{title:"提示",content:data.flagmsg,style:"success",
		        					button:{name:"确定", callback:function(){
		        						var curPage = $(".ui-pg-input").val();
					          			jQuery("#sendmessageList").jqGrid(
					     					'setGridParam',
					     					{
					     						url : 'message/getSendMessage.json',
					     						page : curPage
					     					}
					     				).trigger("reloadGrid");
		       						}},
		       						close:function(){
		       							var curPage = $(".ui-pg-input").val();
					          			jQuery("#sendmessageList").jqGrid(
					     					'setGridParam',
					     					{
					     						url : 'message/getSendMessage.json',
					     						page : curPage
					     					}
					     				).trigger("reloadGrid");
	        						}
	        					}
		        			);
		          			
		          		}
		          	});
				
			}else{
				return false;
			}
		}
	},
	}, {}, {}, {}, {}, {});
}
function nosendmsg(){
	$("#nosendlist").show();
	$("#nosendmessageList").jqGrid({
		url : 'message/getNoSendMessage.json',
		datatype : "json",
		mtype : 'post',
		height : 500,
		rownumbers : true,
		rowNum : 15,
		pgtext : "第{0}页 共{1}页",
		rowList : [ 15, 30, 50 ],
		pager : '#nosendpager1',
		sortname : 'status',
		viewrecords : false,
		sortorder : "desc",
		caption : "&nbsp;&nbsp;&nbsp;&nbsp;草稿箱",
		multiselect : true,
		// 入参
		prmNames : {
			page : "currentPage", // 表示请求页码的参数名称
			rows : "pageSize", // 表示请求行数的参数名称
			totalrows : "rowCount",
			order : "sord",
			search : "search",
		},
		jsonReader : {
			root : "gridData", // json中代表实际模型数据的入口
			records : "rowCount",
			total : "totalPage",
			page : "currentPage",
			repeatitems : false,
			cell : "cell",
			id : "id"
		},

		colNames : [ '收件人', '标题', '日期', '短消息id', '短信主题'],
		colModel : [  {
			name : 'receversName',
			index : 'receversName',
			align : 'center',
			width : 300,
			hidden : false,
			search : false
		}, {
			name : 'mtitle',
			index : 'mtitle',
			width : 400,
			align : 'center',
			formatter : openNoSendFunction,
			sortable : false,
			hidden : false,
			search : false,
		}, {
			name : 'createTime',
			index : 'createTime',
			align : 'center',
			width : 300,
			hidden : false,
			search : false,
		},{
			name : 'messageId',
			index : 'messageId',
			hidden : true,
			search : false,
		},{
			name : 'title',
			index : 'title',
			hidden : true,
			search : false,
		}
		],
		loadComplete : function() {
			$("#nosendmessageList").setGridWidth($(window).width() * 0.98);
		},
		onSelectAll : function() {
		},
		onSelectRow : function() {
		},
		onPaging : function() {
		},
		onSortCol : function() {
		},
		search : function() {
		}
	});
	$("#nosendmessageList").jqGrid('navGrid', '#nosendpager1', {
		del : true,
		add : false,
		edit : false,
		search : false,
		view : false,
		refresh : true,
		addtext : "增加",
		deltext : "删除",
		edittext : "编辑",
		searchtext : "查找",
		viewtext : "查看",
		refreshtext : "刷新",
		alerttext : "请选择需要操作的数据行!",
		delfunc : function(){
		var selrow;
		selrow = jQuery("#nosendmessageList").jqGrid('getGridParam', 'selarrrow');
		if (selrow==null||selrow==""){
			alert("请至少选择一条记录");
			return false;
		}else {
			var rowData = "";
			var newData = "";
			$.each(selrow, function(i, value) {
				rowData = jQuery("#nosendmessageList").jqGrid("getRowData", value);
				newData += rowData.messageId+",";
			});
			newData = newData.substring(0, newData.length-1);
			var r=confirm("您确定要删除草稿吗？");
			if (r==true){
				$.post("message/delNoSendMessage.json",
		          	{	
		    		 	messageid:newData
		          	},
		          	function(data,status){
		          		if (status=="success"){
		          			art.dialog(
		        				{title:"提示",content:data.flagmsg,style:"success",
		        					button:{name:"确定", callback:function(){
		        						var curPage = $(".ui-pg-input").val();
					          			jQuery("#nosendmessageList").jqGrid(
					     					'setGridParam',
					     					{
					     						url : 'message/getNoSendMessage.json',
					     						page : curPage,
					     					}
					     				).trigger("reloadGrid");
		       						}},
		       						close:function(){
		       							var curPage = $(".ui-pg-input").val();
					          			jQuery("#nosendmessageList").jqGrid(
					     					'setGridParam',
					     					{
					     						url : 'message/getNoSendMessage.json',
					     						page : curPage,
					     					}
					     				).trigger("reloadGrid");
	        						}
	        					}
		        			);
		          			
		          		}
		          	});
				
			}else{
				return false;
			}
		}
	},
	}, {}, {}, {}, {}, {});
}
function receivemsg(){
	$("#receivelist").show();
	$("#receivemessageList").jqGrid({
		url : 'message/getReceiveMessage.json',
		datatype : "json",
		mtype : 'post',
		height : 500,
		rownumbers : true,
		rowNum : 15,
		pgtext : "第{0}页 共{1}页",
		rowList : [ 15, 30, 50 ],
		pager : '#receivepager1',
		sortname : 'readstatus',
		viewrecords : false,
		sortorder : "desc",
		caption : "&nbsp;&nbsp;&nbsp;&nbsp;收件箱",
		multiselect : true,
		// 入参
		prmNames : {
			page : "currentPage", // 表示请求页码的参数名称
			rows : "pageSize", // 表示请求行数的参数名称
			totalrows : "rowCount",
			order : "sord",
			search : "search",
		},
		jsonReader : {
			root : "gridData", // json中代表实际模型数据的入口
			records : "rowCount",
			total : "totalPage",
			page : "currentPage",
			repeatitems : false,
			cell : "cell",
			id : "id"
		},

		colNames : [ '发件人', '标题', '日期', '短消息id', '阅读状态', '短信主题'],
		colModel : [  {
			name : 'senderName',
			index : 'senderName',
			align : 'center',
			width : 300,
			hidden : false,
			search : false
		}, {
			name : 'mtitle',
			index : 'mtitle',
			width : 400,
			align : 'center',
			formatter : openReceiveFunction,
			sortable : false,
			hidden : false,
			search : false,
		}, {
			name : 'rectime',
			index : 'rectime',
			align : 'center',
			width : 300,
			hidden : false,
			search : false,
		},{
			name : 'messageId',
			index : 'messageId',
			hidden : true,
			search : false,
		},{
			name : 'readstatus',
			index : 'readstatus',
			hidden : true,
			search : false,
		},{
			name : 'title',
			index : 'title',
			hidden : true,
			search : false,
		}
		],
		loadComplete : function() {
			$("#receivemessageList").setGridWidth($(window).width() * 0.98);
		},
		onSelectAll : function() {
		},
		onSelectRow : function() {
		},
		onPaging : function() {
		},
		onSortCol : function() {
		},
		search : function() {
		}
	});
	$("#receivemessageList").jqGrid('navGrid', '#receivepager1', {
		del : true,
		add : false,
		edit : false,
		search : false,
		view : false,
		refresh : true,
		addtext : "增加",
		deltext : "删除",
		edittext : "编辑",
		searchtext : "查找",
		viewtext : "查看",
		refreshtext : "刷新",
		alerttext : "请选择需要操作的数据行!",
		delfunc : function(){
			var selrow;
			selrow = jQuery("#receivemessageList").jqGrid('getGridParam', 'selarrrow');
			if (selrow==null||selrow==""){
				alert("请至少选择一条记录");
				return false;
			}else {
				var rowData = "";
				var newData = "";
				$.each(selrow, function(i, value) {
					rowData = jQuery("#receivemessageList").jqGrid("getRowData", value);
					newData += rowData.messageId+",";
				});
				newData = newData.substring(0, newData.length-1);
				var r=confirm("您确定要删除短消息吗？");
				if (r==true){
					$.post("message/delReceiveMessage.json",
			          	{	
							messageid:newData
			          	},
			          	function(data,status){
			          		if (status=="success"){
			          			art.dialog(
			        				{title:"提示",content:data.flagmsg,style:"success",
			        					button:{name:"确定", callback:function(){
			        						var curPage = $(".ui-pg-input").val();
						          			jQuery("#receivemessageList").jqGrid(
						     					'setGridParam',
						     					{
						     						url : 'message/getReceiveMessage.json',
						     						page : curPage
						     					}
						     				).trigger("reloadGrid");
			       						}},
			       						close:function(){
			       							var curPage = $(".ui-pg-input").val();
						          			jQuery("#receivemessageList").jqGrid(
						     					'setGridParam',
						     					{
						     						url : 'message/getReceiveMessage.json',
						     						page : curPage
						     					}
						     				).trigger("reloadGrid");
		        						}
		        					}
			        			);
			          			
			          		}
			          	});
					
				}else{
					return false;
				}
			}
		},
	}, {}, {}, {}, {}, {});
}
function openSendFunction(cellvalue, options, rowdata){
	var retstr="<a href='javascript:void(0);' " +
	"onclick=\"post('message/goSendDetail'," +
	"{messageid:\'"+rowdata.messageid+"\'})\">" +
	rowdata.title+"</a>";
	return retstr;
}
function openReceiveFunction(cellvalue, options, rowdata){
	var retstr;
	if(rowdata.readstatus=="2"){
		retstr="<a href='javascript:void(0);' " +
		"onclick=\"post('message/goReceiveDetail'," +
		"{readstatus:\'"+rowdata.readstatus+"\',messageid:\'"+rowdata.messageId+"\'})\">" +
		"<font color='red'>"+rowdata.title+"</font></a>";
	}else{
		retstr="<a href='javascript:void(0);' " +
		"onclick=\"post('message/goReceiveDetail'," +
		"{readstatus:\'"+rowdata.readstatus+"\',messageid:\'"+rowdata.messageId+"\'})\">" +
		rowdata.title+"</a>";
	}
	return retstr;
}
function openNoSendFunction(cellvalue, options, rowdata){
	
	var retstr="<a href=\"javascript:void(0)\" onclick=\"post('message/goEditMessage',"+
	"{messageid:'"+rowdata.messageId+"'})\">"+rowdata.title+"</a>";
	return retstr;
}
function post(URL, PARAMS) {        
    var temp = document.createElement("form");        
    temp.action = URL;        
    temp.method = "post";        
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);        
    temp.submit();
    $("form").remove();
    return temp;
}