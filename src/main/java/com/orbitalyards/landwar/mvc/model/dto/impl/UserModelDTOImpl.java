package com.orbitalyards.landwar.mvc.model.dto.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbitalyards.landwar.jpa.model.AppUser;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.mvc.model.dto.UserModelDTO;

@Component
public class UserModelDTOImpl implements UserModelDTO {

	private static Logger logger = LoggerFactory.getLogger(UnitInfoEntryDTOImpl.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();
	
	@Override
	public UserModel toModelFromJson(String jsonData) throws JsonMappingException, JsonProcessingException {
		if(jsonData.isBlank() || jsonData.isBlank()) {
			throw new JsonMappingException(null, "Error: data string was empty or blank.");
		}
		
		UserModel model = null;
		
		try {
			model = jsonMapper.readValue(jsonData, UserModel.class);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		}
		
		return model;
	}

	@Override
	public String toJsonFromModel(UserModel model) throws JsonProcessingException {
		
		if(model == null) {
			throw new JsonMappingException(null, "Error: UserModel object was null.");			
		}
		
		String json = jsonMapper.writeValueAsString(model);
		
		if(json.isBlank() || json.isEmpty()) {
			throw new JsonMappingException(null, "Error: UserModel object was null.");	
		}
		return json;
	}

	@Override
	public UserModel toModelFromPersist(UserModel userModel, AppUser jpaModel) {
		
		userModel.setUserName(jpaModel.getUserName());
		userModel.setLoggedIn(jpaModel.getLogIn());
		userModel.setUserCode(jpaModel.getPassCode());
			
		return userModel;
	}

	@Override
	public AppUser toPersistFromModel(UserModel entry, AppUser jpaModel) {
		
		jpaModel.setLogIn(entry.getLoggedIn());
		jpaModel.setUserName(entry.getUserName());
		jpaModel.setPasscode(entry.getUserCode());
		
		return jpaModel;
	}
}
