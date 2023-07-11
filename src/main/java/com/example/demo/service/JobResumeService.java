package com.example.demo.service;

import com.example.demo.dao.JobResumeDao;
import com.example.demo.model.JobResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobResumeService {
    @Autowired
    private JobResumeDao jobResumeDao;

    public JobResumeService(JobResumeDao jobResumeDao) {
        this.jobResumeDao = jobResumeDao;
    }

    public List<JobResume> getResumeByCategory(String category){
        return jobResumeDao.getResumeByCategory(category);
    }


}
