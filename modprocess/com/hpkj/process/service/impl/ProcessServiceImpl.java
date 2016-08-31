package com.hpkj.process.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;


import com.hpkj.core.exception.BaseException;
import com.hpkj.core.service.BaseServiceImpl;
import com.hpkj.process.dao.ProcessDao;
import com.hpkj.process.service.ProcessService;
import com.hpkj.process.vo.ProcessDefinitionInfoVo;
import com.hpkj.reimburse.dao.impl.ReimburseDaoImpl;
import com.hpkj.reimburse.entity.HpoaReimburseInfos;

@Service("processService")
@Transactional
public class ProcessServiceImpl extends BaseServiceImpl implements
		ProcessService {

	@Resource
	private ProcessDao processDao;
	@Resource
	private ReimburseDaoImpl reimburseDao;
	
	// @Resource
	  ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	//查找所有最新版本的流程定义
	@Override
	public ProcessDefinitionInfoVo findAllLatestVersions(String page, String rows, int from, int length, String order, String sort) {
		// TODO Auto-generated method stub
		ProcessDefinitionInfoVo prodefInfoVo = new ProcessDefinitionInfoVo();		
	

		List<ProcessDefinition> all = processEngine.getRepositoryService()//
				.createProcessDefinitionQuery()//
				.orderByProcessDefinitionVersion().asc()
				//.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)//
				.list();

		// 过滤出所有最新的版本
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for (ProcessDefinition pd : all) {
			map.put(pd.getKey(),pd);
		}

		//map转换成list
		 
	       List listValue = new ArrayList();  
	       Iterator it = map.keySet().iterator();  
	       while (it.hasNext()) {  
	           String key = it.next().toString();  
//	           listKey.add(map.values());  
	           listValue.add(map.get(key));  
	       }  

	      
		// 显示
		for (ProcessDefinition pd : map.values()) {
			System.out.println("id=" + pd.getId()// 格式为：{key}-{version}，用于唯一的标识一个流程定义
					+ ", name=" + pd.getName()// 流程定义的名称，jpdl.xml中根元素的name属性的值
					+ ", key=" + pd.getKey()// 流程定义的key，jpdl.xml中根元素的key属性的值，默认是name属性的值
					+ ", version=" + pd.getVersion()// 自动生成的，同一个名称的第一个为1，以后的自动加1.
					+ ", deploymentId=" + pd.getDeploymentId()); // 所属的部署对象
		}
		
	       
		prodefInfoVo.setEntityrows(listValue);
		
		prodefInfoVo.setPage(Integer.parseInt(page));
		prodefInfoVo.setRecords(map.size());
		
		//int total = (allList.size()%Integer.parseInt(rows)==0) ? (allList.size()/Integer.parseInt(rows)) : (allList.size()/Integer.parseInt(rows)+1);
		int total = map.size();		
		prodefInfoVo.setTotal(total);
		return prodefInfoVo;
	}


	//部署流程定义
	@Override
	public String addProcessDefinition(String fileName,String path)throws BaseException { 
		// TODO Auto-generated method stub
		String rtn = "2";
		try{
			//fileName="shenpiliucheng.bpmn";
			  //获取资源相对路径  
		    String bpmnPath = path+"\\"+fileName;  
		   // String pngPath = "diagrams/helloworld.png";  
		    FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath); 
			
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(path+"\\"+fileName));
		Deployment deploymentId = processEngine.getRepositoryService()//
				.createDeployment()//
//				.name("请假流程")// 添加部署规则的显示别名
				.addZipInputStream(zipInputStream)
			rce("com/hpkj/processdefinition/MyProcess.bpmn")
				.deploy();
		System.out.println("部署成功：deploymentId = " + deploymentId);
		    rtn = "1";
		} catch (Exception e) {
			        rtn="2";
					throw new BaseException("部署流程定义时出错",e);
				}
				return rtn;
	
     }


	//删除流程定义
	@Override
	public String deleteProcessDefinition(String key)throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			// 查询出指定key的所有版本的流程定义
			List<ProcessDefinition> list = processEngine.getRepositoryService()//
					.createProcessDefinitionQuery()//
					.processDefinitionKey(key)//
					.list();

			// 一一删除
			for (ProcessDefinition pd : list) {
				processEngine.getRepositoryService().deleteDeployment(pd.getDeploymentId(), true);
			}
			rtn="1";
		} catch (Exception e) {
			// TODO: handle exception
			rtn="2";
			throw new BaseException("删除流程定义出错", e);
		}
		return rtn;
	}


	//查看流程定义图片
	@Override
	public InputStream getProcessImageResourceAsStream(String id) {
		// TODO Auto-generated method stub
		//ProcessEngineConfiguration processEngineConfiguration = Context.getProcessEngineConfiguration();
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration());
		// 根据id取出对应的流程定义对象
		
				ProcessDefinition pd = processEngine.getRepositoryService()//
						.createProcessDefinitionQuery()//
						.processDefinitionId(id)//
						.singleResult();

				
				// 返回图片资源
				return processEngine.getRepositoryService().getResourceAsStream(pd.getDeploymentId(),pd.getDiagramResourceName() );
	}


	/**
	 * 使用任务ID，获取当前任务节点中对应的Form key中的连接的值
	 * @param taskId
	 * @return
	 * @throws BaseException
	 */
	@Override
	public String findTaskFormKeyByTaskId(String taskId) throws BaseException {
		// TODO Auto-generated method stub
		TaskFormData formData = processEngine.getFormService().getTaskFormData(taskId);
		//获取Form key的值
		String url = formData.getFormKey();
		return url;
	}


	/**
	 * 使用任务Id ，查找报销单ID，从而获取报销单信息
	 * @param taskId
	 * @return
	 * @throws BaseException
	 */
	@Override
	public HpoaReimburseInfos findReimburseInfoByTaskId(String taskId)throws BaseException {
		// TODO Auto-generated method stub
		//1：使用任务ID，查询任务对象Task
				Task task = processEngine.getTaskService().createTaskQuery()//
								.taskId(taskId)//使用任务ID查询
								.singleResult();
		//2：使用任务对象Task获取流程实例ID
		        String processInstanceId = task.getProcessInstanceId();
		//3：使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
				ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery()//
								.processInstanceId(processInstanceId)//使用流程实例ID查询
								.singleResult();
		//4：使用流程实例对象获取BUSINESS_KEY
		String buniness_key = pi.getBusinessKey();
		//5：获取BUSINESS_KEY对应的主键ID，使用主键ID，查询请假单对象（LeaveBill.1）
				String id = "";
				if(StringUtils.isNotBlank(buniness_key)){
					//截取字符串，取buniness_key小数点的第2个值
					id = buniness_key.split("\\.")[1];
				}
				//查询报销单对象
				//使用hql语句：from LeaveBill o where o.id=1
				HpoaReimburseInfos hpoaReimburseInfos = reimburseDao.getModelById(HpoaReimburseInfos.class, id);
		return hpoaReimburseInfos;
	}


	/**
	 * 已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中
	 * @param taskId
	 * @return
	 */
	@Override
	public List<String> findOutComeListByTaskId(String taskId)
			throws BaseException {
		// TODO Auto-generated method stub
		//返回存放连线的名称集合
	    List<String> list = new ArrayList<String>();
	  //1:使用任务ID，查询任务对象
	  		Task task = processEngine.getTaskService().createTaskQuery()//
	  					.taskId(taskId)//使用任务ID查询
	  					.singleResult();
	  	//2：获取流程定义ID
			String processDefinitionId = task.getProcessDefinitionId();
		//3：查询ProcessDefinitionEntiy对象
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) processEngine.getRepositoryService().getProcessDefinition(processDefinitionId);
		//使用任务对象Task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
				ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery()//
							.processInstanceId(processInstanceId)//使用流程实例ID查询
							.singleResult();
		//获取当前活动的id
		String activityId = pi.getActivityId();
		//4：获取当前的活动
				ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);
		//5：获取当前活动完成之后连线的名称
		List<PvmTransition> pvmList = activityImpl.getOutgoingTransitions();
		if(pvmList!=null && pvmList.size()>0){
			for(PvmTransition pvm:pvmList){
				String name = (String) pvm.getProperty("name");
				if(StringUtils.isNotBlank(name)){
					list.add(name);
				}
				else{
					list.add("默认提交");
				}
			}
		}
		return list;
	}


	/**
	 * 查询所有历史审核人的审核信息，帮助当前人完成审核，返回List<Comment>
	 * @param taskId
	 * @return
	 */
	@Override
	public List<Comment> findCommentByTaskId(String taskId)
			throws BaseException {
		// TODO Auto-generated method stub
		List<Comment> list = new ArrayList<Comment>();
		//使用当前的任务ID，查询当前流程对应的历史任务ID
		//使用当前任务ID，获取当前任务对象
		Task task = processEngine.getTaskService().createTaskQuery()//
				.taskId(taskId)//使用任务ID查询
				.singleResult();
		//获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		list = processEngine.getTaskService().getProcessInstanceComments(processInstanceId);
		return list;
	}


	/**
     * 提交任务
     * @return
     */
	@Override
	public String saveHandleInfo(String taskId, String reimburseId,
			String comment, String outcome,String userName) throws BaseException {
		// TODO Auto-generated method stub
		String rtn = "2";
		try {
			/**
			 * 1：在完成之前，添加一个批注信息，向act_hi_comment表中添加数据，用于记录对当前申请人的一些审核信息
			 */
			//使用任务ID，查询任务对象，获取流程流程实例ID
			Task task = processEngine.getTaskService().createTaskQuery()//
							.taskId(taskId)//使用任务ID查询
							.singleResult();
			//获取流程实例ID
			String processInstanceId = task.getProcessInstanceId();
			/**
			 * 注意：添加批注的时候，由于Activiti底层代码是使用：
			 * 		String userId = Authentication.getAuthenticatedUserId();
				    CommentEntity comment = new CommentEntity();
				    comment.setUserId(userId);
				  所有需要从Session中获取当前登录人，作为该任务的办理人（审核人），对应act_hi_comment表中的User_ID的字段，不过不添加审核人，该字段为null
				 所以要求，添加配置执行使用Authentication.setAuthenticatedUserId();添加当前任务的审核人
			 * */
			Authentication.setAuthenticatedUserId(userName);
			processEngine.getTaskService().addComment(taskId, processInstanceId, comment);
			/**
			 * 2：如果连线的名称是“默认提交”，那么就不需要设置，如果不是，就需要设置流程变量
			 * 在完成任务之前，设置流程变量，按照连线的名称，去完成任务
					 流程变量的名称：outcome
					 流程变量的值：连线的名称
			 */
			Map<String, Object> variables = new HashMap<String,Object>();
			if(outcome!=null && !outcome.equals("默认提交")){
				variables.put("outcome", outcome);
			}
			//3：使用任务ID，完成当前人的个人任务，同时流程变量
			processEngine.getTaskService().complete(taskId, variables);
			//4：当任务完成之后，需要指定下一个任务的办理人（使用类）-----未开发完成
			TaskEntity tt=new TaskEntity();
			
			/**
			 * 5：在完成任务之后，判断流程是否结束
	   			如果流程结束了，更新请假单表的状态从1变成2（审核中-->审核完成）
			 */
			ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery()//
							.processInstanceId(processInstanceId)//使用流程实例ID查询
							.singleResult();
			//流程结束了
					if(pi==null){
						//更新请假单表的状态从1变成2（审核中-->审核完成）
						HpoaReimburseInfos hpoaReimburseInfos = reimburseDao.getModelById(HpoaReimburseInfos.class, reimburseId);
						hpoaReimburseInfos.setReistatus(2);
					}
			rtn="1";
		} catch (Exception e) {
			
			throw new BaseException("提交任务错误",e);
		}
		
		return rtn;
	}



	
	
	
	
	
	
	
	
}
