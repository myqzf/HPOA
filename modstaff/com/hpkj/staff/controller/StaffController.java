package com.hpkj.staff.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.staff.service.StaffService;
import com.hpkj.staff.vo.DictTitleVo;
import com.hpkj.staff.vo.StaffDetailInfoVo;
/**
 * 员工基础信息维护
 * @author lipeiliu
 *
 */
@Controller
@RequestMapping("/staff")
public class StaffController {
	@Resource(name="staffService")
	private StaffService staffService;
	private StaffDetailInfoVo staffInfo;//存放填写内容的对象
	private List<DictTitleVo> deptList=null;//部门列表
	private List<DictTitleVo> qualidList=null;//学历列表
	private List<DictTitleVo> posiList=null;//职位列表
	private List<DictTitleVo> compList=null;//公司列表
	/**
	 * 跳转至全部员工信息列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/gotoListStaff")
	public String gotoListStaff(HttpServletRequest request){
		try {
			qualidList=staffService.getDictList(4);
			deptList=staffService.getDictList(1);
			posiList=staffService.getDictList(98);
			compList=staffService.getDictList(108);
			request.setAttribute("qualidList",qualidList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("posiList",posiList);
			request.setAttribute("compList",compList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/WEB-INF/jsp/modstaff/showStaffList";
	}	
	/**
	 * 跳转至添加员工信息页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/gotoAddStaff")
	public String gotoAddStaff(HttpServletRequest request){
		try {
			qualidList=staffService.getDictList(4);
			deptList=staffService.getDictList(1);
			posiList=staffService.getDictList(98);
			compList=staffService.getDictList(108);
			request.setAttribute("qualidList", qualidList);
			request.setAttribute("deptList",deptList);
			request.setAttribute("posiList",posiList);
			request.setAttribute("compList",compList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modstaff/addStaff";
	}
	/**
	 * 跳转至修改员工信息页面
	 * @param staffId
	 * @param request
	 * @return
	 */
	@RequestMapping("/gotoModifyStaff")
	public String gotoModifyStaff(String staffId,HttpServletRequest request){
		try {
			HpoaStaffInfo staffInfo=staffService.getStaffInfo(staffId);
			qualidList=staffService.getDictList(4);
			deptList=staffService.getDictList(1);
			posiList=staffService.getDictList(98);
			compList=staffService.getDictList(108);
			request.setAttribute("staffInfo", staffInfo);
			request.setAttribute("qualidList",qualidList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("posiList",posiList);
			request.setAttribute("compList",compList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "/WEB-INF/jsp/modstaff/updateStaff";
	}
	/**
	 * 跳转至员工详细信息页面
	 * @param staffId
	 * @param request
	 * @return
	 */
	@RequestMapping("/gotoStaffDetail")
	public String gotoStaffDetail(String staffId,HttpServletRequest request){
		try {
			StaffDetailInfoVo staffInfo=staffService.getStaffDetailInfo(staffId);		
			request.setAttribute("staffInfo", staffInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "/WEB-INF/jsp/modstaff/staffDetail";
	}
	/**
	 * 弹出上传照片页面
	 * @return
	 */
	@RequestMapping("/gotoUploadPicture")
	public String gotoUploadPicture(String staffId,HttpServletRequest request){
		request.setAttribute("staffId",staffId);
		return "/WEB-INF/jsp/modstaff/uploadPicture";
	}
}
