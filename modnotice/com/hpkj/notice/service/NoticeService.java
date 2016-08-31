package com.hpkj.notice.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.notice.vo.NoticeListVo;
import com.hpkj.notice.vo.NoticeReadVo;
import com.hpkj.notice.vo.NoticeScrollListVo;

public interface NoticeService extends IBaseService {
	/**
	 * 根据员工id获取滚动公告列表
	 * @param staffid 员工id
	 * @return 滚动公告列表
	 * @throws BaseException
	 */
	public List<NoticeScrollListVo> getNoticeScrollList(String staffid)throws BaseException;
	/**
	 * 获取公告用来阅读的详细信息
	 * @param noticeid 公告id
	 * @return 用来阅读的公告详细信息
	 * @throws BaseException
	 */
	public NoticeReadVo getReadNotice(String noticeid)throws BaseException;
	/**
	 * 发布公告
	 * @param title 标题
	 * @param content 正文
	 * @param staffid 发布者id
	 * @return 0系统繁忙,1发布成功,2发布失败
	 * @throws BaseException
	 */
	public int publishNotice(String title,String content,String staffid)throws BaseException;
	/**
	 * 获取公告列表
	 * @param from 起始长度
	 * @param length 每页长度
	 * @param sidx 排序字段
	 * @param sord 排序方式
	 * @return 公告列表
	 * @throws BaseException
	 */
	public List<NoticeListVo> getNoticeList(int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 获取全部公司id与全称
	 * @return 公司id与全称
	 * @throws BaseException
	 */
	public List getCompList()throws BaseException;
	/**
	 * 设置公告是否置顶
	 * @param noticeid 公告id 
	 * @param stauts 状态:1置顶,2不置顶
	 * @return 0系统繁忙,1设置成功,2设置失败
	 * @throws BaseException
	 */
	public int setTop(String noticeid,String stauts)throws BaseException;
	/**
	 * 修改公告
	 * @param noticeid 公告id
	 * @param title 标题
	 * @param content 正文
	 * @return 0系统繁忙,1修改成功,2修改失败
	 * @throws BaseException
	 */
	public int editNotice(String noticeid, String title, String content)throws BaseException;
	/**
	 * 删除公告
	 * @param noticeid 公告id
	 * @return 0系统繁忙,1删除成功,2删除失败
	 * @throws BaseException
	 */
	public int delNotice(String noticeid)throws BaseException;
}
