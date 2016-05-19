package com.dream.packing.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dream.packing.entity.Person;

@Repository("personDao")
public class PersonDao extends CommonDao<Person> {
	
	public long addPerson(Map<String, Object> personMap) {
		return addClass(Person.class, personMap);
	}
	
	public boolean updatePerson(Map<String, Object> personMap, String whereSql) {
		return updateByWhere(Person.class, personMap, whereSql);
	}
}
