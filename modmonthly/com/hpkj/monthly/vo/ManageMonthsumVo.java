package com.hpkj.monthly.vo;

import java.util.ArrayList;
import java.util.List;

import com.hpkj.monthly.entity.HpoaMonthsumInfo;
import com.hpkj.staff.entity.HpoaStaffInfo;


public class ManageMonthsumVo {

	private Integer total;
	private Integer page;
	private Integer records;
	private String  staffnumber;
	
	private List<MonthlyVo> gridDTOs = new ArrayList<MonthlyVo>();
	private List<HpoaStaffInfo> gridDTOsStaff = new ArrayList<HpoaStaffInfo>();
	
	
	  
	
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
	public List<MonthlyVo> getGridDTOs() {
		return gridDTOs;
	}
	public void setGridDTOs(List<MonthlyVo> gridDTOs) {
		this.gridDTOs = gridDTOs;
	}
	public String getStaffnumber() {
		return staffnumber;
	}
	public void setStaffnumber(String staffnumber) {
		this.staffnumber = staffnumber;
	}
	public List<HpoaStaffInfo> getGridDTOsStaff() {
		return gridDTOsStaff;
	}
	public void setGridDTOsStaff(List<HpoaStaffInfo> gridDTOsStaff) {
		this.gridDTOsStaff = gridDTOsStaff;
	}

	
	
	
	
}
