package com.dream.packing.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dream.packing.entity.Power;

@Repository("powerDao")
public class PowerDao extends CommonDao<Power> {
	
	public long addPower(Map<String, Object> powerMap) {
		return addClass(Power.class, powerMap);
	}
}
