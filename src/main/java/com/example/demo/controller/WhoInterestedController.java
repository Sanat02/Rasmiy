package com.example.demo.controller;

import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import com.example.demo.service.WhoInterestedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/whointerested")
public class WhoInterestedController {
    @Autowired
    private WhoInterestedService whoInterestedService;

    public WhoInterestedController(WhoInterestedService whoInterestedService) {
        this.whoInterestedService = whoInterestedService;
    }
    @GetMapping("/jobseekers")
    public List<User> getInterestedJobSeekers()
    {
        return whoInterestedService.getInterestedJobSeekers();
    }
    @GetMapping("/jobs")
    public List<JobResume> getInterestedJobs()
    {
        return whoInterestedService.getInterestedJobs();
    }
}
