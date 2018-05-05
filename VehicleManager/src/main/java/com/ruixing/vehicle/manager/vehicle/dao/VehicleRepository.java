package com.ruixing.vehicle.manager.vehicle.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ruixing.vehicle.manager.domain.VehicleInfo;

public interface VehicleRepository extends JpaRepository<VehicleInfo, String> {

	@Query("select new VehicleInfo(c.qrCode as qrCode,c.chpColor as chpColor,c.chpNo as chpNo, c.driverOwner as driverOwner,c.driverName as driverName,c.supercargoCompany as supercargoCompany,c.freightCategory as freightCategory,c.freightCapacity as freightCapacity,c.noteDate as noteDate ) from VehicleInfo c where c.noteDate>=?1 and c.noteDate<?2 and c.status=?3 ")
	Page<VehicleInfo> findeViewByStatus(String startTime, String endTime, String status, Pageable pageable);

	/**
	 * 根据二维码查找车辆信息
	 * 
	 * @param qrCode
	 * @return
	 */
	VehicleInfo findByQrCode(String qrCode);

	@Transactional
	@Modifying
	@Query("delete from VehicleInfo where qrCode = ?1")
	void deleteByQrCode(String qrCode);
}
