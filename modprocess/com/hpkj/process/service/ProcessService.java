package com.hpkj.process.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;


import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.IBaseService;
import com.hpkj.process.vo.ProcessDefinitionInfoVo;
import com.hpkj.reimburse.entity.HpoaReimburseInfos;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.springframework.web.multipart.MultipartFile;

public interface ProcessService extends IBaseService {

	/**
	 * 查找所有最新版本的流程定义
	 * @param sort 
	 * @param order 
	 * @param length 
	 * @param from 
	 * @param rows 
	 * @param page 
	 * @return
	 */
	public ProcessDefinitionInfoVo findAllLatestVersions(String page, String rows, int from, int length, String order, String sort);

	/**
	 * 部署流程定义
	 * @param fileName文件名
	 * @return
	 * @throws BaseException
	 */
	public String addProcessDefinition(String fileName,String path) throws BaseException;

	/**
	 * 删除流程定义
	 * @param key
	 * @return
	 * @throws BaseException 
	 */
	public String deleteProcessDefinition(String key) throws BaseException;

	/**
	 * 查看流程定义图片
	 * @param id
	 * @return
	 */
	public InputStream getProcessImageResourceAsStream(String id);

	

//	public String addProcessDefinition(ZipInputStream zipInputStream) throws BaseException;

	/**
	 * 使用任务ID，获取当前任务节点中对应的Form key中的连接的值
	 * @param taskId
	 * @return
	 * @throws BaseException
	 */
	public String findTaskFormKeyByTaskId(String taskId)throws BaseException;

	/**
	 * 使用任务Id ，从而获取报销单信息
	 * @param taskId
	 * @return
	 * @throws BaseException
	 */
	public HpoaReimburseInfos findReimburseInfoByTaskId(String taskId)throws BaseException;

	/**
	 * 已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
	 * @param taskId
	 * @return
	 */
	public List<String> findOutComeListByTaskId(String taskId)throws BaseException;

	/**
	 * 查询所有历史审核人的审核信息，帮助当前人完成审核，返回List<Comment>
	 */ 
	public List<Comment> findCommentByTaskId(String taskId)throws BaseException;

	/**
     * 提交任务
     * @return
     */
	public String saveHandleInfo(String taskId, String reimburseId,String comment, String outcome,String userName)throws BaseException;

}
