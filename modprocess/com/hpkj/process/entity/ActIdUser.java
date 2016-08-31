package com.hpkj.process.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ActIdUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_ID_USER", schema = "HPOA")
public class ActIdUser implements java.io.Serializable {

	// Fields

	private String id;
	private BigDecimal rev;
	private String first;
	private String last;
	private String email;
	private String pwd;
	private String pictureId;
	private Set<ActIdMembership> actIdMemberships = new HashSet<ActIdMembership>(
			0);

	// Constructors

	/** default constructor */
	public ActIdUser() {
	}

	/** minimal constructor */
	public ActIdUser(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActIdUser(String id, BigDecimal rev, String first, String last,
			String email, String pwd, String pictureId,
			Set<ActIdMembership> actIdMemberships) {
		this.id = id;
		this.rev = rev;
		this.first = first;
		this.last = last;
		this.email = email;
		this.pwd = pwd;
		this.pictureId = pictureId;
		this.actIdMemberships = actIdMemberships;
	}

	// Property accessors
	@Id
	@Column(name = "ID_", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

	@Column(name = "FIRST_")
	public String getFirst() {
		return this.first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	@Column(name = "LAST_")
	public String getLast() {
		return this.last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	@Column(name = "EMAIL_")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PWD_")
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "PICTURE_ID_")
	public String getPictureId() {
		return this.pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actIdUser")
	public Set<ActIdMembership> getActIdMemberships() {
		return this.actIdMemberships;
	}

	public void setActIdMemberships(Set<ActIdMembership> actIdMemberships) {
		this.actIdMemberships = actIdMemberships;
	}

}