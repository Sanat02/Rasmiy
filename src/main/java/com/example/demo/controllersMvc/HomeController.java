package com.example.demo.controllersMvc;

import com.example.demo.enums.AccountType;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping
    public String getHomePage(Model model){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();

        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        }
        else{
            model.addAttribute("username", auth.getName());
           AccountType accountType = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).
                   orElse(null)).getAccountType();
            model.addAttribute("type", accountType);

        }
        return "home";
    }
}
