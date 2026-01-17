package com.project;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.models.MyUser;
import org.mindrot.jbcrypt.BCrypt;

public class UserStorage {
    static String path = "src/main/java/com/project/users.json";

    public static MyUser saveUser(MyUser user){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();

        List<MyUser> users = new ArrayList<MyUser>();

        try(FileReader fr = new FileReader(path)){
            Type userListType = new TypeToken<List<MyUser>>() {}.getType();
            users = gson.fromJson(fr, userListType);
            for(MyUser u : users){
                if(u.getName().equals(user.getName())){
                    return null;
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        users.add(user);
        try(FileWriter fw = new FileWriter(path)){
            fw.write(gson.toJson(users));
        }
        catch(IOException e){
            System.out.println("Couldn't write data to file " + e.getMessage());
        }
        return user;
    }

    public static MyUser getUser(MyUser user){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();

        List<MyUser> users = new ArrayList<MyUser>();
        Type userListType = new TypeToken<List<MyUser>>() {}.getType();

        try(FileReader fr = new FileReader(path)){
            users = gson.fromJson(fr, userListType);

            for(MyUser u : users){
                if(u.getName().equals(user.getName()) && BCrypt.checkpw(user.getPassword(), u.getPassword())){
                    return u;
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
