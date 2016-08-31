$(document).ready(function(e){
	var n=0;
	var sum=0;
	var money=0;
	var count=0;
	var itemlist='';
	var numlist='';
	var pricelist='';
	var moneylist='';
	var remarklist='';
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
		var flagnum=false;
		var flagprice=false;
		var flagremark=false;
		var flagmoney=false;
		for(var i=0;i<=count;i++){
			 if($("#item"+i).val()==""){
				flagitem=true;
			 }else{
				 itemlist=itemlist+$("#item"+i).val()+",";
			 }
			 if($("#num"+i).val()==""){
				flagnum=true;
			 }else{
				numlist=numlist+$("#num"+i).val()+",";
			 }
			 if($("#price"+i).val()==""){
				flagprice=true;
			 }else{
				pricelist=pricelist+$("#price"+i).val()+",";
			 }
			 if($("#remark"+i).val()==""){
				flagremark=true;
			 }else{
				 remarklist=remarklist+$("#remark"+i).val()+",";
			 }
			 if($("#money"+i).html()==""){
				 flagmoney=true;
			 }else{
				 moneylist=moneylist+$("#money"+i).html()+",";
			 }
		}
		if(flagitem){
			 alert("报销项目不能为空");
			 return false;
		}
		if(flagnum){
			 alert("数量不能为空");
			 return false;
		}
		if(flagprice){
			 alert("单价不能为空");
			 return false;
		}
		if(flagmoney){
			alert("请输入金额");
			return false;
		}
		if(flagremark){
			 alert("备注不能为空");
			 return false;
		}
		$.ajax({
			type : "post",
			url : "reimburse/savePurchReq.json",//
			data : {
				reidate:realdate,//日期
				entryname:itemlist,//货物名列表
				reimoney:$("#sum").html(),//总金额
				itemlist:itemlist,
				numlist:numlist,
				pricelist:pricelist,
				remarklist:remarklist,
				moneylist:moneylist,
				uppermoney:$("#uppermoney").html(),//大写金额
				staffid:$("#staffid").val(),//员工id
				reitype:3,
				isdel:1
				},
			success:function(data){
	         	if(data.flagmsg=="1"){
	         		window.location.href="/HPOA/reimburse/goPurchReqList";
	         	}else if(data.flagmsg=="0"){
	         		art.dialog.alert("系统出错!");
	         	}else{
	         		art.dialog.alert("提交失败！");
	         	}
			}
		});
	});
	//统计按钮
	$("#statistics").click(function(){
		sum=0;
		var flag=true;
		for(var i=0;i<=count;i++){
			if($("#price"+i).val()==''){
				flag=false;
				alert("单价不能为空！");
				break;
			}
			$("#money"+i).html($("#price"+i).val()*$("#num"+i).val());
			 sum=sum+parseInt($("#money"+i).html());
		}
		$("#sum").html(sum);
		if(flag){
			$("#statistics").css("display","none");
			$("#uppermoney").html(convertCurrency(sum));
		}	

	});
	//添加项目按钮
	$("#additem").click(function(e){
		$("#uppermoney").html("");
		$("#sum").html("");
//		$("#statistics").css("display","block");
		$("#tab").before("<tr><td><input type='text' id='item"+(++count)+"' class='reim'></td>"
				+"<td><input type='text' id='num"+count+"' class='reim'></td>"
				+"<td><input type='text' id='price"+count+"' class='reim'></td>"
				+"<td><lable id='money"+count+"'/></lable></td>"
				+"<td><textarea id='remark"+count+"' class='reim'></textarea></td><tr>"
		);
		$(".reim").css("width",reimwidth);
		$(".reim").css("height",reimheight);
		//$("#statistics").css("display","block");
		
	});
	//设置高宽与table中单元格一致
	$(".reim").css("width",$("th").css("width"));
	$(".reim").css("height",$(".reim").parent().css("height"));
});

function convertCurrency(numberValue){  
	var numberValue=new String(Math.round(numberValue*100)); // 数字金额  
	var chineseValue=""; // 转换后的汉字金额  
	var String1 = "零壹贰叁肆伍陆柒捌玖"; // 汉字数字  
	var String2 = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 对应单位  
	var len=numberValue.length; // numberValue 的字符串长度  
	var Ch1; // 数字的汉语读法  
	var Ch2; // 数字位的汉字读法  
	var nZero=0; // 用来计算连续的零值的个数  
	var String3; // 指定位置的数值  
	if(len>15){  
	alert("超出计算范围");  
	return "";  
	}  
	if (numberValue==0){  
	chineseValue = "零元整";  
	return chineseValue;  
	}  
	String2 = String2.substr(String2.length-len, len); // 取出对应位数的STRING2的值  
	for(var i=0; i<len; i++){  
	String3 = parseInt(numberValue.substr(i, 1),10); // 取出需转换的某一位的值  
	if ( i != (len - 3) && i != (len - 7) && i != (len - 11) && i !=(len - 15) ){  
	if ( String3 == 0 ){  
	Ch1 = "";  
	Ch2 = "";  
	nZero = nZero + 1;  
	}  
	else if ( String3 != 0 && nZero != 0 ){  
	Ch1 = "零" + String1.substr(String3, 1);  
	Ch2 = String2.substr(i, 1);  
	nZero = 0;  
	}  
	else{  
	Ch1 = String1.substr(String3, 1);  
	Ch2 = String2.substr(i, 1);  
	nZero = 0;  
	}  
	}  
	else{ // 该位是万亿，亿，万，元位等关键位  
	if( String3 != 0 && nZero != 0 ){  
	Ch1 = "零" + String1.substr(String3, 1);  
	Ch2 = String2.substr(i, 1);  
	nZero = 0;  
	}  
	else if ( String3 != 0 && nZero == 0 ){  
	Ch1 = String1.substr(String3, 1);  
	Ch2 = String2.substr(i, 1);  
	nZero = 0;  
	}  
	else if( String3 == 0 && nZero >= 3 ){  
	Ch1 = "";  
	Ch2 = "";  
	nZero = nZero + 1;  
	}  
	else{  
	Ch1 = "";  
	Ch2 = String2.substr(i, 1);  
	nZero = nZero + 1;  
	}  
	if( i == (len - 11) || i == (len - 3)){ // 如果该位是亿位或元位，则必须写上  
	Ch2 = String2.substr(i, 1);  
	}  
	}  
	chineseValue = chineseValue + Ch1 + Ch2;  
	}  
	if ( String3 == 0 ){ // 最后一位（分）为0时，加上“整”  
	chineseValue = chineseValue + "整";  
	}  
	return chineseValue;  
	}  