package com.example.demo.repository;

import com.example.demo.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findByJob(String job);
    List<Resume> findByUserId(int userId);

}
