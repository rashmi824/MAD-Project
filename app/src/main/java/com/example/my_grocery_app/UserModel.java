package com.example.my_grocery_app;

public class UserModel {
    private String username, name, email, telNo, password;

    public UserModel(){

    }

    public UserModel(String username, String name, String email, String telNo, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.telNo = telNo;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

