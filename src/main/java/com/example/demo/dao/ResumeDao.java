package com.example.demo.dao;

import com.example.demo.enums.AccountType;
import com.example.demo.enums.ContactType;
import com.example.demo.model.Contacts;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component


public class ResumeDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ResumeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Resume> getAllResumes() {
        String sql = "select* from resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Resume getResumeById(int id) {
        String sql = "select * from resumes where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    public void addResume(Resume resume) {
        String sql = "INSERT INTO resumes (id, user_id, expected_salary,job_experience,education,job) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, resume.getId(), resume.getUser_id(), resume.getExpected_salary()
                , resume.getJob_experience(), resume.getEducation(), resume.getJob());

    }

    public void deleteResume(int resumeId) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, resumeId);

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete resume with ID: " + resumeId);
        }
    }




}
