package com.ruixing.vehicle.manager.core.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
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
import com.ruixing.vehicle.manager.user.dao.IUserInfoRepository;
import com.ruixing.vehicle.manager.vehicle.dao.VehicleRepository;

@Component
public class SendMessageTimerTask {
	private Logger logger = LoggerFactory.getLogger(SendMessageTimerTask.class);

	@Value("${messageString}")
	private String message;

	@Value("${passWord}")
	private String passWord;

	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private IUserInfoRepository userRepository;

	// 启动后延迟10秒执行第一次，之后间隔上次任务一分钟执行一次
	@Scheduled(initialDelay = 10 * 1000, fixedDelay = 60 * 1000)
	@Transactional
	public void process() {
		List<VehicleInfo> list = vehicleRepository.findeByMessageStatus();
		if (null != list && !list.isEmpty()) {
			String sendMessage = "";
			List<String> phoneNumberList = userRepository.queryPhoneNumber();
			String phoneNumber = "";
			if (null != phoneNumberList && !phoneNumberList.isEmpty()) {
				phoneNumber = phoneNumberList.toString().replace("[", "").replace("]", "");
			}
			String carNumber = "";
			String vehicleId = "";
			HttpClient httpclient = null;
			PostMethod post = null;
			for (VehicleInfo vehicleInfo : list) {
				carNumber = vehicleInfo.getChpNo();
				vehicleId = vehicleInfo.getQrCode();
				sendMessage = message.replace("#num", carNumber).replaceAll("#type", vehicleInfo.getFreightCategory());
				String info = null;
				try {
					httpclient = new HttpClient();
					post = new PostMethod("https://api.ums86.com:9600/sms/Api/Send.do");//
					post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "gbk");
					post.addParameter("SpCode", "247564");
					post.addParameter("LoginName", "jr_kj");
					post.addParameter("Password", passWord);
					post.addParameter("MessageContent", sendMessage);
					post.addParameter("UserNumber", phoneNumber);
					post.addParameter("SerialNumber", "");
					post.addParameter("ScheduleTime", "");
					post.addParameter("f", "1");
					httpclient.executeMethod(post);
					info = new String(post.getResponseBody(), "gbk");
					if (info.contains("result=0&description=发送短信成功")) {
						Map<String, String> numList = getFailList(info, phoneNumber);
						messageRepository.save(getMessageInfo(sendMessage, numList));
						vehicleRepository.updateMessageStatus(vehicleId);
						logger.error("id为" + vehicleId + "，车牌号为 " + carNumber + "的车辆短信发送成功,response是：" + info);
					} else {
						logger.error("id为" + vehicleId + "，车牌号为 " + carNumber + "的车辆短信发送失败,发送号码为：" + phoneNumber
								+ "message是：" + sendMessage + ",response是：" + info);
					}

				} catch (Exception e) {
					logger.error("id为" + vehicleId + "，车牌号为 " + carNumber + "的车辆短信发送异常,发送号码为：" + phoneNumber
							+ "message是：" + sendMessage + ",response是：" + info);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				} finally {
					httpclient = null;
					post = null;
				}
			}
		}
	}

	private MessageInfo getMessageInfo(String MessageStr, Map<String, String> numberList) {
		MessageInfo messInfo = new MessageInfo();
		messInfo.setMessageContent(MessageStr);
		messInfo.setFailList(numberList.get("fail"));
		messInfo.setSuccessList(numberList.get("success"));
		messInfo.setMessageState(true);
		messInfo.setRecordTime(new Date());
		return messInfo;
	}
	
	private Map<String, String> getFailList(String respon, String phoneNumber) {

		Map<String, String> list = new HashMap<String, String>();
		String failStr = respon.substring(respon.indexOf("faillist=") + 9, respon.indexOf("&task_id"));
		if (StringUtils.isNotBlank(failStr)) {
			failStr = failStr.substring(0, failStr.length() - 1);
			String[] failArr = failStr.split(",");
			for (String string : failArr) {
				phoneNumber = phoneNumber.replace("," + string + "," , "");
				phoneNumber = phoneNumber.replace("," + string , "");
				phoneNumber = phoneNumber.replace(string + "," , "");
				phoneNumber = phoneNumber.replace(string , "");
			}
			list.put("fail", failStr);
			list.put("success", phoneNumber);
		} else {
			list.put("fail", "");
			list.put("success", phoneNumber);
		}

		return list;
	}

}
