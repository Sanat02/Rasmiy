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

    public EducationDto getEducationById(int id) {
        Education education = educationDao.getEducationById(id);
        if (education == null) {
            return null;
        }
        EducationDto educationDto = EducationDto.builder()
                .institution_name(education.getInstitution_name())
                .degree(education.getDegree())
                .start_date(education.getStart_date())
                .end_date(education.getEnd_date())
                .build();
        return educationDto;

    }
}
