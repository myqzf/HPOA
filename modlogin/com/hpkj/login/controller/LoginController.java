package com.hpkj.login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.core.util.Cn2Spell;
import com.hpkj.forum.entity.BbsUserInfo;
import com.hpkj.forum.service.ForumService;
import com.hpkj.login.entity.SysUserInfo;
import com.hpkj.login.service.LoginService;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.staff.service.StaffService;
import com.hpkj.staff.vo.StaffDetailInfoVo;
/**
 * OA系统登录模块
 * 登录后将保存用户session信息
 * @author 越蓬
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource(name="loginService")
	private LoginService loginService;
	@Resource(name="staffService")
	private StaffService staffService;
	@Resource(name="forumService")
	private ForumService forumService;
	
	
	public static void main(String[] args) {
		String aa = ResourceBundle.getBundle("db").getString("jdbc.username");
		System.out.println(aa);
	}
	/**
	 * 跳转到登录页
	 * @return 
	 */
	@RequestMapping("/goLogin")
	public String goLogin(){

		return "/WEB-INF/jsp/modlogin/login";
	}
	/**
	 * 跳转至主页
	 * @return
	 */
	@RequestMapping("/goMain")
	public String goMain(HttpServletRequest request,HttpSession session,String account,String password){
		try{
			UserInfo u=(UserInfo)session.getAttribute("user");
			JSONObject json=new JSONObject();
			//json=JSONObject.valueToString(u.getFuncList())
			request.setAttribute("list", u.getFuncStr());
			if(account.equals(password)){
				int status=1;//初始化密码修改
				request.setAttribute("status", status);
			}
			
		}catch(Exception e){
			
		}
		//return "/WEB-INF/jsp/modlogin/loginsuccess";
		return "/WEB-INF/jsp/modlogin/newmain";
	}
	/**
	 * 注销当前用户
	 * @return
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session){
		session.removeAttribute("user");
		return "/WEB-INF/jsp/modlogin/login";
	}
	/**
	 * 通过ajax方式验证用户名密码是否正确
	 * @param account
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/jsonCheckUser.json")
	@ResponseBody
	public Map jsonCheckUser(@RequestParam("account") String account, 
							@RequestParam("password") String password, 
							 HttpSession session){
		Map map=new HashMap();
		try{
			String flag="0";
			flag=loginService.checkUser(account, password);
			//用户名密码正确
			if("1".equals(flag)){
				UserInfo u = loginService.getUser(account, password);
				if(u != null){
					session.setAttribute("user", u);
					loginService.modifySession(u.getUserID(), session.getId());
				}
			}
			map.put("flag",flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 跳转到用户管理页
	 * @return
	 */
	@RequestMapping("/goManageUser")
	public String goManageUser(@RequestParam("staffid")String staffid,HttpServletRequest request){
		try{
			String userAccount="";
			String userName="";
			String userPwd="";
			HpoaStaffInfo hsi=new HpoaStaffInfo();
			hsi=loginService.getObjById(HpoaStaffInfo.class, staffid);
			if(hsi.getStaffUserid()!=null){
				SysUserInfo sui=new SysUserInfo();
				sui=loginService.getObjById(SysUserInfo.class, hsi.getStaffUserid());
				userAccount=sui.getUserAccount();
				userName=sui.getUserName();
				userPwd=sui.getUserPad();
			}else{
				userAccount=Cn2Spell.converterToSpell(hsi.getStaffName());
				userName=hsi.getStaffName();
			}
			request.setAttribute("flag", hsi.getStaffUserid());
			request.setAttribute("userPwd", userPwd);
			request.setAttribute("userAccount", userAccount);
			request.setAttribute("userName", userName);
			request.setAttribute("staffid", staffid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modlogin/manageuser";
	}
	/**
	 * 读取jqgrid格式所需的角色列表数据
	 * @param staffid
	 * @param currentPage
	 * @param pageSize
	 * @param sord
	 * @param sidx
	 * @return
	 */
	@RequestMapping("/jqgridjsonGetRoleList.json")
	@ResponseBody
	public Map jqgridjsonGetRoleList(String staffid,int currentPage,int pageSize,String sord,String sidx){
		Map map=new HashMap();
		try{
			int from,length,rowCount;
			from = pageSize * (currentPage - 1);
			length = pageSize;
			rowCount=loginService.getUserRole(staffid, 0, 0, sidx, sord).size();
			map.put("rowCount", rowCount);
			map.put("totalPage", (int) Math.ceil((double) rowCount / (double) pageSize));
			map.put("gridData", loginService.getUserRole(staffid, from, length, sidx, sord));
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 读取角色列表数据
	 * @param staffid
	 * @return
	 */
	@RequestMapping("/jsonGetRoleList.json")
	@ResponseBody
	public Map jsonGetRoleList(String staffid){
		Map map=new HashMap();
		try{
			map.put("roleData", loginService.getUserRole(staffid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * ajax添加用户
	 * @param staffid
	 * @param userAccount
	 * @param userName
	 * @param roles
	 * @return
	 */
	@RequestMapping("/jsonAddUser.json")
	@ResponseBody
	public Map jsonAddUser(String staffid,String userAccount,String userName,String userPwd,String roles){
		Map map=new HashMap();
		try{
			String flag="0";
			flag=loginService.addUser(staffid, userAccount, userName, userPwd, roles);
			
			//员工表的信息填到论坛人员表中，任建波
			StaffDetailInfoVo staffInfo=staffService.getStaffDetailInfo(staffid);		

			BbsUserInfo bbsUserInfo = new BbsUserInfo();
			
			bbsUserInfo.setStaffId(staffInfo.getStaffId());
			bbsUserInfo.setNickName(staffInfo.getStaffName());
			bbsUserInfo.setPsurl(staffInfo.getStaffPhotourl());
			
			forumService.saveBbsUser(bbsUserInfo);			
			//员工表的信息填到论坛人员表中结束，任建波
			
			map.put("flag", flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 修改用户
	 * @param userid
	 * @param pwd
	 * @param userName
	 * @param roles
	 * @return
	 */
	@RequestMapping("/jsonModifyUser.json")
	@ResponseBody
	public Map jsonModifyUser(String userid, String userPwd,String userName,String roles){
		Map map=new HashMap();
		try{
			String flag="0";
			flag=loginService.modifyUser(userid, userName, userPwd, roles);			
			map.put("flag", flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
}
