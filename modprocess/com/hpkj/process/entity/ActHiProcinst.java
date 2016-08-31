package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ActHiProcinst entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_HI_PROCINST", schema = "HPOA", uniqueConstraints = @UniqueConstraint(columnNames = "PROC_INST_ID_"))
public class ActHiProcinst implements java.io.Serializable {

	// Fields

	private String id;
	private String procInstId;
	private String businessKey;
	private String procDefId;
	private Timestamp startTime;
	private Timestamp endTime;
	private BigDecimal duration;
	private String startUserId;
	private String startActId;
	private String endActId;
	private String superProcessInstanceId;
	private String deleteReason;
	private String tenantId;
	private String name;

	// Constructors

	/** default constructor */
	public ActHiProcinst() {
	}

	/** minimal constructor */
	public ActHiProcinst(String id, String procInstId, String procDefId,
			Timestamp startTime) {
		this.id = id;
		this.procInstId = procInstId;
		this.procDefId = procDefId;
		this.startTime = startTime;
	}

	/** full constructor */
	public ActHiProcinst(String id, String procInstId, String businessKey,
			String procDefId, Timestamp startTime, Timestamp endTime,
			BigDecimal duration, String startUserId, String startActId,
			String endActId, String superProcessInstanceId,
			String deleteReason, String tenantId, String name) {
		this.id = id;
		this.procInstId = procInstId;
		this.businessKey = businessKey;
		this.procDefId = procDefId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.startUserId = startUserId;
		this.startActId = startActId;
		this.endActId = endActId;
		this.superProcessInstanceId = superProcessInstanceId;
		this.deleteReason = deleteReason;
		this.tenantId = tenantId;
		this.name = name;
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

	@Column(name = "PROC_INST_ID_", unique = true, nullable = false)
	public String getProcInstId() {
		return this.procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "BUSINESS_KEY_")
	public String getBusinessKey() {
		return this.businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	@Column(name = "PROC_DEF_ID_", nullable = false)
	public String getProcDefId() {
		return this.procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
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

	@Column(name = "START_USER_ID_")
	public String getStartUserId() {
		return this.startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}

	@Column(name = "START_ACT_ID_")
	public String getStartActId() {
		return this.startActId;
	}

	public void setStartActId(String startActId) {
		this.startActId = startActId;
	}

	@Column(name = "END_ACT_ID_")
	public String getEndActId() {
		return this.endActId;
	}

	public void setEndActId(String endActId) {
		this.endActId = endActId;
	}

	@Column(name = "SUPER_PROCESS_INSTANCE_ID_")
	public String getSuperProcessInstanceId() {
		return this.superProcessInstanceId;
	}

	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}

	@Column(name = "DELETE_REASON_")
	public String getDeleteReason() {
		return this.deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
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

}