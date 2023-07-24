package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.enums.AccountType;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor

public class AccountController {
    private final UserService userService;

    @PostMapping("/applicant")
    public void createAccountApplicant(@RequestBody UserDto user) {
        log.info("Applicant:" + user.getAccountName() + " created succesfully!");
        user.setAccountType(AccountType.JOB_SEEKER);
        userService.save(user);
    }


    @PostMapping("/employer")
    public void createAccountEmployer(@RequestBody UserDto user) {
        log.info("Employer:" + user.getAccountName() + " created succesfully!");
        user.setAccountType(AccountType.EMPLOYER);
        userService.save(user);
    }


    @GetMapping
    public List<UserDto> getAllAccounts() {
        return userService.getAllUsers();
    }
    @GetMapping("/jobseekers")
    public List<UserDto> getAllJobSeekers(){
        return userService.getAllJobSeekers();
    }

}
