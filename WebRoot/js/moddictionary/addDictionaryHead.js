if (!String.prototype.trim) {
		String.prototype.trim = function() {
			return this.replace(/^\s+|\s+$/g, '');
		};
	}
		

		/**
		 * 提交添加的字典头信息
		 * 
		 * @param 
		 */
		function addDictHead(){
			headName = $("#headName").val();
			
 			
			if(headName==null ||headName.trim()==""){
				alert("字典头名称不能为空！");
				return false;
			}
			if(headName.length>10){
				alert("你输入的字典头名称过长！");
				return false;
			}
			console.log(headName.length);
			$.post("/HPOA/dictionary/addDictHeadInfo", {
				headName : headName,
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