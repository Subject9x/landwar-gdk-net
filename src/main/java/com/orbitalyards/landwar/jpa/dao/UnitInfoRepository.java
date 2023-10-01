package com.orbitalyards.landwar.jpa.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.orbitalyards.landwar.jpa.model.UnitInfo;

import jakarta.persistence.PersistenceException;

@Repository
public interface UnitInfoRepository {

	Optional<UnitInfo> loadById(Long id) throws Exception, PersistenceException;
	
	List<UnitInfo> loadByUser(Long userId) throws Exception, PersistenceException;
	
	Long insert(UnitInfo info) throws Exception, PersistenceException;
}
