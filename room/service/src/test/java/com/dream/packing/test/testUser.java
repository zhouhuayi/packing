package com.dream.packing.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dream.packing.dao.RolePowerDao;
import com.dream.packing.dao.UserRoleDao;
import com.dream.packing.server.PowerServer;
import com.dream.packing.server.RoleServer;
import com.dream.packing.server.UserServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:Spring.xml")
public class testUser {
	@Autowired
	private UserServer userServer;
	
	@Autowired
	private PowerServer powerServer;
	
	@Autowired
	private RoleServer roleServer;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RolePowerDao rolePowerDao;
	
	@Test
	public void test() {		
//		Map<String, Map<String, Object>> dataMap = new HashMap<String, Map<String,Object>>();
//		User user = new User();
//		user.setPassWord("weilaishi123");
//		user.setUserName("启梦之路007");
//		user.setId(1);
//		
//		Person person = new Person();
//		person.setPhone("13003221674");
//		person.setEmail("weilai_zhilu4@sina.com");
//		
//		dataMap.put("user", ConvertUtil.convertBean(user));
//		dataMap.put("person", ConvertUtil.convertBean(person));
		
//		System.out.println(userServer.addUser(dataMap));
		
		System.err.println(userServer.loginForWord("13003221671", "weilaishi123"));
		
//		System.out.println(powerServer.addPower("提供方管理", 1));
//		System.out.println(powerServer.addPower("需求方管理", 1));
//		System.out.println(powerServer.addPower("后台维护人员管理", 1));
//		System.out.println(powerServer.addPower("权限管理", null));
//		System.out.println(powerServer.addPower("角色管理", null));
		
//		List<Integer> powerIds = new ArrayList<Integer>();
//		powerIds.add(1);
//		powerIds.add(3);
//		powerIds.add(2);
//		System.out.println(roleServer.addRole("后台管理员", powerIds));
//		System.out.println(userRoleDao.findRoleByUserId(1));
		
		System.out.println(rolePowerDao.findPowers("3"));
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("roleId", 3);
		map1.put("powerId", 4);
		listMap.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("roleId", 3);
		map2.put("powerId", 5);
		listMap.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("roleId", 3);
		map3.put("powerId", 6);
		listMap.add(map3);
		rolePowerDao.bacthAddPower(listMap);
		
		//登陆
		/*System.out.println(accountService.login("admin", "123"));
		
		String eventSql = "create event if not exists ev_test2 "
				+ " on schedule every 50 second "
				+ " on completion preserve "
				+ " do call signin();";
		
		System.out.println(commonDao.addClass(eventSql));*/
	}
}