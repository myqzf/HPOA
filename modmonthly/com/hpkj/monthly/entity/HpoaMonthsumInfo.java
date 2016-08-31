package com.hpkj.monthly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaMonthsumInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_MONTHSUM_INFO", schema = "HPOA")
public class HpoaMonthsumInfo implements java.io.Serializable {

	// Fields

	private String monthsumid;
	private String staffId;
	private String monthscope;
	private String sumlink;
	private String monthtitle;
	private String monthcontent;
	private String uploaddate;
	private String bak1;//所属年份
	private String bak2;
	private String bak3;

	// Constructors

	/** default constructor */
	public HpoaMonthsumInfo() {
	}

	/** minimal constructor */
	public HpoaMonthsumInfo(String monthsumid) {
		this.monthsumid = monthsumid;
	}

	/** full constructor */
	public HpoaMonthsumInfo(String monthsumid, String staffId,
			String monthscope, String sumlink, String monthtitle,
			String monthcontent, String uploaddate, String bak1, String bak2,
			String bak3) {
		this.monthsumid = monthsumid;
		this.staffId = staffId;
		this.monthscope = monthscope;
		this.sumlink = sumlink;
		this.monthtitle = monthtitle;
		this.monthcontent = monthcontent;
		this.uploaddate = uploaddate;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "MONTHSUMID", unique = true, nullable = false, length = 32)
	public String getMonthsumid() {
		return this.monthsumid;
	}

	public void setMonthsumid(String monthsumid) {
		this.monthsumid = monthsumid;
	}

	@Column(name = "STAFF_ID", length = 32)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "MONTHSCOPE", length = 32)
	public String getMonthscope() {
		return this.monthscope;
	}

	public void setMonthscope(String monthscope) {
		this.monthscope = monthscope;
	}

	@Column(name = "SUMLINK", length = 2000)
	public String getSumlink() {
		return this.sumlink;
	}

	public void setSumlink(String sumlink) {
		this.sumlink = sumlink;
	}

	@Column(name = "MONTHTITLE", length = 200)
	public String getMonthtitle() {
		return this.monthtitle;
	}

	public void setMonthtitle(String monthtitle) {
		this.monthtitle = monthtitle;
	}

	@Column(name = "MONTHCONTENT")
	public String getMonthcontent() {
		return this.monthcontent;
	}

	public void setMonthcontent(String monthcontent) {
		this.monthcontent = monthcontent;
	}

	@Column(name = "UPLOADDATE", length = 20)
	public String getUploaddate() {
		return this.uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
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