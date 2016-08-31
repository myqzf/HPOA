package com.hpkj.message.vo;

public class SendMessageVo {
	private String messageid;//短消息ID
	private String title;//短消息主题
	private String recevername;//接收者名称
	private String sendtime;//发送时间
	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
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
}
