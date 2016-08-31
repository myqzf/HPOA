package com.hpkj.orgmanage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysOrgStaffLink entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ORG_STAFF_LINK", schema = "HPOA")
public class SysOrgStaffLink implements java.io.Serializable {

	// Fields

	private String orgStaffLinkid;
	private String orgId;
	private String staffId;
	private String bak1;
	private String bak2;
	private String bak3;

	// Constructors

	/** default constructor */
	public SysOrgStaffLink() {
	}

	/** minimal constructor */
	public SysOrgStaffLink(String orgStaffLinkid) {
		this.orgStaffLinkid = orgStaffLinkid;
	}

	/** full constructor */
	public SysOrgStaffLink(String orgStaffLinkid, String orgId, String staffId,
			String bak1, String bak2, String bak3) {
		this.orgStaffLinkid = orgStaffLinkid;
		this.orgId = orgId;
		this.staffId = staffId;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ORG_STAFF_LINKID", unique = true, nullable = false, length = 32)
	public String getOrgStaffLinkid() {
		return this.orgStaffLinkid;
	}

	public void setOrgStaffLinkid(String orgStaffLinkid) {
		this.orgStaffLinkid = orgStaffLinkid;
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