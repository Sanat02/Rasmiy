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
    public List<ResumeDto> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/id/{id}")
    public ResumeDto getAllResumes(@PathVariable int id) {
        return resumeService.getResumeById(id);
    }

    @PostMapping
    public void createResume(@RequestBody Resume resume) {
        log.info("Resume created id:" + resume.getId());
        resumeService.createResume(resume);
    }

    @DeleteMapping("/{id}")
    public void deleteResumeById(@PathVariable("id") int id) {
        log.info("Deleted resume with id: " + id);
        resumeService.deleteResume(id);
    }


    @PutMapping("/{id}")
    public Resume updateResume(@PathVariable int id, @RequestBody Resume updatedResume) {
        updatedResume.setId(id);
        log.info("Updated resume with id:" + id);
        resumeService.updateResume(updatedResume, id);
        return updatedResume;
    }


}
