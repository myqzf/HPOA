package com.hpkj.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * BbsCardInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BBS_CARD_INFO", schema = "HPOA")
public class BbsCardInfo implements java.io.Serializable {

	// Fields

	private String cid;//帖子ID
	private String csid;//所属版块
	private String cuid;//用户ID
	private String ctitle;//标题
	private Integer cclickcount;//点击数
	private String ctime;//发帖时间
	private String cflag;//状态（自定义）
	private String clastr;//最后回复人ID
	private String clastrtime;//最后回复时间
	private String cisnew;//是否最新发布
	private String cistop;//是否置顶
	private String bak1;//
	private String bak2;//
	private String bak3;//
	private String ccontent;//主贴内容
	private Integer userPoint;//员工积分
	private String bbsLevel;//员工等级称号


	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "CID", unique = true, nullable = false, length = 32)
	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Column(name = "CSID", length = 32)
	public String getCsid() {
		return this.csid;
	}

	public void setCsid(String csid) {
		this.csid = csid;
	}

	@Column(name = "CUID", length = 32)
	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	@Column(name = "CTITLE", length = 200)
	public String getCtitle() {
		return this.ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	@Column(name = "CCLICKCOUNT", precision = 5, scale = 0)
	public Integer getCclickcount() {
		return this.cclickcount;
	}

	public void setCclickcount(Integer cclickcount) {
		this.cclickcount = cclickcount;
	}

	@Column(name = "CTIME", length = 20)
	public String getCtime() {
		return this.ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	@Column(name = "CFLAG", length = 2)
	public String getCflag() {
		return this.cflag;
	}

	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

	@Column(name = "CLASTR", length = 32)
	public String getClastr() {
		return this.clastr;
	}

	public void setClastr(String clastr) {
		this.clastr = clastr;
	}

	@Column(name = "CLASTRTIME", length = 20)
	public String getClastrtime() {
		return this.clastrtime;
	}

	public void setClastrtime(String clastrtime) {
		this.clastrtime = clastrtime;
	}

	@Column(name = "CISNEW", length = 2)
	public String getCisnew() {
		return this.cisnew;
	}

	public void setCisnew(String cisnew) {
		this.cisnew = cisnew;
	}

	@Column(name = "CISTOP", length = 2)
	public String getCistop() {
		return this.cistop;
	}

	public void setCistop(String cistop) {
		this.cistop = cistop;
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

	@Column(name = "CCONTENT")
	public String getCcontent() {
		return this.ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	@Transient
	public Integer getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(Integer userPoint) {
		this.userPoint = userPoint;
	}

	@Transient
	public String getBbsLevel() {
		return bbsLevel;
	}

	public void setBbsLevel(String bbsLevel) {
		this.bbsLevel = bbsLevel;
	}
	
	

}