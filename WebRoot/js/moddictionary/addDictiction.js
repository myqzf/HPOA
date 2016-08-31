if (!String.prototype.trim) {
		String.prototype.trim = function() {
			return this.replace(/^\s+|\s+$/g, '');
		};
	}
		

		/**
		 * 提交添加的字典项信息
		 * 
		 * @param 
		 */
		function addFunc(){
			itemsName = $("#itemsName").val();
			itemsShort = $("#itemsShort").val();
			ifleaf = $("input[name='ifleaf']:checked").val();
			pIdKey=$("#pIdKey").val();
			headId=$("#headId").val();
			itemsId = $("#itemsId").val();
			$("#messageDiv").hide();
			$("#message").html("");
			
			pid=itemsId;
			
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
			$.post("/HPOA/dictionary/addDictInfo", {
				itemsName : itemsName,
				itemsShort : itemsShort,
				ifleaf : ifleaf,
				pid : pid,
				headId : headId
			}, function(data) {
					if (data == 1) {
					alert("添加成功！");
						window.location.href='/HPOA/dictionary/godictictionHead';

					}
					else{
						alert("添加失败，请稍候再试！");
					}
				}
			);
		}