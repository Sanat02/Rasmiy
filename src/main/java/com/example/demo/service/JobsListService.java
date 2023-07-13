package com.example.demo.service;

import com.example.demo.dao.JobsListDao;
import com.example.demo.dto.JobListDto;
import com.example.demo.model.JobList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobsListService {

    private final JobsListDao jobsListDao;
    private final UserService userService;

    public List<JobList> getByCategory(String category){
        return jobsListDao.getJobByCategory(category);
    }

    public List<JobListDto> getAllJobs(){
        List<JobList> jobLists=jobsListDao.getAllJobs();
        List<JobListDto> jobListDtos=jobLists.stream()
                .map(e->JobListDto.builder()
                        .id(e.getId())
                        .date(e.getDate())
                        .category(e.getCategory())
                        .publisher(userService.getUserById(e.getId())).build()
                ).toList();
        return jobListDtos;
    }
}
