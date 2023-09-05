package com.example.demo.repository;


import com.example.demo.model.JobList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListRepository extends JpaRepository<JobList,Integer> {
    Page<JobList> findAll(Pageable pageable);
}
