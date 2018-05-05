package com.ruixing.vehicle.manager.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruixing.vehicle.manager.domain.UserInfo;

@Repository
public interface IUserInfoDao {
	
	List<UserInfo> queryAllUserInfo();
	
	UserInfo queryUserInfoById(String userId);
	
	boolean delUserInfoById(String userId);
	
	boolean addUserInfo(UserInfo userInfo);
	
	boolean updateUserInfo(UserInfo userInfo);
}
