package com.example.demo.service;


import com.example.demo.dto.JobExperienceDto;
import com.example.demo.model.JobExperience;
import com.example.demo.repository.JobExperienceRepository;
import com.example.demo.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobExperienceService {
    private final ResumeRepository resumeRepository;
    private final JobExperienceRepository jobExperienceRepository;

    public Optional<JobExperienceDto> getJobExperienceById(int id) {
        Optional<JobExperience> jobExperienceOptional=jobExperienceRepository.findJobExperienceByResumeId(id);
        if (jobExperienceOptional.isPresent()) {
            JobExperience jobExperience = jobExperienceOptional.get();
            return Optional.ofNullable(JobExperienceDto.builder()
                    .position(jobExperience.getPosition())
                    .startDate(jobExperience.getStartDate())
                    .endDate(jobExperience.getEndDate())
                    .build());
        } else {
            return Optional.empty();
        }
    }

    public void saveJobExperience(JobExperienceDto jobExperience, int resumeId) {
        JobExperience savedJobExperience=jobExperienceRepository.save(JobExperience.builder()
                .endDate(jobExperience.getEndDate())
                .startDate(jobExperience.getStartDate())
                .position(jobExperience.getPosition())
                .resume(resumeRepository.findById(resumeId).get())
                .build());
        log.info("JobExperience saved with id:" + savedJobExperience.getId());
    }



}
