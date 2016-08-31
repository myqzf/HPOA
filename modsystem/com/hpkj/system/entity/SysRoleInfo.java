package com.hpkj.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SysUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ROLE_INFO", schema = "HPOA")
public class SysRoleInfo implements java.io.Serializable {


	private String roleId;//角色ID
	private String roleName;//角色名称
	private String roleDesc;//角色描述
	private String bak1;//
	private String bak2;//
	private String bak3;//
	
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ROLE_ID", unique = true, nullable = false, length = 32)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Column(name = "ROLE_NAME", length = 32)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "ROLE_DESC", length = 200)
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}		
	
	@Column(name = "BAK1", length = 32)
	public String getBak1() {
		return bak1;
	}
	
	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}
	
	@Column(name = "BAK2", length = 32)
	public String getBak2() {
		return bak2;
	}
	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}
	
	@Column(name = "BAK3", length = 32)
	public String getBak3() {
		return bak3;
	}
	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}
	

	

}