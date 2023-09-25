package com.example.demo.controllersMvc;


import com.example.demo.enums.AccountType;
import com.example.demo.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.Collection;


@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final UserService userService;

    @GetMapping
    public String getHomePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        authorities.forEach(authority -> log.info("Authority: " + authority.getAuthority()));

        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).
                    orElse(null)).getAccountType();
            model.addAttribute("type", accountType);

        }
        return "home";
    }

    @GetMapping("/forgot")
    public String forgotPassword() {
        return "forgot";
    }

    @PostMapping("/forgot")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        if (userService.isUserExist(request.getParameter("email")).equals("1")) {
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
            return "redirect:/token?email=" + email;
        } else {
            model.addAttribute("error", "This email does not exist!");
            return "redirect:/forgot?error=error";
        }

    }

    @GetMapping("/reset")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswdToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error", "Invalid token");
        }
        return "reset";
    }

    @PostMapping("/reset")
    public String processResetPassword(@RequestParam String token,
                                       @RequestParam(name = "password") String password,
                                       Model model) {
        try {
            var user = userService.getByResetPasswdToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("message", "Invalid Token");
        }
        return "message";
    }

    @GetMapping("/token")
    public String setToken(@RequestParam String email, Model model) {
        if (userService.isUserExist(email).equals("1")) {
            model.addAttribute("email", email);
            return "token";
        } else {
            return "notExists";
        }
    }

    @PostMapping("/token")
    public String processSetToken(@RequestParam String email,
                                  @RequestParam(name = "token") String token) {
        userService.updateResetPasswordToken(token, email);
        System.out.println(token);
        System.out.println(email);
        return "redirect:/reset?token=" + token;

    }
}
