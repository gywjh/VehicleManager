package com.ruixing.vehicle.manager.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruixing.vehicle.manager.domain.UserInfo;
import com.ruixing.vehicle.manager.user.dao.IUserInfoDao;

public class UserInfoDaoImpl implements IUserInfoDao {

	public boolean addUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delUserInfoById(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<UserInfo> queryAllUserInfo() {
		List<UserInfo> users = new ArrayList<UserInfo>(10);
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId("1");
		userInfo.setUserName("laoyao");
		userInfo.setPhoneId("4567890");
		users.add(userInfo);
		return users;
	}

	public UserInfo queryUserInfoById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
