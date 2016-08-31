package com.hpkj.message.vo;

import java.util.ArrayList;
import java.util.List;

public class JQGridVo {

	private int totalPage;
	private int rowCount;
	private List gridData = new ArrayList();
	public JQGridVo(){
		
	}
	public JQGridVo(List gridData,int rowCount,int pageSize){
		this.gridData=gridData;
		this.rowCount=rowCount;
		this.totalPage=(int) Math.ceil((double) rowCount / (double) pageSize);
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public List getGridData() {
		return gridData;
	}
	public void setGridData(List gridData) {
		this.gridData = gridData;
	}
	
	
	
}
