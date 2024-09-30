package com.example.GroupProject2.controllers;

import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.UserPostRequest;
import com.example.GroupProject2.models.request.UserPutRequest;
import com.example.GroupProject2.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping
    public UserModel addUser(@RequestBody UserPostRequest userPostRequest){
        return userServices.addUser(userPostRequest);
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
    public UserModel updateUser(@PathVariable Integer id,@RequestBody UserPutRequest userPutRequest){
        return userServices.updateUser(id, userPutRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userServices.deleteUser(id);
    }

}
