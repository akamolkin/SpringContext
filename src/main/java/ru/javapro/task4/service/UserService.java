package ru.javapro.task4.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.javapro.task4.dto.AppRespDto;
import ru.javapro.task4.dto.UserDto;
import ru.javapro.task4.entity.User;
import ru.javapro.task4.exception.BadReqException;
import ru.javapro.task4.exception.NotFoundException;

import java.util.List;

@Service
public class UserService {
    private final UserDto userDto;
    public User getUserById(long id){
        User result = userDto.findById(id);
        if (result == null) throw new NotFoundException("User not found with id " + id);
        return result;
    }
    public User getUserByName(String name){
        User result = userDto.findByName(name);
        if (result == null) throw new NotFoundException("User not found with name " + name);
        return result;
    }

    public List<User> getAllUsers(){
        List<User> userList = userDto.getAll();
        System.out.println(userList);
        return userList;
    }
    public AppRespDto addUser(String name){
        if (userDto.findByName(name) != null) throw new BadReqException("User named " + name + " already exists");
        int result = userDto.createUser(name);
        return new AppRespDto(result, "User added") ;
    }

    public void renameUser(String oldName, String newName){
        if (userDto.findByName(oldName) == null) throw new BadReqException("User named " + oldName + " not found");
        if (userDto.findByName(newName) != null) throw new BadReqException("User named " + newName + " already exists");
        if (userDto.updateByName(oldName, newName) == 1) System.out.println("User renamed");
    }

    public void deleteUser(String name){
        if (userDto.deleteByName(name) == 1) System.out.println("User deleted");
    }
    public void deleteAllUser(){
        if (userDto.deleteAll() == 1) System.out.println("All users deleted");
    }
    public UserService(UserDto userDao) {
        this.userDto = userDao;
    }
}
