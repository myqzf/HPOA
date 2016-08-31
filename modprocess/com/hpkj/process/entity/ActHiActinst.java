package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActHiActinst entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_HI_ACTINST", schema = "HPOA")
public class ActHiActinst implements java.io.Serializable {

	// Fields

	private String id;
	private String procDefId;
	private String procInstId;
	private String executionId;
	private String actId;
	private String taskId;
	private String callProcInstId;
	private String actName;
	private String actType;
	private String assignee;
	private Timestamp startTime;
	private Timestamp endTime;
	private BigDecimal duration;
	private String tenantId;

	// Constructors

	/** default constructor */
	public ActHiActinst() {
	}

	/** minimal constructor */
	public ActHiActinst(String id, String procDefId, String procInstId,
			String executionId, String actId, String actType,
			Timestamp startTime) {
		this.id = id;
		this.procDefId = procDefId;
		this.procInstId = procInstId;
		this.executionId = executionId;
		this.actId = actId;
		this.actType = actType;
		this.startTime = startTime;
	}

	/** full constructor */
	public ActHiActinst(String id, String procDefId, String procInstId,
			String executionId, String actId, String taskId,
			String callProcInstId, String actName, String actType,
			String assignee, Timestamp startTime, Timestamp endTime,
			BigDecimal duration, String tenantId) {
		this.id = id;
		this.procDefId = procDefId;
		this.procInstId = procInstId;
		this.executionId = executionId;
		this.actId = actId;
		this.taskId = taskId;
		this.callProcInstId = callProcInstId;
		this.actName = actName;
		this.actType = actType;
		this.assignee = assignee;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
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

	@Column(name = "PROC_DEF_ID_", nullable = false)
	public String getProcDefId() {
		return this.procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	@Column(name = "PROC_INST_ID_", nullable = false)
	public String getProcInstId() {
		return this.procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "EXECUTION_ID_", nullable = false)
	public String getExecutionId() {
		return this.executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	@Column(name = "ACT_ID_", nullable = false)
	public String getActId() {
		return this.actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	@Column(name = "TASK_ID_")
	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name = "CALL_PROC_INST_ID_")
	public String getCallProcInstId() {
		return this.callProcInstId;
	}

	public void setCallProcInstId(String callProcInstId) {
		this.callProcInstId = callProcInstId;
	}

	@Column(name = "ACT_NAME_")
	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	@Column(name = "ACT_TYPE_", nullable = false)
	public String getActType() {
		return this.actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
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

	@Column(name = "TENANT_ID_")
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}