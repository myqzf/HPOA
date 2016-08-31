package com.hpkj.core.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hpkj.core.dao.IBaseDao;
import com.hpkj.core.exception.BaseException;


@Service("baseService")
public class BaseServiceImpl implements IBaseService {
	private static Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
	@Resource
	private IBaseDao baseDao;
	
	@Override
	public <T> void addObj(T model) throws BaseException {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdateModel(model);
	}

	@Override
	public <T> void modifyObj(T model) throws BaseException {
		// TODO Auto-generated method stub
		baseDao.updateModel(model);
	}

	@Override
	public <T> void delObj(T model) throws BaseException {
		// TODO Auto-generated method stub
		baseDao.deleteModel(model);
	}

	@Override
	public <T> void delObjById(Class classz, Serializable id)
			throws BaseException {
		// TODO Auto-generated method stub
		baseDao.deleteModelById(classz, id);
	}

	@Override
	public <T> T getObjById(Class classz, Serializable id) throws BaseException {
		// TODO Auto-generated method stub
		return baseDao.getModelById(classz, id);
	}

	@Override
	public void excuteHql(String hql, Object... args) throws BaseException {
		// TODO Auto-generated method stub
		baseDao.excuteHql(hql,args);
	}

	@Override
	public void excuteSql(String sql, Object... args) throws BaseException {
		// TODO Auto-generated method stub
		baseDao.excuteSql(sql,args);
	}

	@Override
	public <T> List<T> getHqlList(String hql, Object... args)
			throws BaseException {
		// TODO Auto-generated method stub
		return baseDao.getHqlList(hql,args);
	}

	@Override
	public <T> List<T> getHqlList(String hql, int startRow, int pageSize,
			Object... args) throws BaseException {
		// TODO Auto-generated method stub
		return baseDao.getHqlList(hql, startRow, pageSize,args);
	}

	@Override
	public List getSqlList(String sql, Object... args) throws BaseException {
		// TODO Auto-generated method stub
		return baseDao.getSqlList(sql, args);
	}

	@Override
	public List getSqlList(String sql, Class cls, Object... args)
			throws BaseException {
		// TODO Auto-generated method stub
		return baseDao.getSqlList(sql, cls, args);
	}

	@Override
	public List getSqlList(String sql, Class cls, int startRow, int pageSize,
			Object... args) throws BaseException {
		// TODO Auto-generated method stub
		return baseDao.getSqlList(sql, cls, startRow, pageSize, args);
	}

	@Override
	public List getSqlList(String sql, int startRow, int pageSize,
			Object... args) throws BaseException {
		// TODO Auto-generated method stub
		return baseDao.getSqlList(sql, startRow, pageSize, args);
	}

	@Override
	public <T> List<T> getVOSQL(String sqlText, Class clazz, Object... args)
			throws BaseException {
		// TODO Auto-generated method stub
		return baseDao.getVOSQL(sqlText, clazz, args);
	}

	@Override
	public <T> List<T> getVOSQL(String sqlText, Class clazz, int startRow,
			int pageSize, Object... args) throws BaseException {
		// TODO Auto-generated method stub
		return baseDao.getVOSQL(sqlText, clazz, startRow, pageSize, args);
	}
	
	@Override
	public String queryForString(String sql, Object... args) {
		// TODO Auto-generated method stub
		String rtnstr = "";
		try {
			rtnstr = baseDao.queryForString(sql, args);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			rtnstr = "";
		}
		return rtnstr;
	}

}
