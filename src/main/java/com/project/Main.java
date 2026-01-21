package com.project;

import com.project.storage.TaskStorage;

public class Main {
    public static void main(String[] args) {
        TaskStorage.updateTasks();
        Services.start();
    }
}