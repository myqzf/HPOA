
/**
 * 类描述：字符串工具类
 * 创建人：王科飞
 * 创建时间：2015-5-12  上午9:51:21 	
 */
package com.hpkj.core.util;



import org.apache.commons.lang3.StringUtils;




public class StringUtilz {

	/**
	 * 将所有开始字符和结束字符中间的内容去掉
	 * @param src 原字符串
	 * @param beginStr 开始字符
	 * @param endStr 结束字符
	 * @param repStr 替换字符
	 * @return 结果字符串
	 */
	public static  String replaceResource(String beginStr,String endStr,String src,String repStr){
		if(isEmpty(beginStr) || isEmpty(endStr) || isEmpty(src)){
			return "";
		}
		if(isEmpty(repStr)){
			repStr = "";
		}
//		int inx=0;//开始字符串开始查找的位置
//		int endInx=0;//结束字符串开始查找的位置
		//以结束字符串个数为准。
		while(src.indexOf(beginStr)!= -1 && src.indexOf(endStr)!= -1){
			String src1 = src.substring(0, src.indexOf(endStr)+endStr.length());
//			int i=0;//控制器,寻找距离结束字符串最近的开始字符串位置
//			while(src1.indexOf(beginStr,i)!= -1){
//				i=src1.indexOf(beginStr,i)+beginStr.length();
//			}
//			i -= beginStr.length();
//			i = src1.lastIndexOf(beginStr);
			int b1 = src1.lastIndexOf(beginStr);
			int b2 = src1.indexOf(endStr)+endStr.length();
//			String s1 = src.substring(b1, b2);
//			src =src.replaceFirst(s1, repStr) ;//操他妈的，小括号竟然无法去除，而且还特么的死循环，FUCK，只能自己写一个替换了
			//以下是自己写的replace方法
			if(b1==-1 || b2==-1){
				break;
			}
			src = myReplace(src,b1,b2,"");
		}
		return src;
	}
	
	public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
	 
	
	/**
	 * 替换
	 * @param resourceStr 原字符串
	 * @param begin 开始位置
	 * @param end 结束位置
	 * @param replaceByte  替换字符
	 * @return
	 */
	public static String myReplace(String resourceStr,int begin,int end,String replaceByte){
		String rtn = "";
		if(resourceStr.length()<end){
			System.out.println("你特么的在玩儿我么？结束位置比特么原字符串长度都长！直接给你返回空了，自己看着办吧！");
			return rtn;
		}
		String s1 = resourceStr.substring(0,begin);
		String s2 = resourceStr.substring(end, resourceStr.length());
		rtn = s1+replaceByte+s2;
		return rtn;
	}
	
	/**
	 * 查找字符串中指定字符的个数
	 * @param beginStr 开始字符串
	 * @param endStr 结束字符串
	 * @param src
	 * @return
	 */
	public static int indexNum(String beginStr,String endStr,String src){
		if(isEmpty(beginStr) || isEmpty(endStr) || isEmpty(src)){
			return 0;
		}
		int endNum = 0;//字符串中结束字符串的个数
		int inx=0;//开始字符串开始查找的位置
		int endInx=0;//结束字符串开始查找的位置
		while((inx=src.indexOf(beginStr,inx))!= -1 && (endInx=src.indexOf(endStr,endInx))!= -1){
			endNum++;
			inx += beginStr.length();
			endInx += endStr.length();
		}
		return endNum;
	}

	
	
	/**
	 * 查询内容，截取关键部分，返回结果
	 * @param resource 源数据
	 * @param queryStr 关键字
	 * @param front 前边最大字符数
	 * @param behind 后边最大字符数
	 * @param number 关键字出现次数
	 * @param replaceStr 替换掉多于内容
	 * @return
	 */
	public static String subResource(String resource,String queryStr,int front,int behind,int number,String replaceStr){
		String rtn = "";
		//非空验证
		if(StringUtils.isBlank(resource) || StringUtils.isBlank(queryStr)){
			return rtn;
		}
		//开始处理
		int begin=0;
		String cenStr=resource;
		String endStr;
		if(resource.indexOf(queryStr)==-1){
			return rtn;
		}
		for(int i=0;i<number;i++){
			int a = cenStr.indexOf(queryStr);
			//第一次循环处理
			if(i==0){
				//如果起始位置小于前边长度
				if(a<front){
					//判断长度是否足够
					if(resource.length()>(a+queryStr.length()+behind)){
						cenStr = resource.substring(0, a+queryStr.length()+behind) + replaceStr;
						endStr = resource.substring(a+queryStr.length()+behind);
					}else {
						//第一次从头开始长度就不够，直接返回原数据
						rtn = resource;
						break;
					}
				}else {
				//如果起始位置大于前边长度
					//判断数据长度是否足够，如果足够，进行处理
					if(resource.length()>(a+queryStr.length()+behind)){
						cenStr = resource.substring(a-front, a+queryStr.length()+behind) + replaceStr;
						endStr = resource.substring(a+queryStr.length()+behind);
					}else {
						//如果不足够，返回
						rtn = resource.substring(a-front,resource.length());
						break;
					}
				}
			}else {
				if(a<0){
					break;
				}
				if(a<begin){
					continue;
				}
				//后续循环处理
				if(a-begin<front){
					//判断长度是否足够，如果足够，进行正常处理
					if(cenStr.length()>(a+queryStr.length()+behind+replaceStr.length())){
						endStr = cenStr.substring(a+queryStr.length()+behind);
						cenStr = cenStr.substring(begin,a+queryStr.length()+behind)+ replaceStr;
					}else {
						//如果长度不够，处理完毕返回
						cenStr = cenStr.substring(begin);
						rtn += cenStr;
						break;
					}
				}else {
					//判断长度是否足够，如果足够，进行正常处理
					if(cenStr.length()>(a+queryStr.length()+behind+replaceStr.length())){
						endStr = cenStr.substring(a+queryStr.length()+behind);
						cenStr = cenStr.substring(a-front,a+queryStr.length()+behind)+ replaceStr;
					}else {
						cenStr = cenStr.substring(a-front);
						rtn += cenStr;
						break;
					}
				}
			}
			cenStr = cenStr.replaceAll(queryStr, "■■■■■■■■■■");
			rtn += cenStr;
			cenStr = rtn;
			begin=cenStr.length();
			cenStr +=endStr;
		}
		rtn = rtn.replaceAll("■■■■■■■■■■", queryStr);
		if(rtn.length()>300){
			rtn = rtn.substring(0, 300);
			rtn += "......";
		}
		return rtn;
	}
	
	
	/**
	 * 查询内容，截取关键部分，返回结果
	 * @param resource 源数据
	 * @param queryStr 关键字
	 * @param front 前边最大字符数
	 * @param behind 后边最大字符数
	 * @param number 关键字出现次数
	 * @param replaceStr 替换掉多于内容
	 * @return
	 */
	public static String subResource1(String resource,String queryStr,int front,int behind,int number,String replaceStr){
		String rtn = "";
		//非空验证
		if(StringUtils.isBlank(resource) || StringUtils.isBlank(queryStr)){
			return rtn;
		}
		//开始处理
		int begin=0;
		String cenStr=resource;
		String endStr;
		if(resource.indexOf(queryStr)==-1){
			rtn = resource;
			if(rtn.length()>150){
				rtn = rtn.substring(0, 150);
				rtn += "......";
			}
			return rtn;
		}
		for(int i=0;i<number;i++){
			int a = cenStr.indexOf(queryStr);
			//第一次循环处理
			if(i==0){
				//如果起始位置小于前边长度
				if(a<front){
					//判断长度是否足够
					if(resource.length()>(a+queryStr.length()+behind)){
						cenStr = resource.substring(0, a+queryStr.length()+behind) + replaceStr;
						endStr = resource.substring(a+queryStr.length()+behind);
					}else {
						//第一次从头开始长度就不够，直接返回原数据
						rtn = resource;
						break;
					}
				}else {
				//如果起始位置大于前边长度
					//判断数据长度是否足够，如果足够，进行处理
					if(resource.length()>(a+queryStr.length()+behind)){
						cenStr = resource.substring(a-front, a+queryStr.length()+behind) + replaceStr;
						endStr = resource.substring(a+queryStr.length()+behind);
					}else {
						//如果不足够，返回
						rtn = resource.substring(a-front,resource.length());
						break;
					}
				}
			}else {
				if(a<0){
					break;
				}
				if(a<begin){
					continue;
				}
				//后续循环处理
				if(a-begin<front){
					//判断长度是否足够，如果足够，进行正常处理
					if(cenStr.length()>(a+queryStr.length()+behind+replaceStr.length())){
						endStr = cenStr.substring(a+queryStr.length()+behind);
						cenStr = cenStr.substring(begin,a+queryStr.length()+behind)+ replaceStr;
					}else {
						//如果长度不够，处理完毕返回
						cenStr = cenStr.substring(begin);
						rtn += cenStr;
						break;
					}
				}else {
					//判断长度是否足够，如果足够，进行正常处理
					if(cenStr.length()>(a+queryStr.length()+behind+replaceStr.length())){
						endStr = cenStr.substring(a+queryStr.length()+behind);
						cenStr = cenStr.substring(a-front,a+queryStr.length()+behind)+ replaceStr;
					}else {
						cenStr = cenStr.substring(a-front);
						rtn += cenStr;
						break;
					}
				}
			}
			cenStr = cenStr.replaceAll(queryStr, "■■■■■■■■■■");
			rtn += cenStr;
			cenStr = rtn;
			begin=cenStr.length();
			cenStr +=endStr;
		}
		rtn = rtn.replaceAll("■■■■■■■■■■", queryStr);
		if(rtn.length()>300){
			rtn = rtn.substring(0, 300);
			rtn += "......";
		}
		return rtn;
	}
	
	
	/**
	* 方法描述:左填补符号和位数
	* @param 原始字符串,期望长度,填补符号
	* @return 填充后的字符串
	* date:2015年10月9日
	*/
	public static String rpad(String str, int n, String replace) {
		StringBuilder sb = new StringBuilder(str);
			while (sb.length() < n) {
				sb.append(replace);
			}
		return sb.toString();
	}

	/**
	* 方法描述:右填补符号和位数
	* @param 原始字符串,期望长度,填补符号
	* @return 填充后的字符串
	* date:2015年10月9日
	*/
	public static String lpad(String str, int n, String replace) {
		StringBuilder sb = new StringBuilder();
			while ((sb.length() + str.length()) < n) {
				sb.append(replace);
			}
		return sb.append(str).toString();
	}
	
	/** 测试
	 * @param args
	 */
	public static void main(String[] args) {
//		String ss = subResource("智能无辐射技术与现有其他的无线充电技术相比，iNPOFi没有辐射，采用电场脉冲模式，不产生任何辐射，中国泰尔实验室测试结果显示，辐射增加值近乎零。 在高效方面，泰尔试验室还测定，该技术的产品，充电传输效率高达90%以上，彻底改变了传统无线充电最高70%以下电转换低效率问题。 在智能管理方面，采用芯片适配管理技术，其中包括：自动开启、关闭充电过程；自动适配需要的电压、电流，管理充电过程，以确保较高的充电效率；并可以使用一个统一的充电板，为任何品牌、型号的电子产品，进行安全、便利、高效的充电。 在安全性方面，同时考虑到了各种弱电充电中的安全性问题，如静电ESD保护、防过充、防冲击等等，甚至若受电设备自身电源管理出现问题时，可以通过inpofi芯片自动熔断保护电子设备不被损坏。 值得一提的是，对于智能设备厂商而言，inpofi以一颗极小的芯片为核心，实现了超微化设计，仅有1/4个五毛硬币大小，可以方便的集成到任何设备中，也可以集成到各种形态的可穿戴设备中。这是传统电磁原理的产品无法达到的。", "电", 1000, 6, 10, "......");
//		System.out.println(ss);
		//String ss = "<p><span style=color: rgb(50, 50, 50); font-family: 仿宋_GB2312; font-size: 19px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: 34px; orphans: auto; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;>在纪念抗战胜利70周年的日子里，伴随着中央电视台隆重播出大型文献纪录片《平山记忆》，人民政协报副刊也于7月13日、7月27日、8月10日，分三期连载了长篇英雄史诗《子弟兵的呼唤——平山团组诗》，各地媒体纷纷转载。</span><span style=color: rgb(50, 50, 50); font-family: 仿宋_GB2312; font-size: 19px; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; line-height: 34px; orphans: auto; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); display: inline !important; float: none;></span></p>";
		//System.out.println(replaceResource("<", ">", ss, ""));
		String a = "2";
		System.out.println(lpad(a, 4, "0"));
	}
}
