package com.hpkj.orgmanage.controller;

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
import com.hpkj.message.vo.StaffsVo;
import com.hpkj.orgmanage.entity.SysOrgInfo;
import com.hpkj.orgmanage.service.OrgmanageService;
import com.hpkj.staff.service.StaffService;

@Controller
@RequestMapping("/orgmanage")
public class OrgmanageController {
	@Resource(name="orgmanageService")
	OrgmanageService orgmanageService;
	@Resource(name="staffService")
	private StaffService staffService;
	/**
	 * 跳转至管理组织页面
	 * @return
	 */
	@RequestMapping("/goOrgManage")
	private String goOrgManage(){
		return "/WEB-INF/jsp/modorg/orgmanage";
	}
	/**
	 * 获取用户当前公司的组织体系
	 * @param session
	 * @return
	 */
	@RequestMapping("/getOrgList.json")
	@ResponseBody
	private Map getOrgList(HttpSession session){
		Map map=new HashMap();
		try{
			UserInfo u=(UserInfo)session.getAttribute("user");
			map.put("orgList", orgmanageService.getOrgList(u.getStaffComp()));
		}catch(Exception e){
			
		}
		return map;
	}
	/**
	 * 跳转至添加基本组织
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/goAddOrg")
	private String goAddOrg(HttpSession session,HttpServletRequest request){
		try{
			request.setAttribute("flag", "new");
			List li=staffService.getDictList(98);
			request.setAttribute("depList", staffService.getDictList(1));//pid为1时取出的是所有部门
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modorg/addbaseorg";
	}
	/**
	 * 根据公司和部门获取员工列表
	 * @param session
	 * @param depid
	 * @return
	 */
	@RequestMapping("/getStaffByDep.json")
	@ResponseBody
	private Map getStaffByDep(HttpSession session, String depid){
		Map map=new HashMap();
		try{
			String compid=String.valueOf(((UserInfo)session.getAttribute("user")).getStaffComp());
			map.put("staffList", orgmanageService.getStaffByCompDep(compid, depid));
		}catch(Exception e){
			
		}
		return map;
	}
	/**
	 * 添加基本组织
	 * @param session
	 * @param orgName 组织名
	 * @param orgAllName 组织全称
	 * @param staffids 员工id列表
	 * @param depid 部门id
	 * @return
	 */
	@RequestMapping("/addBaseOrg.json")
	@ResponseBody
	private Map addBaseOrg(HttpSession session,String orgName,String orgAllName,String staffids,String depid){
		Map map=new HashMap();
		try{
			UserInfo uif=(UserInfo)session.getAttribute("user");
			int flag=orgmanageService.saveBaseOrgInfo(orgName, orgAllName, staffids, Integer.valueOf(depid), uif.getStaffComp());
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
					message="缺失重要参数";
					break;
				}
			}
			map.put("message", message);
			map.put("rtnflag", flag);
		}catch(Exception e){
			
		}
		return map;
	}
	/**
	 * 跳转至添加下级组织
	 * @param session
	 * @param request
	 * @param orgid 上级部门id
	 * @return
	 */
	@RequestMapping("/goAddSubOrg")
	private String goAddSubOrg(HttpSession session,HttpServletRequest request,String orgid){
		try{
			SysOrgInfo soi=orgmanageService.getObjById(SysOrgInfo.class, orgid);
			UserInfo uif=(UserInfo)session.getAttribute("user");
			request.setAttribute("orgpid", soi.getOrgId());
			request.setAttribute("depid", soi.getOrgDepid());
			request.setAttribute("stafflist", orgmanageService.getStaffByCompDep(String.valueOf(soi.getOrgCompid()), String.valueOf(soi.getOrgDepid())));
			request.setAttribute("flag", "new");
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modorg/addsuborg";
	}
	/**
	 * 添加下级组织
	 * @param session
	 * @param orgName 组织名
	 * @param orgAllName 组织全称
	 * @param staffids 员工id列表
	 * @param depid 部门id
	 * @param orgpid 上级组织id
	 * @return
	 */
	@RequestMapping("/addSubOrg.json")
	@ResponseBody
	private Map addSubOrg(HttpSession session,String orgName,String orgAllName,String staffids,String depid,String orgpid){
		Map map=new HashMap();
		try{
			UserInfo uif=(UserInfo)session.getAttribute("user");
			int flag=orgmanageService.saveSubOrgInfo(orgName, orgAllName, orgpid, staffids, Integer.valueOf(depid), uif.getStaffComp());
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
				message="缺失重要参数";
				break;
			}
		}
		map.put("message", message);
		map.put("rtnflag", flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 跳转至修改组织
	 * @param request
	 * @param orgid
	 * @return
	 */
	@RequestMapping("/goEditOrg")
	private String goEditOrg(HttpServletRequest request, String orgid){
		String rtnurl="";
		try{
			SysOrgInfo soi=orgmanageService.getObjById(SysOrgInfo.class, orgid);
			List<StaffsVo> checklsv=orgmanageService.getStaffByOrg(orgid);
			List<StaffsVo> stafflist=orgmanageService.getStaffByCompDep(String.valueOf(soi.getOrgCompid()), String.valueOf(soi.getOrgDepid()));
			if(checklsv.size()>0){
				for(int i=0;i<stafflist.size();i++){
					for(int j=0;j<checklsv.size();j++){
						if(stafflist.get(i).getStaffId().equals(checklsv.get(j).getStaffId())){
							stafflist.remove(i);
							i--;
						}
					}
					if(checklsv.size()==0){
						break;
					}
				}
			}
			request.setAttribute("flag", "edit");
			request.setAttribute("stafflist", stafflist);
			request.setAttribute("checkstafflist", checklsv);
			request.setAttribute("soi", soi);
			request.setAttribute("orgid", soi.getOrgId());
			request.setAttribute("depid", soi.getOrgDepid());
			if(soi.getOrgPid()==null||"".equals(soi.getOrgPid())){
				List li=staffService.getDictList(98);
				request.setAttribute("depList", staffService.getDictList(1));//pid为1时取出的是所有部门
				rtnurl="/WEB-INF/jsp/modorg/addbaseorg";
			}else{
				request.setAttribute("orgpid", soi.getOrgPid());
				rtnurl="/WEB-INF/jsp/modorg/addsuborg";
			}
		}catch(Exception e){
			
		}
		return rtnurl;
	}
	@RequestMapping("/updateBaseOrg.json")
	@ResponseBody
	private Map updateBaseOrg(HttpSession session,String orgid,String orgName,String orgAllName,String staffids,int depid){
		Map map=new HashMap();
		try{
			UserInfo uif=(UserInfo)session.getAttribute("user");
			int flag=orgmanageService.updateBaseOrgInfo(orgid, orgName, orgAllName, staffids, depid, uif.getStaffComp());
			map.put("rtnflag", flag);
			switch(flag){
				case 0:{
					map.put("message", "系统繁忙");
					break;
				}
				case 1:{
					map.put("message", "修改成功");
					break;
				}
				case 2:{
					map.put("message", "修改失败");
					break;
				}
				case 3:{
					map.put("message", "空缺必要项");
					break;
				}
			}
		}catch(Exception e){
			
		}
		return map;
	}
	@RequestMapping("/updateSubOrg.json")
	@ResponseBody
	private Map updateSubOrg(HttpSession session,String orgid,String orgName,String orgAllName,String orgpid,String staffids,int depid){
		Map map=new HashMap();
		try{
			UserInfo uif=(UserInfo)session.getAttribute("user");
			int flag=orgmanageService.updateSubOrgInfo(orgid, orgName, orgAllName, orgpid, staffids, depid, uif.getStaffComp());
			map.put("rtnflag", flag);
			switch(flag){
				case 0:{
					map.put("message", "系统繁忙");
					break;
				}
				case 1:{
					map.put("message", "修改成功");
					break;
				}
				case 2:{
					map.put("message", "修改失败");
					break;
				}
				case 3:{
					map.put("message", "空缺必要项");
					break;
				}
			}
		}catch(Exception e){
			
		}
		return map;
	}
	@RequestMapping("/deleteOrg.json")
	@ResponseBody
	private Map deleteOrg(String orgid){
		Map map=new HashMap();
		try{
			int flag=orgmanageService.deleteOrg(orgid);
			switch(flag){
				case 0:{
					map.put("message", "系统繁忙");
					break;
				}
				case 1:{
					map.put("message", "删除成功");
					break;
				}
				case 2:{
					map.put("message", "删除失败");
					break;
				}
				case 3:{
					map.put("message", "缺失关键参数");
					break;
				}
			}
		}catch(Exception e){
			
		}
		return map;
	}
}
