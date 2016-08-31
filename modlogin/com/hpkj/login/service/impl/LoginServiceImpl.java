package com.hpkj.login.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpkj.core.util.EncryptUtilz;
import com.hpkj.core.util.MenuJsonCreat;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.login.dao.impl.LoginDaoImpl;
import com.hpkj.login.entity.SysUserInfo;
import com.hpkj.login.entity.SysUserRoleLink;
import com.hpkj.login.service.LoginService;
import com.hpkj.login.vo.FunPInfo;
import com.hpkj.login.vo.FunSInfo;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.login.vo.UserRoleVo;
import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.system.entity.SysFuncInfo;

@Service("loginService")
@Transactional
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
	
	@Resource
	private LoginDaoImpl loginDao;
	
	/*
	 * 根据账号检查用户名密码是否正确,结果:1正确,2用户名或密码不正确,3用户名或密码不能为空,4此用户已被冻结,0系统繁忙，请稍后再试
	 * @see com.hpkj.login.service.LoginService#checkUser(java.lang.String, java.lang.String)
	 */
	@Override
	public String checkUser(String userAccount,String userPassWord)throws BaseException{
		String flag="0";
		try{
			if(userAccount==null || userPassWord==null || "".equals(userAccount)|| "".equals(userPassWord)){
				return "3";
			}
			String sql = "select t.user_state from SYS_USER_INFO t where t.user_account = ? and t.user_pad = ?";
			
			String userstate = loginDao.queryForString(sql, userAccount, EncryptUtilz.MD5(userPassWord));
			if("1".equals(userstate)){
				flag="1";
			}else if("2".equals(userstate)){
				flag="4";
			}else if(userPassWord.equals(userAccount)){
				flag="5";
			}else{
				flag="2";
			}
		}catch(Exception e){
			throw new BaseException("根据账号检查用户名密码是否正确时出错",e);
		}
		return flag;
	}
	/*
	 * 根据账号密码获取一个用户的vo
	 * @see com.hpkj.login.service.LoginService#getUser(java.lang.String, java.lang.String)
	 */
	@Override
	public UserInfo getUser(String userAccount,String userPassWord)throws BaseException{
		try{
			if(userAccount==null || userPassWord==null || "".equals(userAccount)|| "".equals(userPassWord)){
				return null;
			}
			UserInfo uif=new UserInfo();
			String userhql="from SysUserInfo where userAccount=? and userPad=?";
			String compsql="select hsi.staff_comp from hpoa_staff_info hsi where hsi.staff_userid=?";
			String getStaffidsql="select t.staff_id from HPOA_STAFF_INFO t where t.staff_userid=?";
			//根据用户名密码获取用户列表
			List<SysUserInfo> list=loginDao.getHqlList(userhql, userAccount, EncryptUtilz.MD5(userPassWord));
			uif.setUserID(list.get(0).getUserId());
			//获取员工id
			uif.setStaffID(loginDao.queryForString(getStaffidsql, uif.getUserID()));
			//获取公司id
			uif.setStaffComp(Integer.parseInt(loginDao.queryForString(compsql, uif.getUserID())));
			uif.setUserName(list.get(0).getUserName());
			/*String sfisql="select distinct sfi.func_id,sfi.func_name,sfi.func_desc,sfi.func_url,sfi.func_pid,sfi.is_used,sfi.is_leaf,sfi.bak1,sfi.bak2,sfi.bak3 " +
					"from SYS_FUNC_INFO sfi join SYS_FUNC_ROLE sfr on sfi.func_id=sfr.func_id join  SYS_ROLE_INFO sri on sri.role_id=sfr.role_id " +
					"join SYS_USER_ROLE_LINK surl on surl.role_id=sfr.role_id join  SYS_USER_INFO sui on sui.user_id=surl.user_id " +
					"where sfi.is_used=1 and sui.user_id=?";
			//获取功能权限列表
			List<SysFuncInfo> lsfi=loginDao.getSqlList(sfisql, SysFuncInfo.class, uif.getUserID());
			uif.setFuncList(lsfi);*/
			//lfpi为权限的主菜单项,lfsi为权限的子菜单项
			List<FunPInfo> lfpi=new ArrayList();
			List<FunSInfo> lfsi=new ArrayList();
			String fpisql="select distinct sfi.func_id as funid,sfi.func_name as funname from SYS_FUNC_INFO sfi " +
					"join SYS_FUNC_ROLE sfr on sfi.func_id=sfr.func_id join  SYS_ROLE_INFO sri on sri.role_id=sfr.role_id " +
					"join SYS_USER_ROLE_LINK surl on surl.role_id=sfr.role_id join  SYS_USER_INFO sui on sui.user_id=surl.user_id " +
					"where sfi.is_used=1 and sfi.func_pid is null and sui.user_id=?";
			String fsisql="select distinct sfi.func_id as funid,sfi.func_name as funname,sfi.func_url as funurl,sfi.func_pid as funpid from SYS_FUNC_INFO sfi " +
					"join SYS_FUNC_ROLE sfr on sfi.func_id=sfr.func_id join  SYS_ROLE_INFO sri on sri.role_id=sfr.role_id " +
					"join SYS_USER_ROLE_LINK surl on surl.role_id=sfr.role_id join  SYS_USER_INFO sui on sui.user_id=surl.user_id " +
					"where sfi.is_used=1 and sfi.func_pid is not null and sui.user_id=?";
			lfpi=loginDao.getVOSQL(fpisql, FunPInfo.class, uif.getUserID());
			lfsi=loginDao.getVOSQL(fsisql, FunSInfo.class, uif.getUserID());
			uif.setFuncStr(MenuJsonCreat.doJson(lfpi, lfsi));
			return uif;
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException("根据账号密码获取一个用户的vo时出错",e);
		}
	}
	/*
	 * 根据用户id和旧密码修改密码
	 * @see com.hpkj.login.service.LoginService#modifyPWD(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String modifyPWD(String userid,String oldpwd,String newpwd)throws BaseException{
		try{
			if(oldpwd==null || newpwd==null || "".equals(oldpwd)||"".equals(newpwd)){
				return "3";
			}
			String flag="0";
			String checksql="select t.user_id from sys_user_info t where t.user_id=? and t.user_pad=?";
			List li=new ArrayList();
			li=loginDao.getSqlList(checksql, userid, EncryptUtilz.MD5(oldpwd));
			if(li.size()==0){
				flag="2";
			}else{
				String modifysql="update sys_user_info t set t.user_pad=? where t.user_id=?";
				loginDao.excuteSql(modifysql, EncryptUtilz.MD5(newpwd), userid);
				loginDao.sqlcommit();
				flag="1";
			}
			return flag;
		}catch(Exception e){
			throw new BaseException("根据用户id和旧密码修改密码时出错",e);
		}
	}
	/*
	 * 保存sessionid
	 * @see com.hpkj.login.service.LoginService#modifySessionID(java.lang.String, java.lang.String)
	 */
	@Override
	public void modifySession(String userid,String sessionid)throws BaseException{
		try{
			if(userid==null||sessionid==null||userid.isEmpty()||sessionid.isEmpty()){
				return;
			}
			String modifysql="update sys_user_info t set t.bak1=? where t.user_id=?";
			loginDao.excuteSql(modifysql, sessionid, userid);
			loginDao.sqlcommit();
		}catch(Exception e){
			throw new BaseException("保存sessionid",e);
		}
	}
	/*
	 * 检查sessionid是否与库中一致,结果:true一致,false不一致
	 * @see com.hpkj.login.service.LoginService#checkSession(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkSession(String userid,String sessionid){
		boolean flag=false;
		try{
			if(userid==null||sessionid==null||userid.isEmpty()||sessionid.isEmpty()){
				return flag;
			}
			String checksql="select t.user_id from sys_user_info t where t.user_id=? and t.bak1=?";
			List li=loginDao.getSqlList(checksql, userid, sessionid);
			if(li.size()>0){
				flag=true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	/*
	 * 根据员工id获取全部角色
	 * @see com.hpkj.login.service.LoginService#getUserRole(java.lang.String)
	 */
	@Override
	public List<UserRoleVo> getUserRole(String staffid, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(staffid==null||staffid.isEmpty()){
				return null;
			}
			HpoaStaffInfo tmphsi=new HpoaStaffInfo();
			tmphsi=loginDao.getModelById(HpoaStaffInfo.class, staffid);
			String sql="";
			if(sidx==null||sord==null||sidx.isEmpty()){
				sql="select sri.role_id as roleId, sri.role_name as roleName, sri.role_desc as roleDesc, '' as isCheck " +
						"from SYS_ROLE_INFO sri ";
			}else{
				sql="select sri.role_id as roleId, sri.role_name as roleName, sri.role_desc as roleDesc, '' as isCheck " +
						"from SYS_ROLE_INFO sri order by "+sidx+" "+sord;
			}
			List<UserRoleVo> lurv=new ArrayList();
			//查询所有角色
			if (length > 0) {
				lurv = loginDao.getVOSQL(sql, UserRoleVo.class, from, length);
			} else {
				lurv = loginDao.getVOSQL(sql, UserRoleVo.class);
			}
			String staffsql;
			//如果该员工已有账号，查询该员工已有的角色，并替换默认角色列表中内容
			if(tmphsi.getStaffUserid()!=null && length>0){
				if(sidx==null||sord==null||sidx.isEmpty()){
					staffsql="select sri.role_id as roleId, sri.role_name as roleName, sri.role_desc as roleDesc, " +
							"(case when surl.user_id is null then ''else '1' end) as isCheck " +
							"from SYS_ROLE_INFO sri left join SYS_USER_ROLE_LINK surl on sri.role_id = surl.role_id " +
							"left join hpoa_staff_info hsi on surl.user_id=hsi.staff_userid " +
							"where hsi.staff_id=? ";
				}else{
					staffsql="select sri.role_id as roleId, sri.role_name as roleName, sri.role_desc as roleDesc, " +
							"(case when surl.user_id is null then ''else '1' end) as isCheck " +
							"from SYS_ROLE_INFO sri left join SYS_USER_ROLE_LINK surl on sri.role_id = surl.role_id " +
							"left join hpoa_staff_info hsi on surl.user_id=hsi.staff_userid " +
							"where hsi.staff_id=? order by "+sidx+" "+sord;
				}
				List<UserRoleVo> stalurv=new ArrayList();
				stalurv=loginDao.getVOSQL(staffsql, UserRoleVo.class, staffid);
				for(int i=0;i<lurv.size();i++){
					for(int j=0;j<stalurv.size();j++){
						if(lurv.get(i).getRoleId().equals(stalurv.get(j).getRoleId())){
							lurv.set(i, stalurv.get(j));
						}
					}
				}
			}
			return lurv;
		}catch(Exception e){
			throw new BaseException("根据员工id获取全部角色时出错",e);
		}
	}
	/*
	 * 根据员工id获取全部角色
	 * @see com.hpkj.login.service.LoginService#getUserRole(java.lang.String)
	 */
	@Override
	public List<UserRoleVo> getUserRole(String staffid)throws BaseException{
		try{
			if(staffid==null||staffid.isEmpty()){
				return null;
			}
			HpoaStaffInfo tmphsi=new HpoaStaffInfo();
			tmphsi=loginDao.getModelById(HpoaStaffInfo.class, staffid);
			String sql="select sri.role_id as roleId, sri.role_name as roleName, sri.role_desc as roleDesc, '' as isCheck " +
					"from SYS_ROLE_INFO sri ";
			List<UserRoleVo> lurv=new ArrayList();
			//查询所有角色
			lurv = loginDao.getVOSQL(sql, UserRoleVo.class);
			String staffsql;
			//如果该员工已有账号，查询该员工已有的角色，并替换默认角色列表中内容
			if(tmphsi.getStaffUserid()!=null){
				staffsql="select sri.role_id as roleId, sri.role_name as roleName, sri.role_desc as roleDesc, " +
						"(case when surl.user_id is null then ''else '1' end) as isCheck " +
						"from SYS_ROLE_INFO sri left join SYS_USER_ROLE_LINK surl on sri.role_id = surl.role_id " +
						"left join hpoa_staff_info hsi on surl.user_id=hsi.staff_userid " +
						"where hsi.staff_id=? ";
				List<UserRoleVo> stalurv=new ArrayList();
				stalurv=loginDao.getVOSQL(staffsql, UserRoleVo.class, staffid);
				for(int i=0;i<lurv.size();i++){
					for(int j=0;j<stalurv.size();j++){
						if(lurv.get(i).getRoleId().equals(stalurv.get(j).getRoleId())){
							lurv.set(i, stalurv.get(j));
						}
					}
				}
			}
			return lurv;
		}catch(Exception e){
			throw new BaseException("根据员工id获取全部角色时出错",e);
		}
	}
	/*
	 * 检查账号是否可用
	 * @see com.hpkj.login.service.LoginService#checkUserAccount(java.lang.String)
	 */
	@Override
	public boolean checkUserAccount(String userAccount)throws BaseException{
		try{
			boolean flag=false;
			if(userAccount==null||"".equals(userAccount)){
				return flag;
			}
			String sql="select t.user_id from SYS_USER_INFO t where t.user_account=?";
			List li=loginDao.getSqlList(sql, userAccount);
			if(li.size()==0){
				flag=true;
			}
			return flag;
		}catch(Exception e){
			throw new BaseException("检查账号是否可用时出错",e);
		}
	}
	/*
	 * 获取一个用户编号
	 * @see com.hpkj.login.service.LoginService#getUserNumber()
	 */
	@Override
	public String getUserNumber()throws BaseException{
		try{
			String userNumber="";
			String sql="select max(t.user_number) from sys_user_info t";
			userNumber=String.valueOf(Long.valueOf(loginDao.queryForString(sql))+1);
			return userNumber;
		}catch(Exception e){
			throw new BaseException("产生新用户编号时出错",e);
		}
	}
	/*
	 * 添加用户用户,返回值:0系统错误,1修改成功,2用户名已存在,3除密码外有空缺项
	 * @see com.hpkj.login.service.LoginService#addUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addUser(String staffid,String userAccount,String userName,String userPwd,String roles)throws BaseException{
		try{
			String flag="0";
			if(staffid!=null && !"".equals(staffid)){
				if("".equals(userAccount)||"".equals(userName)||"".equals(roles)){
					return "3";
				}
				if(!checkUserAccount(userAccount)){
					return "2";
				}
				//保存用户基本信息
				SysUserInfo sui=new SysUserInfo();
				sui.setUserAccount(userAccount);
				sui.setUserName(userName);
				sui.setUserState(1);
				sui.setUserNumber(getUserNumber());
				sui.setUserPad(EncryptUtilz.MD5(userPwd));
				loginDao.saveModel(sui);
				loginDao.sqlcommit();
				String sql="update hpoa_staff_info hsi set hsi.staff_userid=? where hsi.staff_id=?";
				String userid=sui.getUserId();
				loginDao.excuteSql(sql, userid, staffid);
				String[] rolearr=roles.split(",");
				for(int i=0;i<rolearr.length;i++){
					SysUserRoleLink surl=new SysUserRoleLink();
					surl.setRoleId(rolearr[i]);
					surl.setUserId(userid);
					loginDao.saveModel(surl);
				}
				loginDao.sqlcommit();
				flag="1";
			}else{
				flag="3";
			}
			return flag;
		}catch(Exception e){
			throw new BaseException("添加用户时发生错误",e);
		}
	}
	/*
	 * 修改用户,返回值:0系统错误,1修改成功,2用户名已存在,3有空缺项
	 * @see com.hpkj.login.service.LoginService#modifyUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String modifyUser(String userid,String userName,String userPwd,String roles)throws BaseException{
		try{
			String flag="0";
			if(! "".equals(userid) && userid != null){
				if("".equals(userName)||"".equals(roles)){
					return "3";
				}
				SysUserInfo sui=loginDao.getModelById(SysUserInfo.class, userid);
				String updatesql="";
				if(sui.getUserPad().equals(userPwd)){
					updatesql="update sys_user_info sui set sui.user_name=? where sui.user_id=?";
					loginDao.excuteSql(updatesql, userName, userid);
				}else{
					updatesql="update sys_user_info sui set sui.user_pad=?,sui.user_name=? where sui.user_id=?";
					loginDao.excuteSql(updatesql, EncryptUtilz.MD5(userPwd), userName, userid);
				}
				String delSql="delete sys_user_role_link surl where surl.user_id=?";
				loginDao.excuteSql(delSql, userid);
				loginDao.sqlcommit();
				String[] rolearr=roles.split(",");
				for(int i=0;i<rolearr.length;i++){
					SysUserRoleLink surl=new SysUserRoleLink();
					surl.setRoleId(rolearr[i]);
					surl.setUserId(userid);
					loginDao.saveModel(surl);
				}
				loginDao.sqlcommit();
				flag="1";
			}else{
				flag="3";
			}
			return flag;
		}catch(Exception e){
			throw new BaseException("修改用户时发生错误",e);
		}
	}
	/*
	 * 根据用户id获取用户的详细称呼，格式为XXX公司XXX部门XXX(职位)XXX(个人姓名)
	 * @see com.hpkj.login.service.LoginService#getUserDetailedCall(java.lang.String)
	 */
	@Override
	public String getUserDetailedCall(String userid)throws BaseException{
		try{
			if(userid==null||userid.isEmpty()){
				return null;
			}
			String userDetailedCall="";
			String sql="select sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| sui.user_name " +
					"from hpoa_staff_info hsi join sys_user_info sui on hsi.staff_userid=sui.user_id " +
					"join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
					"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
					"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id where hsi.staff_userid=?";
			userDetailedCall=loginDao.queryForString(sql, userid);
			return userDetailedCall;
		}catch(Exception e){
			throw new BaseException("根据用户id获取用户的详细称呼时出错",e);
		}
	}
}

		
