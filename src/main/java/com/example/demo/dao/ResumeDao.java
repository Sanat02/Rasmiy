package com.example.demo.dao;


import com.example.demo.model.Resume;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor

public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;


    public List<Resume> getAllResumes() {
        String sql = "SELECT id, user_id AS userId, job, expected_salary AS expectedSalary FROM resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Resume getResumeById(int id) {
        String sql = "SELECT id, user_id AS userId, job, expected_salary AS expectedSalary FROM resumes WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    public void addResume(Resume resume) {
        String sql = "INSERT INTO resumes (id, user_id, expected_salary, job) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, resume.getId(), resume.getUserId(), resume.getExpectedSalary(), resume.getJob());

    }

    public void deleteResumeById(int resumeId) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, resumeId);

    }

    public void updateResume(Resume resume) {
        String sql = "UPDATE resumes SET user_id = ?, job = ?, expected_salary = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, resume.getUserId(), resume.getJob(), resume.getExpectedSalary(), resume.getId());

    }

    public Resume getResumeByJob(String job) {
        String sql = "SELECT id, user_id AS userId, job, expected_salary AS expectedSalary FROM resumes WHERE job = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), job);
    }
}
