package com.dream.packing.mapper;

import java.util.List;
import java.util.Map;

import com.dream.packing.entity.Params;

public interface CommonMapper {
	
	public long executeAction(String sql);
	
	public Map<String, Object> findOneData(String sql);
	
	public List<Map<String, Object>> findManyData(String sql);
	
	public int addEntity(Params params);
	
	public long findCount(String sql);
	
	public int batchAdd(Params params);
}
