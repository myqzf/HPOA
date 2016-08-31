package com.hpkj.login.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.login.vo.UserRoleVo;

public interface LoginService extends IBaseService {
	/**
	 * 根据账号检查用户名密码是否正确,结果:1正确,2用户名或密码不正确,3用户名或密码不能为空,4此用户已被冻结,0系统繁忙，请稍后再试
	 * @param userAccount 用户账号
	 * @param userPassword 用户密码
	 * @return 1正确,2用户名或密码不正确,3用户名或密码不能为空,4此用户已被冻结,5用户名和密码相同，需修改密码，0系统繁忙，请稍后再试
	 * @throws BaseException
	 */
	public String checkUser(String userAccount,String userPassWord)throws BaseException;
	/**
	 * 根据账号密码获取一个用户的vo
	 * @param userAccount 账号
	 * @param userPassWord 密码
	 * @return 该用户VO
	 * @throws BaseException
	 */
	public UserInfo getUser(String userAccount,String userPassWord)throws BaseException;
	/**
	 * 根据用户id和旧密码修改密码，返回情况：0:系统繁忙,1:修改成功,2旧密码错误,3旧密码或新密码不能为空
	 * @param userid 用户id
	 * @param oldpwd 旧密码
	 * @param newpwd 新密码
	 * @return 0:系统繁忙,1:修改成功,2旧密码错误,3旧密码或新密码不能为空,4用户id为空
	 * @throws BaseException
	 */
	public String modifyPWD(String userid,String oldpwd,String newpwd)throws BaseException;
	/**
	 * 保存sessionid
	 * @param userid 用户id
	 * @param sessionid 会话id
	 * @return
	 * @throws BaseException
	 */
	public void modifySession(String userid,String sessionid)throws BaseException;
	/**
	 * 检查sessionid是否与库中一致,结果:true一致,false不一致
	 * @param userid 用户id
	 * @param sessionid 会话id
	 * @return true一致,false不一致
	 */
	public boolean checkSession(String userid,String sessionid);
	/**
	 * 根据员工id获取全部角色
	 * @param staffid 员工id
	 * @param from 起始长度
	 * @param length 每页长度
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @return 角色列表
	 * @throws BaseException
	 */
	public List<UserRoleVo> getUserRole(String staffid, int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 根据员工id获取全部角色
	 * @param staffid 员工id
	 * @return 角色列表
	 * @throws BaseException
	 */
	public List<UserRoleVo> getUserRole(String staffid)throws BaseException;
	/**
	 * 检查账号是否可用,返回结果:true可用,false不可用
	 * @param userAccount 账号
	 * @return true可用,false不可用
	 * @throws BaseException
	 */
	public boolean checkUserAccount(String userAccount)throws BaseException;
	/**
	 * 获取一个用户编号
	 * @return 用户编号
	 * @throws BaseException
	 */
	public String getUserNumber()throws BaseException;
	/**
	 * 添加用户,返回值:0系统错误,1添加成功,2用户名已存在,3有空缺项
	 * @param staffid 员工id
	 * @param userAccount 账号
	 * @param userName 用户姓名
	 * @param userPwd 密码
	 * @param roles 权限列表
	 * @return 0系统错误,1添加成功,2用户名已存在,3有空缺项
	 * @throws BaseException
	 */
	public String addUser(String staffid,String userAccount,String userName,String userPwd,String roles)throws BaseException;
	/**
	 * 修改用户,返回值:0系统错误,1修改成功,2用户名已存在,3有空缺项
	 * @param userid 用户id
	 * @param userName 用户姓名
	 * @param userPwd 密码
	 * @param roles 权限列表
	 * @return 0系统错误,1添加成功,2用户名已存在（修改用户名时）,3有空缺项
	 * @throws BaseException
	 */
	public String modifyUser(String userid, String userName,String userPwd,String roles)throws BaseException;
	/**
	 * 根据用户id获取用户的详细称呼，格式为XXX公司XXX部门XXX(职位)XXX(个人姓名)
	 * @param userid 用户id
	 * @return 详细称呼
	 * @throws BaseException
	 */
	public String getUserDetailedCall(String userid)throws BaseException;
}
