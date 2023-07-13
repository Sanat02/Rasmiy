package com.example.demo.controller;

import com.example.demo.dto.JobListDto;
import com.example.demo.model.JobList;
import com.example.demo.service.JobsListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobslist")
@RequiredArgsConstructor
public class JobsListController {
    private final JobsListService jobsListService;

   @GetMapping
    public List<JobListDto> getAllJobs(){
        return jobsListService.getAllJobs();
   }

    @GetMapping("/getByCategory/{category}")
    public List<JobList> getByCategory(@PathVariable String category){
        return jobsListService.getByCategory(category);
    }
}
