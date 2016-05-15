package com.dream.packing.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.packing.common.abnormal.RollBackException;
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

	private Map<String, Object> messageMap = new HashMap<String, Object>();

	public Map<String, Object> loginForWord(String userName, String userPassword) {
		messageMap.put("result", "failure");
		messageMap.put("data", null);
		
		if (userName == null || userName.trim().equals("")) {
			messageMap.put("message", "用户名不能为空");
		} else if (userPassword == null || userPassword.trim().equals("")) {
			messageMap.put("message", "密码不能为空");
		} else {
			Map<String, Object> userMap = null;
			try {
				boolean isExist = userDao.validateUserName(userName);

				if (!isExist) {
					messageMap.put("message", "用户名不存在");
				} else {
					userPassword = MD5Util.convertMD5(MD5Util.string2MD5(userPassword));
					userMap = userDao.loginForword(userName, userPassword);

					if (userMap == null || userMap.size() == 0) {
						messageMap.put("message", "用户名或密码错误！");
					} else {
						messageMap.put("result", "success");
						messageMap.put("message", "登录成功");
						messageMap.put("data", userMap);
					}
				}
			} catch (Exception e) {
				messageMap.put("message", "用户名或密码包含特殊字符！");
			}
		}
		return messageMap;
	}

	@Transactional
	public Map<String, Object> addUser(Map<String, Map<String, Object>> paramsMap) throws RuntimeException {
		messageMap.put("result", "failure");
		messageMap.put("data", null);
		
		if (paramsMap == null || paramsMap.size() == 0) {
			messageMap.put("message", "请勿恶意请求谢谢！");
		} else {
			Map<String, Object> userMap = paramsMap.get("user");
			Map<String, Object> personMap = paramsMap.get("person");

			if (userMap == null || userMap.size() == 0 || personMap == null || personMap.size() == 0) {
				messageMap.put("message", "信息不完整，请将每个必填项中的内容填入谢谢合作！");
			} else if (personMap.get("phone") == null) {
				messageMap.put("message", "手机号不能为空！");
			} else if (ValidateUtil.validatePhone(personMap.get("phone").toString()) == false) {
				messageMap.put("message", "手机号格式不正确！");
			} else if (userMap.get("passWord") == null) {
				messageMap.put("message", "密码不能为空！");
			} else if (ValidateUtil.validatePassword(userMap.get("passWord").toString()) == false) {
				messageMap.put("message", "密码不符合要求，密码由8-16位数字或这字母组成！");
			} else if(userDao.validateUserName(personMap.get("phone").toString())) {
				messageMap.put("message", "手机号已被注册！");
			} else if(personMap.get("phone") !=null && userDao.validateUserName(personMap.get("email").toString())) {
				messageMap.put("message", "邮箱已被注册！");
			} else {
				if (personMap.get("gender") == null || ValidateUtil.validateSex(personMap.get("gender").toString()) == false) {
					personMap.put("gender", SexEnum.SECRECY.getSex());
				}
				userMap.put("headImages","http://localhost/backstage/images/user.png");
				Date nowDateTime = new Date();
				personMap.put("lastModifiedTime", nowDateTime);
				userMap.put("createTime", nowDateTime);
				userMap.put("lastModifiedTime", nowDateTime);
				userMap.put("dataState", DataConstant.NORMALSTATE);
				userMap.put("passWord",MD5Util.convertMD5(MD5Util.string2MD5(userMap.get("passWord").toString())));
				try {

					long personId = personDao.addPerson(personMap);
					personMap.put("id", personId);
					userMap.put("personId", personId);

					long userId = userDao.addUser(userMap);
					userMap.put("id", userId);
					paramsMap.put("user", userMap);
					paramsMap.put("person", personMap);
					messageMap.put("result", "success");
					messageMap.put("message", "添加成功！");
					messageMap.put("data", paramsMap);

				} catch (Exception e) {
					messageMap.put("message", "添加失败，请稍后再试！");
					throw new RollBackException("用户添加失败", e);
				}
			}
		}
		return messageMap;
	}
}
