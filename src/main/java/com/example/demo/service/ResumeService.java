package com.example.demo.service;

import com.example.demo.dao.ResumeDao;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ResumeService {
    @Autowired
    private final ResumeDao resumeDao;

    public ResumeService(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    public List<Resume> getAllResumes(){
        return resumeDao.getAllResumes();
    }

    public List<Resume> getUserResumes(String email){
        return resumeDao.getResumesByUser(email);
    }

    public User getUserByPhone(String phone){return resumeDao.getUserByPhone(phone);}

}
