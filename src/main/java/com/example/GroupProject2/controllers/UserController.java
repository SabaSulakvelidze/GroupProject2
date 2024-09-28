package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.UserRequest;
import com.example.GroupProject2.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping
    public UserModel addUser(@RequestBody UserRequest userRequest){
        return userServices.addUser(userRequest);
    }

    @GetMapping
    public List<UserModel> getAllUsers(){
        return userServices.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getSingleUser(@PathVariable Integer id){
        return userServices.getSingleUser(id);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Integer id,@RequestBody UserRequest userRequest){
        return userServices.updateUser(id,userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userServices.deleteUser(id);
    }

}
