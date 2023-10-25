package com.orbitalyards.landwar.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.model.User;

@Repository
public interface UnitInfoRepository extends ListPagingAndSortingRepository<UnitInfo, Long>, CrudRepository<UnitInfo, Long> {

	UnitInfo findByUnitName(String unitName);
	
//	UnitInfo findById(Long id);
	
//	UnitInfo loadByUser(Long userId);
	
	List<UnitInfo> findAllByAppUser(User appUser);
	
}
