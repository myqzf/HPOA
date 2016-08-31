package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActHiVarinst entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_HI_VARINST", schema = "HPOA")
public class ActHiVarinst implements java.io.Serializable {

	// Fields

	private String id;
	private String procInstId;
	private String executionId;
	private String taskId;
	private String name;
	private String varType;
	private BigDecimal rev;
	private String bytearrayId;
	private Double double_;
	private BigDecimal long_;
	private String text;
	private String text2;
	private Timestamp createTime;
	private Timestamp lastUpdatedTime;

	// Constructors

	/** default constructor */
	public ActHiVarinst() {
	}

	/** minimal constructor */
	public ActHiVarinst(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public ActHiVarinst(String id, String procInstId, String executionId,
			String taskId, String name, String varType, BigDecimal rev,
			String bytearrayId, Double double_, BigDecimal long_, String text,
			String text2, Timestamp createTime, Timestamp lastUpdatedTime) {
		this.id = id;
		this.procInstId = procInstId;
		this.executionId = executionId;
		this.taskId = taskId;
		this.name = name;
		this.varType = varType;
		this.rev = rev;
		this.bytearrayId = bytearrayId;
		this.double_ = double_;
		this.long_ = long_;
		this.text = text;
		this.text2 = text2;
		this.createTime = createTime;
		this.lastUpdatedTime = lastUpdatedTime;
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

	@Column(name = "CREATE_TIME_", length = 11)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "LAST_UPDATED_TIME_", length = 11)
	public Timestamp getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

}