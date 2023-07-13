package com.example.demo.service;

import com.example.demo.dao.WhoIsInterestedDao;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import com.example.demo.model.WhoInterested;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WhoInterestedService {
    private final WhoIsInterestedDao whoIsInterestedDao;
    private final UserService userService;
    private final JobResumeService jobResumeService;



    public List<WhoInterestedDto> getAllInterested() {
        List<WhoInterested> interesteds = whoIsInterestedDao.getAllInterestedList();
        List<WhoInterestedDto> interestedDtos = interesteds.stream()
                .map(e -> WhoInterestedDto.builder()
                        .id(e.getId())
                        .date(e.getDate())
                        .applicant(userService.getUserById(e.getApplicant_id()))
                        .job_resume(jobResumeService.getJobResumeById(e.getJob_resume_id()))
                        .build()
                ).toList();
        return interestedDtos;
    }

    public List<JobResumeDto> getInterestedJobsByUser(String email){
        List<WhoInterestedDto> interestedDtos=getAllInterested();
        List<JobResumeDto> jobResumeDtos=interestedDtos.stream()
                .filter(e->e.getApplicant().getEmail().equals(email)).map(e->e.getJob_resume()).collect(Collectors.toList());
        return jobResumeDtos;
    }


}
