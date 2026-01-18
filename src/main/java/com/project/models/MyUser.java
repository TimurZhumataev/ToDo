package com.project.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MyUser {
    private int id;
    private String name;
    private String password;
    private List<Integer> tasksId;

    public MyUser() {};

    public MyUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static List<MyUser> idUser(List<MyUser> users) {
        for(int i = 0; i < users.size(); i++){
            if(i == 0){
                continue;
            }
            if(users.get(i).getId() <= users.get(i-1).getId()){
                users.get(i).setId(users.get(i-1).getId() + 1);
            }
        }
        return users;
    }
}
