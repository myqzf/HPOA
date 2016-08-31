package com.hpkj.core.service;

import java.io.Serializable;
import java.util.List;

import com.hpkj.core.exception.BaseException;

/**
 * ****************************************************
 * 
 * @ClassName IBaseService
 * @Description TODO(描述类的作用)
 * @author g
 * @date 20150506
 * 
 *  ****************************************************
 */

public interface IBaseService {
	/**
	 * 
	 * @Title: addObj
	 * @Description: TODO(保存数据实体)
	 * @param model
	 *            操作的数据实体
	 * @return void 返回空
	 * @throws BaseException
	 * 
	 */
	public <T> void addObj(T model) throws BaseException;

	/**
	 * 
	 * @Title: modifyObj
	 * @Description: TODO(更新数据实体)
	 * @param model
	 *            操作的数据实体
	 * @return void 返回空
	 * @throws BaseException
	 * 
	 */
	public <T> void modifyObj(T model) throws BaseException;

	/**
	 * 
	 * @Title: delObj
	 * @Description: TODO(删除数据实体)
	 * @param model
	 *            操作的数据实体
	 * @return void 返回空
	 * @throws BaseException
	 * 
	 */
	public <T> void delObj(T model) throws BaseException;

	/**
	 * 
	 * @Title: delObjById
	 * @Description: TODO(根据ID删除对象)
	 * @param classz
	 *            对象类
	 * @param id
	 *            主键ID
	 * @return void 返回空
	 * @throws BaseException
	 * 
	 */
	public <T> void delObjById(Class classz, Serializable id)
			throws BaseException;

	/**
	 * 
	 * @Title: getObjById
	 * @Description: TODO(根据ID获取对象对象)
	 * @param classz
	 *            对象类
	 * @param id
	 *            主键ID
	 * @return void 返回空
	 * @throws BaseException
	 * 
	 */
	public <T> T getObjById(Class classz, Serializable id) throws BaseException;

	/**
	 * 
	 * @Title: excuteHql
	 * @Description: TODO(执行hql语句)
	 * @param hql
	 * @return void 返回空
	 * @throws BaseException
	 * 
	 */
	public void excuteHql(String hql, Object... args) throws BaseException;

	/**
	 * 
	 * @Title: excuteSql
	 * @Description: TODO(执行sql语句)
	 * @param sql
	 * @return void 返回空
	 * @throws BaseException
	 * 
	 */
	public void excuteSql(String sql, Object... args) throws BaseException;
	
	/**
	 * 
	 * @param hql
	 * @param args
	 * @return
	 * @throws BaseException
	 */
	public <T> List<T> getHqlList(String hql, Object... args)
			throws BaseException;

	public <T> List<T> getHqlList(String hql, int startRow, int pageSize,
			Object... args) throws BaseException;

	public List getSqlList(String sql, Object... args) throws BaseException;

	public List getSqlList(String sql, Class cls, Object... args)
			throws BaseException;

	public List getSqlList(String sql, Class cls, int startRow, int pageSize,
			Object... args) throws BaseException;

	public List getSqlList(String sql, int startRow, int pageSize,
			Object... args) throws BaseException;

	public <T> List<T> getVOSQL(String sqlText, Class clazz, Object... args)
			throws BaseException;

	
	public <T> List<T> getVOSQL(String sqlText, Class clazz, int startRow,
			int pageSize, Object... args) throws BaseException;

	/**
	 *获取字段的字符串内容
	 * @param sql
	 * @param args
	 * @return
	 */
	public String queryForString(String sql, Object... args);
	
}
