package com.ruixing.vehicle.manager.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruixing.vehicle.manager.domain.UserInfo;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Integer> {

	UserInfo queryUserInfoByUserName(String userName);

	UserInfo queryUserInfoById(Integer id);
}
