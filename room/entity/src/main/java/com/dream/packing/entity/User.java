package com.dream.packing.entity;

import java.util.Date;

public class User {
	
	private Integer id;

	private String userName;

	private String passWord;
	
	private String headImages;

	private Integer personId;

	private Date createTime;

	private Date lastModifiedTime;

	private boolean dataState;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getHeadImages() {
		return headImages;
	}

	public void setHeadImages(String headImages) {
		this.headImages = headImages;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public boolean isDataState() {
		return dataState;
	}

	public void setDataState(boolean dataState) {
		this.dataState = dataState;
	}

}
