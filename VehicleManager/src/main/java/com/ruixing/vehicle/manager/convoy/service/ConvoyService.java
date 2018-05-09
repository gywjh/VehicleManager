package com.ruixing.vehicle.manager.convoy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruixing.vehicle.manager.convoy.dao.ConvoyRepository;
import com.ruixing.vehicle.manager.convoy.entity.SaveConvoyRequest;
import com.ruixing.vehicle.manager.domain.ConvoyEndDate;
import com.ruixing.vehicle.manager.domain.ConvoyInfo;
import com.ruixing.vehicle.manager.domain.ConvoyStartDate;

@Service
public class ConvoyService {

	@Autowired
	private ConvoyRepository convoyRepository;

	public void save(SaveConvoyRequest request) {
		if (null != request) {
			ConvoyInfo dbConvoyInfo = new ConvoyInfo();
			dbConvoyInfo.setDriverCompany(request.getDriverCompany());
			dbConvoyInfo.setDriverIcNumb(request.getDriverIcNumb());
			dbConvoyInfo.setDriverName(request.getDriverName());
			dbConvoyInfo.setDriverSex(request.getDriverSex());
			dbConvoyInfo.setDriverType(request.getDriverType());
			dbConvoyInfo.setDrivingOwner(request.getDrivingOwner());
			dbConvoyInfo.setEscortCompany(request.getEscortCompany());
			dbConvoyInfo.setEscortDriverType(request.getEscortDriverType());
			dbConvoyInfo.setEscortIcNumber(request.getEscortIcNumber());
			dbConvoyInfo.setEscortName(request.getEscortName());
			dbConvoyInfo.setEscortSex(request.getEscortSex());
			dbConvoyInfo.setGoodsType(request.getGoodsType());
			dbConvoyInfo.setGoodsWeight(request.getGoodsWeight());
			dbConvoyInfo.setPlateColor(request.getPlateColor());
			dbConvoyInfo.setPlateNumber(request.getPlateNumber());
			dbConvoyInfo.setRecordDate(request.getRecordDate());
			dbConvoyInfo.setVehicleId(request.getVehicleId());
			convoyRepository.save(dbConvoyInfo);
		}
	}

	public void delete(String id) {
		convoyRepository.delete(id);
	}

	public void updateConvoy(String id, int type) {
		if (0 == type) {
			ConvoyStartDate startConvoy = new ConvoyStartDate();
			startConvoy.setId(id);
			startConvoy.setStartDate(new Date());
			convoyRepository.save(startConvoy);
		} else {
			ConvoyEndDate endConvoy = new ConvoyEndDate();
			endConvoy.setEndDate(new Date());
			endConvoy.setId(id);
			convoyRepository.save(endConvoy);
		}
	}

	public List<ConvoyInfo> findAllList() {
		return convoyRepository.findAll();
	}
}
