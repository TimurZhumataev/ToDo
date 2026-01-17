package com.project.models;

public class MyUser {
    private String name;
    private String password;

    public MyUser() {};

    public MyUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    };

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
