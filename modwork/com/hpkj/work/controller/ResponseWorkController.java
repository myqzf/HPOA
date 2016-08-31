package com.hpkj.work.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.login.vo.UserInfo;
import com.hpkj.message.vo.UiGridVo;
import com.hpkj.work.service.WorkService;
import com.hpkj.work.vo.LeaderVo;

@Controller
@RequestMapping("/responseWork")
public class ResponseWorkController {
	@Resource(name="workService")
	private WorkService workService;
	/**
	 * 跳转至收到的工作任务页
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("goReceiveManage")
	public String goReceiveManage(HttpSession session,HttpServletRequest request){
		return "/WEB-INF/jsp/modwork/receiveManage";
	}
	/**
	 * 获取收到的工作任务列表
	 * @param page
	 * @param rows
	 * @param order
	 * @param sort
	 * @param session
	 * @return
	 */
	@RequestMapping("getReceiveList.json")
	@ResponseBody
	public UiGridVo getReceiveList(int page,int rows,String order,String sort,HttpSession session){
		UiGridVo ugv=new UiGridVo();
		try{
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=workService.getReceiveWorkList(staffid, 0, 0, sort, order).size();
			ugv=new UiGridVo(rowCount,rows,length,workService.getReceiveWorkList(staffid, from, length, sort, order));
		}catch(Exception e){
			
		}
		return ugv;
	}
	/**
	 * 跳转至工作汇报页
	 * @param request
	 * @param subAssignWorkid 工作任务id
	 * @return
	 */
	@RequestMapping("goResponseWork")
	public String goResponseWork(HttpServletRequest request,String subAssignWorkid){
		try{
			request.setAttribute("workDetail", workService.getReceiveWorkDetail(subAssignWorkid));
			request.setAttribute("responseList", workService.getResponseList(subAssignWorkid));
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modwork/responseWork";
	}
	/**
	 * 添加工作汇报
	 * @param session
	 * @param subAssignid 工作任务id
	 * @param content 汇报内容
	 * @param percent 进度
	 * @return
	 */
	@RequestMapping("addWorkResponse.json")
	@ResponseBody
	public Map addWorkResponse(HttpSession session, String subAssignid, String content, String percent){
		Map map=new HashMap();
		try{
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			int flag=workService.addWorkResponse(subAssignid, staffid, content, percent);
			String message="";
			switch(flag){
				case 0:{
					message="系统繁忙";
					break;
				}
				case 1:{
					message="汇报成功";
					break;
				}
				case 2:{
					message="汇报失败";
					break;
				}
				case 3:{
					message="缺失关键参数";
					break;
				}
			}
			map.put("message", message);
			map.put("flag", flag);
		}catch(Exception e){
			
		}
		return map;
	}
	/**
	 * 跳转至单独汇报页
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("goIndividualResponse")
	public String goIndividualResponse(HttpSession session,HttpServletRequest request){
		try{
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			List<LeaderVo> leaderList=workService.getLeaderList(staffid);
			request.setAttribute("leaderList", leaderList);
			request.setAttribute("defaultLeaderid", leaderList.get(0).getLeaderid());
			request.setAttribute("defaultLeaderName", leaderList.get(0).getLeaderName());
			request.setAttribute("defaultorg", leaderList.get(0).getOrgid());
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modwork/individualResponse";
	}
	/**
	 * 添加单独汇报
	 * @param session
	 * @param title 标题
	 * @param content 汇报内容
	 * @param orgid 部门id
	 * @param leaderid 领导id
	 * @return
	 */
	@RequestMapping("addIndividualResponse.json")
	@ResponseBody
	public Map addIndividualResponse(HttpSession session,String title,String content,String orgid,String leaderid){
		Map map=new HashMap();
		try{
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			int flag=workService.addIndividualResponse(staffid, orgid, leaderid, title, content);
			String message="";
			switch(flag){
				case 0:{
					message="系统繁忙";
					break;
				}
				case 1:{
					message="汇报成功";
					break;
				}
				case 2:{
					message="汇报失败";
					break;
				}
				case 3:{
					message="系统繁忙";
					break;
				}
			}
			map.put("message", message);
			map.put("flag", flag);
		}catch(Exception e){
			
		}
		return map;
	}
	@RequestMapping("goReceiveReport")
	public String goReceiveReport(){
		return "/WEB-INF/jsp/modwork/reportList";
	}
	@RequestMapping("getReportList.json")
	@ResponseBody
	public UiGridVo getReportList(HttpSession session,int page,int rows,String order,String sort){
		UiGridVo ugv=new UiGridVo();
		try{
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=workService.getReportList(staffid, 0, 0, sort, order).size();
			ugv=new UiGridVo(rowCount,rows,length,workService.getReportList(staffid, from, length, sort, order));
		}catch(Exception e){
			
		}
		return ugv;
	}
	@RequestMapping("goReportDetail")
	public String goReportDetail(HttpServletRequest request,String reportid){
		try{
			request.setAttribute("reportDetail", workService.getReportDetail(reportid));
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modwork/reportDetail";
	}
}
