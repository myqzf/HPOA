package com.hpkj.core.util;

import java.io.File;
import java.io.IOException;

public class DownloadBySingle {
	public static void main(String[] args) {
		
		File sf = new File("d:/aaa/新建 Microsoft Office Word 97-2003 文档.doc");
		File tf = new File("f:/bbb/新建 Microsoft Office Word 97-2003 文档.doc");
		
		try {
			FileUtil.copy(sf, tf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void CopyFile(String sf, String tf){
		File sfile = new File(sf);
		File tfile = new File(tf);
		try {
			FileUtil.copy(sfile, tfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
