package com.hpkj.monthly.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.dictionary.entity.SysDictItems;
import com.hpkj.monthly.dao.MonthlyDao;
import com.hpkj.monthly.entity.HpoaMonthsumInfo;
import com.hpkj.monthly.service.MonthlyService;
import com.hpkj.monthly.vo.EachMonthlyVo;
import com.hpkj.monthly.vo.HpoaMonthsumInfoVo;
import com.hpkj.monthly.vo.ManageMonthsumVo;
import com.hpkj.monthly.vo.MonthlyVo;
import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.staff.vo.StaffDetailInfoVo;

@Service("monthlyService")
@Transactional
public class MonthlyServiceImpl extends BaseServiceImpl implements
		MonthlyService {

	@Resource
	private MonthlyDao monthlyDAO;

	//查询字典项--月份信息(head_id=52)
	@Override
	public List<SysDictItems> getDictMonthList() throws BaseException {
		// TODO Auto-generated method stub
		String sql = "select * from sys_dict_items di where di.head_id=52 and di.pid  is not null";
		List <SysDictItems> list = null;
		try {
			
			list  = monthlyDAO.getSqlList(sql, SysDictItems.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseException("查询字典项--月份信息错误",e);
		}
		return list;	
	}

	//保存撰写的月报
	@Override
	public String addSaveMonthly(HpoaMonthsumInfo hpoaMonthsumInfo,String staffId ) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		String year="";
		try {
			String monthscope=hpoaMonthsumInfo.getMonthscope();
			if (monthscope.contains("128")) {//“128”即字典表中的12月
			//if (monthscope.contains("12")) {
				Calendar calendar = Calendar.getInstance();   
				calendar.add(Calendar.MONTH, -1);    //得到前一个月   
				int Cyear = calendar.get(Calendar.YEAR); 
				year=Cyear+"";
			}else {
				String dateString =DateTimeUtil.getDate();
				 year=dateString.substring(0, 4);
			}
			
			  SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  Date date = DateTimeUtil.getNow();
			  String datetime = pattern.format(date);
				
			hpoaMonthsumInfo.setStaffId(staffId);
			hpoaMonthsumInfo.setUploaddate(datetime);
			hpoaMonthsumInfo.setBak1(year+"");//所属年份
			monthlyDAO.sqlclear();
			monthlyDAO.saveOrUpdateModel(hpoaMonthsumInfo);
			monthlyDAO.sqlcommit();
			rtn = "1";
			
		} catch (Exception e) {
			
			throw new BaseException("保存撰写的月报出错",e);
		}
		return rtn;
	}

	//保存上传的月报
	@Override
	public int addSaveUploadMonthly(String staffId, String url, String title,
			String datetime,String month) throws BaseException {
		// TODO Auto-generated method stub
		int flag = 0;// 标识状态：0系统错误，1修改成功，2修改失败
		String year="";
		try {
			flag = 2;
			HpoaMonthsumInfo monthly= new HpoaMonthsumInfo();
			
			if (month.contains("128")) {
			//if (month.contains("12")) {
				Calendar calendar = Calendar.getInstance();   
				calendar.add(Calendar.MONTH, -1);    //得到前一个月   
				int Cyear = calendar.get(Calendar.YEAR); 
				year=Cyear+"";
			}else {
				String dateString =DateTimeUtil.getDate();
				 year=dateString.substring(0, 4);
			}
			
			monthly.setBak1(year+"");
			monthly.setStaffId(staffId);
			monthly.setSumlink(url);
			monthly.setMonthtitle(title);
			monthly.setMonthscope(month);
			monthly.setUploaddate(datetime);
			monthlyDAO.saveModel(monthly);
			monthlyDAO.sqlcommit();
			flag = 1;
			
		} catch (Exception e) {
			flag = 0;
			throw new BaseException("保存上传的月报出错",e);
		}
		return flag;
	}

	//员工查询已提交月报列表
	@Override
	public HpoaMonthsumInfoVo searchEachMonthly(
			HpoaMonthsumInfo hpoaMonthsumInfo, String page, String rows,
			int from, int length,String sidx,String sord,String staffId) throws BaseException {
		
		HpoaMonthsumInfoVo hpoaMonthsumInfoVo = new HpoaMonthsumInfoVo();
		try {
			String sqlText = "select m.monthsumid as monthsumid ,m.staff_id as staffId,d.items_name as monthscope ," +
					" m.sumlink as sumlink,m.monthtitle as monthtitle,m.monthcontent as monthcontent,m.uploaddate as uploaddate,m.bak1 as bak1 ,m.bak2 as bak2,m.bak3 as bak3" +
					" from HPOA_MONTHSUM_INFO m " +
					" left join sys_dict_items d on m.monthscope=d.items_id and d.pid=116 " +
					" where m.staff_id='" + staffId + "'";
				sqlText += " order by " + sidx + " " + sord + ",monthsumid desc";
				
			List list =monthlyDAO.getVOSQL(sqlText, EachMonthlyVo.class, from, length);
			//总数据
			List allList = monthlyDAO.getVOSQL(sqlText, EachMonthlyVo.class);
			
			hpoaMonthsumInfoVo.setGridDTOsEach(list);
			//hpoaMonthsumInfoVo.setGridDTOs(list);
			hpoaMonthsumInfoVo.setPage(Integer.parseInt(page));
			hpoaMonthsumInfoVo.setRecords(allList.size());
			
			int total = (allList.size()%Integer.parseInt(rows)==0) ? (allList.size()/Integer.parseInt(rows)) : (allList.size()/Integer.parseInt(rows)+1);
			hpoaMonthsumInfoVo.setTotal(total);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new BaseException("员工查询已提交月报列表出错",e);
		}
		return hpoaMonthsumInfoVo;
	}

	//根据monthsumid查询具体月报内容
	@Override
	public HpoaMonthsumInfo searchEachMonthlyInfo(String monthsumid) throws BaseException {
		// TODO Auto-generated method stub
		HpoaMonthsumInfo mon = new HpoaMonthsumInfo();
		try {
			Assert.notNull(monthsumid,"月报ID为空！");
			mon = monthlyDAO.getModelById(HpoaMonthsumInfo.class, monthsumid);
		} catch (Exception e) {
			
			throw new BaseException("根据月报ID查询功能信息错误",e);
		}
		return mon;
	}
	
	//根据monthsumid查询具体月报内容    带月份   
	@Override
	public EachMonthlyVo searchEachMonthlyInfoTwo(String monthsumid) throws BaseException {
		// TODO Auto-generated method stub
		EachMonthlyVo mon = new EachMonthlyVo();
		try {
			Assert.notNull(monthsumid,"月报ID为空！");
			//mon = monthlyDAO.getModelById(HpoaMonthsumInfo.class, monthsumid);
			String sqlText = "select m.monthsumid as monthsumid ,m.staff_id as staffId,d.items_name as monthscope ," +
					" m.sumlink as sumlink,m.monthtitle as monthtitle,m.monthcontent as monthcontent,m.uploaddate as uploaddate,m.bak1 as bak1 ,m.bak2 as bak2,m.bak3 as bak3" +
					" from HPOA_MONTHSUM_INFO m " +
					" left join sys_dict_items d on m.monthscope=d.items_id and d.pid=116 " +
					" where m.monthsumid=?";
			List<EachMonthlyVo> li = monthlyDAO.getVOSQL(sqlText, EachMonthlyVo.class, monthsumid);					
			mon=li.get(0);
		} catch (Exception e) {
			
			throw new BaseException("根据monthsumid查询具体月报内容    带月份 信息错误",e);
		}
		return mon;
	}

	//根据monthsumid 删除月报
	@Override
	public String deleteMonthly(String monthsumid) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			Assert.notNull(monthsumid,"月报ID为空！");
			String sql = "delete HPOA_MONTHSUM_INFO t where t.monthsumid=?";
			monthlyDAO.excuteSql(sql, monthsumid);
			rtn = "1";
		}
		catch (Exception e) {
			throw new BaseException("根据monthsumid 删除月报",e);
		}
		return rtn;
	}

	//保存修改的月报
	@Override
	public String modifyMonthly(HpoaMonthsumInfo hpoaMonthsumInfo,String staffId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		String year="";
		try {
			 SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date date = DateTimeUtil.getNow();
			  String datetime = pattern.format(date);
			  
			  String monthscope=hpoaMonthsumInfo.getMonthscope();
				if (monthscope.contains("128")) {
					Calendar calendar = Calendar.getInstance();   
					calendar.add(Calendar.MONTH, -1);    //得到前一个月   
					int Cyear = calendar.get(Calendar.YEAR); 
					year=Cyear+"";
				}else {
					String dateString =DateTimeUtil.getDate();
					 year=dateString.substring(0, 4);
				}
			 
			//根据ID查获取月报实体信息
			  HpoaMonthsumInfo hmsi = monthlyDAO.getModelById(HpoaMonthsumInfo.class, hpoaMonthsumInfo.getMonthsumid());
			  
			  hmsi.setMonthscope(hpoaMonthsumInfo.getMonthscope());
			  hmsi.setMonthtitle(hpoaMonthsumInfo.getMonthtitle());
			  hmsi.setMonthcontent(hpoaMonthsumInfo.getMonthcontent());
			  hmsi.setUploaddate(datetime);
			  hmsi.setBak1(year);//所属年份
			monthlyDAO.updateModel(hmsi);
			monthlyDAO.sqlcommit();
			rtn = "1";
		} catch (Exception e) {
			
			throw new BaseException("保存修改的月报错误",e);
		}
		return rtn;
	}
	
   //查询当月月报是否已提交
	@Override
	public String seachIfSubmitted(String monthscope, String staffId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "1";//可上传
		String year="";
		try {
			String dateString =DateTimeUtil.getDate();
			if (monthscope.contains("128")) {//“128”即字典表中的12月
				
				Calendar calendar = Calendar.getInstance();   
				calendar.add(Calendar.MONTH, -1);    //得到前一个月   
				int Cyear = calendar.get(Calendar.YEAR);   
				int month = calendar.get(Calendar.MONTH)+1;   
				year=Cyear+"";
			}else {
				 year=dateString.substring(0, 4);
			}
			
			String sql = "select t.* from hpoa_monthsum_info t where t.monthscope='" + monthscope + "' and t.staff_id ='" + staffId + "'and t.bak1 =  '"+ year + "'";
			
			List li = monthlyDAO.getSqlList(sql);
			if(li.size()>0){
			
				  rtn = "2";//不可上传
		         }
			}catch (Exception e) {
			
			throw new BaseException("查询当月月报是否已提交信息出错",e);
		}
		return rtn;
	}

	//查找年份范围
	@SuppressWarnings("unchecked")
	@Override
	public List<HpoaMonthsumInfo> searchYearScope() throws BaseException {
		List<HpoaMonthsumInfo> list = null;
		try {
			String sqlmin = "SELECT * FROM (SELECT * FROM hpoa_monthsum_info p ORDER BY p.uploaddate ASC) TT WHERE ROWNUM = 1";//最早时间
			String sqlmax = "SELECT * FROM (SELECT * FROM hpoa_monthsum_info p ORDER BY p.uploaddate DESC) TT WHERE ROWNUM = 1";//最早时间
			list  = monthlyDAO.getSqlList(sqlmin, HpoaMonthsumInfo.class);
			 monthlyDAO.getSqlList(sqlmax, HpoaMonthsumInfo.class);
			list.addAll(monthlyDAO.getSqlList(sqlmax, HpoaMonthsumInfo.class));
			System.out.println(list);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("查找年份范围信息错误",e);
		}
		return list;
	}

	//查询月报最新所属月份
	@Override
	public HpoaMonthsumInfo searchNewMonth() throws BaseException {
		List<HpoaMonthsumInfo> list = null;
		HpoaMonthsumInfo hpoaMonthsumInfo = new HpoaMonthsumInfo();
		String sql = "SELECT * FROM (SELECT * FROM hpoa_monthsum_info p ORDER BY p.uploaddate desc) TT WHERE ROWNUM = 1";
		list =  monthlyDAO.getSqlList(sql, HpoaMonthsumInfo.class);
		String monthscope = list.get(0).getMonthscope();
		hpoaMonthsumInfo.setMonthscope(monthscope);
		return hpoaMonthsumInfo;
	}

	//当前最新提交的所有月报     
	@Override
	public ManageMonthsumVo searchCurrentMonthly(String year, String month,
			String page, String rows, int from, int length,String sidx,String sord) throws BaseException {
		// TODO Auto-generated method stub
		ManageMonthsumVo manageMonthsumVo = new ManageMonthsumVo();
		List<MonthlyVo> list = null;
		try {
			//当前页数据
			//String hql = " from HpoaMonthsumInfo s where 1 = 1 ";
			String sql = "select t.monthsumid as monthsumid, t.staff_id as staffId , d.items_name as monthscope,t.sumlink as sumlink,t.monthtitle as monthtitle,t.monthcontent as monthcontent,t.uploaddate as uploaddate," +
					" s.staff_name as staffName,s.staff_phone as staffPhone,s.staff_dept as staffDept,s.staff_posi as staffPosi,s.bak1 as bak1 " +
					" from HPOA_MONTHSUM_INFO t left join hpoa_staff_info s on t.staff_id=s.staff_id " +
					" left join sys_dict_items d on t.monthscope=d.items_id and d.pid=116 " +
					" where t.monthscope='" + month + "' and t.bak1 = '" + year + "'";
			sql += " order by " + sidx + " " + sord + ",t.monthsumid desc";

			 list =monthlyDAO.getVOSQL(sql, MonthlyVo.class, from, length);
			//总数据
			List allList=monthlyDAO.getVOSQL(sql, MonthlyVo.class);
			
			manageMonthsumVo.setGridDTOs(list);
			manageMonthsumVo.setPage(Integer.parseInt(page));
			manageMonthsumVo.setRecords(allList.size());
		      //总页数
		    int total = (allList.size()%Integer.parseInt(rows)==0) ? (allList.size()/Integer.parseInt(rows)) : (allList.size()/Integer.parseInt(rows)+1);
		    manageMonthsumVo.setTotal(total);
		     //查询员工总人数
		    String hql = " from HpoaStaffInfo ";
		    List stafflist = monthlyDAO.getHqlList(hql);
		    manageMonthsumVo.setStaffnumber(stafflist.size()+"");
		    
		} catch (Exception e) {
			throw new BaseException("查找当前最新提交的所有月报信息错误",e);
		}
		return manageMonthsumVo;
	}

	//查询当月未提交月报人员
	@Override
	public ManageMonthsumVo searchNotSubmitStaff(String year, String month,
			String page, String rows, int from, int length,String sidx,String sord) throws BaseException {
		// TODO Auto-generated method stub
		ManageMonthsumVo manageMonthsumVo = new ManageMonthsumVo();
		List<HpoaStaffInfo> list = null;
		try {
			//当前页数据
			String sql = "SELECT sta.staff_Name AS staffId, sta.staff_Name AS staffName, sta.staff_idcard AS staffIdcard, sta.staff_gender AS staffGender, sta.staff_photourl AS staffPhotourl, D .items_name AS staffQualid," +
					" sta.staff_marry AS staffMarry, sta.staff_address AS staffAddress, sta.staff_phone AS staffPhone, E .items_name AS staffDept, f.items_name AS staffPosi, G .items_name AS staffComp, sta.staff_userid AS staffUserid, sta.bak1 AS bak1 ,sta.bak2 AS bak2,sta.bak3 AS bak3" +
					" FROM ( SELECT * FROM ( SELECT si.STAFF_ID FROM HPOA_STAFF_INFO si MINUS ( SELECT DISTINCT s.STAFF_ID FROM HPOA_STAFF_INFO s, HPOA_MONTHSUM_INFO M WHERE s.STAFF_ID = M .STAFF_ID AND M .monthscope = '" + month + "' AND M .bak1 = '" + year + "' GROUP BY s.STAFF_ID ))" +
					" A LEFT JOIN HPOA_STAFF_INFO T ON T .STAFF_ID = A .STAFF_ID ) sta LEFT JOIN sys_dict_items D ON sta.staff_qualid = D .items_id AND D .pid = 4 LEFT JOIN sys_dict_items E ON sta.staff_dept = E .items_id AND E .pid = 1" +
					" LEFT JOIN sys_dict_items f ON sta.staff_posi = f.items_id AND f.pid = 98 LEFT JOIN sys_dict_items G ON sta.staff_comp = G .items_id AND G .pid = 108 ";
			sql += " order by  " + sidx + " " + sord + ",bak1 desc";
			
			 list =monthlyDAO.getVOSQL(sql, HpoaStaffInfo.class, from, length);
			//总数据
			List allList=monthlyDAO.getVOSQL(sql, HpoaStaffInfo.class);
			
			if (allList.size()==0) {
				String hql = " from HpoaStaffInfo ";
				 list=monthlyDAO.getHqlList(hql, from, length);
				 allList = monthlyDAO.getHqlList(hql);
			}
			manageMonthsumVo.setGridDTOsStaff(list);
			manageMonthsumVo.setPage(Integer.parseInt(page));
			manageMonthsumVo.setRecords(allList.size());
		      //总页数
		    int total = (allList.size()%Integer.parseInt(rows)==0) ? (allList.size()/Integer.parseInt(rows)) : (allList.size()/Integer.parseInt(rows)+1);
		    manageMonthsumVo.setTotal(total);
		     //查询员工总人数
		    String hql = " from HpoaStaffInfo ";
		    List stafflist = monthlyDAO.getHqlList(hql);
		    manageMonthsumVo.setStaffnumber(stafflist.size()+"");
		    
		} catch (Exception e) {
			throw new BaseException("查询当月未提交月报人员错误",e);
		}
		return manageMonthsumVo;
	}

	//查询员工最新一次提交的月报
	@Override
	public List<EachMonthlyVo> getLatestMonthly(String staffId) throws BaseException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM (select m.monthsumid as monthsumid ,m.staff_id as staffId,d.items_name as monthscope , m.sumlink as sumlink,m.monthtitle as monthtitle,m.monthcontent as monthcontent,m.uploaddate as uploaddate,m.bak1 as bak1 ,m.bak2 as bak2,m.bak3 as bak3 FROM hpoa_monthsum_info M left join sys_dict_items d on M.monthscope=d.items_id and d.pid=116 WHERE M .staff_id = '" + staffId + "' ORDER BY M .uploaddate DESC ) TT WHERE ROWNUM = 1";
		//String sql = "select * from sys_dict_items di where di.head_id=52 and di.pid  is not null";

		List <EachMonthlyVo> list = null;
		try {
			
			list  = monthlyDAO.getVOSQL(sql, EachMonthlyVo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseException("查询员工最新一次提交的月报错误",e);
		}
		
		return list;	
	}
}
