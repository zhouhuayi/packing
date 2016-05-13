package com.dream.packing.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dream.packing.common.util.BeanConvertMapUtil;
import com.dream.packing.entity.Person;
import com.dream.packing.entity.User;
import com.dream.packing.server.UserServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:Spring.xml")
public class testUser {
	@Autowired
	private UserServer userServer;
	
	@Test
	public void test() {		
//		Map<String, Map<String, Object>> dataMap = new HashMap<String, Map<String,Object>>();
//		User user = new User();
//		user.setPassWord("weilaishi123");
//		user.setUserName("启梦之路");
//		
//		Person person = new Person();
//		person.setPhone("13003221671");
//		person.setEmail("weilai_zhilu@sina.com");
//		
//		dataMap.put("user", BeanConvertMapUtil.convertBean(user));
//		dataMap.put("person", BeanConvertMapUtil.convertBean(person));
//		
//		System.out.println(userServer.addUser(dataMap));
		System.out.println(userServer.loginForWord("13003221671", "weilaishi123"));
		//登陆
		/*System.out.println(accountService.login("admin", "123"));
		
		String eventSql = "create event if not exists ev_test2 "
				+ " on schedule every 50 second "
				+ " on completion preserve "
				+ " do call signin();";
		
		System.out.println(commonDao.addClass(eventSql));*/
	}
}