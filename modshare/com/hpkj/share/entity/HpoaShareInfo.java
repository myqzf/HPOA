package com.hpkj.share.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaShareInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_SHARE_INFO", schema = "HPOA")
public class HpoaShareInfo implements java.io.Serializable {

	// Fields

	private String shareId;
	private String shareTitle;
	private String shareContent;
	private String shareAuthorId;
	private String shareFileName;
	private String shareStatus;
	private String bak1;
	private String bak2;
	private String bak3;
	private String shareRealFileName;
	private String shareTime;

	// Constructors

	/** default constructor */
	public HpoaShareInfo() {
	}

	/** minimal constructor */
	public HpoaShareInfo(String shareId) {
		this.shareId = shareId;
	}

	/** full constructor */
	public HpoaShareInfo(String shareId, String shareTitle,
			String shareContent, String shareAuthorId, String shareFileName,
			String shareStatus, String bak1, String bak2, String bak3, String shareRealFileName, String shareTime) {
		this.shareId = shareId;
		this.shareTitle = shareTitle;
		this.shareContent = shareContent;
		this.shareAuthorId = shareAuthorId;
		this.shareFileName = shareFileName;
		this.shareStatus = shareStatus;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
		this.shareRealFileName=shareRealFileName;
		this.shareTime=shareTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "SHARE_ID", unique = true, nullable = false, length = 32)
	public String getShareId() {
		return this.shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	@Column(name = "SHARE_TITLE", length = 200)
	public String getShareTitle() {
		return this.shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	@Column(name = "SHARE_CONTENT")
	public String getShareContent() {
		return this.shareContent;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

	@Column(name = "SHARE_AUTHOR_ID", length = 32)
	public String getShareAuthorId() {
		return this.shareAuthorId;
	}

	public void setShareAuthorId(String shareAuthorId) {
		this.shareAuthorId = shareAuthorId;
	}

	@Column(name = "SHARE_FILE_NAME", length = 32)
	public String getShareFileName() {
		return this.shareFileName;
	}

	public void setShareFileName(String shareFileName) {
		this.shareFileName = shareFileName;
	}

	@Column(name = "SHARE_STATUS", length = 2)
	public String getShareStatus() {
		return this.shareStatus;
	}

	public void setShareStatus(String shareStatus) {
		this.shareStatus = shareStatus;
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

	@Column(name = "SHARE_REAL_FILE_NAME", length = 255)
	public String getShareRealFileName() {
		return shareRealFileName;
	}

	public void setShareRealFileName(String shareRealFileName) {
		this.shareRealFileName = shareRealFileName;
	}

	@Column(name = "SHARE_TIME", length = 20)
	public String getShareTime() {
		return shareTime;
	}

	public void setShareTime(String shareTime) {
		this.shareTime = shareTime;
	}

}