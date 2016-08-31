package com.hpkj.orgmanage.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.message.vo.StaffsVo;
import com.hpkj.orgmanage.entity.SysOrgInfo;

public interface OrgmanageService extends IBaseService {
	/**
	 * 根据公司查询全部组织结构
	 * @param compid 公司id
	 * @return 
	 * @throws BaseException
	 */
	public List<SysOrgInfo> getOrgList(Integer compid)throws BaseException;
	/**
	 * 根据公司和部门查询员工列表
	 * @param compid 公司id
	 * @param depid 部门id
	 * @return
	 * @throws BaseException
	 */
	public List<StaffsVo> getStaffByCompDep(String compid, String depid)throws BaseException;
	/**
	 * 新增基本组织
	 * @param orgName 组织名
	 * @param orgAllName 组织全称
	 * @param staffids 员工id（多项）
	 * @param depid 部门id
	 * @param compid 公司id
	 * @return 0系统繁忙,1添加成功,2添加失败,3空缺必要项
	 * @throws BaseException
	 */
	public int saveBaseOrgInfo(String orgName,String orgAllName,String staffids,int depid,int compid)throws BaseException;
	/**
	 * 新增下属组织
	 * @param orgName 组织名
	 * @param orgAllName 组织全称
	 * @param orgpid 上级组织id
	 * @param staffids 员工id(多项)
	 * @param depid 部门id
	 * @param compid 公司id
	 * @return 0系统繁忙,1添加成功,2添加失败,3空缺必要项
	 * @throws BaseException
	 */
	public int saveSubOrgInfo(String orgName,String orgAllName,String orgpid,String staffids,int depid,int compid)throws BaseException;
	/**
	 * 根据组织id获取下属员工列表
	 * @param orgid 组织id 
	 * @return 
	 * @throws BaseException
	 */
	public List<StaffsVo> getStaffByOrg(String orgid)throws BaseException;
	/**
	 * 修改基本组织
	 * @param orgid 组织id
	 * @param orgName 组织名称
	 * @param orgAllName 组织全称
	 * @param staffids 员工id列表
	 * @param depid 部门id
	 * @param compid 公司id
	 * @return 0系统繁忙,1修改成功,2修改失败,3空缺必要项
	 * @throws BaseException
	 */
	public int updateBaseOrgInfo(String orgid, String orgName,String orgAllName,String staffids,int depid,int compid)throws BaseException;
	/**
	 * 修改下属组织
	 * @param orgid 组织id
	 * @param orgName 组织名称
	 * @param orgAllName 组织全称
	 * @param orgpid 上级组织id
	 * @param staffids 员工id列表
	 * @param depid 部门id
	 * @param compid 公司id
	 * @return 0系统繁忙,1修改成功,2修改失败,3空缺必要项
	 * @throws BaseException
	 */
	public int updateSubOrgInfo(String orgid, String orgName,String orgAllName,String orgpid,String staffids,int depid,int compid)throws BaseException;
	/**
	 * 删除组织
	 * @param orgid 组织id
	 * @return 0系统繁忙,1删除成功,2删除失败,3id为空
	 * @throws BaseException
	 */
	public int deleteOrg(String orgid)throws BaseException;
}
