package com.ruixing.vehicle.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruixing.vehicle.manager.domain.VehicleInfo;
import com.ruixing.vehicle.manager.utils.PrintUtil;
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
	public String queryVehicle(Model model) {
		logger.error("start query message info.");
		List<VehicleInfo> pageInfo = vehicleService.queryVehicleIfo("1");
		model.addAttribute("vehicles", pageInfo);
		return "vehicle/list";
	}

	@RequestMapping(path = "/queryRyc", method = RequestMethod.GET)
	public String queryRycVehicle(Model model) {
		logger.error("start query message info.");
		List<VehicleInfo> pageInfo = vehicleService.queryVehicleIfo("0");
		model.addAttribute("vehicles", pageInfo);
		return "vehicle/Recyclebin";
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
	public String updateVehicle(Model model, String qrCode) {
		VehicleInfo vehicleInfo = vehicleService.findByQrCode(qrCode);
		model.addAttribute("vehicleInfo", vehicleInfo);
		return "vehicle/vehicleUpdate";
	}
	
	@RequestMapping(path = "/eidt", method = RequestMethod.POST)
	public String eidtVehicle(Model model, VehicleInfo vehicleInfo) {
		vehicleService.updateVehicleInfo(vehicleInfo);
		return "redirect:/vehicle/query";
	}

	@RequestMapping(path = "/remove", method = RequestMethod.GET)
	public String removeVehicle(Model model, String qrCode) {
		vehicleService.deleteVehicleInfo(qrCode);
		return "redirect:/vehicle/queryRyc";
	}

	@RequestMapping(path = "/delete", method = RequestMethod.GET)
	public String deleteVehicle(Model model, String qrCode) {
		VehicleInfo vehicleInfo = vehicleService.findByQrCode(qrCode);
		vehicleInfo.setStatus("0");
		vehicleService.updateVehicleInfo(vehicleInfo);
		return "redirect:/vehicle/query";
	}

	@RequestMapping(path = "/restore", method = RequestMethod.GET)
	public String restoreVehicle(Model model, String qrCode) {
		VehicleInfo vehicleInfo = vehicleService.findByQrCode(qrCode);
		vehicleInfo.setStatus("1");
		vehicleService.updateVehicleInfo(vehicleInfo);
		return "redirect:/vehicle/queryRyc";
	}

	@RequestMapping(path = "/findqr", method = RequestMethod.GET)
	public String findQRVehicle(Model model, String qrCode) {
		VehicleInfo vehicleInfo = vehicleService.findByQrCode(qrCode);
		model.addAttribute("qrCodePath", vehicleInfo.getQrCodePath());
		model.addAttribute("chpNo", vehicleInfo.getChpNo());
		model.addAttribute("qrCode", qrCode);
		return "vehicle/qrCode";
	}

	@RequestMapping(path = "/print", method = RequestMethod.GET)
	public String printQRImg(Model model, String qrCode) {
		VehicleInfo vehicleInfo = vehicleService.findByQrCode(qrCode);
		model.addAttribute("qrCodePath", vehicleInfo.getQrCodePath());
		model.addAttribute("chpNo", vehicleInfo.getChpNo());
		model.addAttribute("qrCode", qrCode);
		String filePath = null;
		try {
			filePath = ResourceUtils.getURL("classpath:").getPath() + "/static" + vehicleInfo.getQrCodePath();
			PrintUtil.drawImage(filePath, 1);
			model.addAttribute("tips", "打印成功");
		} catch (Exception e) {
			logger.error("打印二维码失败", e);
			model.addAttribute("tips", "打印失败，请重试");
		}
		return "vehicle/qrCode";
	}
}
