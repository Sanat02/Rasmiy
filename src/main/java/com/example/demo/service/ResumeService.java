package com.example.demo.service;

import com.example.demo.dao.EducationDao;
import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.JobListDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.enums.ContactType;
import com.example.demo.model.Education;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class ResumeService {

    private final ResumeDao resumeDao;
    private final UserService userService;
    private final ContactsService contactsService;
    private final EducationService educationService;
    private final JobExperienceService jobExperienceService;

    //BONUS
    public ResumeDto getResumeById(int resumeId) {
        Resume resume = resumeDao.getResumeById(resumeId);
        ResumeDto resumeDto = ResumeDto.builder()
                .id(resume.getId())
                .job(resume.getJob())
                .expectedSalary(resume.getExpectedSalary())
                .applicant(userService.getUserById(resume.getUserId()))
                .contacts(contactsService.getContactsById(resume.getId()))
                .build();
        return resumeDto;
    }

    public List<ResumeDto> getResumeByUser(String email) {
        List<ResumeDto> resumeDtos = getAllResumes().stream()
                .filter(e -> e.getApplicant().getEmail() == email).collect(Collectors.toList());
        return resumeDtos;
    }


    public void createResume(Resume resume) {
        resumeDao.addResume(resume);

    }

    public void updateResume(Resume resume) {
        resumeDao.updateResume(resume);
    }

    public void deleteResume(int resumeId) {
        resumeDao.deleteResumeById(resumeId);
    }


    public List<ResumeDto> getAllResumes() {
        List<Resume> resumes = resumeDao.getAllResumes();
        List<ResumeDto> resumeDtos = resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .expectedSalary(e.getExpectedSalary())
                        .job(e.getJob())
                        .applicant(userService.getUserById(e.getUserId()))
                        .education(educationService.getEducationById(e.getId()))
                        .jobExperience(jobExperienceService.getJobExperienceById(e.getId()))
                        .contacts(contactsService.getContactsById(e.getId()))
                        .build())
                .collect(Collectors.toList());
        return resumeDtos;
    }


    public ResumeDto getResumeByJob(String job) {
        Resume resume = resumeDao.getResumeByJob(job);
        ResumeDto resumeDto = ResumeDto.builder()
                .id(resume.getId())
                .job(resume.getJob())
                .expectedSalary(resume.getExpectedSalary())
                .applicant(userService.getUserById(resume.getUserId()))
                .contacts(contactsService.getContactsById(resume.getId()))
                .build();
        return resumeDto;
    }



}
