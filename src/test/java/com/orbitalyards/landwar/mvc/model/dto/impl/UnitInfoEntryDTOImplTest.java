package com.orbitalyards.landwar.mvc.model.dto.impl;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;

public class UnitInfoEntryDTOImplTest {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	private static Logger logger = LoggerFactory.getLogger(UnitInfoEntryDTOImplTest.class);

	@Test
	public void toModelFromJson() throws JsonMappingException, JsonProcessingException {
		String data = "{\"tags\":[],\"name\":\"coooooool jump\",\"points\":75.0,\"size\":3,\"move\":6,\"evade\":1,\"armor\":4,\"DMGM\":2,\"DMGR\":4,\"range\":8,\"desc\":\"a nice description\",\"img\":\"https://imgur.com/?t=nice\"}";
		
		UnitInfoEntry test = mapper.readValue(data, UnitInfoEntry.class);
		
		assertNotNull(test);
		System.out.println(test.toString());
	}
	
	@Test
	public void toJsonFromModel() throws JsonProcessingException {
		UnitInfoEntry test = new UnitInfoEntry();
		test.setArmor(4);
		test.setDesc("a nice description");
		test.setDmgMelee(2);
		test.setDmgRange(4);
		test.setEvade(1);
		test.setImgUrl("https://imgur.com/?t=nice");
		test.setMove(6);
		test.setPointsCost(75);
		test.setRange(8);
		test.setSize(3);
//		test.setTags(new ArrayList<String>());
		test.setUnitName("coooooool jump");
		
		String out = mapper.writeValueAsString(test);
		
		assertNotNull(out);
		assertTrue(out.length() > 0);
		System.out.println(out);
		logger.info(out);
		
	}
	
	public void toModelFromPersist() {
	}
	
	public void toPersistFromModel() {
		
	}
}
