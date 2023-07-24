package com.example.demo.controller;

import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.service.WhoInterestedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/whointerested")
@RequiredArgsConstructor
public class WhoInterestedController {
    private final WhoInterestedService whoInterestedService;


   //get who is interested
    @GetMapping("/jobId/{jobId}")
    public List<WhoInterestedDto> getInterestedApplicants(@PathVariable int jobId) {
        return whoInterestedService.getInterestedApplicants(jobId);
    }
}
