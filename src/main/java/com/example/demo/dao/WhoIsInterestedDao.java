package com.example.demo.dao;

import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import com.example.demo.model.WhoInterested;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WhoIsInterestedDao {
    private final JdbcTemplate jdbcTemplate;


    public List<WhoInterested> getAllInterestedList() {
        String sql = "select * from who_interested";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WhoInterested.class));
    }

    public List<WhoInterested> getInterestedApplicants(int id) {
        String sql = "select * from who_interested where job_resume_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WhoInterested.class), id);
    }

    public void createInterested(WhoInterested whoInterested) {
        String sql = "INSERT INTO who_interested(id,applicant_id,job_resume_id,date) " +
                "VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, whoInterested.getId(), whoInterested.getApplicant_id(),
                whoInterested.getJob_resume_id(), whoInterested.getDate());
    }

}
