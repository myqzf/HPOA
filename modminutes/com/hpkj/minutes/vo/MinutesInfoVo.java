package com.hpkj.minutes.vo;

import java.util.ArrayList;
import java.util.List;


public class MinutesInfoVo {

	private Integer total;
	private Integer page;
	private Integer records;  
	//private List<HpoaMinutesInfo> rows = new ArrayList<HpoaMinutesInfo>();
	private List<MinutesListVo> rows = new ArrayList<MinutesListVo>();
	
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
	public List<MinutesListVo> getRows() {
		return rows;
	}
	public void setRows(List<MinutesListVo> rows) {
		this.rows = rows;
	}
	
	
	
	
	
	
}
