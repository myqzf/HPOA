package com.hpkj.process.utils;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.security.auth.message.callback.PrivateKeyCallback;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import antlr.collections.List;


import com.hpkj.core.dao.IBaseDao;
import com.hpkj.login.vo.UserInfo;
import com.hpkj.process.dao.ProcessDao;
import com.hpkj.process.service.ProcessService;


/**
 * 员工经理任务分配
 *
 */
@SuppressWarnings("serial")
public class ManagerTaskHandler implements TaskListener {

	@Resource(name="processService")
	private  ProcessService processService;
	@Resource
	private ProcessDao processDao;
	@Autowired
	private  HttpServletRequest request;
	@Override
	public void notify(DelegateTask delegateTask) {
//		/**懒加载异常*/
//		Employee employee = SessionContext.get();
//		//设置个人任务的办理人
//		delegateTask.setAssignee(employee.getManager().getName());
//		UserInfo user = (UserInfo) pageContext.getSession().getAttribute("user");
        String assignee =delegateTask.getAssignee();
 //       HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
 //       HttpSession session = RequestContextHolder.getRequestAttributes().getRequest().getSession();
		/**从新查询当前用户，再获取当前用户对应的领导*/
//		 org.hibernate.Session session= processDao.getSession();
//		String username =this.getUserInfo(session);
//		System.out.println("username"+username);
		HttpSession session=request.getSession();
//		String userName = ((UserInfo) ((ServletRequest) session).getAttribute("user")).getUserName();
		UserInfo ui=(UserInfo)session.getAttribute("user");
		System.out.println("userName"+ui);
//		String name = ResourceUtil.getSessionUserName().getUserName();
//		String username= request.getSession().getAttribute("users").toString();//得到员工id
//		System.out.println("username"+username);
//		Employee employee = SessionContext.get();
//		//当前用户
//		String name = employee.getName();
//		//使用当前用户名查询用户的详细信息
//		//从web中获取spring容器
//		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
//		IEmployeeService employeeService = (IEmployeeService) ac.getBean("employeeService");
//		Employee emp = employeeService.findEmployeeByName(name);
		//设置个人任务的办理人
//		delegateTask.setAssignee(emp.getManager().getName());
		
	}
	

}
