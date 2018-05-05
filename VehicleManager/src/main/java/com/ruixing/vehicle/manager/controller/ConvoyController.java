package com.ruixing.vehicle.manager.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruixing.vehicle.manager.convoy.entity.ConvoyDelRequest;
import com.ruixing.vehicle.manager.convoy.entity.ConvoyFindRequest;
import com.ruixing.vehicle.manager.convoy.entity.ConvoyTime;
import com.ruixing.vehicle.manager.convoy.service.ConvoyService;
import com.ruixing.vehicle.manager.domain.ConvoyInfo;

@Controller
@EnableAutoConfiguration
@RequestMapping(path = "/convoy")
public class ConvoyController {
	
	@Autowired
	private ConvoyService convoyService;
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody ConvoyDelRequest delRequest)
	{
		if(null != delRequest)
		{
			String id = delRequest.getId();
			if(StringUtils.isNotBlank(id))
			{
				convoyService.delete(id);
			}
		}
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public Page<ConvoyInfo> findList(@RequestBody ConvoyFindRequest findRequest)
	{
		return convoyService.findAllList(findRequest);
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
