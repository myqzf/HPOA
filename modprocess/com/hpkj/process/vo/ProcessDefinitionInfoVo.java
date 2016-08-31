package com.hpkj.process.vo;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;

import com.hpkj.process.entity.ActReProcdef;


public class ProcessDefinitionInfoVo {

	private Integer total;
	private Integer page;
	private Integer records;  
	private List<ProcessDefinitionListVo> rows = new ArrayList<ProcessDefinitionListVo>();
	
	private List<ProcessDefinitionEntity> entityrows = new ArrayList<ProcessDefinitionEntity>();
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public List<ProcessDefinitionListVo> getRows() {
		return rows;
	}
	public void setRows(List<ProcessDefinitionListVo> rows) {
		this.rows = rows;
	}
	public List<ProcessDefinitionEntity> getEntityrows() {
		return entityrows;
	}
	public void setEntityrows(List<ProcessDefinitionEntity> entityrows) {
		this.entityrows = entityrows;
	}
	

	
	
	
	
	
	
	
	
}
