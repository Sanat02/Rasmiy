package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.JobResume;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobResumeRepository extends JpaRepository<JobResume,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE JobResume jr SET jr.jobTitle = :jobTitle, jr.jobDescription = :jobDescription, jr.experience = :experience, jr.salary = :salary, jr.category = :category WHERE jr.id = :id")
    void updateJobResume(
            Integer id,
            String jobTitle,
            String jobDescription,
            Integer experience,
            Double salary,
            Category category
    );

    List<JobResume> findJobResumesByUserId(int userId);
}
