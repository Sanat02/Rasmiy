package com.example.demo.service;


import com.example.demo.dto.EducationDto;
import com.example.demo.model.Education;
import com.example.demo.repository.EducationRepository;
import com.example.demo.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class EducationService {

    private final EducationRepository educationRepository;
    private final ResumeRepository resumeRepository;

    public Optional<EducationDto> getEducationByResumeId(int id) {
        Optional<Education> educationOptional=educationRepository.findByResumeId(id);

        if (educationOptional.isPresent()) {
            Education education = educationOptional.get();
            return Optional.ofNullable(EducationDto.builder()
                    .institutionName(education.getInstitutionName())
                    .degree(education.getDegree())
                    .startDate(education.getStartDate())
                    .endDate(education.getEndDate())
                    .build());
        } else {
            return Optional.empty();
        }
    }


    public void saveEducation(EducationDto education, int resumeId) {
        Education savedEducation=educationRepository.save(Education.builder()
                .degree(education.getDegree())
                .endDate(education.getEndDate())
                .startDate(education.getStartDate())
                .resume(resumeRepository.findById(resumeId).get())
                .institutionName(education.getInstitutionName())
                .build());
        log.info("Education saved with id:" + savedEducation.getId());
    }


}
