package com.example.demo.controller;

import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/resumes")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping()
    public List<Resume> getResumes1() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/getUserResume/{email}")
    public List<Resume> getUserByName(@PathVariable String email) {
        return resumeService.getUserResumes(email);
    }
    @GetMapping("/phone/{phone}")
    public User getUserByPhone(@PathVariable String phone) {
        return resumeService.getUserByPhone(phone);
    }
}
