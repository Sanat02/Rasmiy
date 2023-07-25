package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.enums.AccountType;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resumes")
public class ResumeController {
    private final ResumeService resumeService;

    //get all resumes
    @GetMapping
    public List<ResumeDto> getAllResumes() {
        return resumeService.getAllResumes();
    }

    //get resumes by job
    @GetMapping("/job/{job}")
    public List<ResumeDto> getAllResumes(@PathVariable String job) {
        return resumeService.getResumeByJob(job);
    }

    //create resume
    @PostMapping
    public void createJobResume(@RequestBody ResumeDto resumeDto , Authentication auth) {
        resumeDto.getApplicant().setAccountType(AccountType.JOB_SEEKER);
        resumeService.saveResume(resumeDto,auth);
    }

    //update resume
    @PutMapping
    public void updateResume(@RequestBody ResumeDto resumeDto) {
        resumeService.updateResume(resumeDto);
    }

    //delete resume
    @DeleteMapping("/id/{resume_id}")
    public void deleteResume(@PathVariable int resume_id) {
        resumeService.deleteResume(resume_id);
    }



}
