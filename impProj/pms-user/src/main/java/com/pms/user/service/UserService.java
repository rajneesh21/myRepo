package com.pms.user.service;

import com.pms.user.model.UserEmail;
import com.pms.user.model.Users;
import com.pms.user.repository.UserEmailRepository;
import com.pms.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserEmailRepository userEmailRepository;


    public UserService(UserRepository userRepository, UserEmailRepository userEmailRepository) {
        this.userRepository = userRepository;
        this.userEmailRepository = userEmailRepository;
    }

    public Users addUser(Users user){
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setIsActive(1);
        return userRepository.save(user);
    }


    public Users updateUser(Users user){
        try{
            Optional<Users> optionalUser = userRepository.findById(user.getUserId());
            if(optionalUser.isPresent()){
                Users existingUser = optionalUser.get();
                user.setCreatedAt(existingUser.getCreatedAt());
                user.setUpdatedAt(LocalDateTime.now());
                user.setUpdatedBy(user.getUserId());
                user.setCreatedBy(user.getUserId());
                user.setIsActive(existingUser.getIsActive());
                return userRepository.save(user);
            }
        }catch (Exception ex){
            logger.error("Error while updating user info !", ex);
        }
        return null;
    }

    public Users getUserByEmail(String emailId){
        try {
            if (emailId!=null && emailId.length()>5){
                Optional<Users> optionalUser = userRepository.findByEmail(emailId);
                if(optionalUser.isPresent()){
                    return optionalUser.get();
                }
            }
        }catch (Exception e){
            logger.error("error while finding user by email", e);
        }
        return null;
    }

    public Users getUser(Integer id){
        try {
            Optional<Users> optionalUsers = userRepository.findById(id);
            if(optionalUsers.isPresent()){
                return optionalUsers.get();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public List<String> getAllDesignation(){
        try{
            return userRepository.getAllDesignation();
        }catch (Exception ex){
            logger.error("Error while getting all list of designation!", ex);
        }
        return Collections.emptyList();
    }

    public List<Users> getDoctorByDesignation(String value){
        try{
            return userRepository.getDoctorByDesignation(value);
        }catch (Exception ex){
            logger.error("Error while getting all list of user by designation!", ex);
        }
        return Collections.emptyList();
    }

    public UserEmail getUserByAuthPasscode(String authPasscode){
        try {
            Optional<UserEmail> optionalUsersEmail =userEmailRepository.findByAuthPasscode(authPasscode);
            if(optionalUsersEmail.isPresent()){
                return optionalUsersEmail.get();
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }



}
