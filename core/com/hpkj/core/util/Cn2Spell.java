package com.hpkj.core.util;

import org.apache.commons.lang3.StringUtils;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**  
 * 汉字转换位汉语拼音，英文字符不变  
 * @author xuke  
 *  
 */  
public class Cn2Spell {   
	
	
    
    /**  
 * 汉字转换为汉语拼音首字母，英文字符不变  
    * @param chines 汉字  
    * @return 拼音  
    */  
    public static String converterToFirstSpell(String chines){          
        String pinyinName = "";   
        char[] nameChar = chines.toCharArray();   
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
        for (int i = 0; i < nameChar.length; i++) {   
            if (nameChar[i] > 128) {   
                try {   
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);   
                } catch (BadHanyuPinyinOutputFormatCombination e) {   
                    e.printStackTrace();   
                }   
            }else{   
                pinyinName += nameChar[i];   
            }   
        }   
        return pinyinName;   
    }   
    
    /**  
    * 汉字转换位汉语拼音，英文字符不变  
    * @param chines 汉字  
    * @return 拼音  
    */  
    public static String converterToSpell(String chines){
        String pinyinName = "";   
        char[] nameChar = chines.toCharArray();   
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
        for (int i = 0; i < nameChar.length; i++) {   
            if (nameChar[i] > 128) {   
                try {   
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];   
                } catch (BadHanyuPinyinOutputFormatCombination e) {   
                    e.printStackTrace();   
                }   
            }else{   
                pinyinName += nameChar[i];   
            }   
        }   
        return pinyinName;   
    }   
    
    
    
    /**
	 * 去掉符号
	 * @return
	 */
	public static String removeSign(String resource){
		String rtn = "";
		if(StringUtils.isNotBlank(resource)){
			StringBuffer a = new StringBuffer();
	        char[] chars = resource.toCharArray();   
	        for(int i = 0; i < chars.length; i ++) {   
	            if((chars[i] >= 19968 && chars[i] <= 40869) || (chars[i] >= 97 && chars[i] <= 122) || (chars[i] >= 65 && chars[i] <= 90)) {   
	                a.append(chars[i]);
	            }   
	        }
	        rtn = a.toString();
		}
		return rtn;
	}

	
	
	
    
	 public static void main(String[] args) { 
//	        String a = StringUtilz.replaceResource("<", ">", "<p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">重庆部发布住房公积金异地贷款操作细则，缴存城市公积金中心负责审核职工缴存和已贷款情况，并向贷款城市公积金中心出具书面证明。经济之声《央广财经评论》本期观点：公积金异地买房真正落地仍有难度。</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">央广网北京9月22日消息 据经济之声《央广财经评论》报道，公积金异地无法贷款的问题，一直困扰着一些工作调动的缴存人。比如原先在A城市工作，缴存了不少公积金，调动到B城市后，如国想在B城市买房，原来在A城市缴存的公积金就无法使用。这造成了大量的公积金闲置，白白浪费了缴存人的钱。今后，这样的困扰可以消除了。住建部近日发布《关于住房公积金异地个人住房贷款有关操作问题的通知》。通知明确住房公积金异地个人住房贷款办理流程，并提出，公积金管理部门应抓紧出台异地贷款业务细则。此外，住建部将建设全国住房公积金异地贷款业务信息交换系统。去年10月，住建部等三部委曾出台政策，要求公积金缴存异地互认，并推进异地贷款业务，但是相关细则一直没有出台。有业内人士指出，相比贷款条件、调整缴存期限等政策，“异地互贷”落实仍有难度，因此，此次住建部下发的通知将再次加速“异地互贷”的推进。也为今后各地出台异地贷款细则给出了行动指南。</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">重庆公积金异地贷款细则的出台会给房地产市场带来怎样的影响？著名房产专家、浙报传媒地产研究院院长丁建刚就此做出了解读与评论。</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">经济之声：这一次住建部出台的这个细则能不能让异地贷款加速在全国落地？现在来看，能不能实现全国范围内的公积金贷款的互认？</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">丁建刚：既然细则出台了，应该速度会大大加快，因为这个细则规定的还是比较细，比如在公积金的缴存地有一些什么样的权利和义务等，这些会大大推进异地贷款的速度。</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">实现全国范围内的互认，我还不敢这么乐观，但至少在大中城市和联网更方便的一些城市可能推进速度会更快一些。</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">经济之声：现在最大的一个问题就是公积金的额度问题。公积金首套二套的政策已经是历史上最宽松的，但是想到达到首付两成，对于目前的公积金额度来说来比较难，不同的城市公积金贷款的额度不同，怎样能够实现异地的互认？</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">丁建刚：额度问题基本很难判断，因为每个城市的额度确实不太一样，比如我所在的杭州，如果夫妇俩是可以代贷到80万这样的额度。总的来说，异地贷款实现之后，公积金的用款量会大幅度上升。可以这样判断，既有正向流动，又有逆向流动，所谓正向流动即是说，这些外来务工人员可能会在他们所务工的城市购房，同时也会有一部分务工人员在他所在务工的城市缴公积金，但他可能会回到家乡某一个县城来购房，这种双向的作用都是有的。</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">经济之声：是不是对于三四线城市来说，这个政策应该利好更多一些？</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">丁建刚：总量来说，我估计可能利好会更多一些，因为现在外来的务工人员有2亿多，主要在东南沿海城市务工，这些人员大部分可能最终还是要回到自己家乡购房。如果能够实现公积金的异地贷款互认，如果他们缴存公积金量比较大，在他们的家乡，通常内陆县城房价还是低的多，有公积金的帮助，他们可能会尽快实现购房的愿望。</p><p style\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">经济之声：以前异地贷款为什么不能承认？难点在哪儿？</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">丁建刚：首先可能技术手段上有一些障碍，但更多的障碍在于公积金管理还是有一些地方的利益，因为缴存在这个地方，就归你缴存地的公积金中心来管理的，这个很难说，可能还是有一些体制上的差异。</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">经济之声：次住建部出台的细则会对楼市带来怎样的影响？</p><p style=\"margin: 0px 0px 25px; padding: 0px; border: 0px; outline: 0px; font-size: 14px; line-height: 24px; text-indent: 28px !important; color: rgb(51, 51, 68); font-family: 宋体, Arial, Helvetica, Tahoma, Helvetica; font-style: normal; font-variant: normal; font-weight: normal; letter-spacing: normal; orphans: auto; text-align: start; text-transform: none; white-space: normal; widows: auto; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255);\">丁建刚：虽然一二线城市今年上半年到现在复苏的情况比较明显，但是三、四线城市复苏仍然比较低迷，出台这样的政策主要还是稳定和支持住房消费、提振楼市的信心，我想可能会有一些正面的影响，但是这个影响也不能过高。因为现在对楼市最大的影响实际还是整个宏观经济、货币政策，公积金的政策只是占了一小部分，并不是说所有人都能够使用公积金，都能够缴存公积金。理论上大家都要缴存，实际上还是有相当一部分人没有缴公积金。所以对整个市场的影响仅仅是一个参数，而且不是决定性的参数。</p><p><br/></p>", "");
//	        System.out.println(a);
//	        CppccDraftInfo cdi = new CppccDraftInfo();
//	        cdi.setContentnosign(removeSign(cdi.getDraftcontent()));
//	        cdi.setFirstpinyin(converterToSpell(cdi.getContentnosign()));
//	        System.out.println(cdi.getFirstpinyin());
//	        String b = removeSign(a);
//	        System.out.println(b);
//	        String pinyinString = converterToFirstSpell(b);
//	        System.out.println(pinyinString);
	    }    

}  


