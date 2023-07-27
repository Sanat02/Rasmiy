package com.example.demo.controller;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.JobResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobresume")
@RequiredArgsConstructor
public class JobResumeController {
    private final JobResumeService jobResumeService;


    @PostMapping
    public ResponseEntity<String> createJobResume(@Valid @RequestBody JobResumeDto jobResumeDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            });
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        jobResumeDto.getUser().setAccountType(AccountType.EMPLOYER);
        jobResumeService.saveJobResume(jobResumeDto);
        return ResponseEntity.ok("job resume was created successfully");
    }


    @PutMapping
    public void updateJobResume(@RequestBody JobResumeDto jobResumeDto) {
        jobResumeService.updateJobResume(jobResumeDto);
    }


    @DeleteMapping("/id/{id}")
    public void deleteJobResume(@PathVariable int id) {
        jobResumeService.deleteJobResume(id);
    }


    @GetMapping
    public List<JobResumeDto> getAllJobResumes() {
        return jobResumeService.gettAllJobResumes();
    }


    @GetMapping("/category/{categoryName}")
    public List<JobResumeDto> getJobResumesByCategoryName(@PathVariable String categoryName) {
        return jobResumeService.getJobResumeByCategoryName(categoryName);
    }


}
