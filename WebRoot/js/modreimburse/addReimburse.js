$(document).ready(function(e){
	var n=0;
	var sum=0;
	var count=0;
	var itemlist='';
	var moneylist='';
	var reasonlist='';
	var oDate = new Date(); //实例一个时间对象；
	var year=oDate.getFullYear();
	var month=oDate.getMonth()+1;
	var day=oDate.getDate();
	if(month<10){
		month='0'+month;
	}
	if(day<10){
		day='0'+day;
	}
	var realdate=year+'年'+month+'月'+day+'日';
	$('#date').html(realdate);
	var reimwidth=$("th").css("width");
	var reimheight=$(".reim").parent().css("height");
	//提交按钮
	$("#submit").click(function(e){
		var flagitem=false;
		var flagreason=false;
		var flagmoney=false;
		for(var i=0;i<=count;i++){
			 if($("#item"+i).val()==""){
				flagitem=true;
			 }else{
				 itemlist=itemlist+$("#item"+i).val()+",";
			 }
			 if($("#reason"+i).val()==""){
					flagreason=true;
			 }else{
					 reasonlist=reasonlist+$("#reason"+i).val()+",";
			 }
			 if($("#money"+i).val()==""){
				 flagmoney=true;
			 }else{
				 moneylist=moneylist+$("#money"+i).val()+",";
			 }
		}
		if(flagitem){
			 alert("报销项目不能为空");
			 return false;
		}
		if(flagmoney){
			alert("请输入报销金额");
			return false;
		}
		if(flagreason){
			 alert("报销项目不能为空");
			 return false;
		}
		sum=0;
		for(var i=0;i<=count;i++){
			 sum=sum+parseInt($("#money"+i).val());
		}
		$("#statistics").css("display","none");
		$("#sum").html(sum);
		$("#uppermoney").html(convertCurrency($("#sum").html()));
		$.ajax({
			type : "post",
			url : "reimburse/saveReimburse.json",//
			data : {
				reidate:realdate,//日期
				reidept:$("#dept").val(),//员工所在部门
				itemlist:itemlist,//项目名列表
				reasonlist:reasonlist,//摘要列表
				moneylist:moneylist,//金额列表
				reimoney:$("#sum").html(),//总金额
				uppermoney:$("#uppermoney").html(),//大写金额
				},
			success:function(data){
				art.dialog(
					{title:"提示",content:data.flagmsg,background:'#ccc',opacity: 0,lock:true,style:"success",
						button:{name:"确定", callback:function(){
							if(window.attachEvent) {
								location.href='gotoAdd';
							}else{
								location.href='reimburse/gotoAdd';
							}
						}},
						close:function(){
							if(window.attachEvent) {
								location.href='gotoAdd';
							}else{
								location.href='reimburse/gotoAdd';
							}
						}
					}
				);
			}
		});
	});
	//统计按钮
	$("#statistics").click(function(){
		sum=0;
		var flag=true;
		for(var i=0;i<=count;i++){
			if($("#money"+i).val()==""){
				flag=false;
				alert("金额不能为空！");
				break;
			}
			 sum=sum+parseInt($("#money"+i).val());
		}
		if(flag){
			$("#statistics").css("display","none");
			$("#sum").html(sum);
			$("#uppermoney").html(convertCurrency($("#sum").html()));
		}	

	});
	//添加项目按钮
	$("#additem").click(function(e){
		$("#uppermoney").html("");
		$("#sum").html("");
//		$("#statistics").css("display","block");
		$("#tab").before("<tr><td><input type='text' id='item"+(++count)+"' class='reim'></td><td>"
				+"<textarea id='reason"+count+"' class='reim'></textarea></td><td>"
				+"<input type='text' id='money"+count+"' class='reim'/></td></tr>");
		$(".reim").css("width",reimwidth);
		$(".reim").css("height",reimheight);
		//$("#statistics").css("display","block");
		
	});
	//设置高宽与table中单元格一致
	$(".reim").css("width",$("th").css("width"));
	$(".reim").css("height",$(".reim").parent().css("height"));
});