package com.example.demo.controllersMvc;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobresumes")
public class EditJobResumeController {
    private final UserService userService;
    private final JobResumeService jobResumeService;

    @GetMapping("/add")
    public String editJobResume(Model model) {
        return "editVacancy";
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addJobResume(
            @RequestParam(name = "resume_name") String vacancyName,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "expected_salary") int expectedSalary,
            @RequestParam(name = "vacancy-description") String description,
            @RequestParam(name = "from_year") int startDate,
            @RequestParam(name = "till_year") int endDate
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        JobResumeDto jobResumeDto = JobResumeDto.builder()
                .jobTitle(vacancyName)
                .user(userService.mapToUserDto(userService.getUserById(userDto.getId()).get()))
                .jobDescription(description)
                .salary(expectedSalary)
                .experience(startDate)
                .category(category)
                .build();

        int jobResumeId = jobResumeService.saveJobResume(jobResumeDto);
        return "redirect:/jobresumes/" + jobResumeId;
    }

    @GetMapping("/{jobResumeId}")
    public String getJobResume(Model model, @PathVariable int jobResumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        JobResumeDto jobResumeDto = jobResumeService.mapToJobResumeDto(jobResumeService.getJobResumeById(jobResumeId).get());
        if (jobResumeDto.getUser().getId() == userDto.getId()) {
            model.addAttribute("jobresume", jobResumeDto);
            return "seeVacancy";
        } else {
            return "prohibited";
        }
    }

    @GetMapping("/edit/{jobResumeId}")
    public String editJobResume(Model model, @PathVariable int jobResumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        JobResumeDto jobResumeDto = jobResumeService.mapToJobResumeDto(jobResumeService.getJobResumeById(jobResumeId).get());
        if (jobResumeDto.getUser().getId() == userDto.getId()) {
            model.addAttribute("jobresume", jobResumeDto);
            return "updateVacancy";
        } else {
            return "prohibited";
        }
    }

    @PostMapping("/edit/{jobResumeId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String editJobResume(
            @RequestParam(name = "resume_name") String vacancyName,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "expected_salary") int expectedSalary,
            @RequestParam(name = "vacancy-description") String description,
            @RequestParam(name = "from_year") int startDate,
            @RequestParam(name = "till_year") int endDate,
            @PathVariable int jobResumeId
    ) {
        JobResumeDto jobResumeDto = JobResumeDto.builder()
                .id(jobResumeId)
                .jobTitle(vacancyName)
                .jobDescription(description)
                .salary(expectedSalary)
                .experience(startDate)
                .category(category)
                .build();

        jobResumeService.updateJobResume(jobResumeDto);
        return "redirect:/jobresumes/" + jobResumeId;
    }
}
