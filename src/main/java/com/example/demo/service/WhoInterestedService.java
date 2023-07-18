package com.example.demo.service;

import com.example.demo.dao.WhoIsInterestedDao;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.model.WhoInterested;
import lombok.RequiredArgsConstructor;
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


    public List<WhoInterestedDto> getInterestedApplicants(int job_id){
        List<WhoInterested> whoInteresteds=whoIsInterestedDao.getInterestedApplicants(job_id);
        List<WhoInterestedDto> whoInterestedDtos=whoInteresteds.stream()
                .map(e->WhoInterestedDto.builder()
                        .id(e.getApplicant_id())
                        .applicant(userService.getUserById(e.getApplicant_id()))
                        .date(e.getDate())
                        .job_resume(jobResumeService.getJobResumeById(e.getJob_resume_id()))
                        .build()
                ).toList();
        return whoInterestedDtos;
    }
    public void createInterested(WhoInterested whoInterested){
        whoIsInterestedDao.createInterested(whoInterested);
    }


}
