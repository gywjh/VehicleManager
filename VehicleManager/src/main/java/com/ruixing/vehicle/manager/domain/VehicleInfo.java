package com.ruixing.vehicle.manager.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VehicleInfo {

	/**
	 * 二维码
	 */
	@Id
	private String qrCode;

	// 车牌颜色 蓝、黄、绿、白、黑
	private String chpColor;
	// 车牌号码
	private String chpNo;
	// 行驶证所属人
	private String driverOwner;
	// 驾驶员姓名
	private String driverName;
	// 驾驶员性别
	private String driverSex;
	// 驾驶员身份证
	private String driverID;
	// 驾驶员驾照类型
	private String driverType;
	// 驾驶员所属公司
	private String driverCompany;
	// 押运员姓名
	private String supercargoName;
	// 押运员性别
	private String supercargoSex;
	// 押运员身份证
	private String supercargoID;
	// 押运员驾照类型
	private String supercargoType;
	// 押运员所属公司
	private String supercargoCompany;
	// 货物品类
	private String freightCategory;
	// 载重量
	private float freightCapacity;
	// 记录日期
	private String noteDate;
	// 数据状态
	private String status = "1";
	// 二维码图片路径
	private String qrCodePath;

	public VehicleInfo() {
	}

	public VehicleInfo(String qrCode, String chpColor, String chpNo, String driverOwner, String driverName,
			String supercargoCompany, String freightCategory, float freightCapacity, String noteDate) {
		this.qrCode = qrCode;
		this.chpColor = chpColor;
		this.chpNo = chpNo;
		this.driverOwner = driverOwner;
		this.driverName = driverName;
		this.supercargoCompany = supercargoCompany;
		this.freightCategory = freightCategory;
		this.freightCapacity = freightCapacity;
		this.noteDate = noteDate;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getChpColor() {
		return chpColor;
	}

	public void setChpColor(String chpColor) {
		this.chpColor = chpColor;
	}

	public String getChpNo() {
		return chpNo;
	}

	public void setChpNo(String chpNo) {
		this.chpNo = chpNo;
	}

	public String getDriverOwner() {
		return driverOwner;
	}

	public void setDriverOwner(String driverOwner) {
		this.driverOwner = driverOwner;
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

	public String getDriverID() {
		return driverID;
	}

	public void setDriverID(String driverID) {
		this.driverID = driverID;
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

	public String getSupercargoName() {
		return supercargoName;
	}

	public void setSupercargoName(String supercargoName) {
		this.supercargoName = supercargoName;
	}

	public String getSupercargoSex() {
		return supercargoSex;
	}

	public void setSupercargoSex(String supercargoSex) {
		this.supercargoSex = supercargoSex;
	}

	public String getSupercargoID() {
		return supercargoID;
	}

	public void setSupercargoID(String supercargoID) {
		this.supercargoID = supercargoID;
	}

	public String getSupercargoType() {
		return supercargoType;
	}

	public void setSupercargoType(String supercargoType) {
		this.supercargoType = supercargoType;
	}

	public String getSupercargoCompany() {
		return supercargoCompany;
	}

	public void setSupercargoCompany(String supercargoCompany) {
		this.supercargoCompany = supercargoCompany;
	}

	public String getFreightCategory() {
		return freightCategory;
	}

	public void setFreightCategory(String freightCategory) {
		this.freightCategory = freightCategory;
	}

	public float getFreightCapacity() {
		return freightCapacity;
	}

	public void setFreightCapacity(float freightCapacity) {
		this.freightCapacity = freightCapacity;
	}

	public String getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(String noteDate) {
		this.noteDate = noteDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}

}
