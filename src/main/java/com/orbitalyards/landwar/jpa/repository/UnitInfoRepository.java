package com.orbitalyards.landwar.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orbitalyards.landwar.jpa.model.AppUser;
import com.orbitalyards.landwar.jpa.model.UnitInfo;

@Repository
public interface UnitInfoRepository extends JpaRepository<UnitInfo, Long> {

	List<UnitInfo> findByUnitName(String unitName);
	
	UnitInfo findByAppUser(AppUser appUser);
	
	List<UnitInfo> findByAppUserOrderById(AppUser appUser);
	
}
