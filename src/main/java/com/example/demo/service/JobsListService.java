package com.example.demo.service;


import com.example.demo.dto.JobListDto;
import com.example.demo.model.JobList;
import com.example.demo.repository.JobListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
@Slf4j
public class JobsListService {
    private final JobListRepository jobListRepository;
    private final UserService userService;
    private final CategoryService categoryService;


    public Page<JobListDto> getAllJobs(int start, int end) {
        Pageable pageable = PageRequest.of(start, end);
        Page<JobList> jobs = jobListRepository.findAll(pageable);

        Page<JobListDto> jobListDtos = jobs.map(jobList -> {
            return JobListDto.builder()
                    .id(jobList.getId())
                    .date(jobList.getDate())
                    .category(categoryService.mapToCategoryDto(categoryService.getCategoryById(jobList.getCategoryId()).get()))
                    .publisher(userService.mapToUserDto(userService.getUserById(jobList.getId()).get()))
                    .build();
        });

        return jobListDtos;
    }
}
