package com.ruixing.vehicle.manager.convoy.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.ruixing.vehicle.manager.domain.ConvoyEndDate;
import com.ruixing.vehicle.manager.domain.ConvoyInfo;
import com.ruixing.vehicle.manager.domain.ConvoyStartDate;

public interface ConvoyRepository extends Repository<ConvoyInfo, String>{
	
	public void save(ConvoyInfo convoyInfo);
	
	public void delete(String id);
	
	public void save(ConvoyStartDate updateConvoy);
	
	public void save(ConvoyEndDate updateConvoy);
	
	List<ConvoyInfo> findAll();

}
