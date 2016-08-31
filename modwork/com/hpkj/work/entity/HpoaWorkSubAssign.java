package com.hpkj.work.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaWorkSubAssign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_WORK_SUB_ASSIGN", schema = "HPOA")
public class HpoaWorkSubAssign implements java.io.Serializable {

	// Fields

	private String subassignId;
	private String assignId;
	private String content;
	private String percent;
	private String status;
	private String starttime;
	private String endtime;
	private String bak1;
	private String bak2;
	private String bak3;
	private String suborgId;
	private String receverId;

	// Constructors

	/** default constructor */
	public HpoaWorkSubAssign() {
	}

	/** minimal constructor */
	public HpoaWorkSubAssign(String subassignId) {
		this.subassignId = subassignId;
	}

	/** full constructor */
	public HpoaWorkSubAssign(String subassignId, String assignId,
			String content, String percent, String status, String starttime,
			String endtime, String bak1, String bak2, String bak3,
			String suborgId, String receverId) {
		this.subassignId = subassignId;
		this.assignId = assignId;
		this.content = content;
		this.percent = percent;
		this.status = status;
		this.starttime = starttime;
		this.endtime = endtime;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
		this.suborgId = suborgId;
		this.receverId = receverId;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "SUBASSIGN_ID", unique = true, nullable = false, length = 32)
	public String getSubassignId() {
		return this.subassignId;
	}

	public void setSubassignId(String subassignId) {
		this.subassignId = subassignId;
	}

	@Column(name = "ASSIGN_ID", length = 32)
	public String getAssignId() {
		return this.assignId;
	}

	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}

	@Column(name = "CONTENT", length = 2000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "PERCENT", length = 4)
	public String getPercent() {
		return this.percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STARTTIME", length = 20)
	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	@Column(name = "ENDTIME", length = 20)
	public String getEndtime() {
		return this.endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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

	@Column(name = "SUBORG_ID", length = 32)
	public String getSuborgId() {
		return this.suborgId;
	}

	public void setSuborgId(String suborgId) {
		this.suborgId = suborgId;
	}

	@Column(name = "RECEVER_ID", length = 32)
	public String getReceverId() {
		return this.receverId;
	}

	public void setReceverId(String receverId) {
		this.receverId = receverId;
	}

}