package com.dream.packing.common.util;

import java.security.MessageDigest;

public class MD5Util {
	/**
	 * MD5加码 生成32位md5码
	 * 
	 * @author 周化益
	 * @param inStr 传入的字符串
	 * @return 加密后的字符串
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = (md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		
		return hexValue.toString();
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 * 
	 * @author 周化益
	 * @param inStr 传入的字符串
	 */
	public static String convertMD5(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(string2MD5(string2MD5(convertMD5("dsadsa"))));
		System.out.println(convertMD5(string2MD5("dsadsa")));
	}
}
