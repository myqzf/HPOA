package com.hpkj.daily.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.daily.entity.HpoaDailyInfo;
import com.hpkj.daily.vo.DailyInfoVo;

public interface DailyService extends IBaseService{
	/**
	 * 添加员工日志
	 * @param dailyInfo
	 * @return
	 * @throws BaseException
	 */
	public int addDaily(HpoaDailyInfo dailyInfo) throws BaseException;
	
	/**
	 * 根据员工id获取员工和职时间
	 * @param staffId
	 * @return
	 * @throws BaseException
	 */
	public String getStaffTime(String staffId) throws BaseException;
	/**
	 * 根据员工ID和日期获得指定日期的日志内容
	 * @param staffId
	 * @param daydate
	 * @return
	 * @throws BaseException
	 */	
	public DailyInfoVo getDailyInfo(String staffId,String daydate) throws BaseException;
	/**
	 * 判断当日日志是否已经存在
	 * @param staffId
	 * @param daydate
	 * @return true:当日日志已经存在;false:当日尚未写过日志
	 * @throws BaseException
	 */
	public boolean isExist(String staffId,String daydate) throws BaseException;
	/**
	 * 检索所有存在日志的日期
	 * @param staffId
	 * @return
	 * @throws BaseException
	 */
	public List timeList(String staffId) throws BaseException;
}
