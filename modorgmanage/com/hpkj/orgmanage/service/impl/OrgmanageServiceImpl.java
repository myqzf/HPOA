package com.hpkj.orgmanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.StringUtilz;
import com.hpkj.message.vo.StaffsVo;
import com.hpkj.orgmanage.dao.impl.OrgmanageDaoImpl;
import com.hpkj.orgmanage.entity.SysOrgInfo;
import com.hpkj.orgmanage.entity.SysOrgStaffLink;
import com.hpkj.orgmanage.service.OrgmanageService;
import com.hpkj.staff.entity.HpoaStaffInfo;
@Service("orgmanageService")
public class OrgmanageServiceImpl extends BaseServiceImpl implements OrgmanageService {
	@Resource
	private OrgmanageDaoImpl orgmanageDao;
	/*
	 * 根据公司查询全部组织结构
	 * @see com.hpkj.orgmanage.service.OrgmanageService#getOrgList(java.lang.String)
	 */
	public List<SysOrgInfo> getOrgList(Integer compid)throws BaseException{
		try{
			List<SysOrgInfo> lsoi=new ArrayList();
			String hql="from SysOrgInfo where orgCompid=? and bak1='1'";
			lsoi=orgmanageDao.getHqlList(hql, compid);
			return lsoi;
		}catch(Exception e){
			throw new BaseException("根据公司查询全部组织结构时出错",e);
		}
	}
	/*
	 * 根据公司和部门查询员工列表
	 * @see com.hpkj.orgmanage.service.OrgmanageService#getStaffByCompDep(int, int)
	 */
	public List<StaffsVo> getStaffByCompDep(String compid, String depid)throws BaseException{
		try{
			List<StaffsVo> lsv=new ArrayList();
			String sql="select hsi.staff_id as staffId,hsi.staff_name as staffName from hpoa_staff_info hsi where hsi.staff_comp=? and hsi.staff_dept=?";
			lsv=orgmanageDao.getVOSQL(sql, StaffsVo.class, compid, depid);
			return lsv;
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException("根据公司和部门查询员工列表时出错",e);
		}
	}
	/*
	 * 新增基本组织
	 * @see com.hpkj.orgmanage.service.OrgmanageService#saveBaseOrgInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public int saveBaseOrgInfo(String orgName,String orgAllName,String staffids,int depid,int compid)throws BaseException{
		try{
			int flag=0;//0系统繁忙,1添加成功,2添加失败,3空缺必要项
			if(orgName==null||"".equals(orgName)||staffids==null||"".equals(staffids)||depid==0||compid==0){
				flag=3;
				return flag;
			}
			SysOrgInfo soi=new SysOrgInfo();
			soi.setOrgName(orgName);
			soi.setOrgAllName(orgAllName);
			soi.setOrgDepid(depid);
			soi.setOrgCompid(compid);
			soi.setBak1("1");
			orgmanageDao.saveModel(soi);
			orgmanageDao.sqlcommit();
			if(soi.getOrgId()==null||"".equals(soi.getOrgId())){
				flag=2;
				return flag;
			}
			String[] staffid=staffids.split(",");
			String orgid=soi.getOrgId();
			for(int i=0;i<staffid.length;i++){
				SysOrgStaffLink sosl=new SysOrgStaffLink();
				sosl.setOrgId(orgid);
				sosl.setStaffId(staffid[i]);
				orgmanageDao.saveModel(sosl);
			}
			orgmanageDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("新增基本组织时出错",e);
		}
	}
	/*
	 * 新增下属组织
	 * @see com.hpkj.orgmanage.service.OrgmanageService#saveSubOrgInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public int saveSubOrgInfo(String orgName,String orgAllName,String orgpid,String staffids,int depid,int compid)throws BaseException{
		try{
			int flag=0;//0系统繁忙,1添加成功,2添加失败,3空缺必要项
			if(orgName==null||"".equals(orgName)||orgpid==null||"".equals(orgpid)||staffids==null||"".equals(staffids)||depid==0||compid==0){
				flag=3;
				return flag;
			}
			SysOrgInfo soi=new SysOrgInfo();
			soi.setOrgName(orgName);
			soi.setOrgAllName(orgAllName);
			soi.setOrgPid(orgpid);
			soi.setOrgDepid(depid);
			soi.setOrgCompid(compid);
			soi.setBak1("1");
			orgmanageDao.saveModel(soi);
			orgmanageDao.sqlcommit();
			if(soi.getOrgId()==null||"".equals(soi.getOrgId())){
				flag=2;
				return flag;
			}
			String[] staffid=staffids.split(",");
			String orgid=soi.getOrgId();
			for(int i=0;i<staffid.length;i++){
				SysOrgStaffLink sosl=new SysOrgStaffLink();
				sosl.setOrgId(orgid);
				sosl.setStaffId(staffid[i]);
				orgmanageDao.saveModel(sosl);
			}
			orgmanageDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("新增下属组织时出错",e);
		}
	}
	/*
	 * 根据组织id获取下属员工列表
	 * @see com.hpkj.orgmanage.service.OrgmanageService#getStaffByOrg(java.lang.String)
	 */
	public List<StaffsVo> getStaffByOrg(String orgid)throws BaseException{
		try{
			List<StaffsVo> lsv=new ArrayList();
			String sql="select hsi.staff_id as staffId, hsi.staff_name as staffName " +
					"from sys_org_staff_link sosl join hpoa_staff_info hsi on sosl.staff_id=hsi.staff_id " +
					"where sosl.org_id=?";
			lsv=orgmanageDao.getVOSQL(sql, StaffsVo.class, orgid);
			return lsv;
		}catch(Exception e){
			throw new BaseException("根据组织id获取下属员工列表时出错",e);
		}
	}
	/*
	 * 修改基本组织
	 * @see com.hpkj.orgmanage.service.OrgmanageService#updateSubOrgInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public int updateBaseOrgInfo(String orgid, String orgName,String orgAllName,String staffids,int depid,int compid)throws BaseException{
		try{
			int flag=0;//0系统繁忙,1修改成功,2修改失败,3空缺必要项
			if(StringUtilz.isEmpty(orgid)||StringUtilz.isEmpty(orgName)||StringUtilz.isEmpty(orgAllName)||StringUtilz.isEmpty(staffids)||depid==0||compid==0){
				flag=3;
				return flag;
			}
			SysOrgInfo soi=new SysOrgInfo();
			soi.setOrgId(orgid);
			soi.setOrgName(orgName);
			soi.setOrgAllName(orgAllName);
			soi.setOrgDepid(depid);
			soi.setOrgCompid(compid);
			soi.setBak1("1");
			orgmanageDao.updateModel(soi);
			String sql="delete sys_org_staff_link sosl where sosl.org_id=?";
			orgmanageDao.excuteSql(sql, orgid);
			String[] staffid=staffids.split(",");
			for(int i=0;i<staffid.length;i++){
				SysOrgStaffLink sosl=new SysOrgStaffLink();
				sosl.setOrgId(orgid);
				sosl.setStaffId(staffid[i]);
				orgmanageDao.saveModel(sosl);
			}
			orgmanageDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("修改基本组织时出错",e);
		}
	}
	/*
	 * 修改下属组织
	 * @see com.hpkj.orgmanage.service.OrgmanageService#updateSubOrgInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public int updateSubOrgInfo(String orgid, String orgName,String orgAllName,String orgpid,String staffids,int depid,int compid)throws BaseException{
		try{
			int flag=0;//0系统繁忙,1修改成功,2修改失败,3空缺必要项
			if(StringUtilz.isEmpty(orgid)||StringUtilz.isEmpty(orgName)||StringUtilz.isEmpty(orgAllName)||StringUtilz.isEmpty(staffids)||StringUtilz.isEmpty(orgpid)||depid==0||compid==0){
				flag=3;
				return flag;
			}
			SysOrgInfo soi=new SysOrgInfo();
			soi.setOrgId(orgid);
			soi.setOrgName(orgName);
			soi.setOrgAllName(orgAllName);
			soi.setOrgPid(orgpid);
			soi.setOrgDepid(depid);
			soi.setOrgCompid(compid);
			soi.setBak1("1");
			orgmanageDao.updateModel(soi);
			String sql="delete sys_org_staff_link sosl where sosl.org_id=?";
			orgmanageDao.excuteSql(sql, orgid);
			String[] staffid=staffids.split(",");
			for(int i=0;i<staffid.length;i++){
				SysOrgStaffLink sosl=new SysOrgStaffLink();
				sosl.setOrgId(orgid);
				sosl.setStaffId(staffid[i]);
				orgmanageDao.saveModel(sosl);
			}
			orgmanageDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("修改下属组织时出错",e);
		}
	}
	/*
	 * 删除组织
	 * @see com.hpkj.orgmanage.service.OrgmanageService#deleteOrg(java.lang.String)
	 */
	public int deleteOrg(String orgid)throws BaseException{
		try{
			int flag=0;//0系统繁忙,1删除成功,2删除失败,3id为空
			if(StringUtilz.isEmpty(orgid)){
				flag=3;
				return flag;
			}
			String sql="update sys_org_info soi set soi.bak1='2' where soi.org_id=?";
			orgmanageDao.excuteSql(sql, orgid);
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("删除组织时出错",e);
		}
	}
}
