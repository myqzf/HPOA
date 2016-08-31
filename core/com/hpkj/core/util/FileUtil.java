package com.hpkj.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FileUtil {
private static int BUFFER_SIZE = 1024 * 1024 ;
	
	public static String getValue(String key){
		return ResourceBundle.getBundle("sys").getString(key);
	}
	
	public static String getUploadFileValue(String key){
		return ResourceBundle.getBundle("fileupload").getString(key);
	}
	
	public static void main(String[] args){
		System.out.println(FileUtil.getValue("file_web_path"));	
	}
	
	/**
	 * 
	 * @Title: writeTxt
	 * @Description: TODO(写文件)
	 * @param path
	 * @param fileName
	 * @param errorContent
	 *            void
	 * 
	 */
	public static void writeTxt(String file, String conttent)
	{
		try
		{
			File tmp = new File(file);
			FileWriter fw = null;
			try
			{
				fw = new FileWriter(tmp, true);
				fw.append(conttent + "\n");
			} catch (Exception e)
			{
				System.out.println(e);
			} finally
			{
				fw.flush();
				fw.close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: copy
	 * @Description: TODO(文件复制)
	 * @param src
	 * @param dst
	 * @throws IOException
	 *             void
	 * 
	 */
	public static void copy(File src, File dst) throws IOException
	{

		File p = dst.getParentFile();
		if (!p.exists())
		{
			p.mkdirs();
		}

		try
		{
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try
			{
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;
				while ((len = in.read(buffer)) > 0)
				{
					out.write(buffer, 0, len);
				}
			} finally
			{
				if (null != in)
				{
					in.close();
				}
				if (null != out)
				{
					out.close();
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new IOException("拷贝文件" + src.getName() + "到" + dst.getName() + "时出错！");
		}
	}

	/**
	 * 
	 * @Title: getresource
	 * @Description: TODO(取资源文件)
	 * @param str
	 * @return String
	 * 
	 */
	public static String getResource(String str)
	{
		return ResourceBundle.getBundle("sys").getString(str);
	}

	
	/**
	 * 根据文件名称，key查询配置文件中的值
	 * @param propertiesName 配置文件名称
	 * @param str key
	 * @return
	 */
	public static String getSqlResource(String propertiesName,String str){
		return ResourceBundle.getBundle(propertiesName).getString(str);
	}
	
	/**
	 * 
	 * @Title: Q2B
	 * @Description: TODO(全角转半角)
	 * @param str
	 * @return String
	 * 
	 */
	public static String Q2B(String str)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if (c >= 65281 && c < 65373)
				sb.append((char) (c - 65248));
			else
				sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Title: getHtmlPicture
	 * @Description: TODO(描述方法的作用)
	 * @param httpUrl
	 *            远程路径
	 * @return String
	 * 
	 */
	public static String getRemotePic(String httpUrl,String loclUrl)
	{
	
		URL url;
		BufferedInputStream in;
		FileOutputStream file;
		File locFile = new File(loclUrl);
		File p = locFile.getParentFile();
		if (!p.exists())
		{
			p.mkdirs();
		}

		try
		{
			//System.out.println("获取网络图片");
			url = new URL(httpUrl);
			in = new BufferedInputStream(url.openStream());

			file = new FileOutputStream(locFile);
			int t;
			while ((t = in.read()) != -1)
			{
				file.write(t);
			}
			file.close();
			in.close();
			//System.out.println("图片获取成功");
			
			return loclUrl;
			
		}catch (Exception e)
		{
			//e.printStackTrace();
			return null;
		}
	}
}
