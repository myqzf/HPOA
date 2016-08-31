package com.hpkj.staff.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.staff.vo.DictTitleVo;
import com.hpkj.staff.vo.StaffDetailInfoVo;

public interface StaffService extends IBaseService{
	/**
	 * 所有员工信息列表
	 * @param sidx  排序字段
	 * @param sord 排序方式
	 * @param startRow 起始记录
	 * @param pageSize 每页记录数
	 * @return 员工信息列表
	 * @throws BaseException
	 */
	public List getStaffList(String sidx,String sord,int startRow, int pageSize) throws BaseException;
	/**
	 * 根据员工id获取员工的详细信息
	 * @param staffId 员工id
	 * @return 员工实体
	 * @throws BaseException
	 */
	public HpoaStaffInfo getStaffInfo(String staffId)throws BaseException;	
	/**
	 * 根据关键字查询员工信息
	 * @param staff 查询条件
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @param from 起始记录
	 * @param length 每页记录数
	 * @return 员工信息列表
	 * @throws BaseException
	 */
	public List getSearchList(StaffDetailInfoVo staff, String sidx,String sord,int from, int length) throws BaseException;
	/**
	 * 添加员工信息
	 * @param staffInfo 员工实体
	 * @return 0：系统出错,1：添加成功,2：添加失败,3未填写姓名
	 * @throws BaseException
	 */
	public int addStaff(HpoaStaffInfo staffInfo) throws BaseException;
	/**
	 * 根据父项id号（pid）查找字典列表中响应项
	 * @param pid 
	 * @return 字典中符合条件的id和内容列表
	 * @throws BaseException
	 */
	public List<DictTitleVo> getDictList(int pid) throws BaseException;
	/**
	 * 根据员工id删除员工
	 * @param staffId 员工id
	 * @return 0：系统出错,1：删除成功,2：删除失败
	 * @throws BaseException
	 */
	public int delStaff(String staffId) throws BaseException;
	/**
	 * 0：系统出错,1：修改成功,2：修改失败,3员工姓名为空
	 * @param staffInfo
	 * @return 0系统出错，1修改成功，2修改失败，3员工姓名为空	
	 * @throws BaseException
	 */
	public int modifyStaff(HpoaStaffInfo staffInfo) throws BaseException;
	/**
	 * 查看员工详细信息
	 * @param staffId
	 * @return 员工详细信息
	 * @throws BaseException
	 */
	public StaffDetailInfoVo getStaffDetailInfo(String staffId) throws BaseException;
	/**
	 * 根据员工id上传照片
	 * @param staffId
	 * @param photoUrl 图片路径
	 * @return 0：系统出错,1：照片上传成功,2：照片上传失败
	 * @throws BaseException
	 */
	public int modifyPhoto(String staffId, String photoUrl) throws BaseException ;
	/**
	 * 根据员工id删除照片
	 * @param staffId
	 * @return 0：系统出错,1：照片删除成功,2：照片删除失败
	 * @throws BaseException
	 */
	public int delStaffPhoto(String staffId) throws BaseException;

	
}
