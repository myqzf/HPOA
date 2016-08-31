package com.hpkj.culture.service;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.culture.entity.HpoaCultureInfo;
import com.hpkj.culture.vo.CultureInfoVo;

public interface CultureService extends IBaseService {

	
/**
 * 保存添加的企业文化
 * @param hpoaCultureInfo
 * @param staffId 添加者ID
 * @return
 * @throws BaseException 
 */
public String addSaveCulture(HpoaCultureInfo hpoaCultureInfo, String staffId) throws BaseException;
 
/**
 * easyui 查询企业文化列表
 * @param hpoaCultureInfo
 * @param page 当前页
 * @param rows 行数
 * @param from 起始行
 * @param length 长度
 * @return
 * @throws BaseException 
 */
public CultureInfoVo searchCultureList(HpoaCultureInfo hpoaCultureInfo,
		String page, String rows, int from, int length,String sort,String order) throws BaseException;
 
/**
 * 根据文化id查询文化详细信息
 * @param cultureid 文化id
 * @return
 * @throws BaseException 
 */
public HpoaCultureInfo searchCultureContent(String cultureid) throws BaseException;
 
/**
 * 保存修改的企业文化信息
 * @param hpoaCultureInfo
 * @param staffId 修改人id
 * @return
 * @throws BaseException 
 */
public String modifyCultureInfo(HpoaCultureInfo hpoaCultureInfo, String staffId) throws BaseException;
 
/**
 * 根据文化id删除企业文化信息
 * @param cultureid文化id
 * @return
 * @throws BaseException 
 */
public String deleteCultureInfo(String cultureid) throws BaseException;

}
