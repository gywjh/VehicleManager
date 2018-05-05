package com.ruixing.vehicle.manager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "operation_tablet")
public class OperationInfo {

	private String id;
	
	private String userId;
	
	private String userName;
	
	private String operationPlace;
	
	private String handleResult;
	
	private String resultMark;
	
	private Date recordDate;
	
	private int recordStatus = 1;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "user_id", length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "user_name", length = 100)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "operation_place", length = 100)
	public String getOperationPlace() {
		return operationPlace;
	}

	public void setOperationPlace(String operationPlace) {
		this.operationPlace = operationPlace;
	}

	@Column(name = "handle_result" , length = 50)
	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	@Column(name = "result_mark" , length = 100)
	public String getResultMark() {
		return resultMark;
	}

	public void setResultMark(String resultMark) {
		this.resultMark = resultMark;
	}

	@Column(name = "record_date" , columnDefinition = "timestamp")
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name = "record_status" , length = 2)
	public int getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}
}
