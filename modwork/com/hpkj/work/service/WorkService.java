package com.hpkj.work.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.orgmanage.entity.SysOrgInfo;
import com.hpkj.work.entity.HpoaWorkSubAssign;
import com.hpkj.work.vo.ControlWorkListVo;
import com.hpkj.work.vo.LeaderVo;
import com.hpkj.work.vo.ReceiveWorkDetailVo;
import com.hpkj.work.vo.ReceiveWorkListVo;
import com.hpkj.work.vo.ReportDetailVo;
import com.hpkj.work.vo.ReportListVo;
import com.hpkj.work.vo.SimpleResponseVo;
import com.hpkj.work.vo.SubAssignVo;
import com.hpkj.work.vo.SubStaffVo;

public interface WorkService extends IBaseService {
	/**
	 * 根据员工id获取他所管理的员工
	 * @param staffid 员工id
	 * @return
	 * @throws BaseException
	 */
	public List<SubStaffVo> getSubOrgStaffList(String staffid)throws BaseException;
	/**
	 * 根据员工id获取所在部门或部门列表
	 * @param staffid
	 * @return
	 * @throws BaseException
	 */
	public List<SysOrgInfo> getOrgList(String staffid)throws BaseException;
	/**
	 * 根据部门id获取下属员工
	 * @param orgid 部门id
	 * @return
	 * @throws BaseException
	 */
	public List<SubStaffVo> getSubStaffList(String orgid)throws BaseException;
	/**
	 * 添加工作任务
	 * @param orgid 发布工作部门
	 * @param title 工作标题
	 * @param content 工作内容
	 * @param lhws 工作详细情况
	 * @return 0:系统繁忙,1:添加成功,2:添加失败,3:详细情况为空,4:标题或发布部门为空
	 * @throws BaseException
	 */
	public int addWork(String orgid,String title,String content,List<HpoaWorkSubAssign> lhws)throws BaseException;
	/**
	 * 添加详细工作任务
	 * @param lhws 详细工作任务列表
	 * @param workAssignid 工作id
	 * @return 0:系统繁忙,1:添加成功,2:添加是吧,3:内容为空
	 * @throws BaseException
	 */
	public int addSubWork(List<HpoaWorkSubAssign> lhws,String workAssignid)throws BaseException;
	/**
	 * 更新工作状态
	 * @param workAssignid 工作id
	 * @param percent 百分比
	 * @return 0:系统繁忙,1:添加成功,2:添加失败,3:参数为空
	 * @throws BaseException
	 */
	public int editWork(String workAssignid,String percent)throws BaseException;
	/**
	 * 根据员工id获取所在部门下发的工作任务
	 * @param staffid 员工id
	 * @param from 起始长度
	 * @param length 每页长度
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @return
	 * @throws BaseException
	 */
	public List<ControlWorkListVo> getWorkControlList(String staffid, int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 根据员工id获取他所收到的工作任务
	 * @param staffid 员工id
	 * @param from 起始长度
	 * @param length 每页长度
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @return
	 * @throws BaseException
	 */
	public List<ReceiveWorkListVo> getReceiveWorkList(String staffid, int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 根据分配的工作任务id查询详细信息
	 * @param subAssignid 工作任务id
	 * @return
	 * @throws Exception
	 */
	public ReceiveWorkDetailVo getReceiveWorkDetail(String subAssignid)throws BaseException;
	/**
	 * 根据工作任务id获取回复列表
	 * @param subAssignid 工作安排id
	 * @return
	 * @throws Exception
	 */
	public List<SimpleResponseVo> getResponseList(String subAssignid)throws BaseException;
	/**
	 * 添加工作汇报
	 * @param subAssignid 工作任务id
	 * @param staffid 汇报员工id
	 * @param content 汇报正文
	 * @param percent 进度
	 * @return 0:系统繁忙,1:添加成功,2:添加失败,3:未填写工作任务id
	 * @throws BaseException
	 */
	public int addWorkResponse(String subAssignid, String staffid, String content, String percent)throws BaseException;
	/**
	 * 根据工作id获取分配的工作任务列表
	 * @param assignid 工作id
	 * @return
	 * @throws BaseException
	 */
	public List<SubAssignVo> getSubAssignList(String assignid)throws BaseException;
	/**
	 * 根据工作安排id修改状态
	 * @param subAssignid 工作安排id
	 * @param value 状态:1正在进行,2暂停,3终止,4完结
	 * @return 0:系统繁忙,1:修改成功,2:修改失败,3:关键参数为空
	 * @throws BaseException
	 */
	public int editSubAssignStatus(String subAssignid,String value)throws BaseException;
	/**
	 * 完成工作任务
	 * @param subAssignid 工作安排(工作任务)id
	 * @return 0:系统繁忙,1:修改成功,2:修改失败,3:工作安排id为空
	 * @throws BaseException
	 */
	public int endSubAssign(String subAssignid)throws BaseException;
	/**
	 * 根据工作id修改状态
	 * @param assignid 工作id
	 * @param value 状态:1正在进行,2暂停,3终止,4完结
	 * @return 0:系统繁忙,1:修改成功,2:修改失败,3:关键参数为空
	 * @throws BaseException
	 */
	public int editAssignStatus(String assignid,String value)throws BaseException;
	/**
	 * 完成工作
	 * @param assignid 工作id
	 * @param baktxt 完成备注
	 * @return 0:系统繁忙,1:修改成功,2:修改失败,3:工作id为空
	 * @throws BaseException
	 */
	public int endAssign(String assignid,String baktxt)throws BaseException;
	/**
	 * 根据员工id获取上级管理者列表
	 * @param staffid 员工id
	 * @return
	 * @throws BaseException
	 */
	public List<LeaderVo> getLeaderList(String staffid)throws BaseException;
	/**
	 * 新增单独回复
	 * @param staffid 回复人id
	 * @param orgid 部门id
	 * @param leaderid 接收领导id
	 * @param title 标题
	 * @param content 正文
	 * @return 0:系统繁忙,1:修改成功,2:修改失败,3:关键参数为空
	 * @throws BaseException
	 */
	public int addIndividualResponse(String staffid,String orgid,String leaderid,String title,String content)throws BaseException;
	/**
	 * 获取收到的汇报列表
	 * @param staffid 员工id
	 * @param from
	 * @param length
	 * @param sidx
	 * @param sord
	 * @return
	 * @throws BaseException
	 */
	public List<ReportListVo> getReportList(String staffid, int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 获取汇报详细信息
	 * @param reportid 汇报id
	 * @return
	 * @throws BaseException
	 */
	public ReportDetailVo getReportDetail(String reportid)throws BaseException;
	/**
	 * 获取全部工作列表
	 * @param from
	 * @param length
	 * @param sidx
	 * @param sord
	 * @return
	 * @throws BaseException
	 */
	public List<ControlWorkListVo> getAllWorkList(int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 根据工作id获取分配的工作任务以及回复列表
	 * @param assignid 工作id
	 * @return
	 * @throws BaseException
	 */
	public List<SubAssignVo> getSubAssignListAndReport(String assignid)throws BaseException;
}
