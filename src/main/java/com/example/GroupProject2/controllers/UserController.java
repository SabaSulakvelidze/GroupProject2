package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping
    public List<UserModel> getAllUsers(){
        return userServices.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getSingleUser(@PathVariable Integer id){
        return userServices.getSingleUser(id);
    }

}
