package com.hpkj.work.vo;

import java.util.List;

public class SubAssignVo {
	private String subAssignid;
	private String content;
	private String receiveOrgName;
	private String receiveStaffName;
	private String percent;
	private String status;
	private String statusmsg;
	private String startTime;
	private String count;
	private List<SimpleResponseVo> resportList;
	public String getSubAssignid() {
		return subAssignid;
	}
	public void setSubAssignid(String subAssignid) {
		this.subAssignid = subAssignid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReceiveOrgName() {
		return receiveOrgName;
	}
	public void setReceiveOrgName(String receiveOrgName) {
		this.receiveOrgName = receiveOrgName;
	}
	public String getReceiveStaffName() {
		return receiveStaffName;
	}
	public void setReceiveStaffName(String receiveStaffName) {
		this.receiveStaffName = receiveStaffName;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusmsg() {
		return statusmsg;
	}
	public void setStatusmsg(String statusmsg) {
		this.statusmsg = statusmsg;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<SimpleResponseVo> getResportList() {
		return resportList;
	}
	public void setResportList(List<SimpleResponseVo> resportList) {
		this.resportList = resportList;
	}
}
