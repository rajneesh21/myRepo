package com.rk.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rk.shared.Utils;
import com.rk.ui.model.request.UserDetailsRequestModel;
import com.rk.ui.model.response.UserRest;
import com.rk.userservice.UserService;

@Service
public class UserServiceImpl implements UserService{
	Map<String,UserRest> users;
	Utils utils;
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils=utils;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest returnValue=new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId=utils.generateUserId();
		returnValue.setUserId(userId);
		if(users==null) users=new HashMap<>();
		users.put(userId, returnValue);
		
		return returnValue;
	}

}
