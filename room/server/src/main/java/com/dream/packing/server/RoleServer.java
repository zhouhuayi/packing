package com.dream.packing.server;

import java.util.List;
import java.util.Map;

public interface RoleServer {
	public Map<String, Object> addRole(String roleName, List<Integer> powerIds);
}
