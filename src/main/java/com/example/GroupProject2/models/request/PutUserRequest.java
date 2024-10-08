package com.example.GroupProject2.models.request;

import com.example.GroupProject2.models.Enum.UserRole;

public class PutUserRequest {

    private String name;

    private UserRole userRole;
    private Double budget;

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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
