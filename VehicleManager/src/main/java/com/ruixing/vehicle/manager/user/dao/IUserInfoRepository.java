package com.ruixing.vehicle.manager.user.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ruixing.vehicle.manager.domain.UserInfo;

@Repository
public interface IUserInfoRepository extends JpaRepository<UserInfo, Integer> {

	UserInfo queryUserInfoByUserName(String userName);

	UserInfo queryUserInfoById(Integer id);
	
	@Query(value = "select phone_id from users", nativeQuery = true)
	List<String> queryPhoneNumber();
	
	/**
	 * 分页查询
	 * @param spc
	 * @param pageable
	 * @return
	 */
	Page<UserInfo> findAll(Specification<UserInfo> spc, Pageable pageable);

}
