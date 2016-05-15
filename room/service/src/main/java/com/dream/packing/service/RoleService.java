package com.dream.packing.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.packing.common.abnormal.RollBackException;
import com.dream.packing.dao.RoleDao;
import com.dream.packing.dao.RolePowerDao;
import com.dream.packing.server.RoleServer;

@Service("roleServer")
public class RoleService implements RoleServer {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RolePowerDao rolePowerDao;
	
	private Map<String, Object> messageMap = new HashMap<String, Object>();
	
	@Transactional
	public Map<String, Object> addRole(String roleName, List<Integer> powerIds) {
		messageMap.put("result", "failure");
		messageMap.put("data", null);
		
		if(roleName == null || roleName.trim().equals("")) {
			messageMap.put("message", "角色名称不能为空或者空字符！");
		} else {
			Map<String, Object> roleMap  = new HashMap<String, Object>();
			Date nowTime= new Date();
			roleMap.put("createTime", nowTime);
			roleMap.put("lastModifiedTime", nowTime);
			roleMap.put("roleName", roleName);
			try {
				long roleId = roleDao.addRole(roleMap);
				roleMap.put("id", roleId);
				List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
				
				for (Integer powerId : powerIds) {
					Map<String, Object> mapData = new HashMap<String, Object>();
					mapData.put("powerId", powerId);
					mapData.put("roleId", roleId);
					
					listMap.add(mapData);
				}
				rolePowerDao.bacthAddPower(listMap);
				
				messageMap.put("result", "success");
				messageMap.put("message", "添加成功！");
				messageMap.put("data", roleMap);
			} catch (Exception e) {
				messageMap.put("message", "添加失败，请稍后再试！");
				throw new RollBackException("角色添加失败", e);
			}
		}
		return messageMap;
	}
}
