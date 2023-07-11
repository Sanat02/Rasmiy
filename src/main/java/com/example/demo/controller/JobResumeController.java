package com.example.demo.controller;

import com.example.demo.model.JobResume;
import com.example.demo.service.JobResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobresumes")
public class JobResumeController {
    @Autowired
    private JobResumeService jobResumeService;

    public JobResumeController(JobResumeService jobResumeService) {
        this.jobResumeService = jobResumeService;
    }

    @GetMapping("/getByCategory/{category}")
    public List<JobResume> getResumesByCategory(@PathVariable String category) {
        return jobResumeService.getResumeByCategory(category);
    }
}
