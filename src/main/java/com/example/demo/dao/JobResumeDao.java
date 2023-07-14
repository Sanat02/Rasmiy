package com.example.demo.dao;

import com.example.demo.model.JobResume;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JobResumeDao {

    private final JdbcTemplate jdbcTemplate;


    //поиск резюме по категории
    public List<JobResume> getResumeByCategory(String category) {
        String sql = "select * from job_resumes where category = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), category);
    }

    //поиск резюме где зарплата <
    public List<JobResume> getResumeByCategory(Integer salary) {
        String sql = "select * from job_resumes where salary < ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), salary);
    }

    //поиск резюме где опыт >
    public List<JobResume> getResumeByCategory(int year) {
        String sql = "select * from job_resumes where experience > ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), year);
    }

    //поиск резюме по id
    public List<JobResume> getJobResumeById(int id) {
        String sql = "select * from job_resumes where id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class), id);
    }

    public List<JobResume> getAllJobResumes() {
        String sql = "select * from job_resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobResume.class));
    }
}
