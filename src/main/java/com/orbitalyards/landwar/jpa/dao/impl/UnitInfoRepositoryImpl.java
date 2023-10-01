//package com.orbitalyards.landwar.jpa.dao.impl;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.stereotype.Component;
//
//import com.orbitalyards.landwar.jpa.dao.UnitInfoRepository;
//import com.orbitalyards.landwar.jpa.model.UnitInfo;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.PersistenceException;
//
//public class UnitInfoRepositoryImpl implements UnitInfoRepository{
//	
//	@Autowired
//	LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
//	
//	private EntityManager entityManager;
//	
//	@Override
//	public Optional<UnitInfo> loadById(Long id) throws Exception, PersistenceException{
//		
//		UnitInfo found = entityManager.find(UnitInfo.class, id);
//		
//		Optional<UnitInfo> ret = Optional.of(found);
//		
//		return ret;
//	}
//
//	@Override
//	public List<UnitInfo> loadByUser(Long userId) throws Exception, PersistenceException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Long insert(UnitInfo info) throws Exception, PersistenceException {
//	    
//		entityManager.getTransaction().begin();
//		entityManager.persist(info);
//		entityManager.getTransaction().commit();
//		
//		return info.getId();
//	}
//	
//	@PostConstruct
//	public void postConstruct() {
//		entityManager = localContainerEntityManagerFactoryBean.getNativeEntityManagerFactory().createEntityManager();
//		Objects.requireNonNull(entityManager);
//	}
//	
//}
