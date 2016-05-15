package com.dream.packing.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dream.packing.entity.UserRole;

@Repository("userRoleDao")
public class UserRoleDao extends CommonDao<UserRole> {
	
	public int bacthRole(List<Map<String, Object>> listMap) {
		return bacthRole(listMap);
	}
}
