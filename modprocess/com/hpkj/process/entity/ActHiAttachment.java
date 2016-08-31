package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActHiAttachment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_HI_ATTACHMENT", schema = "HPOA")
public class ActHiAttachment implements java.io.Serializable {

	// Fields

	private String id;
	private BigDecimal rev;
	private String userId;
	private String name;
	private String description;
	private String type;
	private String taskId;
	private String procInstId;
	private String url;
	private String contentId;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public ActHiAttachment() {
	}

	/** minimal constructor */
	public ActHiAttachment(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActHiAttachment(String id, BigDecimal rev, String userId,
			String name, String description, String type, String taskId,
			String procInstId, String url, String contentId, Timestamp time) {
		this.id = id;
		this.rev = rev;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.type = type;
		this.taskId = taskId;
		this.procInstId = procInstId;
		this.url = url;
		this.contentId = contentId;
		this.time = time;
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

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	@Column(name = "USER_ID_")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Column(name = "TYPE_")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TASK_ID_")
	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name = "PROC_INST_ID_")
	public String getProcInstId() {
		return this.procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "URL_")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "CONTENT_ID_")
	public String getContentId() {
		return this.contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	@Column(name = "TIME_", length = 11)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}