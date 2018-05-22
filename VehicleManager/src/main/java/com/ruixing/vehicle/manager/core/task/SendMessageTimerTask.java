package com.ruixing.vehicle.manager.core.task;

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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruixing.vehicle.manager.domain.MessageInfo;
import com.ruixing.vehicle.manager.message.dao.MessageRepository;
import com.ruixing.vehicle.manager.user.dao.IUserInfoRepository;

@Component
@ConfigurationProperties
public class SendMessageTimerTask {
	private Logger logger = LoggerFactory.getLogger(SendMessageTimerTask.class);

	@Value("${enterMessageString}")
	private String enterMssage;
	@Value("${leaveMessageString}")
	private String leaveMessag;
	
	private Map<String, String> place = new HashMap<String,String>();

	@Value("${passWord}")
	private String passWord;

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private IUserInfoRepository userRepository;

	// 启动后延迟10秒执行第一次，之后间隔上次任务一分钟执行一次
	@Scheduled(initialDelay = 10 * 1000, fixedDelay = 60 * 1000)
	@Transactional
	public void process() {
		List<MessageInfo> list = messageRepository.findeBySendStatus();
		if (null != list && !list.isEmpty()) {
			String sendMessage = "";
			String phoneNumber = "";
			String carNumber = "";
			String goodType = "";
			int messageId = 0;
			List<String> phoneNumberList = null;
			HttpClient httpclient = null;
			PostMethod post = null;
			for (MessageInfo messageInfo : list) {
				carNumber = messageInfo.getCarNumber();
				messageId = messageInfo.getId();
				goodType = messageInfo.getGoodType();
				phoneNumberList = userRepository.queryPhoneNumber();
				if (null != phoneNumberList && !phoneNumberList.isEmpty()) {
					phoneNumber = phoneNumberList.toString().replace("[", "").replace("]", "");
				}
				if(0 == messageInfo.getRunType())//根据出入类型选择发送不同的模板
				{
					sendMessage = enterMssage.replace("#num", carNumber).replaceAll("#type", goodType);
				}
				else
				{
					sendMessage = leaveMessag.replace("#num", carNumber).replaceAll("#type", goodType);
				}
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
						Map<String, String> numList = getFailSuccessList(info, phoneNumber);
						messageRepository.save(getMessageInfo(messageInfo, numList));
						logger.error("id为" + messageId + "，车牌号为 " + carNumber + "的车辆短信发送成功,response是：" + info);
					} else {
						logger.error("id为" + messageId + "，车牌号为 " + carNumber + "的车辆短信发送失败,发送号码为：" + phoneNumber
								+ "message是：" + sendMessage + ",response是：" + info);
					}

				} catch (Exception e) {
					logger.error("id为" + messageId + "，车牌号为 " + carNumber + "的车辆短信发送异常,发送号码为：" + phoneNumber
							+ "message是：" + sendMessage + ",response是：" + info);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				} finally {
					httpclient = null;
					post = null;
				}
			}
		}
	}

	private MessageInfo getMessageInfo(MessageInfo messageInfo, Map<String, String> numberList) {
		messageInfo.setFailList(numberList.get("fail"));
		messageInfo.setSuccessList(numberList.get("success"));
		messageInfo.setSendStatus(1);
		return messageInfo;
	}
	
	private Map<String, String> getFailSuccessList(String respon, String phoneNumber) {

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

	public Map<String, String> getPlace() {
		return place;
	}

	public void setPlace(Map<String, String> place) {
		this.place = place;
	}

}
