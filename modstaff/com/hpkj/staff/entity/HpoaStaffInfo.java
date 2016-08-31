package com.hpkj.staff.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaStaffInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_STAFF_INFO", schema = "HPOA")
public class HpoaStaffInfo implements java.io.Serializable {

	// Fields
 
	private String staffId;//员工ID
	private String staffName;//员工姓名
	private String staffIdcard;//员工身份证号
	private String staffGender;//员工性别
	private String staffPhotourl;//员工照片
	private String staffQualid;//学历
	private String staffMarry;//婚否
	private String staffPhone;//电话
	private String staffAddress;//家庭住址
	private String staffDept;//隶属部门
	private String staffPosi;//隶属职位
	private String staffComp;//隶属公司
	private String staffUserid;//登录账号
	private String bak1;//添加员工时间
	private String bak2;//备用二
	private String bak3;//备用三
	private String staffNumber;//员工工号

	// Constructors

	/** default constructor */
	public HpoaStaffInfo() {
	}

	/** minimal constructor */
	public HpoaStaffInfo(String staffId) {
		this.staffId = staffId;
	}

	/** full constructor */
	public HpoaStaffInfo(String staffId, String staffName, String staffIdcard,
			String staffGender, String staffPhotourl, String staffQualid,
			String staffMarry, String staffPhone, String staffAddress,
			String staffDept, String staffPosi, String staffComp,
			String staffUserid, String bak1, String bak2, String bak3, String staffNumber) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffIdcard = staffIdcard;
		this.staffGender = staffGender;
		this.staffPhotourl = staffPhotourl;
		this.staffQualid = staffQualid;
		this.staffMarry = staffMarry;
		this.staffPhone = staffPhone;
		this.staffAddress = staffAddress;
		this.staffDept = staffDept;
		this.staffPosi = staffPosi;
		this.staffComp = staffComp;
		this.staffUserid = staffUserid;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
		this.staffNumber=staffNumber;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "STAFF_ID", unique = true, nullable = false, length = 32)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "STAFF_NAME", length = 32)
	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	@Column(name = "STAFF_IDCARD", length = 20)
	public String getStaffIdcard() {
		return this.staffIdcard;
	}

	public void setStaffIdcard(String staffIdcard) {
		this.staffIdcard = staffIdcard;
	}

	@Column(name = "STAFF_GENDER", length = 1)
	public String getStaffGender() {
		return this.staffGender;
	}

	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}

	@Column(name = "STAFF_PHOTOURL", length = 200)
	public String getStaffPhotourl() {
		return this.staffPhotourl;
	}

	public void setStaffPhotourl(String staffPhotourl) {
		this.staffPhotourl = staffPhotourl;
	}

	@Column(name = "STAFF_QUALID", length = 32)
	public String getStaffQualid() {
		return this.staffQualid;
	}

	public void setStaffQualid(String staffQualid) {
		this.staffQualid = staffQualid;
	}

	@Column(name = "STAFF_MARRY", length = 1)
	public String getStaffMarry() {
		return this.staffMarry;
	}

	public void setStaffMarry(String staffMarry) {
		this.staffMarry = staffMarry;
	}

	@Column(name = "STAFF_PHONE", length = 50)
	public String getStaffPhone() {
		return this.staffPhone;
	}

	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}

	@Column(name = "STAFF_ADDRESS", length = 200)
	public String getStaffAddress() {
		return this.staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	@Column(name = "STAFF_DEPT", length = 32)
	public String getStaffDept() {
		return this.staffDept;
	}

	public void setStaffDept(String staffDept) {
		this.staffDept = staffDept;
	}

	@Column(name = "STAFF_POSI", length = 32)
	public String getStaffPosi() {
		return this.staffPosi;
	}

	public void setStaffPosi(String staffPosi) {
		this.staffPosi = staffPosi;
	}

	@Column(name = "STAFF_COMP", length = 32)
	public String getStaffComp() {
		return this.staffComp;
	}

	public void setStaffComp(String staffComp) {
		this.staffComp = staffComp;
	}

	@Column(name = "STAFF_USERID", length = 32)
	public String getStaffUserid() {
		return this.staffUserid;
	}

	public void setStaffUserid(String staffUserid) {
		this.staffUserid = staffUserid;
	}

	@Column(name = "BAK1", length = 32)
	public String getBak1() {
		return this.bak1;
	}

	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

	@Column(name = "BAK2", length = 32)
	public String getBak2() {
		return this.bak2;
	}

	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}

	@Column(name = "BAK3", length = 32)
	public String getBak3() {
		return this.bak3;
	}

	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}

	@Column(name = "STAFF_NUMBER", length = 32)
	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

}