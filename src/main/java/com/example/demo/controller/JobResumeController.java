package com.example.demo.controller;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.JobResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobresume")
@RequiredArgsConstructor
public class JobResumeController {
    private final JobResumeService jobResumeService;


    @PostMapping
    public HttpStatus createJobResume(@RequestBody JobResumeDto jobResumeDto) {
        jobResumeDto.getUser().setAccountType(AccountType.EMPLOYER);
        jobResumeService.saveJobResume(jobResumeDto);
        return HttpStatus.OK;
    }


    @PutMapping
    public void updateJobResume(@RequestBody JobResumeDto jobResumeDto) {
        jobResumeService.updateJobResume(jobResumeDto);
    }


    @DeleteMapping("/id/{id}")
    public void deleteJobResume(@PathVariable int id){
        jobResumeService.deleteJobResume(id);
    }


    @GetMapping
    public List<JobResumeDto> getAllJobResumes(){
        return  jobResumeService.gettAllJobResumes();
    }



    @GetMapping("/category/{categoryName}")
    public List<JobResumeDto> getJobResumesByCategoryName(@PathVariable String categoryName) {
        return jobResumeService.getJobResumeByCategoryName(categoryName);
    }


}
