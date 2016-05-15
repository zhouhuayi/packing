package com.dream.packing.entity;

import java.util.Date;

public class Power {
	private int id;
	
	private String powerName;
	
	private int parentPowerId;
	
	private Date createTime;
	
	private Date lastModifiedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}
	
	public int getParentPowerId() {
		return parentPowerId;
	}

	public void setParentPowerId(int parentPowerId) {
		this.parentPowerId = parentPowerId;
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
}
