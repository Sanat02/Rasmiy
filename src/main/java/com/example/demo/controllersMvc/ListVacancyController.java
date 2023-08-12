package com.example.demo.controllersMvc;

import com.example.demo.dto.JobListDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.JobsListService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vacancies")
public class ListVacancyController {
    private final JobsListService jobsListService;
    private final UserService userService;

    private static final int PAGE_SIZE = 5;

    @GetMapping
    public String getVacancies(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Page<JobListDto> vacancies = jobsListService.getAllJobs(page, PAGE_SIZE);
        model.addAttribute("vacancies", vacancies);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", auth.getName());
            AccountType accountType = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).
                    orElse(null)).getAccountType();
            model.addAttribute("type", accountType);
        }

        return "vacancies";
    }


}
