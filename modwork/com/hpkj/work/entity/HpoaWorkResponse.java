package com.hpkj.work.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaWorkResponse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_WORK_RESPONSE", schema = "HPOA")
public class HpoaWorkResponse implements java.io.Serializable {

	// Fields

	private String responseId;
	private String subassignId;
	private String receiverId;
	private String title;
	private String content;
	private String bak1;
	private String bak2;
	private String bak3;
	private String orgId;
	private String staffId;
	private String responseTime;
	private String percent;

	// Constructors

	/** default constructor */
	public HpoaWorkResponse() {
	}

	/** minimal constructor */
	public HpoaWorkResponse(String responseId) {
		this.responseId = responseId;
	}

	/** full constructor */
	public HpoaWorkResponse(String responseId, String subassignId,
			String receiverId, String title, String content, String bak1,
			String bak2, String bak3, String orgId, String staffId, String responseTime) {
		this.responseId = responseId;
		this.subassignId = subassignId;
		this.receiverId = receiverId;
		this.title = title;
		this.content = content;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
		this.orgId = orgId;
		this.staffId = staffId;
		this.responseTime=responseTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "RESPONSE_ID", unique = true, nullable = false, length = 32)
	public String getResponseId() {
		return this.responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	@Column(name = "SUBASSIGN_ID", length = 32)
	public String getSubassignId() {
		return this.subassignId;
	}

	public void setSubassignId(String subassignId) {
		this.subassignId = subassignId;
	}

	@Column(name = "RECEIVER_ID", length = 32)
	public String getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	@Column(name = "TITLE", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "BAK1", length = 32)
	public String getBak1() {
		return this.bak1;
	}

	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

	@Column(name = "BAK2", length = 32)
	public String getBak2() {
		return this.bak2;
	}

	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}

	@Column(name = "BAK3", length = 32)
	public String getBak3() {
		return this.bak3;
	}

	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "STAFF_ID", length = 32)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "RESPONSE_TIME", length = 20)
	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	@Column(name = "PERCENT", length = 4)
	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

}