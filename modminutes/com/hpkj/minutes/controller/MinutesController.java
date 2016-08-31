package com.hpkj.minutes.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.hpkj.core.exception.BaseException;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.minutes.entity.HpoaMinutesInfo;
import com.hpkj.minutes.service.MinutesService;
import com.hpkj.minutes.vo.MinutesInfoVo;

/**
 * 会议纪要   管理
 * @author qzf
 *
 */
@Controller
@RequestMapping("/minutes")
public class MinutesController {

	@Resource(name="minutesService")
	private  MinutesService minutesService; 
	
	 private String rtnFlg;//操作结果：1成功，2失败
	    private String path;// 路径
		private String filename;// 文件名
		private String message;//反馈信息
	
	
	/**
	 * 跳转到添加会议纪要 addMinutes.jsp页面
	 * @return 
	 */
	@RequestMapping("/goAddMinutes")
	public String goAddMinutes(){
		return "/WEB-INF/jsp/modminutes/addMinutes";
	}
	
	
	 /**
     * 保存添加会议纪要
     * @return
     */
	@RequestMapping("/addMinutesInfo")
	//@ResponseBody
    public String addMinutesInfo(@RequestParam(value = "minutesFile", required = false) MultipartFile minutesFile,HpoaMinutesInfo hpoaMinutesInfo,HttpSession session ,HttpServletRequest request){
		
		
		try {
			String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();
			String fileName = minutesFile.getOriginalFilename();      //得到文件名   带.doc
			if(fileName == ""){
				rtnFlg = minutesService.addMinutesInfo(hpoaMinutesInfo ,staffId);
			}else {
				
				SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//上传文件名包含的日期的格式
				 Date date1 = DateTimeUtil.getNow();
			     String returnDate = pattern.format(date1);//当前日期   文件名所包含的
			     fileName=returnDate+"~"+fileName;  // 新的文件名 = 当前日期及时间 +文件名 
			    // String[] fileNameExtension = {".doc",".docx"}; 
			    String path=request.getSession().getServletContext().getRealPath("/minutesfile");
			    File oldFile=new File(path,fileName);
				if(oldFile.exists()){
					oldFile.delete();
				}
				File targetFile = new File(path, fileName);  				
				minutesFile.transferTo(targetFile); 
				hpoaMinutesInfo.setMinuteslink(fileName);
				rtnFlg = minutesService.addMinutesInfo(hpoaMinutesInfo ,staffId);
			    
			}
			
			
			
		} catch (Exception e) {
			rtnFlg="2";
			e.printStackTrace();
			
		}
		request.setAttribute("rtnFlg", rtnFlg);
    	//return rtnFlg  ;
     	return "/WEB-INF/jsp/modminutes/minutesList";
    }


	/**
	 * 跳转会议纪要列表页面  
	 * @return 
	 */
	@RequestMapping("/goMinuteslist")
	public String goMinuteslist(){
		return "/WEB-INF/jsp/modminutes/minutesList";
	}
	
	
	/**  
	 * easyui 查询会议纪要列表   
	 */
	@RequestMapping("/searchMinutesList")
	@ResponseBody
	public MinutesInfoVo searchMinutesList(HpoaMinutesInfo hpoaMinutesInfo,String page,String rows,String order,String sort){

		MinutesInfoVo minutesInfoVo = new MinutesInfoVo();
		
		int from = Integer.parseInt(rows) * (Integer.parseInt(page) - 1);
		int length = Integer.parseInt(rows);
		
		try {			
			minutesInfoVo = minutesService.searchMinutesList(hpoaMinutesInfo,page, rows, from, length,order,sort);		
			
		} catch (BaseException e) {			
			e.printStackTrace();
		}
		
		return minutesInfoVo;		
	}
	
	
	/**
	 * 删除会议纪要
	 * @return
	 */
	@RequestMapping("/deleteMinutesInfo")
	@ResponseBody
	public String deleteMinutesInfo(String minutesid, HttpServletRequest request) {
		try {
			if (minutesid!=null) {
				HpoaMinutesInfo mut=new HpoaMinutesInfo();
				mut=minutesService.searchMinutesInfo(minutesid);
				String minuteslink=mut.getMinuteslink();
				if (minuteslink!=null) {
					String path=request.getSession().getServletContext().getRealPath("/minutesfile");
	    				File oldFile=new File(path,minuteslink);
	    				if(oldFile.exists()){
	    					oldFile.delete();
	    				}
				}
				rtnFlg=minutesService.deleteMinutesInfo(minutesid);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			rtnFlg="2";
		}
		return rtnFlg;
	}
	
	
	/**
	 * 跳转到查看会议纪要详情 viewMinutesInfo.jsp页面
	 * @return 
	 */
	@RequestMapping("/toviewMinutesInfo")
	public String viewwMinutesInfo(String minutesid, HttpServletRequest request){
		HpoaMinutesInfo mut = new HpoaMinutesInfo();
    	try {
    		mut = minutesService.searchMinutesInfo(minutesid);
			
			request.setAttribute("mut", mut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modminutes/viewMinutesInfo";
    	//return "/WEB-INF/jsp/modminutes/frontMinutesInfo";
	}
	
	
	
	/**
	 * 跳转到修改会议纪要详情 viewMinutesInfo.jsp页面  
	 * @return 
	 */
	@RequestMapping("/tomodifyMinutesInfo")
	public String modifyMinutesInfo(String minutesid, HttpServletRequest request){
		HpoaMinutesInfo mut = new HpoaMinutesInfo();
    	try {
    		mut = minutesService.searchMinutesInfo(minutesid);
			
			request.setAttribute("mut", mut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modminutes/modifyMinutesInfo";
	}
	
	
	 /**
     * 保存修改的会议纪要
     * @return
     */
	@RequestMapping("/saveModifyMinutesInfo")
	//@ResponseBody
    public String ModifyMinutesInfo(@RequestParam(value = "minutesFile", required = false) MultipartFile minutesFile,HpoaMinutesInfo hpoaMinutesInfo,HttpSession session ,HttpServletRequest request){
		
		
		try {
			String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();
			if (minutesFile==null) {
				rtnFlg = minutesService.modifyMinutesInfo(hpoaMinutesInfo ,staffId);
		//	}
		//	String fileName = minutesFile.getOriginalFilename();      //得到文件名   带.doc
		//	if(fileName==""){
				rtnFlg = minutesService.modifyMinutesInfo(hpoaMinutesInfo ,staffId);
			}else {
				String fileName = minutesFile.getOriginalFilename();      //得到文件名   带.doc
				SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//上传文件名包含的日期的格式
				 Date date1 = DateTimeUtil.getNow();
			     String returnDate = pattern.format(date1);//当前日期   文件名所包含的
			     fileName=returnDate+"~"+fileName;  // 新的文件名 = 当前日期及时间 +文件名 
			    // String[] fileNameExtension = {".doc",".docx"}; 
			    String path=request.getSession().getServletContext().getRealPath("/minutesfile");
			    File oldFile=new File(path,fileName);
				if(oldFile.exists()){
					oldFile.delete();
				}
				File targetFile = new File(path, fileName);  				
				minutesFile.transferTo(targetFile); 
				hpoaMinutesInfo.setMinuteslink(fileName);
				rtnFlg = minutesService.modifyMinutesInfo(hpoaMinutesInfo ,staffId);
			    
			}
			
		} catch (Exception e) {
			rtnFlg="4";
			e.printStackTrace();
			
		}
		request.setAttribute("rtnFlg", rtnFlg);
    	//return rtnFlg  ;
     	return "/WEB-INF/jsp/modminutes/minutesList";
    }
	
	    /**
		 * 检查上传的会议纪要附件是否存在
		 * @return
		 */
		@RequestMapping("/checkMinutesAttachment") 
		@ResponseBody
		public String checkMinutesAttachment(String fileName,HttpServletResponse response,HttpServletRequest request){
		
				String path = "";
				path=request.getSession().getServletContext().getRealPath("/minutesfile");
				try {
					
					fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
					
					        File f= new File(path+"/"+fileName);  
					        
					        if(f.exists()){
					        	message="true";
							}else {
								message="false";
							}
					        
					} catch (Exception e) {
						message="false";
						e.printStackTrace();
						return null;
					}
		          
				return message;
	       	}
	
	
	 /**
	  * 下载或查看  上传的会议纪要附件
	 * @param fileName 文件名称
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/minutesFileDownload.do")  
	    public void downloadFile(String fileName,HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{  
		
	        //response.setCharacterEncoding("utf-8");  
	        response.setContentType("multipart/form-data");  
	        String dowmName = java.net.URLDecoder.decode(fileName, "UTF-8");
	        dowmName=dowmName.substring(dowmName.indexOf("~")+1);
	        dowmName = new String(dowmName.getBytes("GB2312"), "ISO_8859_1"); 
	        response.setHeader("Content-Disposition", "attachment;fileName="+dowmName);  
	        try {  
	        	fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
	         
	        	path=request.getSession().getServletContext().getRealPath("/minutesfile");
	            File file=new File(path+"/"+fileName);  
	        	
	            InputStream inputStream=new FileInputStream(file);  
	            OutputStream os=response.getOutputStream();  
	            byte[] b=new byte[1024];  
	            int length;  
	            while((length=inputStream.read(b))>0){  
	                os.write(b,0,length);  
	            }  
	            inputStream.close();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	
	
	/**
	 * 删除会议纪要所属的附件
	 * @return
	 */
	@RequestMapping("/deleteMinutesAttachment") 
	@ResponseBody
	public String deleteMinutesAttachment(String minutesid,HttpServletRequest request){
		try{
			
			String[] fileNameExtension = {".doc",".docx"};
			HpoaMinutesInfo info = minutesService.searchMinutesInfo(minutesid);
			String minuteslink=info.getMinuteslink();
			if (minuteslink!=null) {
				String path=request.getSession().getServletContext().getRealPath("/minutesfile");
				//String noticeUrl=Attachmenturl.substring(Attachmenturl.lastIndexOf("."));
				for(String fileExt : fileNameExtension){
					File oldFile=new File(path,minuteslink);
					
					if(oldFile.exists()){
						oldFile.delete();
					}
				}
			}
			
			switch(minutesService.deleteMinutesAttachment(minutesid)){
				case 0:{
					message="系统出错";
					break;
				}
				case 1:{
					message="删除成功";
					break;
				}
				case 2:{
					message="删除失败";
					break;
				}
			}
		}catch(Exception e){
			message="系统出错";
		}
		return message;
	}
	
	
	/**
	 * 跳转到前台查看会议纪要详情 frontMinutesInfo.jsp页面
	 * @return 
	 */
	@RequestMapping("/tofrontMinutesInfo")
	public String tofrontMinutesInfo(String minutesid, HttpServletRequest request){
		HpoaMinutesInfo mut = new HpoaMinutesInfo();
    	try {
    		mut = minutesService.searchMinutesInfo(minutesid);
			
			request.setAttribute("mut", mut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modminutes/frontMinutesInfo";
	}
	
	
	public String getRtnFlg() {
		return rtnFlg;
	}


	public void setRtnFlg(String rtnFlg) {
		this.rtnFlg = rtnFlg;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
