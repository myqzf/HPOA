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
 * ActIdGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_ID_GROUP", schema = "HPOA")
public class ActIdGroup implements java.io.Serializable {

	// Fields

	private String id;
	private BigDecimal rev;
	private String name;
	private String type;
	private Set<ActIdMembership> actIdMemberships = new HashSet<ActIdMembership>(
			0);

	// Constructors

	/** default constructor */
	public ActIdGroup() {
	}

	/** minimal constructor */
	public ActIdGroup(String id) {
		this.id = id;
	}

	/** full constructor */
	public ActIdGroup(String id, BigDecimal rev, String name, String type,
			Set<ActIdMembership> actIdMemberships) {
		this.id = id;
		this.rev = rev;
		this.name = name;
		this.type = type;
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

	@Column(name = "NAME_")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE_")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "actIdGroup")
	public Set<ActIdMembership> getActIdMemberships() {
		return this.actIdMemberships;
	}

	public void setActIdMemberships(Set<ActIdMembership> actIdMemberships) {
		this.actIdMemberships = actIdMemberships;
	}

}