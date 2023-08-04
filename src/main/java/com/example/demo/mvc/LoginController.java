package com.example.demo.mvc;

import com.example.demo.dto.UserDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    @GetMapping
    public String login() {
        return "login";
    }

    @GetMapping("/error")
    public String loginError() {
        return "loginError";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String login(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password

    ) {


        if (userService.isUserExist(email).equalsIgnoreCase("0")) {
            return "redirect:/login/error";
        } else {
            UserDto userDto =userService.mapToUserDto(userService.getUserByEmail(email).get());
            return "redirect:/profile/" + userDto.getId();
        }
    }

}
