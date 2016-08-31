package com.hpkj.culture.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.core.exception.BaseException;
import com.hpkj.culture.entity.HpoaCultureInfo;
import com.hpkj.culture.service.CultureService;
import com.hpkj.culture.vo.CultureInfoVo;
import com.hpkj.login.vo.UserInfo;

/**
 * 公司文化   管理
 * @author qzf
 *
 */
@Controller
@RequestMapping("/culture")
public class CultureController { 
	@Resource(name="cultureService")
	private  CultureService cultureService; 
	
    private String rtnFlg;//操作结果：1成功，2失败
	
	/**
	 * 跳转到添加企业文化  addCulture.jsp页面
	 * @return 
	 */
	@RequestMapping("/goAddCulture")
	public String goAddCulture(){
		return "/WEB-INF/jsp/modculture/addCulture";
	}
	

	 /**
     * 保存添加的企业文化   
     * @return
     */
	@RequestMapping("/saveCulture")
	@ResponseBody
    public String saveCulture(HpoaCultureInfo hpoaCultureInfo,HttpSession session ){
		
		
		try {
			
			String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();
			//System.out.println(staffId);
			rtnFlg = cultureService.addSaveCulture(hpoaCultureInfo ,staffId);
			
		} catch (Exception e) {
			rtnFlg="2";
			e.printStackTrace();
			
		}
    	return rtnFlg  ;
    }

	/**
	 * 跳转公司文化列表页面
	 * @return 
	 */
	@RequestMapping("/goCulturelist")
	public String goCulturelist(){
		return "/WEB-INF/jsp/modculture/cultureList";
	}

	
	
	/**
	 * easyui 查询企业文化列表   
	 */
	@RequestMapping("/searchCultureList")
	@ResponseBody
	public CultureInfoVo searchCultureList(HpoaCultureInfo hpoaCultureInfo,String page,String rows,String sort,String order){

		CultureInfoVo cultureInfoVo = new CultureInfoVo();
		
		int from = Integer.parseInt(rows) * (Integer.parseInt(page) - 1);
		int length = Integer.parseInt(rows);
		
		try {			
			cultureInfoVo = cultureService.searchCultureList(hpoaCultureInfo,page, rows, from, length,sort,order);		
			
		} catch (BaseException e) {			
			e.printStackTrace();
		}
		
		return cultureInfoVo;		
	}
	
	
	/**
	 * 跳转到查看文化内容 viewCultureContent.jsp页面
	 * @return 
	 */
	@RequestMapping("/toviewCultureContent")
	public String viewCultureContent(String cultureid, HttpServletRequest request){
		HpoaCultureInfo cul = new HpoaCultureInfo();
    	try {
			cul = cultureService.searchCultureContent(cultureid);
			
			request.setAttribute("cul", cul);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modculture/viewCultureContent";
	}
	
	/**
	 * 跳转到修改文化内容 modifyCultureInfo.jsp页面
	 * @return 
	 */
	@RequestMapping("/tomodifyCultureContent")
	public String modifyCultureContent(String cultureid, HttpServletRequest request){
		HpoaCultureInfo cul = new HpoaCultureInfo();
    	try {
			cul = cultureService.searchCultureContent(cultureid);
			
			request.setAttribute("cul", cul);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modculture/modifyCultureInfo";
	}
	
	 /**
     * 保存修改的企业文化信息
     * @return
     */
	@RequestMapping("/saveModifyCultureInfo")
	@ResponseBody
    public String saveModifyCultureInfo(HpoaCultureInfo hpoaCultureInfo,HttpSession session){
		try {
			String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();
			rtnFlg = cultureService.modifyCultureInfo(hpoaCultureInfo,staffId);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
    	return rtnFlg;
    }
	
	/**
     *  删除企业文化信息
     * @return
     */
	@RequestMapping("/deleteCultureInfo")
	@ResponseBody
    public String deleteCultureInfo(String cultureid, HttpServletRequest request){
    	String rtnFlg;
    	try {
			rtnFlg = cultureService.deleteCultureInfo(cultureid);
		} catch (Exception e) {
			e.printStackTrace();
			rtnFlg = "2";
		}
    	return rtnFlg;
    }
	
	
	
	
	
	
	
	
	
	
	public String getRtnFlg() {
		return rtnFlg;
	}


	public void setRtnFlg(String rtnFlg) {
		this.rtnFlg = rtnFlg;
	}
	
	
	
	
	
}
