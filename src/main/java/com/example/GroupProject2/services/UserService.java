package com.example.GroupProject2.services;

import com.example.GroupProject2.models.Enum.UserRole;
import com.example.GroupProject2.models.entity.ProductModel;
import com.example.GroupProject2.models.entity.UserModel;
import com.example.GroupProject2.models.request.CartItemRequest;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private static Map<Integer, UserModel> userModelMap;

    @PostConstruct
    public void init() {
        userModelMap = new HashMap<>();
        int id = -1;
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


}
