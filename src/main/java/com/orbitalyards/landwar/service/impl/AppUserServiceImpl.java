package com.orbitalyards.landwar.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.orbitalyards.landwar.jpa.model.AppUser;
import com.orbitalyards.landwar.jpa.model.ref.Role;
import com.orbitalyards.landwar.jpa.repository.UserRepository;
import com.orbitalyards.landwar.jpa.repository.UserRoleRepository;
import com.orbitalyards.landwar.mvc.model.body.AppResponse;
import com.orbitalyards.landwar.mvc.model.body.UserResponse;
import com.orbitalyards.landwar.mvc.model.data.UserModel;
import com.orbitalyards.landwar.mvc.model.dto.UserModelDTO;
import com.orbitalyards.landwar.service.AppUserService;
import com.orbitalyards.landwar.service.exception.UserServiceException;

@Service(value = "svcAppUser")
public class AppUserServiceImpl implements AppUserService {

	private static Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);
	
	@Autowired
	private UserModelDTO userModelDTO;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public AppResponse registerUser(String userName, String userCode) {
		
		Optional<AppUser> exists = null;
		try {
			exists = userRepository.findByUserName(userName);
		}
		catch(Exception e) {
			logger.error(e.getMessage(), e);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder()
									.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
									.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		if(!exists.isEmpty() || exists.isPresent()) {
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EXIST_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder()
									.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
									.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
									.setMsg(userError.getMessage())
									.setUserException(userError)
									.build();
			return resp;
		}
		
		UserModel newUserModel = new UserModel();
		newUserModel.setUserName(userName);
		newUserModel.setUserCode(userCode);
		
		AppUser newUser = null;
		try {
			newUser = new AppUser();
			newUser = userModelDTO.toPersistFromModel(newUserModel, newUser);
			newUser = userRepository.save(newUser);
		}
		catch(Exception e) {
			logger.error(e.getMessage(), e);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		if(newUser == null || newUser.getId() == 0L) {
			logger.error("User failed to persist: {}", newUser);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		UserResponse resp = new UserResponse.Builder()
								.setUser(userModelDTO.toModelFromPersist(newUserModel, newUser))
								.setMsg("User created successfully")
								.build();
		return resp;
	}
	
	@Override
	public AppResponse loginUser(String userName, String userCode) {
		return updateUserLogin(userName, userCode, true);
	}
	

	@Override
	public AppResponse logoutUser(String userName, String userCode) {
		return updateUserLogin(userName, userCode, false);
	}
	
	@Override
	public AppResponse deleteUser(String userName, String userCode)  {
		
		Optional<AppUser> exists = userRepository.findByUserName(userName);
		
		if(exists.isEmpty() || !exists.isPresent()) {
			logger.error(UserServiceException.errors.EMPTY_USERNAME.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		AppUser user = exists.get();
		
		if(!user.getPassCode().equals(userCode)) {
			logger.error(UserServiceException.errors.USER_BAD_CODE.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.USER_BAD_CODE.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		try {
			userRepository.delete(user);	
		}
		catch(Exception e) {
			logger.error(UserServiceException.errors.JPA_GENERAL.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}

		logger.info("User[{}] self-deleted.", userName);
		UserResponse resp = new UserResponse.Builder()
								.setMsg("User deleted successfully.")
								.build();
		return resp;
	}
	
	@Override
	public AppResponse adminDeleteUser(String userName, String adminUser, String adminCode) {
		
		AppResponse validated = validateUserRole(adminUser, adminCode, Arrays.asList("ADMIN")); 
		if(validated != null) {
			return validated;
		}
		
		Optional<AppUser> toDelete = userRepository.findByUserName(userName);
		
		if(toDelete.isEmpty() || !toDelete.isPresent()) {
			logger.error(UserServiceException.errors.EMPTY_USERNAME.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		try {
			userRepository.delete(toDelete.get());
		}
		catch(Exception e) {
			logger.error(e.getMessage(), e);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		UserResponse resp = new UserResponse.Builder()
				.setMsg("User deleted successfully.")
				.build();
		return resp;
	}
	
	@Override
	public AppResponse updateUser(String userName, String userCode){
		//TODO - use case?
		Optional<AppUser> exists = userRepository.findById(0l);
		
		if(exists.isEmpty() || !exists.isPresent()) {
			logger.error(UserServiceException.errors.EMPTY_USERNAME.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		return null;
	}

	
	@Override
	public AppResponse updateUserRole( String userName, String userCode, List<String> roles){
	
		Optional<AppUser> exists = userRepository.findByUserName(userName);
		
		if(exists.isEmpty() || !exists.isPresent()) {
			logger.error(UserServiceException.errors.EMPTY_USERNAME.msg() + " {}", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EMPTY_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		AppUser user = exists.get();
		
		Iterable<Role> jpaRoles = userRoleRepository.findAll();
		Set<Role> updateRoles = new HashSet<Role>();
		
		jpaRoles.forEach(t -> {
			if(roles.contains(t.getRole())) {
				updateRoles.add(t);
			}
		});
		
		user.setRoles(updateRoles);
		
		try {
			userRepository.save(user);
		}
		catch(Exception e) {
			logger.error(e.getMessage(), e);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		logger.info("User[{}] updated roles successfully.");
		UserResponse resp = new UserResponse.Builder()
								.setMsg("Updated roles successfully.")
								.build();
		return resp;
	}
	
	private AppResponse validateUserRole(String userName, String userCode, List<String> roles){
				
		Optional<AppUser> exists = userRepository.findByUserName(userName);
		
		if(!Optional.empty().isEmpty() || !Optional.empty().isPresent()) {
			logger.error("User[{}] doesn't exist in database.", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EXIST_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		if(!exists.get().getPassCode().equals(userCode)) {
			logger.error("User[{}] failed pass-code match.", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.USER_BAD_CODE.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		AppUser user = exists.get();
		
		if(user.getRoles() == null) {
			logger.info("User[{}] had null role list, adding new list.", userName);
			user.setRoles(new HashSet<Role>());
		}
		
		List<String> userRoles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
		if(!userRoles.containsAll(roles)) {
			logger.error("User[{}] missing roles [" + roles.toString() + "]", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.USER_ROLE_MATCH.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.UNAUTHORIZED)
					.setStatusCode(HttpStatus.UNAUTHORIZED.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		return null;
	}
	
	private AppResponse updateUserLogin(String userName, String userCode, boolean loginVal){

		Optional<AppUser> exists = userRepository.findByUserName(userName);
		
		if(!Optional.empty().isEmpty() || !Optional.empty().isPresent()) {
			logger.error("User[{}] doesn't exist in database.", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.EXIST_USERNAME.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		if(!exists.get().getPassCode().equals(userCode)) {
			logger.error("User[{}] failed pass-code match.", userName);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.USER_BAD_CODE.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.BAD_REQUEST)
					.setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		if(exists.get().getLogIn()) {
			logger.info("User[{}] already logged in", userName);
			UserResponse resp = new UserResponse.Builder()
					.setMsg("You are already logged in.")
					.build();
			return resp;
		}
		
		AppUser user = exists.get();
		
		user.setLogIn(true);
		user.setLogInTime(new Timestamp(System.currentTimeMillis()));

		try {
			user = userRepository.save(user);	
		}
		catch(Exception e) {
			logger.error(e.getMessage(), e);
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		if(exists.get().getLogIn() != loginVal) {
			logger.error("Uncaught JPA exception on user {}", exists.get().getUserName());
			UserServiceException userError = new UserServiceException(UserServiceException.errors.JPA_GENERAL.msg());
			UserResponse resp = new UserResponse.Builder()
					.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
					.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.setMsg(userError.getMessage())
					.setUserException(userError)
					.build();
			return resp;
		}
		
		logger.info("User[{}] login updated to [" + loginVal + "].", userName);
		UserResponse resp = new UserResponse.Builder()
				.setMsg("Login successful.")
				.build();
		return resp;
	}
}
