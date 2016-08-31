package com.hpkj.process.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActHiComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_HI_COMMENT", schema = "HPOA")
public class ActHiComment implements java.io.Serializable {

	// Fields

	private String id;
	private String type;
	private Timestamp time;
	private String userId;
	private String taskId;
	private String procInstId;
	private String action;
	private String message;
	private String fullMsg;

	// Constructors

	/** default constructor */
	public ActHiComment() {
	}

	/** minimal constructor */
	public ActHiComment(String id, Timestamp time) {
		this.id = id;
		this.time = time;
	}

	/** full constructor */
	public ActHiComment(String id, String type, Timestamp time, String userId,
			String taskId, String procInstId, String action, String message,
			String fullMsg) {
		this.id = id;
		this.type = type;
		this.time = time;
		this.userId = userId;
		this.taskId = taskId;
		this.procInstId = procInstId;
		this.action = action;
		this.message = message;
		this.fullMsg = fullMsg;
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

	@Column(name = "TYPE_")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TIME_", nullable = false, length = 11)
	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Column(name = "USER_ID_")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Column(name = "ACTION_")
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "MESSAGE_")
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "FULL_MSG_")
	public String getFullMsg() {
		return this.fullMsg;
	}

	public void setFullMsg(String fullMsg) {
		this.fullMsg = fullMsg;
	}

}