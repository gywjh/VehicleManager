package com.ruixing.vehicle.manager.operation.service;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ruixing.vehicle.manager.domain.OperationInfo;
import com.ruixing.vehicle.manager.operation.dao.OperationRepository;
import com.ruixing.vehicle.manager.operation.entity.FindOperationRequest;
import com.ruixing.vehicle.manager.operation.entity.SaveOperationRequest;
import com.ruixing.vehicle.manager.utils.Constants;

@Service
public class OperationService {

	@Autowired
	private OperationRepository operRepository;

	public void getOperationInfo(SaveOperationRequest requestEntity) {
		OperationInfo operationDaoBean = new OperationInfo();
		operationDaoBean.setHandleResult(requestEntity.getHandleResult());
		operationDaoBean.setOperationPlace(requestEntity.getOperationPlace());
		operationDaoBean.setRecordDate(new Date());
		operationDaoBean.setRecordStatus(1);
		operationDaoBean.setResultMark(requestEntity.getResultMark());
		operationDaoBean.setUserId(requestEntity.getUserId());
		operationDaoBean.setUserName(requestEntity.getUserName());
		operRepository.save(operationDaoBean);
	}

	public void deleteOperationInfo(String id) {
		operRepository.delete(id);
	}

	public Page<OperationInfo> findOperationInfoList(FindOperationRequest findRequest) {
		Pageable pageable = Constants.getPageable(findRequest.getCurrentPage() + 1, "recodeTime");
		Specification<OperationInfo> spec = getWhereClause(findRequest);
		return operRepository.findAll(spec, pageable);
	}

	private Specification<OperationInfo> getWhereClause(FindOperationRequest findRequest) {
		return new Specification<OperationInfo>() {
			@Override
			public Predicate toPredicate(Root<OperationInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (null != findRequest) {
					String name = findRequest.getUserName();
					if (StringUtils.isNotBlank(name)) {
						predicate.getExpressions().add(cb.like(root.<String>get("userName"), StringUtils.trim(name)));
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
