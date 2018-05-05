package com.ruixing.vehicle.manager.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruixing.vehicle.manager.domain.UserInfo;
import com.ruixing.vehicle.manager.user.dao.IUserInfoDao;
import com.ruixing.vehicle.manager.user.service.IUserinfoService;

public class UserinfoServiceImpl implements IUserinfoService {

	@Autowired
	private IUserInfoDao dao;

	public boolean addUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delUserInfoById(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<UserInfo> queryAllUserInfo() {
		// TODO Auto-generated method stub
		return null;
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
