package com.hpkj.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;


import com.hpkj.system.dao.RoleAndFuncDao;
import com.hpkj.system.entity.SysFuncInfo;
import com.hpkj.system.entity.SysFuncRole;
import com.hpkj.system.entity.SysRoleInfo;
import com.hpkj.system.service.RoleAndFuncService;
import com.hpkj.system.vo.SysRoleInfoVo;

@Service("roleAndFuncService")
@Transactional
public class RoleAndFuncServiceImpl extends BaseServiceImpl implements RoleAndFuncService{

	@Resource
	private RoleAndFuncDao roleAndFuncDao;	
	
		
	
	/*
	 * jq查询角色列表
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#searchRoleList(com.hpkj.system.entity.SysRoleInfo, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public SysRoleInfoVo searchRoleList(SysRoleInfo sysRoleInfo,String page,String rows,int from,int length) throws BaseException {
		
		SysRoleInfoVo sysRoleInfoVo = new SysRoleInfoVo();		
		try {
		//当前页数据
		String hql = " from SysRoleInfo s where 1 = 1 ";
		
		if (!"".equals(sysRoleInfo.getRoleName())
				&& sysRoleInfo.getRoleName() != null) {
			hql = hql + " and s.roleName like '%"+ sysRoleInfo.getRoleName() + "%'";
		}
		
		if (!"".equals(sysRoleInfo.getRoleDesc())
				&& sysRoleInfo.getRoleDesc() != null) {
			hql = hql + " and s.roleDesc like '%"+ sysRoleInfo.getRoleDesc() + "%'";
		}
		
		List list = roleAndFuncDao.getHqlList(hql,from,length);
		

		
		
		//总数据
		List allList = roleAndFuncDao.getHqlList(" from SysRoleInfo ");
		
		sysRoleInfoVo.setGridDTOs(list);
		sysRoleInfoVo.setPage(Integer.parseInt(page));
		sysRoleInfoVo.setRecords(allList.size());
		
		int total = (allList.size()%Integer.parseInt(rows)==0) ? (allList.size()/Integer.parseInt(rows)) : (allList.size()/Integer.parseInt(rows)+1);
				
		sysRoleInfoVo.setTotal(total);

		} catch (Exception e) {
			throw new BaseException("jq查询角色列表时出错", e);
		}
		return sysRoleInfoVo;
		
	}
	
	
	
	
	
	
	
	/*
	 * 查询角色列表
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#searchRoleList()
	 */
	@Override
	public List searchRoleList() throws BaseException {
		
		List roleList = new ArrayList();
		
		try {
			String sql = " from SysRoleInfo where 1=1 ";
			
			roleList = roleAndFuncDao.getHqlList(sql);
			
			
			
		} catch (Exception e) {
			throw new BaseException("查询角色列表时出错", e);
		}
		
		
		return roleList;
		
	}

	
	/*
	 * 分配权限页面，根据角色ID查询所有权限列表,拥有权限默认勾选
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#searchFuncListByRoleId(java.lang.String)
	 */
	@Override
	public List searchFuncListByRoleId(String roleId) throws BaseException {
		
		List list = new ArrayList();
		
		try {
			if(StringUtils.isNotBlank(roleId)){					
				list = roleAndFuncDao.getSqlList("select * from SYS_FUNC_INFO where IS_USED=1 order by to_number(bak1)",SysFuncInfo.class);
					//查询角色权限列表
					List ownList = roleAndFuncDao.getHqlList("from SysFuncRole where roleId = ?", roleId);

					if(ownList!=null && ownList.size()>0){
						//遍历角色拥有的权限
						for(int i=0;i<ownList.size();i++){							
							SysFuncRole sfr = (SysFuncRole)ownList.get(i);
							//根据角色拥有的权限查询权限实体
							SysFuncInfo sysFuncInfo = (SysFuncInfo) roleAndFuncDao.getSession().get(SysFuncInfo.class, sfr.getFuncId());	
							//如果根据SysFuncRole表中关联funcId查询sysFuncInfo不为空，则设置ztree默认勾选改节点
							if(sysFuncInfo!=null){
							sysFuncInfo.setChecked("true");	
							}
						}
					}
				}

		} catch (Exception e) {
			//log.error("根据角色ID查询权限列表错误",e);
			throw new BaseException("根据角色ID查询权限列表错误",e);
		}
		return list;
	}

	/*
	 * 查询权限列表
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#searchFuncList()
	 */
	@Override
	public List searchFuncList() throws BaseException {
		List funcList = new ArrayList();
		
		try {			
			funcList = roleAndFuncDao.getHqlList(" from SysFuncInfo ");
			
		} catch (Exception e) {
			throw new BaseException("查询权限列表时错误",e);
		}		
		return funcList;
	}
	
	
	
		
	/*
	 * 新增主要权限
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#saveMainFunc(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public String saveMainFunc(String funcName, String funcDesc,
			String funcUrl, int isUsed)
			throws BaseException {
		String rtn = "2";
		try {
			Assert.notNull(funcName,"功能名称为空！");
			SysFuncInfo sfi = new SysFuncInfo();
			sfi.setFuncDesc(funcDesc);
			sfi.setFuncName(funcName);
			sfi.setFuncUrl(funcUrl);
			sfi.setIsLeaf(2);
			sfi.setIsUsed(isUsed);
			//手动获取id(最大id+1)
			String idsql="select max(to_number(sfi.func_id)) from sys_func_info sfi";
			sfi.setFuncId(String.valueOf(Integer.valueOf(roleAndFuncDao.queryForString(idsql))+1));
			//获取最大bak1的值，并加一，排序用
			String sql = "select max(to_number(bak1)) from SYS_FUNC_INFO";
			List list = roleAndFuncDao.getSqlList(sql);
			if(list!=null && list.size()>0){
				int a = Integer.parseInt(list.get(0).toString())+1;
				sfi.setBak1(a+"");
			}
			roleAndFuncDao.saveModel(sfi);
			roleAndFuncDao.sqlcommit();
			rtn = "1";
		} catch (Exception e) {
			
			throw new BaseException("新增功能信息错误",e);
		}
		return rtn;
	}
	
	
	
	
	
	
	
	
	/*
	 * 新增权限信息
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#saveFuncInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public String saveFuncInfo(String funcName, String funcDesc,
			String funcUrl, String funcPid, int isUsed, int isLeaf)
			throws BaseException {
		String rtn = "2";
		try {
			Assert.notNull(funcName,"功能名称为空！");
			Assert.notNull(funcPid,"功能父ID为空！");
			SysFuncInfo sfi = new SysFuncInfo();
			sfi.setFuncDesc(funcDesc);
			sfi.setFuncName(funcName);
			sfi.setFuncPid(funcPid);
			sfi.setFuncUrl(funcUrl);
			sfi.setIsLeaf(isLeaf);
			sfi.setIsUsed(isUsed);
			//手动获取id(最大id+1)
			String idsql="select max(to_number(sfi.func_id)) from sys_func_info sfi";
			sfi.setFuncId(String.valueOf(Integer.valueOf(roleAndFuncDao.queryForString(idsql))+1));
			//获取最大bak1的值，并加一，排序用
			String sql = "select max(to_number(BAK1)) from SYS_FUNC_INFO";
			String bak1 = roleAndFuncDao.queryForString(sql);
			if(bak1.isEmpty()){
				sfi.setBak1("1");
			}else{
				int a = Integer.parseInt(bak1)+1;
				sfi.setBak1(a+"");
			}

			roleAndFuncDao.saveModel(sfi);
			roleAndFuncDao.sqlcommit();
			rtn = "1";
		} catch (Exception e) {
			
			throw new BaseException("新增功能信息错误",e);
		}
		return rtn;
	}
	
	
	
	/*
	 * 根据权限ID查询功能信息
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#searchFuncById(java.lang.String)
	 */
	@Override
	public SysFuncInfo searchFuncById(String funcId) throws BaseException {
		SysFuncInfo sri = new SysFuncInfo();
		try {
			Assert.notNull(funcId,"功能ID为空！");
			sri = roleAndFuncDao.getModelById(SysFuncInfo.class, funcId);
		} catch (Exception e) {
			
			throw new BaseException("根据功能ID查询功能信息错误",e);
		}
		return sri;
	}

	
		
	/*
	 * 查询角色所拥有的权限
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#searchFuncByRoleId(java.lang.String)
	 */
	@Override
	public List searchFuncByRoleId(String roleId) throws BaseException {
		
		List<SysFuncInfo> list = new ArrayList<SysFuncInfo>();
		
		try {
			if(StringUtils.isNotBlank(roleId)){				
				//查询角色权限列表
					List ownList = roleAndFuncDao.getHqlList("from SysFuncRole where roleId = ?", roleId);

					if(ownList!=null && ownList.size()>0){
						//遍历角色拥有的权限
						for(int i=0;i<ownList.size();i++){							
							SysFuncRole sfr = (SysFuncRole)ownList.get(i);
							//根据角色拥有的权限查询权限实体
							SysFuncInfo sysFuncInfo = new SysFuncInfo();
							
							//如果角色权限关联表中funcId不为空，就查询该角色对应SysFuncInfo实体
							if(sfr.getFuncId()!=null){
								sysFuncInfo = (SysFuncInfo) roleAndFuncDao.getSession().get(SysFuncInfo.class, sfr.getFuncId());
															
								if(sysFuncInfo!=null){								
									list.add(sysFuncInfo);								
								}						
							
							}else{
								return null;
							}
						}
					}
				}

		} catch (Exception e) {
			//log.error("根据角色ID查询权限列表错误",e);
			throw new BaseException("根据角色ID查询权限列表错误",e);
		}
		return list;
	}
	
	
	
	
	
	

	/*
	 * 修改权限信息
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#updateFuncInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public String updateFuncInfo(String funcId, String funcName,
			String funcDesc, String funcUrl, int isUsed,
			int isLeaf) throws BaseException {
		
		String rtn = "2";
		try {
			Assert.notNull(funcId,"功能ID为空！");
			Assert.notNull(funcName,"功能名称为空！");
			//根据ID查询功能信息
			SysFuncInfo sri = roleAndFuncDao.getModelById(SysFuncInfo.class, funcId);
			
			if(sri!=null && StringUtils.isNotBlank(sri.getFuncId())){
				sri.setFuncDesc(funcDesc);
				sri.setFuncName(funcName);
				sri.setFuncUrl(funcUrl);
				sri.setIsLeaf(isLeaf);
				sri.setIsUsed(isUsed);
				roleAndFuncDao.updateModel(sri);
				roleAndFuncDao.sqlcommit();
			}
			rtn = "1";
		} catch (Exception e) {
			
			throw new BaseException("修改功能信息错误",e);
		}
		return rtn;
	}


	/*
	 * 删除权限
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#deleteFuncInfo(java.lang.String)
	 */
	@Override
	public String deleteFuncInfo(String funcId) throws BaseException {
		String rtn = "2";
		try {
			Assert.notNull(funcId,"功能ID为空！");
			roleAndFuncDao.deleteModelById(SysFuncInfo.class, funcId);
			//删除功能角色关联表中的功能ID
			String sql = "delete SYS_FUNC_INFO t where t.FUNC_ID=?";
			roleAndFuncDao.excuteSql(sql, funcId);
			rtn = "1";
		}
		catch (Exception e) {
			
			throw new BaseException("删除功能信息错误",e);
		}
		return rtn;
	}



	
	/*
	 * 修改角色权限  1先删除所有旧角色权限数据，2将新角色权限数据添加，完成更新权限操作
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#updateRoleFuncLink(java.lang.String, java.lang.String)
	 */
	@Override
	public String updateRoleFuncLink(String roleId, String funcArray)throws BaseException {
		String rtn = "2";
		try {
			//如果数据为空，返回失败
			if(StringUtils.isBlank(roleId) || StringUtils.isBlank(funcArray) || funcArray.length()<1){
				return rtn;
			}
			//删除旧数据
			
			String sql = "delete SYS_FUNC_ROLE t where t.ROLE_ID = ?";
			roleAndFuncDao.excuteSql(sql, roleId);
			
			//添加新数据
			String[] fa = funcArray.split(",");
			if(fa.length>0){
				for(int i=0;i<fa.length;i++){
					SysFuncRole sfr = new SysFuncRole();
					sfr.setFuncId(fa[i]);
					sfr.setRoleId(roleId);
					roleAndFuncDao.saveModel(sfr);
					roleAndFuncDao.sqlcommit();
				}
			}
			rtn ="1";
		} catch (Exception e) {
			
			throw new BaseException("修改权限错误",e);
		}
		return rtn;
	}
		
	

	/*
	 * 跳转到修改角色页面
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#searchRoleInfoById(java.lang.String)
	 */
	@Override
	public SysRoleInfo searchRoleInfoById(String roleId) throws BaseException {
		SysRoleInfo sri;
		try {
			sri = roleAndFuncDao.getModelById(SysRoleInfo.class, roleId);
		} catch (Exception e) {
			
			throw new BaseException("根据角色ID查询角色信息错误",e);
		}
		return sri;
	}



	
	/*
	 * 修改角色信息
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#modifyRoleInfo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String modifyRoleInfo(String roleId, String roleName, String roleDesc)throws BaseException {
		String rtnFlg = "2";
		try {
			if(StringUtils.isNotBlank(roleName)){
				roleName = roleName.trim();
			}else {
				return "2";
			}
			//根据角色ID获取角色信息
			SysRoleInfo sri = roleAndFuncDao.getModelById(SysRoleInfo.class, roleId);
			if(sri!=null && StringUtils.isNotBlank(sri.getRoleId())){
				String hql = "from SysRoleInfo where roleName = ? and roleId != ?";
				List list = roleAndFuncDao.getHqlList(hql, roleName,roleId);
				if(list!=null && list.size()>0){
					return "3";
				}
				sri.setRoleName(roleName);
				sri.setRoleDesc(roleDesc);
				roleAndFuncDao.updateModel(sri);
				roleAndFuncDao.sqlcommit();
			}
			rtnFlg="1";
		} catch (Exception e) {
			
			throw new BaseException("修改角色信息错误",e);
		}
		return rtnFlg;
	}


	/*
	 * 删除角色
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#deleteRole(java.lang.String)
	 */
	@Override
	public void deleteRole(String roleId) throws BaseException {
		try {
			//先删除用户角色关联表
			String sql = "delete SYS_USER_ROLE_LINK t where t.ROLE_ID = ?";
			roleAndFuncDao.excuteSql(sql, roleId);
			//再删除角色权限关联表
			String sql1 = "delete SYS_FUNC_ROLE t where t.ROLE_ID = ?";
			roleAndFuncDao.excuteSql(sql1, roleId);
			//最后删除角色信息
			roleAndFuncDao.deleteModelById(SysRoleInfo.class, roleId);
			roleAndFuncDao.sqlcommit();
		} catch (Exception e) {
			
			throw new BaseException("删除角色错误",e);
		}
		
	}

	
	/*
	 * 添加角色
	 * (non-Javadoc)
	 * @see com.hpkj.system.service.RoleAndFuncService#saveRoleInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public String saveRoleInfo(String roleName, String roleDesc)throws BaseException {
		try {
			if(StringUtils.isNotBlank(roleName)){
				roleName = roleName.trim();
			}
			String hql = "from SysRoleInfo where roleName = ?";
			List list = roleAndFuncDao.getHqlList(hql, roleName);
			if(list!=null && list.size()>0){
				return "3";
			}
			SysRoleInfo sri = new SysRoleInfo();
			sri.setRoleName(roleName.trim());
			sri.setRoleDesc(roleDesc);
			roleAndFuncDao.saveModel(sri);
			roleAndFuncDao.sqlcommit();
			return "1";
		} catch (Exception e) {
			
			throw new BaseException("添加角色错误",e);
		}
	}
	
}
