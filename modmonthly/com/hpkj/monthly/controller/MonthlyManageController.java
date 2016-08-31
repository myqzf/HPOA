package com.hpkj.monthly.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.dictionary.entity.SysDictItems;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.monthly.entity.HpoaMonthsumInfo;
import com.hpkj.monthly.service.MonthlyService;
import com.hpkj.monthly.vo.HpoaMonthsumInfoVo;
import com.hpkj.monthly.vo.ManageMonthsumVo;
import com.hpkj.monthly.vo.MonthlyVo;
import com.hpkj.staff.vo.StaffDetailInfoVo;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;


/**
 *  月报管理---统计与查看
 * @author qzf
 */
@Controller
@RequestMapping("/monthlyManage")
public class MonthlyManageController {
	
	@Resource(name="monthlyService")
	private  MonthlyService monthlyService; 

	 private String rtnFlg;//操作结果：1成功，2失败
	 
	
	
	/**
	 * 跳转到月报管理 monthlyManage.jsp页面
	 * @return 
	 */
	@RequestMapping("/goMonthlyManage")
	public String goMonthlyManage(HttpServletRequest request){
		HpoaMonthsumInfo mon = new HpoaMonthsumInfo();
    	try {
			mon = monthlyService.searchNewMonth();
			request.setAttribute("mon", mon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmonthly/monthlyManage";
	}
	
	/**
	 * 查询HpoaMonthsumInfo表   年份范围信息
	 * @return 
	 */
	@RequestMapping("/findYearScope.json")
	@ResponseBody
	public List<HpoaMonthsumInfo> findYearScope() {
		List<HpoaMonthsumInfo> list =null;
		try {
			 list = monthlyService.searchYearScope();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

	/**
	 * jq查询 当前最新提交的所有月报
	 */
	@RequestMapping("/searchCurrentMonthly")
	@ResponseBody
	public ManageMonthsumVo searchCurrentMonthly(String year , String month ,String page,String rows,String sidx,String sord){

		ManageMonthsumVo manageMonthsumVo = new ManageMonthsumVo();
		List<ManageMonthsumVo> list =null;
		
		int from = Integer.parseInt(rows) * (Integer.parseInt(page) - 1);
		int length = Integer.parseInt(rows);
		
		try {			
			manageMonthsumVo = monthlyService.searchCurrentMonthly(year,month,page, rows, from, length,sidx,sord);	
			//ManageMonthsumVo.setGridDTOs(list);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return manageMonthsumVo;		
	}
	
	
	/**
	 * jq查询 未提交月报员工
	 */
	@RequestMapping("/searchNotSubmitStaff")
	@ResponseBody
	public ManageMonthsumVo searchNotSubmitStaff(String year , String month ,String page,String rows,String sidx,String sord){

		ManageMonthsumVo manageMonthsumVo = new ManageMonthsumVo();
		List<ManageMonthsumVo> list =null;
		
		int from = Integer.parseInt(rows) * (Integer.parseInt(page) - 1);
		int length = Integer.parseInt(rows);
		
		try {			
			manageMonthsumVo = monthlyService.searchNotSubmitStaff(year,month,page, rows, from, length,sidx,sord);	
			//ManageMonthsumVo.setGridDTOs(list);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return manageMonthsumVo;		
	}
	
	/**
	 * 跳转到月报管理 monthlyManage.jsp页面
	 * @return 
	 */
	@RequestMapping("/queryNotSubmit")
	public String goqueryNotSubmit(){
		
		return "/WEB-INF/jsp/modmonthly/notSubmitMonthly";
	}
	
}
