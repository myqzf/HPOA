package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActHiTaskinst entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_HI_TASKINST", schema = "HPOA")
public class ActHiTaskinst implements java.io.Serializable {

	// Fields

	private String id;
	private String procDefId;
	private String taskDefKey;
	private String procInstId;
	private String executionId;
	private String parentTaskId;
	private String name;
	private String description;
	private String owner;
	private String assignee;
	private Timestamp startTime;
	private Timestamp claimTime;
	private Timestamp endTime;
	private BigDecimal duration;
	private String deleteReason;
	private BigDecimal priority;
	private Timestamp dueDate;
	private String formKey;
	private String category;
	private String tenantId;

	// Constructors

	/** default constructor */
	public ActHiTaskinst() {
	}

	/** minimal constructor */
	public ActHiTaskinst(String id, Timestamp startTime) {
		this.id = id;
		this.startTime = startTime;
	}

	/** full constructor */
	public ActHiTaskinst(String id, String procDefId, String taskDefKey,
			String procInstId, String executionId, String parentTaskId,
			String name, String description, String owner, String assignee,
			Timestamp startTime, Timestamp claimTime, Timestamp endTime,
			BigDecimal duration, String deleteReason, BigDecimal priority,
			Timestamp dueDate, String formKey, String category, String tenantId) {
		this.id = id;
		this.procDefId = procDefId;
		this.taskDefKey = taskDefKey;
		this.procInstId = procInstId;
		this.executionId = executionId;
		this.parentTaskId = parentTaskId;
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.assignee = assignee;
		this.startTime = startTime;
		this.claimTime = claimTime;
		this.endTime = endTime;
		this.duration = duration;
		this.deleteReason = deleteReason;
		this.priority = priority;
		this.dueDate = dueDate;
		this.formKey = formKey;
		this.category = category;
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

	@Column(name = "PROC_DEF_ID_")
	public String getProcDefId() {
		return this.procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	@Column(name = "TASK_DEF_KEY_")
	public String getTaskDefKey() {
		return this.taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	@Column(name = "PROC_INST_ID_")
	public String getProcInstId() {
		return this.procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "EXECUTION_ID_")
	public String getExecutionId() {
		return this.executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	@Column(name = "PARENT_TASK_ID_")
	public String getParentTaskId() {
		return this.parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	@Column(name = "NAME_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION_")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Column(name = "START_TIME_", nullable = false, length = 11)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "CLAIM_TIME_", length = 11)
	public Timestamp getClaimTime() {
		return this.claimTime;
	}

	public void setClaimTime(Timestamp claimTime) {
		this.claimTime = claimTime;
	}

	@Column(name = "END_TIME_", length = 11)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "DURATION_", scale = 0)
	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	@Column(name = "DELETE_REASON_")
	public String getDeleteReason() {
		return this.deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	@Column(name = "PRIORITY_", precision = 22, scale = 0)
	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	@Column(name = "DUE_DATE_", length = 11)
	public Timestamp getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "FORM_KEY_")
	public String getFormKey() {
		return this.formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
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

}