package com.example.demo.controller;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.WhoInterestedDto;
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

    @GetMapping
    public List<WhoInterestedDto> getInterestedJobs() {
        return whoInterestedService.getAllInterested();
    }
}
