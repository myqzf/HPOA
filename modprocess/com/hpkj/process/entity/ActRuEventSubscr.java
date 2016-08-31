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
 * ActRuEventSubscr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_RU_EVENT_SUBSCR", schema = "HPOA")
public class ActRuEventSubscr implements java.io.Serializable {

	// Fields

	private String id;
	private ActRuExecution actRuExecution;
	private BigDecimal rev;
	private String eventType;
	private String eventName;
	private String procInstId;
	private String activityId;
	private String configuration;
	private Timestamp created;
	private String procDefId;
	private String tenantId;

	// Constructors

	/** default constructor */
	public ActRuEventSubscr() {
	}

	/** minimal constructor */
	public ActRuEventSubscr(String id, String eventType, Timestamp created) {
		this.id = id;
		this.eventType = eventType;
		this.created = created;
	}

	/** full constructor */
	public ActRuEventSubscr(String id, ActRuExecution actRuExecution,
			BigDecimal rev, String eventType, String eventName,
			String procInstId, String activityId, String configuration,
			Timestamp created, String procDefId, String tenantId) {
		this.id = id;
		this.actRuExecution = actRuExecution;
		this.rev = rev;
		this.eventType = eventType;
		this.eventName = eventName;
		this.procInstId = procInstId;
		this.activityId = activityId;
		this.configuration = configuration;
		this.created = created;
		this.procDefId = procDefId;
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
	@JoinColumn(name = "EXECUTION_ID_")
	public ActRuExecution getActRuExecution() {
		return this.actRuExecution;
	}

	public void setActRuExecution(ActRuExecution actRuExecution) {
		this.actRuExecution = actRuExecution;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	@Column(name = "EVENT_TYPE_", nullable = false)
	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Column(name = "EVENT_NAME_")
	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Column(name = "PROC_INST_ID_")
	public String getProcInstId() {
		return this.procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "ACTIVITY_ID_")
	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Column(name = "CONFIGURATION_")
	public String getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	@Column(name = "CREATED_", nullable = false, length = 11)
	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	@Column(name = "PROC_DEF_ID_")
	public String getProcDefId() {
		return this.procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	@Column(name = "TENANT_ID_")
	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}