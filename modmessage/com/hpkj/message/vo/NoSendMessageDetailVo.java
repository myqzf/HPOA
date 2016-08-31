package com.hpkj.message.vo;

public class NoSendMessageDetailVo {
	private String messageid;//短消息ID
	private String title;//短消息主题
	private String receversName;//接收者名称
	private String receversid;//接收者ID
	private String createTime;//创建时间
	private String content;//短信内容
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
	public String getReceversName() {
		return receversName;
	}
	public void setReceversName(String receversName) {
		this.receversName = receversName;
	}
	public String getReceversid() {
		return receversid;
	}
	public void setReceversid(String receversid) {
		this.receversid = receversid;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}