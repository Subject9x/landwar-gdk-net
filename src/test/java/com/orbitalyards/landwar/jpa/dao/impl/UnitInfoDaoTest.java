package com.orbitalyards.landwar.jpa.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.model.map.UnitTagMap;
import com.orbitalyards.landwar.jpa.repository.UnitInfoRepository;

@DataJpaTest
public class UnitInfoDaoTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UnitInfoRepository unitInfoRepository;
	
	
	@Test
	public void insert(){
		
		UnitInfo unit = new UnitInfo();
		
		unit.setArmor(4);
		unit.setDesc("A TEST UNIT");
		unit.setDmgMelee(3);
		unit.setDmgRange(6);
		unit.setEvade(0);
		unit.setMove(6);
		unit.setPointsCost(34);
		unit.setRange(12);
		unit.setSize(4);
		unit.setUnitName("DORPY");
		unit.setUid(String.valueOf(unit.hashCode()));
		
		try {
		
			Set<UnitTagMap> tags = new HashSet<UnitTagMap>();
			
			UnitTagMap tag = new UnitTagMap();
			tag.setTagId(3L);
			tags.add(tag);
			
			tag = new UnitTagMap();
			tag.setTagId(1L);
			tags.add(tag);
			
			tag = new UnitTagMap();
			tag.setTagId(12L);
			tags.add(tag);
			
			unit.setTags(tags);
			

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
		
	}
	
	@Test
	@Ignore
	public void delete() throws Exception{
		
	}
	
	@Test
	@Ignore
	public void getUnitByName() throws Exception{
		
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
