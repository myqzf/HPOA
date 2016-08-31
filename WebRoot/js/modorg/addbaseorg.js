$(document).ready(function(e){
	$('.nonCheckStaff li:first').addClass("active");
	$('.checkedStaff li:first').addClass("active");
	/*选中要进行操作的员工*/	
	beSelected($('.checkedStaff li'));
	beSelected($('.nonCheckStaff li'));
	//移除单个员工
	$('.removeElement').click(function(){
	     var removeElement;
		 var id=$(this).parents('.staffMain').attr('id');
	   	 $('.nonCheckStaff li').bind("click");
		 var aliId;
		 var aLi; 
			 removeElement=$('.checkedStaff .active').html();
			 aliId=$('.checkedStaff .active').attr('id');
			 aLi=$('.checkedStaff li').length-1;
		 if (!removeElement && removeElement != 0) {
           alert('请选中你要移除的员工！');
         }  
		 else{
			 var txt=$("<li id="+aliId+"><span>"+removeElement+"</span></li>");
			 $('.nonCheckStaff ul').append(txt);
		     
			var value = $('.checkedStaff .active').index();
			$('.checkedStaff').find('.active').remove();
			if(value == aLi){
				 $('.checkedStaff li').eq(0).addClass('active');
			}
			$('.checkedStaff li').eq(value).addClass('active');	
		 }
	  
	  beSelected($('.nonCheckStaff li'));
	  
	});
	//移除全部角色
	$('.allremoveElement').click(function(e){
		if($.trim($('.checkedStaff ul').html())==''){
			alert('全部员工已移除');
		}else{
			$(".checkedStaff ul li").each(function(i,e){
				var txt=$("<li id="+this.id+"><span>"+this.innerText+"</span></li>");
			 	$('.nonCheckStaff ul').append(txt);
			 	$(this).remove();
			});
			beSelected($('.nonCheckStaff li'));
		}
	});
	//分配单个角色
	$('.addElement').click(function(){	  
		  var value=$('.nonCheckStaff .active').index();
		  var addElement=$('.nonCheckStaff .active span').html();
		  var aLi=$('.nonCheckStaff li').length-1;
		  var aliId=$('.nonCheckStaff .active').attr("id");
		  if (!addElement && addElement != 0) {
			  alert('请选中你要分配的员工！');
		  }else{
			   var txt=$("<li id="+aliId+">"+addElement+"</li>");
			   if($('.checkedStaff li').length>0){  
				   $('.checkedStaff ul li:first').before(txt);
			   }
			   else{ 
				   $('.checkedStaff ul').append(txt);
			   }
			   $('.nonCheckStaff .active').remove();
			   if(value == aLi){
				   $('.nonCheckRole li').eq(0).addClass('active');
			   }
			   $('.nonCheckStaff li').eq(value).addClass('active');
		 }
	     beSelected($('.checkedStaff li'));
	}); 
	//分配全部角色
	$(".alladdElement").click(function(e){
		if($.trim($('.nonCheckStaff ul').html())==''){
			alert('全部员工已分配');
		}else{
			$(".nonCheckStaff ul li").each(function(i,e){
				var txt=$("<li id="+this.id+">"+this.innerText+"</li>");
			 	$('.checkedStaff ul').append(txt);
			 	$(this).remove();
			});
			beSelected($('.checkedStaff li'));
		}
	});
	//添加
	$("#addbtn").click(function(e){
		if($("#orgname").val()==""){
			alert("请输入部门名称");
			return false;
		}
		if($("#orgallname").val()==""){
			alert("请输入部门全称");
			return false;
		}
		if($("#orgdep").val()=="0"){
			alert("请选择所属主部门");
			return false;
		}
		var staffid="";
		//获取已选择的员工
		$(".checkedStaff ul li").each(function(i, value) {
			staffid = staffid + "," + this.id;
		});
		//去掉第一个字符（第一个字符是一个逗号,）
		staffid=staffid.substr(1);
		if(staffid.length==0){
			alert("请选择员工");
			return false;
		}
		$.ajax({
			url : "/HPOA/orgmanage/addBaseOrg.json",
			ansyc : false,
			data : {
				orgName:$("#orgname").val(),
				orgAllName:$("#orgallname").val(),
				depid:$("#orgdep").val(),
				staffids:staffid,
			},
			type : "post",
			success : function(data){
					alert(data.message);
					if(data.rtnflag=='1'){
						art.dialog.parent.location.reload();
						art.dialog.close();
					}
				}
		});
	});
	$("#editbtn").click(function(e){
		if($("#orgname").val()==""){
			alert("请输入部门名称");
			return false;
		}
		if($("#orgallname").val()==""){
			alert("请输入部门全称");
			return false;
		}
		if($("#orgdep").val()=="0"){
			alert("请选择所属主部门");
			return false;
		}
		var staffid="";
		//获取已选择的员工
		$(".checkedStaff ul li").each(function(i, value) {
			staffid = staffid + "," + this.id;
		});
		//去掉第一个字符（第一个字符是一个逗号,）
		staffid=staffid.substr(1);
		if(staffid.length==0){
			alert("请选择员工");
			return false;
		}
		$.ajax({
			url : "/HPOA/orgmanage/updateBaseOrg.json",
			ansyc : false,
			data : {
				orgid:$("#orgid").val(),
				orgName:$("#orgname").val(),
				orgAllName:$("#orgallname").val(),
				depid:$("#orgdep").val(),
				staffids:staffid,
			},
			type : "post",
			success : function(data){
					alert(data.message);
					if(data.rtnflag=='1'){
						art.dialog.parent.location.reload();
						art.dialog.close();
					}
				}
		});
	});
});
function loadstaff(){
	$('.checkedStaff ul').html("");
	$('.nonCheckStaff ul').html("");
	$.ajax({
		url : "/HPOA/orgmanage/getStaffByDep.json",
		data : {depid:$("#orgdep").val(),
		},
		type : "post",
		success : function(data){
				var flag=true;
				for(var i=0;i<data.staffList.length;i++){
					var txt=$("<li id="+data.staffList[i].staffId+"><span>"+data.staffList[i].staffName+"</span></li>");
					if(flag){
						txt=$("<li id="+data.staffList[i].staffId+" class='active'><span>"+data.staffList[i].staffName+"</span></li>");
						flag=false;
					}
				 	$('.nonCheckStaff ul').append(txt);
				}
				beSelected($('.nonCheckStaff li'));
			}
	});
}

function beSelected(ele){
	ele.click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	});
}	

/*function clearManager(){
	$("#orgmanager").html('<option value="0">请选择</option>');
}

function addManager(id,name){
	$("#orgmanager").append("<option value='"+id+"'>"+name+"</option>");
}

function removeManager(id){
	$("#orgmanager option").each(function(e){
		if($(this).val()==id){
			$(this).remove();
		}
	});
}*/