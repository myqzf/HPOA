package com.hpkj.process.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActIdInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_ID_INFO", schema = "HPOA")
public class ActIdInfo implements java.io.Serializable {

	// Fields

	private String id;
	private BigDecimal rev;
	private String userId;
	private String type;
	private String key;
	private String value;
	private String password;
	private String parentId;

	// Constructors

	/** default constructor */
	public ActIdInfo() {
	}

	/** minimal constructor */
	public ActIdInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActIdInfo(String id, BigDecimal rev, String userId, String type,
			String key, String value, String password, String parentId) {
		this.id = id;
		this.rev = rev;
		this.userId = userId;
		this.type = type;
		this.key = key;
		this.value = value;
		this.password = password;
		this.parentId = parentId;
	}

	// Property accessors
	@Id
	@Column(name = "ID_", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	@Column(name = "USER_ID_")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "TYPE_")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "KEY_")
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "VALUE_")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "PASSWORD_")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PARENT_ID_")
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}