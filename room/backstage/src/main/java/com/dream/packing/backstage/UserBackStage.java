package com.dream.packing.backstage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.packing.server.UserServer;

@Controller
@RequestMapping("/userBackStage")
public class UserBackStage {
	
	@Autowired
	private UserServer userServer;
	
	@RequestMapping("/loginForWord.do")
	@ResponseBody
	public Map<String, Object> loginForWord(String userName, String userPassword, HttpServletRequest request) {
		Map<String, Object> dataMap = userServer.loginForWord(userName, userPassword);
		if(dataMap.get("result").equals("success")) {
			request.getSession().setAttribute("loginInfo", dataMap.get("data"));
		}
		return dataMap;
	}
	
	@RequestMapping("/getLoginSession.do")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, Object> getLoginSession(HttpServletRequest request) {
		return (Map<String, Object>) request.getSession().getAttribute("loginInfo");
	}
}