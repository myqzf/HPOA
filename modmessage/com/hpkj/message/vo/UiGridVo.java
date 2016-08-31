package com.hpkj.message.vo;

import java.util.List;

public class UiGridVo {
	private Integer total;
	private Integer page;
	private Integer records;
	private List rows;
	public UiGridVo(){
		
	}
	public UiGridVo(int total,int page,int records,List rows){
		this.setTotal(total);
		this.setPage(page);
		this.setRecords(records);
		this.setRows(rows);
	}
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
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
