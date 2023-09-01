package com.example.demo.service;

import com.example.demo.dao.WhoIsInterestedDao;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.model.JobResume;
import com.example.demo.model.WhoInterested;
import com.example.demo.repository.WhoInterestedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WhoInterestedService {
    private final WhoInterestedRepository whoInterestedRepository;
    private final UserService userService;
    private final JobResumeService jobResumeService;

    public List<WhoInterestedDto> getInterestedApplicants() {
        log.info("Got all interested applicants!");
        List<WhoInterested> whoInteresteds = whoInterestedRepository.findAll();
        List<WhoInterestedDto> whoInterestedDtos = whoInteresteds.stream()
                .map(e -> WhoInterestedDto.builder()
                        .id(e.getApplicantId())
                        .applicant(userService.mapToUserDto(userService.getUserById(e.getApplicantId()).get()))
                        .date(e.getDate())
                        .job_resume(jobResumeService.mapToJobResumeDto(jobResumeService.getJobResumeById(e.getJobResumeId()).get()))
                        .build()
                ).toList();
        return whoInterestedDtos;
    }


}
