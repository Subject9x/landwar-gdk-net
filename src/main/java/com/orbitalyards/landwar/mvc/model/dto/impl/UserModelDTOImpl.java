package com.orbitalyards.landwar.mvc.model.dto.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.orbitalyards.landwar.jpa.model.User;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.mvc.model.dto.UserModelDTO;

public class UserModelDTOImpl implements UserModelDTO {

	@Override
	public UserModel toModelFromJson(String jsonData) throws JsonMappingException, JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toJsonFromModel(UserModel model) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel toModelFromPersist(User jpaModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User toPersistFromModel(UserModel entry, User jpaModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
