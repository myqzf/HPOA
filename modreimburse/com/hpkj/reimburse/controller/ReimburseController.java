package com.hpkj.reimburse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.core.exception.BaseException;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.message.vo.UiGridVo;
import com.hpkj.reimburse.entity.HpoaReimburseInfo;
import com.hpkj.reimburse.entity.HpoaReimburseInfos;
import com.hpkj.reimburse.service.ReimburseService;
import com.hpkj.reimburse.vo.PurchaseVo;
import com.hpkj.reimburse.vo.TaskInfoVo;
import com.hpkj.reimburse.vo.TaskListVo;
import com.hpkj.staff.vo.StaffDetailInfoVo;

@Controller
@RequestMapping("/reimburse")
public class ReimburseController {
	@Resource(name="reimburseService")
	private ReimburseService reimburseService;
	/**
	 * 新增费用报销单
	 * @return
	 */
	@RequestMapping("/gotoAdd")
	public String gotoAdd(HttpSession session,HttpServletRequest request){		
		try{
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
			request.setAttribute("staff",staff);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/addReimburse";
	}
	
	/**
	 * 修改报销单
	 * @return
	 */
	@RequestMapping("/gotoUpdate")
	public String gotoUpdate(){
		return "/WEB-INF/jsp/modreimburse/update";
	}
	
	/**
	 * 提交报销申请
	 * @param reimInfos
	 * @param itemlist
	 * @param reasonlist
	 * @param moneylist
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveReimburse.json")
	@ResponseBody
	private Map saveReimburse(HpoaReimburseInfos reimInfos,String itemlist,String reasonlist,String moneylist,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(reimburseService.addReimburse(((UserInfo)session.getAttribute("user")).getStaffID(),reimInfos,itemlist,reasonlist,moneylist)){
				case 0:{
					flagmsg="系统出错";
					break;
				}
				case 1:{
					flagmsg="提交成功";
					break;
				}
				case 2:{
					flagmsg="提交失败";
					break;
				}
			}
			map.put("flagmsg", flagmsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 跳转至全部报销信息列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/gotoListReimburse")
	public String gotoListReimburse(HttpServletRequest request){
		return "/WEB-INF/jsp/modreimburse/showReimburseList";
	}
	
	@RequestMapping("getReimburseList.json")
	@ResponseBody
	public UiGridVo getReimburseList(HttpSession session,int page,int rows,String order,String sort){
		UiGridVo ugv=new UiGridVo();
		try{
			String reimburseId=((UserInfo)session.getAttribute("user")).getStaffID();
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=reimburseService.getReimburseList(reimburseId, 0, 0, sort, order).size();
			ugv=new UiGridVo(rowCount,rows,length,reimburseService.getReimburseList(reimburseId, from, length, sort, order));
		}catch(Exception e){
			e.printStackTrace();
		}
		return ugv;
	}
	
	////////////////////////////TODO  采购报销单
	
	/**
	 * 跳转至采购报销单列表页
	 * @return
	 */
	@RequestMapping("goPurchaseList")
	private String goPurchaseList(HttpServletRequest request){
		return "/WEB-INF/jsp/modreimburse/purchaseList";
	}
	
	/**
	 * 获取分页后采购报销单列表
	 * @author wangang
	 * @return
	 */
	@RequestMapping("getUiGridPurchase.json")
	@ResponseBody
	private UiGridVo getUiGridPurchase(int page,int rows,String order,String sort,HttpSession session){
		UiGridVo uiGridVo=null;
		try{
			String reimburseId=((UserInfo)session.getAttribute("user")).getStaffID();
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=reimburseService.getPurchaseList(reimburseId,0, 0, sort, order).size();
			uiGridVo=new UiGridVo(rowCount,rows,length,reimburseService.getPurchaseList(reimburseId,from, length, sort, order));
		}catch(Exception e){
			e.printStackTrace();
		}
		return uiGridVo;
	}
	
	/**
	 * 跳转至添加采购报销单
	 * @author wangang
	 * @return
	 */
	@RequestMapping("goCreatePurchase")
	private String goCreatePurchase(HttpSession session,HttpServletRequest request){
		try{
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
			request.setAttribute("staff",staff);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/addPurchase";
	}
	
	/**
	 * 采购报销单查看页
	 * @author wangang
	 * @return
	 */
	@RequestMapping("findPurchase")
	private String findPurchase(String reimburseId,HttpServletRequest request,HttpSession session){
		try{
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
			request.setAttribute("staff",staff);
			HpoaReimburseInfos hris=(HpoaReimburseInfos)reimburseService.getObjById(HpoaReimburseInfos.class,reimburseId);
			request.setAttribute("reidate", hris.getReidate());
			request.setAttribute("entryname", hris.getEntryname());
			request.setAttribute("reimoney", hris.getReimoney());
			request.setAttribute("uppermoney", hris.getUppermoney());
			request.setAttribute("reinum",hris.getReinum());
			request.setAttribute("remark",hris.getRemark());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/viewPurchase";
	}
	
	/**
	 * 删除采购报销单
	 */
	@RequestMapping("delPurchase.json")
	@ResponseBody
	private Map delPurchase(String reimburseid){
		Map map=new HashMap();
		try{
			String message="";
			switch(reimburseService.delPurchase(reimburseid)){
				case 0: {
					message="系统繁忙";
					break;
				}
				case 1: {
					message="删除成功";
					break;
				}
				case 2: {
					message="删除失败";
					break;
				}
			}
			map.put("flagmsg", message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 提交采购报销申请
	 * @param reimInfos
	 * @param itemlist
	 * @param reasonlist
	 * @param moneylist
	 * @param session
	 * @return
	 */
	@RequestMapping("/savePurchase.json")
	@ResponseBody
	private Map savePurchase(PurchaseVo pv,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(reimburseService.savePurchase(((UserInfo)session.getAttribute("user")).getStaffID(),pv)){
				case 0:{
					flagmsg="0";//系统出错
					break;
				}
				case 1:{
					flagmsg="1";//提交成功
					break;
				}
				case 2:{
					flagmsg="2";//提交失败
					break;
				}
			}
			map.put("flagmsg", flagmsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	///////////////////////////TODO 项目报销单    
	/**
	 * 获取分页后项目报销单列表
	 * @author wangang
	 * @return
	 */
	@RequestMapping("getUiGridItem.json")
	@ResponseBody
	private UiGridVo getUiGridItem(int page,int rows,String order,String sort,HttpSession session){
		UiGridVo uiGridVo=null;
		try{
			String reimburseId=((UserInfo)session.getAttribute("user")).getStaffID();
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=reimburseService.getItemList(reimburseId,0, 0, sort, order).size();
			uiGridVo=new UiGridVo(rowCount,rows,length,reimburseService.getItemList(reimburseId,from, length, sort, order));
		}catch(Exception e){
			e.printStackTrace();
		}
		return uiGridVo;
	}
	
	/**
	 * 跳转至项目报销单列表页
	 * @return
	 */
	@RequestMapping("goItemList")
	private String goItemList(HttpServletRequest request){
		return "/WEB-INF/jsp/modreimburse/itemList";
	}
	
	/**
	 * 跳转至添加项目报销单
	 * @author wangang
	 * @return
	 */
	@RequestMapping("goCreateItem")
	private String goCreateItem(HttpSession session,HttpServletRequest request){
		try{
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
			request.setAttribute("staff",staff);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/addItem";
	}
	
	/**
	 * 删除项目报销单
	 */
	@RequestMapping("delItem.json")
	@ResponseBody
	private Map delItem(String reimburseid){
		Map map=new HashMap();
		try{
			String message="";
			switch(reimburseService.delItem(reimburseid)){
				case 0: {
					message="系统繁忙";
					break;
				}
				case 1: {
					message="删除成功";
					break;
				}
				case 2: {
					message="删除失败";
					break;
				}
			}
			map.put("flagmsg", message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 项目报销单查看页
	 * @author wangang
	 * @return
	 */
	@RequestMapping("findItem")
	private String findItem(String reimburseId,HttpServletRequest request,HttpSession session){
		try{
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
			request.setAttribute("staff",staff);
			HpoaReimburseInfos hris=(HpoaReimburseInfos)reimburseService.getObjById(HpoaReimburseInfos.class,reimburseId);
			request.setAttribute("reidate", hris.getReidate());
			request.setAttribute("entryname", hris.getEntryname());
			request.setAttribute("reimoney", hris.getReimoney());
			request.setAttribute("uppermoney", hris.getUppermoney());
			request.setAttribute("reinum",hris.getReinum());
			request.setAttribute("remark",hris.getRemark());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/viewItem";
	}
	
	/**
	 * 提交项目报销申请
	 * @param reimInfos
	 * @param itemlist
	 * @param reasonlist
	 * @param moneylist
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveItem.json")
	@ResponseBody
	private Map saveItem(PurchaseVo pv,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(reimburseService.saveItem(((UserInfo)session.getAttribute("user")).getStaffID(),pv)){
				case 0:{
					flagmsg="0";//系统出错
					break;
				}
				case 1:{
					flagmsg="1";//提交成功
					break;
				}
				case 2:{
					flagmsg="2";//提交失败
					break;
				}
			}
			map.put("flagmsg", flagmsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	///////////////////////////TODO 费用报销单    
	
	/**
	 * 跳转至费用报销单列表页
	 * @return
	 */
	@RequestMapping("goCostList")
	private String goCostList(HttpServletRequest request){
		try{
			int type=4;
			request.setAttribute("type", type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/costList";
	}
	
	/**
	 * 跳转至添加费用报销单
	 * @author wangang
	 * @return
	 */
	@RequestMapping("goCreateCost")
	private String goCreateCost(HttpSession session,HttpServletRequest request){
		try{
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
			request.setAttribute("staff",staff);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/addCost";
	}
	
	/**
	 * 提交费用报销单
	 * @param reimInfos
	 * @param itemlist
	 * @param remarklist
	 * @param moneylist
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveCost.json")
	@ResponseBody
	private Map saveCost(HpoaReimburseInfos reimInfos,String itemlist,String remarklist,String moneylist,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(reimburseService.saveCost(((UserInfo)session.getAttribute("user")).getStaffID(),reimInfos,itemlist,remarklist,moneylist)){
				case 0:{
					flagmsg="0";//系统出错
					break;
				}
				case 1:{
					flagmsg="1";//提交成功
					break;
				}
				case 2:{
					flagmsg="2";//提交失败
					break;
				}
			}
			map.put("flagmsg", flagmsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取分页后费用报销单列表
	 * @author wangang
	 * @return
	 */
	@RequestMapping("getUiGridCost.json")
	@ResponseBody
	private UiGridVo getUiGridCost(int page,int rows,String order,String sort,HttpSession session){
		UiGridVo uiGridVo=null;
		try{
			String reimburseId=((UserInfo)session.getAttribute("user")).getStaffID();
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=reimburseService.getCostList(reimburseId,0, 0, sort, order).size();
			uiGridVo=new UiGridVo(rowCount,rows,length,reimburseService.getCostList(reimburseId,from, length, sort, order));
		}catch(Exception e){
			e.printStackTrace();
		}
		return uiGridVo;
	}
	
	/**
	 * 删除费用报销单
	 */
	@RequestMapping("delCost.json")
	@ResponseBody
	private Map delCost(String reimburseid){
		Map map=new HashMap();
		try{
			String message="";
			switch(reimburseService.delCost(reimburseid)){
				case 0: {
					message="系统繁忙";
					break;
				}
				case 1: {
					message="删除成功";
					break;
				}
				case 2: {
					message="删除失败";
					break;
				}
			}
			map.put("flagmsg", message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 费用报销单查看页
	 * @author wangang
	 * @return
	 */
	@RequestMapping("findCost")
	private String findCost(String reimburseId,HttpServletRequest request,HttpSession session){
		try{
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
			request.setAttribute("staff",staff);
			HpoaReimburseInfos hris=(HpoaReimburseInfos)reimburseService.getObjById(HpoaReimburseInfos.class,reimburseId);
			request.setAttribute("remark",hris.getRemark());
			request.setAttribute("reidate", hris.getReidate());
			request.setAttribute("reinum",hris.getReinum());
			request.setAttribute("reimoney", hris.getReimoney());
			request.setAttribute("uppermoney", hris.getUppermoney());
			List<HpoaReimburseInfo> list=reimburseService.getList(reimburseId);
			request.setAttribute("list",list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/viewCost";
	}
	
	///////////////////////////TODO 采购申请单   
	
	/**
	 * 跳转至采购申请单列表页
	 * @return
	 */
	@RequestMapping("goPurchReqList")
	private String goPurchReqList(HttpServletRequest request){
		try{
			int type=3;
			request.setAttribute("type", type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/purchReqList";
	}
	
	/**
	 * 获取分页后采购申请单列表
	 * @author wangang
	 * @return
	 */
	@RequestMapping("getUiGridPurchReq.json")
	@ResponseBody
	private UiGridVo getUiGridPurchReq(int page,int rows,String order,String sort,HttpSession session){
		UiGridVo uiGridVo=null;
		try{
			String reimburseId=((UserInfo)session.getAttribute("user")).getStaffID();
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=reimburseService.getPurchReqList(reimburseId,0, 0, sort, order).size();
			uiGridVo=new UiGridVo(rowCount,rows,length,reimburseService.getPurchReqList(reimburseId,from, length, sort, order));
		}catch(Exception e){
			e.printStackTrace();
		}
		return uiGridVo;
	}
	
	/**
	 * 跳转至添加采购申请单
	 * @author wangang
	 * @return
	 */
	@RequestMapping("goCreatePurchReq")
	private String goCreatePurchReq(HttpSession session,HttpServletRequest request){
		try{
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
			request.setAttribute("staff",staff);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/addPurchReq";
	}
	
	/**
	 * 删除采购申请单
	 */
	@RequestMapping("delPurchReq.json")
	@ResponseBody
	private Map delPurchReq(String reimburseid){
		Map map=new HashMap();
		try{
			String message="";
			switch(reimburseService.delPurchReq(reimburseid)){
				case 0: {
					message="系统繁忙";
					break;
				}
				case 1: {
					message="删除成功";
					break;
				}
				case 2: {
					message="删除失败";
					break;
				}
			}
			map.put("flagmsg", message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 采购申请单查看页
	 * @author wangang
	 * @return
	 */
	@RequestMapping("findpurchReq")
	private String findpurchReq(String reimburseId,HttpServletRequest request,HttpSession session){
		try{
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
			request.setAttribute("staff",staff);
			HpoaReimburseInfos hris=(HpoaReimburseInfos)reimburseService.getObjById(HpoaReimburseInfos.class,reimburseId);
			request.setAttribute("remark",hris.getRemark());
			request.setAttribute("reidate", hris.getReidate());
			request.setAttribute("reinum",hris.getReinum());
			request.setAttribute("reimoney", hris.getReimoney());
			request.setAttribute("uppermoney", hris.getUppermoney());
			List<HpoaReimburseInfo> list=reimburseService.getList(reimburseId);
			request.setAttribute("list",list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modreimburse/viewPurchReq";
	}
	
	/**
	 * 提交采购申请单
	 * @param reimInfos
	 * @param itemlist
	 * @param remarklist
	 * @param moneylist
	 * @param session
	 * @return
	 */
	@RequestMapping("/savePurchReq.json")
	@ResponseBody
	private Map savePurchReq(HpoaReimburseInfos reimInfos,String itemlist,String numlist,String pricelist,String remarklist,String moneylist,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(reimburseService.savePurchReq(((UserInfo)session.getAttribute("user")).getStaffID(),reimInfos,itemlist,numlist,pricelist,remarklist,moneylist)){
				case 0:{
					flagmsg="0";//系统出错
					break;
				}
				case 1:{
					flagmsg="1";//提交成功
					break;
				}
				case 2:{
					flagmsg="2";//提交失败
					break;
				}
			}
			map.put("flagmsg", flagmsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	
	/*===========================================================*/
	/**
	 * 启动流程
	 * @return
	 */
	@RequestMapping("startProcess")
	private String startProcess(String reimburseId,HttpSession session,HttpServletRequest request){
		String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
		//更新请假状态，启动流程实例，让启动的流程实例关联业务
		try {
			reimburseService.saveStartProcess(reimburseId,staffId);
			
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modprocessDefinition/taskManageList";//跳转到个人任务管理页面
	}
	
	/**
	 * 个人任务管理首页显示
	 * @param request
	 * @return
	 */
	@RequestMapping("/gotoListTaskManage")
	public String gotoListTaskManage(HttpServletRequest request){
		return "/WEB-INF/jsp/modprocessDefinition/taskManageList";
	}
	/**
	 * 个人任务管理首页显示
	 * @return
	 */
	@RequestMapping("listTask.json")
	@ResponseBody
	public  TaskListVo listTask(String page,String rows,String order,String sort,HttpSession session,HttpServletResponse response){
		TaskListVo taskListVo =new TaskListVo();
		//1：从Session中获取当前用户名
		String username=((UserInfo)session.getAttribute("user")).getUserName();//得到员工id
		List<TaskInfoVo> taskInfoVolist=new ArrayList<TaskInfoVo>();
		List<TaskInfoVo> taskInfoVolistall=new ArrayList<TaskInfoVo>();
		try {			
			//list=staffService.getSearchList(staff,sidx,sord, 0,0);
			//list1=staffService.getSearchList(staff,sidx,sord,pageSize * (currentPage - 1), pageSize);
			int from = Integer.parseInt(rows) * (Integer.parseInt(page) - 1);
			int length = Integer.parseInt(rows);
			//2：使用当前用户名查询正在执行的任务表，获取当前任务的集合List<Task>
			 taskInfoVolist = reimburseService.findTaskListByName(username,page, rows, from, length,order,sort);
			 taskInfoVolistall=reimburseService.findTaskListByName2(username,page, rows, 0, 0);
			int rowCount=taskInfoVolistall.size();
			taskListVo.setRows(taskInfoVolist);
			taskListVo.setRecords(rowCount);
			taskListVo.setTotal(rowCount);
			taskListVo.setPage(Integer.parseInt(page));
			
			
			//map.put("currentPage",currentPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return taskListVo;
	}
 
}
