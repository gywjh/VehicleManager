package com.ruixing.vehicle.manager.convoy.entity;

import java.util.Date;

public class SaveConvoyRequest {

	private String vehicleId;
	
	private String plateColor;
	
	private String plateNumber;
	
	private String drivingOwner;
	
	private String driverName;
	
	private String driverSex;
	
	private String driverIcNumb;
	
	private String driverType;
	
	private String driverCompany;
	
	private String escortName;
	
	private String escortSex;
	
	private String escortIcNumber;
	
	private String escortDriverType;
	
	private String escortCompany;
	
	private String goodsType;
	
	private String goodsWeight;
	
	private Date recordDate;
	
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getDrivingOwner() {
		return drivingOwner;
	}

	public void setDrivingOwner(String drivingOwner) {
		this.drivingOwner = drivingOwner;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverSex() {
		return driverSex;
	}

	public void setDriverSex(String driverSex) {
		this.driverSex = driverSex;
	}

	public String getDriverIcNumb() {
		return driverIcNumb;
	}

	public void setDriverIcNumb(String driverIcNumb) {
		this.driverIcNumb = driverIcNumb;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public String getDriverCompany() {
		return driverCompany;
	}

	public void setDriverCompany(String driverCompany) {
		this.driverCompany = driverCompany;
	}

	public String getEscortName() {
		return escortName;
	}

	public void setEscortName(String escortName) {
		this.escortName = escortName;
	}

	public String getEscortSex() {
		return escortSex;
	}

	public void setEscortSex(String escortSex) {
		this.escortSex = escortSex;
	}

	public String getEscortIcNumber() {
		return escortIcNumber;
	}

	public void setEscortIcNumber(String escortIcNumber) {
		this.escortIcNumber = escortIcNumber;
	}

	public String getEscortDriverType() {
		return escortDriverType;
	}

	public void setEscortDriverType(String escortDriverType) {
		this.escortDriverType = escortDriverType;
	}

	public String getEscortCompany() {
		return escortCompany;
	}

	public void setEscortCompany(String escortCompany) {
		this.escortCompany = escortCompany;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
}
