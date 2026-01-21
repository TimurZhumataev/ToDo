package com.project.models;

import lombok.Data;

import java.util.List;

@Data
public class TaskType {
    private int id;
    private int userId;
    private String name;
    private String description;
    public TaskType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TaskType(int userId, int id, String name, String description) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static List<TaskType> idType(List<TaskType> types) {
        for(int i = 0; i < types.size(); i++){
            if(i == 0){
                continue;
            }
            if(types.get(i).getId() <= types.get(i-1).getId()){
                types.get(i).setId(types.get(i-1).getId() + 1);
            }
        }
        return types;
    }
}
