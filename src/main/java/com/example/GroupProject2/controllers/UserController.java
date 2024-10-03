package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.PostUserRequest;
import com.example.GroupProject2.models.request.PutUserRequest;
import com.example.GroupProject2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userServices;

    @PostMapping
    public UserModel addUser(@RequestBody PostUserRequest postUserRequest){
        return userServices.addUser(postUserRequest);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Integer id, @RequestBody PutUserRequest putUserRequest){
        return userServices.updateUser(id,putUserRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userServices.removeUser(id);
    }


    @GetMapping
    public List<UserModel> getAllUsers(){
        return userServices.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getSingleUser(@PathVariable Integer id){
        return userServices.getSingleUser(id);
    }

}
