package com.hpkj.message.vo;

public class NoSendMessageVo {
	private String messageId;//短消息ID
	private String title;//短消息主题
	private String receversName;//接收者名称
	private String createTime;//创建时间
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReceversName() {
		return receversName;
	}
	public void setReceversName(String receversName) {
		this.receversName = receversName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
