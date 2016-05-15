package com.dream.packing.backstage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.packing.common.util.ConvertUtil;
import com.dream.packing.server.RoleServer;

@Controller
@RequestMapping("/roleBackStage")
public class RoleBackStage {
	@Autowired
	private RoleServer roleServer;
	
	@RequestMapping("/addRole.do")
	@ResponseBody
	public Map<String, Object> loginForWord(String roleName, String powerIds) {
		return roleServer.addRole(roleName, ConvertUtil.strToList(Integer.class, powerIds));
	}
}
