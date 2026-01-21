package com.project.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.Adapters.InstantTypeAdapter;
import com.project.Adapters.LocalDateTypeAdapter;
import com.project.models.MyUser;
import com.project.models.TaskType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TypeStorage {
    static String path = "src/main/java/com/project/json/types.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .setPrettyPrinting()
            .create();

    public static void saveType(TaskType type) {

        List<TaskType> types = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<TaskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        for(TaskType t : types) {
            if(t.getName().equals(type.getName())) {
                System.out.println("Type with this name already exists!");
                return;
            }
        }
        types.add(type);
        TaskType.idType(types);

        try(FileWriter fw = new FileWriter(path)) {
            fw.write(gson.toJson(types));
        }
        catch(IOException e) {
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }
    }

    public static TaskType loadType(String typeName) {
        List<TaskType> types = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<TaskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        for(TaskType type : types ) {
            if(typeName.equals(type.getName())) {
                return type;
            }
        }
        return null;
    }

    public static List<TaskType> loadAllTypes() {
        List<TaskType> types = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<TaskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        return types;
    }

    public static List<TaskType> loadTypes(int userId) {
        List<TaskType> types = new ArrayList<>();
        List<TaskType> userTypes = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<TaskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        for(TaskType type : types ) {
            if(userId == type.getUserId()) {
                System.out.println();
                System.out.println("------------------------");
                System.out.println("Title: " + type.getName());
                System.out.println("Description: " + type.getDescription());
                System.out.println("------------------------");
                System.out.println();
            }
        }
        return userTypes;
    }

    public static void deleteAllTypesOfTasks(MyUser user) {
        List<TaskType> types = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<TaskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        for(TaskType type : types ) {
            if(user.getId() == type.getUserId()) {
                types.remove(type);
            }
        }
        System.out.println("Deleted all types of tasks of " + user.getName());
    }

    public static void deleteTypeOfTasks(String typeName) {
        List<TaskType> types = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<TaskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        for(TaskType type : types ) {
            if(!typeName.equals(type.getName())) {
                types.remove(type);
                System.out.println("Type " + typeName + " was deleted!");

                try(FileWriter fw = new FileWriter(path)) {
                    fw.write(gson.toJson(types));
                }
                catch(IOException e) {
                    System.err.println("Couldn't save type of tasks: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Couldn't find this type of tasks: " + typeName);
    }
}
