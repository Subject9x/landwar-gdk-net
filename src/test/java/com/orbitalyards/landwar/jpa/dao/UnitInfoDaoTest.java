package com.orbitalyards.landwar.jpa.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.orbitalyards.landwar.jpa.model.AppUser;
import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.repository.UnitInfoRepository;
import com.orbitalyards.landwar.jpa.repository.UserRepository;

@DataJpaTest
public class UnitInfoDaoTest extends JPATestRig{

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UnitInfoRepository unitInfoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
	public void testInsertUserList() throws Exception{
		//TEST SETUP
		testBootIni();
		AppUser user = createMockUser(entityManager.getEntityManager());
		
//		Set<UnitInfo> updateSet = new HashSet<UnitInfo>();
//		updateSet.addAll(user.getUnits());
		
//		updateSet.add(createMockUnitInfo());
//		updateSet.add(createMockUnitInfo());
//		updateSet.add(createMockUnitInfo());
		
//		user.setUnits(updateSet);
		
		user = userRepository.save(user);
		//
		
		Optional<AppUser> saved = userRepository.findById(user.getId());
		
		assertFalse(saved.isEmpty());
		assertTrue(saved.isPresent());
		
		AppUser u  = saved.get();
		
		UnitInfo sub = createMockUnitInfo();
		sub.setAppUser(u);
		u.getUnits().add(sub);
		
		unitInfoRepository.save(sub);
		
		saved = userRepository.findById(u.getId());

		assertFalse(saved.isEmpty());
		assertTrue(saved.isPresent());
		
		UnitInfo sub2 = createMockUnitInfo();
		sub2.setUnitName("SECOND TEST");
		sub2.setAppUser(saved.get());
		saved.get().getUnits().add(sub2);

		System.out.println("pre-persist");
		Iterable<UnitInfo> i = saved.get().getUnits();
		Iterator<UnitInfo> itr = i.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next().toString());
		}
		
		System.out.println("---------------------------");
		try {
			userRepository.save(saved.get());
		
			saved = userRepository.findById(u.getId());	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		assertFalse(saved.isEmpty());
		assertTrue(saved.isPresent());
		
		saved.get().getUnits().forEach(t -> {System.out.println(t.toString());});
		
		Optional<UnitInfo> revTest = unitInfoRepository.findById(sub2.getId());
		
		System.out.println(revTest.get().getAppUser().toString());
	}
	
	@Test
	public void testSaveAll() throws Exception{

		//TEST SETUP
		testBootIni();
		AppUser user = createMockUser(entityManager.getEntityManager());
		
		user = userRepository.save(user);
		//
		Set<UnitInfo> updateSet1 = new HashSet<UnitInfo>();
		updateSet1.add(createMockUnitInfo());
		updateSet1.add(createMockUnitInfo());
		
		Iterator<UnitInfo> itr1 = updateSet1.iterator();
		while(itr1.hasNext()) {
			UnitInfo unit = itr1.next();
			unit.setAppUser(user);
			user.getUnits().add(unit);
		}
		
		try {
			unitInfoRepository.saveAll(updateSet1);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		Optional<AppUser> rebounce = userRepository.findById(user.getId());
		
		Set<UnitInfo> checkSet1 = rebounce.get().getUnits();
		
		assertFalse(checkSet1.isEmpty());
		
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
