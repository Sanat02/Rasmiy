package com.example.demo.dao;

import com.example.demo.model.JobList;
import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobsListDao {
    private final JdbcTemplate jdbcTemplate;


    public JobsListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Выборка всех вакансий.
    public List<JobList> getAllJobs() {
        String sql="select * from vacancies";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(JobList.class));
    }



    //Выборка вакансий по категориям.
    public List<JobList> getJobByCategory(String category){
        String sql="select * from vacancies where category = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(JobList.class),category);
    }

    //удаление вакансии по id
    public void deleteJob(int jobId) {
        String sql = "DELETE FROM vacancies WHERE id = ?";
        try {
            jdbcTemplate.update(sql, jobId);
        } catch (DataAccessException e) {
            // Handle any exceptions
            throw new RuntimeException("Failed to delete job: " + e.getMessage(), e);
        }
    }
    //поиск вакансии по id
    public JobList getJobById(int jobId) {
        String sql = "SELECT * FROM jobs WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JobList.class), jobId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }



}
