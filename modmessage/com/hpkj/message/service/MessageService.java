package com.hpkj.message.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.message.vo.NoSendMessageDetailVo;
import com.hpkj.message.vo.NoSendMessageVo;
import com.hpkj.message.vo.ReceiveMessageDetailVo;
import com.hpkj.message.vo.ReceiveMessageVo;
import com.hpkj.message.vo.SendMessageDetailVo;
import com.hpkj.message.vo.SendMessageVo;
import com.hpkj.message.vo.StaffsVo;

public interface MessageService extends IBaseService {
	/**
	 * 根据员工id读取收件箱
	 * @param staffid 员工Id
	 * @param from 起始长度
	 * @param length 每页长度
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @return 收到信息列表
	 * @throws BaseException
	 */
	public List<ReceiveMessageVo> getReceiveMessageList(String staffid, int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 根据员工id读取发件箱
	 * @param staffid 员工Id
	 * @param from 起始长度
	 * @param length 每页长度
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @return 发送信息列表
	 * @throws BaseException
	 */
	public List<SendMessageVo> getSendMessageList(String staffid, int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 根据员工id读取草稿箱
	 * @param staffid 员工Id
	 * @param from 起始长度
	 * @param length 每页长度
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @return 草稿箱列表
	 * @throws BaseException
	 */
	public List<NoSendMessageVo> getNoSendMessageList(String staffid, int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 根据员工id和短信id还有模式删除相应短信
	 * @param staffid 员工id
	 * @param messageid 短信id
	 * @param mode 1收件箱,2发件箱,3草稿箱
	 * @return 返回状态：0系统出错，1删除成功，2删除失败
	 * @throws BaseException
	 */
	public int deleteMessage(String staffid,String messageid,int mode)throws BaseException;
	/**
	 * 根据短信id获取收到短信的详细内容
	 * @param messageid 短信Id
	 * @return 收到短信的详细内容
	 * @throws BaseException
	 */
	public ReceiveMessageDetailVo getReceiveByMessageid(String messageid)throws BaseException;
	/**
	 * 根据短信id获取已发送短信的详细内容
	 * @param messageid 短信Id
	 * @return 收到短信的详细内容
	 * @throws BaseException
	 */
	public SendMessageDetailVo getSendByMessageid(String messageid)throws BaseException;
	/**
	 * 根据短信id获取草稿的详细内容
	 * @param messageid 短信Id
	 * @return 草稿的详细内容
	 * @throws BaseException
	 */
	public NoSendMessageDetailVo getNoSendByMessageid(String messageid)throws BaseException;
	/**
	 * 获取全部员工
	 * @return
	 * @throws BaseException
	 */
	public List<StaffsVo> getAllUsers()throws BaseException;
	/**
	 * 保存短消息
	 * @param staffid 员工id
	 * @param receiverid 收信人id
	 * @param messageid 短信id
	 * @param title 标题
	 * @param content 正文
	 * @param createDate 创建时间
	 * @return 返回状态：0系统出错，1保存成功，2保存失败
	 * @throws BaseException
	 */
	public int saveMessage(String staffid, String receiverid, String messageid, String title, String content, String createDate)throws BaseException;
	/**
	 * 发送短消息
	  * @param staffid 员工id
	 * @param receiverid 收信人id
	 * @param messageid 短信id
	 * @param title 标题
	 * @param content 正文
	 * @param createDate 创建时间
	 * @return 返回状态：0系统出错，1发送成功，2发送失败
	 * @throws BaseException
	 */
	public int sendMessage(String staffid, String receiverid, String messageid, String title, String content, String createDate)throws BaseException;
	/**
	 * 根据员工id和短信id修改阅读状态
	 * @param staffid 员工id
	 * @param messageid 短信id
	 * @throws BaseException
	 */
	public void editReadStatus(String staffid,String messageid)throws BaseException;
	/**
	 * 根据员工id获取未读短信数量
	 * @param staffid 员工id
	 * @return 未读短信数量
	 * @throws BaseException
	 */
	public String getNoReadCount(String staffid)throws BaseException;
	/**
	 * 根据员工id获取未读短信
	 * @param staffid 员工id
	 * @return
	 * @throws BaseException
	 */
	public List<ReceiveMessageVo> getNoReadList(String staffid)throws BaseException;
}
