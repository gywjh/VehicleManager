package com.ruixing.vehicle.manager.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruixing.vehicle.manager.domain.MessageInfo;
import com.ruixing.vehicle.manager.message.dao.MessageRepository;
import com.ruixing.vehicle.manager.utils.Constants;
import com.ruixing.vehicle.manager.vehicle.service.impl.VehicleServiceImpl;

@Controller
@EnableAutoConfiguration
@RequestMapping(path = "/message")
public class MessageController {

	private Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);
	@Autowired
	private MessageRepository messageRepository;

	private int totalPage = 0;
	private DateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@RequestMapping(path = "/query", method = RequestMethod.GET)
	public String queryMessage1(Model model) {
		logger.error("start query message info.");
		List<MessageInfo> pageInfo = messageRepository.findAllByMessageState(true);
		model.addAttribute("messages", pageInfo);
		return "message/list";
	}

	@RequestMapping(path = "/find", method = RequestMethod.GET)
	public String findMessageByTime(Model model, String startTime, String endTime, Integer currenPage) {
		currenPage = null == currenPage ? 0 : currenPage;
		Date startDate = null;
		Date endDate = null;
		try {
			if (!StringUtils.isEmpty(startTime)) {
				startTime = startTime + " 00:00:00";
				startDate = sf.parse(startTime);
			}
			if (!StringUtils.isEmpty(endTime)) {
				endTime = endTime + " 23:59:59";
				endDate = sf.parse(endTime);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		Pageable pageable = Constants.getPageable(currenPage, "recordTime");
		Page<MessageInfo> pageInfo = messageRepository.findByRecordTime(startDate, endDate, true, pageable);
		this.setTotalPage(pageInfo.getTotalPages() <= 0 ? 1 : pageInfo.getTotalPages());
		model.addAttribute("messages", pageInfo);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return "message/list";
	}

	@RequestMapping(path = "/delete", method = RequestMethod.GET)
	public String updateMessage(Integer selectperiod) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -selectperiod);
		Date date = cal.getTime();
		List<MessageInfo> messageList = messageRepository.findByRecordTimeAfter(date);
		for (MessageInfo message : messageList) {
			message.setMessageState(false);
			messageRepository.saveAndFlush(message);
		}
		return "redirect:/message/query";
	}

	@RequestMapping(path = "/deleteOne", method = RequestMethod.GET)
	public String updateOneMessage(int id) {
		MessageInfo message = messageRepository.findById(id);
		message.setMessageState(false);
//		message.setRecordTime(new Date());
		messageRepository.saveAndFlush(message);
		return "redirect:/message/query";
	}

	@RequestMapping(path = "/qyeryRyc", method = RequestMethod.GET)
	public String findRecyclebinMessageByTime(Model model, String startTime, String endTime, Integer currenPage) {
		logger.error("start query message info.");
		List<MessageInfo> pageInfo = messageRepository.findAllByMessageState(false);
		model.addAttribute("messages", pageInfo);
		return "message/Recyclebin";
	}

	/**
	 * 回收站恢復
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping(path = "/update", method = RequestMethod.GET)
	public String updateMessage(Model model, int id) {
		MessageInfo message = messageRepository.findById(id);
		message.setMessageState(true);
//		message.setRecordTime(new Date());
		messageRepository.saveAndFlush(message);
		return "redirect:/message/qyeryRyc";
	}

	@RequestMapping(path = "/remove", method = RequestMethod.GET)
	public String deleteMessage(Model model, int id) {

		messageRepository.delete(id);
		return "redirect:/message/qyeryRyc";
	}

	public void initServerData() {
		List<MessageInfo> messageList = new ArrayList<MessageInfo>();
		MessageInfo messageInfo = null;
		Calendar cal = Calendar.getInstance();

		Date date;
		for (int i = 1; i < 21; i++) {
			messageInfo = new MessageInfo();
			messageInfo.setId(i);
			messageInfo.setMessageContent("Test content " + i);
			messageInfo.setMessageState(true);
			cal.add(Calendar.DATE, -i);
			date = cal.getTime();
			messageInfo.setRecordTime(date);
			messageList.add(messageInfo);
		}
		messageRepository.save(messageList);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
