package com.ruixing.vehicle.manager.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	@Override
	public Page<UserInfo> findAll(Pageable pageable, UserInfo userInfo) {
		Page<UserInfo> users = repository.findAll(new Specification<UserInfo>() {
			@Override
			public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				Path<String> phoneNo = root.get("phoneId");
				Path<String> userName = root.get("userName");
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(userInfo.getUserName())) {
					predicates.add(cb.like(userName.as(String.class), "%" + userInfo.getUserName() + "%"));
				}
				if (!StringUtils.isEmpty(userInfo.getPhoneId())) {
					predicates.add(cb.like(phoneNo.as(String.class), "%" + userInfo.getPhoneId() + "%"));
				}
				Predicate[] pre = new Predicate[predicates.size()];
				criteriaQuery.where(predicates.toArray(pre));
				return cb.and(predicates.toArray(pre));
			}
		}, pageable);
		return users;
	}
}
