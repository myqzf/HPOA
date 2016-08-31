package com.hpkj.reimburse.service;

import java.util.List;

import org.activiti.engine.task.Task;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.message.vo.StaffsVo;
import com.hpkj.reimburse.entity.HpoaReimburseInfos;
import com.hpkj.reimburse.entity.HpoaReimburseInfo;
import com.hpkj.reimburse.vo.PurchaseVo;
import com.hpkj.reimburse.vo.ReimburseListVo;
import com.hpkj.reimburse.vo.TaskInfoVo;
import com.hpkj.staff.vo.StaffDetailInfoVo;

public interface ReimburseService extends IBaseService{
	/**
	 * 获取所有员工信息
	 * @return
	 * @throws BaseException
	 */
	public List<StaffsVo> getAllStaff()throws BaseException;
	/**
	 * 根据员工id获取员工信息
	 * @param staffId
	 * @return
	 * @throws BaseException
	 */
	public StaffDetailInfoVo getStaffInfo(String staffId) throws BaseException;
	/**
	 * 提交报销申请
	 * @param staffid
	 * @param reimInfos
	 * @param itemlist
	 * @param reasonlist
	 * @param moneylist
	 * @return
	 * @throws BaseException
	 */
	public int addReimburse(String staffid,HpoaReimburseInfos reimInfos,String itemlist,String reasonlist,String moneylist)throws BaseException;
	
	//////////////////TODO   采购报销单
	
	/*
	 * 提交采购报销申请
	 * @see com.hpkj.message.service.MessageService#sendMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int savePurchase(String staffid,PurchaseVo pv)throws BaseException;
	
	/*
	 * 获取费用报销列表
	 */
	public List<ReimburseListVo> getReimburseList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException;
	
	/*
	 * 获取采购报销列表
	 */
	public List<ReimburseListVo> getPurchaseList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException;
	
	/*
	 * 删除报销单
	 * @see com.hpkj.notice.service.NoticeService#delNotice(java.lang.String)
	 */
	public int delPurchase(String reimburseid)throws BaseException;
	
	//////////////////TODO   项目报销单
	
	/*
	 * 获取项目报销单列表
	 */
	public List<ReimburseListVo> getItemList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException;
	
	/*
	 * 删除项目报销单
	 * @see com.hpkj.notice.service.NoticeService#delNotice(java.lang.String)
	 */
	public int delItem(String reimburseid)throws BaseException;
	
	/*
	 * 提交项目报销申请
	 * @see com.hpkj.message.service.MessageService#sendMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int saveItem(String staffid,PurchaseVo pv)throws BaseException;
	
	//////////////////TODO   费用报销单
	
	/*
	 * 获取项目报销单列表
	 */
	public List<ReimburseListVo> getCostList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException;
	
	/*
	 * 提交费用报销申请
	 * @see com.hpkj.message.service.MessageService#sendMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int addCost(String staffid,HpoaReimburseInfos reimInfos,String itemlist,String remarklist,String moneylist)throws BaseException;
	
	/*
	 * 提交费用报销单
	 * @see com.hpkj.message.service.MessageService#sendMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int saveCost(String staffid,HpoaReimburseInfos reimInfos,String itemlist,String remarklist,String moneylist)throws BaseException;
	
	/*
	 * 删除费用报销单
	 * @see com.hpkj.notice.service.NoticeService#delNotice(java.lang.String)
	 */
	public int delCost(String reimburseid)throws BaseException;
	
	/**
	 * 获取费用报销单查看页的list
	 */
	public List<HpoaReimburseInfo> getList(String reimburseId) throws BaseException;
	
	////////////////////////////DOTO 采购申请单
	
	/*
	 * 获取采购申请单列表
	 */
	public List<ReimburseListVo> getPurchReqList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException;
	
	/*
	 * 删除采购申请单
	 */
	public int delPurchReq(String reimburseid)throws BaseException;
	
	/*
	 * 提交采购申请单
	 */
	public int savePurchReq(String staffid,HpoaReimburseInfos reimInfos,String itemlist,String numlist,String pricelist,String remarklist,String moneylist)throws BaseException;
	
	/**启动流程*/
	public void StartTask();
	
	/**查询我的个人任务*/  
	public List<Task> findPersonalTaskList(String userName);
	
	/**完成任务*/  
	public void endTask(String reimoney);
	
	/*======================qzf==分割线======================================================*/
	/**
	 * 启动流程
	 * @param reimburseId
	 */
	public void saveStartProcess(String reimburseId,String staffId)throws BaseException;
	/**
	 * 获取个人任务列表(带分页)
	 * @param username
	 * @return
	 * @throws BaseException
	 */
	public List<TaskInfoVo> findTaskListByName(String username,String page, String rows, int from, int length,String order,String sort)throws BaseException;
	/**
	 * 获取个人任务列表(不带分页)
	 * @param username
	 * @return
	 * @throws BaseException
	 */
	public List<TaskInfoVo> findTaskListByName2(String username, String page,String rows, int from, int length)throws BaseException;
}
