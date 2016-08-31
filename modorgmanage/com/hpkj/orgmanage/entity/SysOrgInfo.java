package com.hpkj.orgmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysOrgInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ORG_INFO", schema = "HPOA")
public class SysOrgInfo implements java.io.Serializable {

	// Fields

	private String orgId;
	private String orgName;
	private String orgAllName;
	private String orgPid;
	private Integer orgDepid;
	private Integer orgCompid;
	private String bak1;
	private String bak2;
	private String bak3;

	// Constructors

	/** default constructor */
	public SysOrgInfo() {
	}

	/** minimal constructor */
	public SysOrgInfo(String orgId) {
		this.orgId = orgId;
	}

	/** full constructor */
	public SysOrgInfo(String orgId, String orgName, String orgAllName,
			 String orgPid, Integer orgDepid,
			Integer orgCompid, String bak1, String bak2, String bak3) {
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgAllName = orgAllName;
		this.orgPid = orgPid;
		this.orgDepid = orgDepid;
		this.orgCompid = orgCompid;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ORG_ID", unique = true, nullable = false, length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_NAME", length = 200)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "ORG_ALL_NAME", length = 200)
	public String getOrgAllName() {
		return this.orgAllName;
	}

	public void setOrgAllName(String orgAllName) {
		this.orgAllName = orgAllName;
	}

	@Column(name = "ORG_PID", length = 32)
	public String getOrgPid() {
		return this.orgPid;
	}

	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}

	@Column(name = "ORG_DEPID", precision = 9, scale = 0)
	public Integer getOrgDepid() {
		return this.orgDepid;
	}

	public void setOrgDepid(Integer orgDepid) {
		this.orgDepid = orgDepid;
	}

	@Column(name = "ORG_COMPID", precision = 9, scale = 0)
	public Integer getOrgCompid() {
		return orgCompid;
	}

	public void setOrgCompid(Integer orgCompid) {
		this.orgCompid = orgCompid;
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

}