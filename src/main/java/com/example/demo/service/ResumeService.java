package com.example.demo.service;


import com.example.demo.dto.*;

import com.example.demo.model.*;


import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;
import com.example.demo.repository.ResumeRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserService userService;
    private final ContactsService contactsService;
    private final EducationService educationService;
    private final JobExperienceService jobExperienceService;
    private final CategoryService categoryService;


    public Page<ResumeDto> getAllResumes(int start, int end, String sortField) {
        log.info("Got all users");
        Pageable pageable;
        if (sortField.equalsIgnoreCase("clicks")) {
            pageable = PageRequest.of(start, end);
        } else {
            Sort sort = Sort.by(Sort.Order.desc("resumeDate"));
            pageable = PageRequest.of(start, end, sort);
        }

        Page<Resume> resumes = resumeRepository.findAll(pageable);

        Page<ResumeDto> resumeDtos = resumes.map(e -> {
            return ResumeDto.builder()
                    .id(e.getId())
                    .expectedSalary(e.getExpectedSalary())
                    .job(e.getJob())
                    .applicant(userService.mapToUserDto(userService.getUserById(e.getUser().getId()).orElse(null)))
                    .education(educationService.getEducationByResumeId(e.getId()).orElse(null))
                    .jobExperience(jobExperienceService.getJobExperienceById(e.getId()).orElse(null))
                    .contacts(contactsService.getContactsDtoByResumeId(e.getId()))
                    .date(e.getResumeDate())
                    .build();
        });

        return resumeDtos;
    }


    public List<ResumeDto> getResumeByJob(String job) {
        log.info("Got job:" + job);
        List<Resume> resumes = resumeRepository.findByJob(job);
        List<ResumeDto> resumeDtos = resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .job(e.getJob())
                        .expectedSalary(e.getExpectedSalary())
                        .applicant(userService.mapToUserDto(userService.getUserById(e.getUser().getId()).get()))
                        .date(e.getResumeDate())
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

        Resume savedResume = resumeRepository.save(Resume.builder()
                .expectedSalary(resumeDto.getExpectedSalary())
                .job(resumeDto.getJob())
                .user(userService.getUserById(userId).get())
                .category(categoryService.getCategoryById(Integer.parseInt(resumeDto.getCategory())).get())
                .resumeDate(new Date())
                .build());


        int resumeId = savedResume.getId();

        log.info("The resume with ID " + resumeId + " is saved!");
        return resumeId;

    }

    public void updateResume(ResumeDto resumeDto) {
        Optional<Resume> resume = resumeRepository.findById(resumeDto.getId());
        if (resume.isPresent()) {
            Optional<User> mayBeUser = userService.getUserById(resumeDto.getId());
            int userId;
            if (!mayBeUser.isPresent()) {
                userId = userService.save(resumeDto.getApplicant());
            } else {
                userService.update(resumeDto.getApplicant());
                userId = resumeDto.getApplicant().getId();
            }
            Resume existingResume = resume.get();
            existingResume.setExpectedSalary(resumeDto.getExpectedSalary());
            existingResume.setJob(resumeDto.getJob());
            existingResume.setUser(userService.getUserById(userId).get());
            resumeRepository.save(existingResume);

        } else {
            log.error("The resume id does not exits:" + resume.get().getId());
            throw new IllegalArgumentException("Job Resume with ID " + resumeDto.getId() + " not found.");

        }
    }

    public void deleteResume(int resumeId) {
        log.info("Deleted resume with id:" + resumeId);
        resumeRepository.deleteById(resumeId);
    }

    public List<ResumeDto> getResumesByUserId(int userId) {
        List<Resume> resumes = resumeRepository.findByUserId(userId);
        return resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .expectedSalary(e.getExpectedSalary())
                        .job(e.getJob())
                        .date(e.getResumeDate())
                        .build())
                .collect(Collectors.toList());

    }

    public ResumeDto getResumeById(int resumeId) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);

        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Category category = resume.getCategory();

            if (category != null) {
                return ResumeDto.builder()
                        .id(resumeId)
                        .job(resume.getJob())
                        .category(categoryService.mapToCategoryDto(category).getName())
                        .expectedSalary(resume.getExpectedSalary())
                        .education(educationService.getEducationByResumeId(resumeId).orElse(null))
                        .jobExperience(jobExperienceService.getJobExperienceById(resumeId).orElse(null))
                        .applicant(userService.mapToUserDto(userService.getUserById(resume.getUser().getId()).orElse(null)))
                        .contacts(contactsService.getContactsDtoByResumeId(resumeId))
                        .date(resume.getResumeDate())
                        .build();
            } else {
                log.error("Category is null for Resume ID: " + resumeId);
                return null;
            }
        } else {
            log.error("Resume not found for ID: " + resumeId);
            return null;
        }
    }
}
