package com.example.demo.service;


import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.*;

import com.example.demo.model.*;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeService {

    private final ResumeDao resumeDao;
    private final UserService userService;
    private final ContactsService contactsService;
    private final EducationService educationService;
    private final JobExperienceService jobExperienceService;
    private final CategoryService categoryService;

    public Page<ResumeDto> getAllResumes(int start,int end) {
        log.info("Got all users");
        List<Resume> resumes = resumeDao.getAllResumes();
        List<ResumeDto> resumeDtos = resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .expectedSalary(e.getExpectedSalary())
                        .job(e.getJob())
                        .applicant(userService.mapToUserDto(userService.getUserById(e.getUserId()).orElse(null)))
                        .education(educationService.getEducationByResumeId(e.getId()).orElse(null))
                         .jobExperience(jobExperienceService.getJobExperienceById(e.getId()).orElse(null))
                         .contacts(contactsService.getContactsDtoByResumeId(e.getId()))
                        .build())
                .collect(Collectors.toList());
        var page=toPage(resumeDtos, PageRequest.of(start,end));
        return page;
    }


    public List<ResumeDto> getResumeByJob(String job) {
        log.info("Got job:" + job);
        List<Resume> resumes = resumeDao.getResumeByJob(job);
        List<ResumeDto> resumeDtos = resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .job(e.getJob())
                        .expectedSalary(e.getExpectedSalary())
                        .applicant(userService.mapToUserDto(userService.getUserById(e.getUserId()).get()))
                        // .contacts(contactsService.getContactsDtoByResumeId(e.getId()))
                        .build()
                ).toList();
        return resumeDtos;
    }


    public int saveResume(ResumeDto resumeDto, Authentication auth) {
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
                .categoryId(Integer.parseInt(resumeDto.getCategory()))
                .build()
        );
        log.info("The resume:" + resumeId + " is saved!");
        return resumeId;

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
        } else {
            log.error("The resume id does not exits:" + resumeId.get().getId());
            throw new IllegalArgumentException("Job Resume with ID " + resumeDto.getId() + " not found.");

        }
    }

    public void deleteResume(int resumeId) {
        log.info("Deleted resume with id:" + resumeId);
        resumeDao.delete(resumeId);
    }

    public List<ResumeDto> getResumesByUserId(int userId) {
        List<Resume> resumes = resumeDao.getResumesByUserId(userId);
        return resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .expectedSalary(e.getExpectedSalary())
                        .job(e.getJob())
                        .build())
                .collect(Collectors.toList());

    }

    public ResumeDto getResumeById(int resumeId) {
        Resume resume = resumeDao.getResumeById(resumeId).orElse(null);
        return ResumeDto.builder()
                .id(resumeId)
                .job(resume.getJob())
                .category(categoryService.mapToCategoryDto(categoryService.getCategoryById(resume.getCategoryId()).get()).getName())
                .expectedSalary(resume.getExpectedSalary())
                .education(educationService.getEducationByResumeId(resumeId).orElse(null))
                .jobExperience(jobExperienceService.getJobExperienceById(resumeId).orElse(null))
                .applicant(userService.mapToUserDto(userService.getUserById(resume.getUserId()).get()))
                .build();
    }
    private Page<ResumeDto> toPage(List<ResumeDto> list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ?
                list.size() :
                pageable.getOffset() + pageable.getPageSize());
        List<ResumeDto> subList = list.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, list.size());
    }
}
