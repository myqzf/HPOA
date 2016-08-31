package com.hpkj.message.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * HpoaMessageUserLinkId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HPOA_MESSAGE_USER_LINK", schema = "HPOA")
public class HpoaMessageUserLink implements java.io.Serializable {

	// Fields

	private String id;
	private String messageid;
	private String senderid;
	private String receverid;
	private Integer sendstatus;
	private Integer recevestatus;
	private Integer readstatus;
	private String rectime;
	private String bak1;
	private String bak2;

	// Constructors

	/** default constructor */
	public HpoaMessageUserLink() {
	}

	/** minimal constructor */
	public HpoaMessageUserLink(String id, String messageid, String senderid,
			String receverid) {
		this.id = id;
		this.messageid = messageid;
		this.senderid = senderid;
		this.receverid = receverid;
	}

	/** full constructor */
	public HpoaMessageUserLink(String id, String messageid, String senderid,
			String receverid, Integer sendstatus, Integer recevestatus,
			Integer readstatus, String rectime, String bak1, String bak2) {
		this.id = id;
		this.messageid = messageid;
		this.senderid = senderid;
		this.receverid = receverid;
		this.sendstatus = sendstatus;
		this.recevestatus = recevestatus;
		this.readstatus = readstatus;
		this.rectime = rectime;
		this.bak1 = bak1;
		this.bak2 = bak2;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "MESSAGEID", nullable = false, length = 32)
	public String getMessageid() {
		return this.messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	@Column(name = "SENDERID", nullable = false, length = 32)
	public String getSenderid() {
		return this.senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	@Column(name = "RECEVERID", nullable = false, length = 32)
	public String getReceverid() {
		return this.receverid;
	}

	public void setReceverid(String receverid) {
		this.receverid = receverid;
	}

	@Column(name = "SENDSTATUS", precision = 1, scale = 0)
	public Integer getSendstatus() {
		return this.sendstatus;
	}

	public void setSendstatus(Integer sendstatus) {
		this.sendstatus = sendstatus;
	}

	@Column(name = "RECEVESTATUS", precision = 1, scale = 0)
	public Integer getRecevestatus() {
		return this.recevestatus;
	}

	public void setRecevestatus(Integer recevestatus) {
		this.recevestatus = recevestatus;
	}

	@Column(name = "READSTATUS", precision = 1, scale = 0)
	public Integer getReadstatus() {
		return this.readstatus;
	}

	public void setReadstatus(Integer readstatus) {
		this.readstatus = readstatus;
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

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HpoaMessageUserLink))
			return false;
		HpoaMessageUserLink castOther = (HpoaMessageUserLink) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getMessageid() == castOther.getMessageid()) || (this
						.getMessageid() != null
						&& castOther.getMessageid() != null && this
						.getMessageid().equals(castOther.getMessageid())))
				&& ((this.getSenderid() == castOther.getSenderid()) || (this
						.getSenderid() != null
						&& castOther.getSenderid() != null && this
						.getSenderid().equals(castOther.getSenderid())))
				&& ((this.getReceverid() == castOther.getReceverid()) || (this
						.getReceverid() != null
						&& castOther.getReceverid() != null && this
						.getReceverid().equals(castOther.getReceverid())))
				&& ((this.getSendstatus() == castOther.getSendstatus()) || (this
						.getSendstatus() != null
						&& castOther.getSendstatus() != null && this
						.getSendstatus().equals(castOther.getSendstatus())))
				&& ((this.getRecevestatus() == castOther.getRecevestatus()) || (this
						.getRecevestatus() != null
						&& castOther.getRecevestatus() != null && this
						.getRecevestatus().equals(castOther.getRecevestatus())))
				&& ((this.getReadstatus() == castOther.getReadstatus()) || (this
						.getReadstatus() != null
						&& castOther.getReadstatus() != null && this
						.getReadstatus().equals(castOther.getReadstatus())))
				&& ((this.getRectime() == castOther.getRectime()) || (this
						.getRectime() != null && castOther.getRectime() != null && this
						.getRectime().equals(castOther.getRectime())))
				&& ((this.getBak1() == castOther.getBak1()) || (this.getBak1() != null
						&& castOther.getBak1() != null && this.getBak1()
						.equals(castOther.getBak1())))
				&& ((this.getBak2() == castOther.getBak2()) || (this.getBak2() != null
						&& castOther.getBak2() != null && this.getBak2()
						.equals(castOther.getBak2())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getMessageid() == null ? 0 : this.getMessageid().hashCode());
		result = 37 * result
				+ (getSenderid() == null ? 0 : this.getSenderid().hashCode());
		result = 37 * result
				+ (getReceverid() == null ? 0 : this.getReceverid().hashCode());
		result = 37
				* result
				+ (getSendstatus() == null ? 0 : this.getSendstatus()
						.hashCode());
		result = 37
				* result
				+ (getRecevestatus() == null ? 0 : this.getRecevestatus()
						.hashCode());
		result = 37
				* result
				+ (getReadstatus() == null ? 0 : this.getReadstatus()
						.hashCode());
		result = 37 * result
				+ (getRectime() == null ? 0 : this.getRectime().hashCode());
		result = 37 * result
				+ (getBak1() == null ? 0 : this.getBak1().hashCode());
		result = 37 * result
				+ (getBak2() == null ? 0 : this.getBak2().hashCode());
		return result;
	}

}