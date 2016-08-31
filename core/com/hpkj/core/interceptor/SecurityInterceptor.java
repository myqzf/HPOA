package com.hpkj.core.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hpkj.core.exception.AuthorizationException;
import com.hpkj.login.service.LoginService;
import com.hpkj.login.vo.UserInfo;
@Controller("SecurityInterceptor")
public class SecurityInterceptor implements HandlerInterceptor {
    private List<String> excludedUrls;
    @Resource
    private LoginService loginService;
    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }
 
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	System.out.println("**interceptor**");
        // excluded URLs:
        String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
        	System.out.println("^^"+url);
            if (requestUri.endsWith(url)) {
                return true;
            }
        }
 
        // intercept
        HttpSession session = request.getSession();
        //如果没有登录或与库中会话id不同并且请求的不是js文件，则拦截该请求
        if ((session.getAttribute("user") == null || !loginService.checkSession(((UserInfo)session.getAttribute("user")).getUserID(), session.getId()))) {
        	throw new AuthorizationException();
        } else {
            return true;
        }
    }
    
    @Override
    public void afterCompletion(HttpServletRequest arg0,
    		HttpServletResponse arg1, Object arg2, Exception arg3)
    		throws Exception {
    	// TODO Auto-generated method stub
    	
    }

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
}