package ru.javapro.task4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@ComponentScan
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainApp.class);
        UserService userService = context.getBean(UserService.class);

        userService.deleteAllUser();

        userService.addUser("John");
        userService.addUser("Ann");
        userService.addUser("Bob");

        List<User> userList = userService.getAllUser();

        User user = userService.getUserByName("Bob");
        System.out.println(user);

        userService.renameUser("Bob", "Jack");

        userService.deleteUser("John");

        userList = userService.getAllUser();

    }
}
