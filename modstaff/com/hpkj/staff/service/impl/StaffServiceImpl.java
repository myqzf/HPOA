package com.hpkj.staff.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.staff.dao.impl.StaffDaoImpl;
import com.hpkj.staff.entity.HpoaStaffInfo;
import com.hpkj.staff.service.StaffService;
import com.hpkj.staff.vo.DictTitleVo;
import com.hpkj.staff.vo.StaffDetailInfoVo;
@Service("staffService")
public class StaffServiceImpl extends BaseServiceImpl implements StaffService{
	@Resource
	private StaffDaoImpl staffDao;
	/* 根据父项id号（pid）查找字典列表中响应项
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#getDictList(int)
	 */
	@Override
	public List<DictTitleVo> getDictList(int pid) throws BaseException{
		List<DictTitleVo> dictTitleList=new ArrayList<DictTitleVo>();
		try{
		if (pid == 0){   //判断pid是否为空
			throw new BaseException("查找字典列表时pid不能为空");
		}
		String sqlText="select t.items_id as itemsid,t.items_name as itemsname from sys_dict_items t where t.pid=?";
		dictTitleList=staffDao.getVOSQL(sqlText, DictTitleVo.class, pid);
		}catch(Exception e){
			throw new BaseException("查询全部员工信息列表时出错",e);
		}
		return dictTitleList;
	}	
	/* 全部员工信息列表
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#getStaffList(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public List getStaffList(String sidx,String sord,int startRow, int pageSize) throws BaseException{
		List<StaffDetailInfoVo> list=new ArrayList<StaffDetailInfoVo>();//存放员工信息列表的对象		
		try {
			String sqlText = "select s.staff_id  as staffId,'' as staffPhotourl,s.staff_name as  staffName, s.staff_idcard as staffIdcard,\n"
					+ "(case when s.staff_gender=1 then '男'when s.staff_gender=2 then '女' end) as staffGender,\n"
					+ "d.items_name as staffQualid,\n"
					+ "(case when s.staff_marry=1 then '未婚' when s.staff_marry=2 then '已婚' when s.staff_marry=3 then '离异' end) as staffMarry,\n"
					+ "s.staff_phone as staffPhone,s.staff_address as staffAddress,\n"
					+ "e.items_name as staffDept, f.items_name as staffPosi,g.items_name as staffComp\n"
					+ "from hpoa_staff_info s \n"
					+ "left join sys_dict_items d on s.staff_qualid=d.items_id and d.pid=4 \n"
					+ "left join sys_dict_items e on s.staff_dept=e.items_id and e.pid=1 "
					+ "left join sys_dict_items f on s.staff_posi=f.items_id and f.pid=98 "
					+ "left join sys_dict_items g on s.staff_comp=g.items_id and g.pid=108";
			if(sidx.isEmpty()){
				sqlText += " order by s.staff_id desc";
			}else{
				sqlText += " order by " + sidx + " " + sord + ",s.staff_id desc";
			}
			list = staffDao.getVOSQL(sqlText, StaffDetailInfoVo.class, startRow,pageSize);
			return list;
		} catch (Exception e) {
			throw new BaseException("查询全部员工信息列表时出错",e);
		}
	}
	/* 增加新员工
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#addStaff(com.hpkj.staff.entity.HpoaStaffInfo)
	 */
	@Override
	public int addStaff(HpoaStaffInfo staffInfo) throws BaseException {
		int flag = 0;// 0系统错误，1添加成功，2添加失败，3未填写姓名
		Date now = new Date();
		String date=DateTimeUtil.getDateTime();//当前日期时间		
		try {
			staffInfo.setBak1(date);//添加员工的时间
			if (staffInfo.getStaffName().isEmpty()) {
				flag = 3;
				return flag;
			}
			flag = 2;
			staffDao.saveModel(staffInfo);
			staffDao.sqlcommit();
			flag = 1;
		} catch (Exception e) {
			flag = 0;
			throw new BaseException("添加新员工时出错", e);
		}
		return flag;
	}
	/* 根据员工id删除员工
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#delStaff(java.lang.String)
	 */
	@Override
	public int delStaff(String staffId) throws BaseException{
		int flag = 0;// 标识状态0：系统出错，1：删除成功，2删除失败,3员工ID为空
		try {
			flag = 2;
			if(staffId.isEmpty()){
				flag=3;
			}
			String sql="delete from hpoa_staff_info s where s.staff_id=?";
			staffDao.excuteSql(sql, staffId);
			staffDao.sqlcommit();
			flag = 1;
		} catch (Exception e) {
			flag = 0;
			throw new BaseException("根据员工ID删除员工时出错", e);
		}
		return flag;
	}
	/* 根据员工id获取员工的详细信息
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#getStaffInfo(java.lang.String)
	 */
	@Override
	public HpoaStaffInfo getStaffInfo(String staffId) throws BaseException{
		HpoaStaffInfo staffInfo=null;
		try{
			if (staffId .isEmpty()){   //判断pid是否为空
				throw new BaseException("获取员工详细信息时员工id不能为空");
			}
			staffInfo=staffDao.getModelById(HpoaStaffInfo.class, staffId);
		}catch(Exception e){
			throw new BaseException("得到员工实体时出错",e);
		}
		return staffInfo;
	}
	/* 修改员工信息
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#modifyStaff(com.hpkj.staff.entity.HpoaStaffInfo)
	 */
	@Override
	public int modifyStaff(HpoaStaffInfo staffInfo) throws BaseException {
		int flag = 0;// 标识状态：0系统出错，1修改成功，2修改失败，3员工姓名为空		
		try {			
			if (staffInfo.getStaffName().isEmpty()) {
				flag = 3;
				return flag;
			}			
			flag = 2;
			HpoaStaffInfo staff=this.getStaffInfo(staffInfo.getStaffId());
			staffInfo.setStaffUserid(staff.getStaffUserid());//保留staff_userid字段的值
			staffInfo.setStaffPhotourl(staff.getStaffPhotourl());//保留staff_photourl字段的值
			staffInfo.setBak1(staff.getBak1());//保留bak1字段的值
			staffDao.sqlclear();
			staffDao.saveOrUpdateModel(staffInfo);
			staffDao.sqlcommit();
			flag = 1;
			
		} catch (Exception e) {
			flag = 0;
			e.printStackTrace();
			throw new BaseException("修改员工信息时发生错误", e);
		}
		return flag;
	}	
	/* 根据条件查找员工信息
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#getSearchList(com.hpkj.staff.vo.StaffDetailInfoVo, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public List getSearchList(StaffDetailInfoVo staff,String sidx,String sord,int from, int length) throws BaseException{
		List<Object> staffList=new ArrayList<Object>();
		try {
			String sqlText = "select s.staff_id  as staffId,'' as staffPhotourl,s.staff_name as  staffName, s.staff_idcard as staffIdcard,\n"
					+ "(case when s.staff_gender=1 then '男'when s.staff_gender=2 then '女' end) as staffGender,\n"
					+ "d.items_name as staffQualid,\n"
					+ "(case when s.staff_marry=1 then '未婚' when s.staff_marry=2 then '已婚' when s.staff_marry=3 then '离异' end) as staffMarry,\n"
					+ "s.staff_phone as staffPhone,s.staff_address as staffAddress,\n"
					+ "e.items_name as staffDept, f.items_name as staffPosi,g.items_name as staffComp\n"
					+ "from hpoa_staff_info s "
					+ "left join sys_dict_items d on s.staff_qualid=d.items_id and d.pid=4 \n"
					+ "left join sys_dict_items e on s.staff_dept=e.items_id and e.pid=1 "
					+ "left join sys_dict_items f on s.staff_posi=f.items_id and f.pid=98 "
					+ "left join sys_dict_items g on s.staff_comp=g.items_id and g.pid=108 where 1=1";
			if (!"".equals(staff.getStaffName()) && staff.getStaffName() != null) {
				sqlText = sqlText + " and s.staff_name like '%"+ staff.getStaffName() + "%'";
			}
			if (!"".equals(staff.getStaffIdcard()) && staff.getStaffIdcard() != null) {
				sqlText = sqlText + " and s.staff_idcard like '%"+ staff.getStaffIdcard() + "%'";
			}
			if (!"".equals(staff.getStaffAddress()) && staff.getStaffAddress() != null) {
				sqlText = sqlText + " and s.staff_address like '%"+ staff.getStaffAddress() + "%'";
			}
			if (!"0".equals(staff.getStaffDept()) && staff.getStaffDept() != null) {
				sqlText = sqlText + " and s.staff_dept ='"+ staff.getStaffDept() + "'";
			}
			if (!"0".equals(staff.getStaffPosi()) && staff.getStaffPosi() != null) {
				sqlText = sqlText + " and s.staff_posi ='"+ staff.getStaffPosi() + "'";
			}
			if (!"0".equals(staff.getStaffComp()) && staff.getStaffComp() != null) {
				sqlText = sqlText + " and s.staff_comp ='"+ staff.getStaffComp() + "'";
			}
			if (!"0".equals(staff.getStaffGender()) && staff.getStaffGender() != null) {
				sqlText = sqlText + " and s.staff_gender='"+ staff.getStaffGender() + "'";
			}
			if (!"0".equals(staff.getStaffMarry()) && staff.getStaffMarry() != null) {
				sqlText = sqlText + " and s.staff_marry='"+ staff.getStaffMarry() + "'";
			}
			if (!"0".equals(staff.getStaffQualid())&& staff.getStaffQualid() != null) {
				sqlText = sqlText + " and s.staff_qualid='"+ staff.getStaffQualid() + "'";
			}
			if(sidx.isEmpty()){
				sqlText += " order by s.staff_id desc";
			}else{
				sqlText += " order by " + sidx + " " + sord + ",s.staff_id desc";
			}
			if (length > 0) {
				staffList = staffDao.getVOSQL(sqlText, StaffDetailInfoVo.class, from,length);
			
			} else {
				staffList = staffDao.getVOSQL(sqlText, StaffDetailInfoVo.class);
			}
		} catch (Exception e) {
			throw new BaseException("根据条件查找员工信息时出错", e);
		}
		return staffList;		
	}		
	/* 根据员工id得到员工详细信息
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#getStaffDetailInfo(java.lang.String)
	 */
	@Override
	public StaffDetailInfoVo getStaffDetailInfo(String staffId) throws BaseException{
		StaffDetailInfoVo staffDetailInfo;
		try {
			if (staffId.isEmpty()) {
				return null;
			}
			String sqlText = "select s.staff_id  as staffId,s.staff_name as  staffName, s.staff_idcard as staffIdcard,\n"
					+ "(case when s.staff_gender=1 then '男' when s.staff_gender=2 then '女' end) as staffGender,\n" 
					+"d.items_name as staffQualid,\n"
					+ "(case when s.staff_marry=1 then '未婚' when s.staff_marry=2 then '已婚' when s.staff_marry=3 then '离异' end) as staffMarry,\n" 
					+"s.staff_phone as staffPhone,s.staff_address as staffAddress,s.staff_photourl as staffPhotourl,\n"
					+"e.items_name as staffDept, f.items_name as staffPosi,g.items_name as staffComp\n" 
					+"from hpoa_staff_info s "
					+ "left join sys_dict_items d on s.staff_qualid=d.items_id and d.pid=4 \n"
					+ "left join sys_dict_items e on s.staff_dept=e.items_id and e.pid=1 "
					+ "left join sys_dict_items f on s.staff_posi=f.items_id and f.pid=98 "
					+ "left join sys_dict_items g on s.staff_comp=g.items_id and g.pid=108 where s.staff_id=?";
			List<StaffDetailInfoVo> li = staffDao.getVOSQL(sqlText, StaffDetailInfoVo.class, staffId);					
			staffDetailInfo=li.get(0);
		} catch (Exception e) {
			throw new BaseException("根据员工ID获取员工详细信息时出错", e);
		}
		return staffDetailInfo;
	}
	
	
	
	
	
	
	/* 根据员工id上传照片
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#modifyPhoto(java.lang.String, java.lang.String)
	 */
	@Override
	public int modifyPhoto(String staffId, String photoUrl) throws BaseException {
		int flag = 0;// 标识状态：0系统错误，1修改成功，2修改失败,3员工id为空
		try {
			flag = 2;
			if (staffId.isEmpty()){   //判断pid是否为空
				flag=3;
				return flag;
			}
			String sql = "update hpoa_staff_info s set s.staff_photourl=? where s.staff_id=?";
			staffDao.excuteSql(sql, photoUrl, staffId);
			flag = 1;
		} catch (Exception e) {
			flag = 0;
			throw new BaseException("根据员工ID修改员工照片链接时出错", e);
		}
		return flag;
	}
	/* 根据员工id删除员工照片
	 * (non-Javadoc)
	 * @see com.hpkj.staff.service.StaffService#delStaffPhoto(java.lang.String)
	 */
	@Override
	public int delStaffPhoto(String staffId) throws BaseException {
		int flag=0;// 标识状态：0系统错误，1删除成功，2删除失败,3员工id为空
		try {
			if(staffId.isEmpty()){
				flag=3;
			}
			flag=2;
			String sql = "update hpoa_staff_info s set s.staff_photourl='' where s.staff_id=?";
			staffDao.excuteSql(sql, staffId);
			flag=1;
		} catch (Exception e) {
			flag=0;
			throw new BaseException("根据员工ID删除图片链接时出错", e);
		}
		return flag;
	}
}
