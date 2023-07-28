package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.enums.AccountType;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resumes")
@Slf4j
public class ResumeController {
    private final ResumeService resumeService;


    @GetMapping
    public List<ResumeDto> getAllResumes() {
        return resumeService.getAllResumes();
    }


    @GetMapping("/job/{job}")
    public List<ResumeDto> getAllResumes(@PathVariable String job) {
        return resumeService.getResumeByJob(job);
    }


    @PostMapping
    public ResponseEntity<String> createJobResume(@RequestBody ResumeDto resumeDto , Authentication auth, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        resumeDto.getApplicant().setAccountType(AccountType.JOB_SEEKER);
        resumeService.saveResume(resumeDto,auth);
        return ResponseEntity.ok("Resume created successfully");
    }


    @PutMapping
    public void updateResume(@RequestBody ResumeDto resumeDto) {
        resumeService.updateResume(resumeDto);
    }


    @DeleteMapping("/id/{resume_id}")
    public void deleteResume(@PathVariable int resume_id) {
        resumeService.deleteResume(resume_id);
    }



}
