package com.hpkj.core.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.util.DateTimeUtil;

@Repository("baseDao")
public class BaseDaoImpl implements IBaseDao {

	private static Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

	@Resource
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private String getLimitString(String sql, int startrow, int pagesize) {
		boolean hasOffset;
		if (startrow == 0)
			hasOffset = false;
		else
			hasOffset = true;
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (hasOffset) {
			pagingSelect
					.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (hasOffset) {
			pagingSelect
					.append(" ) row_ where rownum <= " + (pagesize + startrow)
							+ ") where rownum_ > " + (startrow));
		} else {
			pagingSelect.append(" ) where rownum <= " + pagesize);
		}

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <T> void saveModel(T model) throws BaseException {
		try {
			getSession().save(model);
		} catch (Exception e) {
			log.error("数据层保存实体【" + model.getClass() + "】时出错！");
			throw new BaseException("数据层保存实体【" + model.getClass() + "】时出错！", e);
		}
	}

	@Override
	public <T> void saveOrUpdateModel(T model) throws BaseException {
		try {
			getSession().saveOrUpdate(model);
		} catch (Exception e) {
			log.error("数据层保存/更新实体【" + model.getClass() + "】时出错！");
			throw new BaseException("数据层保存/更新实体【" + model.getClass() + "】时出错！",
					e);
		}
	}

	@Override
	public <T> void updateModel(T model) throws BaseException {
		try {
			getSession().update(model);
		} catch (Exception e) {
			log.error("数据层更新实体【" + model.getClass() + "】时出错！");
			throw new BaseException("数据层更新实体【" + model.getClass() + "】时出错！", e);
		}
	}

	@Override
	public <T> void mergeModel(T model) throws BaseException {
		try {
			getSession().merge(model);
		} catch (Exception e) {
			throw new BaseException("数据层更新实体【" + model.getClass() + "】时出错！", e);
		}
	}

	@Override
	public <T> void deleteModel(T model) throws BaseException {
		try {
			getSession().delete(model);
		} catch (Exception e) {
			log.error("数据层删除实体【" + model.getClass() + "】时出错！");
			throw new BaseException("数据层删除实体【" + model.getClass() + "】时出错！", e);
		}
	}

	@Override
	public <T> void deleteModelById(final Class classz, final Serializable id)
			throws BaseException {
		try {
			getSession().delete(getModelById(classz, id));
		} catch (Exception e) {
			log.error("数据层删除实体（根据ID）【" + classz + "】时出错！");
			throw new BaseException("数据层删除实体（根据ID）【" + classz + "】时出错！", e);
		}
	}

	/**
	 * 获取实体 getModelById
	 * 
	 * @param model
	 */
	@Override
	public <T> T getModelById(final Class classz, final Serializable id)
			throws BaseException {
		try {
			return (T) getSession().get(classz, id);
		} catch (Exception e) {
			log.error("数据层加载除实体（根据ID）【" + classz + "】时出错！");
			throw new BaseException("数据层加载除实体（根据ID）【" + classz + "】时出错！", e);
		}
	}

	@Override
	public <T> List<T> getHqlList(String hql, Object... args)
			throws BaseException {
		try {
			Query query = getSession().createQuery(hql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
			}
			List<T> list = query.list();
			System.out.println(query.getQueryString());
			return list;
		} catch (Exception e) {
			log.error("数据层hql【" + hql + "】查询出错!",e);
			throw new BaseException("数据层hql【" + hql + "】查询出错!", e);
		}
	}

	@Override
	public <T> List<T> getHqlList(String hql, int startRow, int pageSize,
			Object... args) throws BaseException {
		try {
			Query query = getSession().createQuery(hql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
			}
			query.setFirstResult(startRow);
			query.setMaxResults(pageSize);
			List<T> list = query.list();
			return list;
		} catch (Exception e) {
			log.error("数据层hql【" + hql + "】分页查询时出错！");
			throw new BaseException("数据层hql【" + hql + "】分页查询时出错！", e);
		}
	}

	@Override
	public List getSqlList(String sql, Object... args) throws BaseException {
		try {
			Query query = getSession().createSQLQuery(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
					if (args[i].getClass().getName().toString()
							.equals("java.lang.String"))
						sql = sql.replaceFirst("\\?", "'" + args[i].toString()
								+ "'");
					else
						sql = sql.replaceFirst("\\?", args[i].toString());
				}
			}
			List list = query.list();
			System.out.println(DateTimeUtil.getDateTime() + " INFO (SQL)：" + sql);
			return list;
		} catch (Exception e) {
			log.error("数据层sql【" + sql + "】查询列表时出错！");
			throw new BaseException("数据层sql【" + sql + "】查询列表时出错！", e);
		}
	}

	@Override
	public List getSqlList(String sql, Class cls, Object... args)
			throws BaseException {
		try {
			Query query = getSession().createSQLQuery(sql).addEntity(cls);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
					if (args[i].getClass().getName().toString()
							.equals("java.lang.String"))
						sql = sql.replaceFirst("\\?", "'" + args[i].toString()
								+ "'");
					else
						sql = sql.replaceFirst("\\?", args[i].toString());
				}
			}
			List list = query.list();
			System.out.println(DateTimeUtil.getDateTime() + " INFO (SQL)：" + sql);
			return list;
		} catch (Exception e) {
			log.error("数据层sql【" + sql + "】返回实体对象列表时出错！",e);
			throw new BaseException("数据层sql【" + sql + "】返回实体对象列表时出错！", e);
		}
	}

	@Override
	public List getSqlList(String sql, Class cls, int startRow, int pageSize,
			Object... args) throws BaseException {
		try {
			Query query = getSession().createSQLQuery(sql).addEntity(cls);
			sql = getLimitString(sql, startRow, pageSize);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
					if (args[i].getClass().getName().toString()
							.equals("java.lang.String"))
						sql = sql.replaceFirst("\\?", "'" + args[i].toString()
								+ "'");
					else
						sql = sql.replaceFirst("\\?", args[i].toString());
				}
			}
			query.setFirstResult(startRow);
			query.setMaxResults(pageSize);
			List list = query.list();
			System.out.println(DateTimeUtil.getDateTime() + " INFO (SQL)：" + sql);
			return list;
		} catch (Exception e) {
			log.error("数据层sql【" + sql + "】分页返回实体对象时出错！");
			throw new BaseException("数据层sql【" + sql + "】分页返回实体对象时出错！", e);
		}
	}

	@Override
	public List getSqlList(String sql, int startRow, int pageSize,
			Object... args) throws BaseException {
		try {
			Query query = getSession().createSQLQuery(sql);
			sql = getLimitString(sql, startRow, pageSize);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
					if (args[i].getClass().getName().toString()
							.equals("java.lang.String"))
						sql = sql.replaceFirst("\\?", "'" + args[i].toString()
								+ "'");
					else
						sql = sql.replaceFirst("\\?", args[i].toString());
				}
			}
			query.setFirstResult(startRow);
			query.setMaxResults(pageSize);
			List list = query.list();
			System.out.println(DateTimeUtil.getDateTime() + " INFO (SQL)：" + sql);
			return list;
		} catch (Exception e) {
			log.error("数据层sql【" + sql + "】分页查询时出错！");
			throw new BaseException("数据层sql【" + sql + "】分页查询时出错！", e);
		}
	}

	@Override
	public void excuteHql(String hql, Object... args) throws BaseException {
		try {
			Query query = getSession().createQuery(hql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
			}
			query.executeUpdate();
		} catch (Exception e) {
			log.error("数据层执行hql【" + hql + "】时出错！");
			throw new BaseException("数据层执行hql【" + hql + "】时出错！", e);
		}
	}

	@Override
	public void excuteSql(String sql, Object... args) throws BaseException {
		try {
			Query query = getSession().createSQLQuery(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
					if (args[i].getClass().getName().toString()
							.equals("java.lang.String"))
						sql = sql.replaceFirst("\\?", "'" + args[i].toString()
								+ "'");
					else
						sql = sql.replaceFirst("\\?", args[i].toString());
				}
			}
			query.executeUpdate();
			
			System.out.println(DateTimeUtil.getDateTime() + " INFO (SQL)：" + sql);
		} catch (Exception e) {
			log.error("数据层执行【" + sql + "】时出错！");
			throw new BaseException("数据层执行【" + sql + "】时出错！", e);
		}
	}

	@Override
	public <T> List<T> getVOSQL(String sqlText, Class clazz, Object... args)
			throws BaseException {
		try {
			SQLQuery query = this.getSession().createSQLQuery(sqlText);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
					if (args[i].getClass().getName().toString()
							.equals("java.lang.String"))
						sqlText = sqlText.replaceFirst("\\?",
								"'" + args[i].toString() + "'");
					else
						sqlText = sqlText.replaceFirst("\\?",
								args[i].toString());
				}
			}

			Field f[] = clazz.getDeclaredFields();
			for (int i = 0; i < f.length; i++) {
				// System.out.println("属性的名称是:"+f[i].getName());//获得属性的名字
				// System.out.println("属性的类型是:"+f[i].getType());//获得属性的类型
				// int mod = f[i].getModifiers();// 获得修饰符的常量总和
				// System.out.println(mod);
				// System.out.println("属性的修饰符有:"+Modifier.toString(mod));//modifier类可以根据常量总和去计算到底有哪些修饰符
				// System.out.println("-------------------------------------------------------");
				String name = f[i].getName();
				String classname = f[i].getType().toString();
				if (classname.equals("class java.util.Date"))
					query.addScalar(name, new org.hibernate.type.DateType());
				else if (classname.equals("class java.lang.Integer"))
					query.addScalar(name, new org.hibernate.type.IntegerType());
				else if (classname.equals("class java.math.BigDecimal"))
					query.addScalar(name,
							new org.hibernate.type.BigDecimalType());
				else if (classname.equals("class java.lang.Short"))
					query.addScalar(name, new org.hibernate.type.ShortType());
				else if (classname.equals("class java.lang.Double"))
					query.addScalar(name, new org.hibernate.type.DoubleType());
				else if (classname.equals("class java.lang.Long"))
					query.addScalar(name, new org.hibernate.type.LongType());
				else if (classname.equals("class java.lang.Byte"))
					query.addScalar(name, new org.hibernate.type.ByteType());
				else
					query.addScalar(name, new org.hibernate.type.StringType());
			}
			query.setResultTransformer(Transformers.aliasToBean(clazz));
			List<T> list = query.list();
			System.out.println(DateTimeUtil.getDateTime() + " INFO (SQL)："
					+ sqlText);
			return list;
		} catch (Exception e) {
			log.error("数据层执行sql【" + sqlText + "】查询返回对象时出错！");
			throw new BaseException("数据层执行sql【" + sqlText + "】查询返回对象时出错！", e);
		}
	}

	@Override
	public <T> List<T> getVOSQL(String sqlText, Class clazz, int startRow,
			int pageSize, Object... args) throws BaseException {
		try {
			SQLQuery query = this.getSession().createSQLQuery(sqlText);
			sqlText = getLimitString(sqlText, startRow, pageSize);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
					if (args[i].getClass().getName().toString()
							.equals("java.lang.String"))
						sqlText = sqlText.replaceFirst("\\?",
								"'" + args[i].toString() + "'");
					else
						sqlText = sqlText.replaceFirst("\\?",
								args[i].toString());
				}
			}

			Field f[] = clazz.getDeclaredFields();
			for (int i = 0; i < f.length; i++) {
				String name = f[i].getName();
				String classname = f[i].getType().toString();
				if (classname.equals("class java.util.Date"))
					query.addScalar(name, new org.hibernate.type.DateType());
				else if (classname.equals("class java.lang.Integer"))
					query.addScalar(name, new org.hibernate.type.IntegerType());
				else if (classname.equals("class java.math.BigDecimal"))
					query.addScalar(name,
							new org.hibernate.type.BigDecimalType());
				else if (classname.equals("class java.lang.Short"))
					query.addScalar(name, new org.hibernate.type.ShortType());
				else if (classname.equals("class java.lang.Double"))
					query.addScalar(name, new org.hibernate.type.DoubleType());
				else if (classname.equals("class java.lang.Long"))
					query.addScalar(name, new org.hibernate.type.LongType());
				else if (classname.equals("class java.lang.Byte"))
					query.addScalar(name, new org.hibernate.type.ByteType());
				else if (classname.equals("class org.springframework.orm.hibernate3.support.ClobStringType"))
					query.addScalar(name, new org.hibernate.type.ClobType());
				else
					query.addScalar(name, new org.hibernate.type.StringType());
			}
			query.setResultTransformer(Transformers.aliasToBean(clazz));
			query.setFirstResult(startRow);
			query.setMaxResults(pageSize);
			List<T> list = query.list();
			System.out.println(DateTimeUtil.getDateTime() + " INFO (SQL)："
					+ sqlText);
			return list;
		} catch (Exception e) {
			log.error("数据层执行sql【" + sqlText + "】查询返回对象分页时出错！");
			throw new BaseException("数据层执行sql【" + sqlText + "】查询返回对象分页时出错！", e);
		}
	}

	@Override
	public String queryForString(String sqlText, Object... args)
			throws BaseException {
		try {
			SQLQuery query = this.getSession().createSQLQuery(sqlText);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
					if (args[i].getClass().getName().toString()
							.equals("java.lang.String"))
						sqlText = sqlText.replaceFirst("\\?",
								"'" + args[i].toString() + "'");
					else
						sqlText = sqlText.replaceFirst("\\?",
								args[i].toString());
				}
			}
			List list = query.list();
			System.out.println(DateTimeUtil.getDateTime() + " INFO (SQL)："
					+ sqlText);
			if (list.size() > 0) {
				Object tmp = list.get(0);
				if (tmp != null)
					return "" + tmp;
				else
					return "";
			} else {
				return "";
			}
		} catch (Exception e) {
			
			log.error("数据层sql【" + sqlText + "】查询指定值时出错！");
			throw new BaseException("数据层sql【" + sqlText + "】查询指定值时出错！", e);
		}
	}

	@Override
	public void sqlcommit() {
		this.getSession().flush();
		this.getSession().clear();
	}
	
	@Override
	public void sqlclear() {
		// TODO Auto-generated method stub
		this.getSession().clear();
	}
}