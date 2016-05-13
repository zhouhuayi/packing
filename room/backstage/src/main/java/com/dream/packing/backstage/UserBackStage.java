package com.dream.packing.backstage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.packing.common.util.MD5Util;
import com.dream.packing.server.UserServer;

@Controller
@RequestMapping("/userBackStage")
public class UserBackStage {
	
	@Autowired
	private UserServer userServer;
	
	@RequestMapping("/loginForWord.do")
	@ResponseBody
	public Map<String, Object> loginForWord(String userName, String userPassword) {
		Map<String, Object> dataMap = userServer.loginForWord(userName, userPassword);
		if(dataMap.get("result").equals("success")) {
			dataMap.put("url", "index.html");
		}
		return dataMap;
	}
}