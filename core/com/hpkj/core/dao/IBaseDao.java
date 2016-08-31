package com.hpkj.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.hpkj.core.exception.BaseException;

public interface IBaseDao {
	/**
	 * 
	 * @Title: saveModel
	 * @Description: TODO(保存数据实体)
	 * @param model 操作的数据实体
	 * @return void  返回空
	 * @throws BaseException 
	 *
	 */
	public <T> void saveModel(T model) throws BaseException;

	/**
	 * 
	 * @Title: saveOrUpdateModel 
	 * @Description: TODO(保存或者更新数据实体)
	 * @param model 操作的数据实体
	 * @return void  返回空
	 * @throws BaseException
	 *
	 */
	public <T> void saveOrUpdateModel(T model) throws BaseException;

	/**
	 * 
	 * @Title: updateModel 
	 * @Description: TODO(更新数据实体)
	 * @param model 操作的数据实体
	 * @return void  返回空
	 * @throws BaseException
	 *
	 */
	public <T> void updateModel(T model) throws BaseException;

	/**
	 * 
	 * @Title: mergeModel 
	 * @Description: TODO(合并数据实体)
	 * @param model 操作的数据实体
	 * @return void  返回空
	 * @throws BaseException
	 *
	 */
	public <T> void mergeModel(T model) throws BaseException;

	/**
	 * 
	 * @Title: deleteModel 
	 * @Description: TODO(删除数据实体)
	 * @param model 操作的数据实体
	 * @return void  返回空
	 * @throws BaseException
	 *
	 */
	public <T> void deleteModel(T model) throws BaseException;

	/**
	 * 
	 * @Title: deleteModelById 
	 * @Description: TODO(根据ID删除对象)
	 * @param classz  对象类
	 * @param id      主键ID
	 * @return void   返回空
	 * @throws BaseException 
	 *
	 */
	public <T> void deleteModelById(final Class classz, final Serializable id)
			throws BaseException;

	/**
	 * 
	 * @Title: deleteModelById 
	 * @Description: TODO(根据ID获取对象对象)
	 * @param classz  对象类
	 * @param id      主键ID
	 * @return void   返回空
	 * @throws BaseException 
	 *
	 */
	public <T> T getModelById(final Class classz, final Serializable id)
			throws BaseException;

	/**
	 * 
	 * @Title: getHqlList 
	 * @Description: TODO(获取对象列表)
	 * @param hql  hql
	 * @return List<T> 返回对象list  
	 * @throws BaseException 
	 *
	 */
	public <T> List<T> getHqlList(String hql,Object ... args) throws BaseException;

	/**
	 * 
	 * @Title: getHqlList 
	 * @Description: TODO(获取对象分页列表)
	 * @param hql  hql
	 * @param startRow  开始行
	 * @param pageSize  每页行数
	 * @return List<T>  返回对象list
	 * @throws BaseException 
	 *
	 */
	public <T> List<T> getHqlList(String hql,int startRow, int pageSize,Object ... args)
			throws BaseException;


	/**
	 * 
	 * @Title: getSqlList 
	 * @Description: TODO(获取对象列表)
	 * @param sql  sql
	 * @return List<T> 返回对象list  每条数据对应一个数组
	 * @throws BaseException 
	 *
	 */
	public List getSqlList(String sql,Object ... args) throws BaseException;

	/**
	 * 
	 * @Title: getSqlList 
	 * @Description: TODO(获取对象分页列表)
	 * @param sql  sql
	 * @param startRow  开始行
	 * @param pageSize  每页行数
	 * @return List<T>  返回对象list 每条数据对应一个数组
	 * @throws BaseException 
	 *
	 */
	public List getSqlList(String sql, int startRow, int pageSize,Object ... args)
			throws BaseException;


    /**
     * 
     * @Title: excuteHql 
     * @Description: TODO(执行hql语句)
     * @param hql
     * @return void   返回空
     * @throws BaseException
     *
     */
	public void excuteHql(String hql,Object ... args) throws BaseException;

	/**
     * 
     * @Title: excuteSql 
     * @Description: TODO(执行sql语句)
     * @param sql
     * @return void   返回空
     * @throws BaseException
     *
     */
	public void excuteSql(String sql,Object ... args) throws BaseException;

	/**
	 * 
	 * @Title: getVOSQL 
	 * @Description: TODO(获取对象列表 把数组转成vo对象)
	 * @param sql       sql   
	 * @param clazz     转成的class
	 * @return List<T>  返回对象list 每条数据对应一个vo对象
	 * @throws BaseException List<T>
	 *
	 */
	public <T> List<T> getVOSQL(String sql, Class clazz,Object ... args)
			throws BaseException;

	/**
	 * 
	 * @Title: getVOSQL 
	 * @Description: TODO(获取对象列表 把数组转成vo对象 分页)
	 * @param sql  sql
	 * @param clazz 转成的class
	 * @param startRow  开始行
	 * @param pageSize  每页行数
	 * @return List<T>  返回对象list 每条数据对应一个vo对象
	 * @throws BaseException 
	 *
	 */
	public <T> List<T> getVOSQL(String sql, Class clazz, int startRow, int pageSize,Object ... args) throws BaseException;

	/**
	 * 
	 * @Title: queryForString
	 * @Description: TODO(直接返回查询结果 ，用户查询相关值，或者总条数)
	 * @param sql       sql
	 * @return String   返回String
	 * @throws BaseException 
	 */
	public String queryForString(String sql,Object ... args) throws BaseException;
    
	/**
	 * 
	 * @Title: getSqlList 
	 * @Description: TODO(sql分页返回对象)
	 * @param sql  
	 * @param cls 实体类
	 * @param startRow  开始行
	 * @param pageSize  每页行数
	 * @return  List
	 * @throws BaseException
	 *
	 */
	public List getSqlList(String sql, Class cls,int startRow, int pageSize,Object ... args)
			throws BaseException;

	/**
	 * 
	 * @Title: getSqlList 
	 * @Description: TODO(sql列表返回对象)
	 * @param sql
	 * @param cls  实体类
	 * @return  List
	 * @throws BaseException
	 *
	 */
	public List getSqlList(String sql, Class cls,Object ... args) throws BaseException;
	
	
	public Session getSession();
	
	
	public void sqlcommit();
	
	public void sqlclear();
}
