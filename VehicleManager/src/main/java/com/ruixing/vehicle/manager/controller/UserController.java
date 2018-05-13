package com.ruixing.vehicle.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruixing.vehicle.manager.domain.UserInfo;
import com.ruixing.vehicle.manager.user.service.IUserinfoService;
//RESTful用法：
// GET  根据用户id查询用户数据
// POST 新增用户
// PUT 修改用户信息
// DELETE 删除用户信息
@Controller
@EnableAutoConfiguration
@RequestMapping(path = "/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserinfoService service;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String addUser(UserInfo userInfo) {
		service.addUserInfo(userInfo);
		return "redirect:/user/query";
	}
	
	@RequestMapping(path = "/regist", method = RequestMethod.GET)
	public String registUser(UserInfo userInfo) {
		return "/user/userAdd";
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String updateUser(Model model, UserInfo userInfo) {
		service.updateUserInfo(userInfo);
		return "redirect:/user/query";
	}
	
	@RequestMapping(path = "/edit", method = RequestMethod.GET)
	public String eidtUser(Model model, Integer id) {
		UserInfo user = service.queryUserInfoById(id);
		model.addAttribute("user", user);
		return "/user/userUpdate";
	}
	
	
	@RequestMapping(path = "/delete", method = RequestMethod.GET)
	public String deleteUser(Model model, Integer id) {
		service.delUserInfoById(id);
		return "redirect:/user/query";
	}

	@RequestMapping(path = "/query", method = RequestMethod.GET)
	public String queryUser(Model model) {
		logger.error("start query message info.");
		List<UserInfo> users = service.queryAllUserInfo();
		model.addAttribute("users", users);
		return "user/list";
	}

	@RequestMapping(path = "/find", method = RequestMethod.GET)
	public String findUserByName(Model model,String userName) {
		logger.error("start query message info.");
		UserInfo user = service.queryUserInfoByUserName(userName);
		List<UserInfo> users = new ArrayList<UserInfo>(3);
		users.add(user);
		model.addAttribute("users", users);
		return "user/list";
	}
}
