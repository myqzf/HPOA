$(document).ready(function(e){
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
	var x=year+'-'+month+'-'+day;//当前日期
	if($("#defaultTime").val()=='0'||$("#defaultTime").val()==''){
		$("#add").removeAttr("disabled");
    }else{
    	$("#add").attr("disabled","true");
    }
	$('#date').datepicker({
		dateFormat : 'yy-mm-dd',//日期控件的日期格式
		//dayNames : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		//dayNamesShort : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		dayNamesMin : ['日','一','二','三','四','五','六'],
		monthNames : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		monthNamesShort : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		altField : '#time',//将日期控件的日期映射到id为time的text框中
		altFormat : 'yy-mm-dd',//日期控件映射值的格式
		firstDay : 1,
		defaultDate:$("#defaultTime").val(),//设置默认显示日期，是一个整数
		showOtherMonths : true,//显示其他月份的日期
		changeMonth : true, //月份可以选择
		changeYear : true,		//年可以选择
		showMonthAfterYear:true,
		//日期的限制优先级，min和max最高
		maxDate : 0,    //设置当天日期以后的日期不可用
		minDate : $("#staffTime").val(),  //最小可用日期，设置为员工入职时候的日期				
		hideIfNoPrevNext : true,
		yearRange : '1950:2030',//设置可用的年份
		onChangeMonthYear : function (year,month,inst) {   //日期控件年月改变时触发
			$.post(
					"/HPOA/dailyManage/timeList",
					{
					},
					function(data){
						for(var i=0;i<data.dateList.length;i++){
							$('#'+data.dateList[i]).css("color","#FF0000");		//存在日志则设置颜色为红色
							$('#'+data.dateList[i]).attr("title","存在日志");
						}
						
					}
					);
		},
		onSelect:function(dateText,inst){    //日期控件日期改变时触发
			$.post(
					"/HPOA/dailyManage/timeList",
					{
					},
					function(data){
						for(var i=0;i<data.dateList.length;i++){
							$('#'+data.dateList[i]).css("color","#FF0000");		//存在日志则设置颜色为红色
							$('#'+data.dateList[i]).attr("title","存在日志");
						}						
						
					}
					);			
			if($("#"+$("#time").val()).getColor()=="#ff0000"){//若是选定日期的颜色为红色则表示有日志，使查看日志按钮可用，否则不可用
				$("#read").removeAttr("disabled");
			}else{
				$("#read").attr("disabled","true");
			}

			if($("#time").val()==x){              //若当前日期为今天则使撰写日志按钮可用，否则不可用
				$("#add").removeAttr("disabled");				
			}else{
				$("#add").attr("disabled","true");
			}

		}
	});
	
	
	$.post(
			"/HPOA/dailyManage/timeList",
			{
			},
			function(data){
				for(var i=0;i<data.dateList.length;i++){
					$('#'+data.dateList[i]).css("color","#FF0000");				//存在日志则设置颜色为红色
					$('#'+data.dateList[i]).attr("title","存在日志");

				}
			
			}
			);
	if($("#"+$("#time").val()).getColor()=="#ff0000"){
		$("#read").removeAttr("disabled");
	}else{
		$("#read").attr("disabled","true");
	}
	$("#read").click(function(e){		
		window.location.href="/HPOA/daily/gotoReadDaily?time="+$("#time").val();
//		window.open("/HPOA/staff/gotoReadDaily?time="+$("#time").val(),
//				"upload","height=500,width=900,top=400,left=400,alwaysRaised=yes,location=no");
	});
	$("#today").click(function(e){		
		$.post(
				"/HPOA/dailyManage/timeList",
				{
				},
				function(data){
					for(var i=0;i<data.dateList.length;i++){
						$('#'+data.dateList[i]).css("color","#FF0000");				//存在日志则设置颜色为红色
						$('#'+data.dateList[i]).attr("title","存在日志");

					}						
					
				}
				);		
		$('#date').datepicker('setDate', x);
		$("#add").removeAttr("disabled");
		if($("#"+$("#time").val()).getColor()=="#ff0000"){//若是选定日期的颜色为红色则表示有日志，使查看日志按钮可用，否则不可用
			$("#read").removeAttr("disabled");
		}else{
			$("#read").attr("disabled","true");
		}
	});
});
//取颜色值方法
$.fn.getColor = function() {
    var rgb = $(this).css('color');
    if(rgb >= 0) return rgb;//如果是一个hex值则直接返回
   else{
	   rgb=rgb.substring(4,rgb.length-1);
	   rgb=rgb.split(',');	  
//        rgb = rgb.match(/^rgb(\d+),\s∗(\d+),\s∗(\d+)$/);
        function hex(x) {return ("0" + parseInt(x).toString(16)).slice(-2);}
        rgb= "#" + hex(rgb[0]) + hex(rgb[1]) + hex(rgb[2]);
    }
    return rgb;
}
