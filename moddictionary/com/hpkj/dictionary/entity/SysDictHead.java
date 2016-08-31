package com.hpkj.dictionary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysDictHead entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_DICT_HEAD", schema = "HPOA")
public class SysDictHead implements java.io.Serializable {

	// Fields

	private Integer headId;
	private String headName;
	private String bak1;
	private String bak2;

	// Constructors

	/** default constructor */
	public SysDictHead() {
	}

	/** minimal constructor */
	public SysDictHead(Integer headId) {
		this.headId = headId;
	}

	/** full constructor */
	public SysDictHead(Integer headId, String headName, String bak1, String bak2) {
		this.headId = headId;
		this.headName = headName;
		this.bak1 = bak1;
		this.bak2 = bak2;
	}

	// Property accessors
	@Id
	@Column(name = "HEAD_ID", unique = true, nullable = false, precision = 9, scale = 0)
	public Integer getHeadId() {
		return this.headId;
	}

	public void setHeadId(Integer headId) {
		this.headId = headId;
	}

	@Column(name = "HEAD_NAME", length = 100)
	public String getHeadName() {
		return this.headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
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

}