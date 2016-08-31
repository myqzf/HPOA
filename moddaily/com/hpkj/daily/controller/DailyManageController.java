package com.hpkj.daily.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.daily.entity.HpoaDailyInfo;
import com.hpkj.daily.service.DailyService;
import com.hpkj.daily.vo.DailyInfoVo;
import com.hpkj.login.vo.UserInfo;
/**
 * 员工信息管理
 * @author lipeiliu
 *
 */
@Controller
@RequestMapping("/dailyManage")
public class DailyManageController {
	@Resource(name="dailyService")
	private DailyService dailyService;
	/**
	 * 添加员工信息
	 * @param staff
	 * @param response
	 */
	@RequestMapping("/addDaily")
	public void addStaff(HpoaDailyInfo dailyInfo,HttpServletResponse response,HttpSession session){
		String staffId=((UserInfo) session.getAttribute("user")).getStaffID();
		dailyInfo.setStaffId(staffId);
		String daydate=DateTimeUtil.getDate();
		DailyInfoVo daily=null;
		response.setContentType("text/html;charset=utf-8");
		String result = "{\"result\":\"2\"}";		
		try {
			if(dailyService.isExist(staffId, daydate)){
			daily=dailyService.getDailyInfo(staffId, daydate);
				dailyInfo.setDailyId(daily.getDailyId());
				dailyInfo.setStaffId(staffId);
			}
			switch(dailyService.addDaily(dailyInfo)){
				case 0:{
					result = "{\"result\":\"0\"}";//系统出错
					break;
				}
				case 1:{
					result = "{\"result\":\"1\"}";//添加成功
					break;
				}
				case 2:{
					result = "{\"result\":\"2\"}";//添加失败
					break;
				}
				case 3:{
					result = "{\"result\":\"3\"}";//未填写内容
					break;
				}
			}
			response.setContentType("application/json");
			response.getWriter().write(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/checkDaily")
	public void checkDaily(String time ,HttpServletResponse response,HttpSession session){
		String staffId=((UserInfo) session.getAttribute("user")).getStaffID();
		response.setContentType("text/html;charset=utf-8");
		String result = "{\"result\":\"0\"}";		
		try {
			if(dailyService.isExist(staffId, time)){
				result = "{\"result\":\"1\"}";//存在
			}
			
			response.setContentType("application/json");
			response.getWriter().write(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/timeList")
	public@ResponseBody Map<String,Object> timeList(HttpServletResponse response,HttpSession session){
		Map timeList=new HashMap();
		String staffId=((UserInfo) session.getAttribute("user")).getStaffID();		
		try {
			timeList.put("dateList", dailyService.timeList(staffId));			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeList;
	}
}
