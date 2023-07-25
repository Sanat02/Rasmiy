package com.example.demo.service;

import com.example.demo.dao.WhoIsInterestedDao;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.model.JobResume;
import com.example.demo.model.WhoInterested;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WhoInterestedService {
    private final WhoIsInterestedDao whoIsInterestedDao;
    private final UserService userService;
    private final JobResumeService jobResumeService;
    public List<WhoInterestedDto> getInterestedApplicants(int job_id){
        log.info("Got all interested applicants!");
        List<WhoInterested> whoInteresteds=whoIsInterestedDao.getInterestedApplicants(job_id);
        List<WhoInterestedDto> whoInterestedDtos=whoInteresteds.stream()
                .map(e->WhoInterestedDto.builder()
                        .id(e.getApplicant_id())
                        .applicant(userService.mapToUserDto(userService.getUserById(e.getApplicant_id()).get()))
                        .date(e.getDate())
                        .job_resume(jobResumeService.mapToJobResumeDto(jobResumeService.getJobResumeById(e.getJob_resume_id()).get()))
                        .build()
                ).toList();
        return whoInterestedDtos;
    }


}
