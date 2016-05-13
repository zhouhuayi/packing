package com.dream.packing.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dream.packing.entity.User;

@Repository("userDao")
public class UserDao extends CommonDao<User> {
	
	public boolean validateUserName(String userName) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from User u ");
		sqlBuffer.append("left join Person p on u.personId = p.id ");
		sqlBuffer.append("where (p.phone = :userName or p.email = :userName) ");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		
		String sql = sqlAppend(sqlBuffer.toString(), params);
		return findCount(sql) > 0;
	}
	
	public Map<String, Object> loginForword(String userName, String userPassword) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select p.*,u.* from User u ");
		sqlBuffer.append("left join Person p on u.personId = p.id ");
		sqlBuffer.append("where (p.phone = :userName or p.email = :userName) ");
		sqlBuffer.append("and u.passWord = :passWord");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("passWord", userPassword);
		
		String sql = sqlAppend(sqlBuffer.toString(), params);
		
		return findOneData(sql);
	}
	
	public long addUser(Map<String, Object> userMap) {
		return addClass(User.class, userMap);
	}
}
