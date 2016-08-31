package com.hpkj.work.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.core.util.StringUtilz;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.message.vo.UiGridVo;
import com.hpkj.work.entity.HpoaWorkAssign;
import com.hpkj.work.entity.HpoaWorkSubAssign;
import com.hpkj.work.service.WorkService;

@Controller
@RequestMapping("/workManage")
public class WorkManageController {
	@Resource(name="workService")
	private WorkService workService;
	/**
	 * 跳转至全部工作列表
	 * @return
	 */
	@RequestMapping("goAllWorkView")
	public String goReceiveWork(){
		return "/WEB-INF/jsp/modwork/allWorkView";
	}
	/**
	 * 跳转至已分配工作页
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("goWorkControl")
	public String goWorkControl(HttpServletRequest request,HttpSession session){
		try{
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modwork/workControl";
	}
	/**
	 * 获取已分配工作列表
	 * @param page
	 * @param rows
	 * @param order
	 * @param sort
	 * @param session
	 * @return
	 */
	@RequestMapping("getControlList.json")
	@ResponseBody
	public UiGridVo getControlList(int page,int rows,String order,String sort,HttpSession session){
		UiGridVo ugv=new UiGridVo();
		try{
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=workService.getWorkControlList(staffid, 0, 0, sort, order).size();
			ugv=new UiGridVo(rowCount,rows,length,workService.getWorkControlList(staffid, from, length, sort, order));
		}catch(Exception e){
			
		}
		return ugv;
	}
	/**
	 * 将工作任务设置为完结
	 * @param subAssignid 工作任务id
	 * @return
	 */
	@RequestMapping("endSubAssign.json")
	@ResponseBody
	public Map endSubAssign(String subAssignid){
		Map map=new HashMap();
		try{
			int flag=workService.endSubAssign(subAssignid);
			String message="";
			switch(flag){
				case 0:{
					message="系统繁忙";
					break;
				}
				case 1:{
					message="操作成功";
					break;
				}
				case 2:{
					message="操作失败";
					break;
				}case 3:{
					message="缺失关键参数";
					break;
				}
			}
			map.put("flag", flag);
			map.put("message", message);
		}catch(Exception e){
			
		}
		return map;
	}
	@RequestMapping("editAssign.json")
	@ResponseBody
	public Map editAssign(String assignid, String percent, String upassign){
		Map map=new HashMap();
		try{
			int flag=workService.editWork(assignid, percent);
			//如果需要添加工作任务
			if(!StringUtilz.isEmpty(upassign)){
				//工作任务分配详情，每条任务用分号(;)隔开，每个字段用逗号(,)隔开，字段顺序为：部门id、员工id、工作说明
				String works[]=upassign.split(";");
				List<HpoaWorkSubAssign> lhws=new ArrayList();
				for(int i=0;i<works.length;i++){
					//以逗号,分割字符串
					String work[]=works[i].split(",");
					HpoaWorkSubAssign hwsa=new HpoaWorkSubAssign();
					//第一个为部门id
					hwsa.setSuborgId(work[0]);
					//第二个为员工id
					hwsa.setReceverId(work[1]);
					//第三个为工作说明
					hwsa.setContent(work[2]);
					//设置进度为0%
					hwsa.setPercent("0");
					//设置状态为可用
					hwsa.setStatus("1");
					//设置开始时间为当前时间
					hwsa.setStarttime(DateTimeUtil.getDateTime());
					lhws.add(hwsa);
				}
				workService.addSubWork(lhws, assignid);
			}
			String message="";
			switch(flag){
				case 0:{
					message="系统繁忙";
					break;
				}
				case 1:{
					message="更新成功";
					break;
				}
				case 2:{
					message="更新失败";
					break;
				}case 3:{
					message="缺失关键参数";
					break;
				}
			}
			map.put("flag", flag);
			map.put("message", message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 完成工作
	 * @param assignid 工作id
	 * @param baktxt 完结备注
	 * @return
	 */
	@RequestMapping("endAssign.json")
	@ResponseBody
	public Map endAssign(String assignid,String baktxt){
		Map map=new HashMap();
		try{
			int flag=workService.endAssign(assignid,baktxt);
			String message="";
			switch(flag){
				case 0:{
					message="系统繁忙";
					break;
				}
				case 1:{
					message="操作成功";
					break;
				}
				case 2:{
					message="操作失败";
					break;
				}case 3:{
					message="缺失关键参数";
					break;
				}
			}
			map.put("flag", flag);
			map.put("message", message);
		}catch(Exception e){
			
		}
		return map;
	}
	/**
	 * 获取回复列表
	 * @param subAssignid 工作任务id
	 * @return
	 */
	@RequestMapping("getResponseList.json")
	@ResponseBody
	public Map getResponseList(String subAssignid){
		Map map=new HashMap();
		try{
			map.put("responseList", workService.getResponseList(subAssignid));
		}catch(Exception e){
			
		}
		return map;
	}
	/**
	 * 跳转至查看工作详情页
	 * @param session
	 * @param request
	 * @param assignid 工作id
	 * @return
	 */
	@RequestMapping("getWorkDetail")
	public String getWorkDetail(HttpSession session,HttpServletRequest request,String assignid){
		try{
			HpoaWorkAssign hwa=new HpoaWorkAssign();
			hwa=workService.getObjById(HpoaWorkAssign.class, assignid);
			request.setAttribute("assignWork", hwa);
			request.setAttribute("subAssign", workService.getSubAssignListAndReport(assignid));
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modwork/workDetail";
	}
	@RequestMapping("getAllWorkList.json")
	@ResponseBody
	public UiGridVo getAllWorkList(int page,int rows,String order,String sort){
		UiGridVo ugv=new UiGridVo();
		try{
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=workService.getAllWorkList(from, length, sort, order).size();
			ugv=new UiGridVo(rowCount,rows,length,workService.getAllWorkList(from, length, sort, order));
		}catch(Exception e){
			
		}
		return ugv;
	}
}
