package com.example.demo.service;

import com.example.demo.dao.JobExperienceDao;
import com.example.demo.dto.EducationDto;
import com.example.demo.dto.JobExperienceDto;
import com.example.demo.model.JobExperience;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobExperienceService {
    private final JobExperienceDao jobExperienceDao;

    public Optional<JobExperienceDto> getJobExperienceById(int id) {
        JobExperience jobExperience = jobExperienceDao.getExperienceById(id);

        Optional<JobExperienceDto> jobExperienceDto = Optional.ofNullable(JobExperienceDto.builder()
                .position(jobExperience.getPosition())
                .endDate(jobExperience.getEndDate())
                .startDate(jobExperience.getStartDate())
                .build());
        return jobExperienceDto;
    }

    public void saveJobExperience(JobExperienceDto jobExperience, int resumeId) {
        jobExperienceDao.save(JobExperience.builder()
                .endDate(jobExperience.getStartDate())
                .startDate(jobExperience.getStartDate())
                .position(jobExperience.getPosition())
                .resumeId(resumeId)
                .build());
    }


    public void updateEducation(JobExperienceDto jobExperience, int id) {
        jobExperienceDao.update(JobExperience.builder()
                .endDate(jobExperience.getStartDate())
                .startDate(jobExperience.getStartDate())
                .position(jobExperience.getPosition())
                .resumeId(id)
                .build());
    }
}
