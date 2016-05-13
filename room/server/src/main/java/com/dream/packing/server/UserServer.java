package com.dream.packing.server;

import java.util.Map;

public interface UserServer {
	public Map<String, Object> loginForWord(String userName, String userPassword);
	
	public Map<String, Object> addUser(Map<String, Map<String, Object>> mapData);
}
