package com.example.demo.controller;

import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.service.WhoInterestedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/whointerested")
@RequiredArgsConstructor
public class WhoInterestedController {
    private final WhoInterestedService whoInterestedService;


   //get who is interested
    @GetMapping("/jobId/{jobId}")
    public List<WhoInterestedDto> getInterestedApplicants(@PathVariable int jobId) {
        return whoInterestedService.getInterestedApplicants(jobId);
    }
    //TODO сделать автоинкремент
//    @PostMapping("/{user_id}/apply/{job_resume_id}")
//    public void applyForJobResume(@PathVariable int user_id, @PathVariable int job_resume_id,
//                                  @RequestBody WhoInterested whoInterested) {
//        whoInterested.setApplicant_id(user_id);
//        whoInterested.setJob_resume_id(job_resume_id);
//        whoInterested.setDate(LocalDateTime.now());
//        whoInterestedService.createInterested(whoInterested);
//    }
}
