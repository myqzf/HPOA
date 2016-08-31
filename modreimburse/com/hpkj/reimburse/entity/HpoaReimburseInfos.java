package com.hpkj.reimburse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaReimburseInfos entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_REIMBURSE_INFOS", schema = "HPOA")
public class HpoaReimburseInfos implements java.io.Serializable {

	// Fields

	private String id;
	private String reidate;
	private Integer reinum;
	private String reimoney;
	private String uppermoney;
	private String staffid;
	private Integer reistatus;
	private String entryname;
	private String remark;
	private Integer reitype;
	private Integer isdel;
	private String bak1;
	private String bak2;
	private String bak3;

	// Constructors

	/** default constructor */
	public HpoaReimburseInfos() {
	}

	/** minimal constructor */
	public HpoaReimburseInfos(String id) {
		this.id = id;
	}

	/** full constructor */
	public HpoaReimburseInfos(String id, String reidate, Integer reinum,
			String reimoney, String uppermoney, String staffid,
			Integer reistatus, String entryname, String remark,
			Integer reitype, Integer isdel, String bak1, String bak2,
			String bak3) {
		this.id = id;
		this.reidate = reidate;
		this.reinum = reinum;
		this.reimoney = reimoney;
		this.uppermoney = uppermoney;
		this.staffid = staffid;
		this.reistatus = reistatus;
		this.entryname = entryname;
		this.remark = remark;
		this.reitype = reitype;
		this.isdel = isdel;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "REIDATE", length = 32)
	public String getReidate() {
		return this.reidate;
	}

	public void setReidate(String reidate) {
		this.reidate = reidate;
	}

	@Column(name = "REINUM", precision = 6, scale = 0)
	public Integer getReinum() {
		return this.reinum;
	}

	public void setReinum(Integer reinum) {
		this.reinum = reinum;
	}

	@Column(name = "REIMONEY", length = 32)
	public String getReimoney() {
		return this.reimoney;
	}

	public void setReimoney(String reimoney) {
		this.reimoney = reimoney;
	}

	@Column(name = "UPPERMONEY", length = 420)
	public String getUppermoney() {
		return this.uppermoney;
	}

	public void setUppermoney(String uppermoney) {
		this.uppermoney = uppermoney;
	}

	@Column(name = "STAFFID", length = 32)
	public String getStaffid() {
		return this.staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	@Column(name = "REISTATUS", precision = 6, scale = 0)
	public Integer getReistatus() {
		return this.reistatus;
	}

	public void setReistatus(Integer reistatus) {
		this.reistatus = reistatus;
	}

	@Column(name = "ENTRYNAME", length = 1000)
	public String getEntryname() {
		return this.entryname;
	}

	public void setEntryname(String entryname) {
		this.entryname = entryname;
	}

	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REITYPE", precision = 6, scale = 0)
	public Integer getReitype() {
		return this.reitype;
	}

	public void setReitype(Integer reitype) {
		this.reitype = reitype;
	}

	@Column(name = "ISDEL", precision = 6, scale = 0)
	public Integer getIsdel() {
		return this.isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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