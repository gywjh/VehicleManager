package com.ruixing.vehicle.manager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 短信实体类
 * 
 * @author polly_ally
 */
@Entity
public class MessageInfo {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "message_info")
	private String messageContent;

	@Column(name = "record_time")
	private Date recordTime;

	@Column(name = "message_statu")
	private Boolean messageState;

	public MessageInfo() {
		super();
	}

	public MessageInfo(Integer id, String messageContent, Date recordTime, Boolean messageState) {
		super();
		this.id = id;
		this.messageContent = messageContent;
		this.recordTime = recordTime;
		this.messageState = messageState;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Boolean getMessageState() {
		return messageState;
	}

	public void setMessageState(Boolean messageState) {
		this.messageState = messageState;
	}

}
