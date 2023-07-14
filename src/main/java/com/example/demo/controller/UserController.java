package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor


public class UserController {
    private final UserService userService;




    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/name/{accountname}")
    public Optional<User> getUserByName(@PathVariable String accountname){
        log.info("Got username:"+accountname);
        return userService.getUserByName(accountname);
    }

    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/isExists/{email}")
    public String isExitsUser(@PathVariable String email){
        return userService.isUserExist(email);
    }




}
