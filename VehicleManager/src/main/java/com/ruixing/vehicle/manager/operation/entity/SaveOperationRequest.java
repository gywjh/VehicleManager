package com.ruixing.vehicle.manager.operation.entity;

public class SaveOperationRequest {
	
	private String userId;
	
	private String userName;
	
	private String operationPlace;
	
	private String handleResult;
	
	private String resultMark;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperationPlace() {
		return operationPlace;
	}

	public void setOperationPlace(String operationPlace) {
		this.operationPlace = operationPlace;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getResultMark() {
		return resultMark;
	}

	public void setResultMark(String resultMark) {
		this.resultMark = resultMark;
	}
	
}
