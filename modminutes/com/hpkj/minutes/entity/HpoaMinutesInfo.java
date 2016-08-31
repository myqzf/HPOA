package com.hpkj.minutes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaMinutesInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_MINUTES_INFO", schema = "HPOA")
public class HpoaMinutesInfo implements java.io.Serializable {

	// Fields

	private String minutesid;
	private String staffId;
	private String staffComp;
	private String meetingtheme;
	private String meetingcontent;
	private String meetingdate;
	private String meetingplace;
	private String attendees;
	private String minuteslink;
	private String remark;
	private String uploaddate;
	private String bak1;
	private String bak2;
	private String bak3;
	private String meetinghost;

	// Constructors

	/** default constructor */
	public HpoaMinutesInfo() {
	}

	/** minimal constructor */
	public HpoaMinutesInfo(String minutesid) {
		this.minutesid = minutesid;
	}

	/** full constructor */
	public HpoaMinutesInfo(String minutesid, String staffId, String staffComp,
			String meetingtheme, String meetingcontent, String meetingdate,
			String meetingplace, String attendees, String minuteslink,
			String remark, String uploaddate, String bak1, String bak2,
			String bak3, String meetinghost) {
		this.minutesid = minutesid;
		this.staffId = staffId;
		this.staffComp = staffComp;
		this.meetingtheme = meetingtheme;
		this.meetingcontent = meetingcontent;
		this.meetingdate = meetingdate;
		this.meetingplace = meetingplace;
		this.attendees = attendees;
		this.minuteslink = minuteslink;
		this.remark = remark;
		this.uploaddate = uploaddate;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
		this.meetinghost = meetinghost;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "MINUTESID", unique = true, nullable = false, length = 32)
	public String getMinutesid() {
		return this.minutesid;
	}

	public void setMinutesid(String minutesid) {
		this.minutesid = minutesid;
	}

	@Column(name = "STAFF_ID", length = 32)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "STAFF_COMP", length = 32)
	public String getStaffComp() {
		return this.staffComp;
	}

	public void setStaffComp(String staffComp) {
		this.staffComp = staffComp;
	}

	@Column(name = "MEETINGTHEME", length = 500)
	public String getMeetingtheme() {
		return this.meetingtheme;
	}

	public void setMeetingtheme(String meetingtheme) {
		this.meetingtheme = meetingtheme;
	}

	@Column(name = "MEETINGCONTENT")
	public String getMeetingcontent() {
		return this.meetingcontent;
	}

	public void setMeetingcontent(String meetingcontent) {
		this.meetingcontent = meetingcontent;
	}

	@Column(name = "MEETINGDATE", length = 32)
	public String getMeetingdate() {
		return this.meetingdate;
	}

	public void setMeetingdate(String meetingdate) {
		this.meetingdate = meetingdate;
	}

	@Column(name = "MEETINGPLACE", length = 200)
	public String getMeetingplace() {
		return this.meetingplace;
	}

	public void setMeetingplace(String meetingplace) {
		this.meetingplace = meetingplace;
	}

	@Column(name = "ATTENDEES", length = 500)
	public String getAttendees() {
		return this.attendees;
	}

	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}

	@Column(name = "MINUTESLINK", length = 2000)
	public String getMinuteslink() {
		return this.minuteslink;
	}

	public void setMinuteslink(String minuteslink) {
		this.minuteslink = minuteslink;
	}

	@Column(name = "REMARK", length = 2000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "UPLOADDATE", length = 32)
	public String getUploaddate() {
		return this.uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
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

	@Column(name = "MEETINGHOST", length = 32)
	public String getMeetinghost() {
		return this.meetinghost;
	}

	public void setMeetinghost(String meetinghost) {
		this.meetinghost = meetinghost;
	}

}