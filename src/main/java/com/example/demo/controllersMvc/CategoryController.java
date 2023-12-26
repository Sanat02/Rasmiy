package com.example.demo.controllersMvc;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.CategoryService;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final UserService userService;
    private final JobResumeService jobResumeService;

    @GetMapping("/{categoryId}")
    public String getHomePage(Model model, @PathVariable int categoryId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        authorities.forEach(authority -> log.info("Authority: " + authority.getAuthority()));
        List<JobResumeDto> jobResumes=jobResumeService.getJobsBycategoryId(categoryId);

        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).
                    orElse(null)).getAccountType();
            model.addAttribute("type", accountType);

        }
        model.addAttribute("jobs",jobResumes);
        return "categoryInfo";
    }
}
