package com.hpkj.share.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.share.entity.HpoaShareInfo;
import com.hpkj.share.vo.ShareDetailVo;
import com.hpkj.share.vo.ShareListVo;

public interface ShareService extends IBaseService {
	/**
	 * 保存共享内容
	 * @param hsi
	 * @return 0:系统繁忙,1:保存成功,2:保存失败,3:缺失必填项
	 * @throws BaseException
	 */
	public int saveShare(HpoaShareInfo hsi)throws BaseException;
	/**
	 * 获取全部共享资料列表
	 * @param from
	 * @param length
	 * @param sidx
	 * @param sord
	 * @return
	 * @throws BaseException
	 */
	public List<ShareListVo> getShareList(int from, int length, String sidx, String sord)throws BaseException;
	/**
	 * 根据id获取共享资料详细内容
	 * @param shareid 共享资料id
	 * @return
	 * @throws BaseException
	 */
	public ShareDetailVo getShareDetail(String shareid)throws BaseException;
}
