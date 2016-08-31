package com.hpkj.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.core.util.EncryptUtilz;
import com.hpkj.login.entity.SysUserInfo;
import com.hpkj.login.service.LoginService;
import com.hpkj.login.vo.UserInfo;
/**
 * OA系统菜单模块，登录后的页面加载
 * @author 越蓬
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource(name="loginService")
	private LoginService loginService;
	/**
	 * 跳转到top页
	 * @return
	 */
	@RequestMapping("/turnTop")
	public String turnTop(HttpServletRequest request, HttpSession session){
		try{
			request.setAttribute("userDetailedCall", loginService.getUserDetailedCall(((UserInfo)session.getAttribute("user")).getUserID()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modlogin/top";
	}
	/**
	 * 跳转到菜单页
	 * @return
	 */
	@RequestMapping("/turnMenu")
	public String turnMenu(){
		return "/WEB-INF/jsp/modlogin/menu";
	}
	/**
	 * 跳转到主页
	 * @return
	 */
	@RequestMapping("/turnMain")
	public String turnMain(HttpServletRequest request, HttpSession session){
		try{
			SysUserInfo sui=loginService.getObjById(SysUserInfo.class, ((UserInfo)session.getAttribute("user")).getUserID());
			request.setAttribute("flag", sui.getUserPad().equals(EncryptUtilz.MD5(sui.getUserAccount())));
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modlogin/main";
	}
	/**
	 * 修改密码
	 * @param oldpwd 旧密码
	 * @param newpwd 新密码
	 * @param session
	 * @return
	 */
	@RequestMapping("/jsonmodifypwd.json")
	@ResponseBody
	public Map jsonmodifypwd(@RequestParam("oldpwd")String oldpwd,@RequestParam("newpwd")String newpwd, HttpSession session){
		Map map=new HashMap();
		try{
			String flag="0";
			String userid=((UserInfo)session.getAttribute("user")).getUserID();
			flag=loginService.modifyPWD(userid, oldpwd, newpwd);
			map.put("flag", flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 跳转到修改密码页
	 * @return
	 */
	@RequestMapping("/goModifyPWD")
	public String goModifyPWD(){
		return "/WEB-INF/jsp/modlogin/modifypwd";
	}
	/**
	 * 根据当前用户读取功能列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/jsonmenu.json")
	@ResponseBody
	public Map jsonmenu(HttpSession session){
		Map map=new HashMap();
		try{
			UserInfo ui=(UserInfo)session.getAttribute("user");
			map.put("funcList", ui.getFuncList());
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
}
