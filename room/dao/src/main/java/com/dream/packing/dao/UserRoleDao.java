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
	
	public List<Map<String, Object>> findRoleByUserId(int userId) {
		String sql = "SELECT r.id, r.roleName FROM USERROLE ur LEFT JOIN ROLE r ON ur.roleId = r.id WHERE userId = " + userId;
		return findManyData(sql);
	}
}
