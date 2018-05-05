package com.ruixing.vehicle.manager.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class UserController {

	/*@Autowired
	private IUserinfoService service;*/
	
    @RequestMapping("/userinfo")
    @ResponseBody
    public String home() {
    	return "hello";
    	/*List<UserInfo> userInfos = service.queryAllUserInfo();
        return JSONObject.toJSONString(userInfos);*/
    }

}
