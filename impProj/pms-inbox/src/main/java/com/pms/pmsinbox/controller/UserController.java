package com.pms.pmsinbox.controller;

import com.pms.pmsinbox.model.Users;
import com.pms.pmsinbox.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Data
@RestController
@Slf4j
public class UserController {

    private final UserService service;

    @GetMapping("/users/{id}")
    public List<Users> users(@PathVariable("id") Integer roleId) {
        List<Users> usersList = null;
        try {
            usersList= service.getUserDetailsInPagination(roleId).orElseThrow(() -> new RuntimeException("users not present"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return usersList;
    }

    @PostMapping("/statusUpdate")
    public Users statusUpdate( @RequestBody Map<?,?> body) {
        return service.updateUserStatus(body);
    }

    @GetMapping("/users/details/{userId}")
    public Users fetchUserDetailsById(@PathVariable("userId") Integer userId) {
        Users user = null;
        try {
            user= service.fetchUserDetailsById(userId).orElseThrow(() -> new RuntimeException("user not present"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return user;
    }

    @GetMapping("/users/page")
    public List<Users> getPagination(
            @RequestParam(defaultValue = "5") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize
            )
    {
        List<Users> list = service.getUserDetailsInPagination(pageNo, pageSize);
        return list;
    }
   }


