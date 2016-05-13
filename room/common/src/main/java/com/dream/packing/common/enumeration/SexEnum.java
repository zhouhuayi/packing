package com.dream.packing.common.enumeration;

public enum SexEnum {
	MAN (1), WOMAN (2), SECRECY (3);
	// 定义私有变量  
    private int sex ;  

    // 构造函数，枚举类型只能为私有  
    private SexEnum(int sex) {  
        this.sex = sex;  
    }

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}
