package com.hpkj.staff.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownExcel {
	public static void down(String newFile,String fileName,HttpServletRequest request,HttpServletResponse response){
		File f = new File(newFile);  
		try{	
		 	if (fileName.indexOf("/") != -1) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
			}
	        if(f.exists()){  
	            FileInputStream  fis = new FileInputStream(f);  
	            if(isIE(request)){
		            fileName=URLEncoder.encode(fileName,"utf-8"); //解决中文文件名下载后乱码的问题   (火狐中文件名为乱码)
	            }else{
		            fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");//解决中文文件名下载后乱码的问题   (火狐中文件名正常)
	        	
	            }
	            byte[] b = new byte[fis.available()];  
	            fis.read(b);  
	            response.setCharacterEncoding("utf-8");  
	            response.setHeader("Content-Disposition","attachment; filename="+fileName); 
	            response.setContentType("text/html;charset=utf-8");
	            //获取响应报文输出流对象   
	            ServletOutputStream  out =response.getOutputStream();  
	            //输出   
	            out.write(b);  
	            out.flush();  
	            fis.close();
	            f.delete();
	            File ff=new File(f.getParent());
	            ff.delete();
	            out.close(); 
	        }
        } catch(Exception e){
			e.printStackTrace();

        }
	}

	public static void shareDown(String newFile,String fileName,HttpServletRequest request,HttpServletResponse response){
		File f = new File(newFile);  
		try{	
		 	if (fileName.indexOf("/") != -1) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
			}
	        if(f.exists()){  
	            FileInputStream  fis = new FileInputStream(f);  
	            if(isIE(request)){
		            fileName=URLEncoder.encode(fileName,"utf-8"); //解决中文文件名下载后乱码的问题   (火狐中文件名为乱码)
	            }else{
		            fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");//解决中文文件名下载后乱码的问题   (火狐中文件名正常)
	        	
	            }
	            byte[] b = new byte[fis.available()];  
	            fis.read(b);  
	            response.setCharacterEncoding("utf-8");  
	            response.setHeader("Content-Disposition","attachment; filename="+fileName); 
	            response.setContentType("text/html;charset=utf-8");
	            //获取响应报文输出流对象   
	            ServletOutputStream  out =response.getOutputStream();  
	            //输出   
	            out.write(b);  
	            out.flush();  
	            fis.close();
	            out.close(); 
	        }
        } catch(Exception e){
			e.printStackTrace();

        }
	}
	
	/**
	 * @Title: isIE
	 * @Description:TODO (判断是否为IE浏览器)
	 * @return true or false;
	 * 
	 */
	public static  boolean isIE(HttpServletRequest request) {
		System.out.println(request.getHeader("USER-AGENT")+"====================================================");
		return request.getHeader("USER-AGENT")
				.toLowerCase().indexOf("msie") > 0 ||  request.getHeader("USER-AGENT")
				.toLowerCase().indexOf("rv:11") >0? true : false;
	}
}
