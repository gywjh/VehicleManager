package com.ruixing.vehicle.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruixing.vehicle.manager.domain.TableEntity;
import com.ruixing.vehicle.manager.domain.UserInfo;
import com.ruixing.vehicle.manager.domain.VehicleInfo;
import com.ruixing.vehicle.manager.user.service.IUserinfoService;
import com.ruixing.vehicle.manager.vehicle.service.IVehicleService;

@RestController
public class DataController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserinfoService service;

	@Resource
	private IVehicleService vehicleService;
	
	@RequestMapping(path = "/user/queryUser", method = RequestMethod.GET)
	public TableEntity<UserInfo> queryUser() {
		logger.error("start query message info.");
		TableEntity<UserInfo> tableEntity = new TableEntity<UserInfo>();
		List<UserInfo> users = service.queryAllUserInfo();
		tableEntity.setRows(users);
		tableEntity.setResults(users.size());
		return tableEntity;
	}

	@RequestMapping(path = "/user/deleteUsers", method = RequestMethod.GET)
	public void deleteUser(Integer ids[]) {
		for (Integer id : ids) {
			service.delUserInfoById(id);
		}
	}
	
	@RequestMapping(path = "/vehicel/queryData", method = RequestMethod.GET)
	public TableEntity<VehicleInfo> queryVehicle() {
		TableEntity<VehicleInfo> tableEntity = new TableEntity<VehicleInfo>();
		List<VehicleInfo> pageInfo = vehicleService.queryVehicleIfo("1");
		tableEntity.setResults(pageInfo.size());
		tableEntity.setRows(pageInfo);
		return tableEntity;
	}
	
	@RequestMapping(path = "/vehicel/deleteVehicle", method = RequestMethod.GET)
	public void deleteVehicle(String ids[]) {
		VehicleInfo vehicleInfo = null;
		for (String id : ids) {
			vehicleInfo = vehicleService.findByQrCode(id);
			vehicleInfo.setStatus("0");
			vehicleService.updateVehicleInfo(vehicleInfo);
		}
	}
	
	@RequestMapping(path = "/vehicel/removeVehicle", method = RequestMethod.GET)
	public void removeVehicle(String ids[]) {
		for (String id : ids) {
			vehicleService.deleteVehicleInfo(id);
		}
	}
	
	@RequestMapping(path = "/vehicel/restoreVehicle", method = RequestMethod.GET)
	public void restoreVehicle(String ids[]) {
		VehicleInfo vehicleInfo = null;
		for (String id : ids) {
			vehicleInfo = vehicleService.findByQrCode(id);
			vehicleInfo.setStatus("1");
			vehicleService.updateVehicleInfo(vehicleInfo);
		}
	}
	
	@RequestMapping(path = "/vehicel/queryRycData", method = RequestMethod.GET)
	public TableEntity<VehicleInfo> queryRycVehicle() {
		TableEntity<VehicleInfo> tableEntity = new TableEntity<VehicleInfo>();
		List<VehicleInfo> pageInfo = vehicleService.queryVehicleIfo("0");
		tableEntity.setResults(pageInfo.size());
		tableEntity.setRows(pageInfo);
		return tableEntity;
	}
}
