$(document).ready(function() { 
		if($("#isLeafaaa").val()==1){
			$("input[type='radio'][name='isLeaf'][value='1']").attr("checked", "checked");
		}else{
			$("input[type='radio'][name='isLeaf'][value='2']").attr("checked", "checked");
		}
	});
	if (!String.prototype.trim) {
		String.prototype.trim = function() {
			return this.replace(/^\s+|\s+$/g, '');
		};
	}
	
	/**
	 * 提交修改的字典项信息
	 * 
	 * @param 
	 */
	function modifyFunc(){
		itemsName = $("#itemsName").val();
		itemsShort = $("#itemsShort").val();
		//isLeaf = $("input[name='isLeaf']:checked").val();
		itemsId=$("#itemsId").val();
		headId=$("#headId").val();
		$("#messageDiv").hide();
		$("#message").html("");
		
		if(itemsName==null ||itemsName.trim()==""){
			$("#message").html("字典项名称不能为空！");
			$("#messageDiv").show();
			$("#itemsName").focus();
			return false;
		}
		if(itemsName.length>20){
			alert("你输入的字典项名称过长！");
			return false;
		}
		$.post("/HPOA/dictionary/modifyDictInfo", {
			itemsName : itemsName,
			itemsShort : itemsShort,
				//isLeaf : isLeaf,
			itemsId : itemsId,
				//headId : headId
		}, function(data) {
				if (data == 1) {
					alert("修改成功！");
				   window.location.href='/HPOA/dictionary/godictictionHead';
					
				}
				else{
					art.dialog.alert("修改失败，请稍候再试！");
				}
			}
		);
	}
	
	/**
	 * 字典头修改
	 */
	function modifyDictHead() {
		var headId = document.getElementById("headId").value;
		var headName = document.getElementById("headName").value;
		
		if(headName==null ||headName.trim()==""){
			alert("字典头名称不能为空！");
			return false;
		}
		if(headName.length>10){
			alert("你输入的字典头名称过长！");
			return false;
		}
		$.ajax({
			async : false,
			dataType : 'json',
			type : 'post',
			url : "/HPOA/dictionary/modifyDictHead",
			data:"headName=" + headName+"&headId="+headId,		
			success : function() {
				alert("修改成功");
				
				  window.location.href="/HPOA/dictionary/godictictionHead";
			},
			error : function() {
				alert("error");

			}
		});
	}