package com.example.demo.controller;


import com.example.demo.enums.AccountType;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor

public class AccountController {
    private final UserService userService;

    //Создание учётной записи (с выбором типа соискатель/работодатель)

    //соискатель
    @PostMapping("/applicant")
    public void createAccountApplicant(@RequestBody User user) {
        log.info("Applicant:" + user.getAccount_name() + " created succesfully!");
        user.setAccount_type(AccountType.JOB_SEEKER);
        userService.createUser(user);
    }

    //работадатель
    @PostMapping("/employer")
    public void createAccountEmployer(@RequestBody User user) {
        log.info("Employer:" + user.getAccount_name() + " created succesfully!");
        user.setAccount_type(AccountType.EMPLOYER);
        userService.createUser(user);
    }
}
