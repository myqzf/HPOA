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
 * ActRuJob entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_RU_JOB", schema = "HPOA")
public class ActRuJob implements java.io.Serializable {

	// Fields

	private String id;
	private ActGeBytearray actGeBytearray;
	private BigDecimal rev;
	private String type;
	private Timestamp lockExpTime;
	private String lockOwner;
	private Boolean exclusive;
	private String executionId;
	private String processInstanceId;
	private String procDefId;
	private BigDecimal retries;
	private String exceptionMsg;
	private Timestamp duedate;
	private String repeat;
	private String handlerType;
	private String handlerCfg;
	private String tenantId;

	// Constructors

	/** default constructor */
	public ActRuJob() {
	}

	/** minimal constructor */
	public ActRuJob(String id, String type) {
		this.id = id;
		this.type = type;
	}

	/** full constructor */
	public ActRuJob(String id, ActGeBytearray actGeBytearray, BigDecimal rev,
			String type, Timestamp lockExpTime, String lockOwner,
			Boolean exclusive, String executionId, String processInstanceId,
			String procDefId, BigDecimal retries, String exceptionMsg,
			Timestamp duedate, String repeat, String handlerType,
			String handlerCfg, String tenantId) {
		this.id = id;
		this.actGeBytearray = actGeBytearray;
		this.rev = rev;
		this.type = type;
		this.lockExpTime = lockExpTime;
		this.lockOwner = lockOwner;
		this.exclusive = exclusive;
		this.executionId = executionId;
		this.processInstanceId = processInstanceId;
		this.procDefId = procDefId;
		this.retries = retries;
		this.exceptionMsg = exceptionMsg;
		this.duedate = duedate;
		this.repeat = repeat;
		this.handlerType = handlerType;
		this.handlerCfg = handlerCfg;
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
	@JoinColumn(name = "EXCEPTION_STACK_ID_")
	public ActGeBytearray getActGeBytearray() {
		return this.actGeBytearray;
	}

	public void setActGeBytearray(ActGeBytearray actGeBytearray) {
		this.actGeBytearray = actGeBytearray;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	@Column(name = "TYPE_", nullable = false)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "LOCK_EXP_TIME_", length = 11)
	public Timestamp getLockExpTime() {
		return this.lockExpTime;
	}

	public void setLockExpTime(Timestamp lockExpTime) {
		this.lockExpTime = lockExpTime;
	}

	@Column(name = "LOCK_OWNER_")
	public String getLockOwner() {
		return this.lockOwner;
	}

	public void setLockOwner(String lockOwner) {
		this.lockOwner = lockOwner;
	}

	@Column(name = "EXCLUSIVE_", precision = 1, scale = 0)
	public Boolean getExclusive() {
		return this.exclusive;
	}

	public void setExclusive(Boolean exclusive) {
		this.exclusive = exclusive;
	}

	@Column(name = "EXECUTION_ID_")
	public String getExecutionId() {
		return this.executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	@Column(name = "PROCESS_INSTANCE_ID_")
	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@Column(name = "PROC_DEF_ID_")
	public String getProcDefId() {
		return this.procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	@Column(name = "RETRIES_", precision = 22, scale = 0)
	public BigDecimal getRetries() {
		return this.retries;
	}

	public void setRetries(BigDecimal retries) {
		this.retries = retries;
	}

	@Column(name = "EXCEPTION_MSG_")
	public String getExceptionMsg() {
		return this.exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Column(name = "DUEDATE_", length = 11)
	public Timestamp getDuedate() {
		return this.duedate;
	}

	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}

	@Column(name = "REPEAT_")
	public String getRepeat() {
		return this.repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	@Column(name = "HANDLER_TYPE_")
	public String getHandlerType() {
		return this.handlerType;
	}

	public void setHandlerType(String handlerType) {
		this.handlerType = handlerType;
	}

	@Column(name = "HANDLER_CFG_")
	public String getHandlerCfg() {
		return this.handlerCfg;
	}

	public void setHandlerCfg(String handlerCfg) {
		this.handlerCfg = handlerCfg;
	}

	@Column(name = "TENANT_ID_")
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}