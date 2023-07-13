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
                .jobExperience(resume.getJob_experience())
                .expectedSalary(resume.getExpected_salary())
                .applicant(userService.getUserById(resume.getUser_id()))
                .contacts(contactsService.getContactsById(resume.getId()))
                .education(resume.getEducation())
                .build();
        return resumeDto;
    }

    public List<ResumeDto> getResumeByUser(String email){
        List<ResumeDto> resumeDtos=getAllResumes().stream()
                .filter(e->e.getApplicant().getEmail()==email).collect(Collectors.toList());
        return resumeDtos;
    }


    public Resume createResume(Resume resume) {
        // TODO: Реализовать создание нового резюме
        return null;
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
                        .jobExperience(e.getJob_experience())
                        .expectedSalary(e.getExpected_salary())
                        .job(e.getJob())
                        .education(e.getEducation())
                        .applicant(userService.getUserById(e.getId()))
                        .contacts(contactsService.getContactsById(e.getId()))
                        .build()
                ).toList();
        return resumeDtos;
    }

    public User getUserByPhone(String phone){
        List<ResumeDto> resumeDtos = getAllResumes();
        return resumeDtos.stream()
                .filter(resumeDto -> resumeDto.getContacts().stream()
                        .anyMatch(contact -> contact.getType().equals(ContactType.PHONE) && contact.getValue().equals(phone)))
                .map(ResumeDto::getApplicant)
                .findFirst()
                .orElse(null);

    }

}
