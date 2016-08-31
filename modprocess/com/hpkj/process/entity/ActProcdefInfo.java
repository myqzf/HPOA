package com.hpkj.process.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ActProcdefInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACT_PROCDEF_INFO", schema = "HPOA")
public class ActProcdefInfo implements java.io.Serializable {

	// Fields

	private String id;
	private ActReProcdef actReProcdef;
	private ActGeBytearray actGeBytearray;
	private BigDecimal rev;

	// Constructors

	/** default constructor */
	public ActProcdefInfo() {
	}

	/** minimal constructor */
	public ActProcdefInfo(String id, ActReProcdef actReProcdef) {
		this.id = id;
		this.actReProcdef = actReProcdef;
	}

	/** full constructor */
	public ActProcdefInfo(String id, ActReProcdef actReProcdef,
			ActGeBytearray actGeBytearray, BigDecimal rev) {
		this.id = id;
		this.actReProcdef = actReProcdef;
		this.actGeBytearray = actGeBytearray;
		this.rev = rev;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROC_DEF_ID_", nullable = false)
	public ActReProcdef getActReProcdef() {
		return this.actReProcdef;
	}

	public void setActReProcdef(ActReProcdef actReProcdef) {
		this.actReProcdef = actReProcdef;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INFO_JSON_ID_")
	public ActGeBytearray getActGeBytearray() {
		return this.actGeBytearray;
	}

	public void setActGeBytearray(ActGeBytearray actGeBytearray) {
		this.actGeBytearray = actGeBytearray;
	}

	@Column(name = "REV_", precision = 22, scale = 0)
	public BigDecimal getRev() {
		return this.rev;
	}

	public void setRev(BigDecimal rev) {
		this.rev = rev;
	}

}