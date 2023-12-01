package com.orbitalyards.landwar.mvc.model.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;

@Component
public interface UnitInfoEntryDTO {
	
	public UnitInfoEntry toModelFromJson(String jsonData) throws JsonMappingException, JsonProcessingException;
	
	public String toJsonFromModel(UnitInfoEntry model) throws JsonProcessingException;
	
	public List<UnitInfoEntry> toModelFromJsonAsList(String jsonListData) throws JsonMappingException, JsonProcessingException;
	
	public UnitInfoEntry toModelFromPersist(UnitInfo jpaModel);
	
	public UnitInfo toPersistFromModel(UnitInfoEntry entry, UnitInfo jpaModel);
	
	public List<UnitInfoEntry> listModelFromPersist(List<UnitInfo> models);
	
	public List<UnitInfo> listPersistFromModels(List<UnitInfoEntry> entries);
	
}
