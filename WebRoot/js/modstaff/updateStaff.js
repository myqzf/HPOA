$(document).ready(function(e){
	//初始化员工各项信息
	$("#staffQualid").val($("#qualid").val());
	$("#staffGender").val($("#gender").val());
	$("#staffDept").val($("#dept").val());
	$("#staffMarry").val($("#marry").val());
	$("#staffPosi").val($("#posi").val());
	$("#staffComp").val($("#comp").val());

	if($("#photoval").val().length>0){
		$("#picture").attr("src","staffpicture/"+$("#photoval").val());
		$("#modifyPicture").html("修改照片");

	}else{
		$("#picture").attr("src","staffpicture/none.jpg");
	}
	//初始化删除链接
	if($("#photoval").val()!=""){
		$("#delPhoto").css("display","inline");
	}else{		
		$("#delPhoto").css("display","none");
		document.getElementById('modifyPicture').innerHTML="上传照片";
	}
	//初始化  身份证号  提示信息	
	$("#lblIdcardCheck").css("display","none");
	var reg= new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X|x)$/);
	
	//提示身份证号输入状态（正确与否）
	$("#staffIdcard").focus(function(e){
		$("#lblIdcardCheck").css("display","inline");
	});
	$("#staffIdcard").keyup(function(e){
		var reg=new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X|x)$/);
		if(reg.test($("#staffIdcard").val())){
			$("#lblIdcardCheck").html('身份证号输入正确');
		}
		if(!reg.test($("#staffIdcard").val())){
			$("#lblIdcardCheck").html('请输入正确的身份证号');
		}
	});
	$("#staffIdcard").blur(function(e){
		$("#lblIdcardCheck").css("display","none");
	});
	//上传照片
	$("#modifyPicture").click(function(e){			
			window.open("/HPOA/staff/gotoUploadPicture?staffId="+$("#id").val(),
				"upload","height=200,width=500,top=400,left=400,alwaysRaised=yes,location=no");
	});
	

	
	
	//删除图片方法
	$("#delPhoto").click(function(e){
		art.dialog.confirm('确定删除照片？',
		function(){
			$.post(
					"staffManage/delPhoto",
					{staffId:$("#id").val()},
					function(data){
						var message="";
						switch(data.result){
						case "0":{
							message = "系统出错";
							break;
						}
						case "1":{
							message ="删除成功";
							break;
						}
						case "2":{
							message = "删除失败";
							break;
						}
						case "3":{
							message = "员工id为空";
							break;
						}
					}
						art.dialog({
							title:'感谢您对我们工作的大力支持',
							content:message,
							background:'#ccc',
							opacity: 0,
							lock:true,
							button: [{
								name:'确定',
								callback: function () {							
										$("#modifyPicture").html("上传照片");

								}
							}],					
						});		
						$("#picture").attr("src","staffpicture/none.jpg");
						$("#delPhoto").css("display","none");
				}
			);
		},
		function(){
		});
	});

	
	

	//修改按钮
	$("#modifyStaff").click(function(e){
		//判断姓名非空
		if($("#staffName").val()==''){
			art.dialog.alert('用户名不能为空');
			return false;
		}	
		//判断公司非空
		if($("#staffComp").val()==''){
			art.dialog.alert('公司不能为空');
			return false;
		}
		//判断职位非空
		if($("#staffPosi").val()==''){
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
			"/HPOA/staffManage/modifyStaff",
			{
				staffId:$("#id").val(),
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
					message ="修改成功";
					break;
				}
				case "2":{
					message = "修改失败";
					break;
				}
				case "3":{
					message = "未填写姓名";
					break;
				}
			}
				art.dialog({
					title:'感谢您对我们工作的大力支持',
					content:message,
					background:'#ccc',
					opacity: 0,
					lock:true,
					button: [{
						name:'确定',
						callback: function () {
							window.location.href="/HPOA/staff/gotoListStaff";
						}
					}],
					close:function(){
						window.location.href="/HPOA/staff/gotoListStaff";
					}
				});
			}
		);
	});
	$("#cancel").click(function(e){
		window.location.href="/HPOA/staff/gotoListStaff";
	});
});
