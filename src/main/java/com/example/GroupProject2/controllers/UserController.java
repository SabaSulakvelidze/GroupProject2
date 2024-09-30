package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.services.UserServices;
import com.example.GroupProject2.models.request.UserRequest;
import com.example.GroupProject2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userServices;

    @GetMapping
    public List<UserModel> getAllUsers(){
        return userServices.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getSingleUser(@PathVariable Integer id){
        return userServices.getSingleUser(id);
    }

}
