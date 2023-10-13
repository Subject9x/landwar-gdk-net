package com.orbitalyards.landwar.mvc.model.dto.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.mvc.model.data.ArmyList;
import com.orbitalyards.landwar.mvc.model.dto.ArmyListDTO;

public class ArmyListDTOImpl implements ArmyListDTO{

	private static Logger logger = LoggerFactory.getLogger(UnitInfoEntryDTOImpl.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	
	@Override
	public ArmyList toModelFromJson(String jsonData) throws JsonMappingException, JsonProcessingException {
		if(jsonData.isBlank() || jsonData.isBlank()) {
			throw new JsonMappingException(null, "Error: data string was empty or blank.");
		}
		
		ArmyList model = null;
		
		try {
			model = jsonMapper.readValue(jsonData, ArmyList.class);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		}
		
		return model;
	}

	@Override
	public String toJsonFromModel(ArmyList model) throws JsonProcessingException {
		
		if(model == null) {
			throw new JsonMappingException(null, "Error: ArmyList object was null.");			
		}
		
		String json = jsonMapper.writeValueAsString(model);
		
		if(json.isBlank() || json.isEmpty()) {
			throw new JsonMappingException(null, "Error: ArmyList object was null.");	
		}
		return json;
	}

	@Override
	public ArmyList toModelFromPersist(UnitInfo jpaModel) {
		return null;
	}

	@Override
	public UnitInfo toPersistFromModel(ArmyList entry, UnitInfo jpaModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArmyList> listModelFromPersist(List<UnitInfo> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UnitInfo> listPersistFromModels(List<ArmyList> entries) {
		// TODO Auto-generated method stub
		return null;
	}

}
