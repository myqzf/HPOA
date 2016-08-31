package com.hpkj.process.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ActReDeployment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_RE_DEPLOYMENT", schema = "HPOA")
public class ActReDeployment implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String category;
	private String tenantId;
	private Timestamp deployTime;
	private Set<ActGeBytearray> actGeBytearraies = new HashSet<ActGeBytearray>(
			0);
	private Set<ActReModel> actReModels = new HashSet<ActReModel>(0);

	// Constructors

	/** default constructor */
	public ActReDeployment() {
	}

	/** minimal constructor */
	public ActReDeployment(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActReDeployment(String id, String name, String category,
			String tenantId, Timestamp deployTime,
			Set<ActGeBytearray> actGeBytearraies, Set<ActReModel> actReModels) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.tenantId = tenantId;
		this.deployTime = deployTime;
		this.actGeBytearraies = actGeBytearraies;
		this.actReModels = actReModels;
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

	@Column(name = "NAME_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CATEGORY_")
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "TENANT_ID_")
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Column(name = "DEPLOY_TIME_", length = 11)
	public Timestamp getDeployTime() {
		return this.deployTime;
	}

	public void setDeployTime(Timestamp deployTime) {
		this.deployTime = deployTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actReDeployment")
	public Set<ActGeBytearray> getActGeBytearraies() {
		return this.actGeBytearraies;
	}

	public void setActGeBytearraies(Set<ActGeBytearray> actGeBytearraies) {
		this.actGeBytearraies = actGeBytearraies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actReDeployment")
	public Set<ActReModel> getActReModels() {
		return this.actReModels;
	}

	public void setActReModels(Set<ActReModel> actReModels) {
		this.actReModels = actReModels;
	}

}