package com.dream.packing.common.constant;

import com.dream.packing.common.enumeration.SexEnum;

public class DataConstant {
	
	public final static Integer NORMALSTATE = 1;
	
	public final static Integer DELETEDSTATE = 2;
	
	public final static Integer TRYSTATE = 3;
	
	public final static Integer CLOSESTATE = 4;
	
	public final static Integer STOPSTATE = 5;
	
	public static void main(String[] args) {
		System.out.println(SexEnum.MAN.getSex());
	}
}
