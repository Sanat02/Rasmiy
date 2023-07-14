package com.example.demo.controller;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.model.JobResume;
import com.example.demo.service.JobResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobresumes")
@RequiredArgsConstructor
public class JobResumeController {

    private final JobResumeService jobResumeService;



    @GetMapping("/category/{category}")
    public List<JobResume> getResumesByCategory(@PathVariable String category) {
        return jobResumeService.getResumeByCategory(category);
    }
    @GetMapping
    public List<JobResumeDto> getAllResumes() {
        return jobResumeService.gettAllJobResumes();
    }
}
