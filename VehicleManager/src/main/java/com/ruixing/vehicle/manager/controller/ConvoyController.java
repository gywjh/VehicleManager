package com.ruixing.vehicle.manager.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruixing.vehicle.manager.convoy.entity.ConvoyTime;
import com.ruixing.vehicle.manager.convoy.service.ConvoyService;
import com.ruixing.vehicle.manager.domain.ConvoyInfo;

@Controller
@EnableAutoConfiguration
@RequestMapping(path = "/convoy")
public class ConvoyController {
	
	@Autowired
	private ConvoyService convoyService;
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(String id) {
		if (StringUtils.isNotBlank(id)) {
			convoyService.delete(id);
		}
		return "redirect:/convoy/find";
	}
		
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String findList(Model model)
	{
		List<ConvoyInfo> list =  convoyService.findAllList();
		model.addAttribute("conList", list);
		return "convoy/list";
	}
	
	@RequestMapping(value = "/updateConvoy", method = RequestMethod.POST)
	public void updateConvoyTime(ConvoyTime updateConvoy)
	{
		if(null != updateConvoy)
		{
			String id = updateConvoy.getId();
			int type = updateConvoy.getConvoyType();
			if(StringUtils.isNotBlank(id))
			{
				convoyService.updateConvoy(id, type);
			}
		}
	}

}
