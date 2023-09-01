package com.example.demo.service;


import com.example.demo.dto.JobListDto;
import com.example.demo.model.JobList;
import com.example.demo.repository.JobListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class JobsListService {
    private final JobListRepository jobListRepository;
    private final UserService userService;
    private final CategoryService categoryService;


    public Page<JobListDto> getAllJobs(int start, int end) {
        List<JobList> jobLists = jobListRepository.findAll();
        List<JobListDto> jobListDtos = jobLists.stream()
                .map(e -> JobListDto.builder()
                        .id(e.getId())
                        .date(e.getDate())
                        .category(categoryService.mapToCategoryDto(categoryService.getCategoryById(e.getCategoryId()).get()))
                        .publisher(userService.mapToUserDto(userService.getUserById(e.getId()).get())).build()
                ).collect(Collectors.toList());
        jobListDtos = sortByCategory(jobListDtos);
        var page = toPage(jobListDtos, PageRequest.of(start, end));
        return page;
    }

    private Page<JobListDto> toPage(List<JobListDto> list, Pageable pageable) {
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ?
                list.size() :
                pageable.getOffset() + pageable.getPageSize());
        List<JobListDto> subList = list.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, list.size());
    }

    private List<JobListDto> sortByCategory(List<JobListDto> jobs) {
        jobs.sort(Comparator.comparing(e -> e.getCategory().getName()));
        return jobs;
    }

}
