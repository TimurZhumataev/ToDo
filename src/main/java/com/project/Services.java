package com.project;

import com.project.models.MyUser;
import com.project.models.Task;
import com.project.models.taskType;
import com.project.storage.TaskStorage;
import com.project.storage.TypeStorage;
import com.project.storage.UserStorage;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
                start();
            }else{
                System.out.println("This user already exists!");
                start();
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
            start();
        }else{
            options(user);
        }
    }

    public static void createTask(MyUser user){
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter deadline date(yyyy-mm-dd): ");
        String endDate = scanner.nextLine();
        LocalDate deadline = LocalDate.parse(endDate);
        System.out.print("Enter priority(from 1 to 5, 1 is the highest priority): ");
        int priority = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter type of task(you can skip it): ");
        String taskType = scanner.nextLine();

        Task task = new Task(user.getId(), title, description, deadline, priority, taskType);
        TaskStorage.saveTask(task);
        System.out.println("Task has been saved!");
        myTasks(user);
    }

    public static void createTaskType(){
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        taskType type = new taskType(title, description);
        TypeStorage.saveType(type);
    }

    public static void options(MyUser user){
        System.out.println("Welcome back " + user.getName() + "!");
        System.out.println("Choose option: ");
        System.out.println("My tasks - 1");
        System.out.println("My types of tasks - 2");
        System.out.println("logout - 3");
        String option = scanner.nextLine();

        if(option.equals("1")){
            myTasks(user);
        }else if(option.equals("2")){
            myTypesOfTasks(user);
        }else if(option.equals("3")){
            start();
        }else{
            System.out.println("Invalid option!");
        }
    }

    public static void myTasks(MyUser user) {
        System.out.println("Choose option: ");
        System.out.println("Create task - 1");
        System.out.println("See my tasks - 2");
        System.out.println("Search tasks by type - 3");
        System.out.println("Search tasks by priority - 4");
        System.out.println("Return to main menu - 5");
        String option = scanner.nextLine();

        if (option.equals("1")) {
            createTask(user);
            myTasks(user);
        }else if (option.equals("2")) {
            TaskStorage.getTasks(user.getId());
            myTasks(user);
        }else if(option.equals("3")) {
            System.out.print("Enter title of type: ");
            String title = scanner.nextLine();
            TaskStorage.getTasksByType(title);
            myTasks(user);
        }else if(option.equals("4")) {
            System.out.print("Enter priority(1-5): ");
            int priority = scanner.nextInt();
            scanner.nextLine();
            TaskStorage.getTasksByPriority(priority);
            myTasks(user);
        }else if(option.equals("5")) {
            options(user);
        }else{
            System.out.println("Invalid option!");
            myTasks(user);
        }
    }

    public static void myTypesOfTasks(MyUser user) {
        System.out.println("Choose option: ");
        System.out.println("Create type of tasks - 1");
        System.out.println("See my types of tasks - 2");
        System.out.println("Return to main menu - 3");
        String option = scanner.nextLine();

        if (option.equals("1")) {
            createTaskType();
            myTypesOfTasks(user);
        }else if(option.equals("2")) {
            TypeStorage.loadTypes(user.getId());
            myTypesOfTasks(user);
        }else if(option.equals("3")) {
            options(user);
        }else{
            System.out.println("Invalid option!");
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
            start();
        }
    }
}
