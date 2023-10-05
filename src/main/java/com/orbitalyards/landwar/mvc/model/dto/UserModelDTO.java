package com.orbitalyards.landwar.mvc.model.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.orbitalyards.landwar.jpa.model.User;
import com.orbitalyards.landwar.mvc.model.data.UserModel;

@Component
public interface UserModelDTO {
	
	public UserModel toModelFromJson(String jsonData) throws JsonMappingException, JsonProcessingException;
	
	public String toJsonFromModel(UserModel model) throws JsonProcessingException;
	
	public UserModel toModelFromPersist(User jpaModel);
	
	public User toPersistFromModel(UserModel entry, User jpaModel);
	
}
