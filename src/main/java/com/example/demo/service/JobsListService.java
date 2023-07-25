package com.example.demo.service;

import com.example.demo.dao.JobsListDao;
import com.example.demo.dto.JobListDto;
import com.example.demo.model.JobList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class JobsListService {

    private final JobsListDao jobsListDao;
    private final UserService userService;

    public List<JobList> getByCategory(String category){
        log.info("Got job by category:"+category);
        return jobsListDao.getJobByCategory(category);
    }

    public List<JobListDto> getAllJobs(){
        List<JobList> jobLists=jobsListDao.getAllJobs();
        List<JobListDto> jobListDtos=jobLists.stream()
                .map(e->JobListDto.builder()
                        .id(e.getId())
                        .date(e.getDate())
                        .categoryId(e.getCategoryId())
                        .publisher(userService.getUserById(e.getId()).get()).build()
                ).toList();
        return jobListDtos;
    }
}
