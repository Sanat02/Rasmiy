package com.example.demo.controller;

import com.example.demo.model.JobList;
import com.example.demo.service.JobsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobslist")
public class JobsListController {
    @Autowired
    private JobsListService jobsListService;

    public JobsListController(JobsListService jobsListService) {
        this.jobsListService = jobsListService;
    }

    @GetMapping
    public List<JobList> getAllJobs(){
        return jobsListService.getAllJobs();
    }

    @GetMapping("/getByCategory/{category}")
    public List<JobList> getByCategory(@PathVariable String category){
        return jobsListService.getByCategory(category);
    }
}
