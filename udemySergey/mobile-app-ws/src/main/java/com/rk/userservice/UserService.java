package com.rk.userservice;

import com.rk.ui.model.request.UserDetailsRequestModel;
import com.rk.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
