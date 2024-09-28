package com.example.GroupProject2.services;

import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.UserRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServices {

    private static Map<Integer, UserModel> userModelMap;
    private static int id;
    @PostConstruct
    public void init(){
        userModelMap = new HashMap<>();
        id = 0;
    }

    public UserModel addUser(UserRequest userRequest){
        UserModel userModel = new UserModel();
        userModel.setUserRole(userRequest.getUserRole());
        userModel.setName(userRequest.getName());
        userModel.setBudget(1000.00);
        userModel.setId(id++);
        userModelMap.put(userModel.getId(), userModel);
        return userModel;
    }

    public Collection<UserModel> getAllUsers(){
        return userModelMap.values();
    }

    public UserModel getSingleUser(Integer id){
        return userModelMap.get(id);
    }

    public UserModel updateUser(Integer id,UserRequest newUserRequest){
        UserModel userModel = userModelMap.get(id);
        if (userModel == null) throw new RuntimeException("User not found with ID: " + id);

        return userModel;
    }

}
