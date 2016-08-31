package com.hpkj.message.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaMessageInfoId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_MESSAGE_INFO", schema = "HPOA")
public class HpoaMessageInfo implements java.io.Serializable {

	// Fields

	private String messageid;
	private String title;
	private String content;
	private String createtime;
	private String sendtime;
	private String rectime;
	private String bak1;
	private String bak2;
	private Integer status;

	// Constructors

	/** default constructor */
	public HpoaMessageInfo() {
	}

	/** minimal constructor */
	public HpoaMessageInfo(String messageid, Integer status) {
		this.messageid = messageid;
		this.status = status;
	}

	/** full constructor */
	public HpoaMessageInfo(String messageid, String title, String content,
			String createtime, String sendtime, String rectime, String bak1,
			String bak2, Integer status) {
		this.messageid = messageid;
		this.title = title;
		this.content = content;
		this.createtime = createtime;
		this.sendtime = sendtime;
		this.rectime = rectime;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "MESSAGEID", nullable = false, length = 32)
	public String getMessageid() {
		return this.messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CREATETIME", length = 20)
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "SENDTIME", length = 20)
	public String getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	@Column(name = "RECTIME", length = 20)
	public String getRectime() {
		return this.rectime;
	}

	public void setRectime(String rectime) {
		this.rectime = rectime;
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

	@Column(name = "STATUS", nullable = false, precision = 1, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HpoaMessageInfo))
			return false;
		HpoaMessageInfo castOther = (HpoaMessageInfo) other;

		return ((this.getMessageid() == castOther.getMessageid()) || (this
				.getMessageid() != null && castOther.getMessageid() != null && this
				.getMessageid().equals(castOther.getMessageid())))
				&& ((this.getTitle() == castOther.getTitle()) || (this
						.getTitle() != null && castOther.getTitle() != null && this
						.getTitle().equals(castOther.getTitle())))
				&& ((this.getContent() == castOther.getContent()) || (this
						.getContent() != null && castOther.getContent() != null && this
						.getContent().equals(castOther.getContent())))
				&& ((this.getCreatetime() == castOther.getCreatetime()) || (this
						.getCreatetime() != null
						&& castOther.getCreatetime() != null && this
						.getCreatetime().equals(castOther.getCreatetime())))
				&& ((this.getSendtime() == castOther.getSendtime()) || (this
						.getSendtime() != null
						&& castOther.getSendtime() != null && this
						.getSendtime().equals(castOther.getSendtime())))
				&& ((this.getRectime() == castOther.getRectime()) || (this
						.getRectime() != null && castOther.getRectime() != null && this
						.getRectime().equals(castOther.getRectime())))
				&& ((this.getBak1() == castOther.getBak1()) || (this.getBak1() != null
						&& castOther.getBak1() != null && this.getBak1()
						.equals(castOther.getBak1())))
				&& ((this.getBak2() == castOther.getBak2()) || (this.getBak2() != null
						&& castOther.getBak2() != null && this.getBak2()
						.equals(castOther.getBak2())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMessageid() == null ? 0 : this.getMessageid().hashCode());
		result = 37 * result
				+ (getTitle() == null ? 0 : this.getTitle().hashCode());
		result = 37 * result
				+ (getContent() == null ? 0 : this.getContent().hashCode());
		result = 37
				* result
				+ (getCreatetime() == null ? 0 : this.getCreatetime()
						.hashCode());
		result = 37 * result
				+ (getSendtime() == null ? 0 : this.getSendtime().hashCode());
		result = 37 * result
				+ (getRectime() == null ? 0 : this.getRectime().hashCode());
		result = 37 * result
				+ (getBak1() == null ? 0 : this.getBak1().hashCode());
		result = 37 * result
				+ (getBak2() == null ? 0 : this.getBak2().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		return result;
	}

}