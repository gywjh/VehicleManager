package com.ruixing.vehicle.manager.message.service;

import java.util.List;

import com.ruixing.vehicle.manager.domain.MessageInfo;

public interface MessageServcie {

	/**
	 * 从中交平台获取数据
	 * @return
	 */
	public void collectDataFromZJ(MessageInfo messageInfo);
	
	/**
	 * 从运政平台获取数据
	 * @return
	 */
	public String collectDataFromYZ();

	/**
	 * 短信发送
	 * @return
	 */
	public boolean sendMessage();
	
	/**
	 * 短信查询
	 * @param obj
	 */
	public void queryMessage(Object obj);
	
	/**
	 * 永久删除已选择短信
	 * @param messageIdList
	 */
	public void deleteMessage(List<String> messageIdList);
	
	
	/**
	 * 更新假删除已选择短信
	 * @param messageIdList
	 */
	public void updateMessage(List<String> messageIdList);
	
	public void saveMessage(MessageInfo messageInfo);
	
}
