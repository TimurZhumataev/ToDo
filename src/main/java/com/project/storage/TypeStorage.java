package com.project.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.project.Adapters.InstantTypeAdapter;
import com.project.Adapters.LocalDateTypeAdapter;
import com.project.models.taskType;

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

    public static void saveType(taskType type) {

        List<taskType> types = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<taskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        types.add(type);
        taskType.idType(types);

        try(FileWriter fw = new FileWriter(path)) {
            fw.write(gson.toJson(types));
        }
        catch(IOException e) {
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }
    }

    public static taskType loadType(String typeName) {
        List<taskType> types = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<taskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        for(taskType type : types ) {
            if(typeName.equals(type.getName())) {
                return type;
            }
        }
        return null;
    }

    public static List<taskType> loadAllTypes() {
        List<taskType> types = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<taskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        return types;
    }

    public static List<taskType> loadTypes(int userId) {
        List<taskType> types = new ArrayList<>();
        List<taskType> userTypes = new ArrayList<>();

        try(FileReader fr =  new FileReader(path)) {
            Type tasksType = new TypeToken<List<taskType>>(){}.getType();
            types = gson.fromJson(fr, tasksType);
        }
        catch(IOException e){
            System.err.println("Couldn't save type of tasks: " + e.getMessage());
        }

        for( taskType type : types ) {
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
}
