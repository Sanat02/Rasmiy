package com.example.demo.repository;

import com.example.demo.model.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobExperienceRepository extends JpaRepository<JobExperience,Integer> {
    Optional<JobExperience> findJobExperienceByResumeId(int resumeId);
}
