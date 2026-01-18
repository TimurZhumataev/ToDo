package com.project.models;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class Task {
    private int id;
    private int userId;
    private String title;
    private String description;
    private Instant startDate;
    private LocalDate endDate;
    private String status;
    private int priority;
    private taskType taskType;

    Instant instant = Instant.now();

    public Task(int userId, String title, String description, LocalDate endDate, int priority, taskType taskType) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.startDate = instant;
        this.endDate = endDate;
        this.status = "In process";
        this.priority = priority;
        this.taskType = taskType;
    }

    public Task(int userId, String title, String description, LocalDate endDate, int priority) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.startDate = instant;
        this.endDate = endDate;
        this.status = "In process";
        this.priority = priority;
    }

    public static List<Task> idTask(List<Task> tasks) {
            for(int i = 0; i < tasks.size(); i++){
                if(i == 0){
                    continue;
                }
                if(tasks.get(i).getId() <= tasks.get(i-1).getId()){
                    tasks.get(i).setId(tasks.get(i-1).getId() + 1);
                }
        }
        return tasks;
    }

}
