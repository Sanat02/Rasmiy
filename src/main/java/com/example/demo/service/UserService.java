package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class UserService {
    @Autowired
    private final UserDao userDao;
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public Optional<User> getUserByName(String name){
        Optional<User> mayBeUser=userDao.getUserByName(name);
        return mayBeUser;
    }

    public Optional<User> getUserByEmail(String email){
        Optional<User> mayBeUser=userDao.getUserByEmail(email);
        return mayBeUser;
    }

    public String isUserExist(String email) {
        try {
            Optional<User> user = userDao.getUserByEmail(email);
            if (user != null) {
                return "Exists";
            } else {
                return "Not exists";
            }
        } catch (EmptyResultDataAccessException e) {
            return "Not exists";
        }
    }


}
