if (!String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, '');
	};
}

//回显可用于是否叶子节点
$(document).ready(function() { 
		if($("#isLeafaaa").val()==1){
			$("input[type='radio'][name='isLeaf'][value='1']").attr("checked", "checked");
		}else{
			$("input[type='radio'][name='isLeaf'][value='2']").attr("checked", "checked");
		}
		if($("#isUsedaaa").val()==1){
			$("input[type='radio'][name='isUsed'][value='1']").attr("checked", "checked");
		}else{
			$("input[type='radio'][name='isUsed'][value='2']").attr("checked", "checked");
		}
	});

//修改
	function modifyFunc(){
		funcName = $("#funcName").val();
		funcDesc = $("#funcDesc").val();
		funcUrl = $("#funcUrl").val();
		isLeaf = $("input[name='isLeaf']:checked").val();
		isUsed = $("input[name='isUsed']:checked").val();
		funcId=$("#funcId").val();
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
		$.post("/HPOA/system/modifyFuncInfo", {
			funcName : funcName,
			funcDesc : funcDesc,
			funcUrl : funcUrl,
			isLeaf : isLeaf,
			isUsed : isUsed,
			funcId : funcId
		}, function(data) {
				if (data == 1) {
			//		window.history.go(-1);					
				    window.location.href='/HPOA/system/toFuncList';					
				}
				else{
					art.dialog.alert("添加失败，请稍候再试！");
				}
			}
		);
	}