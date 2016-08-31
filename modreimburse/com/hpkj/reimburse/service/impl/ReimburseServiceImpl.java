package com.hpkj.reimburse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;


import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.StringUtilz;
import com.hpkj.message.vo.StaffsVo;
import com.hpkj.reimburse.dao.impl.ReimburseDaoImpl;
import com.hpkj.reimburse.entity.HpoaReimburseInfo;
import com.hpkj.reimburse.entity.HpoaReimburseInfos;
import com.hpkj.reimburse.service.ReimburseService;
import com.hpkj.reimburse.vo.PurchaseVo;
import com.hpkj.reimburse.vo.ReimburseListVo;
import com.hpkj.reimburse.vo.TaskInfoVo;
import com.hpkj.staff.dao.StaffDao;
import com.hpkj.staff.dao.impl.StaffDaoImpl;
import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.staff.vo.StaffDetailInfoVo;
@Service("reimburseService")
public class ReimburseServiceImpl extends BaseServiceImpl implements ReimburseService{
	@Resource
	private ReimburseDaoImpl reimburseDao;
	@Resource
	private StaffDaoImpl staffDao;
	// @Resource
	  ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	/*
	 * 获取全部员工
	 * @see com.hpkj.message.service.MessageService#getAllUsers()
	 */
	@Override
	public List<StaffsVo> getAllStaff()throws BaseException{
		try{
			List<StaffsVo> lsv=new ArrayList<StaffsVo>();
			String sql="select hsi.staff_id as staffId, " +
					"sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name as staffName " +
					"from hpoa_staff_info hsi join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
					"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
					"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id ";
			lsv=reimburseDao.getVOSQL(sql, StaffsVo.class);
			return lsv;
		}catch(Exception e){
			throw new BaseException("获取全部员工时出错",e);
		}
	}
	/* 根据员工id得到员工详细信息
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#getStaffDetailInfo(java.lang.String)
	 */
	@Override
	public StaffDetailInfoVo getStaffInfo(String staffId) throws BaseException{
		StaffDetailInfoVo staffDetailInfo;
		try {
			if (staffId.isEmpty()) {
				return null;
			}
			String sqlText = "select s.staff_id  as staffId,s.staff_name as  staffName, s.staff_idcard as staffIdcard,\n"
					+ "(case when s.staff_gender=1 then '男' when s.staff_gender=2 then '女' end) as staffGender,\n" 
					+"d.items_name as staffQualid,\n"
					+ "(case when s.staff_marry=1 then '未婚' when s.staff_marry=2 then '已婚' when s.staff_marry=3 then '离异' end) as staffMarry,\n" 
					+"s.staff_phone as staffPhone,s.staff_address as staffAddress,s.staff_photourl as staffPhotourl,\n"
					+"e.items_name as staffDept, f.items_name as staffPosi,g.items_name as staffComp\n" 
					+"from hpoa_staff_info s "
					+ "left join sys_dict_items d on s.staff_qualid=d.items_id and d.pid=4 \n"
					+ "left join sys_dict_items e on s.staff_dept=e.items_id and e.pid=1 "
					+ "left join sys_dict_items f on s.staff_posi=f.items_id and f.pid=98 "
					+ "left join sys_dict_items g on s.staff_comp=g.items_id and g.pid=108 where s.staff_id=?";
			List<StaffDetailInfoVo> li = reimburseDao.getVOSQL(sqlText, StaffDetailInfoVo.class, staffId);					
			staffDetailInfo=li.get(0);
		} catch (Exception e) {
			throw new BaseException("根据员工ID获取员工详细信息时出错", e);
		}
		return staffDetailInfo;
	}
	
	/*
	 * 提交报销申请
	 * @see com.hpkj.message.service.MessageService#sendMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int addReimburse(String staffid,HpoaReimburseInfos reimInfos,String itemlist,String reasonlist,String moneylist)throws BaseException{
//		String date=DateTimeUtil.getDate();//当前日期
		try{
			int flag=0;
			if(staffid==null){
				flag=2;
				return flag;
			}
//			reimInfos.setReinum("");
			reimburseDao.saveModel(reimInfos);
			reimburseDao.sqlcommit();
			flag=1;

			String[] itemlists=itemlist.split(",");
			String[] reasonlists=reasonlist.split(",");
			String[] moneylists=moneylist.split(",");
			String reimPid=reimInfos.getId();//报销单父id
			for(int i=0;i<itemlists.length;i++){
				HpoaReimburseInfo reim=new HpoaReimburseInfo();
				reim.setParentid(reimPid);
				reim.setReimmoney(moneylists[i]);//金额
				reim.setReimname(itemlists[i]);//项目名
				reim.setReimsum(reasonlists[i]);//摘要
				reimburseDao.saveOrUpdateModel(reim);//保存报销项目
				flag=1;
				reimburseDao.sqlcommit();

			}
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException("添加报销项目时出错",e);
		}
	}
	
	////////////////////////TODO  采购报销单  
	
	/*
	 * 提交采购报销申请
	 * @see com.hpkj.message.service.MessageService#sendMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int savePurchase(String staffid,PurchaseVo pv)throws BaseException{
//		String date=DateTimeUtil.getDate();//当前日期
		try{
			int flag=0;
			if(staffid==null){
				flag=2;
				return flag;
			}
			HpoaReimburseInfos hris=new HpoaReimburseInfos();
			hris.setReidate(pv.getReidate());
			hris.setReinum(pv.getNumber());
			hris.setReimoney(pv.getMoney());
			hris.setUppermoney(pv.getUppermoney());
			hris.setStaffid(staffid);
			hris.setEntryname(pv.getItem());
			hris.setRemark(pv.getRemark());
			hris.setReitype(1);
			hris.setIsdel(1);
			reimburseDao.saveModel(hris);
			reimburseDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException("添加采购报销单时出错",e);
		}
	}
	
	/*
	 * 获取费用报销列表
	 */
	public List<ReimburseListVo> getReimburseList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(StringUtilz.isEmpty(reimburseId)){
				return null;
			}
			List<ReimburseListVo> lblv=new ArrayList();
			String sql="select hri.reimname as reimname,hris.reidate as reidate,hris.reimoney as reimoney " +
					"from hpoa_reimburse_info hri join hpoa_reimburse_infos hris on hri.parentid=hris.id where hris.id=? order by "+sidx+" "+sord;
			lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class, from, length, reimburseId);
			return lblv;
		}catch(Exception e){
			throw new BaseException("获取报销列表时出错",e);
		}
	}
	
	/*
	 * 获取采购报销列表
	 */
	public List<ReimburseListVo> getPurchaseList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(StringUtilz.isEmpty(reimburseId)){
				return null;
			}
			List<ReimburseListVo> lblv=new ArrayList();
			String sql="select  hris.id as reimburseid , hris.reidate as reidate,hris.entryname as entryname,hris.reistatus as reistatus,hris.reimoney as reimoney " +
					"from hpoa_reimburse_infos hris left join hpoa_reimburse_info hri on hris.id=hri.parentid where hris.staffid=? and hris.reitype=1 and hris.isdel=1 order by "+sidx+" "+sord;
			lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class, from, length, reimburseId);
			if(length>0){
				lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class, from, length, reimburseId);
			}else{
				lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class,reimburseId);
			}
			return lblv;
		}catch(Exception e){
			throw new BaseException("获取采购报销列表时出错",e);
		}
	}
	
	/*
	 * 删除采购报销单
	 * @see com.hpkj.notice.service.NoticeService#delNotice(java.lang.String)
	 */
	public int delPurchase(String reimburseid)throws BaseException{
		try{
			int flag=0;
			if(reimburseid==null||reimburseid.isEmpty()){
				flag=2;
				return flag;
			}
			String[] reimburseids = reimburseid.split(",");
			String sql="update hpoa_reimburse_infos hris set hris.isdel='2' where hris.id=?";
			for(int i=0;i<reimburseids.length;i++){
				reimburseDao.excuteSql(sql, reimburseids[i]);
			}
			reimburseDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("删除报销单时出错",e);
		}
	}
	
	////////////////////////TODO  项目报销单    
	
	/*
	 * 获取项目报销单列表
	 */
	public List<ReimburseListVo> getItemList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(StringUtilz.isEmpty(reimburseId)){
				return null;
			}
			List<ReimburseListVo> lblv=new ArrayList();
			String sql="select  hris.id as reimburseid , hris.reidate as reidate,hris.entryname as entryname,hris.reimoney as reimoney " +
					"from hpoa_reimburse_infos hris left join hpoa_reimburse_info hri on hris.id=hri.parentid where hris.staffid=? and hris.reitype=2 and hris.isdel=1 order by "+sidx+" "+sord;
			lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class, from, length, reimburseId);
			if(length>0){
				lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class, from, length, reimburseId);
			}else{
				lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class,reimburseId);
			}
			return lblv;
		}catch(Exception e){
			throw new BaseException("获取采购报销列表时出错",e);
		}
	}
	
	/*
	 * 删除项目报销单
	 */
	public int delItem(String reimburseid)throws BaseException{
		try{
			int flag=0;
			if(reimburseid==null||reimburseid.isEmpty()){
				flag=2;
				return flag;
			}
			String[] reimburseids = reimburseid.split(",");
			String sql="update hpoa_reimburse_infos hris set hris.isdel='2' where hris.id=?";
			for(int i=0;i<reimburseids.length;i++){
				reimburseDao.excuteSql(sql, reimburseids[i]);
			}
			reimburseDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("删除报销单时出错",e);
		}
	}
	
	/*
	 * 提交项目报销申请
	 */
	@Override
	public int saveItem(String staffid,PurchaseVo pv)throws BaseException{
		try{
			int flag=0;
			if(staffid==null){
				flag=2;
				return flag;
			}
			HpoaReimburseInfos hris=new HpoaReimburseInfos();
			hris.setReidate(pv.getReidate());
			hris.setReinum(pv.getNumber());
			hris.setReimoney(pv.getMoney());
			hris.setUppermoney(pv.getUppermoney());
			hris.setStaffid(staffid);
			hris.setEntryname(pv.getItem());
			hris.setRemark(pv.getRemark());
			hris.setReitype(2);
			hris.setIsdel(1);
			reimburseDao.saveModel(hris);
			reimburseDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException("添加项目报销单时出错",e);
		}
	}
	
	////////////////////////TODO  费用报销单    
	
	/*
	 * 提交报销申请
	 */
	@Override
	public int addCost(String staffid,HpoaReimburseInfos reimInfos,String itemlist,String remarklist,String moneylist)throws BaseException{
		try{
			int flag=0;
			if(staffid==null){
				flag=2;
				return flag;
			}
			reimburseDao.saveModel(reimInfos);
			reimburseDao.sqlcommit();
			flag=1;
			String[] itemlists=itemlist.split(",");
			String[] remarklists=remarklist.split(",");
			String[] moneylists=moneylist.split(",");
			String reimPid=reimInfos.getId();//报销单父id
			for(int i=0;i<itemlists.length;i++){
				HpoaReimburseInfo reim=new HpoaReimburseInfo();
				reim.setParentid(reimPid);
				reim.setReimmoney(moneylists[i]);//金额
				reim.setReimname(itemlists[i]);//项目名
				reim.setReimsum(remarklists[i]);//摘要
				reimburseDao.saveOrUpdateModel(reim);//保存报销项目
				flag=1;
				reimburseDao.sqlcommit();

			}
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException("添加报销项目时出错",e);
		}
	}
	
	/*
	 * 获取费用报销单列表
	 */
	public List<ReimburseListVo> getCostList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(StringUtilz.isEmpty(reimburseId)){
				return null;
			}
			List<ReimburseListVo> lblv=new ArrayList();
			String sql="select  hris.id as reimburseid , hris.reidate as reidate,hris.entryname as entryname,hris.reimoney as reimoney " +
					"from hpoa_reimburse_infos hris where hris.staffid=? and hris.reitype=4 and hris.isdel=1 order by "+sidx+" "+sord;
			lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class, from, length, reimburseId);
			if(length>0){
				lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class, from, length, reimburseId);
			}else{
				lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class,reimburseId);
			}
			return lblv;
		}catch(Exception e){
			throw new BaseException("获取采购报销列表时出错",e);
		}
	}
	
	/*
	 * 提交费用报销单
	 */
	@Override
	public int saveCost(String staffid,HpoaReimburseInfos reimInfos,String itemlist,String remarklist,String moneylist)throws BaseException{
//		String date=DateTimeUtil.getDate();//当前日期
		try{
			int flag=0;
			if(staffid==null){
				flag=2;
				return flag;
			}
//			reimInfos.setReinum("");
			reimburseDao.saveModel(reimInfos);
			reimburseDao.sqlcommit();
			flag=1;

			String[] itemlists=itemlist.split(",");
			String[] remarklists=remarklist.split(",");
			String[] moneylists=moneylist.split(",");
			String reimPid=reimInfos.getId();//报销单父id
			for(int i=0;i<itemlists.length;i++){
				HpoaReimburseInfo reim=new HpoaReimburseInfo();
				reim.setParentid(reimPid);
				reim.setReimmoney(moneylists[i]);//金额
				reim.setReimname(itemlists[i]);//项目名
				reim.setReimsum(remarklists[i]);//摘要
				reimburseDao.saveOrUpdateModel(reim);//保存费用报销
				flag=1;
				reimburseDao.sqlcommit();
			}
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException("添加费用报销单时出错",e);
		}
	}
	
	/*
	 * 删除费用报销单
	 */
	public int delCost(String reimburseid)throws BaseException{
		try{
			int flag=0;
			if(reimburseid==null||reimburseid.isEmpty()){
				flag=2;
				return flag;
			}
			String[] reimburseids = reimburseid.split(",");
			String sql="update hpoa_reimburse_infos hris set hris.isdel=2 where hris.id=?";
			for(int i=0;i<reimburseids.length;i++){
				reimburseDao.excuteSql(sql, reimburseids[i]);
			}
			reimburseDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("删除报销单时出错",e);
		}
	}
	
	/**
	 * 获取费用报销单查看页的list
	 */
	public List<HpoaReimburseInfo> getList(String reimburseId) throws BaseException{
		try{
			if(StringUtilz.isEmpty(reimburseId)){
				return null;
			}
			List<HpoaReimburseInfo> list=new ArrayList();
			String sql=" select hri.* "+
					" from HPOA_REIMBURSE_INFO hri where hri.parentid=? ";
			list=reimburseDao.getSqlList(sql,HpoaReimburseInfo.class,reimburseId);
			return list;
		}catch (Exception e) {
			throw new BaseException("获取集合时出错",e);
		}
	}
	
	////////////////////////////DOTO 采购申请单
	
	/*
	 * 获取采购申请单列表
	 */
	public List<ReimburseListVo> getPurchReqList(String reimburseId, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(StringUtilz.isEmpty(reimburseId)){
				return null;
			}
			List<ReimburseListVo> lblv=new ArrayList();
			String sql="select  hris.id as reimburseid , hris.reidate as reidate,hris.entryname as entryname,hris.reimoney as reimoney " +
					"from hpoa_reimburse_infos hris where hris.staffid=? and hris.reitype=3 and hris.isdel=1 order by "+sidx+" "+sord;
			lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class, from, length, reimburseId);
			if(length>0){
				lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class, from, length, reimburseId);
			}else{
				lblv=reimburseDao.getVOSQL(sql, ReimburseListVo.class,reimburseId);
			}
			return lblv;
		}catch(Exception e){
			throw new BaseException("获取采购申请列表时出错",e);
		}
	}
	
	/*
	 * 删除采购申请单
	 */
	public int delPurchReq(String reimburseid)throws BaseException{
		try{
			int flag=0;
			if(reimburseid==null||reimburseid.isEmpty()){
				flag=2;
				return flag;
			}
			String[] reimburseids = reimburseid.split(",");
			String sql="update hpoa_reimburse_infos hris set hris.isdel=2 where hris.id=?";
			for(int i=0;i<reimburseids.length;i++){
				reimburseDao.excuteSql(sql, reimburseids[i]);
			}
			reimburseDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("删除采购申请单时出错",e);
		}
	}
	
	/*
	 * 提交采购申请单
	 */
	@Override
	public int savePurchReq(String staffid,HpoaReimburseInfos reimInfos,String itemlist,String numlist,String pricelist,String remarklist,String moneylist)throws BaseException{
//		String date=DateTimeUtil.getDate();//当前日期
		try{
			int flag=0;
			if(staffid==null){
				flag=2;
				return flag;
			}
//			reimInfos.setReinum("");
			reimburseDao.saveModel(reimInfos);
			reimburseDao.sqlcommit();
			flag=1;

			String[] itemlists=itemlist.split(",");
			String[] numlists=numlist.split(",");
			String[] pricelists=pricelist.split(",");
			String[] remarklists=remarklist.split(",");
			String[] moneylists=moneylist.split(",");
			String reimPid=reimInfos.getId();//报销单父id
			for(int i=0;i<itemlists.length;i++){
				HpoaReimburseInfo reim=new HpoaReimburseInfo();
				reim.setParentid(reimPid);
				reim.setReimmoney(moneylists[i]);//金额
				reim.setReimname(itemlists[i]);//项目名
				reim.setReimsum(remarklists[i]);//摘要
				reim.setBak1(numlists[i]);//数量
				reim.setBak2(pricelists[i]);//单价
				reimburseDao.saveOrUpdateModel(reim);//保存采购申请
				flag=1;
				reimburseDao.sqlcommit();
			}
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException("添加采购申请单时出错",e);
		}
	}
	/**启动流程*/
	public void StartTask(){
	    //启动流程实例  
	    ProcessInstance pi = processEngine.getRuntimeService()//  
	            .startProcessInstanceByKey("Reimburse");//使用流程定义的key的最新版本启动流程  
	    System.out.println("流程实例ID："+pi.getId());  
	    System.out.println("流程定义的ID："+pi.getProcessDefinitionId());
		
	}
	
	/**查询我的个人任务*/  
	public List<Task> findPersonalTaskList(String userName){  
	    //任务办理人  
	    /*String assignee = "赵六";*/  
	    List<Task> list = processEngine.getTaskService()//  
	                    .createTaskQuery()//  
	                    .taskAssignee(userName)//个人任务的查询  
	                    .list();  
	    if(list!=null && list.size()>0){  
	        for(Task task:list){  
	            System.out.println("任务ID："+task.getId());  
	            System.out.println("任务的办理人："+task.getAssignee());  
	            System.out.println("任务名称："+task.getName());  
	            System.out.println("任务的创建时间："+task.getCreateTime());  
	            System.out.println("流程实例ID："+task.getProcessInstanceId());  
	            System.out.println("#######################################");  
	        }  
	    }  
	    return list;
	} 
	
	/**完成任务*/  
	public void endTask(String reimoney){  
	    //任务ID  
		TaskService taskService = processEngine.getTaskService();
		Task task=taskService.createTaskQuery().singleResult();
	    String taskId = task.getId();  
	    //完成任务的同时，设置流程变量，让流程变量判断连线该如何执行  
	    Map<String, Object> variables = new HashMap<String, Object>();  
	    //其中message对应sequenceFlow.bpmn中的${message=='不重要'}，不重要对应流程变量的值  
	    
	    variables.put("reimoney", "reimoney");  
	    processEngine.getTaskService()//  
	                    .complete(taskId,variables);  
	    System.out.println("完成任务："+taskId);  
	}
	
	/*===================qzf====分割线===================================*/
	//启动流程
	@Override
	public void saveStartProcess(String reimburseId,String staffId)throws BaseException {
		// TODO Auto-generated method stub
		//1：根据报销单ID，查询报销单的对象HpoaReimburseInfos
		HpoaReimburseInfos reimburseInfos=reimburseDao.getModelById(HpoaReimburseInfos.class, reimburseId);
		//2：更新请假单的请假状态从0变成1（初始录入-->审核中）
		reimburseInfos.setReistatus(1);
		//3：使用当前对象获取到流程定义的key（对象的名称就是流程定义的key）
		String key = reimburseInfos.getClass().getSimpleName();
		/**
		 * 4：     使用流程变量设置下一个任务的办理人
		 *     userName是流程变量的名称，
		 *     获取的办理人是流程变量的值
		 */
		HpoaStaffInfo staffinfo=staffDao.getModelById(HpoaStaffInfo.class, staffId);
		String satffName=staffinfo.getStaffName();
		Map<String, Object> variables = new HashMap<String,Object>();
		variables.put("userName", satffName);//表示惟一用户
		/**
		 * 5：	(1)使用流程变量设置字符串（格式：LeaveBill.id的形式），通过设置，让启动的流程（流程实例）关联业务
   				(2)使用正在执行对象表中的一个字段BUSINESS_KEY（Activiti提供的一个字段），让启动的流程（流程实例）关联业务
		 */
		//格式：LeaveBill.id的形式（使用流程变量）
		String objId = key+"."+reimburseId;
		//variables.put("objId", objId);
		//6：使用流程定义的key，启动流程实例，同时设置流程变量，同时向正在执行的执行对象表中的字段BUSINESS_KEY添加业务数据，同时让流程关联业务
		processEngine.getRuntimeService().startProcessInstanceByKey(key,objId,variables);
		reimburseDao.saveOrUpdateModel(reimburseInfos);//更新审核状态
		//flag=1;
		reimburseDao.sqlcommit();
	}
	
	//获取个人任务表(带分页查询)
	@Override
	public List<TaskInfoVo> findTaskListByName(String username,String page, String rows, int from, int length,String order,String sort) throws BaseException {
		// TODO Auto-generated method stub
		List<Task> list = processEngine.getTaskService().createTaskQuery()//
				.taskAssignee(username)//指定个人任务查询
				.orderByTaskCreateTime().asc()//
				.listPage(from, length);
		
	
		 List<TaskInfoVo> taskInfoVolist=new ArrayList<TaskInfoVo>();
		 for (Task task : list) {
			 TaskInfoVo taskInfoVo=new  TaskInfoVo();
			 taskInfoVo.setId(task.getId());
			 taskInfoVo.setName(task.getName());
			 taskInfoVo.setAssignee(task.getAssignee());
			 taskInfoVo.setCreateTime(task.getCreateTime());
			 
			taskInfoVolist.add(taskInfoVo);
			//taskInfoVolist.addAll(taskInfoVo)
			  }
		
	
	return taskInfoVolist;
	}
	//获取个人任务表(不带分页查询)
	@Override
	public List<TaskInfoVo> findTaskListByName2(String username, String page,
			String rows, int from, int length) throws BaseException {
		// TODO Auto-generated method stub
		 List<TaskInfoVo> taskInfoVolist=new ArrayList<TaskInfoVo>();

		List<Task> list = processEngine.getTaskService().createTaskQuery()//
				.taskAssignee(username)//指定个人任务查询
				.orderByTaskCreateTime().asc()//
				.list();
		 for (Task task : list) {
			 TaskInfoVo taskInfoVo=new  TaskInfoVo();
			 taskInfoVo.setId(task.getId());
			 taskInfoVo.setName(task.getName());
			 taskInfoVo.setAssignee(task.getAssignee());
			 taskInfoVo.setCreateTime(task.getCreateTime());
			 
			taskInfoVolist.add(taskInfoVo);
	            }
		 return taskInfoVolist;

          }
	
	
	
	
	
	
	
	
	
}