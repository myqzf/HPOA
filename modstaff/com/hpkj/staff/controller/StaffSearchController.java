package com.hpkj.staff.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.staff.service.StaffService;
import com.hpkj.staff.vo.StaffDetailInfoVo;
/**
 * 员工信息查询
 * @author lipeiliu
 *
 */
@Controller
@RequestMapping("/staffSearch")
public class StaffSearchController {
	
	@Resource(name="staffService")
	private StaffService staffService;
	/**
	 * 根据条件查询员工信息
	 * @param currentPage
	 * @param pageSize
	 * @param staff
	 * @param sidx
	 * @param sord
	 * @param response
	 * @return 满足条件的员工信息列表
	 */
	@RequestMapping("/searchStaffList")
	@ResponseBody
	public  Map<String,Object> showStaffList(@RequestParam("currentPage") int currentPage,@RequestParam("pageSize") int pageSize,StaffDetailInfoVo staff,String sidx,String sord,HttpServletResponse response){
		Map<String,Object> map=null;
		List<StaffDetailInfoVo> list=new ArrayList<StaffDetailInfoVo>();
		List<StaffDetailInfoVo> list1=new ArrayList<StaffDetailInfoVo>();//存放全部员工信息列表的对象
		try {			
			staff.setStaffName(java.net.URLDecoder.decode(new String(staff.getStaffName().getBytes("ISO-8859-1"),"utf-8"),"utf-8"));
			staff.setStaffAddress(java.net.URLDecoder.decode(new String(staff.getStaffAddress().getBytes("ISO-8859-1"),"utf-8"),"utf-8"));
			staff.setStaffPosi(java.net.URLDecoder.decode(new String(staff.getStaffPosi().getBytes("ISO-8859-1"),"utf-8"),"utf-8"));
			staff.setStaffComp(java.net.URLDecoder.decode(new String(staff.getStaffComp().getBytes("ISO-8859-1"),"utf-8"),"utf-8"));
			list=staffService.getSearchList(staff,sidx,sord, 0,0);
			list1=staffService.getSearchList(staff,sidx,sord,pageSize * (currentPage - 1), pageSize);
			int rowCount=list.size();
			map=new HashMap<String,Object>();
			map.put("gridData", list1);
			map.put("pageSize", pageSize);
			map.put("totalPage", (int) Math.ceil((double) rowCount / (double) pageSize));
			map.put("rowCount", rowCount);
			map.put("currentPage",currentPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return map;
	}

}
