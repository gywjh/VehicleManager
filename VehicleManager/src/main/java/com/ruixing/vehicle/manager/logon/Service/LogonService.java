package com.ruixing.vehicle.manager.logon.Service;

import org.springframework.stereotype.Service;

import com.ruixing.vehicle.manager.logon.entity.UserEntity;

@Service
public class LogonService {
	
	public UserEntity verifyLogin(String name, String password)
	{
		//通过用户名和密码查询数据库，并进行判断
		UserEntity user = new UserEntity();
		user.setPhoneNumber("123");
		user.setRoleId("0");
		user.setUserId("11111");
		user.setUserName("admin");
		return user;
	}

}
