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

    //выборка всех резюме
    public List<Resume> getAllResumes() {
        String sql = "select* from resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    //поиск резюме по id
    public Resume getResumeById(int id) {
        String sql = "select * from resumes where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    //добавление резюме
    public void addResume(Resume resume) {
        String sql = "INSERT INTO resumes (id, user_id, expected_salary,job) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, resume.getId(), resume.getUser_id(), resume.getExpected_salary(), resume.getJob());

    }

    //удаление резюме по id
    public void deleteResumeById(int resumeId) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, resumeId);

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete resume with ID: " + resumeId);
        }
    }

    //обновление резюме
    public void updateResume(Resume resume,int id) {
        String sql = "UPDATE resumes SET user_id = ?, job = ?, expected_salary = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, resume.getUser_id(), resume.getJob(), resume.getExpected_salary(),id);

        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to update resume with ID: " + resume.getId());
        }
    }

    public Resume getResumeByJob(String job){
        String sql="SELECT * FROM resumes where job = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Resume.class),job);
    }




}
