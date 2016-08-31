package com.hpkj.work.vo;

import java.util.List;

public class ReceiveWorkDetailVo {
	private String subAssignid;//工作任务id
	private String title;//工作标题
	private String content;//工作全部内容
	private String subContent;//任务正文
	private String workStartTime;//工作开始时间
	private String subWorkStartTime;//任务分配时间
	private String workPercent;//工作总进度
	private String subWorkPercent;//任务进度
	private String publishOrgName;//任务发布部门
	private String status;//工作任务状态
	public String getSubAssignid() {
		return subAssignid;
	}
	public void setSubAssignid(String subAssignid) {
		this.subAssignid = subAssignid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubContent() {
		return subContent;
	}
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}
	public String getWorkStartTime() {
		return workStartTime;
	}
	public void setWorkStartTime(String workStartTime) {
		this.workStartTime = workStartTime;
	}
	public String getSubWorkStartTime() {
		return subWorkStartTime;
	}
	public void setSubWorkStartTime(String subWorkStartTime) {
		this.subWorkStartTime = subWorkStartTime;
	}
	public String getWorkPercent() {
		return workPercent;
	}
	public void setWorkPercent(String workPercent) {
		this.workPercent = workPercent;
	}
	public String getSubWorkPercent() {
		return subWorkPercent;
	}
	public void setSubWorkPercent(String subWorkPercent) {
		this.subWorkPercent = subWorkPercent;
	}
	public String getPublishOrgName() {
		return publishOrgName;
	}
	public void setPublishOrgName(String publishOrgName) {
		this.publishOrgName = publishOrgName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
