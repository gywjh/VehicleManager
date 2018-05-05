package com.ruixing.vehicle.manager.convoy.entity;


public class ConvoyTime {
	
	private String id;
	
	/**
	 * 0 : 开始护送
	 * 1 : 结束护送
	 */
	private int convoyType = 0;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getConvoyType() {
		return convoyType;
	}

	public void setConvoyType(int convoyType) {
		this.convoyType = convoyType;
	}

}
