package com.example.demo.service;

import com.example.demo.dao.EducationDao;
import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.EducationDto;
import com.example.demo.dto.JobExperienceDto;
import com.example.demo.dto.JobListDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.enums.ContactType;
import com.example.demo.model.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeDao resumeDao;
    private final UserService userService;
    private final ContactsService contactsService;
    private final EducationService educationService;
    private final JobExperienceService jobExperienceService;


    //    public ResumeDto getResumeById(int resumeId) {
//        Resume resume = resumeDao.getResumeById(resumeId);
//        ResumeDto resumeDto = ResumeDto.builder()
//                .id(resume.getId())
//                .job(resume.getJob())
//                .expectedSalary(resume.getExpectedSalary())
//                .applicant(userService.getUserById(resume.getUserId()))
//                .contacts(contactsService.getContactsByResumeId(resume.getId()))
//                .build();
//        return resumeDto;
//    }
//
//    public List<ResumeDto> getResumeByUser(String email) {
//        List<ResumeDto> resumeDtos = getAllResumes().stream()
//                .filter(e -> e.getApplicant().getEmail() == email).collect(Collectors.toList());
//        return resumeDtos;
//    }
//
//

    public List<ResumeDto> getAllResumes() {
        List<Resume> resumes = resumeDao.getAllResumes();
        List<ResumeDto> resumeDtos = resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .expectedSalary(e.getExpectedSalary())
                        .job(e.getJob())
                        .applicant(userService.mapToUserDto(userService.getUserById(e.getUserId()).get()))
                        .education(educationService.getEducationByResumeId(e.getId()).get())
                        .jobExperience(jobExperienceService.getJobExperienceById(e.getId()).get())
                        .contacts(contactsService.getContactsDtoByResumeId(e.getId()))
                        .build())
                .collect(Collectors.toList());
        return resumeDtos;
    }


    public List<ResumeDto> getResumeByJob(String job) {
        List<Resume> resumes = resumeDao.getResumeByJob(job);
        List<ResumeDto> resumeDtos = resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .job(e.getJob())
                        .expectedSalary(e.getExpectedSalary())
                        .applicant(userService.mapToUserDto(userService.getUserById(e.getUserId()).get()))
                        .contacts(contactsService.getContactsDtoByResumeId(e.getId()))
                        .build()
                ).toList();
        return resumeDtos;
    }


    public void saveResume(ResumeDto resumeDto) {
        Optional<User> mayBeUser = userService.getUserById(resumeDto.getApplicant().getId());
        int userId;
        if (!mayBeUser.isPresent()) {
            userId = userService.save(resumeDto.getApplicant());
        } else {
            userId = userService.getUserByEmail(resumeDto.getApplicant().getEmail()).get().getId();
        }


        int resumeId = resumeDao.save(Resume.builder()
                .expectedSalary(resumeDto.getExpectedSalary())
                .job(resumeDto.getJob())
                .userId(userId)
                .build()
        );
        if (resumeDto.getJobExperience() != null) {
            jobExperienceService.saveJobExperience(resumeDto.getJobExperience(), resumeId);
        }
        if (resumeDto.getEducation() != null) {
            educationService.saveEducation(resumeDto.getEducation(), resumeId);
        }

    }

    public void updateResume(ResumeDto resumeDto) {
        Optional<Resume> resumeId = resumeDao.getResumeById(resumeDto.getId());
        if (resumeId.isPresent()) {
            Optional<User> mayBeUser = userService.getUserById(resumeDto.getId());
            int userId;
            if (!mayBeUser.isPresent()) {
                userId = userService.save(resumeDto.getApplicant());
            } else {
                userService.update(resumeDto.getApplicant());
                userId = resumeDto.getApplicant().getId();
            }
            resumeDao.update(Resume.builder()
                    .expectedSalary(resumeDto.getExpectedSalary())
                    .job(resumeDto.getJob())
                    .userId(userId)
                    .id(resumeDto.getId())
                    .build());
            if (resumeDto.getEducation() != null) {
                Optional<EducationDto> educationDto = educationService.getEducationByResumeId(resumeDto.getId());
                if (!educationDto.isPresent()) {
                    educationService.saveEducation(resumeDto.getEducation(), resumeDto.getId());
                } else {

                    educationService.updateEducation(resumeDto.getEducation(),resumeDto.getEducation().getId());
                }
            }

            if (resumeDto.getJobExperience() != null) {
                Optional<JobExperienceDto> jobExperience = jobExperienceService.getJobExperienceById(resumeDto.getId());
                if (!jobExperience.isPresent()) {
                    jobExperienceService.saveJobExperience(resumeDto.getJobExperience(), resumeDto.getId());
                } else {
                    jobExperienceService.updateEducation(resumeDto.getJobExperience(),resumeDto.getJobExperience().getId());
                }
            }
        } else {
            throw new IllegalArgumentException("Job Resume with ID " + resumeDto.getId() + " not found.");
        }
    }

    public void deleteResume(int resumeId) {
        resumeDao.delete(resumeId);
    }
}
