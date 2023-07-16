package com.example.demo.controller;


import com.example.demo.enums.AccountType;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor

public class AccountController {
    private final UserService userService;

    //Создание учётной записи (с выбором типа соискатель/работодатель)

    //соискатель
    @PostMapping("/applicant")
    public User createAccountApplicant(@RequestBody User user){
        user.setAccount_type(AccountType.JOB_SEEKER);
        return userService.createUser(user);
    }

    //работадатель
    @PostMapping("/employer")
    public User createAccountEmployer(@RequestBody User user){
        user.setAccount_type(AccountType.EMPLOYER);
        return userService.createUser(user);
    }
}
