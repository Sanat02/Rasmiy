package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.model.JobResume;
import com.example.demo.service.JobResumeService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import com.example.demo.service.WhoInterestedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employer")
@RequiredArgsConstructor
public class EmployerController {

    private final JobResumeService jobResumeService;
    private final ResumeService resumeService;
    private final WhoInterestedService whoInterestedService;
    private final UserService userService;


    @PostMapping("{user_id}/vacancy")
    public void createJobResume(@PathVariable int user_id, @RequestBody JobResume jobResume) {
        log.info("Job resume:"+jobResume.getId()+" created!");
        jobResume.setUserId(user_id);
        jobResumeService.createJobResume(jobResume);
    }


    @PutMapping("{user_id}/vacancy/{id}")
    public void updateJobResume(@PathVariable int id,@PathVariable int user_id,@RequestBody JobResume jobResume) {
        log.info("Job resume:"+jobResume.getId()+" updated!");
        jobResume.setUserId(user_id);
        jobResume.setId(id);
        jobResumeService.updateJobResume(jobResume);
    }


    @DeleteMapping("/vacancy/{id}")
    public void deleteJobResume(@PathVariable int id) {
        log.info("Job resume:"+id+" deleted!");
        jobResumeService.deleteJobResume(id);
    }


    @GetMapping("/resumes")
    public List<ResumeDto> getAllResumes(){
        return resumeService.getAllResumes();
    }


    @GetMapping("/resumes/job/{job}")
    public ResumeDto getAllResumes(@PathVariable String job){
        return resumeService.getResumeByJob(job);
    }


    @GetMapping("/whointerested/jobresume/{job_id}")
    public List<WhoInterestedDto> getInterestedApplicants(@PathVariable int job_id){
        return  whoInterestedService.getInterestedApplicants(job_id);
    }


    @GetMapping("/jobseekers")
    public List<UserDto> getAllJobSeekers(){
        return userService.getAllJobSeekers();
    }
}
