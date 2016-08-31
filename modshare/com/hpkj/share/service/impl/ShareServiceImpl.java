package com.hpkj.share.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.core.util.StringUtilz;
import com.hpkj.share.dao.ShareDao;
import com.hpkj.share.entity.HpoaShareInfo;
import com.hpkj.share.service.ShareService;
import com.hpkj.share.vo.ShareDetailVo;
import com.hpkj.share.vo.ShareListVo;
@Service("shareService")
@Transactional
public class ShareServiceImpl extends BaseServiceImpl implements ShareService {
	@Resource
	private ShareDao shareDao;
	/*
	 * 保存共享内容
	 * @see com.hpkj.share.service.ShareService#saveShare(com.hpkj.share.entity.HpoaShareInfo)
	 */
	public int saveShare(HpoaShareInfo hsi)throws BaseException{
		try{
			int flag=0;
			if(StringUtilz.isEmpty(hsi.getShareTitle())||StringUtilz.isEmpty(hsi.getShareContent())||StringUtilz.isEmpty(hsi.getShareAuthorId())||StringUtilz.isEmpty(hsi.getShareFileName())||StringUtilz.isEmpty(hsi.getShareRealFileName())){
				flag=3;
				return flag;
			}
			hsi.setShareStatus("1");
			hsi.setShareTime(DateTimeUtil.getDateTime());
			shareDao.saveModel(hsi);
			if(StringUtilz.isEmpty(hsi.getShareId())){
				flag=2;
			}else{
				flag=1;
			}
			return flag;
		}catch(Exception e){
			throw new BaseException("保存共享内容时出错",e);
		}
	}
	/*
	 * 获取全部共享资料列表
	 * @see com.hpkj.share.service.ShareService#getShareList(int, int, java.lang.String, java.lang.String)
	 */
	public List<ShareListVo> getShareList(int from, int length, String sidx, String sord)throws BaseException{
		try{
			List<ShareListVo> lslv=new ArrayList();
			String sql="select hsi.share_id as shareid, hsi.share_title as shareTitle, hsi.share_time as shareTime, thsi.staff_name as authorName, " +
					"csdi.items_name as authorComp, dsdi.items_name as authroDepart, hsi.share_file_name as shareFileName, hsi.share_real_file_name as shareRealFileName " +
					"from hpoa_share_info hsi join hpoa_staff_info thsi on hsi.share_author_id=thsi.staff_id " +
					"join sys_dict_items csdi on thsi.staff_comp=csdi.items_id join sys_dict_items dsdi on thsi.staff_dept=dsdi.items_id order by "+sidx+" "+sord;
			if(length>0){
				lslv=shareDao.getVOSQL(sql, ShareListVo.class, from, length);
			}else{
				lslv=shareDao.getVOSQL(sql, ShareListVo.class);
			}
			return lslv;
		}catch(Exception e){
			throw new BaseException("获取全部共享资料列表时出错",e);
		}
	}
	/*
	 * 根据id获取共享资料详细内容
	 * @see com.hpkj.share.service.ShareService#getShareDetail(java.lang.String)
	 */
	public ShareDetailVo getShareDetail(String shareid)throws BaseException{
		try{
			if(StringUtilz.isEmpty(shareid)){
				return null;
			}
			List<ShareDetailVo> lsdv=new ArrayList();
			String sql="select hsi.share_title as shareTitle, hsi.share_time as shareTime, thsi.staff_name as authorName, csdi.items_name as authorComp, " +
					"dsdi.items_name as authroDepart, hsi.share_file_name as shareFileName, hsi.share_real_file_name as shareRealFileName, hsi.share_content as content " +
					"from hpoa_share_info hsi join hpoa_staff_info thsi on hsi.share_author_id=thsi.staff_id join sys_dict_items csdi on thsi.staff_comp=csdi.items_id " +
					"join sys_dict_items dsdi on thsi.staff_dept=dsdi.items_id  where hsi.share_id=?";
			lsdv=shareDao.getVOSQL(sql, ShareDetailVo.class, shareid);
			return lsdv.get(0);
		}catch(Exception e){
			throw new BaseException("根据id获取共享资料详细内容时出错",e);
		}
	}
}
