package com.orbitalyards.landwar.jpa.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.repository.UnitInfoRepository;

@DataJpaTest
public class UnitInfoDaoTest extends JPATestRig{

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UnitInfoRepository unitInfoRepository;
	
	public void testBootIni() {
		initMockTags(entityManager.getEntityManager());
		initRandomUsers(entityManager.getEntityManager());
	}
	
	@Test
	public void insert(){
		testBootIni();
		
		UnitInfo unit = createMockUnitInfo();
		
		try {
			unit = entityManager.persistAndFlush(unit);
			entityManager.clear();
			
			Optional<UnitInfo> test = unitInfoRepository.findById(unit.getId());
			
			assertEquals(unit, Optional.of(test));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void update() throws Exception{
		testBootIni();
		
		Long userId = Long.valueOf(ThreadLocalRandom.current().nextInt(1, getUserTestTotal()));		
	
	}
	
	@Test
	@Ignore
	public void delete() throws Exception{
		
	}
	
	@Test
	public void getUnitByName() throws Exception{
		testBootIni();
		
		UnitInfo unit = createMockUnitInfo();
		
		entityManager.persistAndFlush(unit);
		entityManager.clear();
		
		UnitInfo found = unitInfoRepository.findByUnitName(unit.getUnitName());
		
		assertNotNull(found);
		assertEquals(found, unit);
		
	}
	
	@Test
	@Ignore
	public void getUnitById() throws Exception{
		
	}
	
	@Test
	@Ignore
	public void getUnitsById() throws Exception{
		
	}
	
	@Test
	@Ignore
	public void getUnitsByUser() throws Exception{
		
	}
}
