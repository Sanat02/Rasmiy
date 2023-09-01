package com.example.demo.service;

import com.example.demo.dto.JobResumeDto;
import com.example.demo.model.Category;
import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import com.example.demo.repository.JobResumeRepository;
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
    private final UserService userService;
    private final CategoryService categoryService;
    private final JobResumeRepository jobResumeRepository;


    public List<JobResumeDto> gettAllJobResumes() {
        List<JobResume> jobResumes = jobResumeRepository.findAll();
        return jobResumes.stream()
                .map(e -> JobResumeDto.builder()
                        .id(e.getId())
                        .jobDescription(e.getJobDescription())
                        .jobTitle(e.getJobTitle())
                        .salary(e.getSalary())
                        .user(userService.mapToUserDto(userService.getUserById(e.getUserId()).get()))
                        .category(categoryService.mapToCategoryDto(categoryService.getCategoryById(e.getCategory().getId()).get()).getName())
                        .experience(e.getExperience())
                        .build()
                ).toList();

    }

    public List<JobResumeDto> getJobResumeByCategoryName(String categoryName) {
        log.info("Got job with category:" + categoryName);
        List<JobResumeDto> jobResumeDtos = gettAllJobResumes().
                stream().
                filter(e -> e.getCategory().equalsIgnoreCase(categoryName)).
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
        String category = jobResumeDto.getCategory();
        int categoryId = Integer.parseInt(category);

        JobResume savedJobResume = jobResumeRepository.save(JobResume.builder()
                .jobTitle(jobResumeDto.getJobTitle())
                .jobDescription(jobResumeDto.getJobDescription())
                .salary(jobResumeDto.getSalary())
                .experience(jobResumeDto.getExperience())
                .userId(userId)
                .category(categoryService.getCategoryById(categoryId).get())
                .build());


        log.info("Job resume saved with id:" + savedJobResume.getId());
        return savedJobResume.getId();

    }

    public Optional<JobResume> getJobResumeById(int id) {
        return jobResumeRepository.findById(id);
    }

    public void updateJobResume(JobResumeDto jobResumeDto) {
        String category = jobResumeDto.getCategory();
        int categoryId = Integer.parseInt(category);

        Category categoryEntity = categoryService.getCategoryById(categoryId).orElse(null);

        if (categoryEntity != null) {
            jobResumeRepository.updateJobResume(
                    jobResumeDto.getId(),
                    jobResumeDto.getJobTitle(),
                    jobResumeDto.getJobDescription(),
                    jobResumeDto.getExperience(),
                    Double.valueOf(jobResumeDto.getSalary()),
                    categoryEntity
            );
        } else {
            System.out.println("Category entity is null");
        }
    }


    public void deleteJobResume(int id) {
        jobResumeRepository.deleteById(id);
    }

    public JobResumeDto mapToJobResumeDto(JobResume jobResume) {
        return JobResumeDto.builder()
                .id(jobResume.getId())
                .jobDescription(jobResume.getJobDescription())
                .jobTitle(jobResume.getJobTitle())
                .experience(jobResume.getExperience())
                .user(userService.mapToUserDto(userService.getUserById(jobResume.getUserId()).get()))
                .salary(jobResume.getSalary())
                .category(categoryService.mapToCategoryDto(categoryService.getCategoryById(jobResume.getCategory().getId()).get()).getName())
                .build();

    }

    public List<JobResumeDto> getJobResumesByUserId(int userId) {
        List<JobResume> jobResume = jobResumeRepository.findJobResumesByUserId(userId);
        List<JobResumeDto> jobResumeDtos = jobResume.stream().
                map(e -> JobResumeDto.builder()
                        .id(e.getId())
                        .jobTitle(e.getJobTitle())
                        .user(userService.mapToUserDto(userService.getUserById(userId).get()))
                        .jobDescription(e.getJobDescription())
                        .experience(e.getExperience())
                        .salary(e.getSalary())
                        .build()
                ).collect(toList());
        return jobResumeDtos;
    }
}
