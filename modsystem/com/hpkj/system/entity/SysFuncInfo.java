package com.hpkj.system.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * SysUserInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_FUNC_INFO", schema = "HPOA")
public class SysFuncInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String funcId;//功能ID
	private String funcName;//功能名称
	private String funcDesc;//功能描述
	private String funcUrl;//功能访问路径
	private String funcPid;//父级功能ID
	private int isUsed;//是否生效1是2否
	private int isLeaf;//是否叶子节点1是2否
	private String bak1;//
	private String bak2;//
	private String bak3;//
	private String checked="false";//ztree默认勾选
	
  
	
	@Id
	@Column(name = "FUNC_ID", unique = true, nullable = false,  length = 32)
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	
	@Column(name = "FUNC_NAME", length = 50)
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	
	@Column(name = "FUNC_DESC", length = 200)
	public String getFuncDesc() {
		return funcDesc;
	}
	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}
	
	@Column(name = "FUNC_URL", length = 400)
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	
	@Column(name = "FUNC_PID", length = 32)
	public String getFuncPid() {
		return funcPid;
	}
	public void setFuncPid(String funcPid) {
		this.funcPid = funcPid;
	}
	
	@Column(name = "IS_USED", length = 1)
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	
	@Column(name = "IS_LEAF", length = 1)
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
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
	
	
	@Transient
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	


	

}