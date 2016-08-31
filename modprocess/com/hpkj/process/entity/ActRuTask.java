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
 * ActRuTask entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_RU_TASK", schema = "HPOA")
public class ActRuTask implements java.io.Serializable {

	// Fields

	private String id;
	private ActRuExecution actRuExecutionByExecutionId;
	private ActReProcdef actReProcdef;
	private ActRuExecution actRuExecutionByProcInstId;
	private BigDecimal rev;
	private String name;
	private String parentTaskId;
	private String description;
	private String taskDefKey;
	private String owner;
	private String assignee;
	private String delegation;
	private BigDecimal priority;
	private Timestamp createTime;
	private Timestamp dueDate;
	private String category;
	private BigDecimal suspensionState;
	private String tenantId;
	private String formKey;
	private Set<ActRuIdentitylink> actRuIdentitylinks = new HashSet<ActRuIdentitylink>(
			0);

	// Constructors

	/** default constructor */
	public ActRuTask() {
	}

	/** minimal constructor */
	public ActRuTask(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActRuTask(String id, ActRuExecution actRuExecutionByExecutionId,
			ActReProcdef actReProcdef,
			ActRuExecution actRuExecutionByProcInstId, BigDecimal rev,
			String name, String parentTaskId, String description,
			String taskDefKey, String owner, String assignee,
			String delegation, BigDecimal priority, Timestamp createTime,
			Timestamp dueDate, String category, BigDecimal suspensionState,
			String tenantId, String formKey,
			Set<ActRuIdentitylink> actRuIdentitylinks) {
		this.id = id;
		this.actRuExecutionByExecutionId = actRuExecutionByExecutionId;
		this.actReProcdef = actReProcdef;
		this.actRuExecutionByProcInstId = actRuExecutionByProcInstId;
		this.rev = rev;
		this.name = name;
		this.parentTaskId = parentTaskId;
		this.description = description;
		this.taskDefKey = taskDefKey;
		this.owner = owner;
		this.assignee = assignee;
		this.delegation = delegation;
		this.priority = priority;
		this.createTime = createTime;
		this.dueDate = dueDate;
		this.category = category;
		this.suspensionState = suspensionState;
		this.tenantId = tenantId;
		this.formKey = formKey;
		this.actRuIdentitylinks = actRuIdentitylinks;
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
	@JoinColumn(name = "EXECUTION_ID_")
	public ActRuExecution getActRuExecutionByExecutionId() {
		return this.actRuExecutionByExecutionId;
	}

	public void setActRuExecutionByExecutionId(
			ActRuExecution actRuExecutionByExecutionId) {
		this.actRuExecutionByExecutionId = actRuExecutionByExecutionId;
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

	@Column(name = "NAME_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARENT_TASK_ID_")
	public String getParentTaskId() {
		return this.parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	@Column(name = "DESCRIPTION_")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TASK_DEF_KEY_")
	public String getTaskDefKey() {
		return this.taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	@Column(name = "OWNER_")
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "ASSIGNEE_")
	public String getAssignee() {
		return this.assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	@Column(name = "DELEGATION_")
	public String getDelegation() {
		return this.delegation;
	}

	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}

	@Column(name = "PRIORITY_", precision = 22, scale = 0)
	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	@Column(name = "CREATE_TIME_", length = 11)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "DUE_DATE_", length = 11)
	public Timestamp getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "CATEGORY_")
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	@Column(name = "FORM_KEY_")
	public String getFormKey() {
		return this.formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actRuTask")
	public Set<ActRuIdentitylink> getActRuIdentitylinks() {
		return this.actRuIdentitylinks;
	}

	public void setActRuIdentitylinks(Set<ActRuIdentitylink> actRuIdentitylinks) {
		this.actRuIdentitylinks = actRuIdentitylinks;
	}

}