package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ActReProcdef entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_RE_PROCDEF", schema = "HPOA", uniqueConstraints = @UniqueConstraint(columnNames = {
		"KEY_", "VERSION_", "TENANT_ID_" }))
public class ActReProcdef implements java.io.Serializable {

	// Fields

	private String id;
	private BigDecimal rev;
	private String category;
	private String name;
	private String key;
	private BigDecimal version;
	private String deploymentId;
	private String resourceName;
	private String dgrmResourceName;
	private String description;
	private Boolean hasStartFormKey;
	private Boolean hasGraphicalNotation;
	private BigDecimal suspensionState;
	private String tenantId;
	private Set<ActRuTask> actRuTasks = new HashSet<ActRuTask>(0);
	private Set<ActRuIdentitylink> actRuIdentitylinks = new HashSet<ActRuIdentitylink>(
			0);
	private Set<ActRuExecution> actRuExecutions = new HashSet<ActRuExecution>(0);
	private Set<ActProcdefInfo> actProcdefInfos = new HashSet<ActProcdefInfo>(0);

	// Constructors

	/** default constructor */
	public ActReProcdef() {
	}

	/** minimal constructor */
	public ActReProcdef(String id, String key, BigDecimal version) {
		this.id = id;
		this.key = key;
		this.version = version;
	}

	/** full constructor */
	public ActReProcdef(String id, BigDecimal rev, String category,
			String name, String key, BigDecimal version, String deploymentId,
			String resourceName, String dgrmResourceName, String description,
			Boolean hasStartFormKey, Boolean hasGraphicalNotation,
			BigDecimal suspensionState, String tenantId,
			Set<ActRuTask> actRuTasks,
			Set<ActRuIdentitylink> actRuIdentitylinks,
			Set<ActRuExecution> actRuExecutions,
			Set<ActProcdefInfo> actProcdefInfos) {
		this.id = id;
		this.rev = rev;
		this.category = category;
		this.name = name;
		this.key = key;
		this.version = version;
		this.deploymentId = deploymentId;
		this.resourceName = resourceName;
		this.dgrmResourceName = dgrmResourceName;
		this.description = description;
		this.hasStartFormKey = hasStartFormKey;
		this.hasGraphicalNotation = hasGraphicalNotation;
		this.suspensionState = suspensionState;
		this.tenantId = tenantId;
		this.actRuTasks = actRuTasks;
		this.actRuIdentitylinks = actRuIdentitylinks;
		this.actRuExecutions = actRuExecutions;
		this.actProcdefInfos = actProcdefInfos;
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

	@Column(name = "CATEGORY_")
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "NAME_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "KEY_", nullable = false)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "VERSION_", nullable = false, precision = 22, scale = 0)
	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	@Column(name = "DEPLOYMENT_ID_")
	public String getDeploymentId() {
		return this.deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	@Column(name = "RESOURCE_NAME_")
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "DGRM_RESOURCE_NAME_", length = 4000)
	public String getDgrmResourceName() {
		return this.dgrmResourceName;
	}

	public void setDgrmResourceName(String dgrmResourceName) {
		this.dgrmResourceName = dgrmResourceName;
	}

	@Column(name = "DESCRIPTION_")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "HAS_START_FORM_KEY_", precision = 1, scale = 0)
	public Boolean getHasStartFormKey() {
		return this.hasStartFormKey;
	}

	public void setHasStartFormKey(Boolean hasStartFormKey) {
		this.hasStartFormKey = hasStartFormKey;
	}

	@Column(name = "HAS_GRAPHICAL_NOTATION_", precision = 1, scale = 0)
	public Boolean getHasGraphicalNotation() {
		return this.hasGraphicalNotation;
	}

	public void setHasGraphicalNotation(Boolean hasGraphicalNotation) {
		this.hasGraphicalNotation = hasGraphicalNotation;
	}

	@Column(name = "SUSPENSION_STATE_", precision = 22, scale = 0)
	public BigDecimal getSuspensionState() {
		return this.suspensionState;
	}

	public void setSuspensionState(BigDecimal suspensionState) {
		this.suspensionState = suspensionState;
	}

	@Column(name = "TENANT_ID_")
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actReProcdef")
	public Set<ActRuTask> getActRuTasks() {
		return this.actRuTasks;
	}

	public void setActRuTasks(Set<ActRuTask> actRuTasks) {
		this.actRuTasks = actRuTasks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actReProcdef")
	public Set<ActRuIdentitylink> getActRuIdentitylinks() {
		return this.actRuIdentitylinks;
	}

	public void setActRuIdentitylinks(Set<ActRuIdentitylink> actRuIdentitylinks) {
		this.actRuIdentitylinks = actRuIdentitylinks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actReProcdef")
	public Set<ActRuExecution> getActRuExecutions() {
		return this.actRuExecutions;
	}

	public void setActRuExecutions(Set<ActRuExecution> actRuExecutions) {
		this.actRuExecutions = actRuExecutions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actReProcdef")
	public Set<ActProcdefInfo> getActProcdefInfos() {
		return this.actProcdefInfos;
	}

	public void setActProcdefInfos(Set<ActProcdefInfo> actProcdefInfos) {
		this.actProcdefInfos = actProcdefInfos;
	}

}