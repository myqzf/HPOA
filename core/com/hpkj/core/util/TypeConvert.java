package com.hpkj.core.util;

/**
 * 
 */
public class TypeConvert {
	
	/**
	 * 字节数组到short的转换
	 * @param b
	 * @return short
	 */
	public static short byte2short(byte[] b) {
		short s = 0;
        short s0 = (short) (b[0] & 0xff);// 最低位
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
	}

	/**
	 * 字节数组到int的转换
	 * @param b
	 * @return int
	 */
	public static int byte2int(byte[] b) {
		return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16
				| (b[0] & 0xff) << 24;
	}

	/**
	 * 字节数组到long的转换
	 * @param b
	 * @return long
	 */
	public static long byte2long(byte[] b) {
		return (long) b[7] & (long) 255 | ((long) b[6] & (long) 255) << 8
				| ((long) b[5] & (long) 255) << 16
				| ((long) b[4] & (long) 255) << 24
				| ((long) b[3] & (long) 255) << 32
				| ((long) b[2] & (long) 255) << 40
				| ((long) b[1] & (long) 255) << 48 | (long) b[0] << 56;
	}
	
	/**
	 * 将一个字节数组转换成十六进制的字符串形式返回
	 * @param b
	 * @return String
	 */
	public static String byte2HexStr(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for(int i = 0; i < b.length; i++) {
			sb.append(Character.forDigit((b[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(b[i] & 0x0f, 16));
		}
		return sb.toString();
	}
	
	/**
	 * short到字节数组的转换
	 * @param n
	 * @return byte数组
	 */
	public static byte[] short2byte(int n) {
		byte[] b = new byte[2];
		b[0] = (byte) (n >> 8);
		b[1] = (byte) n;
		return b;
	}
	
	/**
	 * int到字节数组的转换
	 * @param n
	 * @return byte数组
	 */
	public static byte[] int2byte(int n) {
		byte[] b = new byte[4];
		b[0] = (byte) (n >> 24);
		b[1] = (byte) (n >> 16);
		b[2] = (byte) (n >> 8);
		b[3] = (byte) n;
		return b;
	}
	
	/**
	 * long到字节数组的转换
	 * @param n
	 * @return byte数组
	 */
	public static byte[] long2byte(long n) {
		byte[] b = new byte[8];
		b[0] = (byte) (int) (n >> 56);
		b[1] = (byte) (int) (n >> 48);
		b[2] = (byte) (int) (n >> 40);
		b[3] = (byte) (int) (n >> 32);
		b[4] = (byte) (int) (n >> 24);
		b[5] = (byte) (int) (n >> 16);
		b[6] = (byte) (int) (n >> 8);
		b[7] = (byte) (int) n;
		return b;
	}
	
	/**
	 * 十六进制字符串到字节数组的转换
	 * @param n
	 * @return byte数组
	 */
	public static byte[] hexStr2byte(String n) {
		byte[] arrB = n.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2   
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	
	/**
	 * char 转 byte
	 * @param c
	 * @return byte
	 */
	public static byte char2Byte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	} 
}
