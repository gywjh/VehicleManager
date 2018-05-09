package com.ruixing.vehicle.manager.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruixing.vehicle.manager.domain.OperationInfo;
import com.ruixing.vehicle.manager.operation.entity.SaveOperationRequest;
import com.ruixing.vehicle.manager.operation.service.OperationService;

@Controller
@EnableAutoConfiguration
@RequestMapping(path = "/operation")
public class OperationController {
	
	@Autowired
	private OperationService operService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void saveOperationInfo(@RequestBody SaveOperationRequest request)
	{
		if(null != request)
		{
			operService.getOperationInfo(request);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String  delOperationInfo(String id)
	{
		if(StringUtils.isNotBlank(id))
		{
			operService.deleteOperationInfo(id);
		}
		return "redirect:/operation/find";
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String getOperationInfoList(Model model)
	{
		List<OperationInfo> list =  operService.findAllList();
		model.addAttribute("opList", list);
		return "operation/list";
	}
}
