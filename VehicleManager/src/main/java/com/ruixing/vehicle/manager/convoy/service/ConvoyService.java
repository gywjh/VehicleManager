package com.ruixing.vehicle.manager.convoy.service;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ruixing.vehicle.manager.convoy.dao.ConvoyRepository;
import com.ruixing.vehicle.manager.convoy.entity.ConvoyFindRequest;
import com.ruixing.vehicle.manager.convoy.entity.SaveConvoyRequest;
import com.ruixing.vehicle.manager.domain.ConvoyEndDate;
import com.ruixing.vehicle.manager.domain.ConvoyInfo;
import com.ruixing.vehicle.manager.domain.ConvoyStartDate;
import com.ruixing.vehicle.manager.utils.Constants;

@Service
public class ConvoyService {

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

	public Page<ConvoyInfo> findAllList(ConvoyFindRequest findRequest) {
		Pageable pageable = Constants.getPageable(findRequest.getCurrentPage() + 1, "recodeTime");
		Specification<ConvoyInfo> spec = getWhereClause(findRequest);
		return convoyRepository.findAll(spec, pageable);
	}

	private Specification<ConvoyInfo> getWhereClause(ConvoyFindRequest findRequest) {
		return new Specification<ConvoyInfo>() {
			@Override
			public Predicate toPredicate(Root<ConvoyInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (null != findRequest) {
					String name = findRequest.getCarNumber();
					if (StringUtils.isNotBlank(name)) {
						predicate.getExpressions()
								.add(cb.like(root.<String>get("plateNumber"), StringUtils.trim(name)));
					}
					String driverName = findRequest.getDriverName();
					if (StringUtils.isNotBlank(driverName)) {
						predicate.getExpressions()
								.add(cb.like(root.<String>get("driverName"), StringUtils.trim(driverName)));
					}
					Date startDate = findRequest.getStartDate();
					if (null != startDate) {
						predicate.getExpressions()
								.add(cb.greaterThanOrEqualTo(root.<Date>get("recordDate"), startDate));
					}
					Date endDate = findRequest.getEndDate();
					if (null != endDate) {
						predicate.getExpressions().add(cb.lessThanOrEqualTo(root.<Date>get("recordDate"), endDate));
					}
				}
				return predicate;
			}
		};
	}

}
