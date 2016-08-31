$(document).ready(function(e){
	$("#lblIdcardCheck").css("display","none");

//	//提示身份证号码输入状态（正确与否）
	$("#staffIdcard").focus(function(e){
		$("#lblIdcardCheck").css("display","inline");
	});
	$("#staffIdcard").keyup(function(e){
		var reg=new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X|x)$/);
		if(reg.test($("#staffIdcard").val())){
			$("#lblIdcardCheck").html('身份证号码输入正确');
		}
		if(!reg.test($("#staffIdcard").val())){
			$("#lblIdcardCheck").html('请输入18位正确的身份证号码');
		}
	});
	$("#staffIdcard").blur(function(e){
		$("#lblIdcardCheck").css("display","none");
	});
	$("#addStaff").click(function(e){
		//判断姓名非空
		if($("#staffName").val()==''){
			art.dialog.alert('用户名不能为空');
			return false;
		}
		//判断公司非空
		if($("#staffComp").val()=='0'){
			art.dialog.alert('公司不能为空');
			return false;
		}
		//判断职位非空
		if($("#staffPosi").val()=='0'){
			art.dialog.alert('职位不能为空');
			return false;
		}
		//判断部门非空
		if($("#staffDept").val()=='0'){
			art.dialog.alert('部门不能为空');
			return false;
		}
		//判断输入的身份证号是否正确
		if($("#staffIdcard").val()!=''){
			var reg=new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X|x)$/);
			if(!reg.test($("#staffIdcard").val())){
				art.dialog.alert('请输入正确的身份证号');
				return false;
			}
		}
		$.post(
			"/HPOA/staffManage/addStaff",
			{	
				staffName:$("#staffName").val(),
				staffIdcard:$("#staffIdcard").val(),
				staffGender:$("#staffGender").val(),
				staffQualid:$("#staffQualid").val(),
				staffMarry:$("#staffMarry").val(),
				staffPhone:$("#staffPhone").val(),
				staffAddress:$("#staffAddress").val(),
				staffDept:$("#staffDept").val(),
				staffPosi:$("#staffPosi").val(),
				staffComp:$("#staffComp").val(),		
				
			},
			function(data){
				var message="";
				switch(data.result){
				case "0":{
					message = "系统出错";
					break;
				}
				case "1":{
					message ="添加成功";
					break;
				}
				case "2":{
					message = "添加失败";
					break;
				}
				case "3":{
					message = "未填写姓名";
					break;
				}
			}
				art.dialog({					
					title:'感谢您对我们工作的支持',
					content:message,
					opacity: 0,
					lock:true,
					button: [{
						name:'确定',
						callback: function () {
							window.location.reload();
						}
					}],
					lock:true,
					close:function(){
						window.location.reload();
					}
				});
			}
		);
		
	});
});