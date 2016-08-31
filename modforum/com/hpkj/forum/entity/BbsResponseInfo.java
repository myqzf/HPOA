package com.hpkj.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * BbsResponseInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BBS_RESPONSE_INFO", schema = "HPOA")
public class BbsResponseInfo implements java.io.Serializable {

	// Fields

	private String rid;//跟帖ID
	private String rcid;//所属主贴
	private String ruid;//跟帖人
	private String rtitle;//跟帖标题
	private String rcontent;//回复内容
	private String rtime;//回复时间
	private String bak1;//
	private String bak2;//
	private String bak3;//
	private String psUrl;//回复人图片地址

	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "RID", unique = true, nullable = false, length = 32)
	public String getRid() {
		return this.rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	@Column(name = "RCID", length = 32)
	public String getRcid() {
		return this.rcid;
	}

	public void setRcid(String rcid) {
		this.rcid = rcid;
	}

	@Column(name = "RUID", length = 32)
	public String getRuid() {
		return this.ruid;
	}

	public void setRuid(String ruid) {
		this.ruid = ruid;
	}

	@Column(name = "RTITLE", length = 200)
	public String getRtitle() {
		return this.rtitle;
	}

	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}

	@Column(name = "RCONTENT")
	public String getRcontent() {
		return this.rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	@Column(name = "RTIME", length = 20)
	public String getRtime() {
		return this.rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
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

	@Transient
	public String getPsUrl() {
		return psUrl;
	}

	public void setPsUrl(String psUrl) {
		this.psUrl = psUrl;
	}

	
	
}