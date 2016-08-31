package com.hpkj.minutes.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.minutes.dao.MinutesDao;
import com.hpkj.minutes.entity.HpoaMinutesInfo;
import com.hpkj.minutes.service.MinutesService;
import com.hpkj.minutes.vo.MinutesInfoVo;
import com.hpkj.minutes.vo.MinutesListVo;
import com.hpkj.monthly.vo.EachMonthlyVo;
import com.hpkj.staff.entity.HpoaStaffInfo;

@Service("minutesService")
@Transactional
public class MinutesServiceImpl extends BaseServiceImpl implements
		MinutesService {

	@Resource
	private MinutesDao minutesDao;

	//保存新增的会议纪要信息
	@Override
	public String addMinutesInfo(HpoaMinutesInfo hpoaMinutesInfo, String staffId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			Assert.notNull(staffId,"添加者ID为空！");
			HpoaStaffInfo sto=new HpoaStaffInfo();
			sto = minutesDao.getModelById(HpoaStaffInfo.class, staffId);
			String staffComp = sto.getStaffComp();
			
			hpoaMinutesInfo.setStaffId(staffId);
			hpoaMinutesInfo.setStaffComp(staffComp);
			hpoaMinutesInfo.setUploaddate(DateTimeUtil.getDateTime());
			
			minutesDao.sqlclear();
			//minutesDao.saveOrUpdateModel(hpoaMinutesInfo);
			minutesDao.saveModel(hpoaMinutesInfo);
			minutesDao.sqlcommit();
			rtn = "1";
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new BaseException("保存新增的会议纪要信息出错",e);
		}
		return rtn;
	}

	//查询会议纪要列表
	@Override
	public MinutesInfoVo searchMinutesList(HpoaMinutesInfo hpoaMinutesInfo,
			String page, String rows, int from, int length,String order,String sort) throws BaseException {
		// TODO Auto-generated method stub
		MinutesInfoVo minutesInfoVo = new MinutesInfoVo();		
		try {
		//当前页数据
		//String hql = " from HpoaMinutesInfo s where 1 = 1 ";
		String sql = "select t.minutesid as minutesid ,s.staff_name as staffName,t.staff_comp as staffComp,t.meetingtheme as meetingtheme," +
				" t.meetingcontent as meetingcontent,t.meetingdate as meetingdate,t.meetingplace as meetingplace,t.attendees as attendees," +
				" t.minuteslink as minuteslink,t.remark as remark,t.uploaddate as uploaddate,t.bak1 as bak1,t.bak2 as bak2,t.bak3 as bak3,t.meetinghost as meetinghost" +
				" from HPOA_MINUTES_INFO t" +
				" left join HPOA_STAFF_INFO s on t.staff_id=s.staff_id";
		sql += " order by " + sort + " " + order + ",minutesid desc";
		List list =minutesDao.getVOSQL(sql, MinutesListVo.class, from, length);
		//总数据
		List allList =minutesDao.getVOSQL(sql, MinutesListVo.class);
		minutesInfoVo.setRows(list);
		minutesInfoVo.setPage(Integer.parseInt(page));
		minutesInfoVo.setRecords(allList.size());
		
		//int total = (allList.size()%Integer.parseInt(rows)==0) ? (allList.size()/Integer.parseInt(rows)) : (allList.size()/Integer.parseInt(rows)+1);
		int total = (allList.size());		
		minutesInfoVo.setTotal(total);

		} catch (Exception e) {
			throw new BaseException("查询会议纪要列表时出错", e);
		}
		return minutesInfoVo;
	}

	//根据纪要id删除会议纪要
	@Override
	public String deleteMinutesInfo(String minutesid) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			Assert.notNull(minutesid,"纪要ID为空！");
			String sql="delete HPOA_MINUTES_INFO t where t.minutesid=?";
			minutesDao.excuteSql(sql, minutesid);
			rtn="1";
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("根据纪要id删除会议纪要时出错", e);
		}
		return rtn;
	}
    //根据纪要id查询纪要详情
	@Override
	public HpoaMinutesInfo searchMinutesInfo(String minutesid) throws BaseException {
		// TODO Auto-generated method stub
		HpoaMinutesInfo mut = new HpoaMinutesInfo();
		try {
			Assert.notNull(minutesid,"纪要ID为空！");
			mut = minutesDao.getModelById(HpoaMinutesInfo.class, minutesid);
		} catch (Exception e) {
			
			throw new BaseException("根据文化id查询文化详细信息错误",e);
		}
		return mut;
	}

	//保存修改的会议纪要
	@Override
	public String modifyMinutesInfo(HpoaMinutesInfo hpoaMinutesInfo,
			String staffId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "4";
		try{
			//根据ID查获取会议纪要实体信息
			HpoaMinutesInfo mut = minutesDao.getModelById(HpoaMinutesInfo.class, hpoaMinutesInfo.getMinutesid());
			  
			if (hpoaMinutesInfo.getMinuteslink()!=null) {
				mut.setMinuteslink(hpoaMinutesInfo.getMinuteslink());
			}
			mut.setMeetingcontent(hpoaMinutesInfo.getMeetingcontent());
			mut.setMeetingdate(hpoaMinutesInfo.getMeetingdate());
			mut.setMeetinghost(hpoaMinutesInfo.getMeetinghost());
			mut.setMeetingplace(hpoaMinutesInfo.getMeetingplace());
			mut.setMeetingtheme(hpoaMinutesInfo.getMeetingtheme());
			mut.setRemark(hpoaMinutesInfo.getRemark());
			mut.setAttendees(hpoaMinutesInfo.getAttendees());
			mut.setRemark(hpoaMinutesInfo.getRemark());
			mut.setUploaddate(DateTimeUtil.getDateTime());

			minutesDao.updateModel(mut);
			minutesDao.sqlcommit();
			rtn = "3";
		} catch (Exception e) {
			
			throw new BaseException("保存修改的会议纪要时出错",e);
		}
		return rtn;
	}

	//删除会议纪要所属的附件
	@Override
	public int deleteMinutesAttachment(String minutesid) throws BaseException {
		// TODO Auto-generated method stub
		int flag = 0;// 标识状态0：系统出错，1：删除成功，2删除失败
		try {
			if (minutesid==null) {
				throw new BaseException("根据id 删除会议纪要所属的附件时出错,参数值为空");
			}
			HpoaMinutesInfo info = searchMinutesInfo(minutesid);
			info.setMinuteslink(null);
			minutesDao.updateModel(info);
			minutesDao.sqlcommit();
			flag = 1;
		} catch (Exception e) {
			flag = 0;
			throw new BaseException("根据id  删除会议纪要所属的附件时出错", e);
		}
		return flag;
	}
	
	
	
	
	
	
}
