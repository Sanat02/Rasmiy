package com.example.demo.controllersMvc;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.enums.AccountType;
import com.example.demo.model.JobResume;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.UserService;
import com.example.demo.service.WhoInterestedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final WhoInterestedService whoInterestedService;
    private static final int PAGE_SIZE = 5;

    @GetMapping()
    public String getAllJobResumes(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "sort", defaultValue = "jobResumeDate") String sortField,
            Model model
    ) {
        Page<JobResumeDto> jobResumes = jobResumeService.getAllJobResumes(page, PAGE_SIZE, sortField);
        model.addAttribute("jobs", jobResumes);
        return "jobs";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String sort(
            @RequestParam(name = "sort") String sort
    ) {
        return "redirect:/jobresumes?page=0&sort=" + sort;
    }

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
                .user(userDto)
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
        JobResume jr = jobResumeService.getJobResumeById(jobResumeId).orElse(null);
        if (jr == null) {
            return "notExists";
        }
        JobResumeDto jobResumeDto = jobResumeService.mapToJobResumeDto(jr);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
            if (jobResumeDto.getUser().getId() != userDto.getId()) {
                return "prohibited";
            }
            model.addAttribute("username", auth.getName());
            AccountType accountType = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).
                    orElse(null)).getAccountType();
            model.addAttribute("type", accountType);
            model.addAttribute("isInterested", whoInterestedService.isClicked(
                    WhoInterestedDto.builder()
                            .applicant(userDto)
                            .job_resume(jobResumeDto)
                            .build()
            ));
        }
        model.addAttribute("jobresume", jobResumeDto);

        return "seeVacancy";
    }

    @GetMapping("/{jobResumeId}/delete")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deleteJobResume(@PathVariable int jobResumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        JobResume jr = jobResumeService.getJobResumeById(jobResumeId).orElse(null);
        if (jr == null) {
            return "notExists";
        }
        JobResumeDto jobResumeDto = jobResumeService.mapToJobResumeDto(jr);
        if (jobResumeDto.getUser().getId() == userDto.getId()) {
            jobResumeService.deleteJobResume(jobResumeId);
            return "redirect:/profile";
        } else {
            return "prohibited";
        }

    }

    @GetMapping("/edit/{jobResumeId}")
    public String editJobResume(Model model, @PathVariable int jobResumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        JobResume jr = jobResumeService.getJobResumeById(jobResumeId).orElse(null);
        if (jr == null) {
            return "notExists";
        }
        JobResumeDto jobResumeDto = jobResumeService.mapToJobResumeDto(jr);
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
