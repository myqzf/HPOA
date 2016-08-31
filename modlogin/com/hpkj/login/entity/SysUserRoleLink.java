package com.hpkj.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_USER_ROLE_LINK", schema = "HPOA")
public class SysUserRoleLink {
	//Fields
	private String userLinkId;//关系id
	private String userId;//用户id
	private String roleId;//角色id
	public SysUserRoleLink(){
		
	}
	public SysUserRoleLink(String userLinkId, String userId, String roleId){
		this.setUserLinkId(userLinkId);
		this.setUserId(userId);
		this.setRoleId(roleId);
	}
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "USER_LINK_ID", unique = true, nullable = false, length = 32)
	public String getUserLinkId() {
		return userLinkId;
	}
	public void setUserLinkId(String userLinkId) {
		this.userLinkId = userLinkId;
	}
	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "ROLE_ID", length = 32)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
