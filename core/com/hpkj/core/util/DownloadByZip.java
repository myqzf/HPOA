package com.hpkj.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


public class DownloadByZip {
	
	public static void main(String[] args) {
		
		List li = new ArrayList();
		
		//li.add("新建 Microsoft Office Word 97-2003 文档");
		li.add("新建 Microsoft Office Word 97-2003 文档 - 副本");
		DownloadByZip.ZipFiles("d://aaa//", li, "d://","aaa.rar");
		

	}
	/**
	 * 入参：path 要打包的文件夹全路径
	 * 	   li   要打包的文件List
	 *     output 输出文件路径
	 *     outputfilename要输出的文件名
	 * @param path
	 * @param li
	 * @param outpath
	 * @param outfilename
	 */
	 public static void ZipFiles(String path, List li, String outpath, String outfilename) {
		    byte[] buf = new byte[1024];
		    try {
		     
		      File dir = new File (path);
		      File[] srcfile = dir.listFiles();	
		      	 File outfile = new File(outpath);
		      	 if (!outfile.exists()){
		      		 outfile.mkdirs();
		      	 }
		      	// System.out.println(outpath+outfilename);
			      // Create the ZIP file
			      ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outpath+outfilename));
			      
			      // Compress the files
			      for (int i = 0; i < srcfile.length; i++) {
				        FileInputStream in = new FileInputStream(srcfile[i]);
				        for (int j=0; j < li.size(); j++){
				        	String docname = li.get(j).toString()+".doc";
				        	//System.out.println(docname);
				        	//System.out.println(srcfile[i].getName());
				        	//要打包的文件名与文件夹中的文件名相同
				        	if (docname.equals(srcfile[i].getName())){
				        		//System.out.println("ok");
				        		 // Add ZIP entry to output stream.
						        out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				        
				        // Transfer bytes from the file to the ZIP file
				        int len;
				        while ( (len = in.read(buf)) > 0) {
				          out.write(buf, 0, len);
				        }
			        }
			        	continue;
			      }
		        out.setEncoding("gbk");
		        // Complete the entry
		        out.closeEntry();
		        in.close();
			      
		      }
		      
		      // Complete the ZIP file
		      out.close();
		     
		      System.out.println("压缩完成.");
		    }
		    catch (IOException e) {
		      e.printStackTrace();
		    }
	}
}
