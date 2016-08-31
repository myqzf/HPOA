package com.hpkj.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.util.MenuJsonCreat;
import com.hpkj.login.controller.entity.TUser;
import com.hpkj.login.controller.service.ILoginService;
import com.hpkj.login.controller.vo.FunPInfo;
import com.hpkj.login.controller.vo.FunSInfo;
import com.hpkj.login.controller.vo.T01;
import com.hpkj.login.controller.vo.UserInfo;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource(name="loginService")
	private ILoginService loginService;
	
	@RequestMapping("/goLogin")
	public String goLogin(){
		
		return "/WEB-INF/modlogin/login";
	}
	
	@RequestMapping("/checkUser")
	public String checkUser(@RequestParam("account") String account, 
							@RequestParam("password") String password, 
							Model mo, HttpSession session){
		try {
			//判断该用户用户名与密码是否相同
			boolean result = loginService.canLogin(account, password);

			if (result){
				//将用户信息放入session中
				UserInfo u = new UserInfo();
				
				u.setUsername(account);
				//session放入user对象
				session.setAttribute("user", u);
				//返回登录人员姓名
				mo.addAttribute("account", account);
				//跳转至OCR识别
				//return "/WEB-INF/modlogin/loginsuccess";
	
				List<FunPInfo> pli = new ArrayList<FunPInfo>();
				List<FunSInfo> sli = new ArrayList<FunSInfo>();
				pli = loginService.funpList();
			
				
				sli = loginService.funsList();
			
				String funJson = MenuJsonCreat.doJson(pli, sli);
				
			
				mo.addAttribute("funJson", funJson);
				
				
				//跳转至后台管理主页
				return "/admin/index";
			}else {
				//否则失败
				return "/WEB-INF/modlogin/loginfail";
			}
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}
	/**
	 * 跳转至OCR识别
	 * @return
	 * @throws BaseException
	 */
	@RequestMapping("/goOcr")
	public String goOcr() throws BaseException{
		return "/WEB-INF/modlogin/loginsuccess";
	}
	
	@RequestMapping("/testTran")
	public String testTran(String in, String out) throws BaseException{
		try {
			//测试事务是否正常
			loginService.updateTestTran(in, out, 10);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//跳转成功
		return "/WEB-INF/modlogin/loginsuccess";
	}
	
	@RequestMapping("/goTestJson")
	public String goTestJson() throws BaseException{
		return "/WEB-INF/modlogin/jsontest";
	}
	
	@RequestMapping("/testJson.json")
	@ResponseBody
	public List<TUser> testJson() throws BaseException{
		//查询Tuser表中详情
		List<TUser> ltuList = loginService.getHqlList("from TUser");
		
		return ltuList;
	}
	
	@RequestMapping("/goUeditor")
	public String goUeditor() throws BaseException{
		
		return "/WEB-INF/modueditor/Index";
	}
	/**
	 * 跳转至easyUI界面
	 * @return
	 */
	@RequestMapping("/findT01Data")
	public String findT01Data(){
		
		return "/demotable/dt";
	}
	@RequestMapping("/T01Data.json")
	@ResponseBody
	public Map T01Data(String page, String rows) throws Exception{
		
		//当前页  
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
        //每页显示条数  
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);  
        //每页的开始记录  第一页为1  第二页为number +1   
        int start = (intPage-1)*number;  

		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<T01> l01 = new ArrayList<T01>();
		
		l01 = loginService.t01List(start, number);
		map.put("rows", l01);
		map.put("total",loginService.t01Count());//total键 存放总记录数，必须的  
		map.put("page", page);
		
		return map;
	}
	
	
}
