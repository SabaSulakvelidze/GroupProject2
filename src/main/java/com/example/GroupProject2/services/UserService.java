package com.example.GroupProject2.services;

import com.example.GroupProject2.models.Enum.UserRole;
import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.PostUserRequest;
import com.example.GroupProject2.models.request.PutUserRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private static Map<Integer, UserModel> userModelMap;
    private static int id;

    @PostConstruct
    public void init() {
        userModelMap = new HashMap<>();
        id = -1;
        UserModel admin = new UserModel(++id, "Admin", UserRole.ADMIN, 0.00);
        UserModel user1 = new UserModel(++id, "User1", UserRole.USER, 1000.00);
        UserModel user2 = new UserModel(++id, "User2", UserRole.USER, 1000.00);

        userModelMap.put(user1.getId(), user1);
        userModelMap.put(user2.getId(), user2);
        userModelMap.put(admin.getId(), admin);
    }

    public List<UserModel> getAllUsers() {
        return new ArrayList<>(userModelMap.values());
    }

    public UserModel getSingleUser(Integer id) {
        return userModelMap.get(id);
    }


    public UserModel addUser(PostUserRequest postUserRequest) {
        UserModel userModel = new UserModel(id, postUserRequest.getName(), postUserRequest.getUserRole(), postUserRequest.getUserRole() == UserRole.USER ? 1000.00 : 0.00);
        userModelMap.put(userModel.getId(), userModel);
        return userModel;
    }

    public UserModel updateUser(Integer id, PutUserRequest putUserRequest) {
        UserModel userModel = userModelMap.get(id);
        userModel.setName(putUserRequest.getName());
        userModel.setBudget(putUserRequest.getBudget());
        userModel.setUserRole(putUserRequest.getUserRole());
        return userModel;
    }

    public void removeUser(Integer id){
        userModelMap.remove(id);
    }
}
