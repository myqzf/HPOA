package com.hpkj.share.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.core.util.FileUtil;
import com.hpkj.core.util.StringUtilz;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.message.vo.UiGridVo;
import com.hpkj.share.entity.HpoaShareInfo;
import com.hpkj.share.service.ShareService;
import com.hpkj.staff.util.DownExcel;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Resource
	private ShareService shareService;
	/**
	 * 跳转至上传资料共享页
	 * @return
	 */
	@RequestMapping("goAddShare")
	public String goAddShare(){
		return "/WEB-INF/jsp/modshare/addShare";
	}
	@RequestMapping("addShare")
	public String addShare(MultipartFile shareFile,String title,String content,HttpServletRequest request,HttpSession session){
		try{
			if(StringUtilz.isEmpty(shareFile.getOriginalFilename())||StringUtilz.isEmpty(title)||StringUtilz.isEmpty(content)){
				request.setAttribute("message", "请检查信息是否填写完整，是否选择了文件");
				return "/WEB-INF/jsp/modshare/addShare";
			}
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			//String filepath=session.getServletContext().getRealPath("/fileupload/sharefile");
			String filepath=FileUtil.getUploadFileValue("file_share");
			String newFileName=DateTimeUtil.getDateTime("yyyyMMddHHmmssSSS")+shareFile.getOriginalFilename().substring(shareFile.getOriginalFilename().lastIndexOf("."));
			File path=new File(filepath);
			File tmpFile=new File(filepath,newFileName);
			if(!tmpFile.getParentFile().exists()){
				tmpFile.getParentFile().mkdirs();
			}
			shareFile.transferTo(new File(filepath,newFileName));
			HpoaShareInfo hsi=new HpoaShareInfo();
			hsi.setShareAuthorId(staffid);
			hsi.setShareContent(content);
			hsi.setShareTitle(title);
			hsi.setShareFileName(newFileName);
			hsi.setShareRealFileName(shareFile.getOriginalFilename());
			int flag=shareService.saveShare(hsi);
			String message="系统繁忙";
			switch(flag){
				case 0:{
					message="系统繁忙";
					break;
				}
				case 1:{
					message="共享成功";
					break;
				}
				case 2:{
					message="共享失败";
					break;
				}
				case 3:{
					message="缺失关键参数";
					break;
				}
			}
			request.setAttribute("message", message);
			request.setAttribute("flag", flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modshare/uploadmsg";
	}
	@RequestMapping("goAllShare")
	public String goAllShare(){
		return "/WEB-INF/jsp/modshare/allShareList";
	}
	@RequestMapping("getAllShareList.json")
	@ResponseBody
	public UiGridVo getAllShareList(int page,int rows,String order,String sort){
		UiGridVo ugv=new UiGridVo();
		try{
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=shareService.getShareList(0, 0, sort, order).size();
			ugv=new UiGridVo(rowCount,rows,length,shareService.getShareList(from, length, sort, order));
		}catch(Exception e){
			
		}
		return ugv;
	}
	@RequestMapping("goShareDetail")
	public String goShareDetail(HttpServletRequest request,String shareid){
		try{
			request.setAttribute("shareDetail", shareService.getShareDetail(shareid));
		}catch(Exception e){
			
		}
		return "/WEB-INF/jsp/modshare/shareDetail";
	}
	@RequestMapping("downShareFile.json")
	@ResponseBody
	public void downShareFile(String fileName,String realFileName,HttpServletResponse response,HttpServletRequest request,HttpSession session){
		try{
			//String filepath=session.getServletContext().getRealPath("/fileupload/sharefile")+"/"+fileName;
			String filepath=FileUtil.getUploadFileValue("file_share")+"/"+fileName;
			DownExcel.shareDown(filepath, realFileName, request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
