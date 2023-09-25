package com.example.demo.controllersMvc;

import com.example.demo.dto.ChatDto;
import com.example.demo.dto.JobExperienceDto;
import com.example.demo.dto.JobListDto;
import com.example.demo.dto.UserDto;
import com.example.demo.enums.AccountType;
import com.example.demo.model.Chat;
import com.example.demo.model.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.JobsListService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/vacancies")
public class ListVacancyController {
    private final JobResumeService jobResumeService;
    private final JobsListService jobsListService;
    private final UserService userService;
   

    private static final int PAGE_SIZE = 5;

    @GetMapping
    public String getVacancies(@RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "sort", defaultValue = "resumeDate") String sortField,
                               Model model) {
        Page<JobListDto> vacancies = jobsListService.getAllJobs(page, PAGE_SIZE, sortField);
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


    @PostMapping
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String sort(
            @RequestParam(name = "sort") String sort
    ) {
        return "redirect:/vacancies?page=0&sort=" + sort;
    }

    @GetMapping("/{userId}/chat")
    public String chat(@PathVariable int userId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto employerDto = userService.mapToUserDto(userService.getUserById(userId).orElse(null));
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        model.addAttribute("user", userDto);
        model.addAttribute("employer", employerDto);
        return "chat";
    }

    @GetMapping("{userId}")
    public String getInfo(@PathVariable int userId, Model model) {
        User user = userService.getUserById(userId).orElse(null);
        if (user == null) {
            return "notExists";
        }
        UserDto employerDto = userService.mapToUserDto(user);
        employerDto.setJobResumes(jobResumeService.getJobResumesByUserId(employerDto.getId()));
        model.addAttribute("employer", employerDto);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
            AccountType accountType = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).
                    orElse(null)).getAccountType();
            model.addAttribute("type", accountType);
        }
        return "vacancy";
    }


}
