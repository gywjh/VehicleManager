package com.ruixing.vehicle.manager.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruixing.vehicle.manager.domain.OperationInfo;
import com.ruixing.vehicle.manager.operation.entity.DelUpdaOperationRequest;
import com.ruixing.vehicle.manager.operation.entity.FindOperationRequest;
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
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delOperationInfo(@RequestBody DelUpdaOperationRequest delRequest)
	{
		String id = delRequest.getId();
		if(StringUtils.isNotBlank(id))
		{
			operService.deleteOperationInfo(id);
		}
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public Page<OperationInfo> getOperationInfoList(@RequestBody FindOperationRequest findRequest)
	{
		return operService.findOperationInfoList(findRequest);
	}
}
