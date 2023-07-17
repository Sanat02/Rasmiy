package com.example.demo.controller;

import com.example.demo.model.JobResume;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/jobseeker")
@RequiredArgsConstructor
public class JobSeekerController {
    private final ResumeService resumeService;

    //Создание резюме для соискателя с user_id={user_id}
    @PostMapping("{user_id}/resume ")
    public void createJobResume(@PathVariable int user_id, @RequestBody Resume resume) {
        log.info("User resume:"+resume.getId()+" created!");
        resume.setUser_id(user_id);
        resumeService.createResume(resume);
    }
}
