package com.hpkj.notice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaNoticeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_NOTICE_INFO", schema = "HPOA")
public class HpoaNoticeInfo implements java.io.Serializable {

	// Fields

	private String noticeid;
	private String noticetime;
	private String content;
	private String author;
	private String staffId;
	private String remark;
	private String istop;
	private String isnew;
	private String bak1;
	private String bak2;
	private String bak3;
	private String staffComp;
	private String title;

	// Constructors

	/** default constructor */
	public HpoaNoticeInfo() {
	}

	/** minimal constructor */
	public HpoaNoticeInfo(String noticeid) {
		this.noticeid = noticeid;
	}

	/** full constructor */
	public HpoaNoticeInfo(String noticeid, String noticetime, String content,
			String author, String staffId, String remark, String istop,
			String isnew, String bak1, String bak2, String bak3,
			String staffComp, String title) {
		this.noticeid = noticeid;
		this.noticetime = noticetime;
		this.content = content;
		this.author = author;
		this.staffId = staffId;
		this.remark = remark;
		this.istop = istop;
		this.isnew = isnew;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
		this.staffComp = staffComp;
		this.title=title;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "NOTICEID", unique = true, nullable = false, length = 32)
	public String getNoticeid() {
		return this.noticeid;
	}

	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}

	@Column(name = "NOTICETIME", length = 20)
	public String getNoticetime() {
		return this.noticetime;
	}

	public void setNoticetime(String noticetime) {
		this.noticetime = noticetime;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "AUTHOR", length = 20)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "STAFF_ID", length = 32)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ISTOP", length = 2)
	public String getIstop() {
		return this.istop;
	}

	public void setIstop(String istop) {
		this.istop = istop;
	}

	@Column(name = "ISNEW", length = 2)
	public String getIsnew() {
		return this.isnew;
	}

	public void setIsnew(String isnew) {
		this.isnew = isnew;
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

	@Column(name = "STAFF_COMP", length = 32)
	public String getStaffComp() {
		return this.staffComp;
	}

	public void setStaffComp(String staffComp) {
		this.staffComp = staffComp;
	}

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}