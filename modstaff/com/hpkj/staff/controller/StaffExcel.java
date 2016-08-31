package com.hpkj.staff.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.hpkj.login.vo.UserInfo;
import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.staff.service.StaffService;
import com.hpkj.staff.util.DownExcel;
import com.hpkj.staff.util.ToExcel;
import com.hpkj.staff.vo.StaffDetailInfoVo;
/**
 * 员工信息导出excel文件
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/staffExcel")
public class StaffExcel {
	@Resource(name="staffService")
	private StaffService staffService;
	private String path;

	/**
	 * 生成excel
	 * @param currentPage
	 * @param pageSize
	 * @param staff
	 * @param sidx
	 * @param sord
	 * @param response
	 * @param session
	 */
	@RequestMapping("/toExcel")
	@ResponseBody
	public Map toExcel(int currentPage, int pageSize,StaffDetailInfoVo staff,String sidx,String sord,HttpSession session){
		Map map=new HashMap();
		path =session.getServletContext().getRealPath("fileupload/" );
		List<StaffDetailInfoVo> list=new ArrayList<StaffDetailInfoVo>();
		String fileName="";
		try{
			String staffId=((UserInfo) session.getAttribute("user")).getStaffID();
			fileName=path+"/download/"+staffId+"/员工信息表.xls";
			String file=staffId+"/员工信息表.xls";
			String [] TableTitle={"姓名","性别","婚否","身份证号","学历","电话","住址","隶属公司","隶属部门","隶属职位"};
			list = staffService.getSearchList(staff, sidx, sord, pageSize* (currentPage - 1), pageSize);
			ToExcel excel = new ToExcel(fileName,TableTitle,list);
			excel.exportXLS();
			map.put("file", file);
		}catch (Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 下载excel
	 * @param fileName
	 * @param response
	 * @param request
	 */
	@RequestMapping("/downExcel")
	@ResponseBody
	public void downExcel(String fileName,HttpServletResponse response,HttpServletRequest request){
        //处理请求   
        //读取要下载的文件   
		String newFile="";
		try{
			path =request.getServletContext().getRealPath("fileupload/" );
			fileName=java.net.URLDecoder.decode(new String(fileName.getBytes("ISO-8859-1"),"utf-8"),"utf-8");
			newFile=path+"/download/"+fileName;		 	
			DownExcel.down(newFile, fileName, request, response);			
		    }  
		catch(Exception e){
			e.printStackTrace();
		}
		}
      
		
}
