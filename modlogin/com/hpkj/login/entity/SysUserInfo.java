package com.hpkj.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER_INFO", schema = "HPOA")
public class SysUserInfo implements java.io.Serializable {

	// Fields

	private String userId;
	private String userAccount;
	private String userPad;
	private String userNumber;
	private String userName;
	private Integer userState;
	private String bak1;
	private String bak2;

	// Constructors

	/** default constructor */
	public SysUserInfo() {
	}

	/** minimal constructor */
	public SysUserInfo(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public SysUserInfo(String userId, String userAccount, String userPad,
			String userNumber, String userName, Integer userState, String bak1,
			String bak2) {
		this.userId = userId;
		this.userAccount = userAccount;
		this.userPad = userPad;
		this.userNumber = userNumber;
		this.userName = userName;
		this.userState = userState;
		this.bak1 = bak1;
		this.bak2 = bak2;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "USER_ID", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USER_ACCOUNT", length = 32)
	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name = "USER_PAD", length = 40)
	public String getUserPad() {
		return this.userPad;
	}

	public void setUserPad(String userPad) {
		this.userPad = userPad;
	}

	@Column(name = "USER_NUMBER", length = 32)
	public String getUserNumber() {
		return this.userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	@Column(name = "USER_NAME", length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_STATE", precision = 1, scale = 0)
	public Integer getUserState() {
		return this.userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
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

}