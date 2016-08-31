package com.hpkj.system.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.system.entity.SysFuncInfo;
import com.hpkj.system.entity.SysRoleInfo;
import com.hpkj.system.vo.SysRoleInfoVo;

public interface RoleAndFuncService extends IBaseService{
	
	
	/**
	 * 查询角色列表
	 */
	public List searchRoleList() throws BaseException;
	
	/**
	 * 查询权限列表
	 */
	public List searchFuncList() throws BaseException;
	
	
	
	/**
	 * 根据角色ID查询所有权限列表,拥有权限默认勾选
	 * @param roleId 角色id
	 * @return
	 * @throws BaseException
	 */
	public List searchFuncListByRoleId(String roleId) throws BaseException;

	
	/**
	 * 新增权限信息
	 * @param funcName 权限名称
	 * @param funcDesc 权限描述
	 * @param funcUrl 权限地址
	 * @param funcPid 权限父类id
	 * @param isUsed 是否可用
	 * @param isLeaf 子节点
	 * @return
	 * @throws BaseException
	 */
	public String saveFuncInfo(String funcName, String funcDesc,
			String funcUrl, String funcPid, int isUsed, int isLeaf)
			throws BaseException;

	/**
	 * 根据权限ID查询功能信息
	 * @param funcId 权限id
	 * @return
	 * @throws BaseException
	 */
	SysFuncInfo searchFuncById(String funcId) throws BaseException;

	/**
	 * 修改权限信息
	 * @param funcId 权限id
	 * @param funcName 权限名称
	 * @param funcDesc 权限描述
	 * @param funcUrl 权限地址
	 * @param isUsed 是否可用
	 * @param isLeaf 叶子节点
	 * @return
	 * @throws BaseException
	 */
	public String updateFuncInfo(String funcId, String funcName,
			String funcDesc, String funcUrl, int isUsed, int isLeaf) throws BaseException;

	
	/**
	 *  删除权限
	 * @param funcId 权限id
	 * @return
	 * @throws BaseException
	 */
	public String deleteFuncInfo(String funcId) throws BaseException;

	
	
	/**
	 * 分配权限
	 * @param roleId 角色id
	 * @param funcArray 所属角色的权限集合
	 * @return
	 * @throws BaseException
	 */
	public String updateRoleFuncLink(String roleId, String funcArray) throws BaseException;

	
	/**
	 * 根据角色ID查询该角色拥有权限列表
	 * @param roleId 角色id
	 * @return
	 * @throws BaseException
	 */
	List searchFuncByRoleId(String roleId) throws BaseException;

	
	/**
	 * 跳转到修改角色页面
	 * @param roleId 角色id
	 * @return
	 * @throws BaseException
	 */
	public SysRoleInfo searchRoleInfoById(String roleId) throws BaseException;

	
	/**
	 * 修改角色信息
	 * @param roleId 角色id
	 * @param roleName 角色名称
	 * @param roleDesc 角色描述
	 * @return
	 * @throws BaseException
	 */
	public String modifyRoleInfo(String roleId, String roleName, String roleDesc) throws BaseException;

	
	
	/**
	 * 删除角色
	 * @param roleId 角色id
	 * @return
	 */
	public void deleteRole(String roleId) throws BaseException;

	
	/**
	 * jq查询角色列表
	 * @param sysRoleInfo 角色实体
	 * @param page 当前页
	 * @param rows 每页显示数据长度
	 * @param from 分页开始数据
	 * @param length 每页显示数据长度
	 * @return
	 * @throws BaseException
	 */
	public SysRoleInfoVo searchRoleList(SysRoleInfo sysRoleInfo,String page,String rows,int from,int length) throws BaseException;

	
	/**
	 * 新增角色
	 * @param roleName 角色名称
	 * @param roleDesc 角色描述
	 * @return
	 * @throws BaseException
	 */
	public String saveRoleInfo(String roleName, String roleDesc) throws BaseException;

	
	/**
	 * 新增主要权限信息
	 * @param funcName 权限名称
	 * @param funcDesc 权限描述
	 * @param funcUrl 权限地址
	 * @param isUsed 是否可用
	 * @return
	 * @throws BaseException
	 */
	public String saveMainFunc(String funcName, String funcDesc, String funcUrl,
			int isUsed) throws BaseException;
	
	
}
