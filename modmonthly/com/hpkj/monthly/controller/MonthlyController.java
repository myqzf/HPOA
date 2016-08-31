package com.hpkj.monthly.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.hpkj.dictionary.entity.SysDictItems;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.monthly.entity.HpoaMonthsumInfo;
import com.hpkj.monthly.service.MonthlyService;
import com.hpkj.monthly.vo.EachMonthlyVo;
import com.hpkj.monthly.vo.HpoaMonthsumInfoVo;


/**
 *  月报管理---员工撰写与上传
 * @author qzf
 */
@Controller
@RequestMapping("/monthly")
public class MonthlyController {
	
	@Resource(name="monthlyService")
	private  MonthlyService monthlyService; 

	 private String rtnFlg;//操作结果：1成功，2失败
	 
	    private String path;// 路径
		private String filename;// 文件名
		private String message;//反馈信息
	
	
	/**
	 * 跳转到撰写上传  monthly.jsp页面
	 * @return 
	 */
	@RequestMapping("/goWriteMonthly")
	public String goWriteMonthly(){
		return "/WEB-INF/jsp/modmonthly/writemonthly";
	}
	
	/**
	 * 查询SysDictItems表  月份字典项的信息
	 * @return 
	 */
	@RequestMapping("/findDictMonth.json")
	@ResponseBody
	public List<SysDictItems> findDictMonth() throws BaseException{
		//查询SysDictItems表  月份详情
		List<SysDictItems> list = monthlyService.getDictMonthList();
		return list;
	}
	
	 /**
     * 保存撰写的月报
     * @return
     */
	@RequestMapping("/saveMonthly")
	@ResponseBody
    public String saveMonthly(HpoaMonthsumInfo hpoaMonthsumInfo,HttpSession session ){
		
		
		try {
			
			String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();
			//System.out.println(staffId);
			rtnFlg = monthlyService.addSaveMonthly(hpoaMonthsumInfo ,staffId);
			
		} catch (Exception e) {
			rtnFlg="2";
			e.printStackTrace();
			
		}
    	return rtnFlg  ;
    }
	
	/**
	 * 弹出上传文件页面
	 * @return
	 */
	@RequestMapping("/gotoUploadFile")
	public String gotoUploadFile(String title,String month,HttpServletRequest request){
		try {
			String decodtitle=URLDecoder.decode(title,"UTF-8");//转码后标题
			String decodmonth=URLDecoder.decode(month,"UTF-8");//转码后月份
			request.setAttribute("title",decodtitle);
			request.setAttribute("month",decodmonth);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmonthly/uploadFile";
	}
	/**
	 * 月报文件上传
	 * @param staffPicture
	 * @param staffId
	 * @param request
	 * @return
	 */
	
	@RequestMapping("/uploadFile")
	public String uploadPicture(@RequestParam(value = "monthlyFile", required = false) MultipartFile monthlyFile,String month,String title, HttpServletRequest request,HttpSession session){
		try {
		String fileName = monthlyFile.getOriginalFilename();      //得到文件名   带.doc
		//String fileName1=fileName.substring(0,fileName.lastIndexOf("."));//得到文件名   不带.doc  
        String extensionName = fileName.substring(fileName.lastIndexOf("."));    // 获取文件的扩展名   
        String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();// 员工的Id
      //  String userName = ((UserInfo) session.getAttribute("user")).getUserName();//用户姓名
        String date= DateTimeUtil.getDate(); 
        String returnDate = null;
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//上传文件名包含的日期的格式
        SimpleDateFormat pattern1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//保存的数据库的上传时间格式
        Date date1 = DateTimeUtil.getNow();
        returnDate = pattern.format(date1);//当前日期   文件名所包含的
        String datetime = pattern1.format(date1);//当前日期 保存到数据库的
       // filename=staffId+returnDate+extensionName;  // 新的文件名 = 员工的Id+当前日期及时间 +.doc
        filename=returnDate+"~"+fileName;  // 新的文件名 = 当前日期及时间 +文件名 
        String[] fileNameExtension = {".doc",".docx"}; 
		if(monthlyFile.getSize()==0){
			message="请选择文件";//"请选择文件";
			request.setAttribute("message", message);
			return "/WEB-INF/jsp/modmonthly/uploadFileMsg";
			}
		if(monthlyFile.getSize()>2097152){
			message="文件大小不能超过2M";//"请选择文件";
			request.setAttribute("message", message);
			return "/WEB-INF/jsp/modmonthly/uploadFileMsg";
		}
		int flag=0;
		for(String fileExt:fileNameExtension){
			if(fileExt.equals(extensionName)){
				flag=1;
				break;				
			}else{
				flag=0;
			}
		}		
		if(flag==0){
			message="不支持的文件格式.文件类型必须是doc或docx格式";
			request.setAttribute("message", message);
			return "/WEB-INF/jsp/modmonthly/uploadFileMsg";
		}
		for(String fileExt:fileNameExtension){
			File oldFile=new File(path,filename);
			if(oldFile.exists()){
				oldFile.delete();
			}
		}
		path=request.getSession().getServletContext().getRealPath("/monthlyfile");
		String ss= request.getContextPath(); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ss+"/"+"monthlyfile";
		System.out.println(basePath);
		File targetFile = new File(path, filename);  				
		monthlyFile.transferTo(targetFile); 
		
		//读取上传的内容
//		File FileUrl = new File(path+"\\"+filename); // 要读取以上路径的XX文件    文件地址
//		String url = path+"\\"+filename;
//		InputStream is = new FileInputStream(FileUrl);
//		WordExtractor ex =new WordExtractor(is); 
//		String content = ex.getText();
//		// System.out.println(content);
//		is.close();
		
		//保存上传的文件
		String url = basePath+"/"+filename;
		//String url=  uu.replaceAll("\\\\", "/"); 
		switch(monthlyService.addSaveUploadMonthly(staffId, filename,title,datetime,month)){
			case 0:{
				message="系统出错";
				break;
			}
			case 1:{
				message="上传成功";
				break;
			}
			case 2:{
				message="上传失败";
				break;
			}
		}
		
		request.setAttribute("message", message);
//		request.setAttribute("staffPhoto", photoUrl);
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmonthly/uploadFileMsg";
 }

	/**
	 * 跳转到员工已提交的 eachMonthly.jsp页面
	 * @return 
	 */
	@RequestMapping("/goEachMonthly")
	public String goEachMonthly(){
		return "/WEB-INF/jsp/modmonthly/eachMonthly";
	}
	
	/*
	 * jq查询单个员工月报列表
	 */
	@RequestMapping("/searchEachMonthly")
	@ResponseBody
	public HpoaMonthsumInfoVo searchEachMonthly(HpoaMonthsumInfo HpoaMonthsumInfo,String page,String rows,String sidx,String sord,HttpSession session){

		HpoaMonthsumInfoVo HpoaMonthsumInfoVo = new HpoaMonthsumInfoVo();
		
		int from = Integer.parseInt(rows) * (Integer.parseInt(page) - 1);
		int length = Integer.parseInt(rows);
		
		try {	
			String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();
			HpoaMonthsumInfoVo = monthlyService.searchEachMonthly(HpoaMonthsumInfo,page, rows, from, length,sidx,sord,staffId);		
		} catch (BaseException e) {			
			e.printStackTrace();
		}
		
		return HpoaMonthsumInfoVo;		
	}
	
	
	/**
	 * 跳转到修改月报 eachMonthly.jsp页面
	 * @return 
	 */
	@RequestMapping("/toModifyMonthly")
	public String toModifyMonthly(String monthsumid, HttpServletRequest request){
		HpoaMonthsumInfo mon = new HpoaMonthsumInfo();
    	try {
			mon = monthlyService.searchEachMonthlyInfo(monthsumid);
			
			request.setAttribute("mon", mon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmonthly/modifyMonthly";
	}
	/**
	 * 跳转到修改上传月报内容 modifyUploadMonthly.jsp页面
	 * @return 
	 */
	@RequestMapping("/toModifyUploadMonthly")
	public String toModifyUploadMonthly(String monthsumid, HttpServletRequest request){
		HpoaMonthsumInfo mon = new HpoaMonthsumInfo();
    	try {
			mon = monthlyService.searchEachMonthlyInfo(monthsumid);
			
			request.setAttribute("mon", mon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmonthly/modifyUploadMonthly";
	}
	
	/**
     *  删除月报
     * @return
     */
	@RequestMapping("/deleteMonthly")
	@ResponseBody
    public String deleteMonthly(String monthsumid, HttpServletRequest request){
    	String rtnFlg;
    	HpoaMonthsumInfo mon = new HpoaMonthsumInfo();
    	try {
    		mon = monthlyService.searchEachMonthlyInfo(monthsumid);
    		String uploadDate=mon.getUploaddate();
    		String link=mon.getSumlink();
    		if (link !=null) {
    			System.out.println("uploadDate"+uploadDate);
        		String[] fileNameExtension = {".doc",".docx"};
    			String path=request.getSession().getServletContext().getRealPath("/monthlyfile");
    			for(String fileExt : fileNameExtension){
    				File oldFile=new File(path,link);
    				if(oldFile.exists()){
    					oldFile.delete();
    				}
    			}
			}
    		
			rtnFlg = monthlyService.deleteMonthly(monthsumid);
		} catch (Exception e) {
			e.printStackTrace();
			rtnFlg = "2";
		}
    	return rtnFlg;
    }
	
	 /**
     * 修改月报
     * @return
     */
	@RequestMapping("/saveModifyMonthly")
	@ResponseBody
    public String saveModifyMonthly(HpoaMonthsumInfo hpoaMonthsumInfo,HttpSession session){
		try {
			String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();
			rtnFlg = monthlyService.modifyMonthly(hpoaMonthsumInfo,staffId);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
    	return rtnFlg;
    }
	
//	/**
//	 * 跳转到查看详细月报 eachMonthly.jsp页面
//	 * @return 
//	 */
//	@RequestMapping("/toViewMonthly")
//	public String toViewMonthly(String monthsumid, HttpServletRequest request){
//		HpoaMonthsumInfo mon = new HpoaMonthsumInfo();
//    	try {
//			mon = monthlyService.searchEachMonthlyInfo(monthsumid);
//			
//			request.setAttribute("mon", mon);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "/WEB-INF/jsp/modmonthly/viewMonthly";
//	}
	
	/**
	 * 跳转到查看详细月报 eachMonthly.jsp页面   
	 * @return 
	 */
	@RequestMapping("/toViewMonthly")
	public String toViewMonthly(String monthsumid, HttpServletRequest request){
		EachMonthlyVo mon = new EachMonthlyVo();
    	try {
			mon = monthlyService.searchEachMonthlyInfoTwo(monthsumid);
			
			request.setAttribute("mon", mon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmonthly/viewMonthly";
	}
	
	 /**
     * 查询当月月报是否已提交
     * @return
     */
	@RequestMapping("/seachIfSubmitted")
	@ResponseBody
    public String seachIfSubmitted(String monthscope,HttpSession session ){
		
		
		try {
			
			String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();
			//System.out.println(staffId);
			if (staffId!=null) {
				rtnFlg = monthlyService.seachIfSubmitted(monthscope ,staffId);
			}
			
			
		} catch (Exception e) {
			rtnFlg="3";//系统出错
			e.printStackTrace();
			
		}
    	return rtnFlg  ;
    }
	
	
	
	/**
	 * 查询员工最新一次提交的月报
	 * @return 
	 */
	@RequestMapping("/findLatestMonthly.json")
	@ResponseBody
	public List<EachMonthlyVo> findLatestMonthly(HttpSession session) throws BaseException{
		String staffId = ((UserInfo) session.getAttribute("user")).getStaffID();
		//查询SysDictItems表  月份详情
		List<EachMonthlyVo> list = monthlyService.getLatestMonthly(staffId);
		return list;
	}
	
	
	 /**
	  * 下载或查看  上传的月报
	 * @param fileName 文件名称
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/monthlyDownload.do")  
	    public void downloadFile(String fileName,HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{  
		
	        //response.setCharacterEncoding("utf-8");  
	        response.setContentType("multipart/form-data");  
	        String dowmName = java.net.URLDecoder.decode(fileName, "UTF-8");
	        dowmName=dowmName.substring(dowmName.indexOf("~")+1);
	        dowmName = new String(dowmName.getBytes("GB2312"), "ISO_8859_1"); 
	        response.setHeader("Content-Disposition", "attachment;fileName="+dowmName);  
	        try {  
	        	fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
	         
	        	path=request.getSession().getServletContext().getRealPath("/monthlyfile");
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
	 * 检查上传的月报是否存在
	 * @return
	 */
	@RequestMapping("/checkMonthlyAttachment") 
	@ResponseBody
	public String checkMonthlyAttachment(String fileName,HttpServletResponse response,HttpServletRequest request){
	
			String path = "";
			path=request.getSession().getServletContext().getRealPath("/monthlyfile");
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

	
	
}
