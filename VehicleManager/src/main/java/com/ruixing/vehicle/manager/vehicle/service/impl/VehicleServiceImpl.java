package com.ruixing.vehicle.manager.vehicle.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ruixing.vehicle.manager.domain.VehicleInfo;
import com.ruixing.vehicle.manager.utils.Constants;
import com.ruixing.vehicle.manager.utils.QRCodeUitl;
import com.ruixing.vehicle.manager.vehicle.dao.VehicleRepository;
import com.ruixing.vehicle.manager.vehicle.service.IVehicleService;

@Service("vehicleService")
public class VehicleServiceImpl implements IVehicleService {

	Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

	private DateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public boolean addVehicleInfo(VehicleInfo vehicleInfo) {

		logger.error("Start register vehicleInfo ...");
		String qrCode = QRCodeUitl.generateNumCode(Constants.QR_LENGTH);
		String fileName = String.valueOf(qrCode + System.currentTimeMillis()) + ".png";
		try {
			QRCodeUitl.generateQRCode(qrCode, Constants.QR_WIDTH, Constants.QR_HIGTH, Constants.QR_IMAGE_TYPE,
					fileName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		vehicleInfo.setQrCode(qrCode);
		vehicleInfo.setQrCodePath("/images/upload/" + File.separator + fileName);
		vehicleInfo.setNoteDate(sf.format(new Date()));
		vehicleRepository.save(vehicleInfo);
		return false;
	}

	@Override
	public boolean updateVehicleInfo(VehicleInfo vehicleInfo) {
		vehicleRepository.saveAndFlush(vehicleInfo);
		return false;
	}

	@Override
	public VehicleInfo findByQrCode(String qrPath) {
		String qrCode = QRCodeUitl.parseQRCode(qrPath);
		return vehicleRepository.findByQrCode(qrCode);
	}

	@Override
	public void deleteVehicleInfo(String qrCode) {
		VehicleInfo vhicleInfo = vehicleRepository.findByQrCode(qrCode);
		String qrPath = "";
		try {
			qrPath = ResourceUtils.getURL("classpath:").getPath() + File.separator + "static" + File.separator
					+ vhicleInfo.getQrCodePath();
		} catch (FileNotFoundException e) {
			logger.error("get file path error!");
		}
		File file = new File(qrPath);
		if (file.exists()) {
			file.delete();
		}
		vehicleRepository.delete(vhicleInfo);
	}

	@Override
	public String exprotVehicleInfo() {

		return null;
	}

	@Override
	public Page<VehicleInfo> queryVehicleIfo(String startTime, String endTime) {
		Pageable pageable = Constants.getPageable(0, "noteDate");
		Page<VehicleInfo> pageInfo = vehicleRepository.findeViewByStatus(startTime, endTime, "1", pageable);
		return pageInfo;
	}

	@Override
	public Page<VehicleInfo> queryAll() {
		Pageable pageable = Constants.getPageable(0, "noteDate");
		return vehicleRepository.findAll(pageable);
	}

	@Override
	public VehicleInfo findByQrImg(String qrCode) {
		return vehicleRepository.findByQrCode(qrCode);
	}

}
