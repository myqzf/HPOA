package com.hpkj.vacation.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.login.vo.UserInfo;
//import com.hpkj.vacation.service.VacationService;


/*@Controller
@RequestMapping("/overtime")
public class OverTimeController {
	@Resource
//	private VacationService vacationService;
	@RequestMapping("goOverTimeApply")
	public String goOverTimeApply(){
		return "/WEB-INF/jsp/modvacation/overTimeApply";
	}*/
//	@RequestMapping("submitOverTimeApply.json")
//	@ResponseBody
	/*public Map submitOverTimeApply(HttpSession session,String startTime,String endTime,int overTimeType,String content){
		Map map=new HashMap();
		try{
			UserInfo user=(UserInfo)session.getAttribute("user");
//			int flag=vacationService.applyOverTime(user.getStaffID(), startTime, endTime, overTimeType, content);
//			map.put("flag", flag);
//			switch(flag){
				case 0:{
					map.put("message", "系统繁忙");
					break;
				}
				case 1:{
					map.put("messaage", "已提交申请");
					break;
				}
				case 2:{
					map.put("message", "提交失败");
					break;
				}
				case 3:{
					map.put("message", "有空缺项");
				}
			}
		}catch(Exception e){
			
		}
		return map;
	}
}*/
