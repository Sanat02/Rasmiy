package com.example.demo.controller;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.service.JobResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/jobresume")
@RequiredArgsConstructor
public class JobResumeController {
    private final JobResumeService jobResumeService;

    //adding jobresume
    @PostMapping
    public void createJobResume(@RequestBody JobResumeDto jobResumeDto) {
        jobResumeService.saveJobResume(jobResumeDto);
    }

    //updating jobresume
    @PutMapping
    public void updateJobResume(@RequestBody JobResumeDto jobResumeDto) {
        jobResumeService.updateJobResume(jobResumeDto);
    }

    //delete jobresume
    @DeleteMapping("/id/{id}")
    public void deleteJobResume(@PathVariable int id){
        jobResumeService.deleteJobResume(id);
    }


}
