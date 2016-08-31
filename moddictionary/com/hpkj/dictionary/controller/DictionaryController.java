package com.hpkj.dictionary.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.core.exception.BaseException;
import com.hpkj.dictionary.entity.SysDictHead;
import com.hpkj.dictionary.entity.SysDictItems;
import com.hpkj.dictionary.service.DictionaryService;
import com.hpkj.dictionary.vo.TreeInfo;


/**
 *  字典表管理
 * @author qzf
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
	
	@Resource(name="dictionaryService")
	private  DictionaryService dictionaryService; 

	 private String rtnFlg;//操作结果：1成功，2失败
	
	/**
	 * 跳转到字典头dictictionHead.jsp页面
	 * @return 
	 */
	@RequestMapping("/godictictionHead")
	public String goDictictionHead(){
		
		return "/WEB-INF/jsp/moddictionary/dictictionHead";
	}
	
	/**
	 * 跳转到字典项dictictionztree.jsp页面
	 * @return 
	 */
	@RequestMapping("/godictictionZtree")
	public String goDictictionZtree(int headId,HttpServletRequest request){
		request.setAttribute("headId", headId);
		return "/WEB-INF/jsp/moddictionary/dictictionZtree";
	}
	

	/**
	 * 查询SysDictItems表dictictionZtree中的数据
	 * @return 
	 * 
	 */
	@RequestMapping("/findDictictionZtree.json")
	@ResponseBody
	public List<SysDictItems> findDictictionZtree(int headId) throws BaseException{
		//查询SysDictItems表dictictionZtree中详情
		List<SysDictItems> treelist = dictionaryService.getDictionaryZtreeList(headId);
		
		return treelist;
	}
	
	
	 /**
     * 跳转到添加字典项页面
     * @return
     */
	@RequestMapping("/addDictionaryInfo")
    public String addDictionaryInfo(int idKey,int headId, int pIdKey,HttpServletRequest request){
		
		request.setAttribute("idKey", idKey);
		request.setAttribute("headId", headId);
		request.setAttribute("pIdKey", pIdKey);
    	return  "/WEB-INF/jsp/moddictionary/addDictiction";
    }
	
	
	/**
	 * 查询SysDictHead表和SysDictItems表dictictionHead和dictictionZtree中中的信息
	 * @return 
	 */
	@RequestMapping("/findDictHeadAndItems.json")
	@ResponseBody
	public List<TreeInfo> findDictHeadAndItems(HttpSession session) throws BaseException{
		List<TreeInfo> list=new ArrayList<TreeInfo>(); 
		
		list=dictionaryService.getDictHeadAndItemsList();
		
		
		
		return list;
	}
	
	/**
	 * 查询SysDictHead表dictictionHead中的信息
	 * @return 
	 */
	@RequestMapping("/findDictictionHead.json")
	@ResponseBody
	public List<SysDictHead> findDictictionHead() throws BaseException{
		//查询SysDictItems表dictictionZtree中详情
		List<SysDictHead> treelist = dictionaryService.getDictionaryHeadList();
		
		return treelist;
	}
	
	
	 /**
     * 添加字典项
     * @return
     */
	@RequestMapping("/addDictInfo")
	@ResponseBody
    public String addDictInfo(String itemsName, String itemsShort,
			  String ifleaf,  int pid,  int headId){
		
		
		try {
			rtnFlg = dictionaryService.saveDictInfo(itemsName,itemsShort, ifleaf, pid, headId);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			rtnFlg="2";
		}
    	return rtnFlg  ;
    }
	
	/**
     *  删除字典项
     * @return
     */
	@RequestMapping("/deleteDictById")
	@ResponseBody
    public String deleteDictById(String itemsId){
    	String rtnFlg;
    	try {
			rtnFlg = dictionaryService.deleteDictInfo(itemsId);
		} catch (Exception e) {
			e.printStackTrace();
			rtnFlg = "2";
		}
    	return rtnFlg;
    }
	
	 /**
     * 跳转到字典项修改见面
     * @return
     */
	@RequestMapping("/turnModifyDictInfo")
    public String turnModifyDictInfo(int itemsId,HttpServletRequest request){
    	SysDictItems sfi = new SysDictItems();
    	try {
			sfi = dictionaryService.searchDictById(itemsId);
			
			request.setAttribute("sfi", sfi);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "/WEB-INF/jsp/moddictionary/modiDictiction";
    }
	
	 /**
     * 修改字典项信息
     * @return
     */
	@RequestMapping("/modifyDictInfo")
	@ResponseBody
    public String modifyDictInfo(String itemsName,String itemsShort,  int itemsId){
		try {
			rtnFlg = dictionaryService.updateDictInfo(itemsName,itemsShort, itemsId);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
    	return rtnFlg;
    }
	
	
	 /**
     * 跳转到修改字典头页面
     * @return
     *   
     */
	@RequestMapping("/turnModifyDictionaryHeadInfo")
    public String turnModifyDictionaryHeadInfo(int headId,HttpServletRequest request){
		SysDictHead sfi = new SysDictHead();
    	try {
			sfi = dictionaryService.searchDictHeadById(headId);
			
			request.setAttribute("sfi", sfi);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "/WEB-INF/jsp/moddictionary/modiDictictionHead";
    }
	
	 /**
     * 修改字典头信息
     * @return
     */
	@RequestMapping("/modifyDictHead")
	@ResponseBody
    public String modifyDictHead(String headName, int headId
			){
		try {
			rtnFlg = dictionaryService.modifyDictHeadInfo(headName, headId);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
    	return rtnFlg;
    }


	/**
	 * 添加一级字典项   跳转到addOneDictItem.jsp页面
	 * @return 
	 */
	@RequestMapping("/goAddOneDictItem")
	public String goAddOneDictItem(){
		
		return "/WEB-INF/jsp/moddictionary/addOneDictItem";
	}
	
	
	 /**
     * 保存新增的一级字典项
     * @return
     */
	@RequestMapping("/addOneDictictionItems")
	@ResponseBody
    public String addOneDictictionItems(String itemsName, 
			 int headId){
		
		
		try {
			rtnFlg = dictionaryService.addSaveOneDictInfo(itemsName,headId);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			rtnFlg="2";
		}
    	return rtnFlg  ;
    }
	
	/**
	 * 添加字典头   跳转到addDictionaryHead.jsp页面
	 * @return 
	 */
	@RequestMapping("/addDictionaryHead")
	public String addDictionaryHead(){
		
		return "/WEB-INF/jsp/moddictionary/addDictionaryHead";
	}
	
	
	 /**
     * 保存新增的字典头
     * @return
     */
	@RequestMapping("/addDictHeadInfo")
	@ResponseBody
    public String addDictHeadInfo(String headName ){
		
		
		try {
			rtnFlg = dictionaryService.addSaveDictHeadInfo(headName);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			rtnFlg="2";
		}
    	return rtnFlg  ;
    }
	
	
	 /**
     * 根据headId查询字典项信息
     * @return
     */
	@RequestMapping("/searchIfItem")
	@ResponseBody
    public String searchIfItemInfo(int headId){
    	SysDictItems sfi = new SysDictItems();
    	try {
    		rtnFlg = dictionaryService.searchIfItemInfo(headId);
			
    	} catch (Exception e) {
			e.printStackTrace();
			rtnFlg="2";
		}
    	return rtnFlg  ;
    }
	
	/**
     *  删除字典头
     * @return
     */
	@RequestMapping("/deleteDictHeadById")
	@ResponseBody
    public String deleteDictHeadById(String headId){
    	String rtnFlg;
    	try {
			rtnFlg = dictionaryService.deleteDictHeadInfo(headId);
		} catch (Exception e) {
			e.printStackTrace();
			rtnFlg = "2";
		}
    	return rtnFlg;
    }
	
	/**
	 * 内部添加一级字典项   跳转到addInDictItem.jsp页面
	 * @return 
	 */
	@RequestMapping("/goAddInDictItem")
	public String goAddInDictItem(int headId,HttpServletRequest request){
		System.out.println("headId"+headId);
		request.setAttribute("headId", headId);
		return "/WEB-INF/jsp/moddictionary/addInDictItem";
	}
	
	
	public String getRtnFlg() {
		return rtnFlg;
	}

	public void setRtnFlg(String rtnFlg) {
		this.rtnFlg = rtnFlg;
	}
	
	
	
}
