package com.dream.packing.server;

import java.util.List;
import java.util.Map;

public interface RolePowerServer {
	public Map<String, Object> addUserPower(int roleId, List<Integer> powerIds);
}
