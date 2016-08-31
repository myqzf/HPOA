package com.hpkj.work.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaWorkAssign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_WORK_ASSIGN", schema = "HPOA")
public class HpoaWorkAssign implements java.io.Serializable {

	// Fields

	private String assignId;
	private String orgId;
	private String title;
	private String content;
	private String totalpercent;
	private String status;
	private String starttime;
	private String endtime;
	private String bak1;
	private String bak2;
	private String bak3;

	// Constructors

	/** default constructor */
	public HpoaWorkAssign() {
	}

	/** minimal constructor */
	public HpoaWorkAssign(String assignId) {
		this.assignId = assignId;
	}

	/** full constructor */
	public HpoaWorkAssign(String assignId, String orgId, String title,
			String content, String totalpercent, String status,
			String starttime, String endtime, String bak1, String bak2,
			String bak3) {
		this.assignId = assignId;
		this.orgId = orgId;
		this.title = title;
		this.content = content;
		this.totalpercent = totalpercent;
		this.status = status;
		this.starttime = starttime;
		this.endtime = endtime;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ASSIGN_ID", unique = true, nullable = false, length = 32)
	public String getAssignId() {
		return this.assignId;
	}

	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	@Column(name = "TOTALPERCENT", length = 4)
	public String getTotalpercent() {
		return this.totalpercent;
	}

	public void setTotalpercent(String totalpercent) {
		this.totalpercent = totalpercent;
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

	@Column(name = "BAK1", length = 2000)
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

}