package com.ruixing.vehicle.manager.convoy.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import com.ruixing.vehicle.manager.domain.ConvoyEndDate;
import com.ruixing.vehicle.manager.domain.ConvoyInfo;
import com.ruixing.vehicle.manager.domain.ConvoyStartDate;

public interface ConvoyRepository extends JpaSpecificationExecutor<ConvoyInfo>, Repository<ConvoyInfo, String>{
	
	public void save(ConvoyInfo convoyInfo);
	
	public void delete(String id);
	
	public void save(ConvoyStartDate updateConvoy);
	
	public void save(ConvoyEndDate updateConvoy);
	
	Page<ConvoyInfo> findAll(Specification<ConvoyInfo> spec, Pageable pageable);

}
