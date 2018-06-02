package com.ruixing.vehicle.manager.user.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ruixing.vehicle.manager.domain.UserInfo;

public interface IUserinfoService {

	List<UserInfo> queryAllUserInfo();

	UserInfo queryUserInfoByUserName(String userName);
	
	UserInfo queryUserInfoById(Integer id);

	void delUserInfoById(Integer userId);

	boolean addUserInfo(UserInfo userInfo);

	boolean updateUserInfo(UserInfo userInfo);
	
	/**
	 * 分页查询
	 */
	public 	Page<UserInfo> findAll(Pageable page,UserInfo userInfo);

}
