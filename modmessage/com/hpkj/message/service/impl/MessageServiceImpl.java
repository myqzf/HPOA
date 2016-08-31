package com.hpkj.message.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.message.dao.impl.MessageDaoImpl;
import com.hpkj.message.entity.HpoaMessageInfo;
import com.hpkj.message.entity.HpoaMessageUserLink;
import com.hpkj.message.service.MessageService;
import com.hpkj.message.vo.NoSendMessageDetailVo;
import com.hpkj.message.vo.NoSendMessageVo;
import com.hpkj.message.vo.ReceiveMessageDetailVo;
import com.hpkj.message.vo.ReceiveMessageVo;
import com.hpkj.message.vo.SendMessageDetailVo;
import com.hpkj.message.vo.SendMessageVo;
import com.hpkj.message.vo.StaffsVo;
@Service("messageService")
@Transactional
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {
	@Resource
	private MessageDaoImpl messageDao;
	/*
	 * 根据员工id读取收件箱
	 * @see com.hpkj.message.service.MessageService#getReceiveMessageList(java.lang.String, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ReceiveMessageVo> getReceiveMessageList(String staffid, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(staffid==null||staffid.isEmpty()){
				return null;
			}
			List<ReceiveMessageVo> lrm=new ArrayList<ReceiveMessageVo>();
			String sql="";
			if(sidx==null||sord==null||sidx.isEmpty()){
				sql="select hmul.messageid as messageId , hmul.readstatus as readstatus , hmi.title as title, " +
						"sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name as senderName, " +
						"hmi.rectime as rectime " +
						"from hpoa_message_user_link hmul join hpoa_message_info hmi on hmul.messageid=hmi.messageid " +
						"join hpoa_staff_info hsi on hmul.senderid=hsi.staff_id join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
						"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
						"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
						"where hmul.recevestatus=1 and hmi.status=2 and hmul.receverid=? ";
			}else{
				sql="select hmul.messageid as messageId , hmul.readstatus as readstatus , hmi.title as title, " +
						"sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name as senderName, " +
						"hmi.rectime as rectime " +
						"from hpoa_message_user_link hmul join hpoa_message_info hmi on hmul.messageid=hmi.messageid " +
						"join hpoa_staff_info hsi on hmul.senderid=hsi.staff_id join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
						"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
						"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
						"where hmul.recevestatus=1 and hmi.status=2 and hmul.receverid=? order by "+sidx+" "+sord;
			}
			if(length>0){
				lrm=messageDao.getVOSQL(sql, ReceiveMessageVo.class, from, length, staffid);
			}else{
				lrm=messageDao.getVOSQL(sql, ReceiveMessageVo.class,staffid);
			}
			return lrm;
		}catch(Exception e){
			throw new BaseException("根据员工id读取收件箱时出错",e);
		}
	}
	/*
	 * 根据员工id读取发件箱
	 * @see com.hpkj.message.service.MessageService#getSendMessageList(java.lang.String, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<SendMessageVo> getSendMessageList(String staffid, int from, int length, String sidx, String sord)throws BaseException{
		try{
			if(staffid==null||staffid.isEmpty()){
				return null;
			}
			List<SendMessageVo> lrm=new ArrayList<SendMessageVo>();
			String sql="";
			if(sidx==null||sord==null||sidx.isEmpty()){
				sql="select hmulc.messageid as messageid, hmulc.receive as recevername, hmi.title as title , hmi.sendtime as sendtime " +
						"from(select hmul.messageid as messageid , " +
						"wm_concat(sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name) as receive " +
						"from hpoa_message_user_link hmul join HPOA_STAFF_INFO hsi on hmul.receverid=hsi.staff_id " +
						"join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
						"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
						"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
						"where hmul.sendstatus=1 and hmul.senderid=? " +
						"group by hmul.messageid) hmulc join hpoa_message_info hmi on hmi.messageid=hmulc.messageid " +
						"where hmi.status=2 ";
			}else{
				sql="select hmulc.messageid as messageid, hmulc.receive as recevername, hmi.title as title , hmi.sendtime as sendtime " +
						"from(select hmul.messageid as messageid , " +
						"wm_concat(sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name) as receive " +
						"from hpoa_message_user_link hmul join HPOA_STAFF_INFO hsi on hmul.receverid=hsi.staff_id " +
						"join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
						"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
						"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
						"where hmul.sendstatus=1 and hmul.senderid=? " +
						"group by hmul.messageid) hmulc join hpoa_message_info hmi on hmi.messageid=hmulc.messageid " +
						"where hmi.status=2  order by "+sidx+" "+sord;
			}
			if(length>0){
				lrm=messageDao.getVOSQL(sql, SendMessageVo.class, from, length, staffid);
			}else{
				lrm=messageDao.getVOSQL(sql, SendMessageVo.class,staffid);
			}
			return lrm;
		}catch(Exception e){
			throw new BaseException("根据员工id读取发件箱时出错",e);
		}
	}
	/*
	 * 根据员工id读取草稿箱
	 * @see com.hpkj.message.service.MessageService#getNoSendMessageList(java.lang.String, int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<NoSendMessageVo> getNoSendMessageList(String staffid, int from, int length, String sidx, String sord)throws BaseException{
		List<NoSendMessageVo> lnsm=new ArrayList<NoSendMessageVo>();
		try{
			if(staffid==null||staffid.isEmpty()){
				return null;
			}
			String sql="";
			if(sidx==null||sord==null||sidx.isEmpty()){
				sql="select hmulc.messageid as messageId, hmulc.receive as receversName, hmi.title as title , hmi.createtime as createTime " +
						"from(select hmul.messageid as messageid , " +
						"wm_concat(sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name) as receive " +
						"from hpoa_message_user_link hmul join HPOA_STAFF_INFO hsi on hmul.receverid=hsi.staff_id " +
						"join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
						"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
						"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
						"where hmul.senderid=? group by hmul.messageid) hmulc join hpoa_message_info hmi on hmi.messageid=hmulc.messageid " +
						"where hmi.status=1";
			}else{
				sql="select hmulc.messageid as messageId, hmulc.receive as receversName, hmi.title as title , hmi.createtime as createTime " +
						"from(select hmul.messageid as messageid , " +
						"wm_concat(sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name) as receive " +
						"from hpoa_message_user_link hmul join HPOA_STAFF_INFO hsi on hmul.receverid=hsi.staff_id " +
						"join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
						"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
						"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
						"where hmul.senderid=? group by hmul.messageid) hmulc join hpoa_message_info hmi on hmi.messageid=hmulc.messageid " +
						"where hmi.status=1 order by "+sidx+" "+sord;
			}
			if(length>0){
				lnsm=messageDao.getVOSQL(sql, NoSendMessageVo.class, from, length, staffid);
			}else{
				lnsm=messageDao.getVOSQL(sql, NoSendMessageVo.class, staffid);
			}
		}catch(Exception e){
			throw new BaseException("根据员工id读取草稿箱时出错",e);
		}
		return lnsm;
	}
	/*
	 * 根据员工id和短信id还有模式删除相应短信
	 * @see com.hpkj.message.service.MessageService#deleteMessage(java.lang.String, java.lang.String, int)
	 */
	@Override
	public int deleteMessage(String staffid,String messageid,int mode)throws BaseException{
		try{
			int flag=0;
			if(staffid==null||messageid==null||staffid.isEmpty()){
				flag=2;
				return flag;
			}
			String[] messagesId = messageid.split(",");
			List<String> messagesList= java.util.Arrays.asList(messagesId);
			String sql="";
			switch(mode){
				case 1:{
					sql="update hpoa_message_user_link hmul set hmul.recevestatus=2 where hmul.messageid=? and hmul.receverid=?";
					for(int i=0;i<messagesList.size();i++){
						messageDao.excuteSql(sql, messagesList.get(i), staffid);
					}
					messageDao.sqlcommit();
					flag=1;
					break;
				}
				case 2:{
					sql="update hpoa_message_user_link hmul set hmul.sendstatus=2 where hmul.messageid=? and hmul.senderid=?";
					for(int i=0;i<messagesList.size();i++){
						messageDao.excuteSql(sql, messagesList.get(i),staffid);
					}
					messageDao.sqlcommit();
					flag=1;
					break;
				}
				case 3:{
					sql="update hpoa_message_info hmi set hmi.status=3 where hmi.messageid=?";
					for(int i=0;i<messagesList.size();i++){
						messageDao.excuteSql(sql, messagesList.get(i));
					}
					messageDao.sqlcommit();
					flag=1;
					break;
				}
				default:{
					flag=2;
					break;
				}
			}
			return flag;
		}catch(Exception e){
			throw new BaseException("根据员工id和短信id还有模式删除相应短信时出错",e);
		}
	}
	/*
	 * 根据短信id获取收到短信的详细内容
	 * @see com.hpkj.message.service.MessageService#getReceiveByMessageid(java.lang.String)
	 */
	@Override
	public ReceiveMessageDetailVo getReceiveByMessageid(String messageid)throws BaseException{
		try{
			if(messageid==null||messageid.isEmpty()){
				return null;
			}
			ReceiveMessageDetailVo rmdv=new ReceiveMessageDetailVo();
			String sql="select hmi.title as title, " +
					"sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name as senderName, " +
					"hmi.rectime as rectime, hmi.content as content from hpoa_message_user_link hmul " +
					"join hpoa_message_info hmi on hmul.messageid=hmi.messageid join hpoa_staff_info hsi on hmul.senderid=hsi.staff_id " +
					"join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
					"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
					"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
					"where hmi.messageid=?";
			rmdv=(ReceiveMessageDetailVo)messageDao.getVOSQL(sql, ReceiveMessageDetailVo.class, messageid).get(0);
			return rmdv;
		}catch(Exception e){
			throw new BaseException("根据短信id获取收到短信的详细内容时出错",e);
		}
	}
	/*
	 * 根据短信id获取已发送短信的详细内容
	 * @see com.hpkj.message.service.MessageService#getSendByMessageid(java.lang.String)
	 */
	@Override
	public SendMessageDetailVo getSendByMessageid(String messageid)throws BaseException{
		try{
			if(messageid==null||messageid.isEmpty()){
				return null;
			}
			SendMessageDetailVo rmdv=new SendMessageDetailVo();
			String sql="select hmulc.receive as recevername, hmi.title as title , hmi.sendtime as sendtime, hmi.content as content " +
					"from(select hmul.messageid as messageid , " +
					"wm_concat(sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name) as receive " +
					"from hpoa_message_user_link hmul join HPOA_STAFF_INFO hsi on hmul.receverid=hsi.staff_id " +
					"join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
					"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
					"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
					"group by hmul.messageid) hmulc j" +
					"oin hpoa_message_info hmi on hmi.messageid=hmulc.messageid where hmi.messageid=?";
			rmdv=(SendMessageDetailVo)messageDao.getVOSQL(sql, SendMessageDetailVo.class, messageid).get(0);
			return rmdv;
		}catch(Exception e){
			throw new BaseException("根据短信id获取已发送短信的详细内容时出错",e);
		}
	}
	/*
	 * 根据短信id获取草稿的详细内容
	 * @see com.hpkj.message.service.MessageService#getNoSendByMessageid(java.lang.String)
	 */
	@Override
	public NoSendMessageDetailVo getNoSendByMessageid(String messageid)throws BaseException{
		try{
			if(messageid==null||messageid.isEmpty()){
				return null;
			}
			NoSendMessageDetailVo nrmdv=new NoSendMessageDetailVo();
			String sql="select hmulc.messageid as messageid, hmulc.receive as receversName, hmulc.receiveid as receversid , " +
					"hmi.title as title , hmi.createtime as createTime, hmi.content as content from(select hmul.messageid as messageid , " +
					"wm_concat(sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name) as receive, " +
					"wm_concat(hsi.staff_id) as receiveid from hpoa_message_user_link hmul " +
					"join HPOA_STAFF_INFO hsi on hmul.receverid=hsi.staff_id " +
					"join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
					"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
					"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
					"where hmul.messageid=? group by hmul.messageid) hmulc " +
					"join hpoa_message_info hmi on hmi.messageid=hmulc.messageid  where hmi.status=1";
			nrmdv=(NoSendMessageDetailVo)messageDao.getVOSQL(sql, NoSendMessageDetailVo.class, messageid).get(0);
			return nrmdv;
		}catch(Exception e){
			throw new BaseException("根据短信id获取草稿的详细内容时出错",e);
		}
	}
	/*
	 * 获取全部员工
	 * @see com.hpkj.message.service.MessageService#getAllUsers()
	 */
	@Override
	public List<StaffsVo> getAllUsers()throws BaseException{
		try{
			List<StaffsVo> lsv=new ArrayList<StaffsVo>();
			String sql="select hsi.staff_id as staffId, " +
					"sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name as staffName " +
					"from hpoa_staff_info hsi join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
					"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
					"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id ";
			lsv=messageDao.getVOSQL(sql, StaffsVo.class);
			return lsv;
		}catch(Exception e){
			throw new BaseException("获取全部员工时出错",e);
		}
	}
	/*
	 * 保存短消息
	 * @see com.hpkj.message.service.MessageService#saveMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int saveMessage(String staffid, String receiverid, String messageid, String title, String content, String createDate)throws BaseException{
		try{
			int flag=0;
			if(staffid==null||receiverid==null||title==null||staffid.isEmpty()||receiverid.isEmpty()||title.isEmpty()){
				flag=2;
				return flag;
			}
			String[] receivers=receiverid.split(",");
			HpoaMessageInfo hmi=new HpoaMessageInfo();
			hmi.setTitle(title);
			hmi.setContent(content);
			hmi.setStatus(1);
			if("".equals(createDate)){
				hmi.setCreatetime(DateTimeUtil.getDateTime());
			}else{
				hmi.setCreatetime(createDate);
			}
			if(!"".equals(messageid)){
				hmi.setMessageid(messageid);
			}
			messageDao.saveOrUpdateModel(hmi);
			messageDao.sqlcommit();
			String msgid=hmi.getMessageid();
			String sql="delete from hpoa_message_user_link hmul where hmul.messageid=?";
			messageDao.excuteSql(sql, msgid);
			for(int i=0;i<receivers.length;i++){
				HpoaMessageUserLink hmul=new HpoaMessageUserLink();
				hmul.setMessageid(msgid);
				hmul.setSenderid(staffid);
				hmul.setReceverid(receivers[i]);
				messageDao.saveModel(hmul);
			}
			messageDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("保存短消息时出错",e);
		}
	}
	/*
	 * 发送短消息
	 * @see com.hpkj.message.service.MessageService#sendMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int sendMessage(String staffid, String receiverid, String messageid, String title, String content, String createDate)throws BaseException{
		try{
			int flag=0;
			if(staffid==null||receiverid==null||title==null||"".equals(staffid)||"".equals(receiverid)||"".equals(title)){
				flag=2;
				return flag;
			}
			String[] receivers=receiverid.split(",");
			HpoaMessageInfo hmi=new HpoaMessageInfo();
			hmi.setTitle(title);
			hmi.setContent(content);
			hmi.setStatus(2);
			if("".equals(createDate)){
				hmi.setCreatetime(DateTimeUtil.getDateTime());
			}else{
				hmi.setCreatetime(createDate);
			}
			hmi.setSendtime(DateTimeUtil.getDateTime());
			hmi.setRectime(DateTimeUtil.getDateTime());
			if(!"".equals(messageid)){
				hmi.setMessageid(messageid);
			}
			messageDao.saveOrUpdateModel(hmi);
			messageDao.sqlcommit();
			String msgid=hmi.getMessageid();
			String sql="delete from hpoa_message_user_link hmul where hmul.messageid=?";
			messageDao.excuteSql(sql, msgid);
			for(int i=0;i<receivers.length;i++){
				HpoaMessageUserLink hmul=new HpoaMessageUserLink();
				hmul.setMessageid(msgid);
				hmul.setSenderid(staffid);
				hmul.setReceverid(receivers[i]);
				hmul.setReadstatus(2);
				hmul.setSendstatus(1);
				hmul.setRecevestatus(1);
				messageDao.saveModel(hmul);
			}
			messageDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("发送短消息时出错",e);
		}
	}
	/*
	 * 根据员工id和短信id修改阅读状态
	 * @see com.hpkj.message.service.MessageService#editReadStatus(java.lang.String, java.lang.String)
	 */
	@Override
	public void editReadStatus(String staffid,String messageid)throws BaseException{
		try{
			if(staffid==null||messageid==null||"".equals(staffid)||"".equals(messageid)){
				return;
			}
			String sql="update hpoa_message_user_link hmul set hmul.readstatus=1, hmul.rectime=? where hmul.messageid=? and hmul.receverid=?";
			messageDao.excuteSql(sql, DateTimeUtil.getDateTime(), messageid, staffid);
			messageDao.sqlcommit();
		}catch(Exception e){
			throw new BaseException("根据员工id和短信id修改阅读状态时出错",e);
		}
	}
	/*
	 * 根据员工id获取未读短信数量
	 * @see com.hpkj.message.service.MessageService#getNoReadCount(java.lang.String)
	 */
	@Override
	public String getNoReadCount(String staffid)throws BaseException{
		try{
			String count="";
			if(staffid==null||"".equals(staffid)){
				count="0";
				return count;
			}
			String sql="select count(hmul.id) from hpoa_message_user_link hmul " +
					"where hmul.receverid=? and hmul.readstatus=2 and hmul.recevestatus=1";
			count=messageDao.queryForString(sql, staffid);
			return count;
		}catch(Exception e){
			throw new BaseException("根据员工id获取未读短信数量",e);
		}
	}
	/*
	 * 根据员工id获取未读短信
	 * @see com.hpkj.message.service.MessageService#getNoReadList(java.lang.String)
	 */
	@Override
	public List<ReceiveMessageVo> getNoReadList(String staffid)throws BaseException{
		try{
			if(staffid==null||"".equals(staffid)){
				return null;
			}
			List<ReceiveMessageVo> lrmv=new ArrayList<ReceiveMessageVo>();
			String sql="select hmul.messageid as messageId , hmul.readstatus as readstatus , hmi.title as title, " +
					"sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name as senderName, " +
					"hmi.rectime as rectime " +
					"from hpoa_message_user_link hmul join hpoa_message_info hmi on hmul.messageid=hmi.messageid " +
					"join hpoa_staff_info hsi on hmul.senderid=hsi.staff_id join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
					"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
					"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id " +
					"where hmul.recevestatus=1 and hmi.status=2 and hmul.readstatus=2 and hmul.receverid=? ";
			lrmv=messageDao.getVOSQL(sql, ReceiveMessageVo.class, staffid);
			return lrmv;
		}catch(Exception e){
			throw new BaseException("根据员工id获取未读短信时出错",e);
		}
	}
}
