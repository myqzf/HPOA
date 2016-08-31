package com.hpkj.message.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpkj.login.vo.UserInfo;
import com.hpkj.message.service.MessageService;
import com.hpkj.message.vo.JQGridVo;
import com.hpkj.message.vo.NoSendMessageDetailVo;
import com.hpkj.message.vo.ReceiveMessageDetailVo;
import com.hpkj.message.vo.SendMessageDetailVo;
/**
 * OA系统站内短信模块
 * @author 越蓬
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	@Resource(name="messageService")
	MessageService messageService;
	/**
	 * 跳转至短信箱主页
	 * @return
	 */
	@RequestMapping("/goMessage")
	private String goMessage(String flag,HttpServletRequest request,HttpSession session){
		try{
			request.setAttribute("noReadCount", messageService.getNoReadCount(((UserInfo)session.getAttribute("user")).getStaffID()));
			request.setAttribute("flag", flag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmessage/listmessage";
	}
	/**
	 * 读取所收短信列表
	 * @param currentPage
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param session
	 * @return
	 */
	@RequestMapping("/getReceiveMessage.json")
	@ResponseBody
	private JQGridVo getReceiveMessage(int currentPage,int pageSize,String sidx,String sord,HttpSession session){
		JQGridVo jqGridVo=null;
		try{
			int from,length,rowCount;
			from = pageSize * (currentPage - 1);
			length = pageSize;
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			rowCount=messageService.getReceiveMessageList(staffid, 0, 0, sidx, sord).size();
			jqGridVo=new JQGridVo(messageService.getReceiveMessageList(staffid, from, length, sidx, sord),rowCount,pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jqGridVo;
	}
	/**
	 * 删除收件箱内短信
	 * @param messageid
	 * @param session
	 * @return
	 */
	@RequestMapping("/delReceiveMessage.json")
	@ResponseBody
	private Map delReceiveMessage(String messageid,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(messageService.deleteMessage(((UserInfo)session.getAttribute("user")).getStaffID(), messageid,1)){
				case 0:{
					flagmsg="系统出错";
					break;
				}
				case 1:{
					flagmsg="删除成功";
					break;
				}
				case 2:{
					flagmsg="删除失败";
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
	 * 读取已发信息列表
	 * @param currentPage
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param session
	 * @return
	 */
	@RequestMapping("/getSendMessage.json")
	@ResponseBody
	private JQGridVo getSendMessage(int currentPage,int pageSize,String sidx,String sord,HttpSession session){
		JQGridVo jqGridVo=null;
		try{
			int from,length,rowCount;
			from = pageSize * (currentPage - 1);
			length = pageSize;
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			rowCount=messageService.getSendMessageList(staffid, 0, 0, sidx, sord).size();
			jqGridVo=new JQGridVo(messageService.getSendMessageList(staffid, from, length, sidx, sord),rowCount,pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jqGridVo;
	}
	/**
	 * 删除发件箱内短信
	 * @param messageid
	 * @param session
	 * @return
	 */
	@RequestMapping("/delSendMessage.json")
	@ResponseBody
	private Map delSendMessage(String messageid,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(messageService.deleteMessage(((UserInfo)session.getAttribute("user")).getStaffID(), messageid,2)){
				case 0:{
					flagmsg="系统出错";
					break;
				}
				case 1:{
					flagmsg="删除成功";
					break;
				}
				case 2:{
					flagmsg="删除失败";
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
	 * 读取草稿箱内信息列表
	 * @param currentPage
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param session
	 * @return
	 */
	@RequestMapping("/getNoSendMessage.json")
	@ResponseBody
	private JQGridVo getNoSendMessage(int currentPage,int pageSize,String sidx,String sord,HttpSession session){
		JQGridVo jqGridVo=null;
		try{
			int from,length,rowCount;
			from = pageSize * (currentPage - 1);
			length = pageSize;
			String staffid=((UserInfo)session.getAttribute("user")).getStaffID();
			rowCount=messageService.getNoSendMessageList(staffid, 0, 0, sidx, sord).size();
			jqGridVo=new JQGridVo(messageService.getNoSendMessageList(staffid, from, length, sidx, sord),rowCount,pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jqGridVo;
	}
	/**
	 * 删除草稿箱内短信
	 * @param messageid
	 * @param session
	 * @return
	 */
	@RequestMapping("/delNoSendMessage.json")
	@ResponseBody
	private Map delNoSendMessage(String messageid,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(messageService.deleteMessage(((UserInfo)session.getAttribute("user")).getStaffID(), messageid,3)){
				case 0:{
					flagmsg="系统出错";
					break;
				}
				case 1:{
					flagmsg="删除成功";
					break;
				}
				case 2:{
					flagmsg="删除失败";
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
	 * 跳转至收到相应短信页
	 * @param messageid
	 * @param request
	 * @return
	 */
	@RequestMapping("/goReceiveDetail")
	private String goReceiveDetail(String messageid,String readstatus,HttpServletRequest request,HttpSession session){
		try{
			if("2".equals(readstatus)){
				messageService.editReadStatus(((UserInfo)session.getAttribute("user")).getStaffID(), messageid);
			}
			ReceiveMessageDetailVo rmdv=messageService.getReceiveByMessageid(messageid);
			request.setAttribute("receiveMessageDetail", rmdv);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmessage/receivemessage";
	}
	/**
	 * 跳转至已发送短信详情页
	 * @param messageid
	 * @param request
	 * @return
	 */
	@RequestMapping("/goSendDetail")
	private String goSendDetail(String messageid,HttpServletRequest request){
		try{
			SendMessageDetailVo smdv=messageService.getSendByMessageid(messageid);
			String[] recever=smdv.getRecevername().split(",");
			String recevertmp="";
			for(int i=0;i<recever.length;i++){
				recevertmp=recevertmp+"<tr><td>"+recever[i]+"</td></tr>";
			}
			smdv.setRecevername(recevertmp);
			request.setAttribute("sendMessageDetail", smdv);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmessage/sendmessage";
	}
	/**
	 * 跳转至撰写新短信
	 * @return
	 */
	@RequestMapping("/goWriteNewMessage")
	private String geoWriteNewMessage(){
		return "/WEB-INF/jsp/modmessage/writemessage";
	}
	/**
	 * 跳转至编辑短信
	 * @param messageid
	 * @param request
	 * @return
	 */
	@RequestMapping("/goEditMessage")
	private String goEditMessage(String messageid,HttpServletRequest request){
		try{
			NoSendMessageDetailVo nsmdv=messageService.getNoSendByMessageid(messageid);
			String[] receversname=nsmdv.getReceversName().split(",");
			String[] receversid=nsmdv.getReceversid().split(",");
			String recevertmp="";
			for(int i=0;i<receversname.length;i++){
				recevertmp=recevertmp+"<tr><td>"+receversname[i]+"&nbsp;&nbsp;<a href='javascript:void(0);' onclick=cancelrecever('"+receversid[i]+"',this)>取消</a></td></tr>";
			}
			nsmdv.setReceversName(recevertmp);
			request.setAttribute("noSendMessageDetail", nsmdv);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmessage/writemessage";
	}
	/**
	 * 跳转至选择收件人页
	 * @param receiversid
	 * @param request
	 * @return
	 */
	@RequestMapping("/goChoiceUser")
	private String goChoiceUser(String receiversid,HttpServletRequest request){
		try{
			request.setAttribute("receiversid", receiversid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/WEB-INF/jsp/modmessage/choiceuser";
	}
	/**
	 * 获取全部员工
	 * @return
	 */
	@RequestMapping("/getAllStaff.json")
	@ResponseBody
	private Map getAllStaff(){
		Map map=new HashMap();
		try{
			map.put("allStaff", messageService.getAllUsers());
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 保存短信
	 * @param receiverid
	 * @param messageid
	 * @param createDate
	 * @param title
	 * @param content
	 * @param session
	 * @return
	 */
	@RequestMapping("/saveMessage.json")
	@ResponseBody
	private Map saveMessage(String receiverid,String messageid,String createDate,String title,String content,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(messageService.saveMessage(((UserInfo)session.getAttribute("user")).getStaffID(), receiverid, messageid, title, content, createDate)){
				case 0:{
					flagmsg="系统出错";
					break;
				}
				case 1:{
					flagmsg="保存成功";
					break;
				}
				case 2:{
					flagmsg="保存失败";
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
	 * 发送短信
	 * @param receiverid
	 * @param messageid
	 * @param createDate
	 * @param title
	 * @param content
	 * @param session
	 * @return
	 */
	@RequestMapping("/sendMessage.json")
	@ResponseBody
	private Map sendMessage(String receiverid,String messageid,String createDate,String title,String content,HttpSession session){
		Map map=new HashMap();
		try{
			String flagmsg="";
			switch(messageService.sendMessage(((UserInfo)session.getAttribute("user")).getStaffID(), receiverid, messageid, title, content, createDate)){
				case 0:{
					flagmsg="系统出错";
					break;
				}
				case 1:{
					flagmsg="发送成功";
					break;
				}
				case 2:{
					flagmsg="发送失败";
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
	 * 读取未读短信列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/jsonNoReadMessage.json")
	@ResponseBody
	public Map jsonNoReadMessage(HttpSession session){
		Map map=new HashMap();
		try{
			map.put("noRead", messageService.getNoReadList(((UserInfo)session.getAttribute("user")).getStaffID()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
}
