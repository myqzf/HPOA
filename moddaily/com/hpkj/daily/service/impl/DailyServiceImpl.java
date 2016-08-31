package com.hpkj.daily.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.daily.dao.impl.DailyDaoImpl;
import com.hpkj.daily.entity.HpoaDailyInfo;
import com.hpkj.daily.service.DailyService;
import com.hpkj.daily.vo.DailyInfoVo;
@Service("dailyService")
public class DailyServiceImpl extends BaseServiceImpl implements DailyService{
	@Resource
	private DailyDaoImpl dailyDao;
	/* 添加员工日志
	 * (non-Javadoc)
	 * @see com.hpkj.daily.service.DailyService#addDaily(com.hpkj.daily.entity.HpoaDailyInfo)
	 */
	@Override
	public int addDaily(HpoaDailyInfo dailyInfo) throws BaseException{
		int flag = 0;
		String date=DateTimeUtil.getDateTime();//当前日期时间		
		try {
			dailyInfo.setInputtime(date);//录入的时间
			if (dailyInfo.getContent().isEmpty()) {
				flag = 3;
				return flag;
			}
			flag = 2;
			dailyDao.sqlclear();
			dailyDao.saveOrUpdateModel(dailyInfo);
			dailyDao.sqlcommit();
			flag = 1;
		} catch (Exception e) {
			flag = 0;
			throw new BaseException("添加工作日志时出错", e);
		}
		return flag;
		
	}	
	
	/* 获取员工入职时间
	 * (non-Javadoc)
	 * @see com.hpkj.daily.service.DailyService#getStaffTime(java.lang.String)
	 */
	@Override
	public String getStaffTime(String staffId) throws BaseException{
		String date="";
		try{
			String sql="select t.bak1 as bak1 from hpoa_staff_info t where t.staff_id=?";
			date=(dailyDao.queryForString(sql, staffId)).substring(0, 10);
		}catch(Exception e){
			throw new BaseException("查询员工入职时间时出错",e);
		}
		return date;
	}
	
	/* 根据员工id和日期获取指定日期的日志内容
	 * (non-Javadoc)
	 * @see com.hpkj.daily.service.DailyService#getDailyInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public DailyInfoVo getDailyInfo(String staffId,String daydate) throws BaseException{		
		DailyInfoVo dailyInfo=null;
		try {
			String sql="select t.daily_id as dailyId,t.staff_id as staffId,t.daydate as daydate," +
					"t.title as title,t.content as content,t.plan as plan,t.state as state," +
					"t.inputtime as inputtime from hpoa_daily_info t where t.staff_id=? and t.daydate=?";
			dailyInfo = (DailyInfoVo)dailyDao.getVOSQL(sql, DailyInfoVo.class, staffId,daydate).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseException("得到日志实体时出错",e);
		}
		return dailyInfo;
	}
	/* 判断指定日期内的日志内容是否已经存在
	 * (non-Javadoc)
	 * @see com.hpkj.daily.service.DailyService#isExist(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isExist(String staffId,String daydate) throws BaseException{
		boolean flag=false;//true：当日日志已经存在，false:当日日志不存在
		try{
		String hql="from HpoaDailyInfo where staffId=? and daydate=?";
		List list1=dailyDao.getHqlList(hql, staffId,daydate);
		if(list1.size()==0){
			flag=false;
		}else{
			flag=true;
		}
		}catch(Exception e){
			// TODO Auto-generated catch block
			throw new BaseException("判断指定日期内的日志内容是否已经存在时出错",e);
		}
		return flag;
	}
	
	/* 判断指定日期内的日志内容是否已经存在
	 * (non-Javadoc)
	 * @see com.hpkj.daily.service.DailyService#isExist(java.lang.String, java.lang.String)
	 */
	@Override
	public List timeList(String staffId) throws BaseException{
		List list1;
		try{
		String sql="select t.daydate from HPOA_DAILY_INFO t where t.staff_id=?";
		list1=dailyDao.getSqlList(sql, staffId);
		
		}catch(Exception e){
			// TODO Auto-generated catch block
			throw new BaseException("判断指定日期内的日志内容是否已经存在时出错",e);
		}
		return list1;
	}
}
