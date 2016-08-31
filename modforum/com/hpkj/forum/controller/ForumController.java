package com.hpkj.forum.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.core.exception.BaseException;
import com.hpkj.forum.entity.BbsCardInfo;
import com.hpkj.forum.entity.BbsResponseInfo;
import com.hpkj.forum.entity.BbsSessionInfo;
import com.hpkj.forum.entity.BbsUserInfo;
import com.hpkj.forum.service.ForumService;
import com.hpkj.login.vo.UserInfo;

/**
 * 论坛
 * @author 任建波
 *
 */

@Controller
@RequestMapping("/forum")
public class ForumController {

	
	@Resource(name="forumService")
	private ForumService forumService;
	

	
	//***********************板块部分**************************

	/**
	 * 查询板块列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getForum")
	public String getForum(String masterId,HttpServletRequest request){
		List<BbsSessionInfo> forumList;
		
		try {
			 forumList = forumService.searchForumList(masterId);
			
			 for(int i=0;i<forumList.size();i++){
				//获取本版块最新帖子
				 List<BbsCardInfo> newCard = forumService.searchNewCard(forumList.get(i).getSessionId());
								 
				 if(newCard.size()!=0){
				 //获取最新帖子作者	
				 List<BbsUserInfo> list = forumService.getBbsUserInfo(newCard.get(0).getCuid());
				 
				 forumList.get(i).setcTitle(newCard.get(0).getCtitle());
				 forumList.get(i).setNickName(list.get(0).getNickName());
				 forumList.get(i).setcTime(newCard.get(0).getCtime());
				 forumList.get(i).setcId(newCard.get(0).getCid());
				 }			
			 }		 
			 
			request.setAttribute("forumList", forumList);
			
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/jsp/modforum/forumList";
	}
	
	
	/**
	 * 板块管理列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/forumManage")
	public String forumManage(String masterId,HttpServletRequest request){
		List<BbsSessionInfo> forumList;
		
		try {
			 forumList = forumService.searchForumList(masterId);
			
			request.setAttribute("forumList", forumList);
			
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/jsp/modforum/forumManage";
	}
	
	
	/**
	 * 跳转到新增板块
	 */
	@RequestMapping("/toSaveForum")
	public String toSaveForum(){
		
		return "/WEB-INF/jsp/modforum/addForum";
	}
	
	
	/**
	 * 新增板块
	 */
	@RequestMapping("/saveForum")
	public String saveForum(BbsSessionInfo bbsSessionInfo,HttpSession session){
		
		UserInfo userInfo = (UserInfo) session.getAttribute("user");		
		try {
			bbsSessionInfo.setMasterId(userInfo.getStaffID());
			
			//设为版主
			List<BbsUserInfo> bbsUserInfo = forumService.getBbsUserInfo(userInfo.getStaffID());
			bbsUserInfo.get(0).setIsMaster("1");
			for(BbsUserInfo bbsUser : bbsUserInfo){
				forumService.updateBbsUser(bbsUser);
			}			
			//设为版主结束
			
			forumService.saveForum(bbsSessionInfo);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/forum/forumManage"; 	
	}
	
	
	/**
	 * 跳转到修改板块页面
	 */
	@RequestMapping("/toupdateForum")
	public String toupdateForum(String sessionId,HttpServletRequest request){
		
		try {
			BbsSessionInfo bbsSessionInfo =	forumService.getForumByID(sessionId);
		
			request.setAttribute("bbsSessionInfo", bbsSessionInfo);
		
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modforum/updateForum";
	}
	
	
	
	
	/**
	 * 修改板块
	 */
	@RequestMapping("/updateForum")
	public String updateForum(String sessionId,String sessionName){
		
		try {	
	
			forumService.updateForum(sessionId,sessionName);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		return "redirect:/forum/forumManage"; 	
	}
	
	
	
	/**
	 * 删除板块
	 */
	@RequestMapping("/delecteForum")
	public String delecteForum(String sessionId){
		
		try {	
	
			forumService.delecteForum(sessionId);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		return "redirect:/forum/forumManage"; 	
	}
	
	

	//***********************帖子部分**************************
	
	
	/**
	 * 查询帖子列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getCard")
	public String getcard(String sessionId,Integer currentPage,HttpServletRequest request){
		List<BbsCardInfo> cardList;
		List<BbsCardInfo> cardLists;
		if(currentPage==null){
			currentPage=1;
		}
		
		int rows=2;
		
		int from = rows * (currentPage - 1);
		int length = rows;
		int beginPageIndex; // 页码列表的开始索引（包含）
		int endPageIndex; // 页码列表的结束索引（包含）
		
		try {	
			//******分页开始
			//当前页数据数
			cardList = forumService.searchCardList(sessionId,from,length);			
			//总数据数
			cardLists = forumService.searchCardList(sessionId,0,0);
			//总页数
			int totalPage = (cardLists.size()%rows==0) ? (cardLists.size()/rows) : (cardLists.size()/rows+1);
			// 计算 beginPageIndex 和 endPageIndex
			// >> 总页数不多于5页，则全部显示
			if (totalPage <= 5) {
				beginPageIndex = 1;
				endPageIndex = totalPage;
			}
			// >> 总页数多于5页，则显示当前页附近的共5个页码
			else {
				// 当前页附近的共5个页码（前2个 + 当前页 + 后2个）
				beginPageIndex = currentPage - 2;
				endPageIndex = currentPage + 2;
				// 当前面的页码不足4个时，则显示前5个页码
				if (beginPageIndex < 1) {
					beginPageIndex = 1;
					endPageIndex = 5;
				}
				// 当后面的页码不足5个时，则显示后5个页码
				if (endPageIndex > totalPage) {
					endPageIndex = totalPage;
					beginPageIndex = totalPage - 5 + 1;
				}
			}	
			//******分页结束
			
			
			
			//根据员工ID查询昵称
			for(int i =0;i<cardList.size();i++){
				List<BbsUserInfo> list = forumService.getBbsUserInfo(cardList.get(i).getCuid());

				cardList.get(i).setUserPoint(list.get(0).getPoint());
				cardList.get(i).setCsid(list.get(0).getNickName());
				cardList.get(i).setBbsLevel(list.get(0).getBbsLevel());
			}
			
			
			BbsSessionInfo bbsSessionInfo = forumService.getForumByID(sessionId);
		
			//所属板块名称
			request.setAttribute("sessionName", bbsSessionInfo.getSessionName());
			request.setAttribute("cardList", cardList);
			request.setAttribute("totalPage", totalPage);
			
			//所属板块ID
			request.setAttribute("sessionId", sessionId);
			
			//分页
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginPageIndex", beginPageIndex);
			request.setAttribute("endPageIndex", endPageIndex);
			
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/jsp/modforum/cardList";
	}
	
	/**
	 * 帖子置顶
	 */
	@RequestMapping("/toTop")
	public String toTop(String cid,String sessionId){
		
		try {
			forumService.toTop(cid);
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/forum/getCard?sessionId="+sessionId; 
		
	}
	
	
	/**
	 * 跳转到新增帖子
	 */
	@RequestMapping("/toSaveCard")
	public String toSaveCard(String sessionId,HttpServletRequest request,HttpSession session){
		
		UserInfo userInfo = (UserInfo) session.getAttribute("user");
		
		try {
			List<BbsUserInfo> list = forumService.getBbsUserInfo(userInfo.getStaffID());
			if(list.size()==0){
				request.setAttribute("bbsUserInfo",0);
			}else{				
				request.setAttribute("bbsUserInfo",list.get(0).getStaffId());
			}
		
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("sessionId",sessionId);
		return "/WEB-INF/jsp/modforum/addCard";
	}
	
	
	/**
	 * 新增帖子
	 */
	@RequestMapping("/saveCard")
	public String saveCard(BbsCardInfo bbsCardInfo,HttpServletRequest request,HttpSession session){
		
		UserInfo userInfo = (UserInfo) session.getAttribute("user");
		
		try {				

			
			
			bbsCardInfo.setCuid(userInfo.getStaffID());			
			forumService.saveCard(bbsCardInfo);
			
//			BbsSessionInfo bbsSessionInfo = forumService.getForumByID(bbsCardInfo.getCsid());

			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/forum/getCard?sessionId="+bbsCardInfo.getCsid()+"&currentPage=1"; 	
	}
	
	
	
	
	
	
	
   //************************回帖部分********************************	
	
	/**
	 * 查询回帖列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getResponseList")
	public String getResponseList(String cid,String sessionId,Integer currentPage,HttpSession session,HttpServletRequest request){
		List<BbsResponseInfo> responseList;
		List<BbsResponseInfo> responseLists;
		UserInfo userInfo = (UserInfo) session.getAttribute("user");		
		int isMaster = 0;
		
		//分页数据
		if(currentPage==null){
			currentPage=1;
		}		
		int rows=2;		
		int from = rows * (currentPage - 1);
		int length = rows;
		int beginPageIndex; // 页码列表的开始索引（包含）
		int endPageIndex; // 页码列表的结束索引（包含）
		//分页数据结束
		
		try {
			//分页开始
			responseList = forumService.searchResponseList(cid,from,length);
			//总数据数
			responseLists = forumService.searchResponseList(cid,0,0);
			//总页数
			int totalPage = (responseLists.size()%rows==0) ? (responseLists.size()/rows) : (responseLists.size()/rows+1);
			// 计算 beginPageIndex 和 endPageIndex
			// >> 总页数不多于5页，则全部显示
			if (totalPage <= 5) {
				beginPageIndex = 1;
				endPageIndex = totalPage;
			}
			// >> 总页数多于5页，则显示当前页附近的共5个页码
			else {
				// 当前页附近的共5个页码（前2个 + 当前页 + 后2个）
				beginPageIndex = currentPage - 2;
				endPageIndex = currentPage + 2;
				// 当前面的页码不足4个时，则显示前5个页码
				if (beginPageIndex < 1) {
					beginPageIndex = 1;
					endPageIndex = 5;
				}
				// 当后面的页码不足5个时，则显示后5个页码
				if (endPageIndex > totalPage) {
					endPageIndex = totalPage;
					beginPageIndex = totalPage - 5 + 1;
				}
			}	
			//分页结束	
			
			
			//根据员工ID查询昵称
			for(int i =0;i<responseList.size();i++){
				List<BbsUserInfo> list = forumService.getBbsUserInfo(responseList.get(i).getRuid());
				
				responseList.get(i).setRuid(list.get(0).getNickName());
				responseList.get(i).setPsUrl(list.get(0).getPsurl());
			}
			
			//获取帖子实体
			BbsCardInfo bbsCardInfo = forumService.getBbsCardInfo(cid);
			
			//获取板块实体
			BbsSessionInfo bbsSessionInfo = forumService.getForumByID(sessionId);
			
			//是否为版主
			if(bbsSessionInfo.getMasterId().equals(userInfo.getStaffID())){
				isMaster = 1;
				request.setAttribute("isMaster", isMaster);
			}			
			
			request.setAttribute("responseList", responseList);
			request.setAttribute("sessionName", bbsSessionInfo.getSessionName());
			request.setAttribute("sessionId", sessionId);
			request.setAttribute("ctitle", bbsCardInfo.getCtitle());
			request.setAttribute("cid", cid);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("beginPageIndex", beginPageIndex);
			request.setAttribute("endPageIndex", endPageIndex);
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/WEB-INF/jsp/modforum/responseList";
	}
	
		
	
	
	/**
	 * 回复帖子
	 */
	@RequestMapping("/reply")
	@ResponseBody
	public String reply(BbsResponseInfo bbsResponseInfo,HttpSession session){
		String flag = "2";
		UserInfo userInfo = (UserInfo) session.getAttribute("user");
		
		try {
			
			String aa = bbsResponseInfo.getRcontent();
			
			System.out.println(aa);
			bbsResponseInfo.setRuid(userInfo.getStaffID());
			
			forumService.reply(bbsResponseInfo);
			flag = "1";
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
//		 return "redirect:/forum/getResponseList?cid="+bbsResponseInfo.getRcid(); 	
	}
	
	
	
	
	
	//***********************注册论坛账号部分************************************
	
	/**
	 * 跳转到注册论坛账号页面
	 */
//	@RequestMapping("/toRegister")
//	public String toRegister(String sessionId,HttpServletRequest request,HttpSession session){
//		
//		UserInfo userInfo = (UserInfo) session.getAttribute("user");
//		
//		request.setAttribute("staffID", userInfo.getStaffID());
//		request.setAttribute("sessionId", sessionId);
//		
//		return "/WEB-INF/modforum/register";
//	}
	
	
	
	
	/**
	 * 注册论坛账号
	 */
//	@RequestMapping("/register")
//	public String register(String sessionId,BbsUserInfo bbsUserInfo){
//
//		
//		try {
//			
//			BufferedImage image = ImageIO.read(new File("D:/Renjianbo/tomcat/hpoa-apache-tomcat-7.0.37/webapps/HPOA/staffpicture/4028819a5354356301535441a2270000.png"));
//
//			System.out.println(""+image);
//			
//			forumService.register(bbsUserInfo);
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 return "redirect:/forum/getCard?sessionId="+sessionId+"&currentPage=1"; 	
//	}
	
	
}
