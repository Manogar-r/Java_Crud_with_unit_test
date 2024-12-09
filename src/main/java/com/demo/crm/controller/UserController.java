package com.demo.crm.controller;

import com.demo.crm.entity.User;
import com.demo.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    public UserService userService;


    // save new User in the database
    @PostMapping("/save")
    public String saveUserInfo(User user){
        return userService.saveUserInfo(user);
    }

    //get a user by id
    @GetMapping("/get/{id}")
    public User getUserInfo(Long id){
        return userService.getUserInfo(id);
    }

    @GetMapping("/getUsers")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
}