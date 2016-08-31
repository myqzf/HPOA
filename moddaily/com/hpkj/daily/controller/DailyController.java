package com.hpkj.daily.controller;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.core.util.DateUtilz;
import com.hpkj.daily.service.DailyService;
import com.hpkj.daily.vo.DailyInfoVo;
import com.hpkj.login.vo.UserInfo;
/**
 * 员工日志
 * @author lipeiliu
 *
 */
@Controller
@RequestMapping("/daily")
public class DailyController {
	@Resource(name="dailyService")
	private DailyService dailyService;
	/**
	 * 跳转到日志页面
	 * @return
	 */
	@RequestMapping("/gotoDaily")
	public String gotoDaily(String daydate,HttpServletRequest request,HttpSession session){		
		String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
		String date=DateTimeUtil.getDate();//当前日期
		long staffTime;//员工入职天数
		long defaultDate;//指定显示日期
		try {
			if(!"".equals(daydate) && daydate!=null){  
				defaultDate=DateTimeUtil.diffDays(DateTimeUtil.parse(daydate,""), DateTimeUtil.parse(date,""));
				request.setAttribute("defaultTime", defaultDate);//用于设置查看日志返回后日历的显示日期
			}
			String time=dailyService.getStaffTime(staffId);	//得到员工入职日期 
			staffTime=DateTimeUtil.diffDays(DateTimeUtil.parse(time,""), DateTimeUtil.parse(date,""));//员工入职天数
			request.setAttribute("staffTime",staffTime);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/moddaily/dailyMain";
	}
	/**
	 * 跳转到添加日志页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/gotoAddDaily")
	public String gotoAddDaily(HttpServletRequest request,HttpSession session){
		DailyInfoVo dailyInfo=null;
		String plan="";
		try {
			String daydate=DateTimeUtil.getDate();//得到当前日期
			String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
			String yesterday=DateUtilz.getYestodayStringDate();//得到昨天的日期
			if(dailyService.isExist(staffId, daydate)){
				dailyInfo=dailyService.getDailyInfo(staffId, daydate);//如果员工今日日志已经存在,则获取日志内容
				request.setAttribute("dailyInfo",dailyInfo);
			}	
			if(dailyService.isExist(staffId, daydate)){
				plan=dailyService.getDailyInfo(staffId, yesterday).getPlan();//如果员工今日日志已经存在，获取昨日计划内容
			}
			request.setAttribute("planY", plan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/moddaily/addDaily";
	}
	/**
	 * 跳转到查看日志页面
	 * @param time 今日日期
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/gotoReadDaily")
	public String gotoReadDaily(String time,HttpServletRequest request,HttpSession session){
		String staffId=((UserInfo)session.getAttribute("user")).getStaffID();//得到员工id
		DailyInfoVo dailyInfo=null;
		Date day=DateTimeUtil.addDays(DateTimeUtil.parse(time, ""), -1);//得到昨日日期
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        String yesterday = sdf.format(day);//将昨日日期转换为字符串形式
		String plan="";
		try {
			if(dailyService.isExist(staffId, time)){
				dailyInfo=dailyService.getDailyInfo(staffId, time);//如果员工今日日志已经存在,则获取
				request.setAttribute("dailyInfo",dailyInfo);
			}
//			plan=dailyService.getYesterdayPlan(staffId,yesterday);//得到昨日计划内容
			
			if(dailyService.isExist(staffId, yesterday)){
				plan=dailyService.getDailyInfo(staffId, yesterday).getPlan();
			}			
			request.setAttribute("daydate", time);
			request.setAttribute("planY",plan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "/WEB-INF/jsp/moddaily/readDaily";
	}
	
}
