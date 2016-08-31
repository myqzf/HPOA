package com.hpkj.notice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.notice.dao.impl.NoticeDaoImpl;
import com.hpkj.notice.entity.HpoaNoticeInfo;
import com.hpkj.notice.service.NoticeService;
import com.hpkj.notice.vo.NoticeListVo;
import com.hpkj.notice.vo.NoticeReadVo;
import com.hpkj.notice.vo.NoticeScrollListVo;
@Service("noticeService")
@Transactional
public class NoticeServiceImpl extends BaseServiceImpl implements NoticeService {
	@Resource
	private NoticeDaoImpl noticeDao;
	/*
	 * 根据员工id获取滚动公告列表
	 * @see com.hpkj.notice.service.NoticeService#getNoticeScrollList(java.lang.String)
	 */
	@Override
	public List<NoticeScrollListVo> getNoticeScrollList(String staffid)throws BaseException{
		try{
			if(staffid==null||staffid.isEmpty()){
				return null;
			}
			List<NoticeScrollListVo> lnslv=new ArrayList<NoticeScrollListVo>();
			String sql="select hni.noticeid as noticeid, " +
					"(case when hni.istop='1' then '[置顶]'end) || (case when hni.isnew='1'then '(最新)' end) || hni.title as title, " +
					"hni.title as realTitle from hpoa_notice_info hni join hpoa_staff_info hsi " +
					"on hni.staff_comp=hsi.staff_comp where hsi.staff_id=? and hni.bak1='1' order by hni.istop,hni.noticetime desc";
			lnslv=noticeDao.getVOSQL(sql, NoticeScrollListVo.class, staffid);
			for(int i=0;i<lnslv.size();i++){
				if(lnslv.get(i).getTitle().length()>20){
					lnslv.get(i).setTitle(lnslv.get(i).getTitle().substring(0, 20)+"...");
				}
			}
			return lnslv;
		}catch(Exception e){
			throw new BaseException("根据员工id获取滚动公告列表时出错",e);
		}
	}
	/*
	 * 获取公告用来阅读的详细信息
	 * @see com.hpkj.notice.service.NoticeService#getReadNotice(java.lang.String)
	 */
	@Override
	public NoticeReadVo getReadNotice(String noticeid)throws BaseException{
		try{
			if(noticeid==null||noticeid.isEmpty()){
				return null;
			}
			NoticeReadVo nrv=new NoticeReadVo();
			String sql="select t.title as title, t.content as content, t.noticetime as noticetime, " +
					"t.author as author from HPOA_NOTICE_INFO t where t.noticeid=?";
			nrv=(NoticeReadVo)noticeDao.getVOSQL(sql, NoticeReadVo.class, noticeid).get(0);
			return nrv;
		}catch(Exception e){
			throw new BaseException("获取公告用来阅读的详细信息时出错",e);
		}
	}
	/*
	 * 发布公告
	 * @see com.hpkj.notice.service.NoticeService#publishNotice(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int publishNotice(String title,String content,String staffid)throws BaseException{
		try{
			int flag=0;
			if(staffid==null||title==null||staffid.isEmpty()||title.isEmpty()){
				flag=2;
				return flag;
			}
			String comp=noticeDao.queryForString("select t.staff_comp from HPOA_STAFF_INFO t where t.staff_id=?", staffid);
			String authorsql="select sdicomp.items_short || ' ' || sdidep.items_name || ' ' || sdiposi.items_name || ' '|| hsi.staff_name " +
					"from hpoa_staff_info hsi join sys_dict_items sdidep on hsi.staff_dept=sdidep.items_id " +
					"join sys_dict_items sdicomp on hsi.staff_comp=sdicomp.items_id " +
					"join sys_dict_items sdiposi on hsi.staff_posi=sdiposi.items_id where hsi.staff_id=?";
			String author=noticeDao.queryForString(authorsql, staffid);
			//将该公司其他公告的最新状态设置为否
			String updateold="update hpoa_notice_info hni set hni.isnew=2 where hni.staff_comp=?";
			noticeDao.excuteSql(updateold, comp);
			noticeDao.sqlcommit();
			HpoaNoticeInfo hni=new HpoaNoticeInfo();
			hni.setAuthor(author);
			hni.setContent(content);
			hni.setIsnew("1");
			hni.setIstop("2");
			hni.setBak1("1");
			hni.setNoticetime(DateTimeUtil.getDateTime());
			hni.setStaffComp(comp);
			hni.setStaffId(staffid);
			hni.setTitle(title);
			flag=2;
			noticeDao.saveModel(hni);
			noticeDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("发布公告时出错",e);
		}
	}
	/*
	 * 获取公告列表
	 * @see com.hpkj.notice.service.NoticeService#getNoticeList(int, int, java.lang.String, java.lang.String)
	 */
	public List<NoticeListVo> getNoticeList(int from, int length, String sidx, String sord)throws BaseException{
		try{
			List<NoticeListVo> lnlv=new ArrayList<NoticeListVo>();
			String sql="";
			if(sidx==null||sidx.isEmpty()||sord==null){
				sql="select hni.noticeid as noticeid, " +
						"(case when hni.istop='1' then '[置顶]'end) || (case when hni.isnew='1'then '(最新)' end) || hni.title as title, " +
						"hni.noticetime as noticeTime,hni.author as author,items_name as comp ,hni.istop as isTop " +
						"from HPOA_NOTICE_INFO hni join sys_dict_items sdi on hni.staff_comp=sdi.items_id where hni.bak1='1' order by hni.istop asc, hni.isnew";
			}else{
				sql="select hni.noticeid as noticeid, " +
						"(case when hni.istop='1' then '[置顶]'end) || (case when hni.isnew='1'then '(最新)' end) || hni.title as title, " +
						"hni.noticetime as noticeTime,hni.author as author,items_name as comp ,hni.istop as isTop " +
						"from HPOA_NOTICE_INFO hni join sys_dict_items sdi on hni.staff_comp=sdi.items_id where hni.bak1='1' order by hni.istop, hni.isnew asc, "+sidx+" "+sord;
			}
			if(length>0){
				lnlv=noticeDao.getVOSQL(sql, NoticeListVo.class, from, length);
			}else{
				lnlv=noticeDao.getVOSQL(sql, NoticeListVo.class);
			}
			return lnlv;
		}catch(Exception e){
			throw new BaseException("获取公告列表时出错",e);
		}
	}
	/*
	 * 获取全部公司id与全称
	 * @see com.hpkj.notice.service.NoticeService#getCompList()
	 */
	public List getCompList()throws BaseException{
		try{
			List li=new ArrayList();
			//pid为108时查询全部公司信息
			String sql="select sdi.items_id || ':' || items_name as comp from sys_dict_items sdi where sdi.pid='108'";
			li=noticeDao.getSqlList(sql);
			return li;
		}catch(Exception e){
			throw new BaseException("获取全部公司id与全称时出错",e);
		}
	}
	/*
	 * 设置公告是否置顶
	 * @see com.hpkj.notice.service.NoticeService#setTop(java.lang.String, java.lang.String)
	 */
	public int setTop(String noticeid,String stauts)throws BaseException{
		try{
			int flag=0;
			if(noticeid==null||stauts==null||noticeid.isEmpty()||stauts.isEmpty()){
				flag=2;
				return flag;
			}
			String sql="update hpoa_notice_info hni set hni.istop=? where hni.noticeid=?";
			noticeDao.excuteSql(sql, stauts,noticeid);
			noticeDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("设置公告是否置顶时出错",e);
		}
	}
	/*
	 * 修改公告
	 * @see com.hpkj.notice.service.NoticeService#editNotice(java.lang.String, java.lang.String, java.lang.String)
	 */
	public int editNotice(String noticeid, String title, String content)throws BaseException{
		try{
			int flag=0;
			if(noticeid==null||title==null||noticeid.isEmpty()||title.isEmpty()){
				flag=2;
				return flag;
			}
			String sql="update hpoa_notice_info hni set hni.title=?,hni.content=? where hni.noticeid=?";
			noticeDao.excuteSql(sql, title, content, noticeid);
			noticeDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("修改公告时出错",e);
		}
	}
	/*
	 * 删除公告
	 * @see com.hpkj.notice.service.NoticeService#delNotice(java.lang.String)
	 */
	public int delNotice(String noticeid)throws BaseException{
		try{
			int flag=0;
			if(noticeid==null||noticeid.isEmpty()){
				flag=2;
				return flag;
			}
			String[] noticeids = noticeid.split(",");
			String sql="update hpoa_notice_info hni set hni.bak1='2' where hni.noticeid=?";
			for(int i=0;i<noticeids.length;i++){
				noticeDao.excuteSql(sql, noticeids[i]);
			}
			noticeDao.sqlcommit();
			flag=1;
			return flag;
		}catch(Exception e){
			throw new BaseException("删除公告时出错",e);
		}
	}
}
