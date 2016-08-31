package com.hpkj.reimburse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaReimburseInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_REIMBURSE_INFO", schema = "HPOA")
public class HpoaReimburseInfo implements java.io.Serializable {

	// Fields

	private String id;
	private String parentid;
	private String reimname;
	private String reimsum;
	private String reimmoney;
	private String bak1;
	private String bak2;
	private String bak3;

	// Constructors

	/** default constructor */
	public HpoaReimburseInfo() {
	}

	/** minimal constructor */
	public HpoaReimburseInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public HpoaReimburseInfo(String id, String parentid, String reimname,
			String reimsum, String reimmoney, String bak1, String bak2,
			String bak3) {
		this.id = id;
		this.parentid = parentid;
		this.reimname = reimname;
		this.reimsum = reimsum;
		this.reimmoney = reimmoney;
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

	@Column(name = "PARENTID", length = 32)
	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Column(name = "REIMNAME", length = 32)
	public String getReimname() {
		return this.reimname;
	}

	public void setReimname(String reimname) {
		this.reimname = reimname;
	}

	@Column(name = "REIMSUM", length = 85)
	public String getReimsum() {
		return this.reimsum;
	}

	public void setReimsum(String reimsum) {
		this.reimsum = reimsum;
	}

	@Column(name = "REIMMONEY", length = 32)
	public String getReimmoney() {
		return this.reimmoney;
	}

	public void setReimmoney(String reimmoney) {
		this.reimmoney = reimmoney;
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