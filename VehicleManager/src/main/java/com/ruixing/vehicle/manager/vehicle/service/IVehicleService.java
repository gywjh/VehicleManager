package com.ruixing.vehicle.manager.vehicle.service;

import org.springframework.data.domain.Page;

import com.ruixing.vehicle.manager.domain.VehicleInfo;

public interface IVehicleService {

	/**
	 * 注册车辆信息
	 * 
	 * @return
	 */
	public boolean addVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 修改车辆信息
	 * 
	 * @return
	 */
	public boolean updateVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 扫一扫
	 * 
	 * @param qrPath
	 * @return
	 */
	public VehicleInfo findByQrCode(String qrPath);

	public VehicleInfo findByQrImg(String qrCode);
	
	/**
	 * 删除车辆信息
	 * 
	 * @return
	 */
	public void deleteVehicleInfo(String qrCode);

	/**
	 * 导出查询的车辆信息
	 * 
	 * @return
	 */
	public String exprotVehicleInfo();

	public Page<VehicleInfo> queryVehicleIfo(String startTime, String endTime);
	
	public Page<VehicleInfo> queryAll();
}
