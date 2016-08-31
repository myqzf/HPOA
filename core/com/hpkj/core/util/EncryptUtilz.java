package com.hpkj.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.springframework.util.Assert;

/**
 * 描述：加密用类
 * DES加密模式默认为ECB模式，如果修改其它模式采用algorithm/mode/padding的方式，如下
 * Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
 * Cipher cipher = Cipher.getInstance("DES/CFB8/NoPadding");
 * Cipher cipher = Cipher.getInstance("DES/OFB32/PKCS5Padding");
 * 注意：NoPadding方式，要加解密的字符串字节长度必须是8的倍数.采用非ECB模式要使用IvParameterSpec偏移量
 * 
 * @author 梁焱
 * @since 2007-09-24
 */
public class EncryptUtilz {
	
	/**
	 * 默认密匙
	 */
	private static final String DEFAULT_PRIVATE_KEY = "_default_private_key_1.0";

	private byte[] privateKey;
	
	public EncryptUtilz() {
		this(DEFAULT_PRIVATE_KEY.getBytes());
	}
	
	public EncryptUtilz(byte[] privateKey) {
		Assert.notNull(privateKey, "密钥不能为空");
		this.privateKey = privateKey;
	}
    
    /**
     * 用MD5方式加密字符串
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String MD5(String str) {
    	try {
    		if(!StringUtilz.isEmpty(str)) {
    			MessageDigest md = MessageDigest.getInstance("MD5");
    	        md.update(str.getBytes());
    	        byte[] temp = md.digest();
    	        return TypeConvert.byte2HexStr(temp);
    		}
    	} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    	return str;
    }
    
    /**
     * 用SHA方式加密字符串
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String SHA(String str) {
    	try {
    		if(!StringUtilz.isEmpty(str)) {
    			MessageDigest md = MessageDigest.getInstance("SHA");
    	        md.update(str.getBytes());
    	        byte[] temp = md.digest();
    	        return TypeConvert.byte2HexStr(temp);
    		}
    	} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    	return str;
    }
    
    /**
     * 采用DES算法加密
     * @param str
     * @return String
     */
    public String DESEncrypt(String str) {
    	Assert.hasLength(str, "要加密的字符串不能为空.");
    	Assert.isTrue(privateKey.length >=8, "密钥长度不能小于8.");
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(privateKey);
			//创建一个密匙工厂，然后用它把DESKeySpec转换成   
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			//Cipher对象实际完成加密操作   
			Cipher cipher = Cipher.getInstance("DES");
			//用密匙初始化Cipher对象   
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			//现在，获取数据并加密   
			//正式执行加密操作   
			byte[] temp = cipher.doFinal(str.getBytes());
			return TypeConvert.byte2HexStr(temp);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 采用DES算法解密
     * @param str
     * @return String
     */
	public String DESDecrypt(String str) {
		Assert.hasLength(str, "要加密的字符串不能为空.");
    	Assert.isTrue(privateKey.length >=8, "密钥长度不能小于8.");
		try {
			// DES算法要求有一个可信任的随机数源   
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象   
			DESKeySpec desKey = new DESKeySpec(privateKey);
			// 创建一个密匙工厂   
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象   
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作   
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象   
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作   
			byte[] temp = cipher.doFinal(TypeConvert.hexStr2byte(str));
			return new String(temp);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 采用3DES(又叫TripleDES,DESede)算法加密
     * @param str
     * @return String
     */
    public String TripleDESEncrypt(String str) {
    	Assert.hasLength(str, "要加密的字符串不能为空.");
    	Assert.isTrue(privateKey.length >=24, "密钥长度不能小于24.");
		try {
			SecureRandom random = new SecureRandom();
			DESedeKeySpec desKey = new DESedeKeySpec(privateKey);
			//创建一个密匙工厂，然后用它把DESKeySpec转换成   
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			//Cipher对象实际完成加密操作   
			Cipher cipher = Cipher.getInstance("DESede");
			//用密匙初始化Cipher对象   
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			//现在，获取数据并加密   
			//正式执行加密操作   
			byte[] temp = cipher.doFinal(str.getBytes());
			return TypeConvert.byte2HexStr(temp);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 采用3DES(又叫TripleDES,DESede)算法解密
     * @param str
     * @return String
     */
	public String TripleDESDecrypt(String str) {
		Assert.hasLength(str, "要加密的字符串不能为空.");
    	Assert.isTrue(privateKey.length >=24, "密钥长度不能小于24.");
		try {
			// DES算法要求有一个可信任的随机数源   
			SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象   
			DESedeKeySpec desKey = new DESedeKeySpec(privateKey);
			// 创建一个密匙工厂   
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
			// 将DESKeySpec对象转换成SecretKey对象   
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作   
			Cipher cipher = Cipher.getInstance("DESede");
			// 用密匙初始化Cipher对象   
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// 真正开始解密操作   
			byte[] temp = cipher.doFinal(TypeConvert.hexStr2byte(str));
			return new String(temp);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		
		EncryptUtilz e = new EncryptUtilz();
		System.out.println(EncryptUtilz.MD5("123"));
		System.out.println(EncryptUtilz.MD5("MERCHANTID=105620148140002&POSID=896037225&BRANCHID=620000000&ORDERID=pay_20110811_202544_796_506&PAYMENT=1200.0&CURCODE=01&TXCODE=520100&REMARK1=&REMARK2="));
	}
}
