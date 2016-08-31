package com.hpkj.dictionary.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.dictionary.dao.DictionaryDAO;
import com.hpkj.dictionary.entity.SysDictHead;
import com.hpkj.dictionary.entity.SysDictItems;
import com.hpkj.dictionary.service.DictionaryService;
import com.hpkj.dictionary.vo.TreeInfo;

@Service("dictionaryService")
@Transactional
public class DictionaryServiceImpl extends BaseServiceImpl implements
		DictionaryService {
	
	@Resource
	private DictionaryDAO dictionaryDAO;

	
	 // 查询字典树信息
	@Override
	public List<SysDictItems> getDictionaryList() throws BaseException {
		// TODO Auto-generated method stub
//		String sql = "select di.items_id as itensid,di.items_name as itensname,di.pid as pid from sys_dict_items di";
		List <SysDictItems> treelist = null;
		try {
			
			treelist  = dictionaryDAO.getHqlList("from SysDictItems ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseException("查询字典树信息错误",e);
		}
		
		return treelist;
	}


	// 查询字典头信息
	@Override
	public List<SysDictHead> getDictionaryHeadList() throws BaseException {
		// TODO Auto-generated method stub
		List <SysDictHead> headlist = null;
		try {
			
			headlist  = dictionaryDAO.getHqlList("from SysDictHead ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseException("查询字典头信息错误",e);
			//return null;
		}
		
		return headlist;
	}


	//根据HeadId查询字典树信息
	@Override
	public List<SysDictItems> getDictionaryZtreeList(int headId) throws BaseException {
		// TODO Auto-generated method stub
		Assert.notNull(headId,"字典项ID为空！");
		String sql = "select * from sys_dict_items di where di.head_id=" + headId + "";
		List <SysDictItems> treeList = null;
		try {
			
			treeList  = dictionaryDAO.getSqlList(sql, SysDictItems.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseException("根据HeadId查询字典树信息错误",e);
		}
		
		return treeList;	
	}

    //查询字典头和字典树信息
	@Override
	public List<TreeInfo> getDictHeadAndItemsList() throws BaseException {
		// TODO Auto-generated method stub
		List<TreeInfo> headlist=new ArrayList<TreeInfo>(); 
		
		try {
			
			List <SysDictHead> treelist  = dictionaryDAO.getHqlList("from SysDictHead ");
			for (int i = 0; i < treelist.size(); i++) {
				TreeInfo treeInfo = new TreeInfo();
				int headId=treelist.get(i).getHeadId();
				String sql = "select * from sys_dict_items di where di.head_id=" + headId + "";
				List <SysDictItems> treelist1 = null;
				treelist1  = dictionaryDAO.getSqlList(sql, SysDictItems.class);
				treeInfo.setIdKey(i+1+"");
				treeInfo.setName(treelist.get(i).getHeadName());
				treeInfo.setpIdKey(null);
				treeInfo.setIfleaf("1");
				treeInfo.setHeadId(treelist.get(i).getHeadId());
				treeInfo.setItemsId(null);
				headlist.add(treeInfo);
			}
			for (int i = 0; i < treelist.size(); i++) {
				int headId=treelist.get(i).getHeadId();
				String sql = "select * from sys_dict_items di where di.head_id=" + headId + "";
				List <SysDictItems> treelist1 = null;
				treelist1  = dictionaryDAO.getSqlList(sql, SysDictItems.class);
				for (int j = 0; j < treelist1.size(); j++) {
					TreeInfo treeInfo = new TreeInfo();
					treeInfo.setIdKey(10000000+treelist1.get(j).getItemsId()+"");
					treeInfo.setName(treelist1.get(j).getItemsName());
					if (treelist1.get(j).getPid()==null) {
						treeInfo.setpIdKey(i+1+"");
					}else {
						treeInfo.setpIdKey(10000000+treelist1.get(j).getPid()+"");
					}
					
					treeInfo.setIfleaf(treelist1.get(j).getIfleaf());
					treeInfo.setHeadId(treelist1.get(j).getHeadId());
					treeInfo.setItemsId(treelist1.get(j).getItemsId());
					headlist.add(treeInfo);
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BaseException("查询字典头信息错误",e);
		}
		return headlist;
	}
	
	//保存新增字典树信息
	@Override
	public String saveDictInfo(String itemsName,String itemsShort, String ifleaf,
			int pid, int headId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";// 标识状态1：删除成功，2删除失败
		try {
			Assert.notNull(itemsName,"字典项名称为空！");
			Assert.notNull(ifleaf,"是否叶子节点为空！");
			Assert.notNull(pid,"父级ID为空！");
			Assert.notNull(headId,"字典头ID为空！");
			SysDictItems sfi = new SysDictItems();
			
			if (pid<10000000) {
				sfi.setPid(null);
			}else {
				sfi.setPid(pid-10000000);
			}
			sfi.setItemsName(itemsName);
			sfi.setItemsShort(itemsShort);
			sfi.setIfleaf(ifleaf);
			sfi.setHeadId(headId);
			//获取最大bak1的值，并加一，排序用
			/*String sql = "select max(to_number(bak1)) from sys_func_info";
			List list = dictionaryDAO.getSqlList(sql);
			if(list!=null && list.size()>0){
				int a = Integer.parseInt(list.get(0).toString())+1;
				sfi.setBak1(a+"");
			}*/
			sfi.setItemsId(searchNextVal());
			dictionaryDAO.saveModel(sfi);
			dictionaryDAO.sqlcommit();
			rtn = "1";
		} catch (Exception e) {
			throw new BaseException("保存新增字典项错误",e);
		}
		return rtn;
	}
	
	/**
	 * 获取序列 字典项
	 * @return
	 * @throws BaseException
	 */
	private int searchNextVal() throws BaseException{
		int id = 0;
		try {
			String sql = "select sys_dict_items_seq.nextval from dual";
			List li = dictionaryDAO.getSqlList(sql);
			if(li.size()>0){
				id = Integer.parseInt(li.get(0).toString());
			}
		} catch (Exception e) {
			throw new BaseException("查询序列错误",e);
		}
		return id;
	}
	
	/**
	 * 获取序列 字典头
	 * @return
	 * @throws BaseException
	 */
	private int searchNextHeadVal() throws BaseException{
		int id = 0;
		try {
			String sql = "select sys_dict_head_seq.nextval from dual";
			List li = dictionaryDAO.getSqlList(sql);
			if(li.size()>0){
				id = Integer.parseInt(li.get(0).toString());
			}
		} catch (Exception e) {
			throw new BaseException("查询序列错误",e);
		}
		return id;
	}


	//删除字典树信息
	@Override
	public String deleteDictInfo(String itemsId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			Assert.notNull(itemsId,"字典项ID为空！");
			//dictionaryDAO.deleteModelById(SysDictItems.class, itemsId);
			//删除功能角色关联表中的功能ID
			String sql = "delete SYS_DICT_ITEMS t where t.items_id=?";
			dictionaryDAO.excuteSql(sql, itemsId);
			rtn = "1";
		}
		catch (Exception e) {
			
			throw new BaseException("删除功能信息错误",e);
		}
		return rtn;
	}


	//根据字典项ID查询字典信息
	@Override
	public SysDictItems searchDictById(int itemsId) throws BaseException {
		// TODO Auto-generated method stub
		SysDictItems sri = new SysDictItems();
		try {
			Assert.notNull(itemsId,"字典项ID为空！");
			sri = dictionaryDAO.getModelById(SysDictItems.class, itemsId);
		} catch (Exception e) {
			
			throw new BaseException("根据功能ID查询功能信息错误",e);
		}
		return sri;
	}


	//保存修改的字典项信息
	@Override
	public String updateDictInfo(String itemsName,String itemsShort,
			int itemsId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			Assert.notNull(itemsId,"字典项ID为空！");
			Assert.notNull(itemsName,"字典项名称为空！");
			//根据ID查询字典项信息
			SysDictItems sri = dictionaryDAO.getModelById(SysDictItems.class, itemsId);
			
			
			if(sri!=null && StringUtils.isNotBlank(sri.getItemsId()+"")){
				sri.setItemsName(itemsName);
				sri.setItemsShort(itemsShort);
				
				dictionaryDAO.updateModel(sri);
				dictionaryDAO.sqlcommit();
			}
			rtn = "1";
		} catch (Exception e) {
			
			throw new BaseException("修改功能信息错误",e);
		}
		return rtn;
	}


	//根据字典头ID查询字典头信息
	@Override
	public SysDictHead searchDictHeadById(int headId) throws BaseException {
		// TODO Auto-generated method stub
		SysDictHead sri = new SysDictHead();
		try {
			Assert.notNull(headId,"字典头ID为空！");
			sri = dictionaryDAO.getModelById(SysDictHead.class, headId);
		} catch (Exception e) {
			
			throw new BaseException("根据字典头ID查询字典头信息错误",e);
		}
		return sri;
	}


	//保存修改的字典头信息
	@Override
	public String modifyDictHeadInfo(String headName,int headId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			Assert.notNull(headId,"字典头ID为空！");
			Assert.notNull(headName,"字典头名称为空！");
			//根据ID查询字典头信息
			SysDictHead sri = dictionaryDAO.getModelById(SysDictHead.class, headId);
			
			if(sri!=null && StringUtils.isNotBlank(sri.getHeadId()+"")){
				sri.setHeadName(headName);
				dictionaryDAO.updateModel(sri);
				dictionaryDAO.sqlcommit();
			}
			rtn = "1";
		} catch (Exception e) {
			
			throw new BaseException("修改字典头信息错误",e);
		}
		return rtn;
	}


	//保存新增一级字典树信息
	@Override
	public String addSaveOneDictInfo(String itemsName, int headId) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			Assert.notNull(itemsName,"字典项名称为空！");
			Assert.notNull(headId,"字典头ID为空！");
			SysDictItems sfi = new SysDictItems();
			
		
			
			sfi.setItemsName(itemsName);
			sfi.setPid(null);
			sfi.setIfleaf("2");
			sfi.setHeadId(headId);
			//获取最大bak1的值，并加一，排序用
			/*String sql = "select max(to_number(bak1)) from sys_func_info";
			List list = dictionaryDAO.getSqlList(sql);
			if(list!=null && list.size()>0){
				int a = Integer.parseInt(list.get(0).toString())+1;
				sfi.setBak1(a+"");
			}*/
			sfi.setItemsId(searchNextVal());
			dictionaryDAO.saveModel(sfi);
			dictionaryDAO.sqlcommit();
			rtn = "1";
		} catch (Exception e) {
			throw new BaseException("保存新增一级字典项错误",e);
		}
		return rtn;
	}


	

	//保存新增的字典头信息
		@Override
		public String addSaveDictHeadInfo(String headName) throws BaseException {
			// TODO Auto-generated method stub
			String rtn = "2";
			try {
				Assert.notNull(headName,"字典头名称为空！");
				SysDictHead sfi = new SysDictHead();
				
				sfi.setHeadName(headName);
				sfi.setHeadId(searchNextHeadVal());
				dictionaryDAO.saveModel(sfi);
				dictionaryDAO.sqlcommit();
				rtn = "1";
			} catch (Exception e) {
				throw new BaseException("保存新增一级字典项错误",e);
			}
			return rtn;
		}


		//根据headId查询字典项信息
		@Override
		public String searchIfItemInfo(int headId) throws BaseException {
			// TODO Auto-generated method stub
			String rtn = "1";
			
			try {
				Assert.notNull(headId,"字典头ID为空！");
			
				String sql = "select t.* from sys_dict_items t where t.head_id='" + headId + "'";
				List li = dictionaryDAO.getSqlList(sql);
				if(li.size()>0){
				
					  rtn = "2";
			         }
				}catch (Exception e) {
				
				throw new BaseException("根据headId查询字典项信息",e);
			}
			 
			return rtn;
		}
		

		//删除字典头信息
		@Override
		public String deleteDictHeadInfo(String headId) throws BaseException {
			// TODO Auto-generated method stub
			String rtn = "2";
			try {
				Assert.notNull(headId,"字典项ID为空！");
				String sql = "delete SYS_DICT_HEAD t where t.head_id=?";
				dictionaryDAO.excuteSql(sql, headId);
				rtn = "1";
			}
			catch (Exception e) {
				
				throw new BaseException("删除字典头信息错误",e);
			}
			return rtn;
		}


		
	
	

}
