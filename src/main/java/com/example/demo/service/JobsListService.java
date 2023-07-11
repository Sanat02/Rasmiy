package com.example.demo.service;

import com.example.demo.dao.JobsListDao;
import com.example.demo.model.JobList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsListService {
    @Autowired
    private JobsListDao jobsListDao;

    public JobsListService(JobsListDao jobsListDao) {
        this.jobsListDao = jobsListDao;
    }
    public List<JobList> getAllJobs(){
        return jobsListDao.getAllJobs();
    }
    public List<JobList> getByCategory(String category){
        return jobsListDao.getJobByCategory(category);
    }
}
