package com.dream.packing.backstage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.packing.server.PowerServer;

@Controller
@RequestMapping("/powerBackStage")
public class PowerBackStage {
	@Autowired
	private PowerServer powerServer;
	
	@RequestMapping("/addPower.do")
	@ResponseBody
	public Map<String, Object> addPower(String powerName, Integer parentPowerId) {
		return powerServer.addPower(powerName, parentPowerId);
	}
}
