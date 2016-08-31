
    
    //设置登录窗口
    function openPwd() {
        $('#w').window({
            title: '修改密码',
            width: 300,
            modal: true,
            shadow: true,
            closed: true,
            height: 200,
            resizable:false
        });
    }
    //关闭登录窗口
    function closePwd() {
        $('#w').window('close');
    }

    

    //修改密码
    function serverLogin() {
        var newpwd = $('#txtNewPass');
        var oldpwd = $('#txtOldPass');
        var repwd = $('#txtRePass');
        if (newpwd.val() == '') {
            msgShow('系统提示', '请输入密码！', 'warning');
            return false;
        }
        if (oldpwd.val() == '') {
            msgShow('系统提示', '请在一次输入密码！', 'warning');
            return false;
        }

        if (newpwd.val() != repwd.val()) {
            msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
            return false;
        }

        $.post("/HPOA/menu/jsonmodifypwd.json", {newpwd:newpwd.val(),oldpwd:oldpwd.val()}, function(msg) {
        	console.log(msg.flag);
        	if(msg.flag==0){
        		msgShow('系统提示', '系统繁忙，修改失败');
        		return false;
        	}
        	if(msg.flag==1){
        		alert('恭喜，密码修改成功！请重新登录');
        		window.top.location.href="/HPOA/login/loginOut";
        	}
        	if(msg.flag==2){
        		msgShow('系统提示', '旧密码错误，修改失败');
        		return false;
        	}
        	if(msg.flag==3){
        		msgShow('系统提示', '旧密码或新密码不能为空，修改失败');
        		return false;
        	}
        	if(msg.flag==4){
        		msgShow('系统提示', '用户id为空，修改失败');
        		return false;
        	}
            close();
        });
        
    }

    $(function() {

        openPwd();

        $('#editpass').click(function() {
            $('#w').window('open');
        });

        $('#btnEp').click(function() {
            serverLogin();
        })

		$('#btnCancel').click(function(){closePwd();})

        $('#loginOut').click(function() {
            $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
                if (r) {
                    window.parent.document.location.href="/HPOA/login/loginOut";
                }
            });
        });
    });
	$(document).ready(function(e){
		$.ajax({
		url : "message/jsonNoReadMessage.json",
		type : "POST",
		success : function(data){
				var messages="&nbsp;";
				var wordcount=0;
				for(var i=0;i<data.noRead.length;i++){
					//messages="<label>"+messages+data.noRead[i].senderName+"给您发来的：</label><a href='/HPOA/message/goReceiveDetail?readstatus="+data.noRead[i].readstatus+"&messageid="+data.noRead[i].messageId+"'>"+data.noRead[i].title+"</a>&nbsp;";
					messages="<label>"+messages+data.noRead[i].senderName+"给您发来的：</label><a href='javascript:void(0)' onclick=\"viewMessage('"+data.noRead[i].messageId+"','"+data.noRead[i].readstatus+"')\">"+data.noRead[i].title+"</a>&nbsp;";
					wordcount=wordcount+data.noRead[i].senderName.length+data.noRead[i].title.length+7;
				}
				
				$("#messagescroll").html(messages);
				$("#messagescroll").css("height",Number($("#messagescroll").css("font-size").substring(0,$("#messagescroll").css("font-size").indexOf("p")))+Number($("#messagescroll").css("padding-top").substring(0,$("#messagescroll").css("padding-top").indexOf("p"))));
				//div最大允许宽度(包括左右内填充)
				var divwidth=$("#messagescroll").css("width").substring(0,$("#messagescroll").css("width").indexOf("p"))-$("#messagescroll").css("padding-right").substring(0,$("#messagescroll").css("padding-right").indexOf("p"));
				//链接宽度
				var linkwidth=0;
				if($("#messagescroll a").css("font-size")!=undefined){
					linkwidth=wordcount*$("#messagescroll a").css("font-size").substring(0,2);
				}
				//链接宽度大于div宽度再加载滚动条
				if(linkwidth>divwidth){
					var elem = document.getElementById("messagescroll");
					var sc = new HorizontalScroll(elem);
				}
			}
		});
		$.ajax({
		url : "notice/getNoticeScrollList.json",
		type : "POST",
		success : function(data){
				var notice="";
				var licount=0;
				for(var i=0;i<data.noticeScroll.length;i++){
					//notice=notice+"<li><a href='notice/goReadNotice?noticeid="+data.noticeScroll[i].noticeid+"' title='"+data.noticeScroll[i].realTitle+"'>"+data.noticeScroll[i].title+"</a></li>";
					notice=notice+"<li><a href='javascript:void(0)' onclick=viewNotice('"+data.noticeScroll[i].noticeid+"') title='"+data.noticeScroll[i].realTitle+"'>"+data.noticeScroll[i].title+"</a></li>";
					licount++;
				}
				$("#listnotice").html(notice);
				if($("#listnotice li").css("height")!=undefined){
					if($("li").css("height").substring(0,2)*licount>$("#listnotice").css("height").substring(0,$("#listnotice").css("height").indexOf('p'))){
						var scroll2 = new ScrollText("listnotice", true,70, true);
					}
				}
			}
		});
		if($("#status").val()==1){
			 $('#w').window('open');
		}
	});
	function viewNotice(noticeid){
		if($("#tabs").tabs('exists','查看公告')){
			$("#tabs").tabs('close','查看公告');
		}
		$("#tabs").tabs('add',{title:'查看公告',closable:true,href:'notice/goReadNotice?noticeid='+noticeid,closeable:'true'});
	}
	function viewMessage(messageid,readstatus){
		if($("#tabs").tabs('exists','短信箱')){
			$("#tabs").tabs('close','短信箱');
		}
		$("#tabs").tabs('add',{title:'短信箱',closable:true,content:'<iframe scrolling="auto" frameborder="0" src="/HPOA/message/goReceiveDetail?readstatus='+readstatus+'&messageid='+messageid+'" style="width:100%;height:100%;"></iframe>',closeable:'true'});
	}