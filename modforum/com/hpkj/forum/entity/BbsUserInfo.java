package com.hpkj.forum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BbsUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BBS_USER_INFO", schema = "HPOA")
public class BbsUserInfo implements java.io.Serializable {

	// Fields

	private String bbsUserId;//BBS用户id
	private String staffId;//OA系统用户ID
	private String nickName;//昵称
	private String signContent;//签名档
	private String popstar;//星级
	private Integer point;//积分
	private String bbsLevel;//等级称号
	private String psurl;//头像链接
	private String isMaster;//是否为版主
	private String state;//状态（自定义）
	private String remark;//备注
	private String bak1;//备用一
	private String bak2;//
	private String bak3;	//
	
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "BBSUSERID", unique = true, nullable = false, length = 32)
	public String getBbsUserId() {
		return bbsUserId;
	}

	public void setBbsUserId(String bbsUserId) {
		this.bbsUserId = bbsUserId;
	}

	@Column(name = "STAFF_ID", length = 32)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	
	@Column(name = "NICKNAME", length = 32)
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "SIGNCONTENT", length = 2000)
	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}

	
	@Column(name = "POPSTAR", length = 10)
	public String getPopstar() {
		return this.popstar;
	}

	public void setPopstar(String popstar) {
		this.popstar = popstar;
	}

	@Column(name = "POINT", precision = 8, scale = 0)
	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
	
	
	
	@Column(name = "BBSLEVEL", length = 32)
	public String getBbsLevel() {
		return bbsLevel;
	}

	public void setBbsLevel(String bbsLevel) {
		this.bbsLevel = bbsLevel;
	}

	
	@Column(name = "ISMASTER", length = 2)
	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	@Column(name = "PSURL", length = 2000)
	public String getPsurl() {
		return this.psurl;
	}

	public void setPsurl(String psurl) {
		this.psurl = psurl;
	}




	@Column(name = "STATE", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "REMARK", length = 400)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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