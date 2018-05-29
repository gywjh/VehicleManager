package com.ruixing.vehicle.manager.vehicle.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.ruixing.vehicle.manager.domain.VehicleInfo;
import com.ruixing.vehicle.manager.utils.Constants;
import com.ruixing.vehicle.manager.utils.ExcelUtil;
import com.ruixing.vehicle.manager.utils.QRCodeUitl;
import com.ruixing.vehicle.manager.utils.SheetResult;
import com.ruixing.vehicle.manager.vehicle.dao.VehicleRepository;
import com.ruixing.vehicle.manager.vehicle.service.IVehicleService;

@Service("vehicleService")
public class VehicleServiceImpl implements IVehicleService {

	Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

	private DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	public void updateVehicleInfo(VehicleInfo vehicleInfo) {
		vehicleInfo.setNoteDate(sf.format(new Date()));
		vehicleRepository.saveAndFlush(vehicleInfo);
	}

	@Override
	public VehicleInfo findByQrImge(String qrPath) {
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

		List<VehicleInfo> vehicleInfoList = vehicleRepository.findAll();
		List<List<String>> excleResult = new ArrayList<List<String>>(vehicleInfoList.size() + 1);
		excleResult.add(Arrays.asList(Constants.header));
		List<String> data = null;
		for (VehicleInfo vehicleInfo : vehicleInfoList) {
			data = new ArrayList<String>(Constants.header.length);
			data.add(vehicleInfo.getChpNo());
			data.add(vehicleInfo.getChpColor());
			data.add(vehicleInfo.getDriverOwner());
			data.add(vehicleInfo.getDriverName());
			data.add(vehicleInfo.getDriverSex());
			data.add(vehicleInfo.getDriverID());
			data.add(vehicleInfo.getDriverType());
			data.add(vehicleInfo.getDriverCompany());
			data.add(vehicleInfo.getSupercargoName());
			data.add(vehicleInfo.getSupercargoSex());
			data.add(vehicleInfo.getSupercargoID());
			data.add(vehicleInfo.getSupercargoType());
			data.add(vehicleInfo.getSupercargoCompany());
			data.add(vehicleInfo.getFreightCategory());
			data.add(String.valueOf(vehicleInfo.getFreightCapacity()));
			data.add(vehicleInfo.getNoteDate());
			excleResult.add(data);
		}
		SheetResult sheetResult = new SheetResult();
		sheetResult.setHeadRowNum(1);
		sheetResult.setDataList(excleResult);
		String fileName = System.currentTimeMillis() + ".xlsx";

		try {
			ExcelUtil.writeDataToExcel(ExcelUtil.createWorkBook(ExcelUtil.XLSX), "车辆信息",
					System.getProperty("user.dir") + Constants.EXCLE_TEMP_PATH + fileName, sheetResult);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return fileName;
	}

	@Override
	public List<VehicleInfo> queryVehicleIfo(String status) {
		List<VehicleInfo> pageInfo = vehicleRepository.findeViewByStatus(status);
		return pageInfo;
	}

	@Override
	public List<VehicleInfo> queryAll() {
		return vehicleRepository.findAll();
	}

	@Override
	public VehicleInfo findByQrCode(String qrCode) {
		return vehicleRepository.findByQrCode(qrCode);
	}

	@Override
	public Page<VehicleInfo> findAll(Pageable pageable, VehicleInfo vehicleInfo) {
		Page<VehicleInfo> eList = vehicleRepository.findAll(new Specification<VehicleInfo>() {
			@Override
			public Predicate toPredicate(Root<VehicleInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				Path<String> chpNo = root.get("chpNo");
				Path<String> chpColor = root.get("chpColor");
				Path<String> driverName = root.get("driverName");
				Path<String> noteDate = root.get("noteDate");
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(vehicleInfo.getChpNo())) {
					predicates.add(cb.like(chpNo.as(String.class), "%" + vehicleInfo.getChpNo() + "%"));
				}
				if (!StringUtils.isEmpty(vehicleInfo.getChpColor())) {
					predicates.add(cb.like(chpColor.as(String.class), "%" + vehicleInfo.getChpColor() + "%"));
				}
				if (!StringUtils.isEmpty(vehicleInfo.getDriverName())) {
					predicates.add(cb.like(driverName.as(String.class), "%" + vehicleInfo.getDriverName() + "%"));
				}
				if (!StringUtils.isEmpty(vehicleInfo.getStartDate())) {
					predicates.add(cb.greaterThan(noteDate.as(String.class), vehicleInfo.getStartDate()));
				}
				if (!StringUtils.isEmpty(vehicleInfo.getEndDate())) {
					predicates.add(cb.lessThanOrEqualTo(noteDate.as(String.class), vehicleInfo.getEndDate()));
				}
				Predicate[] pre = new Predicate[predicates.size()];
				criteriaQuery.where(predicates.toArray(pre));
				return cb.and(predicates.toArray(pre));
			}
		}, pageable);
		return eList;
	}

}
