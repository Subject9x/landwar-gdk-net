package com.orbitalyards.landwar.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.orbitalyards.landwar.jpa.model.UnitInfo;

public interface UnitInfoRepository extends CrudRepository<UnitInfo, Long> {

//	UnitInfo findById(Long id);
	
//	UnitInfo loadByUser(Long userId);
	
//	List<UnitInfo> loadAllByUser(Long userId);
	
}
