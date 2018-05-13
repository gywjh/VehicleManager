package com.ruixing.vehicle.manager.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruixing.vehicle.manager.domain.UserInfo;
import com.ruixing.vehicle.manager.user.dao.IUserInfoRepository;
import com.ruixing.vehicle.manager.user.service.IUserinfoService;
@Service
public class UserinfoServiceImpl implements IUserinfoService {

	@Autowired
	private IUserInfoRepository repository;

	public boolean addUserInfo(UserInfo userInfo) {
		if(null != repository.queryUserInfoByUserName(userInfo.getUserName()))
		{
			return false;
		}
		repository.save(userInfo);
		return true;
	}

	public void delUserInfoById(Integer id) {
		repository.delete(id);
	}

	public List<UserInfo> queryAllUserInfo() {
		return repository.findAll();
	}

	public UserInfo queryUserInfoByUserName(String userName) {
		return repository.queryUserInfoByUserName(userName);
	}

	public boolean updateUserInfo(UserInfo userInfo) {
		UserInfo user = repository.queryUserInfoByUserName(userInfo.getUserName());
		if (!user.getId().equals(userInfo.getId()))
		{
			return false;
		}
		repository.save(userInfo);
		return true;
	}

	@Override
	public UserInfo queryUserInfoById(Integer id) {
		return repository.queryUserInfoById(id);
	}
}
