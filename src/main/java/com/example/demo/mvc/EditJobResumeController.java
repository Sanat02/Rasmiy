package com.example.demo.mvc;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobresumes")
public class EditJobResumeController {
    private final UserService userService;
    private final JobResumeService jobResumeService;
    private final CategoryService categoryService;

    @GetMapping("/add/{userId}")
    public String addMovie(Model model, @PathVariable int userId) {
        model.addAttribute("id", userId);
        return "editVacancy";
    }

    @PostMapping("add/{userId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addResume(
            @RequestParam(name = "resume_name") String vacancyName,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "expected_salary") int expectedSalary,
            @RequestParam(name = "vacancy-description") String description,
            @RequestParam(name = "from_year") int startDate,
            @RequestParam(name = "till_year") int endDate,
            Authentication auth,
            @PathVariable int userId
    ) {
        //int daysDifference =endDate-
        JobResumeDto jobResumeDto= JobResumeDto.builder()
                .jobTitle(vacancyName)
                .user(userService.mapToUserDto(userService.getUserById(userId).get()))
                .jobDescription(description)
                .salary(expectedSalary)
                .experience(startDate)
                .build();

        int jobResumeId = jobResumeService.saveJobResume(jobResumeDto);
        return "redirect:/jobresumes/"+jobResumeId;
    }

    @GetMapping("/{jobResumeId}")
    public String getResume(Model model, @PathVariable int jobResumeId) {
        JobResumeDto jobResumeDto=jobResumeService.mapToJobResumeDto(jobResumeService.getJobResumeById(jobResumeId).get());
        model.addAttribute("jobresume", jobResumeDto);
        return "seeVacancy";
    }
}
