package com.dream.packing.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dream.packing.entity.RolePower;

@Repository("rolePowerDao")
public class RolePowerDao extends CommonDao<RolePower> {
	
	public int bacthAddPower(List<Map<String, Object>> listMap) {
		return batchAdd(RolePower.class, listMap);
	}
}
