package com.example.demo.service;


import com.example.demo.dto.WhoInterestedDto;
import com.example.demo.model.WhoInterested;
import com.example.demo.repository.WhoInterestedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WhoInterestedService {
    private final WhoInterestedRepository whoInterestedRepository;
    private final UserService userService;
    private final JobResumeService jobResumeService;

    public void saveClick(WhoInterestedDto whoInterestedDto) {
        WhoInterested whoInterested = WhoInterested.builder()
                .applicantId(whoInterestedDto.getApplicant().getId())
                .date(LocalDate.now())
                .jobResumeId(whoInterestedDto.getJob_resume().getId())
                .build();
        whoInterestedRepository.save(whoInterested);

    }

    public boolean isClicked(WhoInterestedDto whoInterestedDto) {
        WhoInterested whoInterested = whoInterestedRepository.findWhoInterestedByApplicantIdAndAndJobResumeId(
                whoInterestedDto.getApplicant().getId(), whoInterestedDto.getJob_resume().getId()).orElse(null);
        if (whoInterested == null) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteClick(WhoInterestedDto whoInterestedDto) {
        WhoInterested whoInterested = whoInterestedRepository.findWhoInterestedByApplicantIdAndAndJobResumeId(
                whoInterestedDto.getApplicant().getId(), whoInterestedDto.getJob_resume().getId()).orElse(null);
        if(whoInterested!=null){
            whoInterestedRepository.deleteById(whoInterested.getId());
        }
    }


}
