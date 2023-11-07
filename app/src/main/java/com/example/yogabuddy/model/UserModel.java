package com.example.yogabuddy.model;

import java.util.Date;

public class UserModel {
    String username,email;
    String joinDate;

    public UserModel(String username, String email, String joinDate) {
        this.username = username;
        this.email = email;
        this.joinDate = joinDate;
    }

    public UserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
