package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.enums.AccountType;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public void createUser(User user) {
        userDao.createUser(user);
    }


    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        List<UserDto> userDtos=users.stream()
                .map(e->UserDto.builder()
                        .id(e.getId())
                        .profilePhoto(e.getProfilePhoto())
                        .phoneNumber(e.getPhoneNumber())
                        .accountType(e.getAccountType())
                        .password(e.getPassword())
                        .email(e.getEmail())
                        .accountName(e.getAccountName())
                        .build()
                ).collect(Collectors.toList());

        return userDtos;
    }

    public Optional<User> getUserByName(String name) {
        Optional<User> mayBeUser = userDao.getUserByName(name);
        return mayBeUser;
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> mayBeUser = userDao.getUserByEmail(email);
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

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }


    public List<UserDto> getAllJobSeekers() {
        List<User> users = userDao.getAllJobSeekers();
        List<UserDto> userDtos = users.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .accountName(e.getAccountName())
                        .accountType(AccountType.JOB_SEEKER)
                        .password(e.getPassword())
                        .phoneNumber(e.getPassword())
                        .email(e.getEmail())
                        .profilePhoto(e.getProfilePhoto())
                        .build()
                ).toList();
        return userDtos;
    }

    public List<UserDto> getAllEmployers() {
        List<UserDto> employers = getAllUsers().stream()
                .filter(e->e.getAccountType().equals(AccountType.EMPLOYER)).collect(Collectors.toList());
        return employers;
    }


}
