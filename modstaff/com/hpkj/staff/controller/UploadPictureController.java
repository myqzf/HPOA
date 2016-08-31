package com.hpkj.staff.controller;

import java.io.File;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hpkj.forum.service.ForumService;
import com.hpkj.staff.service.StaffService;
/**
 * 员工照片上传
 * @author lipeiliu
 *
 */
@Controller
@RequestMapping("/uploadPicture")
public class UploadPictureController {
	@Resource(name="staffService")
	private StaffService staffService;
	@Resource(name="forumService")
	private ForumService forumService;
	
	private String path;// 路径
	private String photoUrl;// 员工图片名
	private String message;//反馈信息
	/**
	 * 上传照片
	 * @param staffPicture员工照片
	 * @param staffId员工id
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadPicture")
	public String uploadPicture(@RequestParam(value = "staffPicture", required = false) MultipartFile staffPicture,String staffId, HttpServletRequest request){
		try {
			if(staffPicture.getSize()==0){
				message="请选择文件";//"请选择文件";
				request.setAttribute("message", message);
				return "/WEB-INF/jsp/modstaff/uploadPicutreMsg";
				}
		String fileName = staffPicture.getOriginalFilename();      //得到图片文件名
        String extensionName = fileName.substring(fileName.lastIndexOf("."));    // 获取图片的扩展名   
        photoUrl=staffId+extensionName;  // 新的图片文件名 = 获取员工id+"."图片扩展名  
        String[] fileNameExtension = {".bmp",".png",".gif",".jpeg",".jpg"}; 		
		if(staffPicture.getSize()>2097152){
			message="图片大小不能超过2M";//"请选择文件";
			request.setAttribute("message", message);
			return "/WEB-INF/jsp/modstaff/uploadPicutreMsg";
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
			message="不支持的图片格式.图片类型必须是bmp、png、gif、jpeg、jpg格式";
			request.setAttribute("message", message);
			return "/WEB-INF/jsp/modstaff/uploadPicutreMsg";
		}
		for(String fileExt:fileNameExtension){
			File oldFile=new File(path,staffId+fileExt);
			if(oldFile.exists()){
				oldFile.delete();
			}
		}
		path=request.getSession().getServletContext().getRealPath("/staffpicture");
		File targetFile = new File(path, photoUrl);  				
		staffPicture.transferTo(targetFile); 
		//修改论坛表图片地址
		forumService.modifyPhoto(staffId, photoUrl);
		
		switch(staffService.modifyPhoto(staffId, photoUrl)){
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
		case 3:{
			message="staffid为空";
			break;
		}
		}
		request.setAttribute("message", message);
		request.setAttribute("staffPhoto", photoUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modstaff/uploadPicutreMsg";
	}

}
