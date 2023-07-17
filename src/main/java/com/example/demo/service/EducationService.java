package com.example.demo.service;

import com.example.demo.dao.EducationDao;
import com.example.demo.dto.EducationDto;
import com.example.demo.model.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EducationService {
    private final EducationDao educationDao;

    public EducationDto getEducationByResumeId(int id) {
        Education education = educationDao.getEducationByResumeId(id);
        if (education == null) {
            return null;
        }
        EducationDto educationDto = EducationDto.builder()
                .institutionName(education.getInstitutionName())
                .degree(education.getDegree())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .build();
        return educationDto;

    }
}
