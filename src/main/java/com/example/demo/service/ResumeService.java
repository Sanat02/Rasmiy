package com.example.demo.service;

import com.example.demo.dao.ResumeDao;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class ResumeService {
    @Autowired
    private final ResumeDao resumeDao;

    //BONUS
    public Optional<Resume> getResumeById(int resumeId) {
        // TODO: Реализовать получение резюме по идентификатору
        return Optional.empty();
    }



    public Resume createResume(Resume resume) {
        // TODO: Реализовать создание нового резюме
        return null;
    }

    public Resume updateResume(Resume resume) {
        // TODO: Реализовать обновление информации в резюме
        return null;
    }

    public void deleteResume(int resumeId) {
        // TODO: Реализовать удаление резюме по идентификатору
    }
    //Bonus окончен

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
