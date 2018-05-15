package com.ruixing.vehicle.manager.vehicle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ruixing.vehicle.manager.domain.VehicleInfo;

public interface VehicleRepository extends JpaRepository<VehicleInfo, String> {

	@Query("select new VehicleInfo(c.qrCode as qrCode,c.chpColor as chpColor,c.chpNo as chpNo, c.driverOwner as driverOwner,c.driverName as driverName,c.supercargoCompany as supercargoCompany,c.freightCategory as freightCategory,c.freightCapacity as freightCapacity,c.noteDate as noteDate ) from VehicleInfo c where c.status=?1 ")
	List<VehicleInfo> findeViewByStatus(String status);
	
	@Query("select new VehicleInfo(c.qrCode as prId, c.chpNo as chpNo, c.freightCategory as freightCategory) from VehicleInfo c where messageStatus = 0 ")
	List<VehicleInfo> findeByMessageStatus();

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
	
	/**
	 * 更新车辆短信发送信息
	 */
	@Query(value = "update vehicle_info set message_status = 1 where qr_code = ?1", nativeQuery = true)
    @Modifying
    @Transactional
	void updateMessageStatus(String id);
}
