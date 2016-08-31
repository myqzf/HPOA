package com.hpkj.staff.vo;

public class StaffDetailInfoVo {
	private String staffId;//员工ID
	private String staffName;//员工姓名
	private String staffIdcard;//员工身份证号
	private String staffGender;//员工性别
	private String staffQualid;//学历
	private String staffMarry;//婚否
	private String staffPhone;//电话
	private String staffAddress;//家庭住址
	private String staffPhotourl;//照片
	private String staffDept;//隶属部门
	private String staffPosi;//隶属职位
	private String staffComp;//隶属公司
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffIdcard() {
		return staffIdcard;
	}
	public void setStaffIdcard(String staffIdcard) {
		this.staffIdcard = staffIdcard;
	}
	public String getStaffGender() {
		return staffGender;
	}
	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}
	public String getStaffQualid() {
		return staffQualid;
	}
	public void setStaffQualid(String staffQualid) {
		this.staffQualid = staffQualid;
	}
	public String getStaffMarry() {
		return staffMarry;
	}
	public void setStaffMarry(String staffMarry) {
		this.staffMarry = staffMarry;
	}
	public String getStaffPhone() {
		return staffPhone;
	}
	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}
	public String getStaffAddress() {
		return staffAddress;
	}
	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
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
	public String getStaffComp() {
		return staffComp;
	}
	public void setStaffComp(String staffComp) {
		this.staffComp = staffComp;
	}
	public String getStaffPhotourl() {
		return staffPhotourl;
	}
	public void setStaffPhotourl(String staffPhotourl) {
		this.staffPhotourl = staffPhotourl;
	}
	public Object[] toObject(){
		Object[] object=new Object[10];
		object[0]=this.staffName;
		object[1]=this.staffGender;
		object[2]=this.staffMarry;
		object[3]=this.staffIdcard;
		object[4]=this.staffQualid;
		object[5]=this.staffPhone;
		object[6]=this.staffAddress;
		object[7]=this.staffComp;
		object[8]=this.staffDept;
		object[9]=this.staffPosi;
		return object;
	}
}
