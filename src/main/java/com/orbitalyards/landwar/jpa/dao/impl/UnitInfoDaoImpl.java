//package com.orbitalyards.landwar.jpa.dao.impl;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Component;
//
//import com.orbitalyards.landwar.jpa.dao.UnitInfoDao;
//import com.orbitalyards.landwar.jpa.model.UnitInfo;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.NoResultException;
//import jakarta.persistence.PersistenceContext;
//
//@Component
//public class UnitInfoDaoImpl implements UnitInfoDao<UnitInfo>{
//
//	@PersistenceContext
//	private EntityManager entityManager;
//	
//	@Override
//	public Optional<UnitInfo> loadById(Long unitId) {
//		
//		UnitInfo found = entityManager.find(UnitInfo.class, unitId);
//	    try {
//	        return Optional.of(found);
//	    } catch (NoResultException ex) {
//	        //log
//	    }
//		return Optional.empty();
//	}
//
//	@Override
//	public Optional<UnitInfo> loadByName(String unitName) {
//		// TODO Auto-generated method stub
//		
//		return Optional.empty();
//	}
//
//	@Override
//	public Optional<List<UnitInfo>> loadByUser(Long userId) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public void updateUnit(UnitInfo unit) {
//		
//		entityManager.merge(unit);
//		
//	}
//
//	@Override
//	public void deleteUnit(UnitInfo unit) {
//		entityManager.remove(unit);
//	}
//
//}
