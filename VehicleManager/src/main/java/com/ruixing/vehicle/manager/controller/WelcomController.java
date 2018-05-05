package com.ruixing.vehicle.manager.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAutoConfiguration
public class WelcomController {
	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String queryMessage(Model model) {
		return "index";
	}
}
