package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.entity.UserModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class UserController {

    @PostMapping
    public UserModel addUser(@RequestBody UserModel userModel){

        return userModel;
    }

}
