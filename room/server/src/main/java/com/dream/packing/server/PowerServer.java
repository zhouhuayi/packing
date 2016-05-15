package com.dream.packing.server;

import java.util.Map;

public interface PowerServer {
	public Map<String, Object> addPower(String powerName, Integer parentPowerId);
}
