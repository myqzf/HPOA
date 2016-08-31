package com.hpkj.monthly.vo;

import java.util.ArrayList;
import java.util.List;

import com.hpkj.monthly.entity.HpoaMonthsumInfo;
import com.hpkj.staff.entity.HpoaStaffInfo;

public class MonthlyVo {

	private String monthsumid;//月报Id
	private String staffId;//员工Id
	private String monthscope;//月报所属月份
	private String sumlink;//链接
	private String monthtitle;//月报标题
	private String monthcontent;//月报内容
	private String uploaddate;//提交时间
	
	private String staffName;//员工姓名
	private String staffPhone;//电话
	private String staffDept;//隶属部门
	private String staffPosi;//隶属职位
	private String bak1;//添加员工时间
	
	
	public String getMonthsumid() {
		return monthsumid;
	}
	public void setMonthsumid(String monthsumid) {
		this.monthsumid = monthsumid;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getMonthscope() {
		return monthscope;
	}
	public void setMonthscope(String monthscope) {
		this.monthscope = monthscope;
	}
	public String getSumlink() {
		return sumlink;
	}
	public void setSumlink(String sumlink) {
		this.sumlink = sumlink;
	}
	public String getMonthtitle() {
		return monthtitle;
	}
	public void setMonthtitle(String monthtitle) {
		this.monthtitle = monthtitle;
	}
	public String getMonthcontent() {
		return monthcontent;
	}
	public void setMonthcontent(String monthcontent) {
		this.monthcontent = monthcontent;
	}
	public String getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffPhone() {
		return staffPhone;
	}
	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}
	public String getStaffDept() {
		return staffDept;
	}
	public void setStaffDept(String staffDept) {
		this.staffDept = staffDept;
	}
	public String getStaffPosi() {
		return staffPosi;
	}
	public void setStaffPosi(String staffPosi) {
		this.staffPosi = staffPosi;
	}
	public String getBak1() {
		return bak1;
	}
	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}
	  
	
	
	
	
}
