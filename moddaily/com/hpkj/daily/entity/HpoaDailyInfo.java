package com.hpkj.daily.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaDailyInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_DAILY_INFO", schema = "HPOA")
public class HpoaDailyInfo implements java.io.Serializable {

	// Fields

	private String dailyId;//日志ID
	private String staffId;//员工ID
	private String daydate;//日期
	private String title;//标题
	private String content;//内容
	private String plan;//明日计划
	private String state;//状态
	private String inputtime;//输入时间
	private String bak1;
	private String bak2;
	private String bak3;
	
	// Constructors

	/** default constructor */
	public HpoaDailyInfo() {
	}

	/** minimal constructor */
	public HpoaDailyInfo(String dailyId) {
		this.dailyId = dailyId;
	}

	/** full constructor */
	public HpoaDailyInfo(String dailyId, String staffId, String daydate,
			String title, String content, String plan, String state,
			String inputtime, String bak1, String bak2, String bak3) {
		this.dailyId = dailyId;//日志ID
		this.staffId = staffId;//员工ID
		this.daydate = daydate;//日期
		this.title = title;//标题
		this.content = content;//内容
		this.plan = plan;//明日计划
		this.state = state;//状态
		this.inputtime = inputtime;//录入时间
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.bak3 = bak3;
	}

	// Property accessors

	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "DAILY_ID", unique = true, nullable = false, length = 32)
	public String getDailyId() {
		return this.dailyId;
	}

	public void setDailyId(String dailyId) {
		this.dailyId = dailyId;
	}

	@Column(name = "STAFF_ID", length = 32)
	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Column(name = "DAYDATE", length = 20)
	public String getDaydate() {
		return this.daydate;
	}

	public void setDaydate(String daydate) {
		this.daydate = daydate;
	}

	@Column(name = "TITLE", length = 2000)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT", length = 4000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "PLAN", length = 4000)
	public String getPlan() {
		return this.plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "INPUTTIME", length = 20)
	public String getInputtime() {
		return this.inputtime;
	}

	public void setInputtime(String inputtime) {
		this.inputtime = inputtime;
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


}