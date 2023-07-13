package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

   @GetMapping
    public List<ResumeDto> getAllResumes(){
       return resumeService.getAllResumes();
   }

}
