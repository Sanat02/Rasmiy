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

    @GetMapping("/error")
    public String registerError() {
        return "userExists";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addResume(
            @RequestParam(name = "account_name") String accountName,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "account_type") String accountValue,
            @RequestParam(name = "phone_number") String phoneNumber,
            Model model
    ) {
        AccountType accountType;
        if (accountValue.equalsIgnoreCase(AccountType.EMPLOYER.getValue())) {
            accountType = AccountType.EMPLOYER;
        } else {
            accountType = AccountType.JOB_SEEKER;
        }

        if (userService.isUserExist(email).equalsIgnoreCase("1")) {
            return "redirect:/register/error";
        } else {
            UserDto userDto = UserDto.builder()
                    .password(password)
                    .accountName(accountName)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .accountType(accountType)
                    .build();
            DataBinder dataBinder = new DataBinder(userDto);
            dataBinder.setValidator(validator);
            dataBinder.validate();


            BindingResult bindingResult = dataBinder.getBindingResult();
            if (!bindingResult.hasErrors()) {
                int userId = userService.save(userDto);
                return "redirect:/login";
            } else {
                String errorMessages = getErrorMessages(bindingResult);
                model.addAttribute("userDto", userDto);
                model.addAttribute("error",errorMessages);
                return "registration";
            }
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
