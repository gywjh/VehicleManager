package com.ruixing.vehicle.manager.operation.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import com.ruixing.vehicle.manager.domain.OperationInfo;

public interface OperationRepository
		extends JpaSpecificationExecutor<OperationInfo>, Repository<OperationInfo, String> {

	public void save(OperationInfo dbOperation);

	public void delete(String id);

	Page<OperationInfo> findAll(Specification<OperationInfo> spec, Pageable pageable);
}
