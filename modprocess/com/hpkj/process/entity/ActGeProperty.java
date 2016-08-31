package com.hpkj.process.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ActGeProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_GE_PROPERTY", schema = "HPOA")
public class ActGeProperty implements java.io.Serializable {

	// Fields

	private String name;
	private String value;
	private BigDecimal rev;

	// Constructors

	/** default constructor */
	public ActGeProperty() {
	}

	/** minimal constructor */
	public ActGeProperty(String name) {
		this.name = name;
	}

	/** full constructor */
	public ActGeProperty(String name, String value, BigDecimal rev) {
		this.name = name;
		this.value = value;
		this.rev = rev;
	}

	// Property accessors
	@Id
	@Column(name = "NAME_", unique = true, nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VALUE_")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

}