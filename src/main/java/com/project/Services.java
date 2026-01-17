package com.project;

import com.project.models.MyUser;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Scanner;

public class Services {
    static Scanner scanner = new Scanner(System.in);

    public static void register() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        if(username.isEmpty() || password.length()<8){
            System.out.println("Username cannot be empty or the number of characters in your password is less than 8");
        }else {
            MyUser user = new MyUser(username, BCrypt.hashpw(password, BCrypt.gensalt()));
            if(UserStorage.saveUser(user) != null){
                System.out.println("You're registered!");
            }else{
                System.out.println("This user already exists!");
            }
        }
    }

    public static void login(){
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        MyUser user = new MyUser(username, password);
        if(UserStorage.getUser(user) == null){
            System.out.println("Username or password invalid!");
        }else{
            System.out.println("User found!");
        }
    }
    public static void start() {
        System.out.println("Welcome to ToDoList");
        System.out.println("Choose on of the options: ");
        System.out.println("1 - login");
        System.out.println("2 - register");
        String choice = scanner.nextLine();
        if(choice.equals("1")){
            login();
        }else if(choice.equals("2")){
            register();
        }else{
            System.out.println("Invalid choice");
        }
    }
}
