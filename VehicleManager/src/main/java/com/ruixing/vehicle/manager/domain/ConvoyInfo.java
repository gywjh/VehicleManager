package com.ruixing.vehicle.manager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "convoy_tablet")
public class ConvoyInfo {
	
	private String id;

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
	
	private Date startDate;
	
	private Date endDate;
	
	private int convoyStatus = 0;

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

	@Column(name = "vehicle_id", length = 50)
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Column(name = "plate_color", length = 10)
	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	@Column(name = "plate_number", length = 20)
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	@Column(name = "driving_owner", length = 50)
	public String getDrivingOwner() {
		return drivingOwner;
	}

	public void setDrivingOwner(String drivingOwner) {
		this.drivingOwner = drivingOwner;
	}

	@Column(name = "driver_name", length = 50)
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	@Column(name = "driver_sex", length = 5)
	public String getDriverSex() {
		return driverSex;
	}

	public void setDriverSex(String driverSex) {
		this.driverSex = driverSex;
	}

	@Column(name = "driver_ic_number", length = 30)
	public String getDriverIcNumb() {
		return driverIcNumb;
	}

	public void setDriverIcNumb(String driverIcNumb) {
		this.driverIcNumb = driverIcNumb;
	}

	@Column(name = "driver_type", length = 20)
	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	@Column(name = "driver_company", length = 50)
	public String getDriverCompany() {
		return driverCompany;
	}

	public void setDriverCompany(String driverCompany) {
		this.driverCompany = driverCompany;
	}

	@Column(name = "escoort_name", length = 50)
	public String getEscortName() {
		return escortName;
	}

	public void setEscortName(String escortName) {
		this.escortName = escortName;
	}

	@Column(name = "escort_sex", length = 5)
	public String getEscortSex() {
		return escortSex;
	}

	public void setEscortSex(String escortSex) {
		this.escortSex = escortSex;
	}

	@Column(name = "escort_ic_number", length = 30)
	public String getEscortIcNumber() {
		return escortIcNumber;
	}

	public void setEscortIcNumber(String escortIcNumber) {
		this.escortIcNumber = escortIcNumber;
	}

	@Column(name = "escort_driver_type", length = 30)
	public String getEscortDriverType() {
		return escortDriverType;
	}

	public void setEscortDriverType(String escortDriverType) {
		this.escortDriverType = escortDriverType;
	}

	@Column(name = "escort_company", length = 30)
	public String getEscortCompany() {
		return escortCompany;
	}

	public void setEscortCompany(String escortCompany) {
		this.escortCompany = escortCompany;
	}

	@Column(name = "goods_type", length = 30)
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	@Column(name = "goods_weight", length = 10)
	public String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	@Column(name = "record_date", columnDefinition = "timestamp")
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name = "start_date", columnDefinition = "timestamp")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", columnDefinition = "timestamp")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "convoy_status", length = 2)
	public int getConvoyStatus() {
		return convoyStatus;
	}

	public void setConvoyStatus(int convoyStatus) {
		this.convoyStatus = convoyStatus;
	}
}
