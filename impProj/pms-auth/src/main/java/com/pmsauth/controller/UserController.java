package com.pmsauth.controller;

import com.pmsauth.model.User;
import com.pmsauth.model.UserLogin;
import com.pmsauth.service.KeycloakAdminClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final KeycloakAdminClientService keycloakAdminClientService;

    public UserController(KeycloakAdminClientService kcAdminClient) {
        this.keycloakAdminClientService = kcAdminClient;
    }

    @PostMapping("/keyRegister")
    public User createUser(@RequestBody User user) {

        keycloakAdminClientService.addUser(user);
        this.assignRoleToExistingUser(user);
        return user;
    }

    @PostMapping("/keyAssignRole")
    public void assignRoleToExistingUser(@RequestBody User user){
        keycloakAdminClientService.addRealmRoleToUser(user.getEmail(), user.getRole());
    }

    @GetMapping("/keyUsers")
    public List<String> getAllUserNames(){
        return keycloakAdminClientService.getAllUser();
    }

    @GetMapping("/status")
    public String status() {
        return "Controller started...!";
    }

    @GetMapping("/keyRoles")
    public List<String> getAllRoles(){
        return keycloakAdminClientService.getAllRoles();
    }

    @PostMapping("/KeyAddRole/{role}")
    public String addRole(@PathVariable String role){
        keycloakAdminClientService.addRealmRole(role);
        return role;
    }

    @PostMapping(path = "/keyLogin")
    public ResponseEntity<Object> login(@RequestBody UserLogin user) {
        ResponseEntity<Object> response = null;
        try{
            response = ResponseEntity.ok(keycloakAdminClientService.validateUser(user));
        }catch (Exception ex){
            logger.warn("Error while logging user !", ex);
        }
        return response;
    }

    @PostMapping(path = "/updatePassword")
    public ResponseEntity<Object> updatePassword(@RequestBody UserLogin user) {
        ResponseEntity<Object> response = null;
        try{
            response = ResponseEntity.ok(keycloakAdminClientService.validateUser(user));
        }catch (Exception ex){
            logger.warn("Error while logging user !", ex);
        }
        return response;
    }

    @PostMapping(path = "/resetPassword")
    public ResponseEntity<Object> resetPassword(@RequestBody UserLogin user) {
        ResponseEntity<Object> response = null;
        try{
            response = ResponseEntity.ok(keycloakAdminClientService.resetPassword(user.getUsername(),user.getPassword()));
        }catch (Exception ex){
            logger.warn("Error While Resetting Password!", ex);
        }
        return response;
    }

}
