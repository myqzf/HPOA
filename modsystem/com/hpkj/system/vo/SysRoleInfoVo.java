package com.hpkj.system.vo;

import java.util.ArrayList;
import java.util.List;

import com.hpkj.system.entity.SysRoleInfo;

public class SysRoleInfoVo {

	private Integer total;
	private Integer page;
	private Integer records;
	private List<SysRoleInfo> gridDTOs = new ArrayList<SysRoleInfo>();
	
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
	public List<SysRoleInfo> getGridDTOs() {
		return gridDTOs;
	}
	public void setGridDTOs(List<SysRoleInfo> gridDTOs) {
		this.gridDTOs = gridDTOs;
	}
	
	
	
}
