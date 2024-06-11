package com.example.demo.controllersMvc;


import com.example.demo.dto.UserDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    private final Validator validator;


    @GetMapping
    public String register() {
        return "registration";
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addResume(
            @RequestParam(name = "account_name") String accountName,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "phone_number") String phoneNumber,
            Model model
    ) {

        if (userService.isUserExist(email).equalsIgnoreCase("1")) {
            return "redirect:/register/error";
        } else {
            UserDto userDto = UserDto.builder()
                    .password(password)
                    .accountName(accountName)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .build();

            int userId = userService.save(userDto);
            return "redirect:/login";

        }
    }

    private String getErrorMessages(BindingResult bindingResult) {
        StringBuilder errorMessages = new StringBuilder();
        bindingResult.getAllErrors().forEach(error -> {
            errorMessages.append(error.getDefaultMessage()).append("; ");
        });
        return errorMessages.toString();
    }


}
