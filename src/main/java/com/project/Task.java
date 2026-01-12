package com.project;

import java.util.Date;

public class Task {
    private int id;
    private String title;
    private String description;
    private Date date;
    private String status;
    private int priority;
    private Type type;

    public Task(String title, String description, Date date, String status, int priority, Type type) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;
        this.priority = priority;
        this.type = type;
    }
}
