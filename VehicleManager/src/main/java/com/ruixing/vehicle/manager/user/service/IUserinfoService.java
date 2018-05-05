package com.ruixing.vehicle.manager.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ruixing.vehicle.manager.domain.UserInfo;

@Service(value = "UserinfoServiceImpl")
public interface IUserinfoService {

	List<UserInfo> queryAllUserInfo();

	UserInfo queryUserInfoById(String userId);

	boolean delUserInfoById(String userId);

	boolean addUserInfo(UserInfo userInfo);

	boolean updateUserInfo(UserInfo userInfo);

}
