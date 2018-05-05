package com.ruixing.vehicle.manager.message.service;

import java.util.List;

public interface MessageServcie {

	/**
	 * 从中交平台获取数据
	 * @return
	 */
	public String collectDataFromZJ();
	
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
}
