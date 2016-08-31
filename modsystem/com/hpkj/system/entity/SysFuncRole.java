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
@Table(name = "SYS_FUNC_ROLE", schema = "HPOA")
public class SysFuncRole implements java.io.Serializable {



	private String roleFuncLink;//角色功能关系ID
	private String funcId;//功能ID
	private String roleId;//角色ID
	private String bak1;//
	private String bak2;//
	private String bak3;//
	
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ROLE_FUNC_LINK", unique = true, nullable = false, length = 32)
	public String getRoleFuncLink() {
		return roleFuncLink;
	}

	public void setRoleFuncLink(String roleFuncLink) {
		this.roleFuncLink = roleFuncLink;
	}

	@Column(name = "FUNC_ID", length = 32)
	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	@Column(name = "ROLE_ID", length = 32)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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