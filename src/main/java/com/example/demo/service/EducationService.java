package com.example.demo.service;

import com.example.demo.dao.EducationDao;
import com.example.demo.dto.EducationDto;
import com.example.demo.model.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class EducationService {
    private final EducationDao educationDao;

    //    public Optional<EducationDto> getEducationByResumeId(int id) {
//        Optional<Education> education = educationDao.getEducationByResumeId(id);
//        return Optional.ofNullable(EducationDto.builder()
//                .institutionName(education.get().getInstitutionName())
//                .degree(education.get().getDegree())
//                .startDate(education.get().getStartDate())
//                .endDate(education.get().getEndDate())
//                .build());
//
//    }
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
            // Return an empty Optional if there is no education record for the given resume ID
            return Optional.empty();
        }
    }


    public void saveEducation(EducationDto education, int resumeId) {
        educationDao.save(Education.builder()
                .degree(education.getDegree())
                .endDate(education.getEndDate())
                .startDate(education.getStartDate())
                .resumeId(resumeId)
                .institutionName(education.getInstitutionName())
                .build()
        );
    }

    public void updateEducation(EducationDto education, int resumId) {
        educationDao.update(Education.builder()
                .degree(education.getDegree())
                .endDate(education.getEndDate())
                .startDate(education.getStartDate())
                .resumeId(resumId)
                .institutionName(education.getInstitutionName())
                .build());
    }
}
