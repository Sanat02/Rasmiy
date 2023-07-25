package com.example.demo.controller;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.JobResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/jobresume")
@RequiredArgsConstructor
public class JobResumeController {
    private final JobResumeService jobResumeService;

    //adding jobresume
    @PostMapping
    public void createJobResume(@RequestBody JobResumeDto jobResumeDto) {
        jobResumeDto.getUser().setAccountType(AccountType.EMPLOYER);
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

    //get all jobresumes
    @GetMapping
    public List<JobResumeDto> getAllJobResumes(){
        return  jobResumeService.gettAllJobResumes();
    }


    //get job resume by category
    @GetMapping("/category/{category_name}")
    public List<JobResumeDto> getJobResumesByCategoryName(@PathVariable String categoryName) {
        return jobResumeService.getJobResumeByCategoryName(categoryName);
    }


}
