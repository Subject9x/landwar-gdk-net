package com.orbitalyards.landwar.mvc.model.dto;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.mvc.model.data.ArmyList;

public interface ArmyListDTO {
	
	public ArmyList toModelFromJson(String jsonData) throws JsonMappingException, JsonProcessingException;
	
	public String toJsonFromModel(ArmyList model) throws JsonProcessingException;
	
	public ArmyList toModelFromPersist(UnitInfo jpaModel);
	
	public UnitInfo toPersistFromModel(ArmyList entry, UnitInfo jpaModel);
	
	public List<ArmyList> listModelFromPersist(List<UnitInfo> models);
	
	public List<UnitInfo> listPersistFromModels(List<ArmyList> entries);
}
