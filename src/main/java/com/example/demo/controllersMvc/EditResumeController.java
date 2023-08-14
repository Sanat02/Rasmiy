package com.example.demo.controllersMvc;

import com.example.demo.dto.*;
import com.example.demo.enums.ContactType;
import com.example.demo.model.Contacts;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resumes")
public class EditResumeController {
    private final ResumeService resumeService;
    private final UserService userService;
    private final EducationService educationService;
    private final JobExperienceService jobExperienceService;
    private final ContactsService contactsService;
    private static final int PAGE_SIZE = 5;

    @GetMapping()
    public String getAllResumes(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Page<ResumeDto> resumes = resumeService.getAllResumes(page, PAGE_SIZE);
        model.addAttribute("resumes", resumes);
        return "resumes";
    }


    @GetMapping("/add")
    public String editResume(Model model) {
        return "editResume";
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addResume(
            @RequestParam(name = "resume_name") String resumeName,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "expected_salary") Integer expectedSalary,
            @RequestParam(name = "university", required = false) String university,
            @RequestParam(name = "degree", required = false) String degree,
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate,
            @RequestParam(name = "position", required = false) String position,
            @RequestParam(name = "startDateJob", required = false) LocalDate startDateJob,
            @RequestParam(name = "endDateJob", required = false) LocalDate endDateJob,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "telegram", required = false) String telegram,
            @RequestParam(name = "linkedin", required = false) String linkedin,
            @RequestParam(name = "facebook", required = false) String facebook
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        ResumeDto resumeDto = ResumeDto.builder()
                .job(resumeName)
                .expectedSalary(expectedSalary)
                .applicant(userDto)
                .category(category)
                .build();
        int resumeId = resumeService.saveResume(resumeDto, auth);
        boolean isEducationFilled = university != null && degree != null && startDate != null && endDate != null;

        if (isEducationFilled) {
            EducationDto educationDto = EducationDto.builder()
                    .institutionName(university)
                    .degree(degree)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
            educationService.saveEducation(educationDto, resumeId);
        }
        boolean isJobExperienceFilled = position != null && startDateJob != null && endDateJob != null;
        if (isJobExperienceFilled) {
            JobExperienceDto jobExperienceDto = JobExperienceDto.builder()
                    .position(position)
                    .startDate(startDateJob)
                    .endDate(endDateJob)
                    .build();
            jobExperienceService.saveJobExperience(jobExperienceDto, resumeId);
        }
        List<ContactDto> contacts = new ArrayList<>();
        if (phone != null && !phone.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.PHONE)
                    .value(phone)
                    .build());
        }
        if (email != null && !email.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.EMAIL)
                    .value(email)
                    .build());
        }
        if (telegram != null && !telegram.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.TELEGRAM)
                    .value(telegram)
                    .build());
        }
        if (linkedin != null && !linkedin.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.LINKEDIN)
                    .value(linkedin)
                    .build());
        }
        if (facebook != null && !facebook.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.FACEBOOK)
                    .value(facebook)
                    .build());

        }
        if (!contacts.isEmpty()) {
            contactsService.saveContacts(contacts, resumeId);
        }


        return "redirect:/resumes/" + resumeId;
    }

    @GetMapping("/{resumeId}")
    public String getResume(Model model, @PathVariable int resumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        ResumeDto resumeDto = resumeService.getResumeById(resumeId);
        if (resumeDto.getApplicant().getId() == userDto.getId()) {
            model.addAttribute("resume", resumeDto);
            return "additionalResume";
        } else {
            return "prohibited";
        }
    }

    @GetMapping("/{resumeId}/education")
    public String addEducation(Model model, @PathVariable int resumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        ResumeDto resumeDto = resumeService.getResumeById(resumeId);
        if (resumeDto.getApplicant().getId() == userDto.getId()) {
            model.addAttribute("resume", resumeDto);
            return "education";
        } else {
            return "prohibited";
        }
    }

    @GetMapping("/{resumeId}/experience")
    public String addExperience(Model model, @PathVariable int resumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        ResumeDto resumeDto = resumeService.getResumeById(resumeId);
        if (resumeDto.getApplicant().getId() == userDto.getId()) {
            model.addAttribute("resume", resumeDto);
            return "experience";
        } else {
            return "prohibited";
        }
    }

    @GetMapping("/{resumeId}/contacts")
    public String addContacts(Model model, @PathVariable int resumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        ResumeDto resumeDto = resumeService.getResumeById(resumeId);
        if (resumeDto.getApplicant().getId() == userDto.getId()) {
            model.addAttribute("resume", resumeDto);
            return "contacts";
        } else {
            return "prohibited";
        }
    }


    @PostMapping("/{resumeId}/education")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addAdditionalInfo(
            @RequestParam(name = "university") String university,
            @RequestParam(name = "degree") String degree,
            @RequestParam(name = "startDate") LocalDate startDate,
            @RequestParam(name = "endDate") LocalDate endDate,
            @PathVariable int resumeId
    ) {
        EducationDto educationDto = EducationDto.builder()
                .institutionName(university)
                .degree(degree)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        educationService.saveEducation(educationDto, resumeId);
        return "redirect:/resumes/" + resumeId;
    }

    @PostMapping("/{resumeId}/contacts")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addAdditionalContacts(
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "telegram", required = false) String telegram,
            @RequestParam(name = "linkedin", required = false) String linkedin,
            @RequestParam(name = "facebook", required = false) String facebook,
            @PathVariable int resumeId
    ) {
        List<ContactDto> contacts = new ArrayList<>();
        if (phone != null && !phone.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.PHONE)
                    .value(phone)
                    .build());
        }
        if (email != null && !email.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.EMAIL)
                    .value(email)
                    .build());
        }
        if (telegram != null && !telegram.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.TELEGRAM)
                    .value(telegram)
                    .build());
        }
        if (linkedin != null && !linkedin.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.LINKEDIN)
                    .value(linkedin)
                    .build());
        }
        if (facebook != null && !facebook.isEmpty()) {
            contacts.add(ContactDto.builder()
                    .type(ContactType.FACEBOOK)
                    .value(facebook)
                    .build());

        }
        if (!contacts.isEmpty()) {
            contactsService.saveContacts(contacts, resumeId);
        }
        return "redirect:/resumes/" + resumeId;
    }

    @PostMapping("/{resumeId}/experience")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addEducationInfo(
            @RequestParam(name = "position") String position,
            @RequestParam(name = "startDateJob") LocalDate startDateJob,
            @RequestParam(name = "endDateJob") LocalDate endDateJob,
            @PathVariable int resumeId
    ) {
        JobExperienceDto jobExperienceDto = JobExperienceDto.builder()
                .position(position)
                .startDate(startDateJob)
                .endDate(endDateJob)
                .build();
        jobExperienceService.saveJobExperience(jobExperienceDto, resumeId);
        return "redirect:/resumes/" + resumeId;
    }

    @GetMapping("/{resumeId}/delete")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deleteResume(@PathVariable int resumeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        ResumeDto resumeDto = resumeService.getResumeById(resumeId);

        if (resumeDto.getApplicant().getId() == userDto.getId()) {
            resumeService.deleteResume(resumeId);
            return "redirect:/profile";
        } else {
            return "prohibited";
        }

    }

}
