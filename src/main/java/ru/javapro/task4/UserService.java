package ru.javapro.task4;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;
    public User getUserById(long id){
        User result = userDao.findById(id);
        if (result == null) System.out.println("User not found with id " + id);
        return result;
    }
    public User getUserByName(String name){
        User result = userDao.findByName(name);
        if (result == null) System.out.println("User not found with name " + name);
        return result;
    }

    public List<User> getAllUser(){
        List<User> userList = userDao.getAll();
        System.out.println(userList);
        return userList;
    }
    public void addUser(String name){
        if (userDao.findByName(name) != null) {
            System.out.println("User named " + name + " already exists");
            return;
        }
        if (userDao.createUser(name) == 1) System.out.println("User "+ name +" added");
    }

    public void renameUser(String oldName, String newName){
        if (userDao.findByName(oldName) == null) {
            System.out.println("User named " + oldName + " not found");
            return;
        }
        if (userDao.findByName(newName) != null) {
            System.out.println("User named " + newName + " already exists");
            return;
        }
        if (userDao.updateByName(oldName, newName) == 1) System.out.println("User renamed");
    }

    public void deleteUser(String name){
        if (userDao.deleteByName(name) == 1) System.out.println("User deleted");
    }
    public void deleteAllUser(){
        if (userDao.deleteAll() == 1) System.out.println("All users deleted");
    }
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
}
