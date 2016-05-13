package com.dream.packing.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.packing.common.constant.DataConstant;
import com.dream.packing.common.enumeration.SexEnum;
import com.dream.packing.common.util.MD5Util;
import com.dream.packing.common.util.ValidateUtil;
import com.dream.packing.dao.PersonDao;
import com.dream.packing.dao.UserDao;
import com.dream.packing.server.UserServer;

@Service("userServer")
public class UserService implements UserServer {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PersonDao personDao;
	
	private Map<String , Object> dataMap = new HashMap<String, Object>();
	
	public Map<String, Object> loginForWord(String userName, String userPassword) {
		if(userName == null || userName.trim().equals("")) {
			dataMap.put("result", "failure");
			dataMap.put("message", "用户名不能为空");
			dataMap.put("data", null);
		} else if(userPassword == null || userPassword.trim().equals("")) {
			dataMap.put("result", "failure");
			dataMap.put("message", "密码不能为空");
			dataMap.put("data", null);
		} else {
			Map<String, Object> userMap = null;
			try {
				boolean isExist = userDao.validateUserName(userName);
				
				if(!isExist) {
					dataMap.put("result", "failure");
					dataMap.put("message", "用户名不存在");
					dataMap.put("data", null);
				} else {
					userPassword = MD5Util.convertMD5(MD5Util.string2MD5(userPassword));
					userMap = userDao.loginForword(userName, userPassword);
					
					if(userMap == null ||userMap.size() == 0) {
						dataMap.put("result", "failure");
						dataMap.put("message", "用户名或密码错误！");
						dataMap.put("data", null);
					} else {
						dataMap.put("result", "success");
						dataMap.put("message", "登录成功");
						dataMap.put("data", userMap);
					}
				}
			} catch (Exception e) {
				dataMap.put("result", "failure");
				dataMap.put("message", "用户名或密码包含特殊字符！");
				dataMap.put("data", null);
			}
		}
		return dataMap;
	}
	
	@Transactional
	public Map<String, Object> addUser(Map<String, Map<String, Object>> paramsMap) {
		
		if(paramsMap == null || paramsMap.size() == 0) {
			dataMap.put("result", "failure");
			dataMap.put("message", "请勿恶意请求谢谢！");
			dataMap.put("data", null);
		} else {
			Map<String, Object> userMap = paramsMap.get("user");
			Map<String, Object> personMap = paramsMap.get("person");
			
			if(userMap == null || userMap.size() == 0 || personMap == null || personMap.size() == 0) {
				dataMap.put("result", "failure");
				dataMap.put("message", "信息不完整，请将每个必填项中的内容填入谢谢合作！");
				dataMap.put("data", null);
			} else if(personMap.get("phone") == null) {
				dataMap.put("result", "failure");
				dataMap.put("message", "手机号不能为空！");
				dataMap.put("data", null);
			} else if(ValidateUtil.validatePhone(personMap.get("phone").toString()) == false) {
				dataMap.put("result", "failure");
				dataMap.put("message", "手机号格式不正确！");
				dataMap.put("data", null);
			} else if(userMap.get("passWord") == null) {
				dataMap.put("result", "failure");
				dataMap.put("message", "密码不能为空！");
				dataMap.put("data", null);
			} else if(ValidateUtil.validatePassword(userMap.get("passWord").toString()) == false) {
				dataMap.put("result", "failure");
				dataMap.put("message", "密码不符合要求，密码由8-16位数字或这字母组成！");
				dataMap.put("data", null);
			} else {
				if(personMap.get("gender") == null || ValidateUtil.validateSex(personMap.get("gender").toString()) == false) {
					personMap.put("gender", SexEnum.SECRECY.getSex());
				}
				
				userMap.put("headImages", "http://localhost/backstage/images/user.png");
				
				Date nowDateTime = new Date();
				personMap.put("lastModifiedTime", nowDateTime);
				userMap.put("createTime", nowDateTime);
				userMap.put("lastModifiedTime", nowDateTime);
				userMap.put("dataState", DataConstant.NORMALSTATE);
				userMap.put("passWord", MD5Util.convertMD5(MD5Util.string2MD5(userMap.get("passWord").toString())));
				
				long personId = personDao.addPerson(personMap);
				
				userMap.put("personId", personId);
				
				long userId = userDao.addUser(userMap);
				
				if(userId > 0) {
					paramsMap.put("user", userMap);
					paramsMap.put("person", personMap);
					dataMap.put("result", "success");
					dataMap.put("message", "添加成功！");
					dataMap.put("data", paramsMap);
				}
			}
		}
		return dataMap;
	}
}
