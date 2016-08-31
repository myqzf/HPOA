package com.hpkj.staff.controller;

import java.io.File;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.staff.service.StaffService;
/**
 * 员工信息管理
 * @author lipeiliu
 *
 */
@Controller
@RequestMapping("/staffManage")
public class StaffManageController {
	@Resource(name="staffService")
	private StaffService staffService;
	/**
	 * 添加员工信息
	 * @param staff
	 * @param response
	 */
	@RequestMapping("/addStaff")
	public void addStaff(HpoaStaffInfo staff,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		String result = "{\"result\":\"2\"}";		
		try {			
			switch(staffService.addStaff(staff)){
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
					result = "{\"result\":\"3\"}";//未填写姓名
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
	/**
	 * 根据员工id删除员工
	 * @param staffId
	 * @param response
	 */
	@RequestMapping("/delStaff")
	public void delStaff(String staffId,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		String result = "{\"result\":\"2\"}";
		try {
			switch(staffService.delStaff(staffId)){
			case 0:{
				result = "{\"result\":\"0\"}";//系统出错
				break;
			}
			case 1:{
				result = "{\"result\":\"1\"}";//删除成功
				break;
			}
			case 2:{
				result = "{\"result\":\"2\"}";//删除失败
				break;
			}
			case 3:{
				result = "{\"result\":\"3\"}";//员工id为空
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
	/**
	 * 修改员工信息
	 * @param staffInfo
	 * @param response
	 */
	@RequestMapping("/modifyStaff")
	public void modifyStaff(HpoaStaffInfo staffInfo,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		String result = "{\"result\":\"2\"}";
		try {			
			switch(staffService.modifyStaff(staffInfo)){
			case 0:{
				result = "{\"result\":\"0\"}";//系统出错
				break;
			}
			case 1:{				
				result="{\"result\":\"1\"}";//修改成功，修改用户为当前登录用户
				break;						
			}
			case 2:{
				result = "{\"result\":\"2\"}";//修改失败
				break;
			}
			case 3:{
				result= "{\"result\":\"3\"}";//姓名不能为空
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
	/**
	 * 删除员工照片
	 * @param staffId
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delPhoto")
	public void delPhoto(String staffId,HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		String result = "{\"result\":\"2\"}";
		try{
			String[] fileNameExtension = {".bmp",".png",".gif",".jpeg",".jpg"};
			String path=request.getSession().getServletContext().getRealPath("/staffpicture");
			for(String fileExt : fileNameExtension){
				File oldFile=new File(path,staffId+fileExt);
				if(oldFile.exists()){
					oldFile.delete();
				}
			}
			switch(staffService.delStaffPhoto(staffId)){
			case 0:{
				result = "{\"result\":\"0\"}";//系统出错
				break;
			}
			case 1:{
				result = "{\"result\":\"1\"}";//删除成功
				break;
			}
			case 2:{
				result = "{\"result\":\"2\"}";//删除失败
				break;
			}	
			case 3:{
				result = "{\"result\":\"3\"}";//员工id为空
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
	
}
