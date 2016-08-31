package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ActReModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_RE_MODEL", schema = "HPOA")
public class ActReModel implements java.io.Serializable {

	// Fields

	private String id;
	private ActGeBytearray actGeBytearrayByEditorSourceExtraValueId;
	private ActGeBytearray actGeBytearrayByEditorSourceValueId;
	private ActReDeployment actReDeployment;
	private BigDecimal rev;
	private String name;
	private String key;
	private String category;
	private Timestamp createTime;
	private Timestamp lastUpdateTime;
	private BigDecimal version;
	private String metaInfo;
	private String tenantId;

	// Constructors

	/** default constructor */
	public ActReModel() {
	}

	/** minimal constructor */
	public ActReModel(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActReModel(String id,
			ActGeBytearray actGeBytearrayByEditorSourceExtraValueId,
			ActGeBytearray actGeBytearrayByEditorSourceValueId,
			ActReDeployment actReDeployment, BigDecimal rev, String name,
			String key, String category, Timestamp createTime,
			Timestamp lastUpdateTime, BigDecimal version, String metaInfo,
			String tenantId) {
		this.id = id;
		this.actGeBytearrayByEditorSourceExtraValueId = actGeBytearrayByEditorSourceExtraValueId;
		this.actGeBytearrayByEditorSourceValueId = actGeBytearrayByEditorSourceValueId;
		this.actReDeployment = actReDeployment;
		this.rev = rev;
		this.name = name;
		this.key = key;
		this.category = category;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.version = version;
		this.metaInfo = metaInfo;
		this.tenantId = tenantId;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EDITOR_SOURCE_EXTRA_VALUE_ID_")
	public ActGeBytearray getActGeBytearrayByEditorSourceExtraValueId() {
		return this.actGeBytearrayByEditorSourceExtraValueId;
	}

	public void setActGeBytearrayByEditorSourceExtraValueId(
			ActGeBytearray actGeBytearrayByEditorSourceExtraValueId) {
		this.actGeBytearrayByEditorSourceExtraValueId = actGeBytearrayByEditorSourceExtraValueId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EDITOR_SOURCE_VALUE_ID_")
	public ActGeBytearray getActGeBytearrayByEditorSourceValueId() {
		return this.actGeBytearrayByEditorSourceValueId;
	}

	public void setActGeBytearrayByEditorSourceValueId(
			ActGeBytearray actGeBytearrayByEditorSourceValueId) {
		this.actGeBytearrayByEditorSourceValueId = actGeBytearrayByEditorSourceValueId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPLOYMENT_ID_")
	public ActReDeployment getActReDeployment() {
		return this.actReDeployment;
	}

	public void setActReDeployment(ActReDeployment actReDeployment) {
		this.actReDeployment = actReDeployment;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	@Column(name = "NAME_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "KEY_")
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "CATEGORY_")
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "CREATE_TIME_", length = 11)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "LAST_UPDATE_TIME_", length = 11)
	public Timestamp getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Column(name = "VERSION_", precision = 22, scale = 0)
	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	@Column(name = "META_INFO_")
	public String getMetaInfo() {
		return this.metaInfo;
	}

	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}

	@Column(name = "TENANT_ID_")
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}