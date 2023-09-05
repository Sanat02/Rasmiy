package com.example.demo.repository;

import com.example.demo.model.JobResume;
import com.example.demo.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findByJob(String job);
    List<Resume> findByUserId(int userId);

    Page<Resume> findAll(Pageable pageable);

    Page<JobResume> findAllByOrderByResumeDateDesc(Pageable pageable);

}
