// 新增角色
     
    function saveRole(){
    	var roleName = $("#roleName").val();
    	var roleDesc = $("#roleDesc").val();
    	if(roleName==null || roleName.trim()==""){
    		art.dialog.alert("角色名不能为空！");
    		return false;
    	}
    	$.post("/HPOA/system/saveRole",{roleName:roleName,roleDesc:roleDesc},function(data){
         	if(data=="1"){
         		window.location.href="/HPOA/system/toRoleList";
         	}else if(data=="3"){
         		art.dialog.alert("增加失败，角色名已存在!");
         	}else{
         		art.dialog.alert("增加失败，请稍候再试！");
         	}
     	});
    }
