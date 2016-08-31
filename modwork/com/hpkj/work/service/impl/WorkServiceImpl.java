package com.hpkj.work.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.core.util.StringUtilz;
import com.hpkj.orgmanage.entity.SysOrgInfo;
import com.hpkj.work.dao.impl.WorkDaoImpl;
import com.hpkj.work.entity.HpoaWorkAssign;
import com.hpkj.work.entity.HpoaWorkResponse;
import com.hpkj.work.entity.HpoaWorkSubAssign;
import com.hpkj.work.service.WorkService;
import com.hpkj.work.vo.ControlWorkListVo;
import com.hpkj.work.vo.LeaderVo;
import com.hpkj.work.vo.ReceiveWorkDetailVo;
import com.hpkj.work.vo.ReceiveWorkListVo;
import com.hpkj.work.vo.ReportDetailVo;
import com.hpkj.work.vo.ReportListVo;
import com.hpkj.work.vo.SimpleResponseVo;
import com.hpkj.work.vo.SubAssignVo;
import com.hpkj.work.vo.SubStaffVo;
@Service("workService")
@Transactional
public class WorkServiceImpl extends BaseServiceImpl implements WorkService {
	@Resource
	private WorkDaoImpl workDao;
	/*
	 * 根据员工id获取他所管理的员工
	 * @see com.hpkj.work.service.WorkService#getSubOrgStaffList(java.lang.String)
	 */
	public List<SubStaffVo> getSubOrgStaffList(String staffid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(staffid)){
				return null;
			}
			List<SubStaffVo> li=new ArrayList();
			String sql="select hsi.staff_id as staffid, sosl.org_id as orgid, basesoi.orgname|| ' ' || hsi.staff_name as staffName " +
					"from sys_org_staff_link sosl join hpoa_staff_info hsi on sosl.staff_id=hsi.staff_id " +
					"join (select soi.org_id as orgid, soi.org_name as orgname from sys_org_info soi " +
					"join (select soi.org_id as orgid from sys_org_info soi join sys_org_staff_link sosl on soi.org_id=sosl.org_id " +
					"where sosl.staff_id=?) basesoi on soi.org_pid=basesoi.orgid) basesoi on basesoi.orgid=sosl.org_id";
			li=workDao.getVOSQL(sql, SubStaffVo.class, staffid);
			return li;
		}catch(Exception e){
			throw new BaseException("根据员工id获取他所管理的员工时出错",e);
		}
	}
	/*
	 * 根据员工id获取所在部门或部门列表
	 * @see com.hpkj.work.service.WorkService#getOrgList(java.lang.String)
	 */
	public List<SysOrgInfo> getOrgList(String staffid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(staffid)){
				return null;
			}
			List<SysOrgInfo> soili=new ArrayList();
			String sql="select soi.* from sys_org_info soi join sys_org_staff_link sosl on soi.org_id=sosl.org_id where sosl.staff_id=?";
			soili=workDao.getSqlList(sql, SysOrgInfo.class, staffid);
			return soili;
		}catch(Exception e){
			throw new BaseException("根据员工id获取所在部门或部门列表时出错",e);
		}
	}
	/*
	 * 根据部门id获取下属员工
	 * @see com.hpkj.work.service.WorkService#getSubStaffList(java.lang.String)
	 */
	public List<SubStaffVo> getSubStaffList(String orgid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(orgid)){
				return null;
			}
			List<SubStaffVo> li=new ArrayList();
			String sql="select hsi.staff_id as staffid, sosl.org_id as orgid, soi.org_name|| ' ' || hsi.staff_name as staffName from sys_org_staff_link sosl " +
					"join hpoa_staff_info hsi on sosl.staff_id=hsi.staff_id " +
					"join sys_org_info soi on sosl.org_id=soi.org_id " +
					"where soi.org_pid=?";
			li=workDao.getVOSQL(sql, SubStaffVo.class, orgid);
			return li;
		}catch(Exception e){
			throw new BaseException("根据部门id获取下属员工时出错",e);
		}
	}
	/*
	 * 添加工作任务
	 * @see com.hpkj.work.service.WorkService#addWork(java.lang.String, java.lang.String, java.lang.String, java.util.List)
	 */
	public int addWork(String orgid,String title,String content,List<HpoaWorkSubAssign> lhws)throws BaseException{
		try{
			int flag=0;
			if(lhws==null||lhws.size()==0){
				flag=3;
				return flag;
			}
			if(StringUtilz.isEmpty(orgid)||StringUtilz.isEmpty(title)){
				flag=4;
				return flag;
			}
			HpoaWorkAssign hwa=new HpoaWorkAssign();
			hwa.setOrgId(orgid);
			hwa.setTitle(title);
			hwa.setContent(content);
			hwa.setTotalpercent("0");
			hwa.setStarttime(DateTimeUtil.getDateTime());
			hwa.setStatus("1");
			workDao.saveModel(hwa);
			workDao.sqlcommit();
			String workAssignid=hwa.getAssignId();
			if(StringUtilz.isEmpty(workAssignid)){
				flag=2;
				return flag;
			}
			this.addSubWork(lhws, workAssignid);
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("添加工作任务时出错",e);
		}
	}
	/*
	 * 添加详细工作任务
	 * @see com.hpkj.work.service.WorkService#addSubWork(java.util.List)
	 */
	public int addSubWork(List<HpoaWorkSubAssign> lhws,String workAssignid)throws BaseException{
		try{
			int flag=0;
			if(lhws==null||lhws.size()==0){
				flag=3;
				return flag;
			}
			for(int i=0;i<lhws.size();i++){
				HpoaWorkSubAssign hwsa=lhws.get(i);
				hwsa.setAssignId(workAssignid);
				workDao.saveModel(hwsa);
			}
			workDao.sqlcommit();
			return flag;
		}catch(Exception e){
			throw new BaseException("添加详细工作任务时出错",e);
		}
	}
	/*
	 * 更新工作状态
	 * @see com.hpkj.work.service.WorkService#editWork(java.lang.String, java.lang.String)
	 */
	public int editWork(String workAssignid,String percent)throws BaseException{
		try{
			int flag=0;
			if(StringUtilz.isEmpty(workAssignid)||StringUtilz.isEmpty(percent)){
				flag=3;
				return flag;
			}
			String sql="update hpoa_work_assign hwa set hwa.totalpercent=? where hwa.assign_id=?";
			flag=2;
			workDao.excuteSql(sql, percent, workAssignid);
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("更新工作状态时出错",e);
		}
	}
	/*
	 * 根据员工id获取所在部门下发的任务
	 * @see com.hpkj.work.service.WorkService#getWorkControlList(java.lang.String)
	 */
	public List<ControlWorkListVo> getWorkControlList(String staffid, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(StringUtilz.isEmpty(staffid)){
				return null;
			}
			List<ControlWorkListVo> lcw=new ArrayList();
			String sql="select hwa.assign_id as assignid, soi.org_name as orgname, hwa.title as title, hwa.totalpercent || '%' as percent, " +
					"(case hwa.status when '1' then '正在进行' when '2' then  '暂停' when '3' then '终止' when '4' then '完结' end) as status, " +
					"hwa.starttime as startTime, hwa.endtime as endTime, null as compname from hpoa_work_assign hwa " +
					"join sys_org_info soi on hwa.org_id=soi.org_id join sys_org_staff_link sosl on soi.org_id=sosl.org_id where sosl.staff_id=? order by "+sidx+" "+sord;
			if(length>0){
				lcw=workDao.getVOSQL(sql, ControlWorkListVo.class, from, length, staffid);
			}else{
				lcw=workDao.getVOSQL(sql, ControlWorkListVo.class, staffid);
			}
			return lcw;
		}catch(Exception e){
			throw new BaseException("根据员工id获取所在部门下发的任务时出错",e);
		}
	}
	/*
	 * 根据员工id获取他所收到的工作任务
	 * @see com.hpkj.work.service.WorkService#getReceiveWorkList(java.lang.String, int, int, java.lang.String, java.lang.String)
	 */
	public List<ReceiveWorkListVo> getReceiveWorkList(String staffid, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(StringUtilz.isEmpty(staffid)){
				return null;
			}
			List<ReceiveWorkListVo> lrw=new ArrayList();
			String sql="select hwsa.subassign_id as subAssignid, hwa.title as title, psoi.org_name as publishOrg, soi.org_name as receiveOrg, hwsa.percent || '%' as percent, " +
					"(case hwsa.status when '1' then '正在进行' when '2' then  '暂停' when '3' then '终止' when '4' then '完结' end) as status, hwsa.starttime as startTime " +
					"from hpoa_work_sub_assign hwsa join hpoa_work_assign hwa on hwsa.assign_id=hwa.assign_id join sys_org_info psoi on hwa.org_id=psoi.org_id " +
					"join sys_org_info soi on hwsa.suborg_id=soi.org_id where hwsa.recever_id=? order by "+sidx+" "+sord;
			if(length>0){
				lrw=workDao.getVOSQL(sql, ReceiveWorkListVo.class, from, length, staffid);
			}else{
				lrw=workDao.getVOSQL(sql, ReceiveWorkListVo.class, staffid);
			}
			return lrw;
		}catch(Exception e){
			throw new BaseException("根据员工id获取他所收到的工作任务时出错",e);
		}
	}
	/*
	 * 根据分配的工作任务id查询详细信息
	 * @see com.hpkj.work.service.WorkService#getReceiveWorkDetail(java.lang.String)
	 */
	public ReceiveWorkDetailVo getReceiveWorkDetail(String subAssignid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(subAssignid)){
				return null;
			}
			String sql="select hwsa.subassign_id as subAssignid, hwa.title as title, hwa.content as content, hwsa.content as subContent, hwa.starttime as workStartTime, " +
					"hwsa.starttime as subWorkStartTime, hwa.totalpercent as workPercent, hwsa.percent as subWorkPercent, soi.org_name as publishOrgName,hwsa.status as status " +
					"from hpoa_work_assign hwa join hpoa_work_sub_assign hwsa on hwa.assign_id=hwsa.assign_id join sys_org_info soi on soi.org_id=hwa.org_id where hwsa.subassign_id=?";
			List<ReceiveWorkDetailVo> lrwd=workDao.getVOSQL(sql, ReceiveWorkDetailVo.class, subAssignid);
			ReceiveWorkDetailVo rwdv=lrwd.get(0);
			return rwdv;
		}catch(Exception e){
			throw new BaseException("根据分配的工作任务id查询详细信息时出错",e);
		}
	}
	/*
	 * 根据工作任务id获取回复列表
	 * @see com.hpkj.work.service.WorkService#getResponseList(java.lang.String)
	 */
	public List<SimpleResponseVo> getResponseList(String subAssignid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(subAssignid)){
				return null;
			}
			String sql="select hwr.content as content, hwr.response_time as responseTime, hwr.percent as percent from hpoa_work_response hwr where hwr.subassign_id=?";
			List<SimpleResponseVo> lsrv=new ArrayList();
			lsrv=workDao.getVOSQL(sql, SimpleResponseVo.class, subAssignid);
			return lsrv;
		}catch(Exception e){
			throw new BaseException("根据工作任务id获取回复列表时出错",e);
		}
	}
	/*
	 * 添加工作汇报
	 * @see com.hpkj.work.service.WorkService#addWorkResponse(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int addWorkResponse(String subAssignid, String staffid, String content, String percent)throws BaseException{
		try{
			int flag=0;
			if(StringUtilz.isEmpty(subAssignid)){
				flag=3;
				return flag;
			}
			HpoaWorkSubAssign hwsa=new HpoaWorkSubAssign();
			hwsa=workDao.getModelById(HpoaWorkSubAssign.class, subAssignid);
			HpoaWorkResponse hwr=new HpoaWorkResponse();
			if(StringUtilz.isEmpty(percent)){
				hwr.setPercent("0");
			}else{
				hwr.setPercent(percent);
				hwsa.setPercent(percent);
				workDao.updateModel(hwsa);
			}
			hwr.setSubassignId(subAssignid);
			hwr.setContent(content);
			hwr.setStaffId(staffid);
			hwr.setOrgId(hwsa.getSuborgId());
			hwr.setResponseTime(DateTimeUtil.getDateTime());
			workDao.saveModel(hwr);
			workDao.sqlcommit();
			if(StringUtilz.isEmpty(hwr.getResponseId())){
				flag=2;
			}else{
				flag=1;
			}
			return flag;
		}catch(Exception e){
			throw new BaseException("添加工作汇报时出错",e);
		}
	}
	/*
	 * 根据工作id获取分配的工作任务列表
	 * @see com.hpkj.work.service.WorkService#getSubAssignList(java.lang.String)
	 */
	public List<SubAssignVo> getSubAssignList(String assignid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(assignid)){
				return null;
			}
			List<SubAssignVo> lsav=new ArrayList();
			String sql="select hwsa.subassign_id as subAssignid,hwsa.content as content,soi.org_name as receiveOrgName," +
					"hsi.staff_name as receiveStaffName,hwsa.percent as percent,hwsa.status as status,null as resportList," +
					"(case hwsa.status when '1' then '正在进行' when '2' then  '暂停' when '3' then '终止' when '4' then '完结' end) as statusmsg,hwsa.starttime as startTime, thwr.count as count " +
					"from hpoa_work_sub_assign hwsa join hpoa_staff_info hsi on hwsa.recever_id=hsi.staff_id join sys_org_info soi on hwsa.suborg_id=soi.org_id " +
					"left join (select hwr.subassign_id as subassign, count(hwr.response_id) as count from hpoa_work_response hwr group by hwr.subassign_id) thwr on thwr.subassign=hwsa.subassign_id " +
					"where hwsa.assign_id=?";
			lsav=workDao.getVOSQL(sql, SubAssignVo.class, assignid);
			return lsav;
		}catch(Exception e){
			throw new BaseException("根据工作id获取分配的工作任务列表",e);
		}
	}
	/*
	 * 根据工作安排id修改状态,返回状态:0系统繁忙,1修改成功,2修改失败,3关键参数为空
	 * @see com.hpkj.work.service.WorkService#editSubAssignStatus(java.lang.String, java.lang.String)
	 */
	public int editSubAssignStatus(String subAssignid,String value)throws BaseException{
		try{
			int flag=0;
			if(StringUtilz.isEmpty(subAssignid)||StringUtilz.isEmpty(value)){
				flag=3;
				return flag;
			}
			String sql="update hpoa_work_sub_assign hwsa set hwsa.status=? where hwsa.subassign_id=?";
			flag=2;
			workDao.excuteSql(sql, value, subAssignid);
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("根据工作安排id修改状态时出错",e);
		}
	}
	/*
	 * 完成工作任务,返回状态:0系统繁忙,1设置成功,2设置失败,3关键参数为空
	 * @see com.hpkj.work.service.WorkService#endSubAssign(java.lang.String)
	 */
	public int endSubAssign(String subAssignid)throws BaseException{
		try{
			int flag=0;
			if(StringUtilz.isEmpty(subAssignid)){
				flag=3;
				return flag;
			}
			String sql="update hpoa_work_sub_assign hwsa set hwsa.status='4',hwsa.percent='100',hwsa.endtime=? where hwsa.subassign_id=?";
			flag=2;
			workDao.excuteSql(sql, DateTimeUtil.getDateTime(), subAssignid);
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("完成工作任务时出错",e);
		}
	}
	/*
	 * 根据工作id修改状态,返回状态:0系统繁忙,1修改成功,2修改失败,3关键参数为空
	 * @see com.hpkj.work.service.WorkService#editAssignStatus(java.lang.String, java.lang.String)
	 */
	public int editAssignStatus(String assignid,String value)throws BaseException{
		try{
			int flag=0;
			if(StringUtilz.isEmpty(assignid)||StringUtilz.isEmpty(value)){
				flag=3;
				return flag;
			}
			String sql="update hpoa_work_assign hwa set hwa.status=? where hwa.assign_id=?";
			flag=2;
			workDao.excuteSql(sql, value, assignid);
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("根据工作id修改状态时出错",e);
		}
	}
	/*
	 * 完成工作
	 * @see com.hpkj.work.service.WorkService#endAssign(java.lang.String)
	 */
	public int endAssign(String assignid,String baktxt)throws BaseException{
		try{
			int flag=0;
			if(StringUtilz.isEmpty(assignid)){
				flag=3;
				return flag;
			}
			if(baktxt==null){
				baktxt="";
			}
			String sql="update hpoa_work_assign hwa set hwa.status='4',hwa.totalpercent='100',hwa.bak1=? , " +
					"hwa.endtime=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') where hwa.assign_id=?";
			String subsql="update hpoa_work_sub_assign hwsa set hwsa.percent='100',hwsa.status='4',hwsa.endtime=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') where hwsa.assign_id=? and hwsa.status='1'";
			flag=2;
			workDao.excuteSql(subsql, assignid);
			workDao.excuteSql(sql, baktxt, assignid);
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("完成工作时出错",e);
		}
	}
	/*
	 * 根据员工id获取上级管理者列表
	 * @see com.hpkj.work.service.WorkService#getLeaderList(java.lang.String)
	 */
	public List<LeaderVo> getLeaderList(String staffid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(staffid)){
				return null;
			}
			List<LeaderVo> llv=new ArrayList();
			String sql="select distinct hsi.staff_id as leaderid, ssoi.org_name||' 向 '||psoi.org_name||' '||hsi.staff_name as leaderName, ssosl.org_id as orgid from sys_org_info psoi " +
					"join sys_org_staff_link psosl on psosl.org_id=psoi.org_id join hpoa_staff_info hsi on psosl.staff_id=hsi.staff_id " +
					"join sys_org_info ssoi on psoi.org_id=ssoi.org_pid join sys_org_staff_link ssosl on ssoi.org_id=ssosl.org_id where ssosl.staff_id=?";
			llv=workDao.getVOSQL(sql, LeaderVo.class, staffid);
			return llv;
		}catch(Exception e){
			throw new BaseException("根据员工id获取上级管理者列表时出错",e);
		}
	}
	/*
	 * 新增单独回复
	 * @see com.hpkj.work.service.WorkService#addIndividualResponse(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int addIndividualResponse(String staffid,String orgid,String leaderid,String title,String content)throws BaseException{
		try{
			int flag=0;
			if(StringUtilz.isEmpty(staffid)||StringUtilz.isEmpty(orgid)||StringUtilz.isEmpty(leaderid)||StringUtilz.isEmpty(title)||StringUtilz.isEmpty(content)){
				flag=3;
				return flag;
			}
			HpoaWorkResponse hwr=new HpoaWorkResponse();
			hwr.setOrgId(orgid);
			hwr.setStaffId(staffid);
			hwr.setReceiverId(leaderid);
			hwr.setTitle(title);
			hwr.setContent(content);
			hwr.setResponseTime(DateTimeUtil.getDateTime());
			workDao.saveModel(hwr);
			if(hwr.getResponseId()!=null){
				flag=1;
			}else{
				flag=2;
			}
			return flag;
		}catch(Exception e){
			throw new BaseException("新增单独回复时出错",e);
		}
	}
	/*
	 * 获取收到的汇报列表
	 * @see com.hpkj.work.service.WorkService#getReportList(java.lang.String, int, int, java.lang.String, java.lang.String)
	 */
	public List<ReportListVo> getReportList(String staffid, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(StringUtilz.isEmpty(staffid)){
				return null;
			}
			List<ReportListVo> lrlv=new ArrayList();
			String sql="select hwr.response_id as reportid,hsi.staff_name as reporterName,soi.org_name as reporterOrg,hwr.title as title,hwr.response_time as reportTime " +
					"from hpoa_work_response hwr join hpoa_staff_info hsi on hwr.staff_id=hsi.staff_id join sys_org_info soi on hwr.org_id=soi.org_id where hwr.receiver_id=? " +
					"order by "+sidx+" "+sord;
			lrlv=workDao.getVOSQL(sql, ReportListVo.class, from, length, staffid);
			return lrlv;
		}catch(Exception e){
			throw new BaseException("获取收到的汇报列表时出错",e);
		}
	}
	/*
	 * 获取汇报详细信息
	 * @see com.hpkj.work.service.WorkService#getReportDetail(java.lang.String)
	 */
	public ReportDetailVo getReportDetail(String reportid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(reportid)){
				return null;
			}
			ReportDetailVo rdv=new ReportDetailVo();
			String sql="select hsi.staff_name as reporterName,soi.org_name as reporterOrg,hwr.title as title,hwr.content as content,hwr.response_time as reportTime " +
					"from hpoa_work_response hwr join hpoa_staff_info hsi on hwr.staff_id=hsi.staff_id join sys_org_info soi on hwr.org_id=soi.org_id where hwr.response_id=?";
			List<ReportDetailVo> lrdv=workDao.getVOSQL(sql, ReportDetailVo.class, reportid);
			rdv=lrdv.get(0);
			return rdv;
		}catch(Exception e){
			throw new BaseException("获取汇报详细信息时出错",e);
		}
	}
	/*
	 * 获取全部工作列表
	 * @see com.hpkj.work.service.WorkService#getAllWorkList(int, int, java.lang.String, java.lang.String)
	 */
	public List<ControlWorkListVo> getAllWorkList(int from, int length, String sidx, String sord)throws BaseException{
		try{
			List<ControlWorkListVo> lcw=new ArrayList();
			String sql="select hwa.assign_id as assignid, soi.org_name as orgname, hwa.title as title, hwa.totalpercent || '%' as percent, " +
					"(case hwa.status when '1' then '正在进行' when '2' then  '暂停' when '3' then '终止' when '4' then '完结' end) as status, " +
					"hwa.starttime as startTime, hwa.endtime as endTime, csdi.items_name as compname " +
					"from hpoa_work_assign hwa join sys_org_info soi on hwa.org_id=soi.org_id " +
					"join sys_org_staff_link sosl on soi.org_id=sosl.org_id " +
					"join sys_dict_items csdi on soi.org_compid=csdi.items_id order by "+sidx+" "+sord;
			if(length>0){
				lcw=workDao.getVOSQL(sql, ControlWorkListVo.class, from, length);
			}else{
				lcw=workDao.getVOSQL(sql, ControlWorkListVo.class);
			}
			return lcw;
		}catch(Exception e){
			throw new BaseException("获取全部工作列表时出错",e);
		}
	}
	/*
	 * 根据工作id获取分配的工作任务以及回复列表
	 * @see com.hpkj.work.service.WorkService#getSubAssignListAndReport(java.lang.String)
	 */
	public List<SubAssignVo> getSubAssignListAndReport(String assignid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(assignid)){
				return null;
			}
			List<SubAssignVo> lsav=new ArrayList();
			lsav=this.getSubAssignList(assignid);
			int lsavSize=lsav.size();
			for(int i=0;i<lsavSize;i++){
				List<SimpleResponseVo> lsrv=getResponseList(lsav.get(i).getSubAssignid());
				lsav.get(i).setResportList(lsrv);
			}
			return lsav;
		}catch(Exception e){
			throw new BaseException("根据工作id获取分配的工作任务以及回复列表时出错",e);
		}
	}
}
