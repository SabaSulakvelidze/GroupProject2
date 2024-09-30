package com.example.GroupProject2.models.request;

import com.example.GroupProject2.models.Enum.UserRole;

public class UserPostRequest {
    private String name;

    private UserRole userRole;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
