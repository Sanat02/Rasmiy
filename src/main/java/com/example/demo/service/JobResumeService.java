package com.example.demo.service;

import com.example.demo.dao.JobResumeDao;
import com.example.demo.dto.JobResumeDto;
import com.example.demo.model.Category;
import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import static java.util.stream.Collectors.toList;
@Slf4j
@Service
@RequiredArgsConstructor
public class JobResumeService {

    private final JobResumeDao jobResumeDao;
    private final UserService userService;
    private final CategoryService categoryService;


    public List<JobResumeDto> gettAllJobResumes() {

        List<JobResume> jobResumes = jobResumeDao.getAllJobResumes();
        return jobResumes.stream()
                .map(e -> JobResumeDto.builder()
                        .id(e.getId())
                        .jobDescription(e.getJobDescription())
                        .jobTitle(e.getJobTitle())
                        .salary(e.getSalary())
                        .user(userService.mapToUserDto(userService.getUserById(e.getUserId()).get()))
                        .category(categoryService.mapToCategoryDto(categoryService.getCategoryById(e.getCategoryId()).get()))
                        .experience(e.getExperience())
                        .build()
                ).toList();

    }

    public List<JobResumeDto> getJobResumeByCategoryName(String categoryName) {
        log.info("Got job with category:"+categoryName);
        List<JobResumeDto> jobResumeDtos = gettAllJobResumes().
                stream().
                filter(e -> e.getCategory().getName().equalsIgnoreCase(categoryName)).
                collect(toList());
        return jobResumeDtos;

    }

    public int saveJobResume(JobResumeDto jobResumeDto) {
        Optional<User> mayBeUser = userService.getUserById(jobResumeDto.getUser().getId());
        int userId;
        if (!mayBeUser.isPresent()) {
            userId = userService.save(jobResumeDto.getUser());
        } else {
            userId = userService.getUserByEmail(jobResumeDto.getUser().getEmail()).get().getId();
        }

//        Optional<Category> mayBeCategory = categoryService.getCategoryById(jobResumeDto.getCategory().getId());
//        int categoryId;
//        if (!mayBeCategory.isPresent()) {
//            categoryId = categoryService.save(jobResumeDto.getCategory());
//        } else {
//            categoryId = categoryService.getCategoryById(jobResumeDto.getCategory().getId()).get().getId();
//        }

        int jobResumeId=jobResumeDao.save(JobResume.builder()
                .jobTitle(jobResumeDto.getJobTitle())
                .jobDescription(jobResumeDto.getJobDescription())
                .salary(jobResumeDto.getSalary())
                .experience(jobResumeDto.getExperience())
                .userId(userId)
               .categoryId(2)
                .build());
        log.info("Job resume saved with id:"+jobResumeId);
        return jobResumeId;

    }

    public Optional<JobResume> getJobResumeById(int id) {
        return jobResumeDao.getJobResumeById(id);
    }

    public void updateJobResume(JobResumeDto jobResumeDto) {
        //проверяем сущестсвует ли id jobresume
        Optional<JobResume> mayBeJobResume = getJobResumeById(jobResumeDto.getId());

        //если да
        if (mayBeJobResume.isPresent()) {
            Optional<User> mayBeUser = userService.getUserById(jobResumeDto.getUser().getId());
            int userId;
            if (!mayBeUser.isPresent()) {
                userId = userService.save(jobResumeDto.getUser());

            } else {
                userService.update(jobResumeDto.getUser());
                userId = jobResumeDto.getUser().getId();

            }

            Optional<Category> mayBeCategory = categoryService.getCategoryById(jobResumeDto.getCategory().getId());
            int categoryId;
            if (!mayBeCategory.isPresent()) {
                categoryId = categoryService.save(jobResumeDto.getCategory());
            } else {
                categoryService.update(jobResumeDto.getCategory());
                categoryId = jobResumeDto.getCategory().getId();

            }

            JobResume jobResume = JobResume.builder()
                    .jobTitle(jobResumeDto.getJobTitle())
                    .jobDescription(jobResumeDto.getJobDescription())
                    .salary(jobResumeDto.getSalary())
                    .experience(jobResumeDto.getSalary())
                    .userId(userId)
                    .categoryId(categoryId)
                    .id(jobResumeDto.getId())
                    .build();
            jobResumeDao.update(jobResume);

            //если нет
        } else {
            throw new IllegalArgumentException("Job Resume with ID " + jobResumeDto.getId() + " not found.");
        }
    }


    public void deleteJobResume(int id) {
        jobResumeDao.delete(id);
    }

    public JobResumeDto mapToJobResumeDto(JobResume jobResume) {
        return JobResumeDto.builder()
                .id(jobResume.getId())
                .jobDescription(jobResume.getJobDescription())
                .jobTitle(jobResume.getJobTitle())
                .experience(jobResume.getExperience())
                .user(userService.mapToUserDto(userService.getUserById(jobResume.getUserId()).get()))
                .salary(jobResume.getSalary())
                .category(categoryService.mapToCategoryDto(categoryService.getCategoryById(jobResume.getCategoryId()).get()))
                .build();

    }
}
