package com.hpkj.culture.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.culture.dao.CultureDao;
import com.hpkj.culture.entity.HpoaCultureInfo;
import com.hpkj.culture.service.CultureService;
import com.hpkj.culture.vo.CultureInfoVo;
import com.hpkj.culture.vo.CultureListVo;
import com.hpkj.minutes.vo.MinutesListVo;
import com.hpkj.monthly.entity.HpoaMonthsumInfo;
import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.system.vo.SysRoleInfoVo;

@Service("cultureService")
@Transactional
public class CultureServiceImpl extends BaseServiceImpl implements
		CultureService {
	@Resource
	private CultureDao cultureDao;

	
	//保存添加的企业文化
	@Override
	public String addSaveCulture(HpoaCultureInfo hpoaCultureInfo, String staffId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			Assert.notNull(staffId,"添加者ID为空！");
			HpoaStaffInfo sto=new HpoaStaffInfo();
			sto = cultureDao.getModelById(HpoaStaffInfo.class, staffId);
			String staffComp = sto.getStaffComp();
			
			hpoaCultureInfo.setStaffId(staffId);
			hpoaCultureInfo.setReleasedate(DateTimeUtil.getDateTime());
			hpoaCultureInfo.setStaffComp(staffComp);
			cultureDao.sqlclear();
			cultureDao.saveOrUpdateModel(hpoaCultureInfo);
			cultureDao.sqlcommit();
			rtn = "1";
		} catch (Exception e) {
			// TODO: handle exception
			throw new BaseException("保存添加的企业文化信息出错",e);
		}
		
		return rtn;
	}


	//easyui 查询企业文化列表
	@Override
	public CultureInfoVo searchCultureList(HpoaCultureInfo hpoaCultureInfo,
			String page, String rows, int from, int length,String sort,String order) throws BaseException {
		// TODO Auto-generated method stub
		CultureInfoVo cultureInfoVo = new CultureInfoVo();		
		try {
		//当前页数据
		//String hql = " from HpoaCultureInfo s where 1 = 1 ";
		String sql = " select t.cultureid as cultureid,t.staff_comp as staffComp,t.culturetitle as culturetitle," +
				" t.culturecontent as culturecontent,t.releasedate as releasedate,t.bak1 as bak1,t.bak2 as bak2," +
				" t.bak3 as bak3,t.staff_id as staffId" +
				" from HPOA_CULTURE_INFO t ";
		if (sort!=null||order!=null) {
			sql += " order by " + sort + " " + order + ",cultureid desc";
		}
		
		List list =cultureDao.getVOSQL(sql, CultureListVo.class, from, length);
		//总数据
		List allList =cultureDao.getVOSQL(sql, CultureListVo.class);

		cultureInfoVo.setRows(list);
		cultureInfoVo.setPage(Integer.parseInt(page));
		cultureInfoVo.setRecords(allList.size());
		
		//int total = (allList.size()%Integer.parseInt(rows)==0) ? (allList.size()/Integer.parseInt(rows)) : (allList.size()/Integer.parseInt(rows)+1);
		int total = (allList.size());		
		cultureInfoVo.setTotal(total);

		} catch (Exception e) {
			throw new BaseException("easyui 查询企业文化列表", e);
		}
		return cultureInfoVo;
	}


	//根据文化id查询文化详细信息
	@Override
	public HpoaCultureInfo searchCultureContent(String cultureid) throws BaseException {
		// TODO Auto-generated method stub
		HpoaCultureInfo cul = new HpoaCultureInfo();
		try {
			Assert.notNull(cultureid,"文化ID为空！");
			cul = cultureDao.getModelById(HpoaCultureInfo.class, cultureid);
		} catch (Exception e) {
			
			throw new BaseException("根据文化id查询文化详细信息错误",e);
		}
		return cul;
	}


	//保存修改的企业文化信息
	@Override
	public String modifyCultureInfo(HpoaCultureInfo hpoaCultureInfo,
			String staffId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try{
			//根据ID查获取月报实体信息
			HpoaCultureInfo hcul = cultureDao.getModelById(HpoaCultureInfo.class, hpoaCultureInfo.getCultureid());
			  
			
			hcul.setCulturecontent(hpoaCultureInfo.getCulturecontent());
			hcul.setCulturetitle(hpoaCultureInfo.getCulturetitle());
			hcul.setReleasedate(DateTimeUtil.getDateTime());
			hcul.setStaffId(staffId);
			cultureDao.updateModel(hcul);
			cultureDao.sqlcommit();
			rtn = "1";
		} catch (Exception e) {
			
			throw new BaseException("保存修改的月报错误",e);
		}
		return rtn;
	}

    //根据文化id删除企业文化信息
	@Override
	public String deleteCultureInfo(String cultureid) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			Assert.notNull(cultureid,"文化ID为空！");
			String sql = "delete HPOA_CULTURE_INFO t where t.cultureid=?";
			cultureDao.excuteSql(sql, cultureid);
			rtn = "1";
		}
		catch (Exception e) {
			throw new BaseException("根据文化id删除企业文化信息时出错",e);
		}
		return rtn;
	}
	
	

}
