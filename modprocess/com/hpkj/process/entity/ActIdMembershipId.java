package com.hpkj.process.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ActIdMembershipId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ActIdMembershipId implements java.io.Serializable {

	// Fields

	private String userId;
	private String groupId;

	// Constructors

	/** default constructor */
	public ActIdMembershipId() {
	}

	/** full constructor */
	public ActIdMembershipId(String userId, String groupId) {
		this.userId = userId;
		this.groupId = groupId;
	}

	// Property accessors

	@Column(name = "USER_ID_", nullable = false)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "GROUP_ID_", nullable = false)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ActIdMembershipId))
			return false;
		ActIdMembershipId castOther = (ActIdMembershipId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getGroupId() == castOther.getGroupId()) || (this
						.getGroupId() != null && castOther.getGroupId() != null && this
						.getGroupId().equals(castOther.getGroupId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		return result;
	}

}