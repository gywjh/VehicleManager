package com.ruixing.vehicle.manager.vehicle.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	public void updateVehicleInfo(VehicleInfo vehicleInfo);

	/**
	 * 扫一扫
	 * 
	 * @param qrPath
	 * @return
	 */
	public VehicleInfo findByQrImge(String qrPath);

	public VehicleInfo findByQrCode(String qrCode);

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

	public List<VehicleInfo> queryVehicleIfo(String status);

	public List<VehicleInfo> queryAll();
	
	/**
	 * 分页查询
	 */
	public 	Page<VehicleInfo> findAll(Pageable page,VehicleInfo vehicleInfo,String status);
}
