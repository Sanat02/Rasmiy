package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor

public class AccountController {
    private final UserService userService;

    @PostMapping("/applicant")
    public void createAccountApplicant(@RequestBody UserDto user) {
        user.setAccountType(AccountType.JOB_SEEKER);
        userService.save(user);
    }


    @PostMapping("/employer")
    public void createAccountEmployer(@RequestBody UserDto user) {
        user.setAccountType(AccountType.EMPLOYER);
        userService.save(user);
    }


    @GetMapping
    public List<UserDto> getAllAccounts() {
        return userService.getAllUsers();
    }

    @GetMapping("/jobseekers")
    public List<UserDto> getAllJobSeekers() {
        return userService.getAllJobSeekers();
    }

    @GetMapping("/employers")
    public List<UserDto> getAllEmployers() {
        return userService.getAllEmployers();
    }

}
