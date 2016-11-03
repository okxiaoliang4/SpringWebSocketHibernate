package com.jelf.test;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.jelf.util.Util;

/**
 * 创建时间：2015-2-6 下午3:31:07
 * 
 * @author andy
 * @version 2.2
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml",
//		"classpath:spring-hibernate.xml" })
public class TestUserService {

	private static final Logger LOGGER = Logger
			.getLogger(TestUserService.class);

//	@Autowired
//	private UserService userService;
//
//	@Test
//	public void save() {
//		User user = new User();
//		user.setUsername("andy");
//		user.setRegisterDate(new Date());
//		user.setPassword("13022221111");
//		userService.register(user);
//		LOGGER.info(JSON.toJSON(user));
//	}

	@Test
	public void uuid() {
		System.out.println(UUID.randomUUID());
	}

	@Test
	public void MD5() {
		System.out.println(Util.MD5("a"));
	}

}