package com.ruixing.vehicle.manager.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ruixing.vehicle.manager.domain.MessageInfo;
import com.ruixing.vehicle.manager.message.dao.MessageRepository;
import com.ruixing.vehicle.manager.message.service.MessageServcie;
import com.ruixing.vehicle.manager.utils.Constants;

@Service("messageService")
public class MessageServiceImpl implements MessageServcie {

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public String collectDataFromZJ() {
		return null;
	}

	@Override
	public String collectDataFromYZ() {
		return null;
	}

	@Override
	public boolean sendMessage() {
		// 获取就近民警信息
		// 封装短信内容
		// 发送短信
		return false;
	}

	@Override
	public void queryMessage(Object obj) {
		int currentPage = 1;
		Pageable pageable = Constants.getPageable(currentPage,"recordTime");
		messageRepository.findByRecordTime(null, null, true, pageable);
	}

	@Override
	public void deleteMessage(List<String> messageIdList) {
		List<MessageInfo> messageList = null;
		messageRepository.delete(messageList);
	}

	@Override
	public void updateMessage(List<String> messageIdList) {
		MessageInfo messageInfo = null;
		messageRepository.saveAndFlush(messageInfo);
	}

	@Override
	public void saveMessage(MessageInfo messageInfo) {
		messageRepository.save(messageInfo);
		
	}

}
