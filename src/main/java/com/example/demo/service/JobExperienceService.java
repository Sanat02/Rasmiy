package com.example.demo.service;

import com.example.demo.dao.JobExperienceDao;
import com.example.demo.dto.JobExperienceDto;
import com.example.demo.model.JobExperience;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobExperienceService {
    private final JobExperienceDao jobExperienceDao;

    public JobExperienceDto getJobExperienceById(int id){
        JobExperience jobExperience=jobExperienceDao.getExperienceById(id);
        if (jobExperience == null) {
            return null;
        }
        JobExperienceDto jobExperienceDto= JobExperienceDto.builder()
                .position(jobExperience.getPosition())
                .end_date(jobExperience.getEnd_date())
                .start_date(jobExperience.getStart_date())
                .build();
        return jobExperienceDto;
    }
}
