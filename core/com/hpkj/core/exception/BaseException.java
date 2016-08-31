package com.hpkj.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ****************************************************
 * 
 * @ClassName SystemException
 * @Description TODO(通用异常类)
 * @author g
 * @date 20150506
 * 
 * ****************************************************
 */
public class BaseException extends Exception {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    /** 
     *   
     */  
    public BaseException() {  
        super();  
    }  
  
    /** 
     * @param arg0 
     */  
    public BaseException(String arg0) {  
        super(arg0);  
    }  
  
    /** 
     * @param arg0 
     * @param arg1 
     */  
    public BaseException(String arg0, Throwable arg1) {  
        super(arg0, arg1);  
    }  
  
    /** 
     * @param arg0 
     */  
    public BaseException(Throwable arg0) {  
        super(arg0);  
    }  
}	
