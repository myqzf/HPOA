$(document).ready(function(e){
	var n=0;
	var sum=0;
	var count=0;
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
		var flagnumber=false;
		var flagmoney=false;
		var flagremark=false;
		if($("#item").val()==""){
			flagitem=true;
		}else{
			item=$("#item").val();
		}
		if($("#number").val()==""){
			flagnumber=true;
		}else{
			number=$("#number").val();
		}
		if($("#money").val()==""){
			flagmoney=true;
		}else{
			money=$("#money").val();
		}
		if($("#remark").val()==""){
			flagremark=true;
		}else{
			remark=$("#remark").val();
		}
		if(flagitem){
			alert("请输入报销项目");
			return false;
		}
		if(flagmoney){
			alert("请输入报销金额");
			return false;
		}
		if(flagnumber){
			alert("请输入单据数量");
			return false;
		}
		$.ajax({
			type : "post",
			url : "reimburse/savePurchase.json",//
			data : {
				reidate:realdate,//日期
				reidept:$("#dept").html(),//员工所在部门
				number:number,//员工姓名
				item:item,//项目名
				remark:remark,//备注
				money:money,//金额列表
				uppermoney:$("#uppermoney").html(),//大写金额
				},
			success:function(data){
	         	if(data.flagmsg=="1"){
	         		window.location.href="/HPOA/reimburse/goPurchaseList";
	         	}else if(data.flagmsg=="0"){
	         		art.dialog.alert("系统出错!");
	         	}else{
	         		art.dialog.alert("提交失败！");
	         	}
			}
		});
	});

	//设置高宽与table中单元格一致
	$(".reim").css("width",$("th").css("width"));
	$(".reim").css("height",$(".reim").parent().css("height"));
});
function daxie(){
	$("#uppermoney").html(convertCurrency($("#money").val()));
	var Num=$("#money").val();
}

function convertCurrency(Num){
	for(i=Num.length-1;i>=0;i--){
		Num = Num.replace(",","");
		Num = Num.replace(" ","");
	}
	Num = Num.replace("￥","");
	if(isNaN(Num)) {
	alert("请检查小写金额是否正确");
	return false;
	}
	part = String(Num).split(".");
	newchar = "";
	for(i=part[0].length-1;i>=0;i--){
	if(part[0].length > 10){ alert("位数过大，无法计算");return "";}
	tmpnewchar = "";
	perchar = part[0].charAt(i);
	switch(perchar){
	case "0": tmpnewchar="零" + tmpnewchar ;break;
	case "1": tmpnewchar="壹" + tmpnewchar ;break;
	case "2": tmpnewchar="贰" + tmpnewchar ;break;
	case "3": tmpnewchar="叁" + tmpnewchar ;break;
	case "4": tmpnewchar="肆" + tmpnewchar ;break;
	case "5": tmpnewchar="伍" + tmpnewchar ;break;
	case "6": tmpnewchar="陆" + tmpnewchar ;break;
	case "7": tmpnewchar="柒" + tmpnewchar ;break;
	case "8": tmpnewchar="捌" + tmpnewchar ;break;
	case "9": tmpnewchar="玖" + tmpnewchar ;break;
	}
	switch(part[0].length-i-1){
	case 0: tmpnewchar = tmpnewchar +"元" ;break;
	case 1: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
	case 2: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
	case 3: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
	case 4: tmpnewchar= tmpnewchar +"万" ;break;
	case 5: if(perchar!=0)tmpnewchar= tmpnewchar +"拾" ;break;
	case 6: if(perchar!=0)tmpnewchar= tmpnewchar +"佰" ;break;
	case 7: if(perchar!=0)tmpnewchar= tmpnewchar +"仟" ;break;
	case 8: tmpnewchar= tmpnewchar +"亿" ;break;
	case 9: tmpnewchar= tmpnewchar +"拾" ;break;
	}
	newchar = tmpnewchar + newchar;
	}
	if(Num.indexOf(".")!=-1){
	if(part[1].length > 2) {
	part[1] = part[1].substr(0,2);
	}
	for(i=0;i<part[1].length;i++){
	tmpnewchar = "";
	perchar = part[1].charAt(i);
	switch(perchar){
	case "0": tmpnewchar="零" + tmpnewchar ;break;
	case "1": tmpnewchar="壹" + tmpnewchar ;break;
	case "2": tmpnewchar="贰" + tmpnewchar ;break;
	case "3": tmpnewchar="叁" + tmpnewchar ;break;
	case "4": tmpnewchar="肆" + tmpnewchar ;break;
	case "5": tmpnewchar="伍" + tmpnewchar ;break;
	case "6": tmpnewchar="陆" + tmpnewchar ;break;
	case "7": tmpnewchar="柒" + tmpnewchar ;break;
	case "8": tmpnewchar="捌" + tmpnewchar ;break;
	case "9": tmpnewchar="玖" + tmpnewchar ;break;
	}
	if(i==0)tmpnewchar =tmpnewchar + "角";
	if(i==1)tmpnewchar = tmpnewchar + "分";
	newchar = newchar + tmpnewchar;
	}
	}
	while(newchar.search("零零") != -1)
	newchar = newchar.replace("零零", "零");
	newchar = newchar.replace("零亿", "亿");
	newchar = newchar.replace("亿万", "亿");
	newchar = newchar.replace("零万", "万");
	newchar = newchar.replace("零元", "元");
	newchar = newchar.replace("零角", "");
	newchar = newchar.replace("零分", "");
	if (newchar.charAt(newchar.length-1) == "元" || newchar.charAt(newchar.length-1) == "角")
	newchar = newchar+"整";
	return newchar;
	}