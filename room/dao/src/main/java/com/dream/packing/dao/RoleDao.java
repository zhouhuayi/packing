package com.dream.packing.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dream.packing.entity.Role;

@Repository("roleDao")
public class RoleDao extends CommonDao<Role> {
	public long addRole(Map<String, Object> roleMap) {
		return addClass(Role.class, roleMap);
	}
}
