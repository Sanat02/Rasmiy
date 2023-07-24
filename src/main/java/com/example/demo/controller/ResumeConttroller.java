package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resumes")
public class ResumeConttroller {
    private final ResumeService resumeService;

    //get all resumes
    @GetMapping
    public List<ResumeDto> getAllResumes(){
        return resumeService.getAllResumes();
    }

    //get resumes by job
    @GetMapping("/job/{job}")
    public List<ResumeDto> getAllResumes(@PathVariable String job) {
        return resumeService.getResumeByJob(job);
    }

}
