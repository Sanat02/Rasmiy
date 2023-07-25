package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.enums.AccountType;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resumes")
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
    public HttpStatus createJobResume(@RequestBody ResumeDto resumeDto , Authentication auth) {
        resumeDto.getApplicant().setAccountType(AccountType.JOB_SEEKER);
        resumeService.saveResume(resumeDto,auth);
        return HttpStatus.OK;
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
