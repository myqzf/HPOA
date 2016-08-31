package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActEvtLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_EVT_LOG", schema = "HPOA")
public class ActEvtLog implements java.io.Serializable {

	// Fields

	private BigDecimal logNr;
	private String type;
	private String procDefId;
	private String procInstId;
	private String executionId;
	private String taskId;
	private Timestamp timeStamp;
	private String userId;
	private String data;
	private String lockOwner;
	private Timestamp lockTime;
	private Short isProcessed;

	// Constructors

	/** default constructor */
	public ActEvtLog() {
	}

	/** minimal constructor */
	public ActEvtLog(BigDecimal logNr, Timestamp timeStamp) {
		this.logNr = logNr;
		this.timeStamp = timeStamp;
	}

	/** full constructor */
	public ActEvtLog(BigDecimal logNr, String type, String procDefId,
			String procInstId, String executionId, String taskId,
			Timestamp timeStamp, String userId, String data, String lockOwner,
			Timestamp lockTime, Short isProcessed) {
		this.logNr = logNr;
		this.type = type;
		this.procDefId = procDefId;
		this.procInstId = procInstId;
		this.executionId = executionId;
		this.taskId = taskId;
		this.timeStamp = timeStamp;
		this.userId = userId;
		this.data = data;
		this.lockOwner = lockOwner;
		this.lockTime = lockTime;
		this.isProcessed = isProcessed;
	}

	// Property accessors
	@Id
	@Column(name = "LOG_NR_", unique = true, nullable = false, scale = 0)
	public BigDecimal getLogNr() {
		return this.logNr;
	}

	public void setLogNr(BigDecimal logNr) {
		this.logNr = logNr;
	}

	@Column(name = "TYPE_")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "PROC_DEF_ID_")
	public String getProcDefId() {
		return this.procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
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

	@Column(name = "TASK_ID_")
	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name = "TIME_STAMP_", nullable = false, length = 11)
	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Column(name = "USER_ID_")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "DATA_")
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Column(name = "LOCK_OWNER_")
	public String getLockOwner() {
		return this.lockOwner;
	}

	public void setLockOwner(String lockOwner) {
		this.lockOwner = lockOwner;
	}

	@Column(name = "LOCK_TIME_", length = 11)
	public Timestamp getLockTime() {
		return this.lockTime;
	}

	public void setLockTime(Timestamp lockTime) {
		this.lockTime = lockTime;
	}

	@Column(name = "IS_PROCESSED_", precision = 3, scale = 0)
	public Short getIsProcessed() {
		return this.isProcessed;
	}

	public void setIsProcessed(Short isProcessed) {
		this.isProcessed = isProcessed;
	}

}