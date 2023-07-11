package com.example.demo.service;

import com.example.demo.dao.WhoIsInterestedDao;
import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WhoInterestedService {
    @Autowired
    private WhoIsInterestedDao whoIsInterestedDao;

    public WhoInterestedService(WhoIsInterestedDao whoIsInterestedDao) {
        this.whoIsInterestedDao = whoIsInterestedDao;
    }
    public List<User> getInterestedJobSeekers()
    {
        return whoIsInterestedDao.getInterestedJobSeekers();
    }

    public List<JobResume> getInterestedJobs()
    {
        return whoIsInterestedDao.getInterestedJobs();
    }
}
