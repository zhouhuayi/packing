package com.dream.packing.common.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	
	/**
	 * 验证手机号
	 * 
	 * @author zhy
	 * @param mobiles 手机号码
	 * @return
	 */
	public static boolean validatePhone(String mobiles) {  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);  
		return m.matches();
	}
	
	/**
	 * 验证性别
	 * 
	 * @author zhy
	 * @param sex 性别
	 * @return
	 */
	public static boolean validateSex(String sex) {
		Pattern p = Pattern.compile("^[1-3]$");
		Matcher m = p.matcher(sex);  
		return m.matches();
	}
	
	/**
	 * 验证密码
	 * 
	 * @author zhy
	 * @param password 密码
	 * @return
	 */
	public static boolean validatePassword(String password) {
		Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
		Matcher m = p.matcher(password);  
		return m.matches();
	}
	
	
	public static void main(String[] args) throws IOException {  
		System.out.println(ValidateUtil.validatePhone("////'\1~!#@%$&^%(*&"));
		System.out.println(ValidateUtil.validateSex("3"));
	}
}
