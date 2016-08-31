package com.hpkj.process.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActHiIdentitylink entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_HI_IDENTITYLINK", schema = "HPOA")
public class ActHiIdentitylink implements java.io.Serializable {

	// Fields

	private String id;
	private String groupId;
	private String type;
	private String userId;
	private String taskId;
	private String procInstId;

	// Constructors

	/** default constructor */
	public ActHiIdentitylink() {
	}

	/** minimal constructor */
	public ActHiIdentitylink(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActHiIdentitylink(String id, String groupId, String type,
			String userId, String taskId, String procInstId) {
		this.id = id;
		this.groupId = groupId;
		this.type = type;
		this.userId = userId;
		this.taskId = taskId;
		this.procInstId = procInstId;
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

	@Column(name = "GROUP_ID_")
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "TYPE_")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

}