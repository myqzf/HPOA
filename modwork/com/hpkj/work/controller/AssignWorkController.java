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
import com.hpkj.login.vo.UserInfo;
import com.hpkj.orgmanage.entity.SysOrgInfo;
import com.hpkj.work.entity.HpoaWorkAssign;
import com.hpkj.work.entity.HpoaWorkSubAssign;
import com.hpkj.work.service.WorkService;
import com.hpkj.work.vo.SubStaffVo;

@Controller
@RequestMapping("/assignWork")
public class AssignWorkController {
	@Resource(name="workService")
	private WorkService workService;
	/**
	 * 跳转至分配任务
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("goAssignWork")
	public String goAssignWork(HttpSession session,HttpServletRequest request){
		try{
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			//获取该用户所在的部门
			List<SysOrgInfo> soili=workService.getOrgList(staffid);
			//将默认的下属列表设置为该用户第一个所在部门的下属员工
			List<SubStaffVo> subStaff=workService.getSubStaffList(soili.get(0).getOrgId());
			//该用户是否有多个部门
			request.setAttribute("flag", soili.size()>1);
			//该用户所在部门列表
			request.setAttribute("orgList", soili);
			//查到的该用户第一个所在部门为默认的部门
			request.setAttribute("defaultOrgid", soili.get(0).getOrgId());
			request.setAttribute("defaultOrgName", soili.get(0).getOrgName());
			request.setAttribute("subStaff", subStaff);
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modwork/addAssignWork";
	}
	/**
	 * 跳转至修改工作
	 * @param session
	 * @param request
	 * @param assignid
	 * @return
	 */
	@RequestMapping("editAssignWork")
	public String editAssignWork(HttpSession session,HttpServletRequest request,String assignid){
		try{
			HpoaWorkAssign hwa=new HpoaWorkAssign();
			hwa=workService.getObjById(HpoaWorkAssign.class, assignid);
			request.setAttribute("assignWork", hwa);
			request.setAttribute("subStaff", workService.getSubStaffList(hwa.getOrgId()));
			request.setAttribute("subAssign", workService.getSubAssignList(assignid));
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modwork/assignManage";
	}
	/**
	 * 根据部门id获取下属员工列表
	 * @param orgid 部门id
	 * @return
	 */
	@RequestMapping("getSubStaff.json")
	@ResponseBody
	public Map getSubStaff(String orgid){
		Map map=new HashMap();
		try{
			map.put("subStaff", workService.getSubStaffList(orgid));
		}catch(Exception e){
			
		}
		return map;
	}
	/**
	 * 
	 * @param upassign 工作任务组合字符串
	 * @param orgid 部门id
	 * @param title 工作标题
	 * @param content 工作内容
	 * @return
	 */
	@RequestMapping("addWork.json")
	@ResponseBody
	public Map addWork(String upassign, String orgid, String title, String content){
		Map map=new HashMap();
		try{
			//工作分配详情，每条任务用分号(;)隔开，每个字段用逗号(,)隔开，字段顺序为：部门id、员工id、工作说明
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
				hwsa.setStarttime(DateTimeUtil.getDateTime());
				lhws.add(hwsa);
			}
			int flag=workService.addWork(orgid, title, content, lhws);
			String message="";
			switch(flag){
				case 0:{
					message="系统繁忙";
					break;
				}
				case 1:{
					message="添加成功";
					break;
				}
				case 2:{
					message="添加失败";
					break;
				}
				case 3:{
					message="请分配任务";
					break;
				}
				case 4:{
					message="请输入标题";
					break;
				}
			}
			map.put("message", message);
			map.put("flag", flag);
		}catch(Exception e){
			
		}
		return map;
	}
}
