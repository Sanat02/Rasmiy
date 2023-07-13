package com.example.demo.dao;

import com.example.demo.model.JobList;
import com.example.demo.model.User;
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
        String sql="select * from job_listings where category = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(JobList.class),category);
    }
}
