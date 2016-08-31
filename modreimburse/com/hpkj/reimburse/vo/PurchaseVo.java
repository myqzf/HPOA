package com.hpkj.reimburse.vo;

public class PurchaseVo {
	private String reidate;//日期
	private String reidept;//所在部门
	private Integer number;//单据数量
	private String item;//项目名
	private String remark;//备注
	private String money;//金额
	private String uppermoney;//大写金额
	public String getReidate() {
		return reidate;
	}
	public void setReidate(String reidate) {
		this.reidate = reidate;
	}
	public String getReidept() {
		return reidept;
	}
	public void setReidept(String reidept) {
		this.reidept = reidept;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getUppermoney() {
		return uppermoney;
	}
	public void setUppermoney(String uppermoney) {
		this.uppermoney = uppermoney;
	}
	

}
