package com.example.demo.repository;

import com.example.demo.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education,Integer> {

    Optional<Education> findByResumeId(Integer integer);
}
