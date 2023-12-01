package com.orbitalyards.landwar.mvc.model.dto.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.model.map.UnitTags;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.data.UnitTagModel;
import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;

@Component
public class UnitInfoEntryDTOImpl implements UnitInfoEntryDTO {

	private static Logger logger = LoggerFactory.getLogger(UnitInfoEntryDTOImpl.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	
	@Override
	public UnitInfoEntry toModelFromJson(String jsonData) throws JsonMappingException, JsonProcessingException{
		if(jsonData == null || jsonData.isBlank() || jsonData.isBlank()) {
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
	public List<UnitInfoEntry> toModelFromJsonAsList(String jsonListData) throws JsonMappingException, JsonProcessingException{
		if(jsonListData == null || jsonListData.isBlank() || jsonListData.isBlank()) {
			throw new JsonMappingException(null, "Error: data string was empty or blank.");
		}
		List<UnitInfoEntry> units = jsonMapper.readValue(jsonListData, new TypeReference<List<UnitInfoEntry>>() { });
		
		return units;
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
		
		List<UnitTagModel> tags = new ArrayList<UnitTagModel>();
		
		jpaModel.getTags().stream().forEach((UnitTags t)->{
			tags.add(new UnitTagModel(t.getUnitTagId(), t.getRulesId()));
		});
		
		unitEntry.setTags(tags);
		
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
		
		//no need for tag-update complexity, just wholly replace tags with what's being sent.
		Set<UnitTags> updateTags = new HashSet<UnitTags>();
		for(UnitTagModel t : entry.getTags()) {
			UnitTags newTag = new UnitTags();
			newTag.setUnitTagId(t.getId());
			newTag.setRulesId(t.getRulesId());
			newTag.setUnitInfoId(jpaModel);
			updateTags.add(newTag);
		}
		jpaModel.setTags(updateTags);
		
		return jpaModel;
	}	

	public List<UnitInfoEntry> listModelFromPersist(List<UnitInfo> models){
		List<UnitInfoEntry> entries = new ArrayList<UnitInfoEntry>();
		
		models.stream().forEach((UnitInfo m) -> {entries.add(toModelFromPersist(m));});
		
		return entries;
	}
	
	public List<UnitInfo> listPersistFromModels(List<UnitInfoEntry> entries){
		List<UnitInfo> modelList = new ArrayList<UnitInfo>();
		
		entries.stream().forEach((UnitInfoEntry e) -> {modelList.add(toPersistFromModel(e, new UnitInfo()));});
		
		//TODO - will have to query DB for existing models to update.
		
		return modelList;
	}
}
