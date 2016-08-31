package com.hpkj.culture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaCultureInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_CULTURE_INFO", schema = "HPOA")
public class HpoaCultureInfo implements java.io.Serializable {

	// Fields

	private String cultureid;
	private String staffComp;
	private String culturetitle;
	private String culturecontent;
	private String releasedate;
	private String bak1;
	private String bak2;
	private String bak3;
	private String staffId;

	// Constructors

	/** default constructor */
	public HpoaCultureInfo() {
	}

	/** minimal constructor */
	public HpoaCultureInfo(String cultureid) {
		this.cultureid = cultureid;
	}

	/** full constructor */
	public HpoaCultureInfo(String cultureid, String staffComp,
			String culturetitle, String culturecontent, String releasedate,
			String bak1, String bak2, String bak3, String staffId) {
		this.cultureid = cultureid;
		this.staffComp = staffComp;
		this.culturetitle = culturetitle;
		this.culturecontent = culturecontent;
		this.releasedate = releasedate;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
		this.staffId = staffId;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "CULTUREID", unique = true, nullable = false, length = 32)
	public String getCultureid() {
		return this.cultureid;
	}

	public void setCultureid(String cultureid) {
		this.cultureid = cultureid;
	}

	@Column(name = "STAFF_COMP", length = 32)
	public String getStaffComp() {
		return this.staffComp;
	}

	public void setStaffComp(String staffComp) {
		this.staffComp = staffComp;
	}

	@Column(name = "CULTURETITLE", length = 90)
	public String getCulturetitle() {
		return this.culturetitle;
	}

	public void setCulturetitle(String culturetitle) {
		this.culturetitle = culturetitle;
	}

	@Column(name = "CULTURECONTENT")
	public String getCulturecontent() {
		return this.culturecontent;
	}

	public void setCulturecontent(String culturecontent) {
		this.culturecontent = culturecontent;
	}

	@Column(name = "RELEASEDATE", length = 32)
	public String getReleasedate() {
		return this.releasedate;
	}

	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
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

	@Column(name = "STAFF_ID", length = 32)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

}