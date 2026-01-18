package com.project.models;

import lombok.Data;

@Data
public class taskType {
    private int id;
    private String name;
    private String description;
    public taskType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
