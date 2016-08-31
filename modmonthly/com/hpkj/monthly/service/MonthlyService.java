package com.hpkj.monthly.service;

import java.io.File;
import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.dictionary.entity.SysDictHead;
import com.hpkj.dictionary.entity.SysDictItems;
import com.hpkj.monthly.entity.HpoaMonthsumInfo;
import com.hpkj.monthly.vo.EachMonthlyVo;
import com.hpkj.monthly.vo.HpoaMonthsumInfoVo;
import com.hpkj.monthly.vo.ManageMonthsumVo;
import com.hpkj.monthly.vo.MonthlyVo;
import com.hpkj.system.entity.SysRoleInfo;
import com.hpkj.system.vo.SysRoleInfoVo;

public interface MonthlyService extends IBaseService {

	/**
	 * 查询字典项--月份信息
	 * @param
	 * @throws BaseException
	 */
	public List<SysDictItems> getDictMonthList()throws BaseException;

	
	/**
	 *保存撰写的月报
	 * @param title  标题
	 * @param month  所属月份
	 * @param content 内容
     * @param staffId  员工Id
	 * @return
	 * @throws BaseException 
	 */
	public String addSaveMonthly(HpoaMonthsumInfo hpoaMonthsumInfo,String staffId ) throws BaseException;


	/**
	 * 保存上传的月报
	 * @param staffId  员工Id
	 * @param fileUrl  保存路径
	 * @param title    标题
	 * @param date     上传日期
	 * @param content  内容
	 * @param month 
	 * @return
	 * @throws BaseException
	 */
	public int addSaveUploadMonthly(String staffId, String url, String title,
			String datetime,  String month)throws BaseException;


	/**
	 * 员工查询已提交月报列表
	 * @param hpoaMonthsumInfo
	 * @param page
	 * @param rows
	 * @param from 开始行
	 * @param length 每页行数
	 * @return
	 * @throws BaseException
	 */
	public HpoaMonthsumInfoVo searchEachMonthly(
			HpoaMonthsumInfo hpoaMonthsumInfo, String page, String rows,
			int from, int length,String sidx,String sord,String staffId)throws BaseException;


	/**
	 * 根据monthsumid查询具体月报内容
	 * @param monthsumid 月报Id
	 * @return
	 * @throws BaseException 
	 */
	public HpoaMonthsumInfo searchEachMonthlyInfo(String monthsumid) throws BaseException;
	
	/**
	 * 根据monthsumid查询具体月报内容  带月份
	 * @param monthsumid 月报Id
	 * @return
	 * @throws BaseException 
	 */
	public EachMonthlyVo searchEachMonthlyInfoTwo(String monthsumid) throws BaseException;



	/**
	 * 根据monthsumid 删除月报
	 * @param monthsumid 月报Id
	 * @return
	 * @throws BaseException 
	 */
	public String deleteMonthly(String monthsumid) throws BaseException;


	/**
	 * 保存修改的月报
	 * @param monthsumid 月报Id
	 * @param title 标题
	 * @param month 所属月份
	 * @param content 内容
	 * @return
	 * @throws BaseException 
	 */
	public String modifyMonthly(HpoaMonthsumInfo hpoaMonthsumInfo,String staffId) throws BaseException;

 
	/**
	 * 查询当月月报是否已提交
	 * @param monthscope 所属月份
	 * @param staffId   员工Id
	 * @return
	 * @throws BaseException 
	 */
	public String seachIfSubmitted(String monthscope, String staffId) throws BaseException;

 
	/**
	 * 查找年份范围
	 * @return  
	 * @throws BaseException 
	 */
	public List<HpoaMonthsumInfo> searchYearScope() throws BaseException;


	/**
	 * 查询月报最新所属月份
	 * @return
	 * @throws BaseException
	 */
	public HpoaMonthsumInfo searchNewMonth() throws BaseException;

 
	/**
	 * 当前最新提交的所有月报
	 * @param year 所属年份
	 * @param month 所属月份
	 * @param page 当前页
	 * @param rows 每页显示数据长度
	 * @param from 开始行
	 * @param length 行数
	 * @return
	 * @throws BaseException 
	 */
	public ManageMonthsumVo  searchCurrentMonthly(
			 String year, String month,
			String page, String rows, int from, int length,String sidx,String sord) throws BaseException;

 
	/** 
	 * 查询当月未提交月报人员
	 * @param year 所属年份
	 * @param month 所属月份
	 * @param page 当前页
	 * @param rows 页显示数据长度
	 * @param from 开始行
	 * @param length 行数
	 * @return
	 * @throws BaseException 
	 */
	public ManageMonthsumVo searchNotSubmitStaff(String year, String month,
			String page, String rows, int from, int length,String sidx,String sord) throws BaseException;



	/**
	 * 查询员工最新一次提交的月报
	 * @param staffId  员工ID
	 * @return
	 * @throws BaseException 
	 */
	public List<EachMonthlyVo> getLatestMonthly(String staffId) throws BaseException;
	
	
}
