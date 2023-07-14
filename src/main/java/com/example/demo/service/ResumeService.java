package com.example.demo.service;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.JobListDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.enums.ContactType;
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

    //BONUS
    public ResumeDto getResumeById(int resumeId) {
        Resume resume = resumeDao.getResumeById(resumeId);
        ResumeDto resumeDto = ResumeDto.builder()
                .id(resume.getId())
                .job(resume.getJob())
                .expectedSalary(resume.getExpected_salary())
                .applicant(userService.getUserById(resume.getUser_id()))
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

    public Resume updateResume(Resume resume) {
        // TODO: Реализовать обновление информации в резюме
        return null;
    }

    public void deleteResume(int resumeId) {
        // TODO: Реализовать удаление резюме по идентификатору
    }
    //Bonus окончен

    public List<ResumeDto> getAllResumes() {
        List<Resume> resumes = resumeDao.getAllResumes();
        List<ResumeDto> resumeDtos = resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .expectedSalary(e.getExpected_salary())
                        .job(e.getJob())
                        .applicant(userService.getUserById(e.getUser_id()))
                        .contacts(contactsService.getContactsById(e.getId()))
                        .build()
                ).toList();
        return resumeDtos;
    }




}
