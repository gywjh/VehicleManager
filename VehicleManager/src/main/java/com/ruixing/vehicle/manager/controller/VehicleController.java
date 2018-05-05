package com.ruixing.vehicle.manager.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruixing.vehicle.manager.domain.VehicleInfo;
import com.ruixing.vehicle.manager.vehicle.service.IVehicleService;
import com.ruixing.vehicle.manager.vehicle.service.impl.VehicleServiceImpl;

@Controller
@EnableAutoConfiguration
@RequestMapping(path = "/vehicle")
public class VehicleController {

	private Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

	@Resource
	private IVehicleService vehicleService;

	@RequestMapping(path = "/query", method = RequestMethod.GET)
	public String queryVehicle(Model model, String startTime, String endTime) {
		logger.error("start query message info.");
		// Page<VehicleInfo> pageInfo = vehicleService.queryVehicleIfo(startTime,
		// endTime);
		Page<VehicleInfo> pageInfo = vehicleService.queryAll();
		model.addAttribute("vehicles", pageInfo);
		return "vehicle/list";
	}

	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public String addVehicle(Model model) {
		return "vehicle/vehicleAdd";
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String registerVehicle(Model model, VehicleInfo vehicleInfo) {
		vehicleService.addVehicleInfo(vehicleInfo);
		return "redirect:/vehicle/query";
	}

	@RequestMapping(path = "/update", method = RequestMethod.GET)
	public String updateVehicle(Model model, VehicleInfo vehicleInfo) {
		vehicleService.updateVehicleInfo(vehicleInfo);
		return "redirect:/vehicle/query";
	}

	@RequestMapping(path = "/delete", method = RequestMethod.GET)
	public String deleteVehicle(Model model, String qrCode) {
		vehicleService.deleteVehicleInfo(qrCode);
		return "redirect:/vehicle/query";
	}

	@RequestMapping(path = "/findqr", method = RequestMethod.GET)
	public String findQRVehicle(Model model, String qrCode) {
		VehicleInfo vehicleInfo = vehicleService.findByQrImg(qrCode);
		model.addAttribute("qrCodePath", vehicleInfo.getQrCodePath());
		model.addAttribute("chpNo", vehicleInfo.getChpNo());
		return "vehicle/qrCode";
	}
}
