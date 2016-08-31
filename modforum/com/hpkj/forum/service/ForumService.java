package com.hpkj.forum.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.forum.entity.BbsCardInfo;
import com.hpkj.forum.entity.BbsResponseInfo;
import com.hpkj.forum.entity.BbsSessionInfo;
import com.hpkj.forum.entity.BbsUserInfo;


public interface ForumService extends IBaseService{
	
	
	/**
	 * 查询板块列表
	 * @param masterId 版主id
	 * @return
	 * @throws BaseException
	 */
	public List searchForumList(String masterId) throws BaseException;

	
	/**
	 * 查询帖子列表
	 * @param sessionId 板块id
	 * @param from 分页开始数据
	 * @param length 每页显示数据长度
	 * @return
	 * @throws BaseException
	 */
	public List searchCardList(String sessionId,int from,int length) throws BaseException;
	
	/**
	 * 查询跟帖列表
	 * @param cid 帖子id
	 * @param from 分页开始数据
	 * @param length 每页显示数据长度
	 * @return
	 * @throws BaseException
	 */
	public List searchResponseList(String cid,Integer from,Integer length) throws BaseException;

	/**
	 * 回复帖子
	 * @param bbsResponseInfo 跟帖表实体
	 * @throws BaseException
	 */
	public void reply(BbsResponseInfo bbsResponseInfo) throws BaseException;
	
	
	/**
	 * 新增板块
	 * @param bbsSessionInfo 板块实体
	 * @throws BaseException
	 */
	public void saveForum(BbsSessionInfo bbsSessionInfo) throws BaseException;
	
	
	/**
	 * 根据ID获取板块
	 * @param sessionId 板块id
	 * @return 
	 */
	public BbsSessionInfo getForumByID(String sessionId) throws BaseException;

	
	
	
	/**
	 * 修改板块
	 * @param sessionId 板块id
	 * @param sessionName 板块名称
	 * @throws BaseException
	 */
	public void updateForum(String sessionId,String sessionName) throws BaseException;


	/**
	 * 删除板块
	 * @param sessionId 板块id
	 * @throws BaseException
	 */
	public void delecteForum(String sessionId) throws BaseException;

	/**
	 * 新增帖子
	 * @param bbsCardInfo 帖子实体
	 * @throws BaseException
	 */
	public void saveCard(BbsCardInfo bbsCardInfo) throws BaseException;


	/**
	 * 根据员工ID查询论坛会员
	 * @param userId
	 * @return
	 * @throws BaseException
	 */
	public List getBbsUserInfo(String userId) throws BaseException;


	/**
	 * 注册论坛账号
	 */
//	public void register(BbsUserInfo bbsUserInfo) throws BaseException;


	/**
	 * 根据ID查询帖子列表
	 * @param cid 帖子id
	 * @return
	 * @throws BaseException
	 */
	public BbsCardInfo getBbsCardInfo(String cid) throws BaseException;


	/**
	 * 查询最新帖子列表
	 * @param sessionId 板块id
	 * @return
	 * @throws BaseException
	 */
	public List searchNewCard(String sessionId) throws BaseException;

	/**
	 * 置顶帖子
	 * @param cid 帖子id
	 * @throws BaseException
	 */
	public void toTop(String cid) throws BaseException;

	/**
	 * 根据员工id上传照片
	 * @param staffId  员工id
	 * @param photoUrl 照片路径
	 * @return
	 * @throws BaseException
	 */
	public int modifyPhoto(String staffId, String photoUrl) throws BaseException;

	/**
	 * 新增论坛用户
	 * @param bbsUserInfo 
	 * @throws BaseException
	 */
	public void saveBbsUser(BbsUserInfo bbsUserInfo) throws BaseException;

	/**
	 * 修改论坛用户表
	 */
	public void updateBbsUser(BbsUserInfo bbsUserInfo) throws BaseException;

	
}
