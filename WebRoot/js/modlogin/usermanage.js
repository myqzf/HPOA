$(document).ready(function(e) { 
	var flag=false;
	//初始化按钮及密码框，设置账号是否可修改稿
	if($("#flag").val()==null || $("#flag").val()==""){
		$("#flag").attr("disabled",false);
		$("#add").show();
		$("#modify").hide();
		flag=true;
    }else{
    	$("#flag").attr("disabled",true);
    	$("#userAccount").attr("readonly",true);
    	$("#userPad").val($("#Pwd").val());
    	$("#userPad1").val($("#Pwd").val());
    	$("#add").hide();
		$("#modify").show();
    }
	//列出全部角色
	//jqgrid();
	$.ajax({
		url : "/HPOA/login/jsonGetRoleList.json",
		data : {staffid:$("#staffid").val(),
		},
		type : "post",
		success : function(data){
				for(var i=0;i<data.roleData.length;i++){
					if(data.roleData[i].isCheck=='1'){
						var txt=$("<li id="+data.roleData[i].roleId+">"+data.roleData[i].roleName+"</li>");
					 	$('.checkedRole ul').append(txt);
					}else{
						var txt=$("<li id="+data.roleData[i].roleId+"><span>"+data.roleData[i].roleName+"</span></li>");
						if(flag){
							txt=$("<li id="+data.roleData[i].roleId+" class='active'><span>"+data.roleData[i].roleName+"</span></li>");
							flag=false;
						}
					 	$('.nonCheckRole ul').append(txt);
					}
				}
				beSelected($('.checkedRole li'));
				beSelected($('.nonCheckRole li'));
			}
	});
	//添加用户
	$("#add").click(function(e){
		var rowids = $("#roleList").jqGrid("getGridParam", "selarrrow");
		
		var userAccount = $("#userAccount").val();
		if($.trim(userAccount)==""||userAccount==null){
			$("#userAccount").focus();
			alert("账号不能为空！");
			return false;
		}
		var userName = $("#userName").val();
		if(userName==null || $.trim(userName)==""){
			$("#userName").focus();
			alert("姓名不能为空！");
			return false;
		}
		var userpad = $("#userPad").val();
		if(userpad==null || $.trim(userpad)==""){
			$("#userPad").focus();
			alert("密码不能为空！");
			return false;
		}
		var userpad1 = $("#userPad1").val();
		if(userpad1==null || $.trim(userpad1)==""){
			$("#userPad1").focus();
			alert("密码确认不能为空！");
			return false;
		}
		if(userpad != userpad1){
			alert("新密码与再次输入密码不一致");
			return;
		}
		var roles="";
		//获取已选择的角色
		$(".checkedRole ul li").each(function(i, value) {
			roles = roles + "," + this.id;
		});
		//去掉第一个字符（第一个字符是一个逗号,）
		roles=roles.substr(1);
		if(roles.length==0){
			alert("请选择角色！");
			return false;
		}
		$.ajax({
			url : "/HPOA/login/jsonAddUser.json",
			data : {roles:roles,staffid:$("#staffid").val(),userAccount:$("#userAccount").val(),
				userPwd:$("#userPad").val(),userName:$("#userName").val()
			},
			type : "post",
			success : function(data){
					if(data.flag=='0'){
						alert('系统繁忙');
					}else if(data.flag=='1'){
						alert('添加成功');
						rtn();
					}else if(data.flag=='2'){
						alert('用户名已存在');
					}else if(data.flag=='3'){
						alert('请检查是否全部信息都已填写或选择');
					}
				}
		});
	});
	//修改用户
	$("#modify").click(function(e){
		var roles="";
		var userName = $("#userName").val();
		if(userName==null || $.trim(userName)==""){
			$("#userName").focus();
			alert("姓名不能为空！");
			return false;
		}
		var userpad = $("#userPad").val();
		if(userpad==null || $.trim(userpad)==""){
			$("#userPad").focus();
			alert("密码不能为空！");
			return false;
		}
		var userpad1 = $("#userPad1").val();
		if(userpad1==null || $.trim(userpad1)==""){
			$("#userPad1").focus();
			alert("密码确认不能为空！");
			return false;
		}
		if(userpad != userpad1){
			alert("新密码与再次输入密码不一致");
			return;
		}
		var roles="";
		$(".checkedRole ul li").each(function(i, value) {
			roles = roles + "," + this.id;
		});
		roles=roles.substr(1);
		if(roles.length==0){
			alert("请选择角色！");
			return false;
		}
		$.ajax({
			url : "/HPOA/login/jsonModifyUser.json",
			data : {roles:roles,userid:$("#flag").val(),userPwd:$("#userPad").val(),
					userName:$("#userName").val()
			},
			type : "post",
			success : function(data){
					if(data.flag=='0'){
						alert('系统繁忙');
					}else if(data.flag=='1'){
						alert('修改成功');
						rtn();
					}else if(data.flag=='2'){
						alert('用户名已存在');
					}else if(data.flag=='3'){
						alert('请检查是否全部信息都已填写或选择');
					}
				}
		});
	});
	/*选中要进行操作的角色*/	
	function beSelected(ele){
	   ele.click(function(){
		    $(this).addClass('active').siblings().removeClass('active');
	    });
	}	
	beSelected($('.checkedRole li'));
	beSelected($('.nonCheckRole li'));
	//移除单个角色
	$('.removeElement').click(function(){
	     var removeElement;
		 var id=$(this).parents('.roleMain').attr('id');
	   	 $('.nonCheckRole li').bind("click");
		 var aliId;
		 var aLi; 
			 removeElement=$('.checkedRole .active').html();
			 aliId=$('.checkedRole .active').attr('id');
			 aLi=$('.checkedRole li').length-1;         
		 if (!removeElement && removeElement != 0) {
           alert('请选中你要移除的角色！');
         }  
		 else{
			 var txt=$("<li id="+aliId+"><span>"+removeElement+"</span></li>");
			 $('.nonCheckRole ul').append(txt);
		     
			var value = $('.checkedRole .active').index();
			$('.checkedRole').find('.active').remove();
			if(value == aLi){
				 $('.checkedRole li').eq(0).addClass('active');
			}
			$('.checkedRole li').eq(value).addClass('active');		
		 }
	  
	  beSelected($('.nonCheckRole li'));
	  
	});
	//移除全部角色
	$('.allremoveElement').click(function(e){
		if($.trim($('.checkedRole ul').html())==''){
			alert('全部角色已移除');
		}else{
			$(".checkedRole ul li").each(function(i,e){
				var txt=$("<li id="+this.id+"><span>"+this.innerText+"</span></li>");
			 	$('.nonCheckRole ul').append(txt);
			 	$(this).remove();
			});
			beSelected($('.nonCheckRole li'));
		}
	});
	//分配单个角色
	$('.addElement').click(function(){	  
		  var value=$('.nonCheckRole .active').index();
		  var addElement=$('.nonCheckRole .active span').html();
		  var aLi=$('.nonCheckRole li').length-1;
		  var aliId=$('.nonCheckRole .active').attr("id");
		  if (!addElement && addElement != 0) {
			  alert('请选中你要分配的角色！');
		  }else{
			   var txt=$("<li id="+aliId+">"+addElement+"</li>");
			   if($('.checkedRole li').length>0){  
				   $('.checkedRole ul li:first').before(txt);
			   }
			   else{ 
				   $('.checkedRole ul').append(txt);
			   }
			   $('.nonCheckRole .active').remove();
			   if(value == aLi){
				   $('.nonCheckRole li').eq(0).addClass('active');
			   }
			   $('.nonCheckRole li').eq(value).addClass('active');
		 }
	     beSelected($('.checkedRole li'));
	}); 
	//分配全部角色
	$(".alladdElement").click(function(e){
		if($.trim($('.nonCheckRole ul').html())==''){
			alert('全部角色已分配');
		}else{
			$(".nonCheckRole ul li").each(function(i,e){
				var txt=$("<li id="+this.id+">"+this.innerText+"</li>");
			 	$('.checkedRole ul').append(txt);
			 	$(this).remove();
			});
			beSelected($('.checkedRole li'));
		}
	});
});
function jqgrid() {
	$("#list").show();
	$("#roleList").jqGrid({
		url : '/HPOA/login/jqgridjsonGetRoleList.json?staffid='+$("#staffid").val(),
		datatype : "json",
		mtype : 'post',
		height : 400,
		width : 600,
		//autowidth : true,
		rownumbers : true,
		rowNum : 20,
		pgtext : "第{0}页 共{1}页",
		rowList : [ 20, 30, 40 ],
		pager : '#pager1',
		sortname : 'roleId',
		viewrecords : false,
		sortorder : "asc",
		caption : "&nbsp;&nbsp;&nbsp;&nbsp;角色列表",
		multiselect : true,
		ondblClickRow : function(id){
			var ret = $("#roleList").jqGrid('getRowData', id);
		},
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
		gridComplete : function() {
			var roleIds = $("#roleList").getDataIDs();
			oldRole = "-1";
			if (roleIds.length > 0) {

				$.each(roleIds, function(i, value) {
					var rowData = $("#roleList").jqGrid("getRowData",
							value);
					if (rowData.isCheck == "1") {
						// 赋值
						oldRole = oldRole + "," + rowData.roleId;
						$("#roleList").jqGrid('setSelection',
								$("#roleList").getDataIDs()[i]);
					}
				});
			}
		},

		colNames : ['角色ID', '角色名', '角色描述','是否选中'],
		colModel : [  {
			name : 'roleId',
			index : 'roleId',
			width : 80,
			hidden : true,
			search : false
			
		}, {
			name : 'roleName',
			index : 'roleName',
			width : 80,
			hidden : false,
			search : true
		}, {
			name : 'roleDesc',
			index : 'roleDesc',
			width : 80,
			hidden : false,
			search : false
		},{
			name : 'isCheck',
			index : 'isCheck',
			width : 80,
			hidden : true,
			search : false
		}
		],
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

	// jqgrid列标题增加查询框
	jQuery("#roleList").jqGrid('filterToolbar');

	$("#roleList").jqGrid('navGrid', '#pager1', {
		del : false,
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
		// alertcap: "Warning",
		alerttext : "请选择需要操作的数据行!",// , // 当未选中任何行而点击编辑、删除、查看按钮时，弹出的提示信息
	// addfunc : add_pf, // 点击添加按钮，则调用openDialog4Adding方法
	// editfunc : edit_pf, // 点击编辑按钮，则调用openDialog4Updating方法
	//delfunc : del_pf// 点击删除按钮，则调用openDialog4Deleting方法
	}, {}, {}, {}, {}, {});
}
//返回按钮
function rtn(){
	window.history.go(-1);
}