package com.hpkj.dictionary.vo;

import java.util.List;

import com.hpkj.dictionary.entity.SysDictHead;
import com.hpkj.dictionary.entity.SysDictItems;

public class TreeInfo {
	
	private String idKey;//zTree id
	private String pIdKey;//zTree 父级id
	private String name;//zTree 名称
	private Integer itemsId;//字典项Id
	private String itemsName;// 字典项名称
	private String pid;
	private Integer headId;//字典头Id
	private String ifleaf;//是否是叶子节点
	//public List<SysDictHead> dictHeadList;//字典头列表
	//public List<SysDictItems> dictItemsList;//字典项列表
	
	public String getIdKey() {
		return idKey;
	}
	public void setIdKey(String idKey) {
		this.idKey = idKey;
	}
	public String getpIdKey() {
		return pIdKey;
	}
	public void setpIdKey(String pIdKey) {
		this.pIdKey = pIdKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getIfleaf() {
		return ifleaf;
	}
	public void setIfleaf(String ifleaf) {
		this.ifleaf = ifleaf;
	}
	public Integer getHeadId() {
		return headId;
	}
	public void setHeadId(Integer headId) {
		this.headId = headId;
	}
	public Integer getItemsId() {
		return itemsId;
	}
	public void setItemsId(Integer itemsId) {
		this.itemsId = itemsId;
	}
	
	
	
	

	
}
