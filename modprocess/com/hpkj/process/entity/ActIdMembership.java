package com.hpkj.process.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ActIdMembership entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_ID_MEMBERSHIP", schema = "HPOA")
public class ActIdMembership implements java.io.Serializable {

	// Fields

	private ActIdMembershipId id;
	private ActIdGroup actIdGroup;
	private ActIdUser actIdUser;

	// Constructors

	/** default constructor */
	public ActIdMembership() {
	}

	/** full constructor */
	public ActIdMembership(ActIdMembershipId id, ActIdGroup actIdGroup,
			ActIdUser actIdUser) {
		this.id = id;
		this.actIdGroup = actIdGroup;
		this.actIdUser = actIdUser;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID_", nullable = false)),
			@AttributeOverride(name = "groupId", column = @Column(name = "GROUP_ID_", nullable = false)) })
	public ActIdMembershipId getId() {
		return this.id;
	}

	public void setId(ActIdMembershipId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID_", nullable = false, insertable = false, updatable = false)
	public ActIdGroup getActIdGroup() {
		return this.actIdGroup;
	}

	public void setActIdGroup(ActIdGroup actIdGroup) {
		this.actIdGroup = actIdGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID_", nullable = false, insertable = false, updatable = false)
	public ActIdUser getActIdUser() {
		return this.actIdUser;
	}

	public void setActIdUser(ActIdUser actIdUser) {
		this.actIdUser = actIdUser;
	}

}