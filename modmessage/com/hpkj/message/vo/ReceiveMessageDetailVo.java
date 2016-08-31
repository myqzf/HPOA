package com.hpkj.message.vo;

public class ReceiveMessageDetailVo {
	private String title;//短消息主题
	private String senderName;//发送者名称
	private String rectime;// 接收时间
	private String content;//短消息内容
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getRectime() {
		return rectime;
	}
	public void setRectime(String rectime) {
		this.rectime = rectime;
	}
	public String getContent() {
		if(content==null){
			content="";
		}
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
