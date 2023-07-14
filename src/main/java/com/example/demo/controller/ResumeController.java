package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

   @GetMapping
    public List<ResumeDto> getAllResumes(){
       return resumeService.getAllResumes();
   }
    @GetMapping("/getById/{id}")
    public ResumeDto getAllResumes(@PathVariable int id){
        return resumeService.getResumeById(id);
    }

    @PostMapping
    public void createResume(@RequestBody Resume resume){
       log.info(resume.getJob(),resume.getUser_id(),resume.getExpected_salary());
       resumeService.createResume(resume);
    }

}
