package com.hpkj.staff.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.staff.service.StaffService;
import com.hpkj.staff.vo.StaffDetailInfoVo;
/**
 * 全部员工信息列表
 * @author lipeiliu
 *
 */
@Controller
@RequestMapping("/staffList")
public class StaffListController {
	@Resource(name="staffService")
	private StaffService staffService;
	/**
	 * 全部员工信息列表
	 * @return
	 */
	@RequestMapping("/showStaffList")
	public @ResponseBody Map<String,Object> showStaffList(@RequestParam("currentPage") int currentPage,@RequestParam("pageSize") int pageSize,String sidx,String sord){
		Map<String,Object> map=null;
		List list;
		List<StaffDetailInfoVo> list1=new ArrayList<StaffDetailInfoVo>();//存放全部员工信息列表的对象
		try {			
			list = staffService.getStaffList(sidx,sord,0, 0);
			list1=staffService.getStaffList(sidx,sord,pageSize * (currentPage - 1), pageSize);
			int rowCount=list.size();
			map=new HashMap<String,Object>();
			map.put("gridData", list1);
			map.put("pageSize", pageSize);
			map.put("totalPage", (int) Math.ceil((double) rowCount / (double) pageSize));
			map.put("rowCount", rowCount);
			map.put("currentPage",currentPage);
			map.put("sidx", sidx);
			map.put("sord", sord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
