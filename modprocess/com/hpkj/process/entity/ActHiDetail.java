package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActHiDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_HI_DETAIL", schema = "HPOA")
public class ActHiDetail implements java.io.Serializable {

	// Fields

	private String id;
	private String type;
	private String procInstId;
	private String executionId;
	private String taskId;
	private String actInstId;
	private String name;
	private String varType;
	private BigDecimal rev;
	private Timestamp time;
	private String bytearrayId;
	private Double double_;
	private BigDecimal long_;
	private String text;
	private String text2;

	// Constructors

	/** default constructor */
	public ActHiDetail() {
	}

	/** minimal constructor */
	public ActHiDetail(String id, String type, String name, Timestamp time) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.time = time;
	}

	/** full constructor */
	public ActHiDetail(String id, String type, String procInstId,
			String executionId, String taskId, String actInstId, String name,
			String varType, BigDecimal rev, Timestamp time, String bytearrayId,
			Double double_, BigDecimal long_, String text, String text2) {
		this.id = id;
		this.type = type;
		this.procInstId = procInstId;
		this.executionId = executionId;
		this.taskId = taskId;
		this.actInstId = actInstId;
		this.name = name;
		this.varType = varType;
		this.rev = rev;
		this.time = time;
		this.bytearrayId = bytearrayId;
		this.double_ = double_;
		this.long_ = long_;
		this.text = text;
		this.text2 = text2;
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

	@Column(name = "TYPE_", nullable = false)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Column(name = "ACT_INST_ID_")
	public String getActInstId() {
		return this.actInstId;
	}

	public void setActInstId(String actInstId) {
		this.actInstId = actInstId;
	}

	@Column(name = "NAME_", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VAR_TYPE_")
	public String getVarType() {
		return this.varType;
	}

	public void setVarType(String varType) {
		this.varType = varType;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	@Column(name = "TIME_", nullable = false, length = 11)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "BYTEARRAY_ID_")
	public String getBytearrayId() {
		return this.bytearrayId;
	}

	public void setBytearrayId(String bytearrayId) {
		this.bytearrayId = bytearrayId;
	}

	@Column(name = "DOUBLE_", precision = 22, scale = 10)
	public Double getDouble_() {
		return this.double_;
	}

	public void setDouble_(Double double_) {
		this.double_ = double_;
	}

	@Column(name = "LONG_", scale = 0)
	public BigDecimal getLong_() {
		return this.long_;
	}

	public void setLong_(BigDecimal long_) {
		this.long_ = long_;
	}

	@Column(name = "TEXT_")
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "TEXT2_")
	public String getText2() {
		return this.text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

}