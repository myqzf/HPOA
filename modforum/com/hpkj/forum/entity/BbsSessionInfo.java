package com.hpkj.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * BbsSessionInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BBS_SESSION_INFO", schema = "HPOA")
public class BbsSessionInfo implements java.io.Serializable {

	// Fields

	private String sessionId;//版块ID
	private String sessionName;//版块名称
	private String masterId;//版主ID
	private String sessionDesc;//版块描述
	private String sessionPro;//版块主题
	private Integer topCount;//发帖数
	private Integer clickCount;//点击数
	private String sort;//版块排序
	private String bak1;//
	private String bak2;//
	private String bak3;//
	private String cTitle;//最后发布的帖子
	private String nickName;//帖子作者昵称
	private String cTime;//最后发布的帖子的创建时间
	private String cId;//最后发布的帖子的ID
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "SESSIONID", unique = true, nullable = false, length = 32)
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name = "SESSIONNAME", length = 200)
	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	@Column(name = "MASTERID", length = 32)
	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	@Column(name = "SESSIONDESC", length = 2000)
	public String getSessionDesc() {
		return sessionDesc;
	}

	public void setSessionDesc(String sessionDesc) {
		this.sessionDesc = sessionDesc;
	}

	@Column(name = "SESSIONPRO", length = 200)
	public String getSessionPro() {
		return sessionPro;
	}

	public void setSessionPro(String sessionPro) {
		this.sessionPro = sessionPro;
	}

	@Column(name = "TOPCOUNT", precision = 5, scale = 0)
	public Integer getTopCount() {
		return topCount;
	}

	public void setTopCount(Integer topCount) {
		this.topCount = topCount;
	}

	@Column(name = "CLICKCOUNT", precision = 8, scale = 0)
	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}


	@Column(name = "SORT", length = 4)
	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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
	public String getcTitle() {
		return cTitle;
	}

	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}

	@Transient
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Transient
	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	@Transient
	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}
	
	
	

}