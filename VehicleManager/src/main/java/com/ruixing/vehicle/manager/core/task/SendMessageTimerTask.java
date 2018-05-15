package com.ruixing.vehicle.manager.core.task;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruixing.vehicle.manager.domain.MessageInfo;
import com.ruixing.vehicle.manager.domain.VehicleInfo;
import com.ruixing.vehicle.manager.message.dao.MessageRepository;
import com.ruixing.vehicle.manager.vehicle.dao.VehicleRepository;


@Component
public class SendMessageTimerTask {
	private Logger logger = LoggerFactory.getLogger(SendMessageTimerTask.class);

	@Value("${messageString}")
	private String message;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private MessageRepository messageRepository;
	
	String sn="";//序列号
	String pwd="akjr8010";//密码
	Client client = null;
	
	public SendMessageTimerTask()
	{
		try {
			client=new Client(sn,pwd);
		} catch (UnsupportedEncodingException e) {
			logger.error("create message client error :" + e);
		}
	}

	// 启动后延迟10秒执行第一次，之后间隔上次任务一分钟执行一次
//	@Scheduled(initialDelay = 10 * 1000, fixedDelay = 60 * 1000)
	@Transactional
	public void process() {
		List<VehicleInfo> list = vehicleRepository.findeByMessageStatus();
		if (null != list && !list.isEmpty()) {
			String sendMessage = "";
			String phoneNumber = "15829556948";
			String carNumber = "";
			String vehicleId = "";
			for (VehicleInfo vehicleInfo : list) {
				carNumber = vehicleInfo.getChpNo();
				vehicleId = vehicleInfo.getQrCode();
				sendMessage = message.replace("#num", carNumber).replaceAll("#type",
						vehicleInfo.getFreightCategory());
				String result_mt = client.mt(phoneNumber, sendMessage, "", "", "");
				if(result_mt.startsWith("-")||result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
				{
					logger.error("id为" + vehicleId +"，车牌号为 " + carNumber +"的车辆短信发送失败，错误码为：" + result_mt);
					return;
				}
				//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
				else
				{
					try
					{
						messageRepository.save(getMessageInfo(message));
						vehicleRepository.updateMessageStatus(vehicleId);
					}
					catch (Exception e) {
						logger.error("操作数据库时错误：" + e);
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					}
					logger.info("id为" + vehicleId +"，车牌号为 " + carNumber +"的车辆短信发送成功");
				}
			}
		}
	}
	
	private MessageInfo getMessageInfo(String MessageStr)
	{
		MessageInfo messInfo = new MessageInfo();
		messInfo.setMessageContent(MessageStr);
		messInfo.setMessageState(true);
		messInfo.setRecordTime(new Date());
		return messInfo;
	}
}
