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

	public void addUserInfo(UserInfo userInfo) {
		repository.save(userInfo);
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

	public void updateUserInfo(UserInfo userInfo) {
		repository.saveAndFlush(userInfo);
	}

	@Override
	public UserInfo queryUserInfoById(Integer id) {
		return repository.queryUserInfoById(id);
	}
}
