package com.ruixing.vehicle.manager.message.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ruixing.vehicle.manager.domain.MessageInfo;
import com.ruixing.vehicle.manager.message.dao.MessageRepository;
import com.ruixing.vehicle.manager.message.service.MessageServcie;
import com.ruixing.vehicle.manager.utils.Constants;

@Service("messageService")
public class MessageServiceImpl implements MessageServcie {

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void collectDataFromZJ(MessageInfo messageInfo) {
		messageInfo.setMessageState(true);
		messageInfo.setSendStatus(0);
		messageInfo.setRecordTime(new Date());
		messageRepository.save(messageInfo);
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

	@Override
	public Page<MessageInfo> findAll(Pageable pageable, MessageInfo messageInfo, boolean status) {
		Page<MessageInfo> eList = messageRepository.findAll(new Specification<MessageInfo>() {
			@Override
			public Predicate toPredicate(Root<MessageInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				Path<String> messageState = root.get("messageState");
				Path<String> recordTime = root.get("recordTime");
				Path<String> sendStatus = root.get("sendStatus");
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(messageInfo.getSendStatus())) {
					predicates.add(cb.equal(sendStatus.as(Integer.class), messageInfo.getSendStatus()));
				}
				if (!StringUtils.isEmpty(messageInfo.getStartDate())) {
					predicates.add(cb.greaterThan(recordTime.as(String.class), messageInfo.getStartDate()));
				}
				if (!StringUtils.isEmpty(messageInfo.getEndDate())) {
					predicates.add(cb.lessThanOrEqualTo(recordTime.as(String.class), messageInfo.getEndDate()));
				}
				predicates.add(cb.equal(messageState.as(Boolean.class), status));
				Predicate[] pre = new Predicate[predicates.size()];
				criteriaQuery.where(predicates.toArray(pre));
				return cb.and(predicates.toArray(pre));
			}
		}, pageable);
		return eList;
	}

}
