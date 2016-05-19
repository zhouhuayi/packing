package com.dream.packing.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dream.packing.entity.RolePower;

@Repository("rolePowerDao")
public class RolePowerDao extends CommonDao<RolePower> {
	
	public int bacthAddPower(List<Map<String, Object>> listMap) {
		return batchAdd(RolePower.class, listMap);
	}
	
	public List<Map<String, Object>> findPowers(String roles) {
		String sql ="select p.id, p.powerName, rp.roleId, p.parentPowerId, p.redirectUrl from rolePower rp left join power p on rp.powerId = p.id "
				+ "where rp.roleId in("+roles+')';
		return findManyData(sql);
	}
}
