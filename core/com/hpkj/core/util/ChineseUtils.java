package com.hpkj.core.util;

import org.springframework.util.Assert;

public class ChineseUtils {
	
	/**
	 * 检查此字符串是否为中文
	 * @param str 待检验的字符串
	 * @return true 包含
	 */
	public static boolean containsChinese(String str) {
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c) == false) {
				return false;
			}
		}
		return true;
	}
	
    /**
     * 检查此字符是否为中文
     * @param c
     * @return true 如果是中文
     */
    public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS						//CJK 统一表意符号[u4E00-u9FBF]
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS		//CJK 兼容象形文字[uF900-uFAFF]
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A	//CJK 统一表意符号扩展[u3400-u4DBF]
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION 				//常用标点[u2000-u206F]
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION			//CJK 符号和标点[u3000-u303F]
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {	//半角及全角形式[uFF00-uFFEF]
			return true;
		}
		return false;
	}
    
    /**
	 * 将汉字转换为Unicode编码
	 * 
	 * @param s
	 * @return Unicode编码
	 */
    public static String chineseToUnicode(String s) {
		String[] str = new String[s.length()];
		String temp = "";
		for (int i = 0; i < s.length(); i++) {
			str[i] = Integer.toHexString(s.charAt(i) & 0xffff);
			temp = temp + "\\u" + str[i];
		}
		return temp;
	}
    
    /**
     * 将Unicode编码转换为汉字
     * @param s
     * @return 汉字
     */
    public static String unicodeToChinese(String s) {
    	Assert.hasLength(s);
		String str = s.replaceAll("\\u", ",");
		String[] array = str.split(",");
		String temp = "";
		for (int i = 1; i < array.length; i++) {
			temp = temp + (char) Integer.parseInt(array[i], 16);
		}
		return temp;
	}
}
