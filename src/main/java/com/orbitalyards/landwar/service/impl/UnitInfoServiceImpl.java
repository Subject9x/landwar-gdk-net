package com.orbitalyards.landwar.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.orbitalyards.landwar.jpa.model.AppUser;
import com.orbitalyards.landwar.jpa.model.UnitInfo;
import com.orbitalyards.landwar.jpa.repository.UnitInfoRepository;
import com.orbitalyards.landwar.jpa.repository.UserRepository;
import com.orbitalyards.landwar.mvc.model.body.AppResponse;
import com.orbitalyards.landwar.mvc.model.body.AppSyncReponse;
import com.orbitalyards.landwar.mvc.model.body.UnitResponse;
import com.orbitalyards.landwar.mvc.model.data.UnitInfoEntry;
import com.orbitalyards.landwar.mvc.model.dto.UnitInfoEntryDTO;
import com.orbitalyards.landwar.service.UnitInfoService;
import com.orbitalyards.landwar.service.exception.UserServiceException;

import jakarta.transaction.Transactional;


@Service
public class UnitInfoServiceImpl implements UnitInfoService {
	
	private static Logger logger = LoggerFactory.getLogger(UnitInfoServiceImpl.class);
	
	@Autowired
	private UnitInfoRepository unitInfoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UnitInfoEntryDTO unitInfoEntryDTO;
	
	/***
	 * Specific service-route for front-end application implementations. The {@linkplain AppSyncResponse} is used to collect
	 * all 3 forms of data-sync; Update, Create, Delete. These actions are processed in that exact order as well.
	 * @param appUpdate
	 * @return {@linkplain AppResponse}
	 */
	@Override
	public AppResponse syncAppData(AppSyncReponse appUpdate) {
		
		AppResponse response = validateUnitRequest(appUpdate.getUpdate());
		
		if(response == null) {
			Optional<AppUser> checkUser = null;
			try {
				checkUser = userRepository.findByUserName(appUpdate.getUser().getUserName());
			}
			catch(Exception e){
				logger.error(e.getMessage(), e);
				return new UnitResponse.Builder()
						.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
						.setMsg(UserServiceException.errors.JPA_GENERAL.msg())
						.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.build();
			}
			
			if(checkUser.isEmpty() || !checkUser.isPresent()) {
				return new UnitResponse.Builder()
								.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
								.setMsg(UserServiceException.errors.JPA_GENERAL.msg())
								.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
								.build();
			}
			
			AppUser user  = checkUser.get();
			
			try {
				createNewUnits(appUpdate.getCreate(), user);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			
			try {
				updateExistingUnits(appUpdate.getUpdate(), user);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			
			try {
				deleteUnits(null, user);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			
		}
		
		return response;
	}	
	
	@Override
	public AppResponse createUnits(UnitResponse unitInfoPack) {
		
		AppResponse response = validateUnitRequest(unitInfoPack.getUnits());
		
		if(response == null) {
			Optional<AppUser> checkUser = null;
			try {
				checkUser = userRepository.findByUserName(unitInfoPack.getUser().getUserName());
			}
			catch(Exception e){
				logger.error(e.getMessage(), e);
				return new UnitResponse.Builder()
						.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
						.setMsg(UserServiceException.errors.JPA_GENERAL.msg())
						.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.build();
			}
			
			if(checkUser.isEmpty() || !checkUser.isPresent()) {
				return new UnitResponse.Builder()
								.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
								.setMsg(UserServiceException.errors.JPA_GENERAL.msg())
								.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
								.build();
			}
			
			AppUser user  = checkUser.get();
			
			try {
				updateExistingUnits(unitInfoPack.getUnits(), user);
			}
			catch(Exception e) {
				logger.error(e.getMessage(), e);
				return new UnitResponse.Builder()
						.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
						.setMsg("Problem saving units to database.")
						.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.setUnits(unitInfoPack.getUnits())
						.build();
			}
			
			Optional<AppUser> usah = userRepository.findById(user.getId());

			return new UnitResponse.Builder()
					.setHttpStatus(HttpStatus.OK)
					.setMsg("Units have been saved successfully..")
					.setStatusCode(HttpStatus.OK.value())
					.setUnits(unitInfoPack.getUnits())
					.build();
		}
		
		return response;
	}

	@Override
	public AppResponse getUnitsByUser(String userName, String userCode, int count, String sortBy) {
		// TODO Auto-generated method stub
		
		
		Optional<AppUser> checkUser = userRepository.findByUserName(userName);
		
		List<UnitInfo> findUnits = unitInfoRepository.findByAppUserOrderById(checkUser.get());
		
		
		
		AppResponse dbg = new UnitResponse.Builder()
							.setHttpStatus(HttpStatus.OK)
							.setStatusCode(HttpStatus.OK.value())
							.setMsg("yup")
							.build();
		
		return dbg;
	}

	@Override
	public AppResponse updateUnit(UnitResponse unitInfoPack) {
		AppResponse response = validateUnitRequest(unitInfoPack.getUnits());
		
		if(response == null) {
			
		}
		
		return response;
	}

	@Override
	public AppResponse deleteUnits(UnitResponse unitInfoList) {
		AppResponse response = validateUnitRequest(unitInfoList.getUnits());
		
		if(response == null) {
			
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	
	private UnitResponse validateUnitRequest(List<UnitInfoEntry> units) {
		
		if(units == null || units.size() == 0 || units.isEmpty()) {
			UnitResponse resp = new UnitResponse.Builder()
								.setHttpStatus(HttpStatus.BAD_REQUEST)
								.setMsg("Unit list was empty.")
								.build();
			return resp;
		}
		
		return null;
	}
	
	@Transactional
	private void createNewUnits(List<UnitInfoEntry> createList, AppUser user) throws Exception{
		
		List<UnitInfo> createUnits = new ArrayList<UnitInfo>();
		for(UnitInfoEntry updated : createList) {
			UnitInfo newUnit = new UnitInfo();
			newUnit = unitInfoEntryDTO.toPersistFromModel(updated, newUnit);
			newUnit.setAppUser(user);
			user.getUnits().add(newUnit);
			createUnits.add(newUnit);
		}
		
		unitInfoRepository.saveAllAndFlush(createUnits);
	}
	
	@Transactional
	private List<UnitInfoEntry> updateExistingUnits(List<UnitInfoEntry> updateList, AppUser user) throws Exception{
		
		
		List<UnitInfo> existingUnits = unitInfoRepository.findByAppUserOrderById(user);
		for(UnitInfoEntry updated : updateList) {
			for(UnitInfo exist : existingUnits) {
				if(exist.getId().equals(updated.getId())) {
					exist = unitInfoEntryDTO.toPersistFromModel(updated, exist);
				}
			}
		}
		
		existingUnits = unitInfoRepository.saveAllAndFlush(existingUnits);
		
		return unitInfoEntryDTO.listModelFromPersist(existingUnits);
	}
	
	@Transactional
	private void deleteUnits(Long[] deleteList, AppUser user) throws Exception{
		
		List<Long> idList = Arrays.asList(deleteList);
		unitInfoRepository.deleteAllById(idList);
	}
}
