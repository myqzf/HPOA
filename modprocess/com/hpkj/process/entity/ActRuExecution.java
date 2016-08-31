package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ActRuExecution entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_RU_EXECUTION", schema = "HPOA")
public class ActRuExecution implements java.io.Serializable {

	// Fields

	private String id;
	private ActRuExecution actRuExecutionByParentId;
	private ActRuExecution actRuExecutionBySuperExec;
	private ActReProcdef actReProcdef;
	private ActRuExecution actRuExecutionByProcInstId;
	private BigDecimal rev;
	private String businessKey;
	private String actId;
	private Boolean isActive;
	private Boolean isConcurrent;
	private Boolean isScope;
	private Boolean isEventScope;
	private BigDecimal suspensionState;
	private BigDecimal cachedEntState;
	private String tenantId;
	private String name;
	private Timestamp lockTime;
	private Set<ActRuVariable> actRuVariablesForProcInstId = new HashSet<ActRuVariable>(
			0);
	private Set<ActRuIdentitylink> actRuIdentitylinks = new HashSet<ActRuIdentitylink>(
			0);
	private Set<ActRuVariable> actRuVariablesForExecutionId = new HashSet<ActRuVariable>(
			0);
	private Set<ActRuEventSubscr> actRuEventSubscrs = new HashSet<ActRuEventSubscr>(
			0);
	private Set<ActRuExecution> actRuExecutionsForProcInstId = new HashSet<ActRuExecution>(
			0);
	private Set<ActRuExecution> actRuExecutionsForSuperExec = new HashSet<ActRuExecution>(
			0);
	private Set<ActRuExecution> actRuExecutionsForParentId = new HashSet<ActRuExecution>(
			0);
	private Set<ActRuTask> actRuTasksForProcInstId = new HashSet<ActRuTask>(0);
	private Set<ActRuTask> actRuTasksForExecutionId = new HashSet<ActRuTask>(0);

	// Constructors

	/** default constructor */
	public ActRuExecution() {
	}

	/** minimal constructor */
	public ActRuExecution(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActRuExecution(String id, ActRuExecution actRuExecutionByParentId,
			ActRuExecution actRuExecutionBySuperExec,
			ActReProcdef actReProcdef,
			ActRuExecution actRuExecutionByProcInstId, BigDecimal rev,
			String businessKey, String actId, Boolean isActive,
			Boolean isConcurrent, Boolean isScope, Boolean isEventScope,
			BigDecimal suspensionState, BigDecimal cachedEntState,
			String tenantId, String name, Timestamp lockTime,
			Set<ActRuVariable> actRuVariablesForProcInstId,
			Set<ActRuIdentitylink> actRuIdentitylinks,
			Set<ActRuVariable> actRuVariablesForExecutionId,
			Set<ActRuEventSubscr> actRuEventSubscrs,
			Set<ActRuExecution> actRuExecutionsForProcInstId,
			Set<ActRuExecution> actRuExecutionsForSuperExec,
			Set<ActRuExecution> actRuExecutionsForParentId,
			Set<ActRuTask> actRuTasksForProcInstId,
			Set<ActRuTask> actRuTasksForExecutionId) {
		this.id = id;
		this.actRuExecutionByParentId = actRuExecutionByParentId;
		this.actRuExecutionBySuperExec = actRuExecutionBySuperExec;
		this.actReProcdef = actReProcdef;
		this.actRuExecutionByProcInstId = actRuExecutionByProcInstId;
		this.rev = rev;
		this.businessKey = businessKey;
		this.actId = actId;
		this.isActive = isActive;
		this.isConcurrent = isConcurrent;
		this.isScope = isScope;
		this.isEventScope = isEventScope;
		this.suspensionState = suspensionState;
		this.cachedEntState = cachedEntState;
		this.tenantId = tenantId;
		this.name = name;
		this.lockTime = lockTime;
		this.actRuVariablesForProcInstId = actRuVariablesForProcInstId;
		this.actRuIdentitylinks = actRuIdentitylinks;
		this.actRuVariablesForExecutionId = actRuVariablesForExecutionId;
		this.actRuEventSubscrs = actRuEventSubscrs;
		this.actRuExecutionsForProcInstId = actRuExecutionsForProcInstId;
		this.actRuExecutionsForSuperExec = actRuExecutionsForSuperExec;
		this.actRuExecutionsForParentId = actRuExecutionsForParentId;
		this.actRuTasksForProcInstId = actRuTasksForProcInstId;
		this.actRuTasksForExecutionId = actRuTasksForExecutionId;
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
	@JoinColumn(name = "PARENT_ID_")
	public ActRuExecution getActRuExecutionByParentId() {
		return this.actRuExecutionByParentId;
	}

	public void setActRuExecutionByParentId(
			ActRuExecution actRuExecutionByParentId) {
		this.actRuExecutionByParentId = actRuExecutionByParentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPER_EXEC_")
	public ActRuExecution getActRuExecutionBySuperExec() {
		return this.actRuExecutionBySuperExec;
	}

	public void setActRuExecutionBySuperExec(
			ActRuExecution actRuExecutionBySuperExec) {
		this.actRuExecutionBySuperExec = actRuExecutionBySuperExec;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROC_DEF_ID_")
	public ActReProcdef getActReProcdef() {
		return this.actReProcdef;
	}

	public void setActReProcdef(ActReProcdef actReProcdef) {
		this.actReProcdef = actReProcdef;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROC_INST_ID_")
	public ActRuExecution getActRuExecutionByProcInstId() {
		return this.actRuExecutionByProcInstId;
	}

	public void setActRuExecutionByProcInstId(
			ActRuExecution actRuExecutionByProcInstId) {
		this.actRuExecutionByProcInstId = actRuExecutionByProcInstId;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	@Column(name = "BUSINESS_KEY_")
	public String getBusinessKey() {
		return this.businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	@Column(name = "ACT_ID_")
	public String getActId() {
		return this.actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	@Column(name = "IS_ACTIVE_", precision = 1, scale = 0)
	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "IS_CONCURRENT_", precision = 1, scale = 0)
	public Boolean getIsConcurrent() {
		return this.isConcurrent;
	}

	public void setIsConcurrent(Boolean isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	@Column(name = "IS_SCOPE_", precision = 1, scale = 0)
	public Boolean getIsScope() {
		return this.isScope;
	}

	public void setIsScope(Boolean isScope) {
		this.isScope = isScope;
	}

	@Column(name = "IS_EVENT_SCOPE_", precision = 1, scale = 0)
	public Boolean getIsEventScope() {
		return this.isEventScope;
	}

	public void setIsEventScope(Boolean isEventScope) {
		this.isEventScope = isEventScope;
	}

	@Column(name = "SUSPENSION_STATE_", precision = 22, scale = 0)
	public BigDecimal getSuspensionState() {
		return this.suspensionState;
	}

	public void setSuspensionState(BigDecimal suspensionState) {
		this.suspensionState = suspensionState;
	}

	@Column(name = "CACHED_ENT_STATE_", precision = 22, scale = 0)
	public BigDecimal getCachedEntState() {
		return this.cachedEntState;
	}

	public void setCachedEntState(BigDecimal cachedEntState) {
		this.cachedEntState = cachedEntState;
	}

	@Column(name = "TENANT_ID_")
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Column(name = "NAME_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LOCK_TIME_", length = 11)
	public Timestamp getLockTime() {
		return this.lockTime;
	}

	public void setLockTime(Timestamp lockTime) {
		this.lockTime = lockTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuExecutionByProcInstId")
	public Set<ActRuVariable> getActRuVariablesForProcInstId() {
		return this.actRuVariablesForProcInstId;
	}

	public void setActRuVariablesForProcInstId(
			Set<ActRuVariable> actRuVariablesForProcInstId) {
		this.actRuVariablesForProcInstId = actRuVariablesForProcInstId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuExecution")
	public Set<ActRuIdentitylink> getActRuIdentitylinks() {
		return this.actRuIdentitylinks;
	}

	public void setActRuIdentitylinks(Set<ActRuIdentitylink> actRuIdentitylinks) {
		this.actRuIdentitylinks = actRuIdentitylinks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuExecutionByExecutionId")
	public Set<ActRuVariable> getActRuVariablesForExecutionId() {
		return this.actRuVariablesForExecutionId;
	}

	public void setActRuVariablesForExecutionId(
			Set<ActRuVariable> actRuVariablesForExecutionId) {
		this.actRuVariablesForExecutionId = actRuVariablesForExecutionId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuExecution")
	public Set<ActRuEventSubscr> getActRuEventSubscrs() {
		return this.actRuEventSubscrs;
	}

	public void setActRuEventSubscrs(Set<ActRuEventSubscr> actRuEventSubscrs) {
		this.actRuEventSubscrs = actRuEventSubscrs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuExecutionByProcInstId")
	public Set<ActRuExecution> getActRuExecutionsForProcInstId() {
		return this.actRuExecutionsForProcInstId;
	}

	public void setActRuExecutionsForProcInstId(
			Set<ActRuExecution> actRuExecutionsForProcInstId) {
		this.actRuExecutionsForProcInstId = actRuExecutionsForProcInstId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuExecutionBySuperExec")
	public Set<ActRuExecution> getActRuExecutionsForSuperExec() {
		return this.actRuExecutionsForSuperExec;
	}

	public void setActRuExecutionsForSuperExec(
			Set<ActRuExecution> actRuExecutionsForSuperExec) {
		this.actRuExecutionsForSuperExec = actRuExecutionsForSuperExec;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuExecutionByParentId")
	public Set<ActRuExecution> getActRuExecutionsForParentId() {
		return this.actRuExecutionsForParentId;
	}

	public void setActRuExecutionsForParentId(
			Set<ActRuExecution> actRuExecutionsForParentId) {
		this.actRuExecutionsForParentId = actRuExecutionsForParentId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuExecutionByProcInstId")
	public Set<ActRuTask> getActRuTasksForProcInstId() {
		return this.actRuTasksForProcInstId;
	}

	public void setActRuTasksForProcInstId(
			Set<ActRuTask> actRuTasksForProcInstId) {
		this.actRuTasksForProcInstId = actRuTasksForProcInstId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuExecutionByExecutionId")
	public Set<ActRuTask> getActRuTasksForExecutionId() {
		return this.actRuTasksForExecutionId;
	}

	public void setActRuTasksForExecutionId(
			Set<ActRuTask> actRuTasksForExecutionId) {
		this.actRuTasksForExecutionId = actRuTasksForExecutionId;
	}

}