//修改角色信息     
    function modRole(){
    	var roleName = $("#roleName").val();
    	var roleDesc = $("#roleDesc").val();
    	var roleId = $("#roleId").val();
    	if(roleName==null || roleName.trim()==""){
    		art.dialog.alert("角色名不能为空！");
    		return false;
    	}
    	$.post("/HPOA/system/modifyRole",{roleId:roleId,roleName:roleName,roleDesc:roleDesc},function(data){
         	if(data=="1"){
         		window.location.href='/HPOA/system/getRoleInfo';
         	}else if(data.rtnFlg=="3"){
         		art.dialog.alert("修改失败，角色名已存在!");
         	}else{
         		art.dialog.alert("修改失败，请稍候再试！");
         	}
     	});
    }
