if (!String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, '');
	};
}

//添加权限
		function addFunc(){
			funcName = $("#funcName").val();
			funcDesc = $("#funcDesc").val();
			funcUrl = $("#funcUrl").val();
			isUsed = $("input[name='isUsed']:checked").val();
			funcPid=$("#funcPid").val();
			$("#messageDiv").hide();
			$("#message").html("");
			if(funcName==null ||funcName.trim()==""){
				$("#message").html("功能名称不能为空！");
				$("#messageDiv").show();
				$("#funcName").focus();
				return false;
			}
			if(funcUrl==null ||funcUrl.trim()==""){
				$("#message").html("功能路径不能为空！");
				$("#messageDiv").show();
				$("#funcUrl").focus();
				return false;
			}
			$.post("/HPOA/system/addMainFunc", {
				funcName : funcName,
				funcDesc : funcDesc,
				funcUrl : funcUrl,
				isUsed : isUsed,
				funcPid : funcPid
			}, function(data) {
					if (data == 1) {
						window.location.href='/HPOA/system/toFuncList';
					}
					else{
						art.dialog.alert("添加失败，请稍候再试！");
					}
				}
			);
		}