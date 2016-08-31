package com.hpkj.forum.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.forum.dao.ForumDao;
import com.hpkj.forum.entity.BbsCardInfo;
import com.hpkj.forum.entity.BbsResponseInfo;
import com.hpkj.forum.entity.BbsSessionInfo;
import com.hpkj.forum.entity.BbsUserInfo;
import com.hpkj.forum.service.ForumService;



/**
 * 论坛
 * @author 任建波
 *
 */
@Service("forumService")
public class ForumServiceImpl extends BaseServiceImpl implements ForumService{

	@Resource
	private ForumDao forumDao;
	
	
	//**********************板块部分***************************************
	
	/*
	 * 查询板块列表
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#searchForumList(java.lang.String)
	 */
	@Override
	public List searchForumList(String masterId) throws BaseException {
		List<BbsSessionInfo> forumList = new ArrayList<BbsSessionInfo>();

		try{			
			if(masterId!=null){
				forumList = forumDao.getHqlList(" from BbsSessionInfo s where s.masterId=? order by s.sort desc",masterId);
			}else{
				forumList = forumDao.getHqlList(" from BbsSessionInfo s order by s.sort desc");
			}			
		}catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("查询板块列表时出错",e);
		}

		return forumList;
	}

	/*
	 * 新增板块
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#saveForum(com.hpkj.forum.entity.BbsSessionInfo)
	 */
	@Override
	public void saveForum(BbsSessionInfo bbsSessionInfo) throws BaseException {
		try{	
		//获取最大sort的值，并加一，排序用
		String sql = "select max(to_number(SORT)) from BBS_SESSION_INFO";
		String sort = forumDao.queryForString(sql);
		if(sort.isEmpty()){
			bbsSessionInfo.setSort("1");
		}else{
			int a = Integer.parseInt(sort)+1;
			bbsSessionInfo.setSort(a+"");
		}

		forumDao.saveModel(bbsSessionInfo);
		forumDao.sqlcommit();
		}catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("新增板块时出错",e);
		}
	}	

	/*
	 * 根据ID获取板块
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#getForumByID(java.lang.String)
	 */
	@Override
	public BbsSessionInfo getForumByID(String sessionId) throws BaseException {
		try{
			if(sessionId==null){
				throw new BaseException("根据ID获取板块时板块ID为空");
			}
		BbsSessionInfo bbsSessionInfo = forumDao.getModelById(BbsSessionInfo.class,sessionId);
		
		return bbsSessionInfo;	
		
		}catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("根据ID获取板块时出错",e);
		}
	}	

	/*
	 * 修改板块
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#updateForum(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateForum(String sessionId,String sessionName) throws BaseException {
		try{		
			if(sessionId==null){
				throw new BaseException("修改板块时板块ID为空");
			}
		BbsSessionInfo sessionInfo = forumDao.getModelById(BbsSessionInfo.class,sessionId);

		sessionInfo.setSessionName(sessionName);		
		forumDao.updateModel(sessionInfo);
		forumDao.sqlcommit();
		
		}catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("根据ID获取板块时出错",e);
		}
	}

	/*
	 * 删除板块
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#delecteForum(java.lang.String)
	 */
	@Override
	public void delecteForum(String sessionId) throws BaseException {
		try{	
			if(sessionId==null){
				throw new BaseException("删除板块时板块ID为空");
			}
			
		forumDao.deleteModelById(BbsSessionInfo.class, sessionId);
		forumDao.sqlcommit();
		}catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("删除板块时出错",e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	//**********************帖子部分***************************************


	/*
	 * 查询帖子列表
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#searchCardList(java.lang.String, int, int)
	 */
	@Override
	public List searchCardList(String sessionId,int from,int length) throws BaseException {
		List<BbsCardInfo> cardList = new ArrayList<BbsCardInfo>();
			
		try{
			if(sessionId==null){
				throw new BaseException("查询帖子列表时板块ID为空");
			}
			
		if(length!=0){
			cardList = forumDao.getHqlList(" from BbsCardInfo c where c.csid = ? order by to_number(c.bak1) desc ",from,length,sessionId);
		}else{
			cardList = forumDao.getHqlList(" from BbsCardInfo c where c.csid = ? order by to_number(c.bak1) desc ",sessionId);
		
			//板块点击数+1
			BbsSessionInfo bbsSessionInfo = forumDao.getModelById(BbsSessionInfo.class, sessionId);
			if(bbsSessionInfo.getClickCount()==null){
				bbsSessionInfo.setClickCount(1);
			}else{
				bbsSessionInfo.setClickCount(bbsSessionInfo.getClickCount()+1);
			}
			forumDao.updateModel(bbsSessionInfo);
			forumDao.sqlcommit();
		
		}
		}catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("查询帖子列表时出错",e);
		}
		
			return cardList;		
	}
	
	
	/*
	 * 新增帖子
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#saveCard(com.hpkj.forum.entity.BbsCardInfo)
	 */
	@Override
	public void saveCard(BbsCardInfo bbsCardInfo) throws BaseException {
		//本论坛帖子集合
		List<BbsCardInfo> list = forumDao.getHqlList(" from BbsCardInfo c where c.csid=? ", bbsCardInfo.getCsid());
		try{
		//把本版块是否最新cisnew制空
		for(int i=0;i<list.size();i++){

			if(list.get(i).getCisnew()!=null){
				BbsCardInfo cardInfo = forumDao.getModelById(BbsCardInfo.class, list.get(i).getCid());
				cardInfo.setCisnew(null);

				forumDao.updateModel(cardInfo);
				forumDao.sqlcommit();
				break;
			}
		}		
		bbsCardInfo.setCtime(DateTimeUtil.getDateTime("yyyy-MM-dd HH:mm:ss"));
		//设置为最新帖子
		bbsCardInfo.setCisnew("1");
		//是否置顶cistop设为0默认为普通帖子
		bbsCardInfo.setCistop("0");
		
		//获取最大bak1的值，并加一，排序用
		String sql = "select max(to_number(bak1)) from BBS_CARD_INFO";
		String bak1 = forumDao.queryForString(sql);
		if(bak1.isEmpty()){
			bbsCardInfo.setBak1("1");
		}else{
			int a = Integer.parseInt(bak1)+1;
			bbsCardInfo.setBak1(a+"");
		}		
		forumDao.saveModel(bbsCardInfo);
		forumDao.sqlcommit();
		
		
		//员工积分增加，等级称号更改		
		List userCardList = forumDao.getHqlList("FROM BbsCardInfo c where c.cuid=? ",bbsCardInfo.getCuid());
		List<BbsUserInfo> bbsUserInfo = forumDao.getHqlList("FROM BbsUserInfo u where u.staffId=? ", bbsCardInfo.getCuid());
		if(userCardList.size()==0){
			bbsUserInfo.get(0).setBbsLevel("刚出生");
			bbsUserInfo.get(0).setPopstar("1");
		}
		else if(userCardList.size()<=2){
			bbsUserInfo.get(0).setBbsLevel("求仙路上");
			bbsUserInfo.get(0).setPopstar("2");
		}else if (userCardList.size()<=5) {
			bbsUserInfo.get(0).setBbsLevel("土地爷");
			bbsUserInfo.get(0).setPopstar("3");
		}else if(userCardList.size()<=10){
			bbsUserInfo.get(0).setBbsLevel("玉皇大帝");
			bbsUserInfo.get(0).setPopstar("4");
		}else {
			bbsUserInfo.get(0).setBbsLevel("元始天尊");
			bbsUserInfo.get(0).setPopstar("5");
		}		
		bbsUserInfo.get(0).setPoint(userCardList.size()+1);
		for(BbsUserInfo bbsUser : bbsUserInfo){
			forumDao.updateModel(bbsUser);
			forumDao.sqlcommit();
		}		
		
		
		//板块的点击数+1
		BbsSessionInfo bbsSessionInfo = forumDao.getModelById(BbsSessionInfo.class, bbsCardInfo.getCsid());
			bbsSessionInfo.setTopCount(list.size()+1);
			forumDao.updateModel(bbsSessionInfo);
			forumDao.sqlcommit();
		}catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("新增帖子时出错",e);
		}
	}	

	
	
	
	/*
	 * 置顶帖子
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#toTop(java.lang.String)
	 */
	@Override
	public void toTop(String cid) throws BaseException {		
		
		try {
			if(cid==null){
				throw new BaseException("置顶帖子时帖子id为空");
			}
			
			//获取最大bak1的值，并加一，排序用
			BbsCardInfo bbsCardInfo = forumDao.getModelById(BbsCardInfo.class, cid);
			String sql = "select max(to_number(bak1)) from BBS_CARD_INFO";
			String bak1 = forumDao.queryForString(sql);			
			
			if(bak1.isEmpty()){
				//如果bak1为空，则设置为1
				bbsCardInfo.setCistop("1");
			}else{
				int a = Integer.parseInt(bak1)+1;
				bbsCardInfo.setBak1(a+"");
				bbsCardInfo.setCistop("1");
			}	
			
			forumDao.updateModel(bbsCardInfo);
			forumDao.sqlcommit();			
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("置顶帖子时出错",e);
		}
	}	
	
	
	/*
	 * 查询最新帖子列表
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#searchNewCard(java.lang.String)
	 */
	@Override
	public List searchNewCard(String sessionId) throws BaseException {
		List<BbsCardInfo> newCard = new ArrayList<BbsCardInfo>();
		try {
			if(sessionId==null){
				throw new BaseException("查询最新帖子列表时板块id为空");
			}
			
		newCard = forumDao.getHqlList(" from BbsCardInfo c where c.csid = ? and c.cisnew = '1' ",sessionId);
			return newCard;	
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("查询最新帖子列表时出错",e);
		}
	}
	
	
	/*
	 * 根据ID查询帖子列表
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#getBbsCardInfo(java.lang.String)
	 */
	@Override
	public BbsCardInfo getBbsCardInfo(String cid) throws BaseException {
		BbsCardInfo cardInfo = null;
		try {
			if(cid==null){
				throw new BaseException("根据ID查询帖子列表时帖子id为空");
			}
			
			cardInfo = forumDao.getModelById(BbsCardInfo.class, cid);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("根据ID查询帖子列表时出错",e);
		}
		return cardInfo;
	}	
	
	
	
	//************************跟帖部分*************************************
	
	
	/*
	 * 查询跟帖列表
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#searchResponseList(java.lang.String, int, int)
	 */
	@Override
	public List searchResponseList(String cid,Integer from,Integer length) throws BaseException {
		List<BbsResponseInfo> responseList = new ArrayList<BbsResponseInfo>();
		
		try {
			if(cid==null||from==null||length==null){
				throw new BaseException("查询跟帖列表时帖子id为空或from，length为空");
			}
			
		//帖子点击数+1
		BbsCardInfo bbsCardInfo = forumDao.getModelById(BbsCardInfo.class, cid);
		if(bbsCardInfo.getCclickcount()==null){
			bbsCardInfo.setCclickcount(1);
		}else{
			bbsCardInfo.setCclickcount(bbsCardInfo.getCclickcount()+1);
		}
		forumDao.updateModel(bbsCardInfo);
		forumDao.sqlcommit();		
		
		if(length!=0){
			responseList = forumDao.getHqlList(" from BbsResponseInfo r where r.rcid = ? order by to_number(r.bak1) asc  ",from,length,cid);
		}else{
			responseList = forumDao.getHqlList(" from BbsResponseInfo r where r.rcid = ? order by to_number(r.bak1) asc  ",cid);
		}
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("查询跟帖列表时出错",e);
		}

		return responseList;
	}


	/*
	 * 回复帖子
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#reply(com.hpkj.forum.entity.BbsResponseInfo)
	 */
	@Override
	public void reply(BbsResponseInfo bbsResponseInfo) throws BaseException {
		try {		
		bbsResponseInfo.setRtime(DateTimeUtil.getDateTime("yyyy-MM-dd HH:mm:ss"));

		//获取最大bak1的值，并加一，排序用
		String sql = "select max(to_number(bak1)) from BBS_RESPONSE_INFO";
		String bak1 = forumDao.queryForString(sql);
		if(bak1.isEmpty()){
			bbsResponseInfo.setBak1("1");
		}else{
			int a = Integer.parseInt(bak1)+1;
			bbsResponseInfo.setBak1(a+"");
		}			
		forumDao.saveModel(bbsResponseInfo);
		forumDao.sqlcommit();
		
		
		BbsCardInfo bbsCardInfo = forumDao.getModelById(BbsCardInfo.class, bbsResponseInfo.getRcid());
		//向BbsCardInfo表中添加回复时间
		bbsCardInfo.setClastrtime(DateTimeUtil.getDateTime("yyyy-MM-dd HH:mm:ss"));
		//向BbsCardInfo表中添加回复人ID
		bbsCardInfo.setClastr(bbsResponseInfo.getRuid());
		
		forumDao.updateModel(bbsCardInfo);
		forumDao.sqlcommit();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("回复帖子时出错",e);
		}
	}


	
	
	//************************论坛人员表部分***************************************
	
	
	/*
	 * 根据员工ID查询论坛会员
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#getBbsUserInfo(java.lang.String)
	 */
	@Override
	public List getBbsUserInfo(String staffId) throws BaseException{
		
		List<BbsUserInfo> list = null;
		try {
			if(staffId==null){
				throw new BaseException("根据员工ID查询论坛会员时员工ID为空");
			}
			
			list = forumDao.getHqlList(" from BbsUserInfo s where s.staffId=? ", staffId);
			System.out.println(list);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			throw new BaseException("根据员工ID查询论坛会员时出错",e);
		}
		return list;
	}
	
	
	/*
	 * 注册论坛账号
	 */
//	@Override
//	public void register(BbsUserInfo bbsUserInfo) throws BaseException{
//		
//		forumDao.saveModel(bbsUserInfo);
//		forumDao.sqlcommit();
//	}
/*
	 * 根据员工id上传照片,modstaff用，修改员工图片时同时修改论坛人员表中图片
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#modifyPhoto(java.lang.String, java.lang.String)
	 */
	@Override
	public int modifyPhoto(String staffId, String photoUrl) throws BaseException {
		int flag = 0;// 标识状态：0系统错误，1上传成功，2论坛人员表中没有改用户
		try {
			flag = 2;
			
			List list = forumDao.getHqlList("from BbsUserInfo u where u.staffId=?",staffId);
			if(list.size()!=0){
				
				String sql = "update BBS_USER_INFO u set u.PSURL=? where u.STAFF_ID=?";
				forumDao.excuteSql(sql, photoUrl, staffId);
				flag = 1;				
			}			

		} catch (Exception e) {
			flag = 0;
			throw new BaseException("根据员工ID修改员工照片链接时出错", e);
		}
		return flag;
	}
	
	/*
	 * 修改论坛用户表
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#updateBbsUser(com.hpkj.forum.entity.BbsUserInfo)
	 */
	@Override
	public void updateBbsUser(BbsUserInfo bbsUserInfo) throws BaseException {
		try {
		forumDao.updateModel(bbsUserInfo);
		forumDao.sqlcommit();
		} catch (Exception e) {
			throw new BaseException("修改论坛用户表时出错", e);
		}
	}
	
	
	
	/*
	 * 员工表中的员工添加到论坛用户表
	 * (non-Javadoc)
	 * @see com.hpkj.forum.service.ForumService#saveBbsUser(com.hpkj.forum.entity.BbsUserInfo)
	 */
	@Override
	public void saveBbsUser(BbsUserInfo bbsUserInfo) throws BaseException {
		try {
		forumDao.saveModel(bbsUserInfo);
		forumDao.sqlcommit();
		} catch (Exception e) {
			throw new BaseException("员工表中的员工添加到论坛用户表时出错", e);
		}
	}
	
	
	
}
