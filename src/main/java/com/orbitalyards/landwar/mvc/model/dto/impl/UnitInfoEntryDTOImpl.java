package com.orbitalyards.landwar.mvc.model.dto.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;

public class UnitInfoEntryDTOImpl implements UnitInfoEntryDTO {

	private static Logger logger = LoggerFactory.getLogger(UnitInfoEntryDTOImpl.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	
	@Override
	public UnitInfoEntry toModelFromJson(String jsonData) throws JsonMappingException, JsonProcessingException{
		if(jsonData.isBlank() || jsonData.isBlank()) {
			throw new JsonMappingException(null, "Error: data string was empty or blank.");
		}
		
		UnitInfoEntry model = null;
		
		try {
			model = jsonMapper.readValue(jsonData, UnitInfoEntry.class);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		}
		
		return model;
	}

	@Override
	public String toJsonFromModel(UnitInfoEntry model) throws JsonProcessingException{
		
		if(model == null) {
			throw new JsonMappingException(null, "Error: UnitInfoEntry object was null.");			
		}
		
		String json = jsonMapper.writeValueAsString(model);
		
		if(json.isBlank() || json.isEmpty()) {
			throw new JsonMappingException(null, "Error: UnitInfoEntry object was null.");	
		}
		return json;
	}

	/***
	 * Yes deep-copy.
	 */
	@Override
	public UnitInfoEntry toModelFromPersist(UnitInfo jpaModel) {
		
		UnitInfoEntry unitEntry = new UnitInfoEntry();
		
		unitEntry.setArmor(jpaModel.getArmor());
		unitEntry.setDesc(new String(jpaModel.getDesc()));
		unitEntry.setDmgMelee(jpaModel.getDmgMelee());
		unitEntry.setDmgRange(jpaModel.getDmgRange());
		unitEntry.setEvade(jpaModel.getEvade());
		unitEntry.setImgUrl(new String(jpaModel.getImgUrl()));
		unitEntry.setMove(jpaModel.getMove());
		unitEntry.setPointsCost(jpaModel.getPointsCost());
		unitEntry.setRange(jpaModel.getRange());
		unitEntry.setSize(jpaModel.getSize());
		unitEntry.setUnitName(new String(jpaModel.getUnitName()));
		
//		List<String> tags = new ArrayList<String>();
//		jpaModel.getTags().stream().forEach((UnitTagModel t)->{
//			tags.add(null)
//		});
//		unitEntry.setTags(jpaModel.getTags());
		
		return unitEntry;
	}

	@Override
	public UnitInfo toPersistFromModel(UnitInfoEntry entry, UnitInfo jpaModel) {
		
		jpaModel.setArmor(entry.getArmor());
		jpaModel.setDesc(entry.getDesc());
		jpaModel.setDmgMelee(entry.getDmgMelee());
		jpaModel.setDmgRange(entry.getDmgRange());
		jpaModel.setEvade(entry.getEvade());
		jpaModel.setImgUrl(entry.getImgUrl());
		jpaModel.setMove(entry.getMove());
		jpaModel.setPointsCost(entry.getPointsCost());
		jpaModel.setRange(entry.getRange());
		jpaModel.setSize(entry.getSize());
		jpaModel.setUnitName(entry.getUnitName());
//		jpaModel.setTags(entry.getTags());
		
		return jpaModel;
	}	

	public List<UnitInfoEntry> listModelFromPersist(List<UnitInfo> models){
		List<UnitInfoEntry> entries = new ArrayList<UnitInfoEntry>();
		
		models.stream().forEach((UnitInfo m) -> {entries.add(toModelFromPersist(m));});
		
		return entries;
	}
	
	public List<UnitInfo> listPersistFromModels(List<UnitInfoEntry> entries){
		List<UnitInfo> modelList = new ArrayList<UnitInfo>();
		
		//TODO - will have to query DB for existing models to update.
		
		return modelList;
	}
}
