package com.hpkj.process.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.Minutes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;

import com.hpkj.core.exception.BaseException;
import com.hpkj.core.util.DateTimeUtil;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.minutes.entity.HpoaMinutesInfo;
import com.hpkj.minutes.vo.MinutesInfoVo;
import com.hpkj.monthly.entity.HpoaMonthsumInfo;
import com.hpkj.process.service.ProcessService;
import com.hpkj.process.vo.ProcessDefinitionInfoVo;
import com.hpkj.process.vo.ProcessDefinitionListVo;
import com.hpkj.reimburse.entity.HpoaReimburseInfos;
import com.hpkj.reimburse.service.ReimburseService;
import com.hpkj.staff.vo.StaffDetailInfoVo;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

/**
 * activiti工作流程定义管理
 * @author qzf
 *
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

	@Resource(name="processService")
	 private  ProcessService processService; 
	
	@Resource(name="reimburseService")
	private ReimburseService reimburseService;
	
	 private String rtnFlg;//操作结果：1成功，2失败
	 
		protected String activityFontName = "宋体";
		protected String labelFontName = "宋体";
	
	
	
	/**
	 * 跳转流程定义列表页面    
	 * @return 
	 */
	@RequestMapping("/goProcessDefinitionlist")
	public String goProcessDefinitionlist(){
	
		return "/WEB-INF/jsp/modprocessDefinition/processDefinitionList";
	}
	
	/**  
	 * easyui 查询流程定义列表  
	 */
	@RequestMapping("/searchProcessDefinitionlist")
	@ResponseBody
	public ProcessDefinitionInfoVo searchMinutesList(String order,String sort,HttpServletRequest request) throws BaseException{

		ProcessDefinitionInfoVo prodefiInfoVo = new ProcessDefinitionInfoVo();
		//List<ProcessDefinitionListVo> prodefiListVo = new ArrayList<ProcessDefinitionListVo>();
	
		String page="1";String rows="100";
		int from = Integer.parseInt(rows) * (Integer.parseInt(page) - 1);
		int length = Integer.parseInt(rows);
		try {
			
			prodefiInfoVo = processService.findAllLatestVersions(page, rows, from, length,order,sort);
			 List prodefilist = new ArrayList(); 
			//ActionContext.getContext().put("processDefinitionList", processDefinitionList);
			 int size=prodefiInfoVo.getEntityrows().size();
			 for (int i = 0; i < size; i++) {
				ProcessDefinitionListVo prodefiListVo = new ProcessDefinitionListVo();
				 prodefiListVo.setCategory(prodefiInfoVo.getEntityrows().get(i).getCategory());
				 prodefiListVo.setDeploymentId(prodefiInfoVo.getEntityrows().get(i).getDeploymentId());
				 prodefiListVo.setDescription(prodefiInfoVo.getEntityrows().get(i).getDescription());
				 prodefiListVo.setDiagramResourceName(prodefiInfoVo.getEntityrows().get(i).getDiagramResourceName());
				 
				 prodefiListVo.setHasStartFormKey(prodefiInfoVo.getEntityrows().get(i).getHasStartFormKey());
				 prodefiListVo.setId(prodefiInfoVo.getEntityrows().get(i).getId());
				 prodefiListVo.setKey(prodefiInfoVo.getEntityrows().get(i).getKey());
				 prodefiListVo.setName(prodefiInfoVo.getEntityrows().get(i).getName());
				 
				 prodefiListVo.setResourceName(prodefiInfoVo.getEntityrows().get(i).getResourceName());
				 prodefiListVo.setSuspensionState(prodefiInfoVo.getEntityrows().get(i).getSuspensionState());
				 prodefiListVo.setTenantId(prodefiInfoVo.getEntityrows().get(i).getTenantId());
				 prodefiListVo.setVersion(prodefiInfoVo.getEntityrows().get(i).getVersion());
				 
				 //System.out.println(prodefiInfoVo.getEntityrows().get(i).getId());
				 
				 prodefilist.add(prodefiListVo);
			}
			prodefiInfoVo.setRows(prodefilist);
			prodefiInfoVo.setEntityrows(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return prodefiInfoVo;		
	}
	
	
	
	/** 跳转流程部署页面 */
	@RequestMapping("/goAddProcessDefinition")
	public String goAddProcessDefinition() throws Exception {
		return "/WEB-INF/jsp/modprocessDefinition/addProcessDefinition";
	}
	
	/** 部署 */
	@RequestMapping("/AddProcessDefinition")
	public String addProcessDefinition(@RequestParam(value = "processFile", required = false) MultipartFile processFile,HttpServletRequest request) throws Exception {
		String fileName = processFile.getOriginalFilename();      //得到文件名   带.后缀
		
	    String path=request.getSession().getServletContext().getRealPath("/fileupload/processfile");
	    SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//上传文件名包含的日期的格式
		 Date date1 = DateTimeUtil.getNow();
	     String returnDate = pattern.format(date1);//当前日期   文件名所包含的
	     fileName=returnDate+"~"+fileName;  // 新的文件名 = 当前日期及时间 +文件名 
	    File oldFile=new File(path,fileName);
		if(oldFile.exists()){
			oldFile.delete();
		}
		File targetFile = new File(path, fileName);  				
		processFile.transferTo(targetFile); 
//		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(processFile));
		rtnFlg = processService.addProcessDefinition(fileName,path);
		request.setAttribute("rtnFlg", rtnFlg);
		return "/WEB-INF/jsp/modprocessDefinition/processDefinitionList";
	}

	
	     
	/**
	 * 删除流程定义
	 * @return
	 */
	@RequestMapping("/deleteProcessDefinition")
	@ResponseBody
	public String deleteProcessDefinition(String key, HttpServletRequest request) {
		try {
			if (key!=null) {
					String path=request.getSession().getServletContext().getRealPath("/fileupload/processfile");
					  String contain = key+".bar";//名字包含"XXX"的文件  
	    				File oldFile=new File(path,contain);
	    				  File file = new File(path); 
	    			        if(delFilesByPath(path,contain)){
	    			        	   System.out.println(path+"中包含"+contain+"的文件已经全部删除成功!");
	    			        	  }
	    				if(oldFile.exists()){
	    					oldFile.delete();
	    				}
				rtnFlg=processService.deleteProcessDefinition(key);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			rtnFlg="2";
		}
		return rtnFlg;
	}
	
	/**
	 * 删除流程定义 
	 * 用以模糊删除含str的文件（工具）
	 * 
	 * @return
	 */
	public static boolean delFilesByPath(String path, String str) {
		// 参数说明---------path:要删除的文件的文件夹的路径---------str:要匹配的字符串的头
		boolean b = false;
		File file = new File(path);
		File[] tempFile = file.listFiles();
		for (int i = 0; i < tempFile.length; i++) {
			if (tempFile[i].getName().startsWith(str)
					|| tempFile[i].getName().endsWith(str)) {
				tempFile[i].delete();
				b = true;
			}
		}
		return b;
	}
	
	
	/**
	 * 查看流程图（xxx.png）
	 * @return
	 */
	@RequestMapping("/downloadProcessImage")
	@ResponseBody
	public void downloadProcessImage(String id,HttpServletResponse response,HttpServletRequest request) {
        try {  
        	id = URLDecoder.decode(id, "utf-8"); // 自己再进行一次URL解码
            InputStream inputStream;  
        	inputStream = processService.getProcessImageResourceAsStream(id);
        	
            OutputStream os=response.getOutputStream();  
            byte[] b=new byte[1024];  
            int length;  
            while((length=inputStream.read(b))>0){  
                os.write(b,0,length);  
            }  
            inputStream.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } 
        catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	
	
//	=======================================================================
	/**
	 *  办理任务
	 *  跳转到办理任务详情页面
	 */
	@RequestMapping("/goHandleTask")
	public String goHandleTtask(String taskId,HttpServletRequest request) throws Exception {
//		//任务ID
//				//String taskId = workflowBean.getTaskId();
//				//获取任务表单中任务节点的url连接
//				String url = processService.findTaskFormKeyByTaskId(taskId);
//				url += "?taskId="+taskId;
//				request.setAttribute("url", url);
		
		/**一：使用任务ID，查找报销单ID，从而获取报销单信息*/
		HpoaReimburseInfos hpoaReimburseInfos=processService.findReimburseInfoByTaskId(taskId);
		String staffId=hpoaReimburseInfos.getStaffid();
		StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
		request.setAttribute("staff",staff);
		request.setAttribute("hri", hpoaReimburseInfos);
		request.setAttribute("taskId", taskId);
		
		/**二：已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中*/
		List<String> outcomeList = processService.findOutComeListByTaskId(taskId);
		request.setAttribute("outcomeList",outcomeList);
		/**三：查询所有历史审核人的审核信息，帮助当前人完成审核，返回List<Comment>*/
		List<Comment> commentList = processService.findCommentByTaskId(taskId);
		request.setAttribute("commentList",commentList);
		return "/WEB-INF/jsp/modprocessDefinition/HandleTask";
	}
	
	/**
	 *   准备表单数据          暂未用Form Key
	 *  
	 */
	@RequestMapping("/workflowAction_audit")
	public String workflowAction_audit(String taskId,HttpServletRequest request) throws Exception {
		/**一：使用任务ID，查找请假单ID，从而获取请假单信息*/
		HpoaReimburseInfos hpoaReimburseInfos=processService.findReimburseInfoByTaskId(taskId);
		String staffId=hpoaReimburseInfos.getStaffid();
		StaffDetailInfoVo staff=reimburseService.getStaffInfo(staffId);
		request.setAttribute("staff",staff);
		request.setAttribute("hri", hpoaReimburseInfos);
		
		return "/WEB-INF/jsp/modprocessDefinition/HandleTask";
	}
	

	/**
     * 提交任务
     * @return
     */
	@RequestMapping("/saveHandleInfo")
	@ResponseBody
    public String saveHandleInfo(String taskId,String reimburseId,String comment,String outcome,HttpSession session){
		try {
			String userName = ((UserInfo) session.getAttribute("user")).getUserName();
			
			rtnFlg = processService.saveHandleInfo(taskId,reimburseId,comment,outcome,userName);
		} catch (Exception e) {
			
			rtnFlg="2";
		}
    	return rtnFlg;
    }
	
	public String getRtnFlg() {
		return rtnFlg;
	}


	public void setRtnFlg(String rtnFlg) {
		this.rtnFlg = rtnFlg;
	}

	public String getActivityFontName() {
		return activityFontName;
	}

	public void setActivityFontName(String activityFontName) {
		this.activityFontName = activityFontName;
	}

	public String getLabelFontName() {
		return labelFontName;
	}

	public void setLabelFontName(String labelFontName) {
		this.labelFontName = labelFontName;
	}
	
	
	
	
}
