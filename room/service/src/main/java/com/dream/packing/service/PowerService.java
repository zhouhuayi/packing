package com.dream.packing.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dream.packing.common.abnormal.RollBackException;
import com.dream.packing.dao.PowerDao;
import com.dream.packing.server.PowerServer;

@Service("powerServer")
public class PowerService implements PowerServer {
	
	@Autowired
	private PowerDao powerDao;
	
	private Map<String, Object> messageMap = new HashMap<String, Object>();
	
	@Transactional
	public Map<String, Object> addPower(String powerName, Integer parentPowerId) {
		messageMap.put("result", "failure");
		messageMap.put("data", null);
		
		
		if(powerName == null || powerName.trim().equals("")) {
			messageMap.put("message", "权限名称不能为空或者空字符！");
		} else {
			Map<String, Object> powerMap = new HashMap<String, Object>();
			Date nowTime= new Date();
			powerMap.put("createTime", nowTime);
			powerMap.put("lastModifiedTime", nowTime);
			powerMap.put("powerName", powerName);
			powerMap.put("parentPowerId", parentPowerId);
			try {
				long powerId = powerDao.addPower(powerMap);
				powerMap.put("id", powerId);
				
				messageMap.put("result", "success");
				messageMap.put("message", "添加成功！");
				messageMap.put("data", powerMap);
			} catch (Exception e) {
				messageMap.put("message", "添加失败，请稍后再试！");
				throw new RollBackException("添加失败", e);
			}
		}
		return messageMap;
	}
}
