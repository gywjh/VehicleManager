package com.ruixing.vehicle.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.ruixing.vehicle.manager.utils.Constants;
import com.ruixing.vehicle.manager.vehicle.service.IVehicleService;

@RestController
public class DataController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IUserinfoService service;

	@Resource
	private IVehicleService vehicleService;
	
	@Resource
	private MessageServcie messageServcie;
	
	@Autowired
	private MessageRepository messageRepository;

	@RequestMapping(path = "/user/queryUser", method = RequestMethod.GET)
	public TableEntity<UserInfo> queryUser(int start, int limit, int pageIndex, UserInfo userInfo) {

		Pageable page = Constants.getPageable(pageIndex, "id");
		TableEntity<UserInfo> tableEntity = new TableEntity<UserInfo>();
		List<UserInfo> userInfos = service.findAll(page, userInfo).getContent();
		tableEntity.setResults(userInfos.size());
		tableEntity.setRows(userInfos);

		return tableEntity;
	}

	@RequestMapping(path = "/user/deleteUsers", method = RequestMethod.GET)
	public void deleteUser(Integer ids[]) {
		for (Integer id : ids) {
			service.delUserInfoById(id);
		}
	}

	@RequestMapping(path = "/vehicel/queryData", method = RequestMethod.GET)
	public TableEntity<VehicleInfo> queryVehicle(int start, int limit, int pageIndex, VehicleInfo vehicleInfo) {
		logger.info("query vehicle info.");
		Pageable page = Constants.getPageable(pageIndex, "noteDate");
		TableEntity<VehicleInfo> tableEntity = new TableEntity<VehicleInfo>();
		List<VehicleInfo> pageInfo = vehicleService.findAll(page, vehicleInfo, "1").getContent();
		tableEntity.setResults(pageInfo.size());
		tableEntity.setRows(pageInfo);
		return tableEntity;
	}

	@RequestMapping(path = "/vehicel/queryRycData", method = RequestMethod.GET)
	public TableEntity<VehicleInfo> queryRycData(int start, int limit, int pageIndex, VehicleInfo vehicleInfo) {
		logger.info("query vehicle  ryc info.");
		Pageable page = Constants.getPageable(pageIndex, "noteDate");
		TableEntity<VehicleInfo> tableEntity = new TableEntity<VehicleInfo>();
		List<VehicleInfo> pageInfo = vehicleService.findAll(page, vehicleInfo, "0").getContent();
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

	@RequestMapping(path = "/message/queryMessageData", method = RequestMethod.GET)
	public TableEntity<MessageInfo> queryMessageData(int start, int limit, int pageIndex, MessageInfo messageInfo) {
		TableEntity<MessageInfo> tableEntity = new TableEntity<MessageInfo>();
		Pageable page = Constants.getPageable(pageIndex, "recordTime");
		List<MessageInfo> pageInfo = messageServcie.findAll(page, messageInfo, true).getContent();
		tableEntity.setResults(pageInfo.size());
		tableEntity.setRows(pageInfo);
		return tableEntity;
		
	}

	@RequestMapping(path = "/message/queryRycMessage", method = RequestMethod.GET)
	public TableEntity<MessageInfo> queryRycMessage(int start, int limit, int pageIndex, MessageInfo messageInfo) {
		TableEntity<MessageInfo> tableEntity = new TableEntity<MessageInfo>();
		Pageable page = Constants.getPageable(pageIndex, "recordTime");
		List<MessageInfo> pageInfo = messageServcie.findAll(page, messageInfo, false).getContent();
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
