package com.example.demo.mvc;

import com.example.demo.dto.EducationDto;
import com.example.demo.dto.JobExperienceDto;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.service.EducationService;
import com.example.demo.service.JobExperienceService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resumes")
public class EditResumeController {
    private final ResumeService resumeService;
    private final UserService userService;
    private final EducationService educationService;
    private final JobExperienceService jobExperienceService;


    @GetMapping("/add/{userId}")
    public String addMovie(Model model, @PathVariable int userId) {
        model.addAttribute("id", userId);
        return "editResume";
    }

    @PostMapping("add/{userId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addResume(
            @RequestParam(name = "resume_name") String resumeName,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "expected_salary") int expectedSalary,
            Authentication auth,
            @PathVariable int userId
    ) {
        ResumeDto resumeDto = ResumeDto.builder()
                .job(resumeName)
                .expectedSalary(expectedSalary)
                .applicant(userService.mapToUserDto(userService.getUserById(userId).get()))
                .build();
        int resumeId = resumeService.saveResume(resumeDto, auth);
        return "redirect:/resumes/" + resumeId;
    }

    @GetMapping("/{resumeId}")
    public String getResume(Model model, @PathVariable int resumeId) {
        ResumeDto resumeDto = resumeService.getResumeById(resumeId);

        model.addAttribute("resume", resumeDto);
        return "additionalResume";
    }

    @PostMapping("/{resumeId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addAdditionalInfo(
            @RequestParam(name = "university") String university,
            @RequestParam(name = "degree") String degree,
            @RequestParam(name = "startDate") LocalDate startDate,
            @RequestParam(name = "endDate") LocalDate endDate,
            @RequestParam(name = "position") String position,
            @RequestParam(name = "startDateJob") LocalDate startDateJob,
            @RequestParam(name = "endDateJob") LocalDate endDateJob,
            Authentication auth,
            @PathVariable int resumeId
    ) {
        EducationDto educationDto = EducationDto.builder()
                .institutionName(university)
                .degree(degree)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        JobExperienceDto jobExperienceDto = JobExperienceDto.builder()
                .position(position)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        educationService.saveEducation(educationDto, resumeId);
        jobExperienceService.saveJobExperience(jobExperienceDto, resumeId);
        return "redirect:/resumes/" + resumeId;
    }
}
