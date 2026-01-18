package com.project.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.Adapters.InstantTypeAdapter;
import com.project.Adapters.LocalDateTypeAdapter;
import com.project.models.Task;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    static String path = "src/main/java/com/project/json/tasks.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .setPrettyPrinting()
            .create();

    public static Task saveTask(Task task){

        List<Task> tasks =  new ArrayList<Task>();

        try(FileReader fr = new FileReader(path)){
            Type taskListType = new TypeToken<List<Task>>() {}.getType();
            tasks = gson.fromJson(fr, taskListType);
        }
        catch(Exception e){
            System.err.println("Error while saving task: " + e.getMessage());
        }

        tasks.add(task);
        Task.idTask(tasks);

        try(FileWriter fw = new FileWriter(path)){
            fw.write(gson.toJson(tasks));
        }
        catch(IOException e){
            System.err.println("Error while saving task: " + e.getMessage());
        }
        return task;
    }

    public static List<Task> getTasks(int id){
        List<Task> tasks = new ArrayList<>();
        List<Task> tasksWithId = new ArrayList<>();

        try(FileReader fr = new FileReader(path)){
            Type taskListType = new TypeToken<List<Task>>() {}.getType();

            tasks = gson.fromJson(fr, taskListType);
        }catch(IOException e){
            System.err.println("Error while getting tasks: " + e.getMessage());
        }
        for(Task task : tasks){
            if(task.getUserId() == id){
                tasksWithId.add(task);
            }
        }
        for(Task task : tasksWithId){
            System.out.println();
            System.out.println("------------------------");
            System.out.println("Title: " + task.getTitle());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Deadline: " + task.getEndDate());
            System.out.println("Priority: " + task.getPriority());
            System.out.println("Status: " + task.getStatus());
            System.out.println("------------------------");
            System.out.println();
        };
        return tasksWithId;
    }

    public static Task getTask(Task task){

        List<Task> tasks = new ArrayList<Task>();
        Type type = new TypeToken<List<Task>>() {}.getType();

        try(FileReader fr = new FileReader(path)){
            tasks =  gson.fromJson(fr, type);

            for(Task t : tasks){
                if(t.getTitle().equals(task.getTitle())){
                    return t;
                }
            }
        }
        catch (IOException e){
            System.err.println("Error while getting task: " + e.getMessage());
        }
        return null;
    }
}
