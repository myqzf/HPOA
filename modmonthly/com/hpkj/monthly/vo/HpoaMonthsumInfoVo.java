package com.hpkj.monthly.vo;

import java.util.ArrayList;
import java.util.List;

import com.hpkj.monthly.entity.HpoaMonthsumInfo;

public class HpoaMonthsumInfoVo {

	private Integer total;
	private Integer page;
	private Integer records;
	private List<HpoaMonthsumInfo> gridDTOs = new ArrayList<HpoaMonthsumInfo>();
	private List<EachMonthlyVo> gridDTOsEach = new ArrayList<EachMonthlyVo>();
	
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
	public List<HpoaMonthsumInfo> getGridDTOs() {
		return gridDTOs;
	}
	
	public void setGridDTOs(List<HpoaMonthsumInfo> gridDTOs) {
		this.gridDTOs = gridDTOs;
	}

	public HpoaMonthsumInfoVo(){ 
	    }
	public List<EachMonthlyVo> getGridDTOsEach() {
		return gridDTOsEach;
	}
	public void setGridDTOsEach(List<EachMonthlyVo> gridDTOsEach) {
		this.gridDTOsEach = gridDTOsEach;
	}
	
	
	
}
