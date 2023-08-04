package com.example.demo.service;

import com.example.demo.dao.EducationDao;
import com.example.demo.dto.EducationDto;
import com.example.demo.model.Education;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class EducationService {
    private final EducationDao educationDao;

    public Optional<EducationDto> getEducationByResumeId(int id) {
        Optional<Education> educationOptional = educationDao.getEducationByResumeId(id);

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
        int id = educationDao.save(Education.builder()
                .degree(education.getDegree())
                .endDate(education.getEndDate())
                .startDate(education.getStartDate())
                .resumeId(resumeId)
                .institutionName(education.getInstitutionName())
                .build()
        );
        log.info("Education saved with id:" + id);
    }

    public void updateEducation(EducationDto education, int resumId) {
        educationDao.update(Education.builder()
                .degree(education.getDegree())
                .endDate(education.getEndDate())
                .startDate(education.getStartDate())
                .resumeId(resumId)
                .institutionName(education.getInstitutionName())
                .build());
        log.info("Education updated with id:" + education.getId());
    }
}
