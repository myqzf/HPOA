package com.hpkj.login.vo;

import java.util.List;

import com.hpkj.system.entity.SysFuncInfo;



public class UserInfo {
	private String userID;//用户id
	private String staffID;//员工id
	private String userName;//用户姓名
	private int staffComp;//员工公司id
	public List<SysFuncInfo> funcList;//权限功能列表
	private String funcStr;//权限json
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<SysFuncInfo> getFuncList() {
		return funcList;
	}
	public void setFuncList(List<SysFuncInfo> funcList) {
		this.funcList = funcList;
	}
	public int getStaffComp() {
		return staffComp;
	}
	public void setStaffComp(int staffComp) {
		this.staffComp = staffComp;
	}
	public String getFuncStr() {
		return funcStr;
	}
	public void setFuncStr(String funcStr) {
		this.funcStr = funcStr;
	}
}
