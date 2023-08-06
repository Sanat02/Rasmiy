package com.example.demo.controllersMvc;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobresumes")
public class EditJobResumeController {
    private final UserService userService;
    private final JobResumeService jobResumeService;
    @GetMapping("/add/{userId}")
    public String editJobResume(Model model, @PathVariable int userId) {
        model.addAttribute("id", userId);
        return "editVacancy";
    }

    @PostMapping("add/{userId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addJobResume(
            @RequestParam(name = "resume_name") String vacancyName,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "expected_salary") int expectedSalary,
            @RequestParam(name = "vacancy-description") String description,
            @RequestParam(name = "from_year") int startDate,
            @RequestParam(name = "till_year") int endDate,
            Authentication auth,
            @PathVariable int userId
    ) {
        JobResumeDto jobResumeDto = JobResumeDto.builder()
                .jobTitle(vacancyName)
                .user(userService.mapToUserDto(userService.getUserById(userId).get()))
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
        JobResumeDto jobResumeDto = jobResumeService.mapToJobResumeDto(jobResumeService.getJobResumeById(jobResumeId).get());
        model.addAttribute("jobresume", jobResumeDto);
        return "seeVacancy";
    }
}
