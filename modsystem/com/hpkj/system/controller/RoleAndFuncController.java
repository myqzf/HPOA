package com.hpkj.system.controller;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.hpkj.core.exception.BaseException;
import com.hpkj.system.entity.SysFuncInfo;
import com.hpkj.system.entity.SysRoleInfo;
import com.hpkj.system.service.RoleAndFuncService;
import com.hpkj.system.vo.SysRoleInfoVo;

@Controller
@RequestMapping("/system")
public class RoleAndFuncController {
	
	@Resource(name="roleAndFuncService")
	private RoleAndFuncService roleAndFuncService;

	
	
	/**
	 * 查询角色列表
	 */
	@RequestMapping("/getRoleInfo")
	public String getRoleInfo(HttpServletRequest request){
		List<SysRoleInfo> roleList;	
		
		try {
			
			roleList = roleAndFuncService.searchRoleList();
		
			request.setAttribute("roleList", roleList);
			
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modsystem/roleList";
		
	}
	
	/**
	 * 跳转到新增角色页面
	 * @return
	 */
	@RequestMapping("/toSaveRole")
	public String toSaveRole(){
		return "/WEB-INF/jsp/modsystem/addRole";
	}
	
	
	
	/**
	 * 新增角色
	 * @return
	 */
	@RequestMapping("/saveRole")
	@ResponseBody
	public String saveRole(String roleName,String roleDesc){
		String rtnFlg;
		try {
			rtnFlg = roleAndFuncService.saveRoleInfo(roleName, roleDesc);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
		return rtnFlg;
	}
	
	
	
	/**
	 * 跳转到jq查询角色列表
	 * @return
	 */
	@RequestMapping("/toRoleList")
	public String toRoleList(){
		return "/WEB-INF/jsp/modsystem/roleList";
	}
	
	
	
	/*
	 * jq查询角色列表
	 */
	@RequestMapping("/searchRoleList")
	@ResponseBody
	public SysRoleInfoVo searchRoleList(SysRoleInfo sysRoleInfo,String page,String rows){

		SysRoleInfoVo sysRoleInfoVo = new SysRoleInfoVo();
		
		int from = Integer.parseInt(rows) * (Integer.parseInt(page) - 1);
		int length = Integer.parseInt(rows);
		
		try {			
			 sysRoleInfoVo = roleAndFuncService.searchRoleList(sysRoleInfo,page, rows, from, length);		
			
		} catch (BaseException e) {			
			e.printStackTrace();
		}
		
		return sysRoleInfoVo;		
	}
	
	
	
	/**
	 * 跳转到修改角色页面
	 * @return
	 */
	@RequestMapping("/toModifyRole")
	public String toModifyRole(String roleId,HttpServletRequest request){
		SysRoleInfo sri;
		try {
			sri = roleAndFuncService.searchRoleInfoById(roleId);
			
			request.setAttribute("sri", sri);
		} catch (Exception e) {
			
		}
		return "/WEB-INF/jsp/modsystem/modifyRole";
	}
	
	
	/**
	 * 修改角色信息
	 * @return
	 */
	@RequestMapping("/modifyRole")
	@ResponseBody
	public String modifyRole(String roleId,String roleName,String roleDesc){
		String rtnFlg;
		try {
			rtnFlg = roleAndFuncService.modifyRoleInfo(roleId, roleName, roleDesc);
		} catch (Exception e) {
			
			rtnFlg= "2";
		}
		return rtnFlg;
	}
	
	
	
	
	
	
	/**
	 * 删除角色
	 * @return 
	 * @return
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public String deleteRole(String roleId){
		String rtnFlg;
		try {
			roleAndFuncService.deleteRole(roleId);
			
			System.out.println("roleId:"+roleId);
			
			rtnFlg="1";
		} catch (Exception e) {
			
			rtnFlg = "2";
		}
		return rtnFlg;
	}
	
	
	
	/**
	 * 跳转到权限列表
	 * @return
	 */
	@RequestMapping("/toFuncList")
	public String toFuncList(){
		return "/WEB-INF/jsp/modsystem/funcList";
		
	}
	
	
	/**
	 * 跳转到添加主要权限页面
	 */
	@RequestMapping("/toAddMainFunc")
	public String toAddMainFunc(HttpServletRequest request){
				
		return "/WEB-INF/jsp/modsystem/addMainFunc";
		
	}
	
	
	
	
	
	
	/**
	 * 跳转到添加权限页面
	 */
	@RequestMapping("/toAddFunc")
	public String toAddFunc(String funcPid,HttpServletRequest request){
		
		System.out.println(funcPid);
		
		request.setAttribute("funcPid", funcPid);
		
		return "/WEB-INF/jsp/modsystem/addFunc";
		
	}
	
	
	
	/**
	 * 查询权限列表
	 */
	
	@RequestMapping("/getFuncList")
	@ResponseBody
	public List<SysFuncInfo> getFuncList(){
		
		List<SysFuncInfo> funcList = null;
		
		try {
			funcList = roleAndFuncService.searchFuncList();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return funcList;
		
	}
	
	
	/**
	 * 跳转到分配权限页面
	 * @return
	 */
	@RequestMapping("/toRoleFuncList")
	public String toRoleFuncList(String roleId,HttpServletRequest request){
		
		request.setAttribute("roleId", roleId);
		
		return "/WEB-INF/jsp/modsystem/disFunc";
	}
		

	
	
	/**
	 * 分配权限
	 * @return
	 */
	@RequestMapping("/addRoleFunc")
	@ResponseBody
	public String addRoleFunc(String roleId,String funcArray){
		String rtnFlg = null;
		
		try {
			rtnFlg = roleAndFuncService.updateRoleFuncLink(roleId, funcArray);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
		return rtnFlg;
	}
	
	
	
	
	/**
	 * 添加主要权限
	 */
	
	@RequestMapping("/addMainFunc")
	@ResponseBody
    public String addMainFunc(String funcName,String funcDesc,String funcUrl,int isUsed){
    	
		String rtnFlg;
		
		try {
			rtnFlg = roleAndFuncService.saveMainFunc(funcName, funcDesc, funcUrl, isUsed);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
    	return rtnFlg;
    }
	
	
	
	
	/**
	 * 添加权限
	 * @param funcName
	 * @param funcDesc
	 * @param funcUrl
	 * @param funcPid
	 * @param isUsed
	 * @param isLeaf
	 * @return
	 */
	@RequestMapping("/addFuncInfo")
	@ResponseBody
    public String addFuncInfo(String funcName,String funcDesc,String funcUrl,String funcPid,int isUsed,int isLeaf){
    	
		String rtnFlg;
		
		try {
			rtnFlg = roleAndFuncService.saveFuncInfo(funcName, funcDesc, funcUrl, funcPid, isUsed, isLeaf);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
    	return rtnFlg;
    }
	
	
	
    /**
     * 跳转到权限修改见面
     * @return
     */
	@RequestMapping("/toModifyFuncInfo")
    public String toModifyFuncInfo(String funcId,HttpServletRequest request){
    	SysFuncInfo sfi = new SysFuncInfo();
    	try {
			sfi = roleAndFuncService.searchFuncById(funcId);
			
			request.setAttribute("sfi", sfi);
		} catch (Exception e) {

		}
    	return "/WEB-INF/jsp/modsystem/modifyFunc";
    }
	
    /**
     * 修改功能信息
     * @return
     */
	@RequestMapping("/modifyFuncInfo")
	@ResponseBody
    public String modifyFuncInfo(String funcId,String funcName,String funcDesc,String funcUrl,int isUsed,int isLeaf){
    	String rtnFlg;
		try {
			rtnFlg = roleAndFuncService.updateFuncInfo(funcId, funcName, funcDesc, funcUrl, isUsed, isLeaf);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
    	return rtnFlg;
    }
	
	
	/**
     *  删除权限
     * @return
     */
	@RequestMapping("/deleteFuncById")
	@ResponseBody
    public String deleteFuncById(String funcId){
    	String rtnFlg;
    	try {
			rtnFlg = roleAndFuncService.deleteFuncInfo(funcId);
		} catch (Exception e) {
			
			rtnFlg = "2";
		}
    	return rtnFlg;
    }
	
	
	/**
	 * 根据角色ID查询所有权限列表,拥有权限默认勾选
	 * @return
	 */
	@RequestMapping("/searchRoleFuncList")
	@ResponseBody
	public List searchRoleFuncList(String roleId){
		List funcList = null;
		String rtnFlg;
		try {
			funcList = roleAndFuncService.searchFuncListByRoleId(roleId);
			rtnFlg = "1";
		} catch (Exception e) {
			
			rtnFlg = "2";
		}
		return funcList;
	}
	
	
	/**
	 * 跳转到角色拥有权限
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/toFuncInfo")
	public String toFuncInfo(String roleId,HttpServletRequest request){
		
		request.setAttribute("roleId", roleId);
		
		return "/WEB-INF/jsp/modsystem/roleFuncList";
	}
	
	
	
	
	/*
	 * 根据角色ID查询该角色拥有权限列表
	 */
	@RequestMapping("/getFuncInfo")
	@ResponseBody
	public List<SysFuncInfo> getFuncInfo(String roleId){
		List<SysFuncInfo> list = new ArrayList<SysFuncInfo>();	

		try {			
			 list = roleAndFuncService.searchFuncByRoleId(roleId);	
		
		} catch (BaseException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}


}
