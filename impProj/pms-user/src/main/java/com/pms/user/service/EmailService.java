package com.pms.user.service;

import com.pms.user.model.UserEmail;
import com.pms.user.model.Users;

public interface EmailService{

    public boolean sendEmail(UserEmail emailEntity, Users user);

}
