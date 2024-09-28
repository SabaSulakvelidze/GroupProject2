package com.example.GroupProject2.models.entity;

import com.example.GroupProject2.models.Enum.UserRole;

public class UserModel {

    private Integer id;
    private String name;

    private UserRole userRole;
    private Double budget;

    public UserModel() {
    }

    public UserModel(Integer id, String name, UserRole userRole, Double budget) {
        this.id = id;
        this.name = name;
        this.userRole = userRole;
        this.budget = budget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
