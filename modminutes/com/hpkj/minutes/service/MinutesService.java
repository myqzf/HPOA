package com.hpkj.minutes.service;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.minutes.entity.HpoaMinutesInfo;
import com.hpkj.minutes.vo.MinutesInfoVo;

public interface MinutesService extends IBaseService {

/**
 * 保存新增的会议纪要信息
 * @param hpoaMinutesInfo
 * @param staffId 添加者id
 * @return
 * @throws BaseException 
 */
public String addMinutesInfo(HpoaMinutesInfo hpoaMinutesInfo, String staffId) throws BaseException;
 
/** 
 * 查询会议纪要列表
 * @param hpoaMinutesInfo
 * @param page
 * @param rows
 * @param from
 * @param length
 * @return
 * @throws BaseException 
 */
public MinutesInfoVo searchMinutesList(HpoaMinutesInfo hpoaMinutesInfo,
		String page, String rows, int from, int length,String order,String sort) throws BaseException;
 
/**
 * 根据纪要id删除会议纪要
 * @param minutesid 纪要id
 * @return
 * @throws BaseException 
 */
public String deleteMinutesInfo(String minutesid) throws BaseException;
 
/**根据纪要id查询纪要详情
 * @param minutesid 纪要id
 * @return
 * @throws BaseException 
 */
public HpoaMinutesInfo searchMinutesInfo(String minutesid) throws BaseException;
 
/**
 * 保存修改的会议纪要
 * @param hpoaMinutesInfo
 * @param staffId 修改者id
 * @return
 * @throws BaseException 
 */
public String modifyMinutesInfo(HpoaMinutesInfo hpoaMinutesInfo, String staffId) throws BaseException;
 
/**
 * 删除会议纪要所属的附件
 * @param minutesid 纪要id
 * @return
 * @throws BaseException 
 */
public int deleteMinutesAttachment(String minutesid) throws BaseException;

}
