package com.hpkj.dictionary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysDictItems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_DICT_ITEMS", schema = "HPOA")
public class SysDictItems implements java.io.Serializable {

	// Fields

	private Integer itemsId;
	private Integer headId;
	private String itemsName;
	private String itemsShort;
	private Integer pid;
	private Integer sort;
	private String bak1;
	private String bak2;
	private String ifleaf;

	// Constructors

	/** default constructor */
	public SysDictItems() {
	}

	/** minimal constructor */
	public SysDictItems(Integer itemsId) {
		this.itemsId = itemsId;
	}

	/** full constructor */
	public SysDictItems(Integer itemsId, Integer headId, String itemsName,
			String itemsNumber, Integer pid, Integer sort, String bak1,
			String bak2, String ifleaf) {
		this.itemsId = itemsId;
		this.headId = headId;
		this.itemsName = itemsName;
		this.itemsShort = itemsShort;
		this.pid = pid;
		this.sort = sort;
		this.bak1 = bak1;
		this.bak2 = bak2;
		this.ifleaf = ifleaf;
	}

	// Property accessors
	@Id
	@Column(name = "ITEMS_ID", unique = true, nullable = false, precision = 9, scale = 0)
	public Integer getItemsId() {
		return this.itemsId;
	}

	public void setItemsId(Integer itemsId) {
		this.itemsId = itemsId;
	}

	@Column(name = "HEAD_ID", precision = 9, scale = 0)
	public Integer getHeadId() {
		return this.headId;
	}

	public void setHeadId(Integer headId) {
		this.headId = headId;
	}

	@Column(name = "ITEMS_NAME", length = 200)
	public String getItemsName() {
		return this.itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	@Column(name = "ITEMS_SHORT", length = 1000)
	public String getItemsShort() {
		return this.itemsShort;
	}

	public void setItemsShort(String itemsShort) {
		this.itemsShort = itemsShort;
	}

	@Column(name = "PID", precision = 9, scale = 0)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "SORT", precision = 9, scale = 0)
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "BAK1", length = 100)
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

	@Column(name = "IFLEAF", length = 1)
	public String getIfleaf() {
		return this.ifleaf;
	}

	public void setIfleaf(String ifleaf) {
		this.ifleaf = ifleaf;
	}

}