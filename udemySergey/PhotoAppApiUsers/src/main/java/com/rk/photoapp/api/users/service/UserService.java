package com.rk.photoapp.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rk.photoapp.api.users.shared.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
}
