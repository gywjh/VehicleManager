package com.ruixing.vehicle.manager.logon.Service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruixing.vehicle.manager.domain.UserInfo;
import com.ruixing.vehicle.manager.user.dao.IUserInfoRepository;

@Service
public class LogonService {
	
	@Autowired
	private IUserInfoRepository repository;
	
	public UserInfo verifyLogin(String userName, String password)
	{
		UserInfo user = repository.queryUserInfoByUserName(userName);
		if(null != user)
		{
			if (StringUtils.equals(password, user.getPassword()))
			{
				return user;
			}
		}
		return null;
	}

}
