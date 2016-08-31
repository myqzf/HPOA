package com.hpkj.dictionary.service;

import java.util.List;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.dictionary.entity.SysDictHead;
import com.hpkj.dictionary.entity.SysDictItems;
import com.hpkj.dictionary.vo.TreeInfo;

public interface DictionaryService extends IBaseService {
	
	/**
	 * 查询字典项信息
	 * @param
	 * @throws BaseException
	 */
	public List<SysDictItems> getDictionaryList() throws BaseException;

	/**
	 * 查询字典头信息
	 * @param 
	 * @throws BaseException
	 */
	public List<SysDictHead> getDictionaryHeadList() throws BaseException;
	
	/**
	 * 查询字典头和字典树信息
	 * @param 
	 * @throws BaseException
	 */
	public List<TreeInfo> getDictHeadAndItemsList() throws BaseException;

	/**
	 * 根据HeadId查询字典项信息
	 * @param 
	 * @throws BaseException
	 */
	public List<SysDictItems> getDictionaryZtreeList(int i) throws BaseException;

	/**
	 * 保存新增字典树信息
	 * @param funcName 字典项名称
	 * @param funcNumber 通讯地址
	 * @param funcSort 排序字段
	 * @param funcBak1 联系电话
	 * @param funcBak2 邮编
	 * @param isLeaf 是否叶子节点1是2否
	 * @param funcPid 父级ID
	 * @param headId 字典头ID
	 * @throws BaseException
	 */
	public String saveDictInfo(String itemsName,String itemsShort,  String ifleaf,
			int pid, int headId) throws BaseException;

	
	/**
     *  删除字典树信息
     * @param itemsId 字典树Id
	 * @throws BaseException 
     */
	public String deleteDictInfo(String itemsId) throws BaseException;

	/**
	 * 根据字典项ID查询字典信息
	 * @param itemsId 字典树Id
	 * @throws BaseException 
	 */
	public SysDictItems searchDictById(int itemsId) throws BaseException;

	
	/**
	 * 保存修改的字典项信息
	 * @param funcName 字典项名称
	 * @param funcNumber 通讯地址
	 * @param funcSort 排序字段
	 * @param funcBak1 联系电话
	 * @param funcBak2 邮编
	 * @param isLeaf 是否叶子节点1是2否
	 * @param funcId 字典项ID
	 * @throws BaseException
	 */
	public String updateDictInfo(String itemsName,String itemsShort, int itemsId) throws BaseException;

	/**
	 * 根据字典头ID查询字典头信息
	 * @param headId 字典头ID
	 * @throws BaseException 
	 */
	public SysDictHead searchDictHeadById(int headId) throws BaseException;

	
	/**
	 * 保存修改的字典头信息
	 * @param headName 字典头名称
	 * @param bak1 备注一
	 * @param bak2 备注二
	 * @param headId 
	 * @throws BaseException
	 */
	public String modifyDictHeadInfo(String headName, int headId) throws BaseException;

	
	/**
	 * 保存新增一级字典树信息
	 * @param itemsName 字典项名称
	 * @param itemsNumber 通讯地址
	 * @param sort 排序字段
	 * @param bak1 联系电话
	 * @param bak2 邮编
	 * @param headId 字典头ID
	 * @throws BaseException
	 */
	public String addSaveOneDictInfo(String itemsName, int headId) throws BaseException;

	
	/**
	 * 保存新增的字典头信息
	 * @param headName 字典头名称
	 * @throws BaseException
	 */
	public String addSaveDictHeadInfo(String headName) throws BaseException;

	
	/**
	 * 根据headId查询字典项信息
	 * @param headId 字典项ID
	 * @throws BaseException
	 */
	public String searchIfItemInfo(int headId) throws BaseException;

	
	/**
     *  删除字典头信息
     * @param headId 字典头Id
	 * @throws BaseException 
     */
	public String deleteDictHeadInfo(String headId)throws BaseException;

}
