package com.hpkj.message.vo;

public class SendMessageDetailVo {
	private String title;//短消息主题
	private String recevername;//接收者名称
	private String sendtime;//发送时间
	private String content;//短信内容
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRecevername() {
		return recevername;
	}
	public void setRecevername(String recevername) {
		this.recevername = recevername;
	}
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		if(content==null){
			content="";
		}
		this.content = content;
	}
}
