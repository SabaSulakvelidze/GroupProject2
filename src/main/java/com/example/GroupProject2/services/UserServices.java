package com.example.GroupProject2.services;

import com.example.GroupProject2.models.Enum.UserRole;
import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.UserPostRequest;
import com.example.GroupProject2.models.request.UserPutRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServices {

    private static Map<Integer, UserModel> userModelMap;
    private static int id;

    @PostConstruct
    public void init(){
        userModelMap = new HashMap<>();
        id = 0;
        UserPostRequest userPostRequest = new UserPostRequest();
        userPostRequest.setName("Admin");
        userPostRequest.setUserRole(UserRole.ADMIN);
        addUser(userPostRequest);
    }

    public UserModel addUser(UserPostRequest userPostRequest){
        UserModel userModel = new UserModel();
        userModel.setUserRole(userPostRequest.getUserRole());
        userModel.setName(userPostRequest.getName());
        switch (userPostRequest.getUserRole()){
            case USER -> userModel.setBudget(1000.00);
            case ADMIN -> userModel.setBudget(0.00);
            default -> throw new IllegalStateException("Unexpected value: " + userPostRequest.getUserRole());
        }
        userModel.setId(id++);
        userModelMap.put(userModel.getId(), userModel);
        return userModel;
    }

    public List<UserModel> getAllUsers(){
        return new ArrayList<>(userModelMap.values());
    }

    public UserModel getSingleUser(Integer id){
        return userModelMap.get(id);
    }

    public UserModel updateUser(Integer id, UserPutRequest newUserPutRequest){
        UserModel userModel = userModelMap.get(id);
        if (userModel == null) throw new RuntimeException("User not found with ID: " + id);
        userModel.setName(newUserPutRequest.getName());
        userModel.setUserRole(newUserPutRequest.getUserRole());
        userModel.setBudget(newUserPutRequest.getBudget());
        return userModel;
    }

    public void deleteUser(Integer id){
        userModelMap.remove(id);
    }

}
