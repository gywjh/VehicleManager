package com.ruixing.vehicle.manager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

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

	@Column(name = "record_time")
	private Date recordTime;

	@Column(name = "message_statu")
	private Boolean messageState;

	@Column(name = "car_number", length = 20)
	private String carNumber;

	@Column(name = "place_name", length = 20)
	private String placeName;

	@Column(name = "good_type", length = 20)
	private String goodType;

	// 0为未发送，1为发送
	@Column(name = "send_status", length = 21)
	private int sendStatus = 0;

	@Column(name = "success_list", length = 200)
	private String successList;

	@Column(name = "fail_list", length = 200)
	private String failList;

	// 0为入关，1为出关
	@Column(name = "run_type", length = 2)
	private int runType = 0;

	// 查询
	@Transient
	private String startDate;
	@Transient
	private String endDate;

	public MessageInfo() {
		super();
	}

	public MessageInfo(Integer id, Date recordTime, Boolean messageState, String carNumber, String placeName,
			String goodType, int sendStatus, String successList, String failList, int runType) {
		super();
		this.id = id;
		this.recordTime = recordTime;
		this.messageState = messageState;
		this.carNumber = carNumber;
		this.placeName = placeName;
		this.goodType = goodType;
		this.sendStatus = sendStatus;
		this.successList = successList;
		this.failList = failList;
		this.runType = runType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getGoodType() {
		return goodType;
	}

	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSuccessList() {
		return successList;
	}

	public void setSuccessList(String successList) {
		this.successList = successList;
	}

	public String getFailList() {
		return failList;
	}

	public void setFailList(String failList) {
		this.failList = failList;
	}

	public int getRunType() {
		return runType;
	}

	public void setRunType(int runType) {
		this.runType = runType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
