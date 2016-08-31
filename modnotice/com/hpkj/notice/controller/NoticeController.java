package com.hpkj.notice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.login.vo.UserInfo;
import com.hpkj.message.vo.JQGridVo;
import com.hpkj.message.vo.UiGridVo;
import com.hpkj.notice.entity.HpoaNoticeInfo;
import com.hpkj.notice.service.NoticeService;


/**
 * OA系统公告模块
 * @author 越蓬
 *
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Resource(name="noticeService")
	private NoticeService noticeService;
	/**
	 * 跳转至公告管理主页
	 * @return
	 */
	@RequestMapping("goNoticeManage")
	private String goNoticeManage(){
		return "/WEB-INF/jsp/modnotice/noticemanage";
	}
	/**
	 * 跳转至阅读公告页面
	 * @param noticeid 公告id
	 * @param request
	 * @return
	 */
	@RequestMapping("goReadNotice")
	private String goReadNotice(String noticeid,HttpServletRequest request){
		try{
			request.setAttribute("notice", noticeService.getReadNotice(noticeid));
		}catch(Exception e){
			e.printStackTrace();
		}
		return"/WEB-INF/jsp/modnotice/noticeDetail";
	}
	/**
	 * 获取滚动显示的公告
	 * @return
	 */
	@RequestMapping("getNoticeScrollList.json")
	@ResponseBody
	private Map getNoticeScrollList(HttpSession session){
		Map map=new HashMap();
		try{
			map.put("noticeScroll", noticeService.getNoticeScrollList(((UserInfo)session.getAttribute("user")).getStaffID()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 获取全部公司列表
	 * @return
	 */
	@RequestMapping("getCompList.json")
	@ResponseBody
	private Map getCompList(){
		Map map=new HashMap();
		try{
			map.put("complist", noticeService.getCompList());
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 获取分页后列表
	 * @return
	 */
	@RequestMapping("getGridNotice.json")
	@ResponseBody
	private JQGridVo getGridNotice(String title,String comp,String noticeTime,String author,int currentPage,int pageSize,String sidx,String sord,HttpSession session){
		JQGridVo jqGridVo=null;
		try{
			int from,length,rowCount;
			from = pageSize * (currentPage - 1);
			length = pageSize;
			rowCount=noticeService.getNoticeList(0, 0, sidx, sord).size();
			jqGridVo=new JQGridVo(noticeService.getNoticeList(from, length, sidx, sord),rowCount,pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jqGridVo;
	}
	/**
	 * 获取分页后列表
	 * @return
	 */
	@RequestMapping("getUiGridNotice.json")
	@ResponseBody
	private UiGridVo getUiGridNotice(int page,int rows,String order,String sort,HttpSession session){
		UiGridVo uiGridVo=null;
		try{
			int from,length,rowCount;
			from = rows * (page - 1);
			length = rows;
			rowCount=noticeService.getNoticeList(0, 0, sort, order).size();
			uiGridVo=new UiGridVo(rowCount,rows,length,noticeService.getNoticeList(from, length, sort, order));
		}catch(Exception e){
			
		}
		return uiGridVo;
	}
	/**
	 * 跳转至发布公告
	 * @return
	 */
	@RequestMapping("goCreateNotice")
	private String goCreateNotice(){
		return "/WEB-INF/jsp/modnotice/writenotice";
	}
	/**
	 * 发布新公告
	 */
	@RequestMapping("publishNoteice.json")
	@ResponseBody
	private Map publishNotice(String title,String content,HttpSession session){
		Map map=new HashMap();
		try{
			String message="";
			switch(noticeService.publishNotice(title, content, ((UserInfo)session.getAttribute("user")).getStaffID())){
				case 0: {
					message="系统繁忙";
					break;
				}
				case 1: {
					message="发布成功";
					break;
				}
				case 2: {
					message="发布失败";
					break;
				}
			}
			map.put("flagmsg", message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 设置置顶公告
	 * @param noticeid 公告id
	 * @param stauts 状态1置顶,2取消置顶
	 * @return
	 */
	@RequestMapping("setTop.json")
	@ResponseBody
	private Map setTop(String noticeid,String stauts){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(noticeService.setTop(noticeid, stauts)){
				case 0:{
					flagmsg="系统繁忙";
					break;
				}
				case 1:{
					if("2".equals(stauts)){
						flagmsg="取消成功";
					}else{
						flagmsg="置顶成功";
					}
					break;
				}
				case 2:{
					flagmsg="置顶失败";
					break;
				}
			}
			map.put("flagmsg", flagmsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 跳转至修改公告
	 * @return
	 */
	@RequestMapping("goEditNotice")
	private String goEditNotice(String noticeid,HttpServletRequest request){
		try{
			request.setAttribute("noticeid", noticeid);
			HpoaNoticeInfo hni=(HpoaNoticeInfo)noticeService.getObjById(HpoaNoticeInfo.class, noticeid);
			request.setAttribute("title", hni.getTitle());
			request.setAttribute("content", hni.getContent());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modnotice/writenotice";
	}
	/**
	 * 修改公告
	 */
	@RequestMapping("editNoteice.json")
	@ResponseBody
	private Map editNotice(String title,String content,String noticeid){
		Map map=new HashMap();
		try{
			String message="";
			switch(noticeService.editNotice(noticeid, title, content)){
				case 0: {
					message="系统繁忙";
					break;
				}
				case 1: {
					message="修改成功";
					break;
				}
				case 2: {
					message="修改失败";
					break;
				}
			}
			map.put("flagmsg", message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 删除公告
	 */
	@RequestMapping("delNoteice.json")
	@ResponseBody
	private Map delNotice(String noticeid){
		Map map=new HashMap();
		try{
			String message="";
			switch(noticeService.delNotice(noticeid)){
				case 0: {
					message="系统繁忙";
					break;
				}
				case 1: {
					message="删除成功";
					break;
				}
				case 2: {
					message="删除失败";
					break;
				}
			}
			map.put("flagmsg", message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
}
