package com.ruixing.vehicle.manager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruixing.vehicle.manager.logon.Service.LogonService;
import com.ruixing.vehicle.manager.logon.entity.UserEntity;

@Controller
@EnableAutoConfiguration
public class LogonController {
	
	@Autowired
	private LogonService logonService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVerify(Model model, String username,String password,HttpSession session){
        UserEntity user = logonService.verifyLogin(username, password);
        if (null != user) {
            session.setAttribute("user", user);
            return "index";
        } else {
        	model.addAttribute("errorMessage", "用户名或密码错误");
            return "login";
        }
    }
	
	//登录页面  
    @RequestMapping(value = "/login", method = RequestMethod.GET)  
    public String login(){  
        return "login";  
    }  
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute("user");
        return "redirect:/login";
    }

}
