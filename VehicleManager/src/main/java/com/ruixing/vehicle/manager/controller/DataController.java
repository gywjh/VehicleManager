package com.ruixing.vehicle.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruixing.vehicle.manager.domain.MessageInfo;
import com.ruixing.vehicle.manager.domain.TableEntity;
import com.ruixing.vehicle.manager.domain.UserInfo;
import com.ruixing.vehicle.manager.domain.VehicleInfo;
import com.ruixing.vehicle.manager.message.dao.MessageRepository;
import com.ruixing.vehicle.manager.message.service.MessageServcie;
import com.ruixing.vehicle.manager.user.service.IUserinfoService;
import com.ruixing.vehicle.manager.vehicle.service.IVehicleService;

@RestController
public class DataController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IUserinfoService service;

	@Resource
	private IVehicleService vehicleService;

	@Autowired
	private MessageRepository messageRepository;

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

	@RequestMapping(path = "/message/queryMessageData", method = RequestMethod.GET)
	public TableEntity<MessageInfo> queryMessageData() {
		TableEntity<MessageInfo> tableEntity = new TableEntity<MessageInfo>();
		List<MessageInfo> pageInfo = messageRepository.findAllByMessageState(true);
		tableEntity.setResults(pageInfo.size());
		tableEntity.setRows(pageInfo);
		return tableEntity;
	}

	@RequestMapping(path = "/message/queryRycMessage", method = RequestMethod.GET)
	public TableEntity<MessageInfo> queryRycMessage() {
		TableEntity<MessageInfo> tableEntity = new TableEntity<MessageInfo>();
		List<MessageInfo> pageInfo = messageRepository.findAllByMessageState(false);
		tableEntity.setResults(pageInfo.size());
		tableEntity.setRows(pageInfo);
		return tableEntity;
	}
	
	
	@RequestMapping(path = "/message/deleteMessage", method = RequestMethod.GET)
	public void deleteMessage(Integer ids[]) {
		MessageInfo messageInfo = null;
		for (Integer id : ids) {
			messageInfo = messageRepository.findById(id);
			messageInfo.setMessageState(false);
			messageRepository.saveAndFlush(messageInfo);
		}
	}

	@RequestMapping(path = "/message/removeMessage", method = RequestMethod.GET)
	public void removeMessage(Integer ids[]) {
		for (Integer id : ids) {
			messageRepository.delete(id);
		}
	}

	@RequestMapping(path = "/message/restoreMessage", method = RequestMethod.GET)
	public void restoreMessage(Integer ids[]) {
		MessageInfo messageInfo = null;
		for (Integer id : ids) {
			messageInfo = messageRepository.findById(id);
			messageInfo.setMessageState(true);
			messageRepository.saveAndFlush(messageInfo);
		}
	}
}
